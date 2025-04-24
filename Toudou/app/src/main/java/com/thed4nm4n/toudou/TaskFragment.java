package com.thed4nm4n.toudou;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class TaskFragment extends Fragment {

    private final JSONObject task;
    private final String url;

    public TaskFragment(JSONObject task) {
        this.task = task;
        this.url = "http://10.2.99.214:5000/tasks";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_task, container, false);

        TextView taskTextView = view.findViewById(R.id.task_text);
        try {
            taskTextView.setText(task.getString("name"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        ImageButton deleteButton = view.findViewById(R.id.action_delete);
        deleteButton.setOnClickListener(v -> {
            try {
                QueueSingleton.getInstance(getContext()).getRequestQueue().add(
                        new JsonObjectRequest(
                                Request.Method.DELETE,
                                url + "?id=" + task.getString("_id"),
                                null,
                                response -> getParentFragmentManager().beginTransaction().remove(this).commit(),
                                error -> {}));
            } catch (JSONException e) {http://10.2.99.214:5000
                throw new RuntimeException(e);
            }
        });
        return view;
    }
}