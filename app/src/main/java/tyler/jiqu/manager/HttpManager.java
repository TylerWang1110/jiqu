package tyler.jiqu.manager;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;

import okhttp3.Call;
import tyler.jiqu.globle.JiquApplacation;
import tyler.jiqu.util.FileUtil;
import tyler.jiqu.util.Md5Utils;

/**
 * @创建者 Tyler.
 * @创建时间 2016/11/3  22:01.
 * @描述 ${网络管理类}.
 */
public class HttpManager {
    private static HttpManager sHttpManager = new HttpManager();

    private OnDataFinishListener mOnDataFinishListener;

    private HttpManager() {
    }

    public static HttpManager getInstance() {
        return sHttpManager;
    }

    public interface OnDataFinishListener {
        void onDataFinish(Object obj);
    }

    public void getData(final String url, final Class clazz) {
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
                            String name = Md5Utils.encode(url);
                            String path = JiquApplacation.sCacheDir.getAbsolutePath() + File.separator + name;
                            FileUtil.saveString(response, path);
                            Gson gson = new Gson();
                            Object obj = gson.fromJson(response, clazz);
                            //FIXME 通知界面
                            if (mOnDataFinishListener != null) {
                                mOnDataFinishListener.onDataFinish(obj);
                            }
                        }
                    }
                });
    }

    public void setOnDataFinishListener(OnDataFinishListener onDataFinishListener) {
        mOnDataFinishListener = onDataFinishListener;
    }
}
