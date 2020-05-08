package com.text.rexiufu.behavior;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.appbar.AppBarLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.text.rexiufu.R;

import java.util.ArrayList;
import java.util.List;

public class BehaviorActivity extends AppCompatActivity {

    private CoordinatorLayout main_content;
    private AppBarLayout appbar;
    private Toolbar toolbar;
    private TabLayout tablayout;
    private ViewPager viewpager;

    private String[] titles = new String[]{"最新", "热门", "我的"};
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior);
        bindView();
    }

    private void bindView() {
        main_content = findViewById(R.id.main_content);
        appbar = findViewById(R.id.appbar);
        toolbar = findViewById(R.id.toolbar);
        tablayout = findViewById(R.id.tablayout);
        viewpager = findViewById(R.id.viewpager);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.icon_start);
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle("setTitle");
        toolbar.setSubtitle("setSubtitle");
        toolbar.setBackgroundColor(getResources().getColor(R.color.blue));
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Log.e("1111","asdasdsad");
                return false;
            }
        });

        initTabLayout();
    }

    private void initTabLayout() {
        //设置TabLayout可滚动，保证Tab数量过多时也可正常显示
//        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //两个参数分别对应Tab未选中的文字颜色和选中的文字颜色
        tablayout.setTabTextColors(Color.WHITE, Color.RED);
        //绑定ViewPager
        tablayout.setupWithViewPager(viewpager);
        //设置TabLayout的布局方式（GRAVITY_FILL、GRAVITY_CENTER）
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        tablayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //设置TabLayout的选择监听
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //点击Tab时回调
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
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

        tablayout.setupWithViewPager(viewpager, false);
        FmPagerAdapter pagerAdapter = new FmPagerAdapter(getSupportFragmentManager(), mFragments, titles);
        viewpager.setAdapter(pagerAdapter);

        for (int i = 0; i < titles.length; i++) {
            tablayout.getTabAt(i).setText(titles[i]);
        }
    }
}