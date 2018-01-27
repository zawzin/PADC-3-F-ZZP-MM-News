package xyz.zzp.news.network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import xyz.zzp.news.network.responses.GetNewsResponse;
import xyz.zzp.news.network.responses.LoginResponse;

/**
 * Created by Lenovo on 1/6/2018.
 */

public interface NewsApi {

    @FormUrlEncoded
    @POST("v1/getMMNews.php")
    Call<GetNewsResponse> getNews(@Field("page") int page,
                                  @Field("access_token") String accessToken);

    @FormUrlEncoded
    @POST("v1/login.php")
    Call<LoginResponse> loginUser(@Field("phoneNo") String phoneNo,
                                @Field("password")String password);
}
