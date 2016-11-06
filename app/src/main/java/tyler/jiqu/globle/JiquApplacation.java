package tyler.jiqu.globle;

import android.app.Application;
import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @创建者 Tyler.
 * @创建时间 2016/11/2  22:46.
 * @描述 ${TODO}.
 */
public class JiquApplacation extends Application {

    public static Context sAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sAppContext = getApplicationContext();
        initThridFrame();
    }

    /**
     * 初始化第三方框架
     */
    private void initThridFrame() {
        initOkhttpUtil();
    }

    /**
     * 初始化okhttputil
     */
    private void initOkhttpUtil() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }

}
