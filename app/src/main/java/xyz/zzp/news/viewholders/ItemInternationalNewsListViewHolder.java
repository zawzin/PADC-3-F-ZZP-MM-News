package xyz.zzp.news.viewholders;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.zzp.news.R;
import xyz.zzp.news.adapters.InternationalNewsAdapter;

/**
 * Created by Lenovo on 1/12/2018.
 */

public class ItemInternationalNewsListViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.rv_international_news)
    RecyclerView rvInternationalNews;

    private InternationalNewsAdapter mInternationalNewsAdapter;
    public ItemInternationalNewsListViewHolder(View itemView) {

        super(itemView);
        ButterKnife.bind(this,itemView);

        mInternationalNewsAdapter = new InternationalNewsAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(itemView.getContext(),2, LinearLayoutManager.VERTICAL,false);
        rvInternationalNews.setLayoutManager(gridLayoutManager);
        rvInternationalNews.setAdapter(mInternationalNewsAdapter);
    }
}
