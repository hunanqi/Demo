package com.example.utsoft.demo.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.utsoft.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by 胡楠启 on 2017/2/22 10:49
 * Function: jcvp视频播放器框架功能测试
 * Desc:
 */
public class JcvpActivity extends AppCompatActivity {

    @BindView(R.id.videoplayer_jcvpActivity)
    JCVideoPlayerStandard mVideoplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jcvp);
        ButterKnife.bind(this);
        mVideoplayer.setUp("http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4"
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "测试demo");
        mVideoplayer.thumbImageView.setImageURI(Uri.parse("https://www.baidu.com/img/bd_logo1.png"));
    }

    @Override
    public void onBackPressed() {
        //jcvp配置
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
