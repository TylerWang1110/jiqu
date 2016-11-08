package tyler.jiqu.presenter;

import tyler.jiqu.manager.DataManager;
import tyler.jiqu.model.ZhihuNewsThemeModel;
import tyler.jiqu.view.MainActivity;
import tyler.jiqu.view.MainView;

/**
 * @创建者 Tyler.
 * @创建时间 2016/11/3  23:01.
 * @描述 ${TODO}.
 */
public class MainPresenterImpl implements MainPresenter, DataManager.OnDataFinish {

    private MainActivity mMainActivity;
    private ZhihuNewsThemeModel mZhihuNewsThemeModel;

    public MainPresenterImpl(MainView view) {
        this.mMainActivity = (MainActivity) view;
    }

    public void getNewsThemesList(String url) {
        //        OkHttpUtils.get()
        //                .url(url)
        //                .build()
        //                .execute(new StringCallback() {
        //                    @Override
        //                    public void onError(Call call, Exception e, int id) {
        //
        //                    }
        //
        //                    @Override
        //                    public void onResponse(String response, int id) {
        //                        if (response != null) {
        //                            Gson gson = new Gson();
        //                            ZhihuNewsThemeModel zhihuNewsThemeModel = gson.fromJson(response,
        //                                    ZhihuNewsThemeModel.class);
        //                            if (zhihuNewsThemeModel != null) {
        //                                mMainActivity.showNavView(zhihuNewsThemeModel);
        //                            }
        //                        }
        //                    }
        //                });
        DataManager.getInstance().setOnDataFinish(this);
        mZhihuNewsThemeModel = (ZhihuNewsThemeModel) DataManager.getInstance()
                .getDataBean(url, ZhihuNewsThemeModel.class);
        if (mZhihuNewsThemeModel != null) {
            mMainActivity.showNavView(mZhihuNewsThemeModel);
        }

    }

    @Override
    public void onDataFinish(Object obj) {
        mZhihuNewsThemeModel = (ZhihuNewsThemeModel) obj;
        if (mZhihuNewsThemeModel != null) {
            mMainActivity.showNavView(mZhihuNewsThemeModel);
        }
    }
}
