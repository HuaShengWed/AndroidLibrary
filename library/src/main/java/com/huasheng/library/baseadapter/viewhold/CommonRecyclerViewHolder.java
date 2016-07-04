package com.huasheng.library.baseadapter.viewhold;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.huasheng.library.baseadapter.listener.CommonOnRVItemClickListener;
import com.huasheng.library.baseadapter.listener.CommonOnRVItemLongClickListener;

/**
 * @author (YD)
 * @version (1.0)
 * @ProjectName CommonRecyclerViewHolder
 * @Title:  CommonRecyclerViewHolder.java
 * @Package  com.custom.libs.adapter.viewhold
 * @Description: TODO(适用于RecyclerView的item的ViewHolder)
 * @date  2016/1/28
 * @time  16:13
 */
public class CommonRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    protected Context mContext;
    protected CommonOnRVItemClickListener mOnRVItemClickListener;
    protected CommonOnRVItemLongClickListener mOnRVItemLongClickListener;
    protected CommonViewHolderHelper mViewHolderHelper;
    protected RecyclerView mRecyclerView;

    public CommonRecyclerViewHolder(RecyclerView recyclerView, View itemView, CommonOnRVItemClickListener onRVItemClickListener, CommonOnRVItemLongClickListener onRVItemLongClickListener) {
        super(itemView);
        mRecyclerView = recyclerView;
        mContext = mRecyclerView.getContext();
        mOnRVItemClickListener = onRVItemClickListener;
        mOnRVItemLongClickListener = onRVItemLongClickListener;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        mViewHolderHelper = new CommonViewHolderHelper(mRecyclerView, this.itemView);
        mViewHolderHelper.setRecyclerViewHolder(this);
    }

    public CommonViewHolderHelper getViewHolderHelper() {
        return mViewHolderHelper;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == this.itemView.getId() && null != mOnRVItemClickListener) {
            mOnRVItemClickListener.onRVItemClick(mRecyclerView, v, getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (v.getId() == this.itemView.getId() && null != mOnRVItemLongClickListener) {
            return mOnRVItemLongClickListener.onRVItemLongClick(mRecyclerView, v, getAdapterPosition());
        }
        return false;
    }
    
}
