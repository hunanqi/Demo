package com.example.utsoft.demo.activity;

import android.os.Bundle;

import com.example.utsoft.demo.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 胡楠启 on 2017/2/22 10:24
 * Function: mpa图表框架测试activity
 * Desc:
 */
public class MpaActivity extends BaseActivity {

    @BindView(R.id.lc_chart_mpaActivity)
    LineChart mChartMpa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpa);
        ButterKnife.bind(this);
        init();
    }

    /**
     * 图表数据初始化
     */
    private void init(){
        //填充数据
        List<Entry> enities=new ArrayList<>();
        for(int i=1;i<13;i++){
            Entry mpaEnity=new Entry();
            mpaEnity.setX(i);
            mpaEnity.setY(i*100);
            enities.add(mpaEnity);
        }
        //设置数据数据集，这里可以设置数据的显示颜色等属性
        LineDataSet dataSet = new LineDataSet(enities,"test1");
        //把数据集，格式化到对应的图表中，此处还有chardata等 数据格式
        LineData lineData = new LineData(dataSet);
        //把准备好的数据填充到控件中
        mChartMpa.setData(lineData);
        //刷新控件
        mChartMpa.invalidate();
    }
}
