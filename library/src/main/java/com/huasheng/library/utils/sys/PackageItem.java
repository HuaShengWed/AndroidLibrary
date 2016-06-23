/**
 * @(#)PackageItem.java 2014-1-21 Copyright 2014 it.kedacom.com, Inc. All rights
 * reserved.
 */

package com.huasheng.library.utils.sys;

import android.graphics.drawable.Drawable;

/**
 * @author (YD)
 * @version (1.0)
 * @ProjectName PackageItem
 * @Title: PackageItem.java
 * @Package com.custom.libs.utils.sys
 * @Description: TODO(PackageItem包信息)
 * @date 2016/3/15
 * @time 11:29
 */
public class PackageItem {

    private Drawable icon;

    private String name;

    private String packageName;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
