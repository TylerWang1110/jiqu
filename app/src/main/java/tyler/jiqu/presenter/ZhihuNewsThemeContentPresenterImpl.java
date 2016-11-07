package tyler.jiqu.presenter;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import tyler.jiqu.model.ZhihuNewsThemeContentModel;
import tyler.jiqu.view.ZhihuNewsThemeContentFragment;

/**
 * @创建者 Tyler.
 * @创建时间 2016/11/7  15:19.
 * @描述 ${TODO}.
 */
public class ZhihuNewsThemeContentPresenterImpl implements ZhihuNewsThemeContentPresenter {

    private ZhihuNewsThemeContentFragment mZhihuNewsThemeContentFragment;

    public ZhihuNewsThemeContentPresenterImpl(ZhihuNewsThemeContentFragment zhihuNewsThemeContentFragment) {
        this.mZhihuNewsThemeContentFragment = zhihuNewsThemeContentFragment;
    }

    @Override
    public void getThemeContentData(String url) {
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response != null) {
                            Gson gson = new Gson();
                            ZhihuNewsThemeContentModel zhihuNewsThemeContentModel = gson.fromJson(response,
                                    ZhihuNewsThemeContentModel.class);
                                mZhihuNewsThemeContentFragment.showView(zhihuNewsThemeContentModel);
                        }
                    }
                });
    }
}
