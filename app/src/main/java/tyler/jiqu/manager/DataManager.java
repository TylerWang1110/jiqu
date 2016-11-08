package tyler.jiqu.manager;

import java.util.List;

/**
 * @创建者 Tyler.
 * @创建时间 2016/11/3  22:01.
 * @描述 ${数据管理类}.
 */
public class DataManager implements HttpManager.OnDataFinishListener {
    private static DataManager sDataManager = new DataManager();
    private Object mData;
    private List<Object> mDataList;

    private OnDataFinish mOnDataFinish;

    public interface OnDataFinish {
        void onDataFinish(Object obj);
    }

    private DataManager() {
    }

    public static DataManager getInstance() {
        return sDataManager;
    }

    public Object getDataBean(String url, Class clazz) {
        mData = CacheManager.getInstence().getData(url,clazz);
        if (mData == null) {
            HttpManager.getInstance().setOnDataFinishListener(this);
            HttpManager.getInstance().getData(url, clazz);
        }
        return mData;
    }

    public List<Object> getDataList(String url, Class clazz) {

        return null;
    }


    @Override
    public void onDataFinish(Object obj) {
        if (mOnDataFinish != null) {
            mOnDataFinish.onDataFinish(obj);
        }
    }

    public void setOnDataFinish(OnDataFinish onDataFinish) {
        mOnDataFinish = onDataFinish;
    }
}
