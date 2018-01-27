package xyz.zzp.news.events;

import xyz.zzp.news.data.vo.LoginUserVO;

/**
 * Created by Lenovo on 1/21/2018.
 */

public class SuccessLoginEvent {
    private LoginUserVO loginUser;

    public SuccessLoginEvent(LoginUserVO loginUser) {
        this.loginUser = loginUser;
    }

    public LoginUserVO getLoginUser() {
        return loginUser;
    }
}
