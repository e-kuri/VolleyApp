package com.example.admin.volleyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private  final static String WS_URL = "http://jsonplaceholder.typicode.com/posts";
    private TextView tv, pTitle, pBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = ((TextView) findViewById(R.id.mTV));
        pTitle = ((TextView) findViewById(R.id.title));
        pBody = ((TextView) findViewById(R.id.pBody));

    }


    public void onGet(View view) {
        StringRequest request = new StringRequest(WS_URL,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String s) {
                        tv.setText(s);
                    }
                },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(MainActivity.this, volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

    public void onPost(View view) {
        StringRequest request = new StringRequest(Request.Method.POST, WS_URL,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String s) {
                        tv.setText(s);
                    }
                },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(MainActivity.this, volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("userId","1");
                params.put("title",pTitle.getText().toString().trim());
                params.put("body", pBody.getText().toString().trim());
                return params;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}
