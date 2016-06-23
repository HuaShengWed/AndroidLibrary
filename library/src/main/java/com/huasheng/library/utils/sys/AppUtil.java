package com.huasheng.library.utils.sys;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Browser;
import android.provider.MediaStore;
import android.provider.Settings;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.huasheng.library.R;
import com.huasheng.library.utils.LogUtils;
import com.huasheng.library.utils.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * @author (YD)
 * @version (1.0)
 * @ProjectName AppUtil
 * @Title: AppUtil.java
 * @Package com.custom.libs.utils.sys
 * @Description: TODO(App系统工具类)
 * @date 2016/3/14
 * @time 16:43
 */
public class AppUtil {

    /**
     * 发送邮件
     *
     * @param context
     * @param eMail eMail地址
     */
    public static void sendeMile(Context context, String eMail) {
        if (StringUtils.isNull(eMail)) return;
        try {
            Uri emailUri = Uri.parse("mailto:" + eMail);
            // 建立Intent 对象
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, emailUri);
            emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(emailIntent);
        } catch (ActivityNotFoundException e) {
            Log.e("Customer service phone", "ActivityNotFoundException : ", e);
        }
    }

    /**
     *  发送邮件
     * @param context
     * @param eMail eMail地址
     * @param ccEmailArr 抄送地址
     * @param subject 主题
     * @param body 内容
     */
    public static void sendeMile(Context context, String eMail, String[] ccEmailArr, String subject, String body) {
        if (StringUtils.isNull(eMail) || context == null) {
            return;
        }

        try {
            Uri emailUri = Uri.parse("mailto:" + eMail);
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, emailUri);
            emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            if (ccEmailArr != null && ccEmailArr.length > 0) {
                emailIntent.putExtra(Intent.EXTRA_CC, ccEmailArr);
            }

            if (!StringUtils.isNull(subject)) {
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
            } else {
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "The email subject text");
            }
            if (!StringUtils.isNull(body)) {
                emailIntent.putExtra(Intent.EXTRA_TEXT, body);
            } else {
                emailIntent.putExtra(Intent.EXTRA_TEXT, "The email body text");
            }

            context.startActivity(emailIntent);
        } catch (ActivityNotFoundException e) {
            Log.e("Customer service phone", "ActivityNotFoundException : ", e);
        }
    }

    /**
     * 选择邮件客户端发送邮件
     *
     * @param context
     * @param eMail eMail地址
     * @param ccEmail 抄送地址
     * @param subject 主题
     * @param body 内容
     */
    public static void sendeMile4EmailClients(Context context, String eMail, String ccEmail, String subject, String body) {
        if (StringUtils.isNull(eMail) || context == null) {
            return;
        }

        try {
            Uri emailUri = Uri.parse("mailto:" + eMail);
            Intent it = new Intent(Intent.ACTION_SEND, emailUri);
            it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            String[] tos = {
                    eMail
            };
            it.putExtra(Intent.EXTRA_EMAIL, tos);
            if (!StringUtils.isNull(ccEmail)) {
                String[] ccs = {
                        ccEmail
                };
                it.putExtra(Intent.EXTRA_CC, ccs);
            }
            if (!StringUtils.isNull(subject)) {
                it.putExtra(Intent.EXTRA_TEXT, subject);
            } else {
                it.putExtra(Intent.EXTRA_SUBJECT, "The email subject text");
            }
            if (!StringUtils.isNull(body)) {
                it.putExtra(Intent.EXTRA_TEXT, body);
            } else {
                it.putExtra(Intent.EXTRA_TEXT, "The email body text");
            }
            it.setType("message/rfc822");

            context.startActivity(Intent.createChooser(it, "Choose Email Client"));
        } catch (ActivityNotFoundException e) {
            Log.e("Customer service phone", "ActivityNotFoundException : ", e);
        }
    }


    /**
     * 将文本复制到剪贴板
     *
     * @param context
     * @param content 复制内容
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @SuppressWarnings("deprecation")
    public static void copyToClipboard(Context context, String content) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (cm != null) {
            cm.setText(content);
        }
    }

    /**
     * 取得剪贴板的文本
     *
     * @param context
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @SuppressWarnings("deprecation")
    public static String getText4Clipboard(Context context) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (cm != null && cm.getText() != null) {
            return cm.getText().toString();
        }
        return "";
    }

    /**
     * 打开一个url链接
     *
     * @param context
     * @param url url地址
     */
    public static void linkURL(Context context, String url) {
        if (StringUtils.isNull(url) || context == null) {
            return;
        }

        if (url.toLowerCase().startsWith("www.")) {
            url = "http://" + url;
        }

        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Browser.EXTRA_APPLICATION_ID, context.getPackageName());
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtils.e("ActivityNotFoundException   Activity Not Found Exception : " + e);
        }
    }

    /**
     * wireless settings
     *
     * @param context
     */
    public static void wirelessSettings(Context context) {
        if (context == null) return;

        try {
            context.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
        } catch (ActivityNotFoundException e) {
        } catch (Exception e) {
        }
    }

    /**
     * wifi settings
     *
     * @param context
     */
    public static void wifiSettings(Context context) {
        if (context == null) return;

        try {
            context.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
        } catch (ActivityNotFoundException e) {
        } catch (Exception e) {
        }
    }

    /**
     * 打开设置界面
     *
     * @param context
     */
    public static void settings(Context context) {
        if (context == null) return;

        try {
            context.startActivity(new Intent(Settings.ACTION_SETTINGS));
        } catch (ActivityNotFoundException e) {
        } catch (Exception e) {
        }
    }

    /**
     * 设置旋转方式
     *
     * @param context
     * @param autoRotation 自动旋转
     */
    public static void setRotation(Context context, boolean autoRotation) {
        if (context == null) return;

        // 当前屏幕旋转状态，1：自动旋转，
        if (autoRotation) {
            Settings.System.putInt(context.getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, 1);
        } else {
            Settings.System.putInt(context.getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, 0);
        }
    }

    /**
     *  设置禁止手机自动锁屏
     * @param activity
     * @param setkeepScreenOn 自动旋转
     */
    public static void setKeepScreenOn(Activity activity, boolean setkeepScreenOn) {
        if (null == activity) return;

        // activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
        // WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    /**
     * 调用系统拍照功能，将拍照图片存储在 默认媒体库下
     *
     * @param context
     * @param requestCode 返回码
     */
    public static void takePicture(Context context, int requestCode) {
        try {
            if (context == null) {
                return;
            }

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (context instanceof Activity) {
                ((Activity) context).startActivityForResult(intent, requestCode);
            } else {
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        } catch (ActivityNotFoundException e) {
        } catch (Exception e) {
        }
    }



    /**
     * 选择图片资源
     *
     * @param context
     * @param requestCode 返回码
     */
    public static void chooserPics(Context context, int requestCode) {
        if (context == null) {
            return;
        }

        try {
            Intent localIntent = new Intent();
            localIntent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            localIntent.setType("image/*");
            localIntent.setAction(Intent.ACTION_GET_CONTENT);
            if (context instanceof Activity) {
                ((Activity) context).startActivityForResult(Intent.createChooser(localIntent, "Select Picture"), requestCode);
            } else {
                localIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(Intent.createChooser(localIntent, "Select Picture"));
            }
        } catch (Exception e) {
        }
    }

    /**
     * 选择系统相册
     *
     * @Description
     * @param context
     * @param requestCode 返回码
     */
    public static void chooserSysPics(Context context, int requestCode) {
        if (context == null) {
            return;
        }

        try {
            Intent localIntent = new Intent();
            localIntent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            localIntent.setType("image/*");
            // Intent.ACTION_GET_CONTENT
            localIntent.setAction("android.intent.action.GET_CONTENT");
            if (context instanceof Activity) {
                ((Activity) context).startActivityForResult(localIntent, requestCode);
            } else {
                localIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(localIntent);
            }
        } catch (Exception e) {
        }
    }


    /**
     *  保存图片
     * @param context
     * @param absDir 相对路径
     * @param fileName
     * @param bmp
     */
    public static void saveImageToGallery(Context context, String absDir, String fileName, File bmp) {
        // 首先保存图片
        File appDir = new File(absDir);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + appDir)));
    }

    /**
     *  保存图片
     * @param context
     * @param absDir 相对路径
     * @param fileName
     * @param bmp
     */
    public static void saveImageToGallery(Context context, String absDir, String fileName, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(absDir);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + appDir)));
    }

    /**
     * 保存图片
     *
     * @param context
     * @param dir 相对路径
     * @param bmp
     */
    public static void saveImageToGallery(Context context, String dir, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), dir);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + appDir)));
    }

    /**
     * 在应用商店打开
     *
     * @param context
     * @param packageName
     */
    public static void openApp4AppStore(Context context, String packageName) {
        if (null == context) return;

        try {
            if (StringUtils.isNull(packageName)) return;
            if (isPlayStoreInstalled(context)) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
            } else {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + packageName)));
            }
        } catch (Exception e) {
        }
    }

    /**
     * 是否安装了App Store
     *
     * @param context
     * @return
     */
    public static boolean isPlayStoreInstalled(Context context) {
        if (null == context) return false;

        try {

            Intent market = new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=dummy"));
            PackageManager manager = context.getPackageManager();
            List<ResolveInfo> list = manager.queryIntentActivities(market, 0);

            return list.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 评论App
     *
     * @param context
     */
    public static void toScores(Context context) {
        try {
            Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
        }
    }

    /**
     * 在浏览器中打开Url
     *
     * @param context
     * @param url
     */
    public static void openView(Context context, String url) {
        try {
            Intent intentUri = new Intent(Intent.ACTION_VIEW);
            intentUri.setData(Uri.parse(url));
            intentUri.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentUri);
        } catch (Exception e) {
        }
    }

    /**
     * 打开app
     *
     * @param context
     * @param packageName
     */
    public static void openApp(Context context, String packageName) {
        if (null == context) return;
        if (StringUtils.isNull(packageName)) return;

        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        try {
            if (intent != null) {
                context.startActivity(intent);
                return;
            }
        } catch (Exception e) {
        }
    }

    /**
     * 启动APP
     * @param context
     * @throws Exception
     */
    public static void runApp(Context context) throws  Exception {
        PackageManager packageManager = context.getPackageManager();
//        PackageInfo appinfo = packageManager.getPackageInfo(context.getPackageName(),
//                PackageManager.GET_UNINSTALLED_PACKAGES | PackageManager.GET_ACTIVITIES);
        PackageInfo appinfo =null;
        ActivityInfo[] activitys = appinfo.activities;
        if (activitys.length <= 0) {
            return;
        }

        ActivityInfo firstactivity = activitys[0];
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClassName(context.getPackageName(), firstactivity.name);
        context.startActivity(intent);
    }

    /**
     * 激活Activity
     *
     * @param context
     * @param cls 被激活的Activity
     */
    public static void openActivity(Context context, Class<Activity> cls) {
        if (null == context || null == cls) return;

        Intent i = new Intent(context, cls);
        i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(i);
    }

    /**
     * 卸载
     *
     * @param context
     * @param packageName app 报名
     */
    @SuppressLint("InlinedApi")
    public static void uninstallApk(Context context, String packageName) {
        if (null == context) return;
        if (StringUtils.isNull(packageName)) return;

        try {
            Uri packageUri = Uri.parse("package:" + packageName);
            Intent uninstallIntent;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                uninstallIntent = new Intent(Intent.ACTION_DELETE, packageUri);
            } else {
                uninstallIntent = new Intent(Intent.ACTION_UNINSTALL_PACKAGE, packageUri);
            }
            context.startActivity(uninstallIntent);
        } catch (Exception e) {
            Toast.makeText(context, R.string.cantUninstall, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 卸载程序
     *
     * @see AppUtil#uninstallApk(Context, String)
     * @param context
     * @param packageName 包名
     */
    @Deprecated
    public static void uninstallApk2(Context context, String packageName) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        Uri packageURI = Uri.parse("package:" + packageName);
        intent.setData(packageURI);
        context.startActivity(intent);
    }

    /**
     * 打开并安装文件
     *
     * @param context
     * @param file apk文件路径
     */
    public static void installApk(Context context, File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 应用是否在运行
     *
     * @param ctx
     * @return
     */
    public static boolean isRunning(Context ctx) {
        if (null == ctx) {
            return false;
        }

        String packageName = ctx.getPackageName();
        ActivityManager am = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        if (StringUtils.isNull(packageName) || null == am) {
            return false;
        }

        boolean isAppRunning = false;
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(50);
        if (null == list || list.isEmpty()) {
            return false;
        }

        for (ActivityManager.RunningTaskInfo info : list) {
            if (null == info) continue;

            if ((info.topActivity != null && packageName.equals(info.topActivity.getPackageName()))
                    || (info.baseActivity != null && packageName.equals(info.baseActivity.getPackageName()))) {
                isAppRunning = true;
                break;
            }
        }

        return isAppRunning;
    }

    /**
     * 判断服务是否运行
     *
     * @param ctx
     * @param className 判断的服务名字 "com.xxx.xx..XXXService"
     * @return true 运行中
     */
    public static boolean isServiceRunning(Context ctx, String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> servicesList = activityManager.getRunningServices(Integer.MAX_VALUE);
        Iterator<ActivityManager.RunningServiceInfo> l = servicesList.iterator();
        while (l.hasNext()) {
            ActivityManager.RunningServiceInfo si = l.next();
            if (className.equals(si.service.getClassName())) {
                isRunning = true;
            }
        }

        return isRunning;
    }

    /**
     * 停止服务
     *
     * @param ctx
     * @param className 服务名字 "com.xxx.xx..XXXService"
     * @return true, if successful
     */
    public static boolean stopRunningService(Context ctx, String className) {
        Intent intent_service = null;
        boolean ret = false;
        try {
            intent_service = new Intent(ctx, Class.forName(className));
        } catch (Exception e) {
        }
        if (intent_service != null) {
            ret = ctx.stopService(intent_service);
        }
        return ret;
    }
    /**
     * 呼叫电话
     *
     * <pre>
     * 注意：需要权限<uses-permission android:name="android.permission.CALL_PHONE" />
     *
     * @param context
     * @param phoneNum 电话号码
     */
    public static void call(Context context, String phoneNum) {
        if (StringUtils.isNull(phoneNum)) return;

        try {
            Intent pIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + PhoneNumberUtils.formatNumber(phoneNum)));
            pIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(pIntent);
        } catch (ActivityNotFoundException e) {
            Log.e("Customer service phone", "ActivityNotFoundException : ", e);
        }
    }


}
