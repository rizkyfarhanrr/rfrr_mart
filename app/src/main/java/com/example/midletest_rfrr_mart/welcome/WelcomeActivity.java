package com.example.midletest_rfrr_mart.welcome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.midletest_rfrr_mart.R;
import com.example.midletest_rfrr_mart.adapter.SliderPagerAdapter;
import com.example.midletest_rfrr_mart.dashboard.DashboardActivity;
import com.example.midletest_rfrr_mart.function.PreferenceSliderManager;
import com.google.android.material.tabs.TabLayout;

public class WelcomeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Button buttonnext, buttongetstart;
    private SliderPagerAdapter sliderPagerAdapter;
    private PreferenceSliderManager preferenceSliderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferenceSliderManager = new PreferenceSliderManager(this);
        if (!preferenceSliderManager.isFirstTimeLaunch()){
            LaunchToDashboard();
            finish();
        }

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.activity_welcome);

        getSupportActionBar().hide();

        viewPager = findViewById(R.id.pagerIntroSlider);
        TabLayout tabLayout = findViewById(R.id.tabs);
        buttonnext = findViewById(R.id.ButtonNext);
        buttongetstart = findViewById(R.id.getstarted);

        sliderPagerAdapter = new SliderPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        viewPager.setAdapter(sliderPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        changeStatusBarColor();

        buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager.getCurrentItem() < sliderPagerAdapter.getCount()) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
            }
        });

        buttongetstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    LaunchToDashboard();
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == sliderPagerAdapter.getCount() -1){
                    buttonnext.setVisibility(View.GONE);
                    buttongetstart.setVisibility(View.VISIBLE);
                } else {
                    buttonnext.setVisibility(View.VISIBLE);
                    buttongetstart.setVisibility(View.GONE);
                    buttonnext.setText("Next");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void LaunchToDashboard() {
        preferenceSliderManager.setFirstTimeLaunch(false);
        startActivity(new Intent(WelcomeActivity.this, DashboardActivity.class));
        finish();
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
