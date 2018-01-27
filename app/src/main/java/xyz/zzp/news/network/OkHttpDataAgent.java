package xyz.zzp.news.network;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Timeout;
import xyz.zzp.news.MMNewsApp;
import xyz.zzp.news.events.LoadedNewsEvent;
import xyz.zzp.news.network.NewsDataAgent;
import xyz.zzp.news.network.responses.GetNewsResponse;

/**
 * Created by Lenovo on 1/6/2018.
 */

public class OkHttpDataAgent implements NewsDataAgent {

    private static OkHttpDataAgent sObjectInstance;

    private OkHttpDataAgent() {

    }

    public static OkHttpDataAgent getsObjectInstance() {
        if (sObjectInstance == null) {
            sObjectInstance = new OkHttpDataAgent();
        }
        return sObjectInstance;
    }

    @Override
    public void loadNews() {
        new LoadNewsTask().execute("http://padcmyanmar.com/padc-3/mm-news/apis/v1/getMMNews.php");

    }

    @Override
    public void loginUser(String email, String password) {

    }

    private static class LoadNewsTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String url = urls[0];

            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .build();

            RequestBody formBody = new FormBody.Builder()
                    .add("access_token", "b002c7e1a528b7cb460933fc2875e916")
                    .add("page", "1")
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();
            String responseString = null;
            try {
                Response response = httpClient.newCall(request).execute();
                if (response.isSuccessful() && response.body() != null) {
                    responseString = response.body().string();
                }
            } catch (IOException e) {
                Log.e(MMNewsApp.LOG_TAG, e.getMessage());
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            Gson gson = new Gson();
            GetNewsResponse getNewsResponse = gson.fromJson(response, GetNewsResponse.class);

            LoadedNewsEvent event = new LoadedNewsEvent(getNewsResponse.getMmNews());
            EventBus eventBus = EventBus.getDefault();
            eventBus.post(event);
        }
    }
}
