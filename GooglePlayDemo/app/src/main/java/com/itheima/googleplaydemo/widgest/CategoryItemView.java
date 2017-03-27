package com.itheima.googleplaydemo.widgest;

import android.content.Context;
import android.util.AttributeSet;
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
    }

    public void setData(CategoryItemBean bean) {
        List<CategoryItemBean.InfosBean> infos = bean.getInfos();
        mTvTitle.setText(bean.getTitle());


        for (int i = 0; i < infos.size(); i++) {
            CategoryItemBean.InfosBean bean1 = infos.get(i);
            TableRow row = new TableRow(getContext());

            CategoryRowItem item1 = new CategoryRowItem(getContext());
            row.addView(item1);

            CategoryRowItem item2 = new CategoryRowItem(getContext());
            row.addView(item2);

            CategoryRowItem item3 = new CategoryRowItem(getContext());
            row.addView(item3);

            mTabLayout.addView(row);
        }


    }
}
