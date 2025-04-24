package com.thed4nm4n.toudou;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class QueueSingleton {
    private static QueueSingleton instance;
    private final RequestQueue queue;

    private QueueSingleton(Context context) {
        queue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static QueueSingleton getInstance(Context context) {
        if (instance == null) {
            instance = new QueueSingleton(context);
        }

        return instance;
    }

    public RequestQueue getRequestQueue() {
        return queue;
    }
}