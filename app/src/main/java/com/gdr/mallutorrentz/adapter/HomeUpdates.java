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

import java.util.ArrayList;

public class HomeUpdates extends RecyclerView.Adapter < HomeUpdates.ViewHolder > {

    private ArrayList < HomeUpdateModel > models;
    private Context context;

    public HomeUpdates(ArrayList < HomeUpdateModel > models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull@Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_updates_rv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        HomeUpdateModel model = models.get(position);

        Glide.with(context).load(model.getThumbnail()).diskCacheStrategy(DiskCacheStrategy.NONE).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View v) {
            Intent i = new Intent(context, DownloadActivity.class);
            i.putExtra("name", models.get(position).getName());
            context.startActivity(i);
        }
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img);
        }
    }
}