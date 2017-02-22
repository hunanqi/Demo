package com.example.utsoft.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.utsoft.demo.R;
import com.example.utsoft.demo.entity.DataEntity;
import com.example.utsoft.demo.entity.EventTestEntity;
import com.example.utsoft.demo.entity.UserEntity;
import com.example.utsoft.demo.interfac.RetrofitInterface;
import com.example.utsoft.demo.utils.OkHttpUtils;
import com.example.utsoft.demo.utils.ToastUtils;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by 胡楠启 on 2017/2/21 9:17
 * Function: 主界面
 * Desc:
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.btn_okhttp_mainActivity)
    Button mBtnOkhttp;
    @BindView(R.id.btn_eventBus_mainActivity)
    Button mBtnEventBus;
    @BindView(R.id.btn_retrofit_mainActivity)
    Button mBtnRetrofit;
    @BindView(R.id.btn_glide_mainActivity)
    Button mBtnGlide;
    @BindView(R.id.btn_tab_mainActivity)
    Button mBtnTab;
    @BindView(R.id.btn_reclerview_mainActivity)
    Button mBtnReclerview;
    @BindView(R.id.btn_greendao_mainActivity)
    Button mBtnGreendao;
    @BindView(R.id.btn_mpa_mainActivity)
    Button mBtnMpa;
    @BindView(R.id.btn_jcvp_mainActivity)
    Button mBtnJcvp;
    @BindView(R.id.btn_realm_mainActivity)
    Button mBtnRealm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        reg_EventBus();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    //初始化
    private void init() {
        //初始化Logger
        Logger.init("http");
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.btn_okhttp_mainActivity, R.id.btn_eventBus_mainActivity, R.id.btn_retrofit_mainActivity, R.id
            .btn_glide_mainActivity, R.id.btn_tab_mainActivity, R.id.btn_reclerview_mainActivity, R.id
            .btn_greendao_mainActivity, R.id.btn_mpa_mainActivity, R.id.btn_jcvp_mainActivity
            , R.id.btn_realm_mainActivity})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_okhttp_mainActivity://okhttp测试
                okHttp();
                break;
            case R.id.btn_eventBus_mainActivity://eventbus测试
                Intent intent = new Intent(MainActivity.this, EventBusActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_retrofit_mainActivity://retrofit测试
                retrofit();
                break;
            case R.id.btn_glide_mainActivity://glide测试
                ToastUtils.Show(MainActivity.this);
                break;
            case R.id.btn_tab_mainActivity://tabd动画效果
                startActivity(new Intent(MainActivity.this, TabActivity.class));
                break;
            case R.id.btn_reclerview_mainActivity://reclerview框架测试
                startActivity(new Intent(MainActivity.this, RecyclervieActivity.class));
                break;
            case R.id.btn_greendao_mainActivity://greendao框架测试
                startActivity(new Intent(MainActivity.this, GreenDaoActivity.class));
                break;
            case R.id.btn_mpa_mainActivity://mpa图表测试
                startActivity(new Intent(MainActivity.this, MpaActivity.class));
                break;
            case R.id.btn_jcvp_mainActivity://jcvp视屏播放器
                startActivity(new Intent(MainActivity.this, JcvpActivity.class));
                break;
            case R.id.btn_realm_mainActivity://realm框架测试
                startActivity(new Intent(MainActivity.this,RealmActivity.class));
                break;
        }
    }

    /**
     * okhttp网络请求测试
     */
    private void okHttp() {
        final UserEntity userEntity = new UserEntity();
        userEntity.phone = "13438284220";
        userEntity.key = "6fe9a2f9cc05e6941bcc45e30a32e51a";
        OkHttpUtils.sendPost("http://apis.juhe.cn/mobile/get", userEntity, DataEntity.class, new OkHttpUtils
                .MResponse() {


            @Override
            public void success(Object object) {
                DataEntity dataEntity = (DataEntity) object;
                if (dataEntity.getResultcode().equals("200"))
                    Toast.makeText(MainActivity.this, "13438284220归属地为" + dataEntity.getResult().getCity(), Toast
                            .LENGTH_SHORT)
                            .show();
            }

            @Override
            public void error(String string) {

            }
        });
    }

    /**
     * Eventbus 测试
     *
     * @param eventTestEntity
     */
    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onEventTest(EventTestEntity eventTestEntity) {
        String test = eventTestEntity.getTest();
        if (!TextUtils.isEmpty(test)) {
            mBtnEventBus.setText(test);
        }
    }

    /**
     * retrofit 测试
     */
    private void retrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://apis.juhe.cn/")
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<DataEntity> call = retrofitInterface.getData("13438284220", "6fe9a2f9cc05e6941bcc45e30a32e51a");
        call.enqueue(new Callback<DataEntity>() {
            @Override
            public void onResponse(Call<DataEntity> call, Response<DataEntity> response) {
                String city = response.body().getResult().getCity();
                if (!TextUtils.isEmpty(city)) {
                    Toast.makeText(MainActivity.this, "13438284220归属地为" + city, Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<DataEntity> call, Throwable t) {

            }
        });

    }

}
