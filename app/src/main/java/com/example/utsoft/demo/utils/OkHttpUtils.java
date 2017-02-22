package com.example.utsoft.demo.utils;

import android.os.Handler;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 胡楠启 on 2017/2/17.
 * Function：
 * Desc：二次封装的工具类
 */

public class OkHttpUtils {
    private static Handler handler=new Handler();

    /**
     * 网络请求方法
     * @param url
     * @param bin  请求实体类
     * @param tClass 请求实体类的class
     * @ram mresponse 返回接口
     * @param <T>  服务器返回的java类
     */
    public static <T> void sendPost(String url, Object bin, final Class<T> tClass, final
    MResponse mresponse) {
        Logger.i("请求地址:"+url);
        OkHttpClient httpClient = new OkHttpClient();
        RequestBody requestBody = ZOkHttpFrom.getRequestBody(bin.getClass(), bin);
        Request request=new Request.Builder().url(url).post(requestBody).build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {//这里是网址找不到就会进入
                final String s = e.toString();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mresponse.error(s);
                    }
                });
            }
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String errocode = response.toString();
                if(response.isSuccessful()){
                    String json = response.body().string();
                    Logger.json(json);
                    if (TextUtils.isEmpty(json))
                        return;
                    Gson gson=new Gson();
                    final T t = gson.fromJson(json, tClass);
                        handler.post(new Runnable() {//进入主线程
                            @Override
                            public void run() {
                                mresponse.success(t);
                            }
                        });

                }else{//这里代表服404等错误
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            mresponse.error(errocode);
                        }
                    });
                }
            }
        });
    }

    public interface MResponse {
        /**
         * 请求成功返回，返回状态码显示成功
         * @param object
         */
        void success(Object object);


        /**
         * 网络请求错误，包括链接无效，404等
         * @param string
         */
        void error(String string);
    }
}
