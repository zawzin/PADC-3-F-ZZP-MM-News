package xyz.zzp.news.network;

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
     * @param phoneNo
     * @param password
     */
    void loginUser(String phoneNo, String password);
}
