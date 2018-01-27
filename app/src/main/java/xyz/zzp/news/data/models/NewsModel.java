package xyz.zzp.news.data.models;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.OkHttpClient;
import xyz.zzp.news.data.vo.NewsVO;
import xyz.zzp.news.events.LoadedNewsEvent;
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
    private Map<String,NewsVO> mNews;

    private NewsModel(){
//        mDataAgent = OkHttpDataAgent.getsObjectInstance();
//        mDataAgent = HttpUrlConnectionDataAgent.getObjectInstance();
        mDataAgent = RetrofitDataAgent.getObjectInstance();

        mNews = new HashMap<>();

        EventBus.getDefault().register(this);
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

    /**
     * get News object by Id.
     * @param newsId
     * @return
     */
    public NewsVO getNewsById(String newsId){
       return mNews.get(newsId);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onNewsLoaded(LoadedNewsEvent event){
        for(NewsVO news : event.getNewsList()){
            mNews.put(news.getNewsId(),news);
        }
    }

}
