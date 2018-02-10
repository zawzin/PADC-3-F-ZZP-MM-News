package xyz.zzp.news.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.zzp.news.R;
import xyz.zzp.news.data.vo.NewsVO;
import xyz.zzp.news.delegates.NewsActionDelegate;

/**
 * Created by Lenovo on 12/3/2017.
 */


public class ItemNewsViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_publication_title)
    TextView tvPublicationTitle;

    @BindView(R.id.tv_news_brief)
    TextView tvNewsBrief;

    @BindView(R.id.tv_posted_date)
    TextView tvPostedDate;

    @BindView(R.id.iv_publication_logo)
    ImageView ivPublicationLogo;

    @BindView(R.id.iv_news_img)
    ImageView ivNewsImg;

    private NewsActionDelegate mNewsActionDelegate;

    private NewsVO mNews;

    public ItemNewsViewHolder(View itemView, NewsActionDelegate newsActionDelegate)
    {
        super(itemView);
        ButterKnife.bind(this,itemView);

        mNewsActionDelegate = newsActionDelegate;

    }

    @OnClick(R.id.rl_news_item)
    public void onNewsItemTap(View view){

        mNewsActionDelegate.onTapNewsItem(mNews);

    }
    @OnClick(R.id.fl_sentTo)
    public void onTapSendTo(View view){
        mNewsActionDelegate.onTapSentToButton(mNews);
    }

    public void setNews(NewsVO news){
        mNews = news;

        tvPublicationTitle.setText(news.getPublication().getTitle());
        tvNewsBrief.setText(news.getBrief());
        tvPostedDate.setText(news.getPostedDate());

        Glide.with(ivPublicationLogo.getContext())
                .load(news.getPublication().getLogo())
                .into(ivPublicationLogo);

        if (news.getImages()!= null) {
            ivNewsImg.setVisibility(View.VISIBLE);
            Glide.with(ivNewsImg.getContext())
                    .load(news.getImages().get(0))
                    .into(ivNewsImg);
        }else{
            ivNewsImg.setVisibility(View.GONE);
        }
    }

}
