package com.lynn.base.uitls;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Toast统一管理类
 ABC
 */
public class ToastUtil {

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }




    /**
     * 短时间
     * 居中显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShortCenter(Context context, CharSequence message) {
//        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}
