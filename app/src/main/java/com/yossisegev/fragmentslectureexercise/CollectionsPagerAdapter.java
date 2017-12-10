package com.yossisegev.fragmentslectureexercise;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Yossi Segev on 09/12/2017.
 */

public class CollectionsPagerAdapter extends FragmentStatePagerAdapter {

    private List<CollectionFragment> fragments;

    public CollectionsPagerAdapter(FragmentManager fm, List<CollectionFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
