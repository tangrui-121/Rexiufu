package com.text.rexiufu.bottombar;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.text.rexiufu.R;

import java.util.List;

public class BottombarActivity extends AppCompatActivity {

    private ViewPager viewpager;
    private LinearLayout lin_tab1, lin_tab2, lin_tab3, lin_tab4, lin_tab5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottombar);

        viewpager = findViewById(R.id.viewpager);
        lin_tab1 = findViewById(R.id.lin_tab1);
        lin_tab2 = findViewById(R.id.lin_tab2);
        lin_tab3 = findViewById(R.id.lin_tab3);
        lin_tab4 = findViewById(R.id.lin_tab4);
        lin_tab5 = findViewById(R.id.lin_tab5);

        getSupportFragmentManager().beginTransaction().replace(R.id.viewpager, new Fragment1()).commit();

    }


    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> list;

        public ViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}