package xyz.zzp.news.data.models;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import xyz.zzp.news.data.vo.LoginUserVO;
import xyz.zzp.news.events.SuccessLoginEvent;
import xyz.zzp.news.events.UserLogoutEvent;
import xyz.zzp.news.network.NewsDataAgent;
import xyz.zzp.news.network.RetrofitDataAgent;

/**
 * Created by Lenovo on 1/20/2018.
 */

public class LoginUserModel {

    private static LoginUserModel sObjectInstance;
    private NewsDataAgent mDataAgent;
    private LoginUserVO mLoginUser;

    private LoginUserModel(){
        mDataAgent = RetrofitDataAgent.getObjectInstance();

        EventBus.getDefault().register(this);
    }

    public static LoginUserModel getsObjectInstance() {
        if(sObjectInstance == null){
            sObjectInstance = new LoginUserModel();
        }
        return sObjectInstance;
    }

    public void loginUser(String phoneNo, String password){
        mDataAgent.loginUser(phoneNo,password);
    }

    public boolean isUserLogin(){
        return mLoginUser != null;
    }

    public void logOut(){
        mLoginUser = null;
        UserLogoutEvent event = new UserLogoutEvent();
        EventBus.getDefault().post(event);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onLoginUserSuccess(SuccessLoginEvent event){
        mLoginUser = event.getLoginUser();
    }
}
