package com.thed4nm4n.customerlist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Customer> customers;

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

        queue = VolleyQueueSingleton.getInstance(this).getQueue();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            CustomerAddSheet bottomSheet = new CustomerAddSheet();
            bottomSheet.show(getSupportFragmentManager(),  bottomSheet.getTag());
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        JsonArrayRequest request = new JsonArrayRequest(JsonArrayRequest.Method.POST, getString(R.string.url) + "/all", null,
                response -> {
                    customers = new ArrayList<>();

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);
                            customers.add(new Customer(object));
                            Log.d("TESTING", customers.toString());
                        } catch (JSONException e) {
                            Log.d("ERROR", "Error parsing JSON content.");
                        }
                    }

                    ListView listView = findViewById(R.id.list_view);
                    CustomerListAdapter adapter = new CustomerListAdapter(listView.getContext(), customers);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener((parent, view, position, id)-> {
                        Intent intent = new Intent(this, EditCustomerActivity.class);
                        intent.putExtra("customer", customers.get(position));
                        startActivity(intent);
                    });

                },
                error -> Log.d("ERROR", "Error fetching content."));

        queue.add(request);
    }
}