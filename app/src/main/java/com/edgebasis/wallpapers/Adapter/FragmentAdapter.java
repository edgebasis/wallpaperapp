package com.edgebasis.wallpapers.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.edgebasis.wallpapers.Fragment.CategoryFragment;
import com.edgebasis.wallpapers.Fragment.MonthlySpecialFragment;
import com.edgebasis.wallpapers.Fragment.WeeklySpecialFragment;

/**
 * Created by mujtabamahmood on 4/1/18.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    private Context context;

    public FragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0)
            return CategoryFragment.getInstance();
        else if(position == 1)
            return WeeklySpecialFragment.getInestance();
        else if(position ==2)
            return MonthlySpecialFragment.getInstance();
        else
            return null;
    }



    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {


        switch(position)
        {
            case 0:
                return "Category";

            case 1:
                return "Weekly Specials";

            case 2:
                return "Monthly Specials";

        }

        return "";


    }
}
