package com.huasheng.library.baseadapter.listener;

import android.view.View;
import android.view.ViewGroup;

/**
 * @author (YD)
 * @version (1.0)
 * @ProjectName CommonOnRVItemClickListener
 * @Title: CommonOnRVItemClickListener.java
 * @Package com.custom.libs.adapter.listener
 * @Description: TODO(RecyclerView的item点击事件监听器)
 * @date 2016/1/28
 * @time 15:58
 */
public interface CommonOnRVItemClickListener {
    void onRVItemClick(ViewGroup parent, View itemView, int position);
}
