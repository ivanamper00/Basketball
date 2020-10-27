package com.billy.dalawa.controller.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.billy.dalawa.R;
import com.billy.dalawa.controller.MainController;
import com.billy.dalawa.controller.activity.fragment.HomeFragment;
import com.billy.dalawa.controller.activity.fragment.LeaguesFragment;
import com.billy.dalawa.controller.activity.fragment.LivesFragment;
import com.billy.dalawa.controller.activity.fragment.MatchesFragment;
import com.billy.dalawa.controller.activity.fragment.PlayersFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    public static BottomNavigationView bottomNavigationView;
    MainController controller;
    FrameLayout frameLayout;
    FragmentManager fragmentManager;
    LivesFragment livesFragment;
    MatchesFragment matchesFragment;
    HomeFragment homeFragment;
    PlayersFragment playersFragment;
    LeaguesFragment leaguesFragment;
    int counter;

    private BottomNavigationView.OnNavigationItemSelectedListener listener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch(item.getItemId()){
                case R.id.live:
                    livesFragment = new LivesFragment();
                    counter = 0;
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.home_frame_layout, livesFragment).commit();
                    return true;
                case R.id.matches:
                    matchesFragment = new MatchesFragment();
                    counter = 0;
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.home_frame_layout, matchesFragment).commit();
                    return true;
                case R.id.home:
                    homeFragment = new HomeFragment();
                    counter = 0;
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.home_frame_layout, homeFragment).commit();
                    return true;
                case R.id.players:
                    playersFragment = new PlayersFragment();
                    counter = 0;
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.home_frame_layout, playersFragment).commit();
                    return true;
                case R.id.leagues:
                    leaguesFragment = new LeaguesFragment();
                    counter = 0;
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.home_frame_layout, leaguesFragment).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main();
    }

    public void main(){
        controller = new MainController(this);
        bottomNavigationView = findViewById(R.id.home_navigation_menu);
        frameLayout = findViewById(R.id.home_frame_layout);
        bottomNavigationView.setOnNavigationItemSelectedListener(listener);
        bottomNavigationView.setSelectedItemId(R.id.home);

        this.counter = 0;
    }

    @Override
    public void onBackPressed() {
        Log.d("asdasdsad", String.valueOf(counter));
        switch (counter){
            case 0:
                bottomNavigationView.setSelectedItemId(R.id.home);
                counter++;
                break;
            case 1:
                Toast.makeText(this, "Press Back to Exit", Toast.LENGTH_SHORT).show();
                counter++;
                break;
            case 2:
                finish();
                break;
        }
    }
}