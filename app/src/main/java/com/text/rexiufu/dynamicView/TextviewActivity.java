package com.text.rexiufu.dynamicView;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.text.rexiufu.R;
import com.text.rexiufu.behavior.FmPagerAdapter;
import com.text.rexiufu.behavior.TabFragment;

import java.util.ArrayList;
import java.util.List;

public class TextviewActivity extends AppCompatActivity {

    private LinearLayout main_lin;
    private List<TextView> textViews = new ArrayList<>();
    private ViewPager outside_viewpager,inside_viewpager;
    private List<Fragment> mFragments = new ArrayList<>();
    private TabLayout tablayout;
    private String[] titles = new String[]{"最新", "热门", "我的"};

    private static final String TAG = "viewpager";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dybamic_textview);
        main_lin = findViewById(R.id.main_lin);
        outside_viewpager = findViewById(R.id.outside_viewpager);
        inside_viewpager = findViewById(R.id.inside_viewpager);
        tablayout = findViewById(R.id.tablayout);

        initTabLayout();
    }

    private void initTabLayout() {
        //设置TabLayout可滚动，保证Tab数量过多时也可正常显示
//        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //两个参数分别对应Tab未选中的文字颜色和选中的文字颜色
        tablayout.setTabTextColors(Color.WHITE, Color.RED);
        //绑定ViewPager
        tablayout.setupWithViewPager(outside_viewpager);
        //设置TabLayout的布局方式（GRAVITY_FILL、GRAVITY_CENTER）
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        tablayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //设置TabLayout的选择监听
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //点击Tab时回调
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                outside_viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            //重复点击Tab时回调
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
//                scrollToTop(mFragments.get(tab.getPosition()).getTypeList());
            }
        });

        for (int i = 0; i < titles.length; i++) {
            mFragments.add(TabFragment.newInstance(titles[i]));
            tablayout.addTab(tablayout.newTab());
        }

        tablayout.setupWithViewPager(outside_viewpager, false);
        FmPagerAdapter pagerAdapter = new FmPagerAdapter(getSupportFragmentManager(), mFragments, titles);
        outside_viewpager.setAdapter(pagerAdapter);

        for (int i = 0; i < titles.length; i++) {
            tablayout.getTabAt(i).setText(titles[i]);
        }


        outside_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                // arg0是当前选中的页面的Position
                Log.e(TAG, "outside_viewpager onPageSelected------>"+arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // arg0 :当前页面，及你点击滑动的页面；arg1:当前页面偏移的百分比；arg2:当前页面偏移的像素位置
                Log.e(TAG, "outside_viewpager onPageScrolled------>arg0："+arg0+"\nonPageScrolled------>arg1:"+arg1+"\nonPageScrolled------>arg2:"+arg2);
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                //arg0 ==1的时表示正在滑动，arg0==2的时表示滑动完毕了，arg0==0的时表示什么都没做。
                if(arg0 == 0){
                    Log.e(TAG, "outside_viewpager onPageScrollStateChanged------>0");
                }else if(arg0 == 1){
                    Log.e(TAG, "outside_viewpager onPageScrollStateChanged------>1");
                }else if(arg0 == 2){
                    Log.e(TAG, "outside_viewpager onPageScrollStateChanged------>2");
                }

            }
        });

        inside_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                // arg0是当前选中的页面的Position
                Log.e(TAG, "inside_viewpager onPageSelected------>"+arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // arg0 :当前页面，及你点击滑动的页面；arg1:当前页面偏移的百分比；arg2:当前页面偏移的像素位置
                Log.e(TAG, "inside_viewpager onPageScrolled------>arg0："+arg0+"\nonPageScrolled------>arg1:"+arg1+"\nonPageScrolled------>arg2:"+arg2);
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                //arg0 ==1的时表示正在滑动，arg0==2的时表示滑动完毕了，arg0==0的时表示什么都没做。
                if(arg0 == 0){
                    Log.e(TAG, "inside_viewpager onPageScrollStateChanged------>0");
                }else if(arg0 == 1){
                    Log.e(TAG, "inside_viewpager onPageScrollStateChanged------>1");
                }else if(arg0 == 2){
                    Log.e(TAG, "inside_viewpager onPageScrollStateChanged------>2");
                }

            }
        });
    }
}