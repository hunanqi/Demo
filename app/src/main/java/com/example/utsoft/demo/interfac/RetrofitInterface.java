package com.example.utsoft.demo.interfac;

import com.example.utsoft.demo.entity.DataEntity;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 胡楠启 on 2017/2/20.
 * Function：
 * Desc：retroft框架测试接口
 */

public interface RetrofitInterface {
    @POST("mobile/get")
    Call<DataEntity> getData(@Query("phone") String phone,
                               @Query("key") String key);


}
