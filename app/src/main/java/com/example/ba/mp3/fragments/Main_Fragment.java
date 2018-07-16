package com.example.ba.mp3.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ba.mp3.R;

public class Main_Fragment extends Fragment {
    private ViewPager viewPager;
    private TabLayout tab;
    private ItemAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_main,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        viewPager=(ViewPager)view.findViewById(R.id.viewpager);
        tab=(TabLayout)view.findViewById(R.id.tab);
        adapter=new ItemAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);


    }

    private static class ItemAdapter extends FragmentPagerAdapter{

        public ItemAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if ( position == 1) {
                return new Artist_Fragment();
            }else {
                return new Song_Fragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            if ( position == 1) {
                return "Artists";
            }else {
                return "Songs";
            }
        }
    }

}
