package tyler.jiqu.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import tyler.jiqu.R;
import tyler.jiqu.globle.Const;
import tyler.jiqu.model.SplashImageModel;
import tyler.jiqu.presenter.SplashPresenter;
import tyler.jiqu.presenter.SplashPresenterImpl;
import tyler.jiqu.util.SPUtil;
import tyler.jiqu.util.ThemeUtil;

public class SplashActivity extends AppCompatActivity implements SplashView {

    @Bind(R.id.iv_splash)
    ImageView mIvSplash;
    @Bind(R.id.tv_splash)
    TextView mTvSplash;
    private SplashPresenter mMSplashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean skipSplash = (boolean) SPUtil.get(this, Const.SKIPSPLASH, false);
        if (skipSplash) {
            finish();
            go2Main();
            return;
        }

        setContentView(R.layout.activity_splash);
        ThemeUtil.changeStatuBarColor(this);
        ButterKnife.bind(this);
        mMSplashPresenter = new SplashPresenterImpl(this);
        initView();
    }

    private void initView() {
        mMSplashPresenter.getSplashImage(Const.URL_SPLASH_IMAGE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1000);
                go2Main();
            }
        }).start();
    }


    @Override
    public void go2Main() {
        finish();

        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void showImage(SplashImageModel imageModel) {
        if (imageModel != null) {
            Glide.with(this)
                    .load(imageModel.getImg())
                    .into(mIvSplash);
        }
        mTvSplash.setText(imageModel.getText());
    }
}
