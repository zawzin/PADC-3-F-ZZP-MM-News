package xyz.zzp.news.network.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import xyz.zzp.news.data.vo.LoginUserVO;

/**
 * Created by Lenovo on 1/21/2018.
 */

public class LoginResponse {

    private int code;
    private String message;
    @SerializedName("login_user")
    private LoginUserVO loginUsers;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public LoginUserVO getLoginusers() {
        return loginUsers;
    }
}
