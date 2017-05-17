package id.ncr.jamsholatapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import id.ncr.jamsholatapp.fragments.BreakFragment;
import id.ncr.jamsholatapp.fragments.CorrectionFragment;
import id.ncr.jamsholatapp.fragments.GeneralFragment;
import id.ncr.jamsholatapp.fragments.InfoFragment;

/**
 * Created by fikri on 01/02/17.
 */

public class TabFragmentPager extends FragmentPagerAdapter {
    String[] title = new String[]{"Umum", "jeda", "Koreksi", "Info Jum'at"};

    public TabFragmentPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new GeneralFragment();
                break;
            case 1:
                fragment = new BreakFragment();
                break;
            case 2:
                fragment = new CorrectionFragment();
                break;
            case 3:
                fragment = new InfoFragment();
                break;
            default:
                fragment = null;
                break;
        }

        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public int getCount() {
        return title.length;
    }
}