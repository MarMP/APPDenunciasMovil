package com.example.appdenuncias;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {
    InfoFragment page1, page2, page3;

    public MyPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        page1 = new InfoFragment();
        page1.setPosition(1);

        page2 = new InfoFragment();
        page2.setPosition(2);

        page3 = new InfoFragment();
        page3.setPosition(3);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return page1;
            case 1:
                return page2;
            case 2:
                return page3;
        }
        return null;
    }

    @Override
    public int getCount() {
        //devuelve el número de fragmentos que tendremos
        return 3;
    }
}
