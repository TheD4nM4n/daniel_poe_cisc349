package com.thed4nm4n.spotifylist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Cache;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Album> albumList = new ArrayList<>();
    private ImageLoader imageLoader;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        queue = Volley.newRequestQueue(this);

        imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<>(20);

            @Override
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }
        });

        JsonArrayRequest request = new JsonArrayRequest(getString(R.string.data_url), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    String imageUrl;
                    String name;
                    String artist;
                    String danceability;
                    String duration;

                    try {
                        JSONObject albumData = response.getJSONObject(i);
                        name = albumData.getString("album_name");
                        artist = albumData.getString("artist_name");
                        danceability = albumData.getString("danceability");
                        duration = albumData.getString("duration_ms");
                        imageUrl = albumData.getString("album_img");

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    Album album = new Album(imageUrl, name, artist, danceability, duration);
                    albumList.add(album);
                }

                ListView listView = findViewById(R.id.list_view);
                listView.setAdapter(new AlbumListAdapter(getApplicationContext(), imageLoader, albumList));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(MainActivity.this, AlbumActivity.class);
                        intent.putExtra("name", albumList.get(position).getName());
                        intent.putExtra("imageUrl", albumList.get(position).getImageUrl());
                        MainActivity.this.startActivity(intent);
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR", "Unable to do the big thing.");
            }
        });

        queue.add(request);
        queue.start();

    }
}