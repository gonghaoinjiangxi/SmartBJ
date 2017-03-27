package com.itheima.googleplaydemo.widgest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.bean.CategoryItemBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 龚浩 on 2017/3/27.
 */

public class CategoryItemView extends RelativeLayout {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tab_layout)
    TableLayout mTabLayout;
    private int mWidthPixels;

    public CategoryItemView(Context context) {
        this(context, null);
    }

    public CategoryItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.category_item_view, this);
        ButterKnife.bind(this,this);
        init();
    }

    private void init() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        mWidthPixels = metrics.widthPixels;
    }

    public void setData(CategoryItemBean bean) {
        List<CategoryItemBean.InfosBean> infos = bean.getInfos();
        mTvTitle.setText(bean.getTitle());

        mTabLayout.removeAllViews();
        for (int i = 0; i < infos.size(); i++) {
            CategoryItemBean.InfosBean bean1 = infos.get(i);
            TableRow row = new TableRow(getContext());

            TableRow.LayoutParams params = new TableRow.LayoutParams();
            params.width = (mWidthPixels - mTabLayout.getPaddingLeft() - mTabLayout.getPaddingRight())/3;
            params.gravity = Gravity.CENTER;

            CategoryRowItem item1 = new CategoryRowItem(getContext());
            item1.setData(bean1.getName1(),bean1.getUrl1());
            item1.setLayoutParams(params);
            row.addView(item1);

            if(bean1.getName2().length() > 0 ) {
                CategoryRowItem item2 = new CategoryRowItem(getContext());
                item2.setData(bean1.getName2(),bean1.getUrl2());
                item2.setLayoutParams(params);
                row.addView(item2);
            }

            if(bean1.getName3().length() > 0) {
                CategoryRowItem item3 = new CategoryRowItem(getContext());
                item3.setData(bean1.getName3(),bean1.getUrl3());
                item3.setLayoutParams(params);
                row.addView(item3);
            }


            mTabLayout.addView(row);
        }


    }
}
