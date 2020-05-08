package com.text.rexiufu.viewpager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.text.rexiufu.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class ViewpagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager vp;
    private int[] imageIdArray;//图片资源的数组
    private List<View> viewList;//图片资源的集合
    private ViewGroup vg;//放置圆点

    //实例化原点View
    private ImageView iv_point;
    private ImageView[] ivPointArray;

    private boolean isLooper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        //加载ViewPager
        initViewPager();

        //加载底部圆点
        initPoint();

        //修改添加设置ViewPager的当前页，为了保证左右轮播
        vp.setCurrentItem(0);


        //开启一个线程，用于循环
        new Thread(new Runnable() {
            @Override
            public void run() {
                isLooper = true;
                while (isLooper) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //这里是设置当前页的下一页
                            vp.setCurrentItem(vp.getCurrentItem() + 1);
                        }
                    });
                }
            }
        }).start();
    }

    /**
     * 加载底部圆点
     */
    private void initPoint() {
        //这里实例化LinearLayout
        vg = (ViewGroup) findViewById(R.id.guide_ll_point);
        //根据ViewPager的item数量实例化数组
        ivPointArray = new ImageView[viewList.size()];
        //循环新建底部圆点ImageView，将生成的ImageView保存到数组中
        int size = viewList.size();
        for (int i = 0; i < size; i++) {
            iv_point = new ImageView(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(12, 12);
            lp.setMargins(10,0,10,0);
            iv_point.setLayoutParams(lp);
            ivPointArray[i] = iv_point;
            //第一个页面需要设置为选中状态，这里采用两张不同的图片
            if (i == 0) {
                iv_point.setBackgroundResource(R.mipmap.viewpager_checked);
            } else {
                iv_point.setBackgroundResource(R.mipmap.viewpager_unchecked);
            }
            //将数组中的ImageView加入到ViewGroup
            vg.addView(ivPointArray[i]);
        }


    }

    /**
     * 加载图片ViewPager
     */
    private void initViewPager() {
        vp = (ViewPager) findViewById(R.id.guide_vp);
        //实例化图片资源
        imageIdArray = new int[]{R.mipmap.banner1, R.mipmap.banner2, R.mipmap.banner3, R.mipmap.banner4};
        viewList = new ArrayList<>();
        //获取一个Layout参数，设置为全屏
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        //循环创建View并加入到集合中
        int len = imageIdArray.length;
        for (int i = 0; i < len; i++) {
            //new ImageView并设置全屏和图片资源
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            imageView.setBackgroundResource(imageIdArray[i]);

            //将ImageView加入到集合中
            viewList.add(imageView);
        }

        //View集合初始化好后，设置Adapter
        vp.setAdapter(new GuidePageAdapter(viewList));
        //设置滑动监听
        vp.setOnPageChangeListener(this);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 滑动后的监听
     *
     * @param position
     */
    @Override
    public void onPageSelected(int position) {

        //修改全部的position长度
        int newPosition = position % viewList.size();

        //循环设置当前页的标记图
        int length = imageIdArray.length;
        for (int i = 0; i < length; i++) {
            ivPointArray[newPosition].setBackgroundResource(R.mipmap.viewpager_checked);
            if (newPosition != i) {
                ivPointArray[i].setBackgroundResource(R.mipmap.viewpager_unchecked);
            }
        }

//        //循环设置当前页的标记图
//        int length = imageIdArray.length;
//        for (int i = 0;i<length;i++){
//            ivPointArray[position].setBackgroundResource(R.mipmap.full_holo);
//            if (position != i){
//                ivPointArray[i].setBackgroundResource(R.mipmap.empty_holo);
//            }
//        }

        //判断是否是最后一页，若是则显示按钮
//        if (position == imageIdArray.length - 1){
//            ib_start.setVisibility(View.VISIBLE);
//        }else {
//            ib_start.setVisibility(View.GONE);
//        }
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        isLooper = false;
    }


//    private void showBanner() {
//        List<BannerBean> bannerBeans = new ArrayList<>();
//        BannerBean bean1 = new BannerBean();
//        bean1.setImg_resource(R.mipmap.banner3);
//        BannerBean bean2 = new BannerBean();
//        bean2.setImg_resource(R.mipmap.banner4);
//        bannerBeans.add(bean1);
//        bannerBeans.add(bean2);
//        bannerM.setBannerBeanList(bannerBeans);
//        bannerM.setOnItemClickListener(new BannerM.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
////                Uri uri = Uri.parse("http://www.quantum-info.com");
////                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
////                startActivity(intent);
//            }
//        });
//        bannerM.show();
//    }
}