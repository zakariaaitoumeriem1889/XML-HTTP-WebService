package com.moustalik.tahar.filmsfavoris;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.moustalik.tahar.filmsfavoris.model.Film;
import com.moustalik.tahar.filmsfavoris.utils.NetworksUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mPostersRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressBar mLoadingIndicator;
    private final int NUM_OF_COLUMNS = 2;
    private final String POPULAR_QUERY = "popular";
    private final String TOP_RATED_QUERY = "top_rated";
    Film[] films;
    ImagePosterAdapter mImagePosterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPostersRecyclerView = findViewById(R.id.rv_films_posters);
        mLoadingIndicator = findViewById(R.id.pb_loading_indicator);
        mLayoutManager = new GridLayoutManager(this,NUM_OF_COLUMNS);
        mPostersRecyclerView.setLayoutManager(mLayoutManager);
        mPostersRecyclerView.setHasFixedSize(true);
        new FetchFilmsDataAsyncTask().execute(POPULAR_QUERY);



    }

    private void showLoadingIndicator(){
        mPostersRecyclerView.setVisibility(View.INVISIBLE);
        mLoadingIndicator.setVisibility(View.VISIBLE);
    }

    private void showRecyclerView() {
        mPostersRecyclerView.setVisibility(View.VISIBLE);
        mLoadingIndicator.setVisibility(View.INVISIBLE);
    }

    public class FetchFilmsDataAsyncTask extends AsyncTask<String, Void, Film[]> {
        public FetchFilmsDataAsyncTask() {
            super();
        }

        @Override
        protected void onPreExecute() {
            showLoadingIndicator();
            super.onPreExecute();
        }

        @Override
        protected Film[] doInBackground(String... params) {
            // Holds data returned from the API
            String filmResultats = null;

            try {
                URL url = NetworksUtils.buildUrl(params);
                filmResultats = NetworksUtils.getResponseFromHttpUrl(url);

                if(filmResultats == null) {
                    return null;
                }
            } catch (IOException e) {
                return null;
            }

            try {
                return NetworksUtils.filmsDataToArray(filmResultats);
            } catch (JSONException e) {
                e.printStackTrace ();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Film[] films) {

            mImagePosterAdapter = new ImagePosterAdapter(getApplicationContext(), films);
            mPostersRecyclerView.setAdapter(mImagePosterAdapter);
            showRecyclerView();
        }
    }
}
