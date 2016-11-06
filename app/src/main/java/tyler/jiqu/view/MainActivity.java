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

import butterknife.Bind;
import butterknife.ButterKnife;
import tyler.jiqu.R;
import tyler.jiqu.globle.Const;
import tyler.jiqu.manager.PageManger;

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
                this, mDrawerLayout, mTbMain, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        mNavView.setNavigationItemSelectedListener(this);
        mFragmentManager = getSupportFragmentManager();
        FrameLayout mainLayout = PageManger.getInstance().getPage(PageManger.PAGETYPE_ZHIHUNEWS);
        mFragmentManager.beginTransaction().replace(R.id.fl_main_content, new ZhihuNewsFragment(), Const.ZHIHU_NEWS).commit();
    }


    private void initData() {

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

        switch (item.getItemId()) {
            case R.id.nav_camera:

                break;
            case R.id.nav_gallery:

                break;

            case R.id.nav_slideshow:

                break;

            case R.id.nav_manage:

                break;

            case R.id.nav_share:

                break;

            case R.id.nav_send:

                break;
            default:

                break;

        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_main:
                ZhihuNewsFragment fragment = (ZhihuNewsFragment) mFragmentManager.findFragmentByTag(Const.ZHIHU_NEWS);
                fragment.getSvMainZhihu().smoothScrollTo(0,0);
                break;

            default:
                break;

        }
    }
}
