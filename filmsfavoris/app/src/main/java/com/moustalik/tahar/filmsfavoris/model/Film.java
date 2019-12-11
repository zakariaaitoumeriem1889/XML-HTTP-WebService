package com.moustalik.tahar.filmsfavoris.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Film implements Parcelable{
    private String originalTitle;
    private String posterPath;
    private String overview;
    private String releaseDate;
    private Double voterAverage;

    final String POSTER_BASE_URL = "https://image.tmdb.org/t/p/w185";

    public Film() {

    }

    // SETTER METHODS
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    public void setVoterAverage(Double voterAverage) {
        this.voterAverage = voterAverage;
    }

    // GETTER METHODS
    public String getOriginalTitle() {
        return originalTitle;
    }
    public String getPosterPath() {
        return POSTER_BASE_URL + posterPath;
    }
    public String getOverview() {
        return overview;
    }
    public String getReleaseDate() {
        return releaseDate;
    }
    public Double getVoterAverage() {
        return voterAverage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(originalTitle);
        dest.writeString(posterPath);
        dest.writeString(overview);
        dest.writeString(releaseDate);
        dest.writeDouble(voterAverage);
    }

    public Film(Parcel parcel) {
        originalTitle = parcel.readString();
        posterPath = parcel.readString();
        overview = parcel.readString();
        releaseDate = parcel.readString();
        voterAverage = parcel.readDouble();
    }

    public static final Parcelable.Creator<Film> CREATOR = new Parcelable.Creator<Film>() {
        public Film createFromParcel(Parcel src) {
            return new Film(src);
        }

        public Film[] newArray(int size) {
            return new Film[size];
        }
    };
}
