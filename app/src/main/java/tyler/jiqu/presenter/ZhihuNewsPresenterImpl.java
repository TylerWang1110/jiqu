package tyler.jiqu.presenter;

import tyler.jiqu.manager.DataManager;
import tyler.jiqu.model.ZhihuNewsModel;
import tyler.jiqu.view.ZhihuNewsFragment;
import tyler.jiqu.view.ZhihuNewsView;

/**
 * @创建者 Tyler.
 * @创建时间 2016/11/3  23:02.
 * @描述 ${TODO}.
 */
public class ZhihuNewsPresenterImpl implements ZhihuNewsPresenter, DataManager.OnDataFinish {

    private final ZhihuNewsFragment mZhihuNewsFragment;
    private ZhihuNewsModel mZhihuNewsModel;

    public ZhihuNewsPresenterImpl(ZhihuNewsView zhihuNewsView) {
        mZhihuNewsFragment = (ZhihuNewsFragment) zhihuNewsView;
    }

    @Override
    public void getData(String url) {
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
        //                        Gson gson = new Gson();
        //                        ZhihuNewsModel zhihuNewsModel = gson.fromJson(response, ZhihuNewsModel
        // .class);
        //                        if (zhihuNewsModel != null) {
        //                            mZhihuNewsFragment.showView(zhihuNewsModel);
        //                        }
        //                    }
        //                });
        DataManager.getInstance().setOnDataFinish(this);
        mZhihuNewsModel = (ZhihuNewsModel) DataManager.getInstance().getDataBean(url, ZhihuNewsModel.class);
        if (mZhihuNewsModel != null) {
            mZhihuNewsFragment.showView(mZhihuNewsModel);
        }
    }


    @Override
    public void onDataFinish(Object obj) {
        mZhihuNewsModel = (ZhihuNewsModel) obj;
        if (mZhihuNewsModel != null) {

            mZhihuNewsFragment.showView(mZhihuNewsModel);
        }
    }
}
