 package com.motion.takku;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.motion.takku.Fragment.EventsFragment;
import com.motion.takku.Fragment.HomeFragment;
import com.motion.takku.Fragment.ProfleFragment;
import com.motion.takku.Fragment.RankFragment;

 public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNav;
    private String title = "TAK-Ku";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar(title);

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            title = "TAK-Ku";
                            initToolbar(title);
                            break;
                        case R.id.nav_event:
                            selectedFragment = new EventsFragment();
                            title = "Events";
                            initToolbar(title);
                            break;
                        case R.id.nav_rank:
                            selectedFragment = new RankFragment();
                            title = "Rank";
                            initToolbar(title);
                            break;
                        case R.id.nav_profile:
                            selectedFragment = new ProfleFragment();
                            title = "Profile";
                            initToolbar(title);
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment)
                            .commit();

                    return true;
                }
            };

    public void initToolbar(String title){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
    }


 }