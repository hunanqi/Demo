package com.example.utsoft.demo.adpter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 胡楠启 on 2017/2/21.
 * Function：
 * Desc：
 */

public class MFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment>  mFragments;
    private String[] mTitles;
    public MFragmentAdapter(FragmentManager fm, List<Fragment> mFragments,String[] mTitles) {
        super(fm);
        this.mFragments=mFragments;
        this.mTitles=mTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
