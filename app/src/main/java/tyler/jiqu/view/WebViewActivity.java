package tyler.jiqu.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;

import butterknife.Bind;
import butterknife.ButterKnife;
import tyler.jiqu.R;
import tyler.jiqu.util.ThemeUtil;

public class WebViewActivity extends AppCompatActivity {

    public static final String TITLE = "TITLE";
    public static final String URL = "URL";
    @Bind(R.id.tb_activity_web)
    Toolbar mTbActivityWeb;
    @Bind(R.id.wv_activity_wv)
    WebView mWvActivityWv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ThemeUtil.changeStatuBarColor(this);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        Intent intent = getIntent();
        String title = intent.getStringExtra(TITLE);
        String url = intent.getStringExtra(URL);
        mTbActivityWeb.setTitle(title);
        showWebView(url);
        setSupportActionBar(mTbActivityWeb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void showWebView(String url) {
        mWvActivityWv.loadUrl(url);
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
}
