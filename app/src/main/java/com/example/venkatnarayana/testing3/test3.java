package com.example.venkatnarayana.testing3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

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

public class test3 extends AppCompatActivity {

    TextView uber;
    TextView ola;
    Button b1;
    Button b2;
    ProgressBar progressBar;
    private RequestQueue requestQueue;
    String ubername1;
    String uberestimate1;
    String ubername2;
    String uberestimate2;
    String ubername3;
    String uberestimate3;
    String olaname1;
    String olaminamount1;
    String olamaxamount1;
    String olaname2;
    String olaminamount2;
    String olamaxamount2;
    String olaname3;
    String olaminamount3;
    String olamaxamount3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);
        Intent i = getIntent();
        final String slat = i.getStringExtra("slat");
        final String slon = i.getExtras().getString("slon");
        final String elat = i.getStringExtra("elat");
        final String elon = i.getExtras().getString("elon");







        uber=(TextView)findViewById(R.id.textView5);
        ola=(TextView)findViewById(R.id.textView6);
        b1=(Button)findViewById(R.id.button2);
        b2=(Button)findViewById(R.id.button3);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        requestQueue= Volley.newRequestQueue(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u="https://api.uber.com/v1.2/estimates/price?start_latitude=";
                String r="&start_longitude=";
                String l="&end_latitude=";
                String k="&end_longitude=";
                String url1=u+slat+r+slon+l+elat+k+elon;
                progressBar.setVisibility(View.VISIBLE);

                JsonObjectRequest request=new JsonObjectRequest(url1, new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            ubername1=response.getJSONArray("prices").getJSONObject(0).getString("localized_display_name");
                            uberestimate1=response.getJSONArray("prices").getJSONObject(0).getString("estimate");

                            ubername2=response.getJSONArray("prices").getJSONObject(1).getString("localized_display_name");
                            uberestimate2=response.getJSONArray("prices").getJSONObject(1).getString("estimate");

                            ubername3=response.getJSONArray("prices").getJSONObject(2).getString("localized_display_name");
                            uberestimate3=response.getJSONArray("prices").getJSONObject(2).getString("estimate");




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                }){


                            @Override
                            public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<String, String>();
                            headers.put("Authorization", "Token xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");//Token obtained from uber developer api
                            headers.put("Accept-Language", "en_US");
                                headers.put("Content-Type","application/json");
                            return headers;

                    }};
                requestQueue.add(request);

               /* new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            Thread.sleep(3000);


                            //+"  "+uberestimate1+"\n"+ubername2+"  "+uberestimate2+"\n"+ubername3+" "+uberestimate3);

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
                        uber.setText(ubername1+"  "+uberestimate1+"\n"+ubername2+"  "+uberestimate2+"\n"+ubername3+" "+uberestimate3);

                    }
                }, 5000);


            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u="https://sandbox-t.olacabs.com/v1/products?pickup_lat=";
                String r="&pickup_lng=";
                String l="&drop_lat=";
                String k="&drop_lng=";
                String url2=u+slat+r+slon+l+elat+k+elon;
                progressBar.setVisibility(View.VISIBLE);
                JsonObjectRequest request1=new JsonObjectRequest(url2, new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            olaname1=response.getJSONArray("ride_estimate").getJSONObject(0).getString("category");
                            olaminamount1=response.getJSONArray("ride_estimate").getJSONObject(0).getString("amount_min");
                            olamaxamount1=response.getJSONArray("ride_estimate").getJSONObject(0).getString("amount_max");

                            olaname2=response.getJSONArray("ride_estimate").getJSONObject(1).getString("category");
                            olaminamount2=response.getJSONArray("ride_estimate").getJSONObject(1).getString("amount_min");
                            olamaxamount2=response.getJSONArray("ride_estimate").getJSONObject(1).getString("amount_max");

                            olaname3=response.getJSONArray("ride_estimate").getJSONObject(2).getString("category");
                            olaminamount3=response.getJSONArray("ride_estimate").getJSONObject(2).getString("amount_min");
                            olamaxamount3=response.getJSONArray("ride_estimate").getJSONObject(2).getString("amount_max");






                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                }){


                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();

                        headers.put("X-APP-TOKEN","xxxxxxxxxxxxxxxxxxxxxxxxxxxxx");//Token obtained from OLA developer api
                        return headers;

                    }};
                requestQueue.add(request1);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //Execute code here
                        progressBar.setVisibility(View.GONE);
                        ola.setText(olaname1+" "+olaminamount1+"-"+olamaxamount1+"\n"+olaname2+" "+olaminamount2+"-"+olamaxamount2+"\n"+olaname3+" "+olaminamount3+"-"+olamaxamount3+"\n");


                    }
                }, 5000);




            }
        });







    }
}
