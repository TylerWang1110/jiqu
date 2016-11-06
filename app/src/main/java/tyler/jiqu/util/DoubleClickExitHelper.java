//package tyler.jiqu.util;
//
//import android.app.Activity;
//import android.os.Handler;
//import android.os.Looper;
//import android.support.design.widget.Snackbar;
//import android.view.KeyEvent;
//import android.view.View;
//
///**
// * @创建者 Tyler.
// * @创建时间 2016/11/5  12:54.
// * @描述 ${TODO}.
// */
//public class DoubleClickExitHelper {
//    private final Activity mActivity;
//
//    private boolean isOnKeyBacking;
//    private Handler mHandler;
//    private Snackbar snackbar;
//
//    public DoubleClickExitHelper(Activity activity) {
//        mActivity = activity;
//        mHandler = new Handler(Looper.getMainLooper());
//        AppManager.getAppManager().addActivity(mActivity);
//    }
//
//    /**
//     * Activity onKeyDown事件
//     */
//    public boolean onKeyDown(int keyCode, View view) {
//        if (keyCode != KeyEvent.KEYCODE_BACK) {
//            return false;
//        }
//        if (isOnKeyBacking) {
//            mHandler.removeCallbacks(onBackTimeRunnable);
//            if (snackbar != null) {
//                snackbar.dismiss();
//            }
//            AppManager.getAppManager().AppExit(mActivity);
//            return true;
//        } else {
//            isOnKeyBacking = true;
//            if (snackbar == null) {
//                snackbar = Snackbar.make(view, "再次点击退出应用", 2000);
//            }
//            snackbar.show();
//            mHandler.postDelayed(onBackTimeRunnable, 2000);
//            return true;
//        }
//    }
//
//    private Runnable onBackTimeRunnable = new Runnable() {
//        @Override
//        public void run() {
//            isOnKeyBacking = false;
//            if (snackbar != null) {
//                snackbar.dismiss();
//            }
//        }
//    };
//}
