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

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.zzp.news.R;
import xyz.zzp.news.activities.NewsDetailsActivity;
import xyz.zzp.news.adapters.InternationalNewsListAdapter;
import xyz.zzp.news.data.vo.NewsVO;
import xyz.zzp.news.delegates.NewsActionDelegate;

/**
 * Created by Lenovo on 1/7/2018.
 */

public class InternationalNewsFragment extends Fragment implements NewsActionDelegate {

    @BindView(R.id.rv_international_news_list)
    RecyclerView rvInternationalNews;

    private InternationalNewsListAdapter mInternationalNewsListAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_international_news,container,false);
        ButterKnife.bind(this,view);

        mInternationalNewsListAdapter = new InternationalNewsListAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext(),LinearLayoutManager.VERTICAL,false);
        rvInternationalNews.setLayoutManager(linearLayoutManager);
        rvInternationalNews.setAdapter(mInternationalNewsListAdapter);
        return view;
    }

    @Override
    public void onTapNewsItem(NewsVO tappedNews) {
        Intent intent = new Intent(getContext(),NewsDetailsActivity.class);
        intent.putExtra("news_id", tappedNews.getNewsId());
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
}
