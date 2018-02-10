package xyz.zzp.news.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.zzp.news.R;

/**
 * Created by Lenovo on 2/9/2018.
 */

public class UserProfileActivity extends BaseActivity {

    @BindView(R.id.iv_profile)
    ImageView ivProfile;

    @BindView(R.id.iv_cover_img)
    ImageView ivCover;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, UserProfileActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ButterKnife.bind(this, this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 234) {

        }else if(requestCode ==345){

        }
    }

    @OnClick(R.id.iv_edit_profile)
    public void onTapEditProfileImage(View view) {
//        Toast.makeText(getApplicationContext(),"Tap Edit Profile",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        startActivityForResult(intent, 234);
    }
    @OnClick(R.id.iv_edit_cover)
    public void onTapEditCoverImage(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,345);
    }
}
