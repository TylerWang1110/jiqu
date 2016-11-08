package tyler.jiqu.util;

import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import tyler.jiqu.R;

/**
 * @创建者 Tyler.
 * @创建时间 2016/11/7  22:39.
 * @描述 ${TODO}.
 */
public class ThemeUtil {
    public static void switchTheme(Activity activity) {
        if (SPUtil.get(activity, "theme", "dayTheme").equals("dayTheme")) {
            //默认是白天主題
            activity.setTheme(R.style.dayTheme);
        } else {
            //否则是晚上主題
            activity.setTheme(R.style.nightTheme);
        }
    }

    public static void changeStatuBarColor(Activity activity) {
        Window window = activity.getWindow();
        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(activity.getResources().getColor(R.color.colorPrimaryDark));
        }
    }
}
