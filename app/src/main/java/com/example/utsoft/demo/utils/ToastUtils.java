package com.example.utsoft.demo.utils;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

/**
 * Created by 胡楠启 on 2017/2/20.
 * Function：
 * Desc：Toast工具类
 */

public class ToastUtils {
    public static void Show(Context context) {
        Toast toast = new Toast(context);
        ImageView imageView = new ImageView(context);
        Glide.with(context).load("https://www.baidu.com/img/bd_logo1.png").into(imageView);
        toast.setView(imageView);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}
