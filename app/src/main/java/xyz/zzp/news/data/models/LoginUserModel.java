package xyz.zzp.news.data.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;
import java.util.Set;

import xyz.zzp.news.data.vo.LoginUserVO;
import xyz.zzp.news.events.SuccessLoginEvent;
import xyz.zzp.news.events.UserLogoutEvent;
import xyz.zzp.news.network.NewsDataAgent;
import xyz.zzp.news.network.RetrofitDataAgent;
import xyz.zzp.news.utils.AppConstants;

/**
 * Created by Lenovo on 1/20/2018.
 */

public class LoginUserModel {

    private static LoginUserModel sObjectInstance;
    private NewsDataAgent mDataAgent;
    private LoginUserVO mLoginUser;

    private LoginUserModel(Context context){
        mDataAgent = RetrofitDataAgent.getObjectInstance();

        EventBus.getDefault().register(this);

        SharedPreferences sharedPreferences = context.
                getSharedPreferences(AppConstants.LOGIN_USER_DATA_SP,Context.MODE_PRIVATE);
        int loginUserId = sharedPreferences.getInt(AppConstants.LOGIN_USERID_KEY,-1);
        if(loginUserId != -1){
            String name = sharedPreferences.getString(AppConstants.LOGIN_NAME_KEY,null);
            String email = sharedPreferences.getString(AppConstants.LOGIN_EMAIL_KEY,null);
            String phoneNo = sharedPreferences.getString(AppConstants.LOGIN_PHONENO_KEY, null);
            String profileUrl = sharedPreferences.getString(AppConstants.LOGIN_PROFILEURL_KEY,null);
            String coverUrl = sharedPreferences.getString(AppConstants.LOGIN_COVERURL_KEY, null);

            mLoginUser = new LoginUserVO(loginUserId,name,email,phoneNo,profileUrl,coverUrl);
        }
    }

    public static LoginUserModel getsObjectInstance(Context context) {
        if(sObjectInstance == null){
            sObjectInstance = new LoginUserModel(context);
        }
        return sObjectInstance;
    }

    public void loginUser(Context context, String phoneNo, String password){
        mDataAgent.loginUser(context,phoneNo,password);
    }

    public boolean isUserLogin(){
        return mLoginUser != null;
    }

    public LoginUserVO getmLoginUser() {
        return mLoginUser;
    }

    public void logOut(){
        mLoginUser = null;
        UserLogoutEvent event = new UserLogoutEvent();
        EventBus.getDefault().post(event);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onLoginUserSuccess(SuccessLoginEvent event){
        mLoginUser = event.getLoginUser();
        //Save user data in SharedPreferences.
        SharedPreferences sharedPreferences =
                event.getContext().getSharedPreferences(AppConstants.LOGIN_USER_DATA_SP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(AppConstants.LOGIN_USERID_KEY,event.getLoginUser().getUserId());
        editor.putString(AppConstants.LOGIN_NAME_KEY,event.getLoginUser().getName());
        editor.putString(AppConstants.LOGIN_EMAIL_KEY,event.getLoginUser().getEmail());
        editor.putString(AppConstants.LOGIN_PHONENO_KEY,event.getLoginUser().getPhoneNo());
        editor.putString(AppConstants.LOGIN_PROFILEURL_KEY, event.getLoginUser().getProfileUrl());
        editor.putString(AppConstants.LOGIN_COVERURL_KEY,event.getLoginUser().getCoverUrl());
        editor.apply();
    }
}
