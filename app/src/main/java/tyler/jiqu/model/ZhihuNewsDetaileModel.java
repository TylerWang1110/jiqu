package tyler.jiqu.model;

import java.util.List;

/**
 * @创建者 Tyler.
 * @创建时间 2016/11/5  15:13.
 * @描述 ${ZhihuNewsDetaileModel}.
 */
public class ZhihuNewsDetaileModel {


//    分析：
//
//    body : HTML 格式的新闻
//    image-source : 图片的内容提供方。为了避免被起诉非法使用图片，在显示图片时最好附上其版权信息。
//    title : 新闻标题
//    image : 获得的图片同 最新消息 获得的图片分辨率不同。这里获得的是在文章浏览界面中使用的大图。
//    share_url : 供在线查看内容与分享至 SNS 用的 URL
//    js : 供手机端的 WebView(UIWebView) 使用
//    ga_prefix : 供 Google Analytics 使用
//    type : 新闻的类型
//    id : 新闻的 id
//    css : 供手机端的 WebView(UIWebView) 使用
//    可知，知乎日报的文章浏览界面利用 WebView(UIWebView) 实现

//    在较为特殊的情况下，知乎日报可能将某个主题日报的站外文章推送至知乎日报首页。
//    此时返回的 JSON 数据缺少 body，iamge-source，image，js 属性。
//    多出 theme_name，editor_name，theme_id 三个属性。type 由0 变为 1。

    private String body;
    private String image_source;
    private String title;
    private String image;
    private String share_url;
    private String ga_prefix;
    private String type;
    private String id;
    private String theme_name;
    private String editor_name;
    private String theme_id;
    private List<String> js;
    private List<String> images;
    private List<String> css;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getJs() {
        return js;
    }

    public void setJs(List<String> js) {
        this.js = js;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }

    public String getTheme_name() {
        return theme_name;
    }

    public void setTheme_name(String theme_name) {
        this.theme_name = theme_name;
    }

    public String getEditor_name() {
        return editor_name;
    }

    public void setEditor_name(String editor_name) {
        this.editor_name = editor_name;
    }

    public String getTheme_id() {
        return theme_id;
    }

    public void setTheme_id(String theme_id) {
        this.theme_id = theme_id;
    }

    @Override
    public String toString() {
        return "ZhihuNewsDetaileModel{" +
                "body='" + body + '\'' +
                ", image_source='" + image_source + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", share_url='" + share_url + '\'' +
                ", ga_prefix='" + ga_prefix + '\'' +
                ", type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", js=" + js +
                ", images=" + images +
                ", css=" + css +
                '}';
    }
}
