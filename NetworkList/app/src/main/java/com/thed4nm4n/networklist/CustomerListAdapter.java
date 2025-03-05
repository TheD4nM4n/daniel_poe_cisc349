package com.thed4nm4n.networklist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomerListAdapter extends BaseAdapter {
    private Context context;
    private List<Customer> customers;
    public CustomerListAdapter(Context context, List<Customer> customers) {
        this.context = context;
        this.customers = customers;
    }
    @Override
    public int getCount() {
        return null == customers ? 0 : customers.size();
    }

    @Override
    public Object getItem(int position) {
        return null == customers ? null : customers.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent);

        TextView nameTextView = view.findViewById(R.id.nameTextView);
        TextView addressTextView = view.findViewById(R.id.addressTextView);
        TextView phoneTextView = view.findViewById(R.id.phoneTextView);

        nameTextView.setText(customers.get(position).getName());
        addressTextView.setText(customers.get(position).getAddress());
        phoneTextView.setText(customers.get(position).getPhone());

        return view;
    }
}
