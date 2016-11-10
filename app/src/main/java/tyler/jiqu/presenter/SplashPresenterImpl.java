package tyler.jiqu.presenter;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import tyler.jiqu.model.SplashImageModel;
import tyler.jiqu.view.SplashView;

/**
 * @创建者 Tyler.
 * @创建时间 2016/11/2  22:19.
 * @描述 ${TODO}.
 */
public class SplashPresenterImpl implements SplashPresenter{

    private final SplashView mSplashView;

    private SplashImageModel mImageModule;

    public SplashPresenterImpl(SplashView splashView) {

        mSplashView = splashView;
    }

    @Override
    public void getSplashImage(String splashImageUrl) {

                OkHttpUtils.get()
                        .url(splashImageUrl)
                        .build()
                        .execute(new StringCallback() {

                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Gson gson = new Gson();
                                mImageModule = gson.fromJson(response, SplashImageModel.class);
                                mSplashView.showImage(mImageModule);
                            }
                        });
//        DataManager.getInstance().setOnDataFinish(this);
//        mImageModule = (SplashImageModel) DataManager.getInstance().getDataBean(splashImageUrl,
//                SplashImageModel.class);
//        if (mImageModule != null) {
//            mSplashView.showImage(mImageModule);
//        }
    }

//    @Override
//    public void onDataFinish(Object obj) {
//        mImageModule = (SplashImageModel) obj;
//        if (mImageModule != null) {
//        mSplashView.showImage(mImageModule);
//
//        }
//    }
}
