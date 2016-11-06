package tyler.jiqu.manager;

import java.util.List;

/**
 * @创建者 Tyler.
 * @创建时间 2016/11/3  22:01.
 * @描述 ${网络管理类}.
 */
public class HttpManager implements DataGetter {
    private static HttpManager sHttpManager = new HttpManager();

    private HttpManager() {
    }

    public static HttpManager getInstance() {
        return sHttpManager;
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
