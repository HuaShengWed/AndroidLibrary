package com.huasheng.library.baseadapter.listener;

import android.view.ViewGroup;
import android.widget.CompoundButton;

/**
 * @author (YD)
 * @version (1.0)
 * @ProjectName CommonOnItemChildCheckedChangeListener
 * @Title: CommonOnItemChildCheckedChangeListener.java
 * @Package com.custom.libs.adapter.listener
 * @Description: TODO(AdapterView和RecyclerView的item中子控件选中状态变化事件监听器)
 * @date 2016/1/28
 * @time 15:53
 */
public interface CommonOnItemChildCheckedChangeListener {
    void onItemChildCheckedChanged(ViewGroup parent, CompoundButton childView, int position, boolean isChecked);
}
