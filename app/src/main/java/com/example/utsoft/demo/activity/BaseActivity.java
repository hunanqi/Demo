package com.example.utsoft.demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.squareup.leakcanary.RefWatcher;

import org.greenrobot.eventbus.EventBus;
/**
 * Created by 胡楠启 on 2017/2/21 9:16
 * Function: activity的基类，包含是否注册eventbus,内存检测代码
 * Desc:
 */
public class BaseActivity extends AppCompatActivity {
    private boolean isReginEventBus;//表示是否注册eventbus的标识变量
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isReginEventBus) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        if (isReginEventBus) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
        //leakcanary 检测
        RefWatcher refWatcher = MApplication.getRefWatcher(this);
        refWatcher.watch(this);
    }

    //注册+注销
    public void reg_EventBus() {
        isReginEventBus = true;
    }
}
