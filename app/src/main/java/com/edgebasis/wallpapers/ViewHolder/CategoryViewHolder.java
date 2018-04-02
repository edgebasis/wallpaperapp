package com.edgebasis.wallpapers.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.edgebasis.wallpapers.Interface.ItemClickListener;
import com.edgebasis.wallpapers.R;

/**
 * Created by mujtabamahmood on 4/1/18.
 */

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView categoryName;
    public ImageView backgroundImage;

    ItemClickListener itemClickListener;


    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public CategoryViewHolder(View itemView) {
        super(itemView);
        backgroundImage = (ImageView)itemView.findViewById(R.id.categoyImage);
        categoryName = (TextView)itemView.findViewById(R.id.categoryName);

        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
            itemClickListener.onCLick(view, getAdapterPosition());
    }
}
