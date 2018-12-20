package com.umeng.soexample.monthlianxi;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.umeng.soexample.monthlianxi.base.BaseActivity;
import com.umeng.soexample.monthlianxi.fragment.GwcFragment;
import com.umeng.soexample.monthlianxi.fragment.ShouYeFragment;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends BaseActivity implements View.OnClickListener {
    private ViewPager vp;
    private TextView txtShou;
    private TextView txtGou;
    private List<Fragment> list;
    private ShouYeFragment shouYeFragment;
    private GwcFragment gwcFragment;
    private int page = 0;
    public ShowActivity() {
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_show;
    }

    @Override
    protected void initViews() {
        vp = findViewById(R.id.vp);
        txtShou = findViewById(R.id.txt_shouye);
        txtGou = findViewById(R.id.txt_gwc);
        list = new ArrayList<>();
        list.add(new ShouYeFragment());
        list.add(new GwcFragment());
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        getData(page);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                getData(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onClicks() {
        txtShou.setOnClickListener(this);
        txtGou.setOnClickListener(this);
    }

    @Override
    protected void progress() {

    }
    private void getData(int page) {
        vp.setCurrentItem(page);
        txtShou.setBackgroundColor(page == 0? Color.GRAY: Color.WHITE);
        txtGou.setBackgroundColor(page == 1? Color.GRAY:Color.WHITE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txt_shouye:
                page = 0;
                break;
            case R.id.txt_gwc:
                page = 1;
                break;
        }
        getData(page);
    }
}
