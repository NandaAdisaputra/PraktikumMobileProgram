package com.nandaadisaputra.praktikum1mobileprogram.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.nandaadisaputra.praktikum1mobileprogram.fragment.DetailProfilFragment;
import com.nandaadisaputra.praktikum1mobileprogram.fragment.PortofolioFragment;

public class TabAdapter extends FragmentStatePagerAdapter {

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0) {
            return new DetailProfilFragment();
        }
        return new PortofolioFragment();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        if (position == 0) {
            return "Profil";
        }
        return "Portofolio";
    }

    @Override
    public int getCount() {
        return 2;
    }
}
