package tyler.jiqu.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tyler.jiqu.R;
import tyler.jiqu.adapter.ZhihuNewsContentAdapter;
import tyler.jiqu.db.ZhihuNewsDaoHelper;
import tyler.jiqu.db.ZhihuNewsReaded;
import tyler.jiqu.globle.Const;
import tyler.jiqu.manager.HttpManager;
import tyler.jiqu.model.ZhihuNewsModel;
import tyler.jiqu.presenter.ZhihuNewsPresenterImpl;
import tyler.jiqu.util.DateUtil;
import tyler.jiqu.util.L;
import tyler.jiqu.widget.GlideImageLoader;

/**
 * @创建者 Tyler.
 * @创建时间 2016/11/3  22:57.
 * @描述 ${知乎日报首页Fragment界面}.
 */
public class ZhihuNewsFragment extends Fragment implements ZhihuNewsView, SwipeRefreshLayout
        .OnRefreshListener, ZhihuNewsContentAdapter.MyItemClicklistener, View.OnTouchListener {

    private static final String TAG = "ZhihuNewsFragment";
    @Bind(R.id.rv_main_zhihu_news)
    RecyclerView mRvMainZhihuNews;
    @Bind(R.id.banner_main_zhihu_head)
    Banner mBannerMainZhihuHead;
    @Bind(R.id.srl_main_zhihu)
    SwipeRefreshLayout mSrlMainZhihu;
    @Bind(R.id.sv_main_zhihu)
    NestedScrollView mSvMainZhihu;
    @Bind(R.id.tv_snackbar)
    TextView mTvSnackbar;
    private ZhihuNewsPresenterImpl mZhihuNewsPresenter;
    private List<ZhihuNewsModel.TopStoriesBean> mTop_stories = new ArrayList<>();
    private List<ZhihuNewsModel.StoriesBean> mStories = new ArrayList<>();
    private String mDate;
    private LinearLayoutManager mLayoutManager;
    private String mNewDateString;
    private boolean mIsGetMore;
    private ZhihuNewsContentAdapter mContentAdapter;
    private boolean mIsRefresh;
    private Intent mIntent;
    private Dao<ZhihuNewsReaded, Integer> mDao;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
    Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_zhihu, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        mZhihuNewsPresenter = new ZhihuNewsPresenterImpl(this);
        mSrlMainZhihu.setColorSchemeColors(getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorAccent), getResources().getColor(R.color
                        .colorPrimaryDark));
        mSrlMainZhihu.setOnRefreshListener(this);
        mSvMainZhihu.setOnTouchListener(this);
        ZhihuNewsDaoHelper zhihuNewsDaoHelper = new ZhihuNewsDaoHelper(getContext());
        try {
            mDao = zhihuNewsDaoHelper.getDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mZhihuNewsPresenter.getData(Const.URL_ZHIHU_NEWS_LATEST);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 展示数据
     *
     * @param zhihuNewsModel
     */
    public void showView(final ZhihuNewsModel zhihuNewsModel) {
        mDate = zhihuNewsModel.getDate();
        System.out.println("hhhhhhhhhhhhhhhh" + mIsGetMore);
        if (!mIsGetMore) {
            mTop_stories = zhihuNewsModel.getTop_stories();
            showHeadView();
        }

        if (mIsRefresh) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(1000);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mSrlMainZhihu.setRefreshing(false);
                            mStories.clear();
                            mStories.addAll(zhihuNewsModel.getStories());
                            mContentAdapter.notifyDataSetChanged();
                            mIsRefresh = false;
                        }
                    });
                }
            }).start();
        } else {
            mStories.addAll(zhihuNewsModel.getStories());
            showContentView();
        }
        //        ZhihuNewsHeadAdapter zhihuNewsHeadAdapter = new ZhihuNewsHeadAdapter
        // (mTop_stories);
    }


    /**
     * 显示内容布局
     */
    private void showContentView() {
        if (mIsGetMore) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(500);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mContentAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }).start();
            return;
        }
        mLayoutManager = new LinearLayoutManager(this.getContext());
        mRvMainZhihuNews.setLayoutManager(mLayoutManager);
        mContentAdapter = new ZhihuNewsContentAdapter(mStories, mDate, this.getContext());
        mContentAdapter.setMyItemClicklistener(this);
        mRvMainZhihuNews.setAdapter(mContentAdapter);

    }

    /**
     * 显示头布局 轮播图
     */
    private void showHeadView() {
        ArrayList<String> images = new ArrayList<>();
        final ArrayList<String> titles = new ArrayList<>();
        for (ZhihuNewsModel.TopStoriesBean top_story : mTop_stories) {
            images.add(top_story.getImage());
            titles.add(top_story.getTitle());
        }

        if (mBannerMainZhihuHead != null) {

            mBannerMainZhihuHead
                    .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                    .setImages(images)
                    .setImageLoader(new GlideImageLoader())
                    .setBannerTitles(titles)
                    .start();
            mBannerMainZhihuHead.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    //FIXME: 进入详情页
                    ZhihuNewsModel.TopStoriesBean topStoriesBean = mTop_stories.get(position - 1);
                    int id = topStoriesBean.getId();
                    try {
                        mDao.create(new ZhihuNewsReaded(id, ZhihuNewsReaded.ISREADED_TRUE));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    mIntent = new Intent(getContext(), ZhihuNewsDetaileActivity.class);
                    mIntent.putExtra(ZhihuNewsDetaileActivity.NEWS_ID, id);
                    startActivity(mIntent);
                }


            });
        }
    }


    @Override
    public void onRefresh() {
        //        mZhihuNewsPresenter.getData(Const.URL_ZHIHU_NEWS_LATEST);
        HttpManager.getInstance().setOnDataFinishListener(new HttpManager.OnDataFinishListener() {
            @Override
            public void onDataFinish(Object obj) {
                if (obj != null) {
                    showView((ZhihuNewsModel) obj);
                }
            }
        });
        HttpManager.getInstance().getData(Const.URL_ZHIHU_NEWS_LATEST, ZhihuNewsModel.class);

        mIsRefresh = true;
    }

    @Override
    public void onItemClick(View v, int position) {
        //FIXME 进入详情页
        ZhihuNewsModel.StoriesBean storiesBean = mStories.get(position);
        int id = storiesBean.getId();
        try {
            mDao.create(new ZhihuNewsReaded(id, ZhihuNewsReaded.ISREADED_TRUE));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mIntent = new Intent(getContext(), ZhihuNewsDetaileActivity.class);
        mIntent.putExtra(ZhihuNewsDetaileActivity.NEWS_ID, id);
        startActivity(mIntent);
        TextView tv = (TextView) v.findViewById(R.id.tv_item_zhihunews_content);
        tv.setTextColor(getContext().getResources().getColor(R.color.text_gray));
    }

    public NestedScrollView getSvMainZhihu() {
        return mSvMainZhihu;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                int scrollY = v.getScrollY();
                int height = v.getHeight();
                int scrollViewMeasuredHeight = mSvMainZhihu.getChildAt(0).getMeasuredHeight();
                if (scrollY == 0) {
                    L.d(TAG, "滑动到了顶端 view.getScrollY()=" + scrollY);
                }
                if ((scrollY + height) >= scrollViewMeasuredHeight - 150) {
                    L.d(TAG, "滑动到了底部 scrollY=" + scrollY);
                    L.d(TAG, "滑动到了底部 height=" + height);
                    L.d(TAG, "滑动到了底部 scrollViewMeasuredHeight=" + scrollViewMeasuredHeight);
                    mIsGetMore = true;
                    //FIXME 加载更多
                    Snackbar.make(mTvSnackbar, "加载中...", Snackbar.LENGTH_SHORT).show();
                    try {
                        Date date = DateUtil.stringToDate(mDate);
                        mNewDateString = DateUtil.dateToNum(DateUtil.getDayBefore(date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    L.d(TAG, Const.URL_ZHIHU_NEWS_BEFORE + mNewDateString);
                    mZhihuNewsPresenter.getData(Const.URL_ZHIHU_NEWS_BEFORE + mNewDateString);

                }
            default:
                break;
        }
        return false;
    }

}
