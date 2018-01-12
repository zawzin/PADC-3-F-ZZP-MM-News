package xyz.zzp.news.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.zzp.news.R;
import xyz.zzp.news.viewholders.ItemSportNewsListViewHolder;

/**
 * Created by Lenovo on 1/10/2018.
 */

public class SportNewsListAdapter extends RecyclerView.Adapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View sportNewsView = layoutInflater.inflate(R.layout.item_sport_news_list,parent,false);
        ItemSportNewsListViewHolder itemSportNewsListViewHolder = new ItemSportNewsListViewHolder(sportNewsView);
        return itemSportNewsListViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
