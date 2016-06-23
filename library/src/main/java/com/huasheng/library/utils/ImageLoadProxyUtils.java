package com.huasheng.library.utils;


import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * http://www.2cto.com/kf/201412/361295.html
 *
 * @author (YD)
 * @version (1.0)
 * @ProjectName WeddingPlanner
 * @Title: ImageLoadProxy
 * @Package com.yufeng.weddingerplanner.utils
 * @Description: TODO(ImageLoad 工具类)
 * @date 16/6/17
 * @time 下午2:02
 */
public class ImageLoadProxyUtils {


    /**
     * 50 MiB
     */
    private static final int MAX_DISK_CACHE = 1024 * 1024 * 50;
    private static final int MAX_MEMORY_CACHE = 1024 * 1024 * 10;

    public static ImageLoader imageLoader;

    /**
     * 单例构建
     *
     * @return
     */
    public static ImageLoader getImageLoader() {

        if (imageLoader == null) {
            synchronized (ImageLoadProxyUtils.class) {
                imageLoader = ImageLoader.getInstance();
            }
        }

        return imageLoader;
    }

    /**
     * 在Application中初始化参数
     * <p>
     * threadPriority               设置当前线程的优先级
     * diskCacheFileNameGenerator   为使用HASHCODE对UIL进行加密命名， 还可以用MD5(new Md5FileNameGenerator())加密
     * diskCacheSize                 Mb sd卡(本地)缓存的最大值
     * memoryCacheSize              内存缓存的最大值
     * <p>
     *
     * @param context
     */
    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(MAX_DISK_CACHE);
        config.memoryCacheSize(MAX_MEMORY_CACHE);
        config.memoryCache(new LruMemoryCache(MAX_MEMORY_CACHE));
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        // 打印debug log
        if (LogUtils.mIsShowLog) {
            config.writeDebugLogs();
        }
        getImageLoader().init(config.build());
    }

    /**
     * 自定义Option
     *
     * @param url
     * @param target
     * @param options
     */
    public static void displayImage(String url, ImageView target, DisplayImageOptions options) {
        imageLoader.displayImage(url, target, options);
    }

    /**
     * 头像专用
     *
     * @param url
     * @param target
     */
    public static void displayHeadIcon(String url, ImageView target,int defRes) {
        imageLoader.displayImage(url, target, getOptions4Header(defRes));
    }

    /**
     * 图片详情页专用
     *
     * @param url
     * @param target
     * @param loadingListener
     */
    public static void displayImage4Detail(String url, ImageView target, SimpleImageLoadingListener loadingListener) {
        imageLoader.displayImage(url, target, getOption4ExactlyType(), loadingListener);
    }


    /**
     * 图片列表页专用
     *
     * @param url
     * @param target
     * @param loadingResource
     * @param loadingListener
     * @param progressListener DisplayImageOptions
     */
    public static void displayImageList(String url, ImageView target, int loadingResource,
                                        SimpleImageLoadingListener loadingListener,
                                        ImageLoadingProgressListener progressListener) {
        imageLoader.displayImage(url, target, getOptions4PictureList(loadingResource), loadingListener, progressListener);
    }


    /**
     * 图片列表页专用
     *
     * @param url
     * @param target
     * @param displayImageOptions
     * @param loadingListener
     * @param progressListener    DisplayImageOptions
     */
    public static void displayImageList(String url, ImageView target, DisplayImageOptions displayImageOptions,
                                        SimpleImageLoadingListener loadingListener,
                                        ImageLoadingProgressListener progressListener) {
        imageLoader.displayImage(url, target, displayImageOptions, loadingListener, progressListener);
    }

    /**
     * 自定义加载中图片
     *
     * @param url
     * @param target
     * @param loadingResource
     */
    public static void displayImageWithLoadingPicture(String url, ImageView target, int loadingResource) {
        imageLoader.displayImage(url, target, getOptions4PictureList(loadingResource));
    }

    /**
     * 当使用WebView加载大图的时候，使用本方法现下载到本地然后再加载
     *
     * @param url
     * @param loadingListener
     */
    public static void loadImageFromLocalCache(String url, SimpleImageLoadingListener loadingListener) {
        imageLoader.loadImage(url, getOption4ExactlyType(), loadingListener);
    }

    /**
     * 加载头像专用Options
     * 默认加载中、失败和空url为 login_default_head
     * <p>
     * cacheInMemory        设置下载的图片是否缓存在内存中
     * cacheOnDisk          设置下载的图片是否缓存在SD卡中
     * bitmapConfig         设置图片的解码类型  RGB_565模式消耗的内存比ARGB_8888模式少两倍.
     * showImageForEmptyUri 设置图片Uri为空或是错误的时候显示的图片
     * showImageOnFail      设置图片加载或解码过程中发生错误显示的图片
     * showImageOnLoading   设置图片下载期间显示的图片
     *
     * @return
     */
    public static DisplayImageOptions getOptions4Header(int defRes) {
        return new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .showImageForEmptyUri(defRes)
                .showImageOnFail(defRes)
                .showImageOnLoading(defRes)
                .build();
    }

    /**
     * 设置图片放缩类型为模式EXACTLY，用于图片详情页的缩放
     * <p>
     * resetViewBeforeLoading       设置图片在加载前是否重置、复位
     * considerExifParams           是否考虑JPEG图像EXIF参数（旋转，翻转）
     * imageScaleType               设置图片以如何的编码方式显示
     * <p>
     * <p>
     * 注:imageScaleType
     * <p>
     * EXACTLY :图像将完全按比例缩小的目标大小
     * EXACTLY_STRETCHED:图片会缩放到目标大小完全
     * IN_SAMPLE_INT:图像将被二次采样的整数倍
     * IN_SAMPLE_POWER_OF_2:图片将降低2倍，直到下一减少步骤，使图像更小的目标大小
     * NONE:图片不会调整
     *
     * @return
     */
    public static DisplayImageOptions getOption4ExactlyType() {
        return new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .resetViewBeforeLoading(true)
                .considerExifParams(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .build();
    }

    /**
     * 加载图片列表专用，加载前会重置View
     * {@link DisplayImageOptions.Builder#resetViewBeforeLoading} = true
     *
     * @param loadingResource
     * @return
     */
    public static DisplayImageOptions getOptions4PictureList(int loadingResource) {
        return new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .resetViewBeforeLoading(true)
                .showImageOnLoading(loadingResource)
                .showImageForEmptyUri(loadingResource)
                .showImageOnFail(loadingResource)
                .build();
    }

}
