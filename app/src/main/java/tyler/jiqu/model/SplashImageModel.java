package tyler.jiqu.model;

/**
 * @创建者 Tyler.
 * @创建时间 2016/11/2  23:01.
 * @描述 ${Splash界面 image Moudle}.
 */
public class SplashImageModel {
    /**
     * text : Alex Blăjan
     * img : https://pic3.zhimg.com/v2-5ac59d94382fcb23eae38ef269119926.jpg
     */

    private String text;
    private String img;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "SplashImageModel{" +
                "text='" + text + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
