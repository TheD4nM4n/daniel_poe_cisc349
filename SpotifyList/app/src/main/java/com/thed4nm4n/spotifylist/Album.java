package com.thed4nm4n.spotifylist;

import android.graphics.Bitmap;
import android.media.Image;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;

import java.util.Locale;

public class Album {
    private String imageUrl;
    private String name;
    private String artist;
    private String danceability;
    private String duration;

    public Album(String imageUrl, String name, String artist, String danceability, String duration_ms) {
        this.artist = artist;
        this.danceability = danceability;
        this.duration = String.format("%02d:%02d", (Integer.parseInt(duration_ms) / 1000) / 60, (Integer.parseInt(duration_ms) / 1000) % 60);
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getArtist() {
        return artist;
    }

    public String getDanceability() {
        return danceability;
    }

    public String getDuration() {
        return duration;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setDanceability(String danceability) {
        this.danceability = danceability;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setName(String name) {
        this.name = name;
    }
}
