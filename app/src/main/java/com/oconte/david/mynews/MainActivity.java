package com.oconte.david.mynews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import butterknife.ButterKnife;
import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    private MainFragment mainFragment;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        this.configureToolbar();
        this.configureAndShowMainFragment();
    }

    // -------------------
    // CONFIGURATION
    // -------------------

    private void configureAndShowMainFragment() {

        mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.activity_main_frame_layout);

        if (mainFragment == null) {
            mainFragment = new MainFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_main_frame_layout, mainFragment)
                    .commit();
        }
    }

    protected void configureToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setSubtitle("New York Time");

        //afficher le bouton retour
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
