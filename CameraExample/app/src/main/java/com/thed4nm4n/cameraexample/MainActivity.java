package com.thed4nm4n.cameraexample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import android.util.Base64;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Bitmap image;
    public static int REQUEST_IMAGE_CAPTURE = 1;
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

        imageView = findViewById(R.id.image_view);

        findViewById(R.id.capture_button).setOnClickListener((v) -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        });

        findViewById(R.id.send_button).setOnClickListener(v -> {
            uploadToServer(base64Encode(image, Bitmap.CompressFormat.PNG, 80));
        });

        findViewById(R.id.gallery_button).setOnClickListener(v -> {
            String url = "http://192.168.1.13:5000/get-images";
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, null, response -> {
                Intent intent = new Intent(this, GalleryActivity.class);
                ArrayList<JSONObject> imageObjects = new ArrayList<>();
                for (int i = 0; i < response.length(); i++){
                    try {
                        imageObjects.add((JSONObject) response.get(i));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }

                ArrayList<Bitmap> images = new ArrayList<>();
                for (JSONObject object : imageObjects) {
                    try {
                        images.add(base64DecodeImage((String) object.get("image")));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                Log.d("TEST", imageObjects.toString());
                intent.putExtra("images", images);
                startActivity(intent);

            }, error -> {
                Log.d("Test", "Error fetching images");
            });
            queue.add(request);
        });

        queue = Volley.newRequestQueue(this);
        queue.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (resultCode == RESULT_OK) {
                assert data != null;
                Bundle extras = data.getExtras();
                image = (Bitmap) extras.get("data");
                imageView.setImageBitmap(image);
            }
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Camera cancelled.", Toast.LENGTH_SHORT).show();
        }
    }

    public static String base64Encode(Bitmap image, Bitmap.CompressFormat format, int quality) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(format, quality, stream);
        return Base64.encodeToString(stream.toByteArray(), Base64.DEFAULT);
    }
    public static Bitmap base64DecodeImage(String base64) {
        InputStream imageStream = new ByteArrayInputStream(Base64.decode(base64.getBytes(), Base64.DEFAULT));
        return BitmapFactory.decodeStream(imageStream);
    }
    private void uploadToServer(final String image) {
        JSONObject object = new JSONObject();

        try {
            object.put("image", image);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        String url = "http://192.168.1.13:5000/send-image";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object, response -> {
            Log.d("Test", "Upload complete");
        }, error -> {
            Log.d("Test", "Upload error");
        });

        queue.add(request);
    }
}