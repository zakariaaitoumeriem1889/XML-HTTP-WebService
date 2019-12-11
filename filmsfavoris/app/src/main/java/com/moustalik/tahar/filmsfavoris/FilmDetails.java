package com.moustalik.tahar.filmsfavoris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.moustalik.tahar.filmsfavoris.model.Film;
import com.squareup.picasso.Picasso;

public class FilmDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_details);
        TextView moOriginalTitle = (TextView) findViewById (R.id.original_title);
        TextView mRating = (TextView) findViewById (R.id.average_vote);
        TextView mReleaseDate = (TextView) findViewById (R.id.release_date);
        TextView mOverview = (TextView) findViewById (R.id.overview);
        ImageView mposterImage = (ImageView) findViewById (R.id.poster_image);
        ImageView mBackDropImage = findViewById(R.id.backdrop_image);

        Intent intent = getIntent();


        Film film = intent.getParcelableExtra("film");

        // TITLE
        moOriginalTitle.setText(film.getOriginalTitle());
        // VOTER AVERAGE / RATING
        mRating.setText (String.valueOf(film.getVoterAverage ()) + " / 10");
        // IMAGE
        Picasso.with(this)
                .load(film.getPosterPath())
                .fit()
                .error(R.mipmap.ic_launcher_round)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(mposterImage);
        Picasso.with(this)
                .load(film.getPosterPath())
                .fit()
                .error(R.mipmap.ic_launcher_round)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(mBackDropImage);

        // OVERVIEW
        mOverview.setText (film.getOverview ());

        // RELEASE DATE
        mReleaseDate.setText (film.getReleaseDate());
    }
}
