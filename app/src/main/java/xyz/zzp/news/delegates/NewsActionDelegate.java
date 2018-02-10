package xyz.zzp.news.delegates;

import xyz.zzp.news.data.vo.NewsVO;

/**
 * Created by Lenovo on 12/17/2017.
 */

public interface NewsActionDelegate {

    void onTapNewsItem(NewsVO tappedNews);

    void onTapCommentButton();

    void onTapSentToButton(NewsVO tappedNews);

    void onTapFavouriteButton();
}
