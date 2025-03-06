package com.thed4nm4n.walmartlist;

import org.json.JSONException;
import org.json.JSONObject;

public class Store {
    private String name;
    private String phone;
    private String address;
    private String city;

    public Store(JSONObject object) throws JSONException {
        this.name = object.getString("name");
        this.phone = object.getString("phone_number_1");
        this.address = object.getString("street_address");
        this.city = object.getString("city");
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getPhone() {
        return phone;
    }
}
