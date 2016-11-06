package tyler.jiqu.manager;

import java.util.List;

/**
 * @创建者 Tyler.
 * @创建时间 2016/11/3  22:01.
 * @描述 ${数据管理类}.
 */
public class DataManager implements DataGetter {
    private static DataManager sDataManager = new DataManager();

    private DataManager() {
    }

    public static DataManager getInstance() {
        return sDataManager;
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
