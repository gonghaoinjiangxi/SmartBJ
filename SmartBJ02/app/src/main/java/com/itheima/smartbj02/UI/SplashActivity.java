package com.itheima.smartbj02.UI;

import android.content.Context;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.itheima.smartbj02.MainActivity;
import com.itheima.smartbj02.R;
import com.itheima.smartbj02.Utils.SPUtils;

import butterknife.BindView;

/**
 * Created by 龚浩 on 2017/3/22.
 */

public class SplashActivity extends BaseActivity {

    private static final int ANIMATION_DURATION = 2000;

    @BindView(R.id.iv_splash_icon)
    ImageView mIvSplashIcon;
    private Context mContext = this;

    @Override
    public int getLayOutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void init() {
        super.init();
        playAnimation();
    }

    private void playAnimation() {
        AnimationSet set = new AnimationSet(false);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);
        rotateAnimation.setDuration(ANIMATION_DURATION);
        set.addAnimation(rotateAnimation);

        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);
        scaleAnimation.setDuration(ANIMATION_DURATION);
        set.addAnimation(scaleAnimation);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(ANIMATION_DURATION);
        set.addAnimation(alphaAnimation);

        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
                if (SPUtils.getBoolean(mContext, false)) {
                    navigateTo(MainActivity.class);
                } else {
                    navigateTo(GuideActivity.class);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mIvSplashIcon.startAnimation(set);
    }

}
