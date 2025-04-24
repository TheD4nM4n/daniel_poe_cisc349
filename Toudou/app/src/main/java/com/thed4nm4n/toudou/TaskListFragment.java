package com.thed4nm4n.toudou;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TaskListFragment extends Fragment {

    private final List<JSONObject> tasks = new ArrayList<>();
    private String url;
    private String type;

    public TaskListFragment(Context context, String type) {
        this.url = context.getString(R.string.url);
        this.type = type;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);

        JsonArrayRequest request = new JsonArrayRequest(
                url + "?type=" + type,
                response -> {
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            transaction.add(R.id.tasks_frag_container, new TaskFragment((JSONObject) response.get(i)));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    transaction.commit();
                },
                error -> Log.d("ERROR", error.toString())
        );
        QueueSingleton.getInstance(getContext()).getRequestQueue().add(request);

        return view;
    }
}