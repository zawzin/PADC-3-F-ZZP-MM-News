package xyz.zzp.news.events;

import android.content.Context;

import xyz.zzp.news.data.vo.LoginUserVO;

/**
 * Created by Lenovo on 1/21/2018.
 */

public class SuccessLoginEvent {
    private LoginUserVO loginUser;
    private Context context;

    public SuccessLoginEvent(LoginUserVO loginUser, Context context) {
        this.loginUser = loginUser;
        this.context = context;
    }

    public LoginUserVO getLoginUser() {
        return loginUser;
    }

    public Context getContext() {
        return context;
    }
}
