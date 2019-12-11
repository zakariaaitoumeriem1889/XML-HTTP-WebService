package com.moustalik.tahar.filmsfavoris.utils;

import android.net.Uri;
import android.util.Log;

import com.moustalik.tahar.filmsfavoris.model.Film;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworksUtils {
      private static final String MOVIEDB_BASE_URL= "http://api.themoviedb.org/3/movie";
     private  static final String API_KEY = "bef6ffe3aeed3b94d039389b559b9f95";

    public static final String  TITLE = "title",
            OVERVIEW = "overview",
            RELEASE_DATE = "release_date",
            RATING = "vote_average",
            POSTER = "poster_path";

    public static URL buildUrl(String[] query) throws MalformedURLException {
        Uri builtUri = Uri.parse(MOVIEDB_BASE_URL).buildUpon()
                .appendPath(query[0])
                .appendQueryParameter("api_key", API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                String resultat = scanner.next();
                Log.i("RES", resultat);
                return resultat;
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static Film[] filmsDataToArray(String filmJsonResponse) throws JSONException {
        final String RESULTS = "results";
        final String ORIGINAL_TITLE = "original_title";
        final String POSTER_PATH = "poster_path";
        final String OVERVIEW = "overview";
        final String VOTER_AVERAGE = "vote_average";
        final String RELEASE_DATE = "release_date";

        JSONObject filmsJson = new JSONObject(filmJsonResponse);
        JSONArray resultatsArray = filmsJson.getJSONArray(RESULTS);

        Film[] films = new Film[resultatsArray.length()];

        for (int i = 0; i < resultatsArray.length(); i++) {

            films[i] = new Film();
            JSONObject movieInfo = resultatsArray.getJSONObject(i);
            films[i].setOriginalTitle(movieInfo.getString(ORIGINAL_TITLE));
            films[i].setPosterPath(movieInfo.getString(POSTER_PATH));
            films[i].setOverview(movieInfo.getString(OVERVIEW));
            films[i].setVoterAverage(movieInfo.getDouble(VOTER_AVERAGE));
            films[i].setReleaseDate(movieInfo.getString(RELEASE_DATE));
        }

        return films;
    }



}
