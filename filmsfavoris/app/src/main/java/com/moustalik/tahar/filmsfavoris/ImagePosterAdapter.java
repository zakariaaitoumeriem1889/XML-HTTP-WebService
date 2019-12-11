package com.moustalik.tahar.filmsfavoris;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moustalik.tahar.filmsfavoris.model.Film;
import com.squareup.picasso.Picasso;


public class ImagePosterAdapter extends RecyclerView.Adapter<ImagePosterAdapter.ViewHolder> {
    private final Film[] mFilms;
    private final Context mContext;
    View.OnClickListener listener;

    public ImagePosterAdapter(Context mContext, Film[] mFilms) {
        this.mFilms = mFilms;
        this.mContext = mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;

        public ViewHolder(ImageView v) {
            super(v);
            mImageView = v;
        }
    }
    @NonNull
    @Override
    // Create new views (Invoked by the Layout Manager)
    public ImagePosterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Create a new view
        ImageView v = (ImageView) LayoutInflater.from(parent.getContext ())
                .inflate (R.layout.image_poster_view, parent, false);

        ViewHolder vh = new ViewHolder (v);
        return vh;
    }

    @Override
    // Replace the contents of a view (Invoked by the layout manager)
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Picasso.with(mContext)
                .load(mFilms[position].getPosterPath())
                .fit()
                .error(R.mipmap.ic_launcher_round)
                .placeholder(R.mipmap.ic_launcher_round)
                .into((ImageView) holder.mImageView.findViewById (R.id.poster_view));

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, FilmDetails.class);
            intent.putExtra("film", mFilms[position]);
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (mFilms == null || mFilms.length == 0) {
            return -1;
        }

        return mFilms.length;
    }
}
