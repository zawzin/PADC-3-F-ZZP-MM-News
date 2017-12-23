package xyz.zzp.news.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.zzp.news.R;
import xyz.zzp.news.delegates.NewsActionDelegate;

/**
 * Created by Lenovo on 12/3/2017.
 */

public class ItemNewsViewHolder extends RecyclerView.ViewHolder {

    private NewsActionDelegate mNewsActionDelegate;

    public ItemNewsViewHolder(View itemView, NewsActionDelegate newsActionDelegate)
    {
        super(itemView);
        ButterKnife.bind(this,itemView);

        mNewsActionDelegate = newsActionDelegate;
    }

    @OnClick (R.id.cv_news_item_root)
    public void onNewsItemTap(View view){

        mNewsActionDelegate.onTapNewsItem();

    }

}
