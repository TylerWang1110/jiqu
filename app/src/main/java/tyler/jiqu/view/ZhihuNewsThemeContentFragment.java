package tyler.jiqu.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tyler.jiqu.R;
import tyler.jiqu.adapter.ZhihuNewsThemeContentAdapter;
import tyler.jiqu.globle.Const;
import tyler.jiqu.model.ZhihuNewsThemeContentModel;
import tyler.jiqu.model.ZhihuNewsThemeModel;
import tyler.jiqu.presenter.ZhihuNewsThemeContentPresenterImpl;

/**
 * @创建者 Tyler.
 * @创建时间 2016/11/7  15:16.
 * @描述 ${TODO}.
 */
@SuppressLint("ValidFragment")
public class ZhihuNewsThemeContentFragment extends Fragment implements ZhihuNewsThemeContentView,
        SwipeRefreshLayout.OnRefreshListener, ZhihuNewsThemeContentAdapter.OnItemClickListener {

    private ZhihuNewsThemeModel.OthersBean mOthersBean = null;
    @Bind(R.id.rv_themecontent)
    RecyclerView mRvThemecontent;
    @Bind(R.id.srl_themecontent)
    SwipeRefreshLayout mSrlThemecontent;
    private ZhihuNewsThemeContentPresenterImpl mZhihuNewsThemeContentPresenterImpl;
    private int mId;
    private boolean mIsRefresh;
    private List<ZhihuNewsThemeContentModel.StoriesBean> mStories = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_themecontent_zhihu, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mZhihuNewsThemeContentPresenterImpl = new ZhihuNewsThemeContentPresenterImpl(this);
        ButterKnife.bind(this, view);
        init();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public ZhihuNewsThemeContentFragment(ZhihuNewsThemeModel.OthersBean othersBean) {
        mOthersBean = othersBean;
    }


    private void init() {
        mRvThemecontent.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mId = mOthersBean.getId();
        mZhihuNewsThemeContentPresenterImpl.getThemeContentData(Const.URL_ZHIHU_NEWS_THEME_NEWS + mId);
        mSrlThemecontent.setColorSchemeColors(getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorAccent), getResources().getColor(R.color
                        .colorPrimaryDark));
        mSrlThemecontent.setOnRefreshListener(this);
    }

    @Override
    public void showView(final ZhihuNewsThemeContentModel zhihuNewsThemeContentModel) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (mIsRefresh) {
                    SystemClock.sleep(1500);
                }
                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mStories = zhihuNewsThemeContentModel.getStories();
                        ZhihuNewsThemeContentAdapter adapter = new ZhihuNewsThemeContentAdapter
                                (mStories, getContext());
                        mRvThemecontent.setAdapter(adapter);
                        RelativeLayout headView = (RelativeLayout) LayoutInflater.from(getContext())
                                .inflate(R.layout
                                        .layout_zhihu_news_theme_head, mRvThemecontent, false);
                        ImageView ivHead = (ImageView) headView.findViewById(R.id.iv_zhihunews_theme_head);
                        TextView tvHead = (TextView) headView.findViewById(R.id.tv_zhihunews_theme_head);
                        Glide.with(getContext())
                                .load(zhihuNewsThemeContentModel.getImage())
                                .into(ivHead);
                        tvHead.setText(zhihuNewsThemeContentModel.getDescription());
                        adapter.setHeadView(headView);
                        adapter.setOnItemClickListener(ZhihuNewsThemeContentFragment.this);
                        mSrlThemecontent.setRefreshing(false);
                    }
                });
            }
        }).start();
    }


    public RecyclerView getRvThemecontent() {
        return mRvThemecontent;
    }

    @Override
    public void onRefresh() {
        mZhihuNewsThemeContentPresenterImpl.getThemeContentData(Const.URL_ZHIHU_NEWS_THEME_NEWS + mId);
        mIsRefresh = true;
    }


    @Override
    public void OnItemClick(View v, int position) {
        ZhihuNewsThemeContentModel.StoriesBean storiesBean = mStories.get(position - 1);
        Intent intent = new Intent(getActivity(), ZhihuNewsDetaileActivity.class);
        intent.putExtra(ZhihuNewsDetaileActivity.NEWS_ID, storiesBean.getId());
        startActivity(intent);
    }
}
