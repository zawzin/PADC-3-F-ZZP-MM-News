package xyz.zzp.news.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.zzp.news.R;
import xyz.zzp.news.viewholders.ItemInternationalNewsListViewHolder;

/**
 * Created by Lenovo on 1/12/2018.
 */

public class InternationalNewsListAdapter extends RecyclerView.Adapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View internationalNewsView = layoutInflater.inflate(R.layout.item_international_news_list,parent,false);
        ItemInternationalNewsListViewHolder itemInternationalNewsListViewHolder = new ItemInternationalNewsListViewHolder(internationalNewsView);
        return itemInternationalNewsListViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
