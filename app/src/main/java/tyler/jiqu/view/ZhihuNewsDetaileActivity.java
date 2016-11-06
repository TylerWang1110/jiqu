package tyler.jiqu.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import tyler.jiqu.R;
import tyler.jiqu.globle.Const;
import tyler.jiqu.model.ZhihuNewsDetaileModel;
import tyler.jiqu.presenter.ZhihuNewsDetailePresenterImpl;
import tyler.jiqu.util.ToastUtil;

/**
 * 知乎日报详情页
 */
public class ZhihuNewsDetaileActivity extends AppCompatActivity implements ZhihuNewsDetaileView {

    @Bind(R.id.tb_zhihunews_detaile)
    Toolbar mTbZhihunewsDetaile;
    @Bind(R.id.iv_zhihunews_detaile)
    ImageView mIvZhihunewsDetaileHead;
    @Bind(R.id.app_bar)
    AppBarLayout mAppBar;
    @Bind(R.id.ctl_zhihunews_detaile)
    CollapsingToolbarLayout mCtlZhihunewsDetaile;
    @Bind(R.id.wb_zhihu_news_detaile)
    WebView mWbZhihuNewsDetaile;
    private ZhihuNewsDetailePresenterImpl mZhihuNewsDetailePresenter;
    private int mNewsId;
    public static final String NEWS_ID = "NEWS_ID";
    private String mBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhihu_news_detaile);
        ButterKnife.bind(this);
        mZhihuNewsDetailePresenter = new ZhihuNewsDetailePresenterImpl(this);
        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        mNewsId = intent.getIntExtra(NEWS_ID, -1);
        if (mNewsId != -1) {
            mZhihuNewsDetailePresenter.getData(Const.URL_ZHIHU_NEWS_DETAILE + mNewsId);
        }
    }

    private void initView() {
        setSupportActionBar(mTbZhihunewsDetaile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //支持javascript
        mWbZhihuNewsDetaile.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放
        mWbZhihuNewsDetaile.getSettings().setSupportZoom(false);
        // 设置出现缩放工具
        mWbZhihuNewsDetaile.getSettings().setBuiltInZoomControls(false);
        //扩大比例的缩放
        //        mWbZhihuNewsDetaile.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        mWbZhihuNewsDetaile.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWbZhihuNewsDetaile.getSettings().setLoadWithOverviewMode(true);
        mWbZhihuNewsDetaile.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                String src = "file:///android_asset/ZhihuNewsDetaile.js";
                String baseUrl = "file:///android_asset";
                mWbZhihuNewsDetaile.loadDataWithBaseURL(baseUrl, mBody, "text/html", "UTF-8",null);
            }


        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_zhihu_news_detaile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                finish();

                break;

            case R.id.action_collect:
                //FIXME
                ToastUtil.showShort(this, "收藏成功");

                break;
            case R.id.action_share:
                //FIXME
                ToastUtil.showShort(this, "分享成功");

                break;
            case R.id.action_comment:
                //FIXME
                ToastUtil.showShort(this, "查看评论");

                break;


            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showView(ZhihuNewsDetaileModel zhihuNewsDetaileModel) {
        if (zhihuNewsDetaileModel == null) {
            return;
        }
        String title = zhihuNewsDetaileModel.getTitle();
        mCtlZhihunewsDetaile.setTitle(title);
        String image = zhihuNewsDetaileModel.getImage();
        Glide.with(this)
                .load(image)
                .into(mIvZhihunewsDetaileHead);
        String image_source = zhihuNewsDetaileModel.getImage_source();
        mBody = zhihuNewsDetaileModel.getBody();
        //        mWbZhihuNewsDetaile.loadUrl(share_url);

        String src = "file:///android_asset/ZhihuNewsDetaile.js";
        String baseUrl = "file:///android_asset";
        mWbZhihuNewsDetaile.loadDataWithBaseURL(baseUrl, mBody, "text/html", "UTF-8",null);
    }
}
