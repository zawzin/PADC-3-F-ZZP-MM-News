package xyz.zzp.news.data.models;

import okhttp3.OkHttpClient;
import xyz.zzp.news.network.HttpUrlConnectionDataAgent;
import xyz.zzp.news.network.NewsDataAgent;
import xyz.zzp.news.network.OkHttpDataAgent;
import xyz.zzp.news.network.RetrofitDataAgent;

/**
 * Created by Lenovo on 12/23/2017.
 */

public class NewsModel {

    private static NewsModel sObjInstance;
    private NewsDataAgent mDataAgent;

    private NewsModel(){
//        mDataAgent = OkHttpDataAgent.getsObjectInstance();
//        mDataAgent = HttpUrlConnectionDataAgent.getObjectInstance();
        mDataAgent = RetrofitDataAgent.getObjectInstance();
    }
    public static NewsModel getObjInstance(){
        if(sObjInstance == null){
            sObjInstance = new NewsModel();
        }
        return sObjInstance;
    }

    /**
     * Load NewsData from Network api.
     */
    public void loadNews(){
            mDataAgent.loadNews();
    }

}
