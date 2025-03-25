package com.thed4nm4n.customerlist;

import android.content.Context;
import android.view.PixelCopy;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyQueueSingleton {

    private static VolleyQueueSingleton instance;

    private final RequestQueue queue;

    private VolleyQueueSingleton(Context context) {
        queue = Volley.newRequestQueue(context.getApplicationContext());
        queue.start();
    }

    public static VolleyQueueSingleton getInstance(Context context) {
        if (instance == null) {
            instance = new VolleyQueueSingleton(context);
        }

        return instance;
    }

    public RequestQueue getQueue() { return queue; }
}
