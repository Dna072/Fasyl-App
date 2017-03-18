package com.dnastudios.fasylgh.utils;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    private final Map<String, String> map;

    public LoginRequest(String accountNumber, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, "http://192.168.43.12:8000/sign-in", listener, errorListener);
        this.map = new HashMap<>();

        map.put("account", accountNumber);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
