package tyler.jiqu.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tyler.jiqu.R;
import tyler.jiqu.globle.Const;
import tyler.jiqu.manager.PageManger;
import tyler.jiqu.model.ZhihuNewsThemeModel;
import tyler.jiqu.presenter.MainPresenterImpl;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainView, View.OnClickListener {

    @Bind(R.id.tb_main)
    Toolbar mTbMain;
    @Bind(R.id.fab_main)
    FloatingActionButton mFabMain;
    @Bind(R.id.nav_view)
    NavigationView mNavView;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.fl_main_content)
    FrameLayout mFlMainContent;
    private FragmentManager mFragmentManager;
    private Menu mMenu;
    private List<ZhihuNewsThemeModel.OthersBean> mOthers;
    private ZhihuNewsFragment mZhihuNewsFragment;
    private ZhihuNewsThemeContentFragment mZhihuNewsThemeContentFragment;
    private int mlastItem = R.id.nav_home;
    private MainPresenterImpl mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mTbMain);
        initView();
        initData();
        initEvent();
    }


    private void initView() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mTbMain, R.string.navigation_drawer_open, R.string
                .navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        mNavView.setNavigationItemSelectedListener(this);
        mMenu = mNavView.getMenu();
        mFragmentManager = getSupportFragmentManager();
        FrameLayout mainLayout = PageManger.getInstance().getPage(PageManger.PAGETYPE_ZHIHUNEWS);
        showHomeFragment();
    }


    private void initData() {
        mMainPresenter = new MainPresenterImpl(this);
        mMainPresenter.getNewsThemesList(Const.URL_ZHIHU_NEWS_THEME_LIST);
    }

    private void initEvent() {
        mFabMain.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        if (item.getItemId() == mlastItem) {
            item.setChecked(true);
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return false;
        }

        for (int i = 0; i <= mOthers.size(); i++) {
            mMenu.getItem(i).setChecked(false);
        }

        int itemId = item.getItemId();
        if (itemId == R.id.nav_home) {
            showHomeFragment();
            mTbMain.setTitle(R.string.app_name);
        } else {
            ZhihuNewsThemeModel.OthersBean othersBean = mOthers.get(itemId);
            showThemeFragment(othersBean);
            mTbMain.setTitle(othersBean.getName());
        }
        item.setChecked(true);
        mDrawerLayout.closeDrawer(GravityCompat.START);
        mlastItem = itemId;
        return true;
    }

    /**
     * 显示首页Fragment
     */
    private void showHomeFragment() {
        mZhihuNewsFragment = new ZhihuNewsFragment();
        mFragmentManager.beginTransaction().replace(R.id.fl_main_content, mZhihuNewsFragment, Const
                .ZHIHU_NEWS).commit();
    }

    /**
     * 切换显示的Fragment
     *
     * @param othersBean
     */
    private void showThemeFragment(ZhihuNewsThemeModel.OthersBean othersBean) {
        mFragmentManager.beginTransaction().replace(R.id.fl_main_content, new
                ZhihuNewsThemeContentFragment
                (othersBean), Const.ZHIHU_NEWS_THEME_CONTENT).commit();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_main:

                if (mlastItem != R.id.nav_home) {
                    ZhihuNewsThemeContentFragment fragment = (ZhihuNewsThemeContentFragment)
                            mFragmentManager.findFragmentByTag(Const
                            .ZHIHU_NEWS_THEME_CONTENT);
                    fragment.getRvThemecontent().smoothScrollToPosition(0);
                } else {
                    ZhihuNewsFragment fragment = (ZhihuNewsFragment) mFragmentManager.findFragmentByTag(Const
                            .ZHIHU_NEWS);
                    fragment.getSvMainZhihu().smoothScrollTo(0, 0);
                }
                break;

            default:
                break;

        }
    }

    /**
     * 初始化侧边栏数据
     *
     * @param zhihuNewsThemeModel
     */
    public void showNavView(ZhihuNewsThemeModel zhihuNewsThemeModel) {
        mOthers = zhihuNewsThemeModel.getOthers();
        for (int i = 0; i < mOthers.size(); i++) {
            mMenu.add(R.id.nav_menu, i, 0, mOthers.get(i).getName());
        }
        mMenu.getItem(0).setChecked(true);
    }

}
