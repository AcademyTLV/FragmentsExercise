package com.yossisegev.fragmentslectureexercise;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yossisegev.fragmentslectureexercise.entities.Photo;

import java.util.List;

/**
 * Created by Yossi Segev on 10/12/2017.
 */

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.PhotoViewHolder> {

    private Picasso picasso;
    private List<Photo> photos;

    public PhotoListAdapter(Picasso picasso, List<Photo> photos) {
        this.picasso = picasso;
        this.photos = photos;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_list_item, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        Photo photo = photos.get(position);
        picasso.load(photo.getUrls().getSmall()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    static class PhotoViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        public PhotoViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.photo_list_item_image);
        }
    }


}
