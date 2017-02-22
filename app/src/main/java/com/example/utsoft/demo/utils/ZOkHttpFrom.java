package com.example.utsoft.demo.utils;

import android.text.TextUtils;

import com.orhanobut.logger.Logger;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Created by 胡楠启 on 2017/2/17.
 * Function：j将自己的实体类转化为okhttp的表单
 * Desc：
 */

public class ZOkHttpFrom {
    /**
     * 得到okhttp的表单
     * @param clazz  请求实体类的class类型
     * @param object 请求实体类
     * @return okhttp内置表单
     */
    public  static RequestBody getRequestBody(Class<?> clazz, Object object){
        HashMap<String,Object> data=getValuesHash(clazz,object);
        FormBody.Builder builder = new FormBody.Builder();
        Iterator<Map.Entry<String,Object>> iter = data.entrySet().iterator();
        StringBuilder post=new StringBuilder();
        while (iter.hasNext()) {
            Map.Entry<String,Object> entry = (Map.Entry<String,Object>) iter.next();
            Object value=entry.getValue();
            if(TextUtils.isEmpty(String.valueOf(value))||TextUtils.isEmpty(entry.getKey())){
                continue;
            }
            builder.add(entry.getKey(),String.valueOf(value));
            post.append(entry.getKey()+"="+String.valueOf(value)+"&");
        }
        Logger.i("post数据:"+post.toString());
        return builder.build();
    }

    /**
     * 把请求实体类里面的参数取出来，然后放入hashmap中
     * @param clazz
     * @param object
     * @return
     */
    public static HashMap<String,Object> getValuesHash(Class<?> clazz,Object object){
        HashMap<String,Object> result=new HashMap<String,Object>();
        if(object.equals(null))return result;
        Field[] field  = clazz.getFields();
        for(Field f : field){
            if ("serialVersionUID".equals(f.getName()))
                continue;
            try {
                Object value=f.get(object);
                 if(value!=null){
                     result.put(String.valueOf(f.getName()),value);
                 }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

}
