package tyler.jiqu.presenter;

import tyler.jiqu.manager.HttpManager;
import tyler.jiqu.model.ZhihuNewsCommentsModel;
import tyler.jiqu.view.ZhihuNewsCommentsActivity;

/**
 * @创建者 Tyler.
 * @创建时间 2016/11/9  14:05.
 * @描述 ${TODO}.
 */
public class ZhihuNewsCommentsPresenterImpl implements ZhihuNewsCommentsPresenter, HttpManager
        .OnDataFinishListener {

    private final ZhihuNewsCommentsActivity mZhihuNewsCommentsActivity;

    public ZhihuNewsCommentsPresenterImpl(ZhihuNewsCommentsActivity activity) {
        mZhihuNewsCommentsActivity = activity;
    }

    @Override
    public void getData(String url) {
        HttpManager.getInstance().setOnDataFinishListener(this);
        HttpManager.getInstance().getData(url, ZhihuNewsCommentsModel.class);
    }

    @Override
    public void onDataFinish(Object obj) {
        ZhihuNewsCommentsModel zhihuNewsCommentsModel = (ZhihuNewsCommentsModel) obj;
        if (zhihuNewsCommentsModel != null) {
            mZhihuNewsCommentsActivity.showView(zhihuNewsCommentsModel);
        }
    }
}
