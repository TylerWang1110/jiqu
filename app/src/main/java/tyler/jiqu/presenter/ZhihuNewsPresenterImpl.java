package tyler.jiqu.presenter;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import tyler.jiqu.model.ZhihuNewsModel;
import tyler.jiqu.view.ZhihuNewsFragment;
import tyler.jiqu.view.ZhihuNewsView;

/**
 * @创建者 Tyler.
 * @创建时间 2016/11/3  23:02.
 * @描述 ${TODO}.
 */
public class ZhihuNewsPresenterImpl implements ZhihuNewsPresenter {

    private final ZhihuNewsFragment mZhihuNewsFragment;

    public ZhihuNewsPresenterImpl(ZhihuNewsView zhihuNewsView) {
        mZhihuNewsFragment = (ZhihuNewsFragment) zhihuNewsView;
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
                        Gson gson = new Gson();
                        ZhihuNewsModel zhihuNewsModel = gson.fromJson(response, ZhihuNewsModel.class);
                        if (zhihuNewsModel != null) {
                            mZhihuNewsFragment.showView(zhihuNewsModel);
                        }
                    }
                });
    }


}
