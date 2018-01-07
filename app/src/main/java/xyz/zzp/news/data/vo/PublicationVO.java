package xyz.zzp.news.data.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lenovo on 12/17/2017.
 */

public class PublicationVO {

    @SerializedName("publication-id")
    private String publicationId;
    private String title;
    private String logo;


    public String getPublicationId() {
        return publicationId;
    }

    public String getTitle() {
        return title;
    }

    public String getLogo() {
        return logo;
    }

}
