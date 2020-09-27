package com.gdr.mallutorrentz.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gdr.mallutorrentz.R;
import com.gdr.mallutorrentz.activity.DownloadActivity;
import com.gdr.mallutorrentz.model.HomeUpdateModel;
import com.gdr.mallutorrentz.model.LatestModel;

import java.util.ArrayList;

public class LatestAdapter extends RecyclerView.Adapter < LatestAdapter.ViewHolder > {

    private ArrayList < LatestModel > models;
    private Context context;

    public LatestAdapter(ArrayList < LatestModel > models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull@Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_small_latest, parent, false);
        return new LatestAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        LatestModel model = models.get(position);

        Glide.with(context).load(model.getThumbnaill()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.latestimage);

        holder.latestimage.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View v) {
            Intent i = new Intent(context, DownloadActivity.class);
            context.startActivity(i);
        }
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView latestimage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            latestimage = itemView.findViewById(R.id.smaal_item_image);
        }
    }
}