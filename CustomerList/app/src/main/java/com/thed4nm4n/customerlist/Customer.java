package com.thed4nm4n.customerlist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable {
    protected String _id;
    protected String name;
    protected String address;
    protected String phone;
    protected List<String> comments;

    public Customer(JSONObject object) throws JSONException {
        if (object.has("_id")) {
            this.setId(object.getString("_id"));
        }

        this.setName(object.getString("name"));
        this.setAddress(object.getString("address"));
        this.setPhone(object.getString("phone"));
        if (object.has("comments")) {
            JSONArray cmts = object.getJSONArray("comments");
            if (cmts != null) {
                comments = new ArrayList<String>();
                for (int i = 0; i < cmts.length(); i++) {
                    comments.add(cmts.get(i).toString());
                }
            }
        } else {
            comments = new ArrayList<String>();
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getComments() {
        return comments;
    }

    public void addComment() {
        comments.add("");
    }

    public void updateComment(int index, String comment) {
        comments.set(index, comment);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getId() {
        return _id;
    }
}
