package com.itheima.googleplaydemo.widgest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.app.Const;
import com.itheima.googleplaydemo.bean.DetailBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 龚浩 on 2017/3/30.
 */

public class AppImageView extends HorizontalScrollView {

    @BindView(R.id.ll_container)
    LinearLayout mLlContainer;

    public AppImageView(Context context) {
        super(context);
    }

    public AppImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.app_image, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(DetailBean bean) {
        List<String> stringList = bean.getScreen();
        for (int i = 0; i < stringList.size(); i++) {
            String url = Const.HTTP_IMAGINE_URL + stringList.get(i);
            ImageView imageView = new ImageView(getContext());
            imageView.setPadding(getResources().getDimensionPixelOffset(R.dimen.smaller_size), 0, getResources().getDimensionPixelOffset(R.dimen.smaller_size), 0);
            Glide.with(getContext()).load(url).override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).into(imageView);
            mLlContainer.addView(imageView);
        }
    }
}
