package com.umeng.soexample.monthlianxi;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.umeng.soexample.monthlianxi.base.BaseActivity;

public class MainActivity extends BaseActivity {

    private ImageView mImage;
    private Button mBtnTiao;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        mImage = findViewById(R.id.image);
        mBtnTiao = findViewById(R.id.Btn_Tiao);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mImage,"alpha",1f,0f,0.8f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mImage,"rotationY",0,180);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animator1).with(animator2);
        animatorSet.setDuration(3000);
        animatorSet.start();
    }

    @Override
    protected void onClicks() {
        mBtnTiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ShowActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void progress() {

    }
}
