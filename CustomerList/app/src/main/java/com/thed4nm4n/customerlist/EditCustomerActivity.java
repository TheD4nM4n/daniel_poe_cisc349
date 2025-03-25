package com.thed4nm4n.customerlist;

import android.os.Bundle;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class EditCustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sheet_customer_edit);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Customer customer = (Customer) Objects.requireNonNull(getIntent().getExtras()).get("customer");

        assert customer != null;

        TextView nowEditing = findViewById(R.id.now_editing);
        nowEditing.setText(String.format("Now editing %s", customer.getName()));

        ListView commentsList = findViewById(R.id.comments_list);
        BaseAdapter adapter = new CommentsListAdapter(this, customer);

        commentsList.setAdapter(adapter);

        Button addCommentButton = findViewById(R.id.add_comment_button);
        addCommentButton.setOnClickListener((v) -> {
            customer.addComment();
            adapter.notifyDataSetChanged();
        });

        Button updateButton = findViewById(R.id.update_button);
        updateButton.setOnClickListener(v -> {

            try {
                JSONObject object = new JSONObject()
                        .put("name", customer.getName())
                        .put("comments", new JSONArray(customer.getComments()));

                JsonObjectRequest request = new JsonObjectRequest(
                        Request.Method.POST,
                        getString(R.string.url) + "/update",
                        object,
                        response -> {
                            Toast.makeText(this, "Updated customer successfully.", Toast.LENGTH_SHORT).show();
                        },
                        error -> {
                            Toast.makeText(this, "Error updating customer.", Toast.LENGTH_SHORT).show();
                        });

                VolleyQueueSingleton.getInstance(this.getApplicationContext()).getQueue().add(request);


            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });

    }
}