package com.example.hellofragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements HelloFragmentA.HelloFragmentAListener,
        HelloFragmentB.SendCityNameListener {

    private HelloFragmentB fragmentB;
    private HelloFragmentA fragmentA;

    BottomNavigationView navbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (fragmentA == null) {
            fragmentA = new HelloFragmentA();
        }

        if (fragmentB == null) {
            fragmentB = new HelloFragmentB();
        }

        navbar = findViewById(R.id.bottom_nav_bar);
        navbar.setOnNavigationItemSelectedListener(navListener);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragmentA).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.fragment_a:
                            selectedFragment = fragmentA;
                            break;
                        case R.id.fragment_b:
                            selectedFragment = fragmentB;
                            break;
                    }
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };

    @Override
    public void sendData(String text) {
        fragmentB.updateData(text);
    }

    @Override
    public void newCity(String city) {
        fragmentA.updateCity(city);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragmentA)
                .commit();
    }
}
