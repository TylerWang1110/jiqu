package tyler.jiqu.widget;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * @创建者 Tyler.
 * @创建时间 2016/11/4  19:35.
 * @描述 ${配合banner使用的图片加载器}.
 */
public class GlideImageLoader implements ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
    }
}
