package tyler.jiqu.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tyler.jiqu.R;
import tyler.jiqu.adapter.ZhihuNewsCommentsAdapter;
import tyler.jiqu.globle.Const;
import tyler.jiqu.model.ZhihuNewsCommentsModel;
import tyler.jiqu.presenter.ZhihuNewsCommentsPresenterImpl;
import tyler.jiqu.util.ThemeUtil;

public class ZhihuNewsCommentsActivity extends AppCompatActivity {
    public static final String NEWS_ID = "NEWS_ID";
    @Bind(R.id.tb_comments)
    Toolbar mTbComments;
    @Bind(R.id.rv_comments)
    RecyclerView mRvComments;
    private ZhihuNewsCommentsPresenterImpl mZhihuNewsCommentsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhihunewscomments);
        ButterKnife.bind(this);
        ThemeUtil.changeStatuBarColor(this);
        mZhihuNewsCommentsPresenter = new ZhihuNewsCommentsPresenterImpl(this);
        init();
    }

    private void init() {
        mTbComments.setTitle("评论");
        setSupportActionBar(mTbComments);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRvComments.setLayoutManager(new LinearLayoutManager(this));
        int newsId = getIntent().getIntExtra(NEWS_ID, 0);
        // http://news-at.zhihu.com/api/4/story/4232852/short-comments
        mZhihuNewsCommentsPresenter.getData(Const.URL_ZHIHU_NEWS_COMMENTS + newsId + "/short-comments");
    }

    public void showView(ZhihuNewsCommentsModel zhihuNewsCommentsModel) {
        List<ZhihuNewsCommentsModel.CommentsBean> comments = zhihuNewsCommentsModel.getComments();
        mTbComments.setTitle("评论(" + comments.size() + ")");
        //FIXME
        mRvComments.setAdapter(new ZhihuNewsCommentsAdapter(this, comments));

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
