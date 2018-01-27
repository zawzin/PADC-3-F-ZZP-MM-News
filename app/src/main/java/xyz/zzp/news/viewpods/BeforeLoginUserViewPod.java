package xyz.zzp.news.viewpods;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.zzp.news.R;
import xyz.zzp.news.delegates.BeforeLoginDelegate;

/**
 * Created by Lenovo on 1/20/2018.
 */

public class BeforeLoginUserViewPod extends RelativeLayout {

    private BeforeLoginDelegate mDelegate;

    public BeforeLoginUserViewPod(Context context) {
        super(context);
    }

    public BeforeLoginUserViewPod(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BeforeLoginUserViewPod(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
    }
    public void setDelegate(BeforeLoginDelegate delegate){
        mDelegate = delegate;
    }
    @OnClick(R.id.btn_to_login)
    public void onTapToLogin(View view){
        mDelegate.onTapToLogin();
    }
    @OnClick(R.id.btn_to_regsister)
    public void onTapToRegsister(View view){
        mDelegate.onTaptoRegsister();
    }
}
