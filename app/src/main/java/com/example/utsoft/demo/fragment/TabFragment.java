package com.example.utsoft.demo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.utsoft.demo.R;
/**
 * Created by 胡楠启 on 2017/2/21 9:39
 * Function:
 * Desc:tab切换动画测试中viewpager中的fragment
 */
public class TabFragment extends Fragment {
    private String mTitle;
   //获取实例
    public static TabFragment getInstance(String title) {
        TabFragment sf = new TabFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab, null);
        TextView card_title_tv = (TextView) v.findViewById(R.id.tv_tapfragment);
        card_title_tv.setText(mTitle);

        return v;
    }
}