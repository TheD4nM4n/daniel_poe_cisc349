package com.thed4nm4n.networklist;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Customer> customerList = new ArrayList<>();

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

        ListView customerListView = findViewById(R.id.listView);

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest customerRequest = new JsonArrayRequest(getString(R.string.url),
            new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject customerData;
                    String name;
                    String phone;
                    String address;

                    try {
                        customerData = response.getJSONObject(i);
                        name = customerData.getString("name");
                        phone = customerData.getString("phone");
                        address = customerData.getString("address");

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    Customer customer = new Customer(name, phone, address);
                    customerList.add(customer);
                }
                CustomerListAdapter adapter = new CustomerListAdapter(this, customerList);
                customerListView.setAdapter(adapter);
            }
        },  new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR", "Failed to fetch customer data.");
            }
        });

        queue.add(customerRequest);
        queue.start();


    }
}