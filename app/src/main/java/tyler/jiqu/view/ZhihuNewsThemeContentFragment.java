package tyler.jiqu.view;

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
public class ZhihuNewsThemeContentFragment extends Fragment implements ZhihuNewsThemeContentView,
        SwipeRefreshLayout.OnRefreshListener {

    private final ZhihuNewsThemeModel.OthersBean mOthersBean;
    @Bind(R.id.rv_themecontent)
    RecyclerView mRvThemecontent;
    @Bind(R.id.srl_themecontent)
    SwipeRefreshLayout mSrlThemecontent;
    private ZhihuNewsThemeContentPresenterImpl mZhihuNewsThemeContentPresenterImpl;
    private int mId;
    private boolean mIsRefresh;

    public ZhihuNewsThemeContentFragment(ZhihuNewsThemeModel.OthersBean othersBean) {
        mOthersBean = othersBean;
        mZhihuNewsThemeContentPresenterImpl = new ZhihuNewsThemeContentPresenterImpl(this);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_themecontent_zhihu, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        init();
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
                        mRvThemecontent.setAdapter(new ZhihuNewsThemeContentAdapter(zhihuNewsThemeContentModel
                                .getStories()
                                , getContext()));
                        mSrlThemecontent.setRefreshing(false);
                    }
                });
            }
        }).start();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public RecyclerView getRvThemecontent() {
        return mRvThemecontent;
    }

    @Override
    public void onRefresh() {
        mZhihuNewsThemeContentPresenterImpl.getThemeContentData(Const.URL_ZHIHU_NEWS_THEME_NEWS + mId);
        mIsRefresh = true;
    }

}
