package xyz.zzp.news.data.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lenovo on 12/17/2017.
 */

public class FavouriteVO {

    @SerializedName("favourite-id")
    private String favouriteId;
    @SerializedName("favourite-date")
    private String favouriteDate;
    @SerializedName("acted-user")
    private ActedUserVO actedUser;

    public String getFavouriteId() {
        return favouriteId;
    }

    public String getFavouriteDate() {
        return favouriteDate;
    }

    public ActedUserVO getActedUser() {
        return actedUser;
    }


}
