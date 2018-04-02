package com.edgebasis.wallpapers.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.edgebasis.wallpapers.Interface.ItemClickListener;
import com.edgebasis.wallpapers.R;



/**
 * Created by mujtabamahmood on 4/2/18.
 */

public class WallpapersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ItemClickListener itemClickListener;

    public ImageView wallpaper;

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public WallpapersViewHolder(View itemView) {
        super(itemView);
        wallpaper = (ImageView)itemView.findViewById(R.id.wallpaperImageView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
