package id.ncr.jamsholatapp.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import id.ncr.jamsholatapp.R;
import id.ncr.jamsholatapp.adapter.TabFragmentPager;
import id.ncr.jamsholatapp.fragments.BreakFragment;
import id.ncr.jamsholatapp.fragments.CorrectionFragment;
import id.ncr.jamsholatapp.fragments.GeneralFragment;
import id.ncr.jamsholatapp.fragments.InfoFragment;

public class Main extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));

        viewPager = (ViewPager)findViewById(R.id.pager);
        tabLayout = (TabLayout)findViewById(R.id.tabs);

        viewPager.setAdapter(new TabFragmentPager(getSupportFragmentManager()));

        tabLayout.setTabTextColors(getResources().getColor(R.color.colorPrimaryDark),
                getResources().getColor(android.R.color.white));

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }
}