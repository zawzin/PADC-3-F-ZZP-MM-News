package xyz.zzp.news.network;

import android.content.Context;

/**
 * Created by Lenovo on 12/23/2017.
 */

public interface NewsDataAgent {

    /**
     * load news from network api.
     */
    void loadNews();

    /**
     * load user from network api.
     * @param context
     * @param phoneNo
     * @param password
     */
    void loginUser(Context context , String phoneNo, String password);
}
