package com.huasheng.library.baseadapter.listener;

import android.view.View;
import android.view.ViewGroup;

/**
 * @author (YD)
 * @version (1.0)
 * @ProjectName CommonOnItemChildClickListener
 * @Title: CommonOnItemChildClickListener.java
 * @Package com.custom.libs.adapter.listener
 * @Description: TODO(AdapterView和RecyclerView的item中子控件点击事件监听器)
 * @date 2016/1/28
 * @time 15:55
 */
public interface CommonOnItemChildClickListener {
    void onItemChildClick(ViewGroup parent, View childView, int position);
}
