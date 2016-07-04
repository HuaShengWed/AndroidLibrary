package com.huasheng.library.baseadapter.listener;

import android.view.View;
import android.view.ViewGroup;

/**
 * @author (YD)
 * @version (1.0)
 * @ProjectName CommonOnItemChildLongClickListener
 * @Title: CommonOnItemChildLongClickListener.java
 * @Package com.custom.libs.adapter.listener
 * @Description: TODO(AdapterView和RecyclerView的item中子控件长按事件监听器)
 * @date 2016/1/28
 * @time 15:56
 */
public interface CommonOnItemChildLongClickListener {
    boolean onItemChildLongClick(ViewGroup parent, View childView, int position);
}
