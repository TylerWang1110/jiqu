package tyler.jiqu.manager;

import android.widget.FrameLayout;

/**
 * @创建者 Tyler.
 * @创建时间 2016/11/3  23:23.
 * @描述 ${页面管理器}.
 */
public class PageManger {

    //知乎日报
    public static final int PAGETYPE_ZHIHUNEWS = 0;
    //    public static final int PAGETYPE_ZHIHUNEWS = 0;

    private static PageManger sPageManger = new PageManger();

    private PageManger() {
    }

    public static PageManger getInstance() {
        return sPageManger;
    }

    /**
     * 切换首页页面展示
     *
     * @param pageType
     * @return
     */
    public FrameLayout getPage(int pageType) {

        switch (pageType) {
            case PAGETYPE_ZHIHUNEWS:

                break;

            default:
                break;

        }

        return null;
    }
}
