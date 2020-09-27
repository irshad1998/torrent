package com.gdr.mallutorrentz.adapter;

import android.app.Activity;
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
import com.gdr.mallutorrentz.activity.HomeActivity;
import com.gdr.mallutorrentz.model.LatestModel;
import com.gdr.mallutorrentz.model.MalayalamModel;

import java.util.ArrayList;

public class MalayalamAdapter extends RecyclerView.Adapter < MalayalamAdapter.ViewHolder > {

    private ArrayList < MalayalamModel > models;
    private Context context;

    public MalayalamAdapter(ArrayList < MalayalamModel > models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull@Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_small_malayalam, parent, false);
        return new MalayalamAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MalayalamModel model = models.get(position);

        Glide.with(context).load(model.getAThumbnaill()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.mImage);

        holder.mImage.setOnClickListener(new View.OnClickListener() {@Override
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

        ImageView mImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.smaal_item_image_malayalam);
        }
    }
}