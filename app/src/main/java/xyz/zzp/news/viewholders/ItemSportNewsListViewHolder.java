package xyz.zzp.news.viewholders;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.zzp.news.R;
import xyz.zzp.news.adapters.SportNewsAdapter;

/**
 * Created by Lenovo on 1/10/2018.
 */

public class ItemSportNewsListViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.rv_sport_news)
    RecyclerView rvSportNews;

    private SportNewsAdapter mSportNewsAdapter;
    public ItemSportNewsListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);

        mSportNewsAdapter = new SportNewsAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.VERTICAL,false);
        rvSportNews.setLayoutManager(linearLayoutManager);
        rvSportNews.setAdapter(mSportNewsAdapter);
    }
}
