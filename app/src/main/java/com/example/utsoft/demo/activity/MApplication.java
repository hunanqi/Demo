package com.example.utsoft.demo.activity;

import android.app.Application;
import android.content.Context;

import com.example.utsoft.demo.entity.DaoMaster;
import com.example.utsoft.demo.entity.DaoSession;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import org.greenrobot.greendao.database.Database;

import io.realm.Realm;

/**
 * Created by 胡楠启 on 2017/2/20.
 * Function：自定义Application,负责leackcarany和greendao的初始化
 * Desc：
 */

public class MApplication extends Application {
    private RefWatcher refWatcher;//监控内存变量
    private DaoSession mDaoSession;
    @Override public void onCreate() {
        super.onCreate();
        //数据库初始化
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db");
        Database db =  helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        //leakcanary 初始化需要的操作
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        refWatcher = LeakCanary.install(this);
        //Realm 初始化
        Realm.init(this);
    }
    //leackcanary获取RefWatcher方法
    public static RefWatcher getRefWatcher(Context context) {
        MApplication application = (MApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    /**
     * 获取数据库实例的方法
     * @return
     */
     public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
