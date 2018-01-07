package xyz.zzp.news.network;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.zzp.news.events.LoadedNewsEvent;
import xyz.zzp.news.network.responses.GetNewsResponse;

/**
 * Created by Lenovo on 1/6/2018.
 */

public class RetrofitDataAgent implements NewsDataAgent {

    private static RetrofitDataAgent sObjectInstance;
    private NewsApi mNewApi;

    private RetrofitDataAgent(){
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://padcmyanmar.com/padc-3/mm-news/apis/v1/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(httpClient)
                .build();

         mNewApi = retrofit.create(NewsApi.class);
    }

    public static RetrofitDataAgent getObjectInstance() {
        if(sObjectInstance == null){
            sObjectInstance = new RetrofitDataAgent();
        }
        return sObjectInstance;
    }

    @Override
    public void loadNews() {

        Call<GetNewsResponse> getNewsResponseCall = mNewApi.getNews(1,"b002c7e1a528b7cb460933fc2875e916");
        getNewsResponseCall.enqueue(new Callback<GetNewsResponse>() {
            @Override
            public void onResponse(Call<GetNewsResponse> call, Response<GetNewsResponse> response) {
                GetNewsResponse getNewsResponse = response.body();

                if(getNewsResponse != null){
                    LoadedNewsEvent event = new LoadedNewsEvent(getNewsResponse.getMmNews());
                    EventBus.getDefault().post(event);
                }
            }

            @Override
            public void onFailure(Call<GetNewsResponse> call, Throwable t) {

            }
        });
    }
}
