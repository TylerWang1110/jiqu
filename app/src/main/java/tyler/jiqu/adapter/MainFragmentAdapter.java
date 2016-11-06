package tyler.jiqu.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * @创建者 Tyler.
 * @创建时间 2016/11/3  22:00.
 * @描述 ${首页Viewpage Adapter}.
 */
public class MainFragmentAdapter extends PagerAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
