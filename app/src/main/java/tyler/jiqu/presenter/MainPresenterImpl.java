package tyler.jiqu.presenter;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import tyler.jiqu.model.ZhihuNewsThemeModel;
import tyler.jiqu.view.MainActivity;
import tyler.jiqu.view.MainView;

/**
 * @创建者 Tyler.
 * @创建时间 2016/11/3  23:01.
 * @描述 ${TODO}.
 */
public class MainPresenterImpl implements MainPresenter {

    private MainActivity mMainActivity;

    public MainPresenterImpl(MainView view) {
        this.mMainActivity = (MainActivity) view;
    }

    public void getNewsThemesList(String url) {
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
                            ZhihuNewsThemeModel zhihuNewsThemeModel = gson.fromJson(response,
                                    ZhihuNewsThemeModel.class);
                            if (zhihuNewsThemeModel != null) {
                                mMainActivity.showNavView(zhihuNewsThemeModel);
                            }
                        }
                    }
                });
    }
}
