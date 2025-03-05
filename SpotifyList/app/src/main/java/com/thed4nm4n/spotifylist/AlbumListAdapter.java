package com.thed4nm4n.spotifylist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

public class AlbumListAdapter extends BaseAdapter {
    private Context context;
    private ImageLoader imageLoader;
    private List<Album> albums;

    public AlbumListAdapter(Context context, ImageLoader imageLoader, List<Album> albums) {
        this.context = context;
        this.albums = albums;
        this.imageLoader = imageLoader;
    }

    @Override
    public int getCount() {
        return null == albums ? 0 : albums.size();
    }

    @Override
    public Object getItem(int position) {
        return null == albums ? 0 : albums.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, null);

        NetworkImageView coverImageView = view.findViewById(R.id.album_image);

        TextView nameTextView = view.findViewById(R.id.album_name);
        TextView artistTextView = view.findViewById(R.id.artist_name);
        TextView danceabilityTextView = view.findViewById(R.id.danceability_score);
        TextView durationTextView = view.findViewById(R.id.duration_ms);

        nameTextView.setText(albums.get(position).getName());
        artistTextView.setText(albums.get(position).getArtist());
        danceabilityTextView.setText(String.format("danceability: %s", albums.get(position).getDanceability()));
        durationTextView.setText(albums.get(position).getDuration());
        coverImageView.setImageUrl(albums.get(position).getImageUrl(), imageLoader);

        return view;
    }
}
