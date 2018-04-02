package com.edgebasis.wallpapers.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edgebasis.wallpapers.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MonthlySpecialFragment extends Fragment {

    private static MonthlySpecialFragment INSTANCE=null;

    public MonthlySpecialFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_monthly_special, container, false);
    }

    public static MonthlySpecialFragment getInstance() {

        if(INSTANCE == null)
            INSTANCE = new MonthlySpecialFragment();
        return INSTANCE;
    }

}
