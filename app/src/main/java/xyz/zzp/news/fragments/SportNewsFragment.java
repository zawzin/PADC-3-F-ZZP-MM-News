package xyz.zzp.news.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.zzp.news.R;
import xyz.zzp.news.adapters.SportNewsListAdapter;
import xyz.zzp.news.delegates.NewsActionDelegate;

/**
 * Created by Lenovo on 1/7/2018.
 */

public class SportNewsFragment extends Fragment implements NewsActionDelegate {

    @BindView(R.id.rv_sport_news_list)
    RecyclerView rvSportNewsList;

    private SportNewsListAdapter mSportNewsListAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sport_news,container,false);
        ButterKnife.bind(this,view);

        mSportNewsListAdapter = new SportNewsListAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext() ,LinearLayoutManager.VERTICAL,false);
        rvSportNewsList.setLayoutManager(linearLayoutManager);
        rvSportNewsList.setAdapter(mSportNewsListAdapter);

        return view;
    }

    @Override
    public void onTapNewsItem() {

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
}
