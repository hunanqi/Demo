package com.example.utsoft.demo.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.utsoft.demo.R;

import cn.bingoogolapple.bgabanner.BGABanner;
/**
 * Created by 胡楠启 on 2017/2/21 9:31
 * Function: 引导页activity
 * Desc:
 */
public class WellocmActivity extends BaseActivity {

    private BGABanner mBackgroundBanner;//背景图片引导页
    private BGABanner mForegroundBanner;//背景图片上的文字引导页

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();//初始化view
        setListener();//设置监听事件
        processLogic();//设置数据源
    }
    private void initView() {
        setContentView(R.layout.activity_wellocm);
        mBackgroundBanner = (BGABanner) findViewById(R.id.banner_background_wellocmActivity);
        mForegroundBanner = (BGABanner) findViewById(R.id.banner_foreground_wellocmActivity);
    }
    /**
     * 设置进入按钮和跳过按钮控件资源 id 及其点击事件
     * 如果进入按钮和跳过按钮有一个不存在的话就传 0
     * 在 BGABanner 里已经帮开发者处理了防止重复点击事件
     * 在 BGABanner 里已经帮开发者处理了「跳过按钮」和「进入按钮」的显示与隐藏
     */
    private void setListener() {
        mForegroundBanner.setEnterSkipViewIdAndDelegate(R.id.btn_enter_wellocmActivity, R.id.tv_skip_wellocmActivity, new BGABanner
                .GuideDelegate() {
            @Override
            public void onClickEnterOrSkip() {
                startActivity(new Intent(WellocmActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void processLogic() {
        // 设置数据源
        mBackgroundBanner.setData(R.drawable.img_background_1, R.drawable.img_background_2, R.drawable
                .img_background_3);

        mForegroundBanner.setData(R.drawable.img_foreground_1, R.drawable.img_foreground_2, R.drawable
                .img_foreground_3);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 如果开发者的引导页主题是透明的，需要在界面可见时给背景 Banner 设置一个白色背景，避免滑动过程中两个
        // Banner 都设置透明度后能看到 Launcher
        mBackgroundBanner.setBackgroundResource(android.R.color.white);
    }
}
