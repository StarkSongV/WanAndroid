package com.sjc.wanandroid.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sjc.wanandroid.R;
import com.sjc.wanandroid.common.MyApp;


/**
 * Created by qihuiyou on 2017/4/10.
 */

public class ToastUtils {

    private static View toastRoot;

    private ToastUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isShow = true;
    public static android.widget.Toast toast;

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message) {
        if (isShow)
            android.widget.Toast.makeText(context.getApplicationContext(), message, android.widget.Toast.LENGTH_SHORT).show();
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, int message) {
        if (isShow)
            android.widget.Toast.makeText(context.getApplicationContext(), message, android.widget.Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, CharSequence message) {
        if (isShow)
            android.widget.Toast.makeText(context.getApplicationContext(), message, android.widget.Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, int message) {
        if (isShow)
            android.widget.Toast.makeText(context.getApplicationContext(), message, android.widget.Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, CharSequence message, int duration) {
        if (isShow)
            android.widget.Toast.makeText(context.getApplicationContext(), message, duration).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, int message, int duration) {
        if (isShow)
            android.widget.Toast.makeText(context.getApplicationContext(), message, duration).show();
    }

    /**
     * 自定义位置显示Toast
     *
     * @param message
     */
    public static void show(CharSequence message) {
        if (toastRoot==null) {
            toastRoot = LayoutInflater.from(MyApp.getInstance()).inflate(R.layout.common_toast_default_layout, null, false);
        }
        TextView msg = toastRoot.findViewById(R.id.toast_info);
        msg.setText(message.toString());
        if (toast != null) {
            toast.cancel();
            toast = null;
        }

        toast = new android.widget.Toast(MyApp.getInstance());
        toast.setView(toastRoot);
        toast.setGravity(Gravity.BOTTOM, 0, CommonUtil.dip2px(MyApp.getInstance(), 130));
        toast.setDuration(android.widget.Toast.LENGTH_SHORT);
        toast.show();
    }
    /**
     * 自定义位置显示Toast
     *
     * @param strId
     */
    public static void show(int strId) {
        if (toastRoot==null) {
            toastRoot = LayoutInflater.from(MyApp.getInstance()).inflate(R.layout.common_toast_default_layout, null, false);
        }
        TextView msg = toastRoot.findViewById(R.id.toast_info);
        msg.setText(MyApp.getInstance().getResources().getString(strId));
        if (toast != null) {
            toast.cancel();
            toast = null;
        }

        toast = new android.widget.Toast(MyApp.getInstance());
        toast.setView(toastRoot);
        toast.setGravity(Gravity.BOTTOM, 0, CommonUtil.dip2px(MyApp.getInstance(), 130));
        toast.setDuration(android.widget.Toast.LENGTH_SHORT);
        toast.show();
    }
}
