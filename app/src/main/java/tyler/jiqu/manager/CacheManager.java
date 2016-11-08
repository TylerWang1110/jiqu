package tyler.jiqu.manager;

import com.google.gson.Gson;

import java.io.File;

import tyler.jiqu.globle.JiquApplacation;
import tyler.jiqu.util.FileUtil;
import tyler.jiqu.util.Md5Utils;

/**
 * @创建者 Tyler.
 * @创建时间 2016/11/3  22:02.
 * @描述 ${缓存管理类}.
 */
public class CacheManager {

    private static CacheManager sCacheManager = new CacheManager();

    private CacheManager() {

    }

    public static CacheManager getInstence() {
        return sCacheManager;
    }

    public Object getData(String url, Class clazz) {
        String md5 = Md5Utils.encode(url);
        try {
            String data = FileUtil.readString(JiquApplacation.sCacheDir.getAbsolutePath
                    () + File.separator + md5);
            if (data != null && !data.equals("")) {
                Gson gson = new Gson();
                return gson.fromJson(data, clazz);
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
