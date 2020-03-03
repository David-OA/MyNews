package com.oconte.david.mynews;

import android.annotation.SuppressLint;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.oconte.david.mynews.OptionMenu.AboutActivity;
import com.oconte.david.mynews.OptionMenu.HelpActivity;
import com.oconte.david.mynews.OptionMenu.SearchViewActivity;
import com.oconte.david.mynews.ViewPager.ViewPagerAdapter;

import java.util.Objects;

import androidx.viewpager.widget.ViewPager;
import butterknife.ButterKnife;
import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    private MainFragment mainFragment;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tabLayout) TabLayout tabLayout;
    @BindView(R.id.pager) ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        this.configureToolbar();

        this.configureTabLayout();

        this.configureAndShowMainFragmentWithViewPager();

    }

    // -------------------
    // CONFIGURATION
    // -------------------

    private void configureAndShowMainFragmentWithViewPager() {
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }

    private void configureAndShowMainFragment() {

        mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.activity_main_frame_layout);

        if (mainFragment == null) {
            mainFragment = new MainFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_main_frame_layout, mainFragment)
                    .commit();
        }
    }

    /**
     * All for the Toolbar
     */
    protected void configureToolbar() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("My News");

    }

    /**
     * All for the TabLayout
     */
    @SuppressLint("ResourceType")
    protected void configureTabLayout() {
        // Define its gravity and its mode
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        // Define the color to use
        tabLayout.setTabTextColors(getResources().getColorStateList(R.drawable.color_tablayout_selector));

        // Populate your TabLayout
        tabLayout.addTab(tabLayout.newTab().setText("TOP STORIES"));
        tabLayout.addTab(tabLayout.newTab().setText("MOST POPULAR"));
        tabLayout.addTab(tabLayout.newTab().setText("SPORTS"));

    }

    /**
     * For the right menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //View view = toolbar.findViewById(id);
        switch (id) {
            case R.id.menu_main_activity_search:
                this.startSearchActivity();
                return true;
            case R.id.menu_main_activity_notifications:
                this.startNotificationsActivity();
                return true;
            case R.id.menu_main_activity_help:
                this.startHelpActivity();
                return true;
            case R.id.menu_main_activity_about:
                this.startAboutActivity();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void startSearchActivity() {
        Intent intent = new Intent(this, SearchViewActivity.class);
        startActivity(intent);
    }

    private void startNotificationsActivity(){
        Intent intent = new Intent(this, NotificationsActivity.class);
        startActivity(intent);

    }

    private void startHelpActivity(){
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }

    private void startAboutActivity(){
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
}
