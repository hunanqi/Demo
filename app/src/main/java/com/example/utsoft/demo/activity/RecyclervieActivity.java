package com.example.utsoft.demo.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.utsoft.demo.R;
import com.example.utsoft.demo.adpter.MRecyclerViewAdapter;
import com.example.utsoft.demo.entity.RecyclerviewTestEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 胡楠启 on 2017/2/21 10:30
 * Function: recyclerview的adapter框架+下拉刷新框架+Rxjava框架测试
 * Desc:
 */
public class RecyclervieActivity extends BaseActivity {

    @BindView(R.id.rv_test_recyclerViewActivity)
    RecyclerView mRvTest;//recyclerview
    @BindView(R.id.ptf_test_recyclerViewActivity)
    PtrClassicFrameLayout mPtfTest;//下拉刷新控件
    private MRecyclerViewAdapter mAdapter;
    private List<RecyclerviewTestEntity> mEntivitys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclervie);
        ButterKnife.bind(this);
        init();
        setListener();//设置监听事件
    }

    //数据等的初始化
    private void init() {
        mEntivitys = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            RecyclerviewTestEntity testEntity = new RecyclerviewTestEntity();
            testEntity.setTest1("测试" + (i + 1));
            testEntity.setTest2("测试" + (i + 1));
            mEntivitys.add(testEntity);
        }
        mAdapter = new MRecyclerViewAdapter(R.layout.item_recyclerview, mEntivitys);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);//启动动画
        mAdapter.isFirstOnly(false);//重复执行，默认的只执行一次
        mRvTest.setLayoutManager(new LinearLayoutManager(RecyclervieActivity.this));
        mRvTest.setAdapter(mAdapter);
        //下面是关于下拉刷新的代码
        mPtfTest.setLastUpdateTimeRelateObject(this);
        mPtfTest.setPtrHandler(new PtrHandler() {//下拉刷新的监听事件
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {//开始刷新
                reFreshData();//更新数据
            }
        });
    }

    //设置item的点击事件和item上的控件的点击事件
    private void setListener() {
        //添加item点击事件
        mRvTest.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(RecyclervieActivity.this, "点击了item" + position, Toast.LENGTH_SHORT).show();
            }
        });
        //添加item上的控件的点击事件，需要在adapter中绑定控件
        mRvTest.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int id = view.getId();
                Toast.makeText(RecyclervieActivity.this, "点击了子控件" + id + "点击的位置" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //下拉刷新时 刷新列表的数据
    private void reFreshData() {
        //Rxandroid，在子线程中等待2秒模拟网络加载数据然后刷新数据。
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Thread.sleep(2000);
                e.onNext("sss");
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        //下面是刷新数据
                        mPtfTest.refreshComplete();//刷新完成收回头部
                        mEntivitys.clear();
                        for (int i = 0; i < 30; i++) {
                            RecyclerviewTestEntity testEntity = new RecyclerviewTestEntity();
                            int a = (int) (Math.random() * 100);
                            testEntity.setTest1("测试" + a);
                            testEntity.setTest2("测试" + a);
                            mEntivitys.add(testEntity);
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }
}
