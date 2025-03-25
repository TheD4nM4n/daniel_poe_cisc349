package com.thed4nm4n.customerlist;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class CustomerAddSheet extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_add_customer, container, false);

        RequestQueue queue = Volley.newRequestQueue(view.getContext());
        queue.start();

        EditText nameText = view.findViewById(R.id.name_text);
        EditText addressText = view.findViewById(R.id.address_text);
        EditText phoneText = view.findViewById(R.id.phone_text);

        Button addButton = view.findViewById(R.id.add_button);

        addButton.setOnClickListener(v -> {
            Log.d("TESTING", "ONLICK ACTIVATED");
            try {
                JSONObject json = new JSONObject()
                        .put("name", nameText.getText())
                        .put("address", addressText.getText())
                        .put("phone", phoneText.getText());

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                        getString(R.string.url) + "/add",
                        json,
                        response -> {
                            Toast.makeText(view.getContext(), "Added customer.", Toast.LENGTH_SHORT).show();
                            requireActivity().recreate();
                            dismiss();
                        }, error -> {
                    Toast.makeText(view.getContext(), "Error adding customer.", Toast.LENGTH_SHORT).show();
                    dismiss();
                }
                );

                queue.add(request);

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });

        return view;
    }

    public static final String TAG = "ModalBottomSheet";

}
