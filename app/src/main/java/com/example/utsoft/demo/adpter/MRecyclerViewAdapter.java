package com.example.utsoft.demo.adpter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.utsoft.demo.R;
import com.example.utsoft.demo.entity.RecyclerviewTestEntity;

import java.util.List;

/**
 * Created by 胡楠启 on 2017/2/21.
 * Function：
 * Desc：
 */

public class MRecyclerViewAdapter extends BaseQuickAdapter<RecyclerviewTestEntity, BaseViewHolder> {
    public MRecyclerViewAdapter(int layoutResId, List<RecyclerviewTestEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecyclerviewTestEntity item) {
        helper.setText(R.id.tv_item_recyclerview, item.getTest1()).setText(R.id.tv2_item_recyclerview, item.getTest2());
        helper.addOnClickListener(R.id.tv_item_recyclerview);
    }
}
