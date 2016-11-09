package tyler.jiqu.presenter;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import tyler.jiqu.model.ZhihuNewsDetaileModel;
import tyler.jiqu.view.ZhihuNewsDetaileActivity;
import tyler.jiqu.view.ZhihuNewsDetaileView;

/**
 * @创建者 Tyler.
 * @创建时间 2016/11/5  15:00.
 * @描述 ${TODO}.
 */
public class ZhihuNewsDetailePresenterImpl implements ZhihuNewsDetailePresenter {

    private final ZhihuNewsDetaileActivity mZhihuNewsDetaileView;
    private ZhihuNewsDetaileModel mZhihuNewsDetaileModel;

    public ZhihuNewsDetailePresenterImpl(ZhihuNewsDetaileView zhihuNewsDetaileView) {
        mZhihuNewsDetaileView = (ZhihuNewsDetaileActivity) zhihuNewsDetaileView;
    }

    @Override
    public void getData(String url) {
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response != null && !response.equals("")) {
                            Gson gson = new Gson();
                            mZhihuNewsDetaileModel = gson.fromJson(response,
                                    ZhihuNewsDetaileModel.class);
                            mZhihuNewsDetaileView.showView(mZhihuNewsDetaileModel);
                        }
                    }
                });

    }

}
