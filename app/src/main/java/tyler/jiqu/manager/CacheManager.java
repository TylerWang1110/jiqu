package tyler.jiqu.manager;

import java.util.List;

/**
 * @创建者 Tyler.
 * @创建时间 2016/11/3  22:02.
 * @描述 ${缓存管理类}.
 */
public class CacheManager implements DataGetter{

    private static  CacheManager sCacheManager = new CacheManager();

    private CacheManager() {
    }

    public static CacheManager getInstence() {
        return sCacheManager;
    }

    @Override
    public Object getDataBean() {
        return null;
    }

    @Override
    public List<Object> getDataList() {
        return null;
    }
}
