package xyz.zzp.news.data.vo;

import java.util.List;

/**
 * Created by Lenovo on 12/17/2017.
 */

public class NewsVO {

    private String newsId;
    private String brief;
    private String details;
    private List<String> images;
    private String postedDate;

    private PublicationVO publication;
    private List<FavouriteVO> favourites;
    private List<CommentVO> comments;
    private List<SentToVO> sentTos;

    public String getNewsId() {
        return newsId;
    }

    public String getBrief() {
        return brief;
    }

    public String getDetails() {
        return details;
    }

    public List<String> getImages() {
        return images;
    }

    public List<FavouriteVO> getFavourites() {
        return favourites;
    }

    public List<CommentVO> getComments() {
        return comments;
    }

    public List<SentToVO> getSentTos() {
        return sentTos;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public PublicationVO getPublication() {
        return publication;
    }
}
