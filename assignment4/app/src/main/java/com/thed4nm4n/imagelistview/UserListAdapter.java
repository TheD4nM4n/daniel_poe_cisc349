package com.thed4nm4n.imagelistview;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

public class UserListAdapter extends BaseAdapter {
    Context context;
    String[] nameList;
    String[] descList;
    LayoutInflater inflater;
    RequestQueue queue;
    public UserListAdapter(Context applicationContext, String[] nameList, String[] descList) {
        this.context = applicationContext;
        this.nameList = nameList;
        this.descList = descList;
        inflater = (LayoutInflater.from(applicationContext));
        queue = Volley.newRequestQueue(applicationContext);

    }

    @Override
    public int getCount() {
        return nameList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_listview, null);
        TextView name = view.findViewById(R.id.nameTextView);
        TextView desc = view.findViewById(R.id.descriptionTextView);

        ImageView profilePic = view.findViewById(R.id.profile_pic);

        ImageRequest request = new ImageRequest(
                "https://static.toiimg.com/photo/msid-67586673/67586673.jpg",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        profilePic.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        profilePic.setImageResource(R.drawable.ic_launcher_background);
                    }
                });

        queue.add(request);

        name.setText(nameList[i]);
        desc.setText(descList[i]);
        return view;
    }
}
