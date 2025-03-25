package com.thed4nm4n.customerlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class CommentsListAdapter extends BaseAdapter {

    private final Customer customer;
    private final Context context;

    public CommentsListAdapter(Context context, Customer customer) {
        this.context = context;
        this.customer = customer;
    }

    @Override
    public int getCount() { return customer.getComments().size(); }

    @Override
    public Object getItem(int position) { return customer.getComments().get(position); }

    @Override
    public long getItemId(int position) { return 0; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_view_comments, parent, false);

        TextView commentText = view.findViewById(R.id.comment_text);
        commentText.setText(customer.getComments().get(position));

        Button saveCommentButton = view.findViewById(R.id.comment_save_button);
        saveCommentButton.setOnClickListener((v) -> {
            customer.updateComment(position, commentText.getText().toString());
            this.notifyDataSetChanged();
        });

        Button deleteButton = view.findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(v -> {
            customer.getComments().remove(position);
            this.notifyDataSetChanged();
        });

        return view;
    }
}
