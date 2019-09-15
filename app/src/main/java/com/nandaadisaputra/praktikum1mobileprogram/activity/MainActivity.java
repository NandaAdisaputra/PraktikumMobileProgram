package com.nandaadisaputra.praktikum1mobileprogram.activity;


import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.nandaadisaputra.praktikum1mobileprogram.R;
import com.nandaadisaputra.praktikum1mobileprogram.activity.crud.CrudActivity;
import com.nandaadisaputra.praktikum1mobileprogram.fragment.NilaiFragment;
import com.nandaadisaputra.praktikum1mobileprogram.fragment.PortofolioFragment;
import com.nandaadisaputra.praktikum1mobileprogram.fragment.ProfilFragment;

public class MainActivity extends AppCompatActivity {
    private Fragment pageContent = new ProfilFragment();
    private String title = "Home";
    private String KEY_TITLE;
    private String KEY_FRAGMENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = findViewById(R.id.main_toolbar);
        final DrawerLayout drawerLayout = findViewById(R.id.main_drawer);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        NavigationView navigationView = findViewById(R.id.main_navigation);
        navigationView.setNavigationItemSelectedListener(menuItem -> {

            switch (menuItem.getItemId()) {
                case R.id.menu_home:
                    pageContent = new ProfilFragment();
                    title = "Home";
                    break;
                case R.id.menu_nilai:
                    pageContent = new NilaiFragment();
                    title = "Nilai";
                    break;
                case R.id.menu_portofolio:
                    pageContent = new PortofolioFragment();
                    title = "Portofolio";
                    break;
                case R.id.menu_perhitungan:
                    startActivity(new Intent(MainActivity.this, KalkulatorActivity.class));
                    title = "Kalkulator";
                    break;
                case R.id.menu_bangundatar:
                    startActivity(new Intent(MainActivity.this, BangunDatarActivity.class));
                    title = "Bangun Datar";
                    break;
                case R.id.menu_formpenjualan:
                    startActivity(new Intent(MainActivity.this, FormPenjualanActivity.class));
                    title = "Form Penjualan";
                    break;
                case R.id.menu_ifelse:
                    startActivity(new Intent(MainActivity.this, IfElseActivity.class));
                    title = "If Else";
                    break;
                case R.id.menu_googlemap:
                    startActivity(new Intent(MainActivity.this, GoogleMapActivity.class));
                    title = "Google Map";
                    break;
                case R.id.menu_crud:
                    startActivity(new Intent(MainActivity.this, CrudActivity.class));
                    title = "Crud Sqlite";
                    break;
                case R.id.menu_logout:
                    pageContent = new Fragment();
                    title = "Exit Apps";
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pageContent).commit();
            toolbar.setTitle(title);
            drawerLayout.closeDrawers();
            return true;
        });
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pageContent).commit();
            toolbar.setTitle(title);
        } else {
            pageContent = getSupportFragmentManager().getFragment(savedInstanceState, KEY_FRAGMENT);
            title = savedInstanceState.getString(KEY_TITLE);

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pageContent).commit();
            toolbar.setTitle(title);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_TITLE, title);
        getSupportFragmentManager().putFragment(outState, KEY_FRAGMENT, pageContent);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }

}