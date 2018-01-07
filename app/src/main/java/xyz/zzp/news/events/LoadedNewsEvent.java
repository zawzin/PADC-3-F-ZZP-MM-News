package xyz.zzp.news.events;

import java.util.List;

import xyz.zzp.news.data.vo.NewsVO;

/**
 * Created by Lenovo on 12/24/2017.
 */

public class LoadedNewsEvent {

    private List<NewsVO> newsList;

    public LoadedNewsEvent(List<NewsVO> newsList) {
        this.newsList = newsList;
    }

    public List<NewsVO> getNewsList() {
        return newsList;
    }
}
