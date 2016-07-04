package com.huasheng.library.baseadapter.listener;

import android.view.View;
import android.view.ViewGroup;

/**
 * @author (YD)
 * @version (1.0)
 * @ProjectName CommonOnRVItemLongClickListener
 * @Title: CommonOnRVItemLongClickListener.java
 * @Package com.custom.libs.adapter.listener
 * @Description: TODO(RecyclerView的item长按事件监听器)
 * @date 2016/1/28
 * @time 16:03
 */
public interface CommonOnRVItemLongClickListener {
    boolean onRVItemLongClick(ViewGroup parent, View itemView, int position);
}
