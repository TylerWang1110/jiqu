package tyler.jiqu.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import butterknife.Bind;
import butterknife.ButterKnife;
import tyler.jiqu.R;
import tyler.jiqu.globle.Const;
import tyler.jiqu.util.SPUtil;
import tyler.jiqu.util.ThemeUtil;
import tyler.jiqu.util.ToastUtil;

public class SettingsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    @Bind(R.id.tb_settings)
    Toolbar mTbSettings;
    @Bind(R.id.sw_theme)
    Switch mSwTheme;
    @Bind(R.id.sw_splashpage)
    Switch mSwSplashpage;
    @Bind(R.id.sw_nopic)
    Switch mSwNopic;
    private boolean mTheme;
    private boolean mSkipsplash;
    private boolean mNopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        ThemeUtil.changeStatuBarColor(this);
        init();
    }


    private void init() {
        setSupportActionBar(mTbSettings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTheme = (boolean) SPUtil.get(this, Const.THEME_NIGHT, false);
        mSkipsplash = (boolean) SPUtil.get(this, Const.SKIPSPLASH, false);
        mNopic = (boolean) SPUtil.get(this, Const.NOPIC, false);
        mSwTheme.setChecked(mTheme ? true : false);
        mSwSplashpage.setChecked(mSkipsplash ? true : false);
        mSwNopic.setChecked(mNopic ? true : false);
        mSwTheme.setOnCheckedChangeListener(this);
        mSwSplashpage.setOnCheckedChangeListener(this);
        mSwNopic.setOnCheckedChangeListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                finish();

                break;

            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            //FIXME
            case R.id.sw_theme:
                SPUtil.put(this, Const.THEME_NIGHT, isChecked);
                ToastUtil.showShort(this, isChecked ? "夜间" : "白天");
                break;
            case R.id.sw_splashpage:
                SPUtil.put(this, Const.SKIPSPLASH, isChecked);
                ToastUtil.showShort(this, isChecked ? "跳过" : "不跳过");
                break;
            case R.id.sw_nopic:

                SPUtil.put(this, Const.NOPIC, isChecked);
                ToastUtil.showShort(this, isChecked ? "无图" : "有图");
                break;
            default:
                break;

        }
    }
}
