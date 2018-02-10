package xyz.zzp.news.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.zzp.news.R;
import xyz.zzp.news.data.models.LoginUserModel;
import xyz.zzp.news.delegates.LoginScreenDelegate;
import xyz.zzp.news.events.SuccessLoginEvent;
import xyz.zzp.news.network.NewsDataAgent;

/**
 * Created by Lenovo on 1/20/2018.
 */

public class LoginFragment extends Fragment {

    @BindView(R.id.et_email_or_phone)
    EditText etEmailOrphoneNo;

    @BindView(R.id.et_password)
    EditText etPassword;

    private LoginScreenDelegate mLoginScreenDelegate;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mLoginScreenDelegate = (LoginScreenDelegate) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,container,false);
        ButterKnife.bind(this,view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.btn_login)
    public void onTapLogin(View view){
        String phoneNo = etEmailOrphoneNo.getText().toString();
        String password = etPassword.getText().toString();
        LoginUserModel.getsObjectInstance(getContext()).loginUser(getContext(),phoneNo,password);
    }
    @OnClick(R.id.btn_new_seller_register)
    public void onTapToRegister(View view){
        mLoginScreenDelegate.onTapToRegister();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginUserSuccess(SuccessLoginEvent event){
        if(getActivity() != null) {
            getActivity().onBackPressed();
        }
    }
}
