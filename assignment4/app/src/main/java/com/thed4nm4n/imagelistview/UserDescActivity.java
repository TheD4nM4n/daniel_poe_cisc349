package com.thed4nm4n.imagelistview;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.util.Objects;

public class UserDescActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_desc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String name = Objects.requireNonNull(getIntent().getExtras()).getString("name");
        String description = getIntent().getExtras().getString("desc");

        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView descTextView = findViewById(R.id.descriptionTextView);
        ImageView imageView = findViewById(R.id.profilePicImageView);

        RequestQueue queue = Volley.newRequestQueue(this);

        ImageRequest request = new ImageRequest("https://static.toiimg.com/photo/msid-67586673/67586673.jpg", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imageView.setImageBitmap(response);
            }
        }, 512, 512, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR", "Error loading image");
            }
        });

        queue.add(request);

        nameTextView.setText(name);
        descTextView.setText(description);
    }
}