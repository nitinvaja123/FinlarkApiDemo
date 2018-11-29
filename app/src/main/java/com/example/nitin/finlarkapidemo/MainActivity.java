package com.example.nitin.finlarkapidemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.nitin.finlarkapidemo.Utils.Common;
import com.example.nitin.finlarkapidemo.Utils.Constant;
import com.example.nitin.finlarkapidemo.Utils.utils;
import com.example.nitin.finlarkapidemo.adapter.ItemAdapter;
import com.example.nitin.finlarkapidemo.model.MyPojo;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressDialog pd;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;
        recyclerView = (RecyclerView) findViewById(R.id.main_recycler);
        if (Common.isInternetAvailable(context)) {
            new GetData().execute(Constant.GET_Data);
        } else {
            Common.InternetError(context);
        }
    }

    private class GetData extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Please Wait....");
            pd.setTitle("Loadding....");
            pd.setCancelable(true);
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection urlConnection = null;

            Map<String, String> stringMap = new HashMap<>();
            stringMap.put("city_id", String.valueOf(1));
            String requestBody = utils.buildPostParameters(stringMap);
            try {
                urlConnection = (HttpURLConnection) utils.makeRequest("POST", Constant.GET_Data, null, "application/x-www-form-urlencoded", requestBody);
                InputStream inputStream;
                if (urlConnection.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
                    inputStream = urlConnection.getInputStream();
                } else {
                    inputStream = urlConnection.getErrorStream();
                }
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp, response = "";
                while ((temp = bufferedReader.readLine()) != null) {
                    response += temp;
                }
                Log.e("RESPONCE", "" + response);
                return response;
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();

            try {
                Gson gson = new Gson();

                MyPojo mydata = gson.fromJson(s, MyPojo.class);
                if (mydata.getStatus().equals("200")) {
                    GridLayoutManager manager = new GridLayoutManager(MainActivity.this, 3);
                    manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                        @Override
                        public int getSpanSize(int position) {
                            switch (position) {
                                case 0:
                                    return 3;
                                case 1:
                                    return 1;
                                default:
                                    return 1;
                            }
                        }
                    });

                    recyclerView.setLayoutManager(manager);
                    ItemAdapter categoryAdapter = new ItemAdapter(context, mydata.getData().getCategory(), mydata.getData().getEvent());
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(categoryAdapter);

                } else {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

