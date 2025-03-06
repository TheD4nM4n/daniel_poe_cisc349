package com.thed4nm4n.walmartlist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StoreFragment extends Fragment {

    protected Store store;

    public StoreFragment(Store store) {
        this.store = store;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, container, false);

        TextView nameTextView = view.findViewById(R.id.store_name);
        TextView addressTextView = view.findViewById(R.id.store_address);
        TextView cityTextView = view.findViewById(R.id.store_city);
        TextView phoneTextView = view.findViewById(R.id.store_phone);

        nameTextView.setText(store.getName());
        addressTextView.setText(store.getAddress());
        cityTextView.setText(store.getCity());
        phoneTextView.setText(store.getPhone());

        return view;
    }
}