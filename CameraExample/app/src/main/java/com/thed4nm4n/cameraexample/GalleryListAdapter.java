package com.thed4nm4n.cameraexample;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

public class GalleryListAdapter extends BaseAdapter {

    private List<Bitmap> images;
    private Context context;
    private int currentIndex = 0;

    public GalleryListAdapter(Context context, List<Bitmap> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.gallery_row, parent);

        ImageView[] imageViews = {
                view.findViewById(R.id.image_1),
                view.findViewById(R.id.image_2),
                view.findViewById(R.id.image_3)};

        for (ImageView imageView : imageViews) {
            imageView.setImageBitmap(images.get(currentIndex));
            currentIndex++;
        }

        return view;
    }
}
