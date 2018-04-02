package com.edgebasis.wallpapers.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edgebasis.wallpapers.R;

public class WeeklySpecialFragment extends Fragment {

    private static WeeklySpecialFragment INSTANCE= null;

    public WeeklySpecialFragment() {
        // Required empty public constructor
    }

    public static WeeklySpecialFragment getInestance()
    {
        if(INSTANCE == null)
                INSTANCE = new WeeklySpecialFragment();
        return INSTANCE;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weekly_special, container, false);
    }


}
