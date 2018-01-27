package xyz.zzp.news.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.zzp.news.R;
import xyz.zzp.news.activities.NewsDetailsActivity;
import xyz.zzp.news.adapters.NewsAdapter;
import xyz.zzp.news.data.models.NewsModel;
import xyz.zzp.news.data.vo.NewsVO;
import xyz.zzp.news.delegates.NewsActionDelegate;
import xyz.zzp.news.events.LoadedNewsEvent;

/**
 * Created by Lenovo on 1/7/2018.
 */

public class NewsByCategoryFragment extends Fragment implements NewsActionDelegate{

    @BindView(R.id.rv_news_by_category)
    RecyclerView rvNewsByCategory;

    private NewsAdapter mNewsByCategoryAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_news_by_category, container,false);
        ButterKnife.bind(this,view);

        mNewsByCategoryAdapter = new NewsAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvNewsByCategory.setLayoutManager(linearLayoutManager);
        rvNewsByCategory.setAdapter(mNewsByCategoryAdapter);

        NewsModel.getObjInstance().loadNews();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onTapNewsItem(NewsVO tappedNews) {
        Intent intent = new Intent(getContext(),NewsDetailsActivity.class);
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
    public void loadNews(LoadedNewsEvent loadedNewsEvent){
        mNewsByCategoryAdapter.setNews(loadedNewsEvent.getNewsList());
    }
}
