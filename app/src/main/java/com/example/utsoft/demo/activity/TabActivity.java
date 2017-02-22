package com.example.utsoft.demo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.utsoft.demo.R;
import com.example.utsoft.demo.adpter.MFragmentAdapter;
import com.example.utsoft.demo.fragment.TabFragment;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 胡楠启 on 2017/2/21 9:12
 * Function: tab切换activity
 * Desc:
 */
public class TabActivity extends AppCompatActivity implements OnTabSelectListener{
    @BindView(R.id.tab_tabActivity)
    SlidingTabLayout mTab1;//顶部tab
    @BindView(R.id.vp_tabActivity)
    ViewPager mVp;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
            "热门", "iOS", "Android"
            , "前端", "后端", "设计", "工具资源"
    };
  private MFragmentAdapter mAdapter;//自定义适配器
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        ButterKnife.bind(this);
        init();
    }
    //初始化
     private void init(){
         //为fragment 中textviet 设置文字
         for (String title : mTitles) {
             mFragments.add(TabFragment.getInstance(title));
         }
         mAdapter = new MFragmentAdapter(getSupportFragmentManager(),mFragments,mTitles);
         mVp.setAdapter(mAdapter);
         mTab1.setViewPager(mVp);
         mVp.setCurrentItem(4);//设置显示第四个fragment
         mTab1.showDot(4);//设置第四个tab 显示未读消息红点
         mTab1.setOnTabSelectListener(this);
     }
    //tab的选择事件
    @Override
    public void onTabSelect(int position) {
        Toast.makeText(TabActivity.this,mTitles[position]+"",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTabReselect(int position) {
        Toast.makeText(TabActivity.this,position+"",Toast.LENGTH_SHORT).show();
    }

}
