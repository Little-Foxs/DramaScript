package com.liangwang.dramascriptmodule.Views;

import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.EditText;
import android.widget.TextView;

import com.liangwang.dramascriptlibs.Function.ScriptLogger.ScriptLogger;
import com.liangwang.dramascriptlibs.UI.ScriptBarView;
import com.liangwang.dramascriptmodule.R;
import com.liangwang.dramascriptmodule.Views.Fragment1;
import com.liangwang.dramascriptmodule.Views.Fragment2;
import com.liangwang.dramascriptmodule.Views.Fragment3;
import com.liangwang.dramascriptmodule.Views.Fragment4;


/**
 * 　　　　　　　　┏┓　　　┏┓
 * 　　　　　　　┏┛┻━━━┛┻┓
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃
 * 　　　　　　　┃　＞　　　＜　┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃...　⌒　...　┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃　Code is far away from bug with the animal protecting
 * 　　　　　　　　　┃　　　┃   神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┗━━━┓
 * 　　　　　　　　　┃　　　　　　　┣┓
 * 　　　　　　　　　┃　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛
 * <p>
 * ━━━━━━感觉萌萌哒━━━━━━
 * <p>
 * 作者：LigthWang
 * <p>
 * 描述：
 */
public class HomeActivity extends AppCompatActivity{

    public static final String TAG = "HomeActivity";
    private ScriptBarView tabBarAnimView;
    private ViewPager vp;
    private Drawable drawable[] = new Drawable[5];
    private View groundView;
    private int[] location;
    private int pageTable = 0;
    private int lastPageTable = -1;
    private float maxSize = 0;
    private int initY = 0;
    private long durationMillis = 500;


    //闪动动画
    private Animation animation = new Animation() {
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            float size = (maxSize - 1) * interpolatedTime + 1;
            Matrix matrix = t.getMatrix();
            matrix.postTranslate(location[0] - groundView.getWidth() / 2, location[1] - groundView.getHeight() / 2);
            matrix.postScale(size, size, location[0], location[1]);
            if (interpolatedTime == 1) {
                groundView.setVisibility(View.INVISIBLE);
                onTabBarClickListener.onMainBtnsClick(pageTable);
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ScriptLogger.init();
        initView();
        initListener();
        initDrawable();
        initTabBarAnimView();
        onTabBarClickListener.onMainBtnsClick(pageTable);
    }

    private void initView() {
        groundView = findViewById(R.id.groundView);
        vp = (ViewPager) findViewById(R.id.vp);
        vp.setAdapter(new viewPager(getSupportFragmentManager()));
        tabBarAnimView = (ScriptBarView) findViewById(R.id.tabBarAnimView);
    }

    private void initListener() {
        tabBarAnimView.setOnTabBarClickListener(onTabBarClickListener);
        animation.setDuration(durationMillis);
        animation.setInterpolator(new DecelerateInterpolator());
    }

    private void initDrawable() {
        drawable[0] = getResources().getDrawable(R.drawable.page0);
        drawable[1] = getResources().getDrawable(R.drawable.page1);
        drawable[2] = getResources().getDrawable(R.drawable.page1);
        drawable[3] = getResources().getDrawable(R.drawable.page2);
        drawable[4] = getResources().getDrawable(R.drawable.page3);
    }
    private void initTabBarAnimView() {
        tabBarAnimView.setMainBitmap(R.drawable.plus_icon);
        tabBarAnimView.bindBtnsForPage(0, R.drawable.nearby_icon, R.drawable.search_icon, R.drawable.chats_icon);
        tabBarAnimView.bindBtnsForPage(1, R.drawable.profile_icon, R.drawable.edit_icon, 0);
        tabBarAnimView.bindBtnsForPage(2, R.drawable.chats_icon, R.drawable.search_icon, 0);
        tabBarAnimView.bindBtnsForPage(3, R.drawable.settings_icon, 0, R.drawable.edit_icon);
        tabBarAnimView.initializePage(0);
    }

    private ScriptBarView.OnTabBarClickListener onTabBarClickListener = new ScriptBarView.OnTabBarClickListener() {

        @Override
        public void onMainBtnsClick(int position, int[] clickLocation) {
            if (lastPageTable == position)
                return;
            if (position < 5) {
                clickLocation[1] = clickLocation[1] - initY;
                location = clickLocation;
                pageTable = position;
                groundView.setVisibility(View.VISIBLE);
                groundView.startAnimation(animation);
            }
            lastPageTable = position;
        }

        @Override
        public void onMainBtnsClick(int position) {
//            imageView.setImageDrawable(drawable[position]);
            switch (position){
                case 0:
                    vp.setCurrentItem(0);
                    Log.e(TAG,"我是碎片1");
                    break;
                case 1:
                    vp.setCurrentItem(1);
                    Log.e(TAG,"我是碎片2");
                    break;
                case 2:
//                    vp.setCurrentItem(2);
//                    Log.e(TAG,"我是碎片3");
                    break;
                case 3:
                    vp.setCurrentItem(2);
                    Log.e(TAG,"我是碎片3");
                    break;
                case 4:
                    vp.setCurrentItem(3);
                    Log.e(TAG,"我是碎片4");
                    break;
            }
            Log.e(TAG, "center--->" + position);
        }

        @Override
        public void onLeftBtnClick(int page) {
            Log.e(TAG, "left--->" + page);
        }

        @Override
        public void onRightBtnClick(int page) {
            Log.e(TAG, "right--->" + page);
        }

    };

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        int local[] = new int[2];
        groundView.getLocationOnScreen(local);
        initY = local[1];
        maxSize = 2f * (float) (((ViewGroup) groundView.getParent()).getHeight() / groundView.getHeight());
        super.onWindowFocusChanged(hasFocus);
    }

    class viewPager extends FragmentStatePagerAdapter {

        public viewPager(FragmentManager fm) {
            super(fm);
        }

        // 每个条目返回的fragment
        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new Fragment1();
            } else if (position == 1) {
                return new Fragment2();
            } else if (position == 2) {
                return new Fragment3();
            } else{
                return new Fragment4();
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

}
