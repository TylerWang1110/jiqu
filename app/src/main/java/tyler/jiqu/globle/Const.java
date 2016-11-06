package tyler.jiqu.globle;

/**
 * @创建者 Tyler.
 * @创建时间 2016/11/2  22:39.
 * @描述 ${全局常量}.
 */
public class Const {

    public static final String ZHIHU_NEWS = "zhihunews";
    //知乎日报 BaseURL
    public static final String URL_ZHIHU_BASE = "http://news-at.zhihu.com/api/4/";
    //知乎日报 splash页面URL
    public static final String URL_SPLASH_IMAGE = URL_ZHIHU_BASE + "start-image/720*1184";
    //知乎日报 最新URL
    public static final String URL_ZHIHU_NEWS_LATEST = URL_ZHIHU_BASE + "news/latest";
    //    http://news.at.zhihu.com/api/4/news/before/20131119
    //知乎日报 历史URL
    public static final String URL_ZHIHU_NEWS_BEFORE = "http://news.at.zhihu.com/api/4/news/before/";

    //知乎日报 详情页URL  + ID
    public static final String URL_ZHIHU_NEWS_DETAILE = "http://news-at.zhihu.com/api/4/news/";
}
