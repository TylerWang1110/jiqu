package tyler.jiqu.presenter;

import tyler.jiqu.manager.DataManager;
import tyler.jiqu.model.ZhihuNewsThemeContentModel;
import tyler.jiqu.view.ZhihuNewsThemeContentFragment;

/**
 * @创建者 Tyler.
 * @创建时间 2016/11/7  15:19.
 * @描述 ${TODO}.
 */
public class ZhihuNewsThemeContentPresenterImpl implements ZhihuNewsThemeContentPresenter, DataManager
        .OnDataFinish {

    private ZhihuNewsThemeContentFragment mZhihuNewsThemeContentFragment;
    private ZhihuNewsThemeContentModel mZhihuNewsThemeContentModel;

    public ZhihuNewsThemeContentPresenterImpl(ZhihuNewsThemeContentFragment zhihuNewsThemeContentFragment) {
        this.mZhihuNewsThemeContentFragment = zhihuNewsThemeContentFragment;
    }

    @Override
    public void getThemeContentData(String url) {
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
        //                            ZhihuNewsThemeContentModel zhihuNewsThemeContentModel = gson.fromJson
        // (response,
        //                                    ZhihuNewsThemeContentModel.class);
        //                                mZhihuNewsThemeContentFragment.showView(zhihuNewsThemeContentModel);
        //                        }
        //                    }
        //                });
        DataManager.getInstance().setOnDataFinish(this);
        mZhihuNewsThemeContentModel = (ZhihuNewsThemeContentModel) DataManager.getInstance().getDataBean
                (url, ZhihuNewsThemeContentModel.class);

        if (mZhihuNewsThemeContentModel != null) {
            mZhihuNewsThemeContentFragment.showView(mZhihuNewsThemeContentModel);
        }
    }

    @Override
    public void onDataFinish(Object obj) {

        mZhihuNewsThemeContentModel = (ZhihuNewsThemeContentModel) obj;
        if (mZhihuNewsThemeContentModel != null) {
            mZhihuNewsThemeContentFragment.showView(mZhihuNewsThemeContentModel);
        }
    }
}
