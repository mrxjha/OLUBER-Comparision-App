package com.example.venkatnarayana.testing3;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button b;
    EditText text1;
    EditText text2;
    private RequestQueue requestQueue;
    String slat;
    String slon;
    String elat;
    String elon;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=(ProgressBar)findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.GONE);


        b=(Button)findViewById(R.id.button);
        text1=(EditText)findViewById(R.id.editText);
        text2=(EditText)findViewById(R.id.editText2);


        requestQueue= Volley.newRequestQueue(this);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    String u="https://maps.googleapis.com/maps/api/geocode/json?address=";

                    String l="&key=xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";//api key from google cloud developer console
                final String name=text1.getText().toString();
                final String name1=text2.getText().toString();
                progressBar.setVisibility(View.VISIBLE);
                    String r=name;
                String r1=name1;
                    String result = r.replace(" ","+");
                String result1=r1.replace(" ","+");
                    String url=u+result+l;
                String url1=u+result1+l;


                    JsonObjectRequest request=new JsonObjectRequest(url, new Response.Listener<JSONObject>() {


                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                JSONObject ar=response.getJSONArray("results").getJSONObject(0).getJSONObject("geometry");
                                JSONObject ar1=ar.getJSONObject("location");
                                slat=ar1.getString("lat");
                                slon=ar1.getString("lng");


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }

                    })/*{


                            @Override
                            public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<String, String>();
                            headers.put("CUSTOM_HEADER", "Yahoo");
                            headers.put("ANOTHER_CUSTOM_HEADER", "Google");
                            return headers;

                    }}*/;

                JsonObjectRequest request1=new JsonObjectRequest(url1, new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONObject ar=response.getJSONArray("results").getJSONObject(0).getJSONObject("geometry");
                            JSONObject ar1=ar.getJSONObject("location");
                            elat=ar1.getString("lat");
                            elon=ar1.getString("lng");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                    requestQueue.add(request);
                requestQueue.add(request1);



                /*new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            Thread.sleep(3000);
                            progressBar.setVisibility(View.GONE);
                            Intent myIntent = new Intent(MainActivity.this,
                                    test3.class);
                            myIntent.putExtra("slat",slat);
                            myIntent.putExtra("slon",slon);
                            myIntent.putExtra("elat",elat);
                            myIntent.putExtra("elon",elon);


                            startActivity(myIntent);

                        }
                        catch (InterruptedException e)
                        {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }).start();*/
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //Execute code here
                        progressBar.setVisibility(View.GONE);
                        Intent myIntent = new Intent(MainActivity.this,
                                test3.class);
                        myIntent.putExtra("slat",slat);
                        myIntent.putExtra("slon",slon);
                        myIntent.putExtra("elat",elat);
                        myIntent.putExtra("elon",elon);


                        startActivity(myIntent);

                    }
                }, 5000);

                }



        });






    }
}
