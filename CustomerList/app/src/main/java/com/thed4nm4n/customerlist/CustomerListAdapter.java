package com.thed4nm4n.customerlist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomerListAdapter extends BaseAdapter {

    private List<Customer> customers;
    private Context context;

    public CustomerListAdapter(Context context, List<Customer> customers) {
        this.context = context;
        this.customers = customers;
    }

    @Override
    public int getCount() {
        return customers.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_view, parent, false);
        TextView tv = view.findViewById(R.id.customer_name);
        tv.setText(customers.get(position).getName());
        tv = view.findViewById(R.id.customer_address);
        tv.setText(customers.get(position).getAddress());
        tv = view.findViewById(R.id.customer_phone);
        tv.setText(customers.get(position).getPhone());
        return view;
    }
}
