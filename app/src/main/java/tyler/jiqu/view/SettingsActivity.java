package tyler.jiqu.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.Formatter;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import tyler.jiqu.R;
import tyler.jiqu.globle.Const;
import tyler.jiqu.globle.JiquApplacation;
import tyler.jiqu.util.FileUtil;
import tyler.jiqu.util.SPUtil;
import tyler.jiqu.util.ThemeUtil;
import tyler.jiqu.util.ToastUtil;

public class SettingsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener,
        View.OnClickListener {

    @Bind(R.id.tb_settings)
    Toolbar mTbSettings;
    @Bind(R.id.sw_theme)
    Switch mSwTheme;
    @Bind(R.id.sw_splashpage)
    Switch mSwSplashpage;
    @Bind(R.id.sw_nopic)
    Switch mSwNopic;
    @Bind(R.id.tv_settings_cache)
    TextView mTvSettingsCache;
    @Bind(R.id.ll_settings_clearcache)
    LinearLayout mLlSettingsClearcache;
    @Bind(R.id.ll_settings_github)
    LinearLayout mLlSettingsGithub;
    @Bind(R.id.ll_settings_email)
    LinearLayout mLlSettingsEmail;
    @Bind(R.id.tv_settings_escape_clause)
    TextView mTvSettingsEscapeClause;
    //    @Bind(R.id.tv_settings_licence)
    //    TextView mTvSettingsLicence;
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

        mTvSettingsCache.setText(getCacheSize());
        mTheme = (boolean) SPUtil.get(this, Const.THEME_NIGHT, false);
        mSkipsplash = (boolean) SPUtil.get(this, Const.SKIPSPLASH, false);
        mNopic = (boolean) SPUtil.get(this, Const.NOPIC, false);
        mSwTheme.setChecked(mTheme ? true : false);
        mSwSplashpage.setChecked(mSkipsplash ? true : false);
        mSwNopic.setChecked(mNopic ? true : false);
        mSwTheme.setOnCheckedChangeListener(this);
        mSwSplashpage.setOnCheckedChangeListener(this);
        mSwNopic.setOnCheckedChangeListener(this);
        mLlSettingsClearcache.setOnClickListener(this);
        mLlSettingsGithub.setOnClickListener(this);
        mLlSettingsEmail.setOnClickListener(this);
        mTvSettingsEscapeClause.setOnClickListener(this);
        //        mTvSettingsLicence.setOnClickListener(this);
    }

    private String getCacheSize() {
        long dirSize = FileUtil.getDirSize(JiquApplacation.sCacheDir);
        String size = Formatter.formatFileSize(this, dirSize);
        return size;
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_settings_clearcache:
                //清除缓存
                FileUtil.clearFileWithPath(JiquApplacation.sCacheDir.getAbsolutePath());
                mTvSettingsCache.setText(getCacheSize());
                break;
            case R.id.ll_settings_github:
                //访问地址
                openWebBrowser();
                break;
            case R.id.ll_settings_email:
                //打开邮件
                sendEmail();
                break;
            case R.id.tv_settings_escape_clause:
                //showDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                AlertDialog dialog = builder.setCancelable(false)
                        .setTitle("免责条款")
                        .setMessage("API由知乎提供, 获取与共享行为或有侵犯知乎权益的嫌疑, 若被告知需停止共享与使用, 本人将及时删除及下架项目. \r\n内容版权属原作者" +
                                ". ")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
                break;
            //            case R.id.tv_settings_licence:
            //                //
            //                Intent intent = new Intent(this, WebViewActivity.class);
            //                intent.putExtra(WebViewActivity.TITLE, "开源许可");
            //                String url = "";
            //                intent.putExtra(WebViewActivity.URL, url);
            //                startActivity(intent);
            //                break;

            default:
                break;

        }
    }

    private void openWebBrowser() {
        Uri  uri = Uri.parse(Const.URL_GITHUB);
        Intent  intent = new  Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private void sendEmail() {
        Intent data = new Intent(Intent.ACTION_SENDTO);
        data.setData(Uri.parse("mailto:tylerwang1203@gmail.com"));
        //        data.putExtra(Intent.EXTRA_SUBJECT, "这是标题");
        //        data.putExtra(Intent.EXTRA_TEXT, "这是内容");
        startActivity(data);

    }
}
