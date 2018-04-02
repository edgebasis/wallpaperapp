package com.edgebasis.wallpapers.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridLayout;

import com.edgebasis.wallpapers.Common.Common;
import com.edgebasis.wallpapers.Interface.ItemClickListener;
import com.edgebasis.wallpapers.Model.Category;
import com.edgebasis.wallpapers.R;
import com.edgebasis.wallpapers.ViewHolder.CategoryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


public class CategoryFragment extends Fragment {

    private static CategoryFragment INSTANCE=null;

    FirebaseDatabase fbDatabase;
    DatabaseReference dbRefCategories;

    FirebaseRecyclerOptions<Category> fbrOptCategories;
    FirebaseRecyclerAdapter<Category, CategoryViewHolder> fbrAdpCategories;

    RecyclerView recyclerView;



    public CategoryFragment() {
        fbDatabase = FirebaseDatabase.getInstance();
        dbRefCategories = fbDatabase.getReference(Common.STR_CATEGORIES_REFERENCE);

        fbrOptCategories = new FirebaseRecyclerOptions.Builder<Category>()
                .setQuery(dbRefCategories, Category.class)
                .build();

        fbrAdpCategories = new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(fbrOptCategories) {
            @Override
            protected void onBindViewHolder(@NonNull final CategoryViewHolder holder, int position, @NonNull final Category model) {
                Picasso.with(getActivity())
                        .load(model.getLink())
                        .networkPolicy(NetworkPolicy.OFFLINE)
                        .into(holder.backgroundImage, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {
                                Picasso.with(getActivity())
                                        .load(model.getLink())
                                        .error(R.drawable.ic_terrain_black_24dp)
                                        .into(holder.backgroundImage, new Callback() {
                                            @Override
                                            public void onSuccess() {

                                            }

                                            @Override
                                            public void onError() {
                                                Log.e("Error EB", "Image couldn't be loaded");
                                            }
                                        });
                            }
                        });

                holder.categoryName.setText(model.getName());
                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onCLick(View view, int position) {

                    }
                });

            }

            @NonNull
            @Override
            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_category_item, parent, false);

                return new CategoryViewHolder(itemView);
            }
        };

    }


    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.fragmentCatRecyclerview);
        //recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        setCategory();
        return  view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(fbrAdpCategories!=null)
                fbrAdpCategories.startListening();
    }

    @Override
    public void onStop() {
        if(fbrAdpCategories!=null)
            fbrAdpCategories.stopListening();
        super.onStop();

    }

    @Override
    public void onResume() {
        super.onResume();

        if(fbrAdpCategories!=null)
            fbrAdpCategories.startListening();
    }

    private void setCategory() {

    fbrAdpCategories.startListening();
    recyclerView.setAdapter(fbrAdpCategories);

    }


    public static CategoryFragment getInstance() {

        if(INSTANCE == null)
                INSTANCE = new CategoryFragment();
        return INSTANCE;
    }
}
