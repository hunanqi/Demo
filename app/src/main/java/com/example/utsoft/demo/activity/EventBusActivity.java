package com.example.utsoft.demo.activity;

import android.os.Bundle;
import android.widget.Button;

import com.example.utsoft.demo.R;
import com.example.utsoft.demo.entity.EventTestEntity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * Created by 胡楠启 on 2017/2/21 9:17
 * Function: 测试eventbus功能activity
 * Desc:
 */
public class EventBusActivity extends BaseActivity {

    @BindView(R.id.btn_test_eventBusActivity)
    Button mBtnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_test_eventBusActivity)
    public void onClick() {
        EventTestEntity testEntity=new EventTestEntity();
        testEntity.setTest("eventbus测试成功");
        EventBus.getDefault().post(testEntity);
        EventBusActivity.this.finish();
    }
}
