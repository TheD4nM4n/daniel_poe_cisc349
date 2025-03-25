package com.thed4nm4n.customerlist;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AddCustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_customer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RequestQueue queue = Volley.newRequestQueue(getBaseContext());
        queue.start();

        EditText nameText = findViewById(R.id.name_text);
        EditText addressText = findViewById(R.id.address_text);
        EditText phoneText = findViewById(R.id.phone_text);

        Button addButton = findViewById(R.id.add_button);

        addButton.setOnClickListener(v -> {
            Log.d("TESTING", "ONCLICK ACTIVATED");
            try {
                JSONObject json = new JSONObject()
                        .put("name", nameText.getText())
                        .put("address", addressText.getText())
                        .put("phone", phoneText.getText());

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, getString(R.string.url) + "/add", json, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getBaseContext(), "Added customer.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getBaseContext(), "Error adding customer.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

                queue.add(request);

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });

    }
}