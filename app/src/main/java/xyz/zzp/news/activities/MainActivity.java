package xyz.zzp.news.activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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
import xyz.zzp.news.viewpods.EmptyViewPod;

public class MainActivity extends BaseActivity
        implements NewsActionDelegate, BeforeLoginDelegate, LoginUserDelegate {

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

    @BindView(R.id.vp_empty_news)
    EmptyViewPod emptyViewPod;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private NewsAdapter mNewsAdapter;

    private AccountControlViewPod vpAccountControl;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this, this);// initialization

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_all_news);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mNewsAdapter = new NewsAdapter(this);

//        LinearLayoutManager linearLayoutManagernear = new LinearLayoutManager(getApplicationContext(),
//                LinearLayoutManager.HORIZONTAL,false);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        rvNews.setLayoutManager(gridLayoutManager);
        rvNews.setAdapter(mNewsAdapter);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.menu_news_by_category) {
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

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                NewsModel.getObjInstance().loadNews();
            }
        });
        swipeRefreshLayout.setRefreshing(true);
        NewsModel.getObjInstance().loadNews();
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait while data is loading");
        mProgressDialog.show();
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
        } else if (id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100){
            //request call phone permission
            if(grantResults.length>0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                String numberToCall = "+959613098";
                callToNumber(numberToCall);
            }
        }
    }

    @OnClick(R.id.fab)
    public void onTapFab(View view) {
//        Snackbar.make(view, "Replace with your own action - ButterKnife", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();
//        String numberToCall = "+959613098";
//        callToNumber(numberToCall);
        showConfirmDialog();
    }

    private void callToNumber(String numberToCall){

        Uri numberToCallUri = Uri.parse("tel:" + numberToCall);
        Intent intentToCall = new Intent(Intent.ACTION_CALL, numberToCallUri);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    100);

            return;
        }
        startActivity(intentToCall);
    }
    private void showConfirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation")
                .setCancelable(false)
                .setMessage(getResources().getString(R.string.message_to_exit,LoginUserModel.getsObjectInstance(getApplicationContext()).getmLoginUser().getName().toString()))
                .setPositiveButton("Sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Snackbar.make(rvNews,"Ok. You will exit in an hour", Snackbar.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),"this is the right choice",Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
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
    public void onTapSentToButton(NewsVO news) {
        Intent shareIntent = ShareCompat.IntentBuilder
                .from(this)
                .setType("text/plain")
                .setText(news.getBrief())
                .getIntent();

        if(shareIntent.resolveActivity(getPackageManager()) != null){
            startActivity(shareIntent);
        }else{
            Snackbar.make(rvNews,"NO app to handle share action",Snackbar.LENGTH_INDEFINITE).show();
        }
    }

    @Override
    public void onTapFavouriteButton() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNewsLoaded(LoadedNewsEvent loadedNewsEvent){
        Log.d(MMNewsApp.LOG_TAG,"onNewsLoaded : "+ loadedNewsEvent.getNewsList().size());
        swipeRefreshLayout.setRefreshing(false);
        mProgressDialog.dismiss();
        if(!loadedNewsEvent.getNewsList().isEmpty()) {
            mNewsAdapter.setNews(loadedNewsEvent.getNewsList());
            emptyViewPod.setVisibility(View.GONE);
        }
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
        LoginUserModel.getsObjectInstance(getApplicationContext()).logOut();
    }

    @Override
    public void onTapLoginUser() {
        Intent intent = UserProfileActivity.newIntent(getApplicationContext());
        startActivity(intent);
    }
}
