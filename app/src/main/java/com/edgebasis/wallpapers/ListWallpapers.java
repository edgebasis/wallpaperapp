package com.edgebasis.wallpapers;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.edgebasis.wallpapers.Common.Common;
import com.edgebasis.wallpapers.Interface.ItemClickListener;
import com.edgebasis.wallpapers.Model.Wallpaper;
import com.edgebasis.wallpapers.ViewHolder.WallpapersViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class ListWallpapers extends AppCompatActivity {

    Query query;
    FirebaseRecyclerOptions<Wallpaper> options;
    FirebaseRecyclerAdapter<Wallpaper, WallpapersViewHolder> wallpaperAdapter;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_wallpapers);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBarListWP);
        Log.e("LOG>>>: ", Common.CATEGORY_SELECTED);
        toolbar.setTitle(Common.CATEGORY_SELECTED);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview_list_WP);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        loadWallpapers();


    }

    private void loadWallpapers() {

        query = FirebaseDatabase.getInstance().getReference(Common.STR_WALLPAPERS_REFERENCE)
                .orderByChild("categoryId").equalTo(Common.CATEGORY_ID_SELECTED);

        options = new FirebaseRecyclerOptions.Builder<Wallpaper>()
                .setQuery(query, Wallpaper.class)
                .build();
        wallpaperAdapter = new FirebaseRecyclerAdapter<Wallpaper, WallpapersViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final WallpapersViewHolder holder, int position, @NonNull final Wallpaper model) {
                Picasso.with(getBaseContext())
                        .load(model.getLink())
                        .networkPolicy(NetworkPolicy.OFFLINE)
                        .into(holder.wallpaper, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {
                                Picasso.with(getBaseContext())
                                        .load(model.getLink())
                                        .error(R.drawable.ic_terrain_black_24dp)
                                        .into(holder.wallpaper, new Callback() {
                                            @Override
                                            public void onSuccess() {

                                            }

                                            @Override
                                            public void onError() {
                                                Log.e("Error EB: ", "Could not load the image");
                                            }
                                        });
                            }
                        });
                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onCLick(View view, int position) {

                    }
                });
            }

            @Override
            public WallpapersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_wallpaper_item, parent, false);
                int height = parent.getMeasuredHeight()/2;
                itemView.setMinimumHeight(height);
                return new WallpapersViewHolder(itemView);
            }
        };

        wallpaperAdapter.startListening();
        recyclerView.setAdapter(wallpaperAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        if(wallpaperAdapter!=null)
        wallpaperAdapter.startListening();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(wallpaperAdapter!=null)
            wallpaperAdapter.startListening();

    }

    @Override
    protected void onStop() {
        if(wallpaperAdapter!=null)
            wallpaperAdapter.stopListening();
        super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
