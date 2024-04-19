package com.edu.squash.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.edu.squash.R;
import com.lynn.base.uitls.DensityUtil;


public class ImageLoadUtils {

    /*
     *加载图片(默认)
     */
    public static void loadImage(Context context, String url, ImageView imageView) {
//        if (TextUtils.equals(FileUtils.getFileType(url), "gif"))
//            loadAsGif(context, url, imageView);
//        else {
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .error(R.color.white);       //错误图
            Glide.with(context).load(url).apply(options).into(imageView);
//        }
    }

    public static void loadImageNoCrop(Context context, String url, ImageView imageView) {
//        if (TextUtils.equals(FileUtils.getFileType(url), "gif"))
//            loadAsGif(context, url, imageView);
//        else {
            RequestOptions options = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(context).load(url).apply(options).into(imageView);
//        }
    }

//    public static void loadImage(Context context, File file, ImageView imageView) {
//        RequestOptions options = new RequestOptions()
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .error(R.color.black);       //错误图
//        Glide.with(context).load(file).apply(options).into(imageView);
//    }

    public static void loadImage(Context context, Integer resourceId, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.color.white);       //错误图
        Glide.with(context).load(resourceId).apply(options).into(imageView);
    }

    public static void loadAsBitmap(Context context, String url, SimpleTarget<Bitmap> target) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.color.white);       //错误图
        Glide.with(context).asBitmap().load(url).apply(options).into(target);
    }

    public static void loadAsBitmapNoCrop(Context context, String url, SimpleTarget<Bitmap> target) {
        RequestOptions options = new RequestOptions()
                .error(R.color.white);       //错误图
        Glide.with(context).asBitmap().load(url).apply(options).into(target);
    }

    public static void loadAsBitmap(Context context, int url, SimpleTarget<Bitmap> target) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.color.white);       //错误图
        Glide.with(context).asBitmap().load(url).apply(options).into(target);
    }

    public static void loadAsGif(Context context, int url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.color.white);       //错误图
        Glide.with(context).asGif().load(url).apply(options).into(imageView);
    }

    //.diskCacheStrategy(DiskCacheStrategy.SOURCE)//是将图片原尺寸缓存到本地。
    public static void loadAsGifTransparent(Context context, int url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.color.white)//错误图
                .skipMemoryCache(true)

                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context).asGif().load(url).apply(options).into(imageView);
    }

    public static void loadAsGif(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .error(R.color.white);       //错误图
        Glide.with(context).load(url).apply(options).into(imageView);
    }

    public static void loadAsGifIntResoures(Context context, int url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .error(R.color.white);       //错误图
        Glide.with(context).load(url).apply(options).into(imageView);
    }

    /*
     *加载圆形图片
     */
    public static void loadCircleImage(Context context, String url, ImageView imageView) {
//        if (TextUtils.equals(FileUtils.getFileType(url), "gif"))
//            loadAsGif(context, url, imageView);
//        else {
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .circleCrop()//设置圆形
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(context).load(url).apply(options).into(imageView);
//        }
    }

    public static void loadCircleImage(Context context, int url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .circleCrop();//设置圆形
        Glide.with(context).load(url).apply(options).into(imageView);
    }

//    public static void loadRoundImage(Context context, String url, int radius, ImageView imageView) {
//        if (TextUtils.equals(FileUtils.getFileType(url), "gif"))
//            loadAsGif(context, url, imageView);
//        else {
//            RequestOptions options = RequestOptions.bitmapTransform(new RoundedCornersTransformation(DensityUtil.dip2px(radius), 0));
//            Glide.with(context).load(url).apply(options).into(imageView);
//        }
//    }

    public static void loadRoundImageCenterCrop(Context context, String url, int radius, ImageView imageView) {
//        RequestOptions options = RequestOptions.bitmapTransform(new RoundedCornersTransformation(DensityUtil.dip2px(radius), 0));
        Glide.with(context)
                .load(url)
                .transform(new MultiTransformation(new CenterCrop(), new RoundedCorners(DensityUtil.dip2px(radius))))
                .into(imageView);

    }
//
//    public static void loadRoundLeftRightImage(Context context, String url, int radius, ImageView imageView) {
//        RequestOptions options = RequestOptions.bitmapTransform(new RoundedCornersTransformation(DensityUtil.dip2px(radius), 0, RoundedCornersTransformation.CornerType.TOP));
//        Glide.with(context).load(url).apply(options).into(imageView);
//    }
//
//    public static void loadRoundImage(Context context, Uri uri, int radius, ImageView imageView) {
//        RequestOptions options = RequestOptions.bitmapTransform(new RoundedCornersTransformation(DensityUtil.dip2px(radius), 0));
//        Glide.with(context).load(uri).apply(options).into(imageView);
//    }
//
//    //    Glide.with(this).load(url).bitmapTransform(new RoundedCornersTransformation(this, 30, 0, RoundedCornersTransformation.CornerType.BOTTOM)).crossFade(1000).into(image5);
////原文：https://blog.csdn.net/zhangphil/article/details/52806374
//    public static void loadTopRoundImage(Context context, String url, int radius, ImageView imageView) {
//        RequestOptions options = RequestOptions.bitmapTransform(new GlideRoundTransform(radius, 0, GlideRoundTransform.CornerType.TOP));
//        Glide.with(context).
//                load(url)
//                .apply(options)
//                .into(imageView);
//
//
//    }
//
//
//    /*
//     *加载模糊图片（自定义透明度）
//     */
//    public static void loadBlurImage(Context context, String url, ImageView imageView, int blur) {
//        RequestOptions options = RequestOptions.bitmapTransform(new BlurTransformation(blur))
//                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
//        Glide.with(context).load(url).transition(DrawableTransitionOptions.withCrossFade(300)).apply(options).into(imageView);
//    }
//
//    public static void loadBlurImage(Context context, int url, ImageView imageView, int blur) {
//        RequestOptions options = RequestOptions.bitmapTransform(new BlurTransformation(blur));
//        Glide.with(context).load(url).apply(options).into(imageView);
//    }
//
//    public static void loadBlurImage(Context context, Bitmap bitmap, ImageView imageView, int blur) {
//        RequestOptions options = RequestOptions.bitmapTransform(new BlurTransformation(blur, 8));
//        options.diskCacheStrategy(DiskCacheStrategy.NONE);
//        Glide.with(context).load(bitmap).apply(options).into(imageView);
//    }

    public static void clear(Context context, ImageView imageView) {
        Glide.with(context).clear(imageView);
    }
}
