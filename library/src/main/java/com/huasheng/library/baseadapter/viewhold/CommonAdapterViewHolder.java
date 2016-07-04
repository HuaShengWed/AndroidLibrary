package com.huasheng.library.baseadapter.viewhold;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author (YD)
 * @version (1.0)
 * @ProjectName CommonAdapterViewHolder
 * @Title: CommonAdapterViewHolder.java
 * @Package com.custom.libs.adapter.viewhold
 * @Description: TODO(适用于AdapterView的item的ViewHolder)
 * @date 2016/1/28
 * @time 16:05
 */
public class CommonAdapterViewHolder {

    protected View mConvertView;
    protected CommonViewHolderHelper mViewHolderHelper;

    private CommonAdapterViewHolder(ViewGroup parent, int layoutId) {
        mConvertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
        mViewHolderHelper = new CommonViewHolderHelper(parent, mConvertView);
    }



    /**
     * 拿到一个可重用的ViewHolder对象
     *
     * @param convertView
     * @param parent
     * @param layoutId
     * @return
     */
    public static CommonAdapterViewHolder dequeueReusableAdapterViewHolder(View convertView, ViewGroup parent, int layoutId) {
        if (convertView == null) {
            return new CommonAdapterViewHolder(parent, layoutId);
        }
        return (CommonAdapterViewHolder) convertView.getTag();
    }

    public CommonViewHolderHelper getViewHolderHelper() {
        return mViewHolderHelper;
    }

    public View getConvertView() {
        return mConvertView;
    }


}
