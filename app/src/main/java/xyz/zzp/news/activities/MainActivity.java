package xyz.zzp.news.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.zzp.news.MMNewsApp;
import xyz.zzp.news.R;
import xyz.zzp.news.adapters.NewsAdapter;
import xyz.zzp.news.data.models.LoginUserModel;
import xyz.zzp.news.data.models.NewsModel;
import xyz.zzp.news.data.vo.NewsVO;
import xyz.zzp.news.delegates.BeforeLoginDelegate;
import xyz.zzp.news.delegates.LoginUserDelegate;
import xyz.zzp.news.delegates.NewsActionDelegate;
import xyz.zzp.news.events.LoadedNewsEvent;
import xyz.zzp.news.viewpods.AccountControlViewPod;
import xyz.zzp.news.viewpods.BeforeLoginUserViewPod;

public class MainActivity extends AppCompatActivity
        implements NewsActionDelegate,BeforeLoginDelegate,LoginUserDelegate{

    @BindView(R.id.rv_news)
    RecyclerView rvNews;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private NewsAdapter mNewsAdapter;

    private AccountControlViewPod vpAccountControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this,this);// initialization

        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.title_all_news);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mNewsAdapter = new NewsAdapter(this);

//        LinearLayoutManager linearLayoutManagernear = new LinearLayoutManager(getApplicationContext(),
//                LinearLayoutManager.HORIZONTAL,false);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),1);
        rvNews.setLayoutManager(gridLayoutManager);
        rvNews.setAdapter(mNewsAdapter);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.menu_news_by_category) {
                    item.setChecked(true);
                    Intent intent = NewsByCategoryActivity.newIntent(getApplicationContext());
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    item.setChecked(false);
                }
                return false;
            }
        });

        vpAccountControl = (AccountControlViewPod) navigationView.getHeaderView(0);
        vpAccountControl.setDelegate((BeforeLoginDelegate) this);
        vpAccountControl.setDelegate((LoginUserDelegate) this);

        NewsModel.getObjInstance().loadNews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab)
    public void onTapFab(View view){
        Snackbar.make(view, "Replace with your own action - ButterKnife", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onTapNewsItem(NewsVO tappedNews) {
        Intent intent = new Intent(getApplicationContext(),NewsDetailsActivity.class);
        intent.putExtra("news_id",tappedNews.getNewsId());
        startActivity(intent);
    }

    @Override
    public void onTapCommentButton() {

    }

    @Override
    public void onTapSentToButton() {

    }

    @Override
    public void onTapFavouriteButton() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNewsLoaded(LoadedNewsEvent loadedNewsEvent){
        Log.d(MMNewsApp.LOG_TAG,"onNewsLoaded : "+ loadedNewsEvent.getNewsList().size());
        mNewsAdapter.setNews(loadedNewsEvent.getNewsList());
    }

    @Override
    public void onTapToLogin() {
        Intent intent = AccountControlActivity.newInterntLogin(getApplicationContext());
        startActivity(intent);
    }

    @Override
    public void onTaptoRegsister() {
        Intent intent = AccountControlActivity.newInterntRegister(getApplicationContext());
        startActivity(intent);
    }

    @Override
    public void onTapLogout() {
        LoginUserModel.getsObjectInstance().logOut();
    }
}
