package xyz.zzp.news.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.zzp.news.R;
import xyz.zzp.news.delegates.NewsActionDelegate;
import xyz.zzp.news.viewholders.ItemNewsViewHolder;

/**
 * Created by Lenovo on 12/3/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter {

    private NewsActionDelegate mNewsActonDelegate;

    public NewsAdapter(NewsActionDelegate newsActionDelegate)  {
        mNewsActonDelegate = newsActionDelegate;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View newsItemView = inflater.inflate(R.layout.item_news, parent, false);
        ItemNewsViewHolder itemNewsViewHolder = new ItemNewsViewHolder(newsItemView,mNewsActonDelegate);
        return itemNewsViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 16;
    }
}
