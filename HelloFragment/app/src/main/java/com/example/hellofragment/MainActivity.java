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

    private HelloFragmentB fragmentB;   //podany kod
    private HelloFragmentA fragmentA;   //podany kod

    BottomNavigationView navbar;        //podany kod

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);                     //podany kod

        if (fragmentA == null) {                                    //podany kod
            fragmentA = new HelloFragmentA();                       //podany kod
        }

        if (fragmentB == null) {                                    //podany kod
            fragmentB = new HelloFragmentB();                       //podany kod
        }

        navbar = findViewById(R.id.bottom_nav_bar);                 //podany kod
        navbar.setOnNavigationItemSelectedListener(navListener);    //podany kod

        FragmentManager fragmentManager = getSupportFragmentManager();                  //TODO zad. 2.2
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();   //TODO zad. 2.2
        fragmentTransaction.replace(R.id.fragment_container, fragmentA).commit();       //TODO zad. 2.2
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =                         //podany kod
            new BottomNavigationView.OnNavigationItemSelectedListener() {                               //podany kod
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {                       //podany kod
                    Fragment selectedFragment = null;                                                   //podany kod
                    switch (item.getItemId()) {                                                         //podany kod
                        case R.id.fragment_a:                                                           //podany kod
                            selectedFragment = fragmentA;                                               //podany kod
                            break;                                                                      //podany kod
                        case R.id.fragment_b:                                                           //podany kod
                            selectedFragment = fragmentB;                                               //podany kod
                            break;                                                                      //podany kod
                    }
                    FragmentManager fragmentManager = getSupportFragmentManager();                      //podany kod
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();       //podany kod
                    fragmentTransaction.replace(R.id.fragment_container, selectedFragment).commit();    //podany kod
                    return true;
                }
            };

    //TODO zad. 3
    @Override
    public void sendData(String text) {
        fragmentB.updateData(text);
    }

    //TODO zad. 4
    @Override
    public void newCity(String city) {
        fragmentA.updateCity(city);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragmentA)
                .commit();
    }
}
