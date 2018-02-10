package xyz.zzp.news.viewpods;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.zzp.news.R;
import xyz.zzp.news.data.models.LoginUserModel;
import xyz.zzp.news.data.vo.LoginUserVO;
import xyz.zzp.news.delegates.BeforeLoginDelegate;
import xyz.zzp.news.delegates.LoginUserDelegate;
import xyz.zzp.news.events.SuccessLoginEvent;
import xyz.zzp.news.events.UserLogoutEvent;

/**
 * Created by Lenovo on 1/21/2018.
 */

public class AccountControlViewPod extends FrameLayout {

    @BindView(R.id.vp_before_login)
    BeforeLoginUserViewPod vpBeforeLogin;

    @BindView(R.id.vp_login_user)
    LoginUserViewPod vpLoginUser;

    private LoginUserDelegate mLoginUserDelegate;

    public AccountControlViewPod(@NonNull Context context) {
        super(context);
    }

    public AccountControlViewPod(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AccountControlViewPod(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this,this);

        refreshUserSession();
        EventBus.getDefault().register(this);
    }
    public void setDelegate(BeforeLoginDelegate delegate){
        vpBeforeLogin.setDelegate(delegate);
    }
    public void setDelegate(LoginUserDelegate delegate){
        vpLoginUser.setDelegate(delegate);
        mLoginUserDelegate = delegate;
    }
    private void refreshUserSession(){

        if(LoginUserModel.getsObjectInstance(getContext()).isUserLogin()){
            vpBeforeLogin.setVisibility(View.GONE);
            vpLoginUser.setVisibility(View.VISIBLE);
            vpLoginUser.bindData(LoginUserModel.getsObjectInstance(getContext()).getmLoginUser());
        }
        else {
            vpBeforeLogin.setVisibility(View.VISIBLE);
            vpLoginUser.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.vp_login_user)
    public void onTapLoginUser(View view){
        mLoginUserDelegate.onTapLoginUser();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginUserSuccess(SuccessLoginEvent event){
        vpBeforeLogin.setVisibility(View.GONE);
        vpLoginUser.setVisibility(View.VISIBLE);

        vpLoginUser.bindData(event.getLoginUser());
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogoutUser(UserLogoutEvent event){
        vpBeforeLogin.setVisibility(View.VISIBLE);
        vpLoginUser.setVisibility(View.GONE);
    }
}
