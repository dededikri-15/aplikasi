package com.example.aplikasipegawai;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class tampilSemuaPgw extends AppCompatActivity implements ListView.OnItemClickListener{

    private ListView listView;

    private JSONObject JSON_STRING;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_semua_pgw);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        progressDialog = new ProgressDialog(tampilSemuaPgw.this);
        getPegawai();
//        showEmployee();
    }


    private void showEmployee(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = JSON_STRING;
            JSONArray result = jsonObject.getJSONArray(DbContract.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(DbContract.TAG_ID);
                String nama = jo.getString(DbContract.TAG_NAMA);
                String posisi = jo.getString(DbContract.TAG_POSISI);
                String gaji = jo.getString(DbContract.TAG_GAJI);

                HashMap<String,String> employees = new HashMap<>();
                employees.put(DbContract.TAG_ID,id);
                employees.put(DbContract.TAG_NAMA,nama);
                employees.put(DbContract.TAG_POSISI, posisi);
                employees.put(DbContract.TAG_GAJI, gaji);
                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                tampilSemuaPgw.this, list, R.layout.list_item,
                new String[]{DbContract.TAG_ID,DbContract.TAG_NAMA},
                new int[]{R.id.id, R.id.name});

        listView.setAdapter(adapter);
    }

//    private void getJSON(){
//        class GetJSON extends AsyncTask<Void,Void,String>{
//
//            ProgressDialog loading;
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                loading = ProgressDialog.show(tampilSemuaPgw.this,"Mengambil Data","Mohon Tunggu...",false,false);
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                loading.dismiss();
//                JSON_STRING = s;
//                showEmployee();
//            }
//
//            @Override
//            protected String doInBackground(Void... params) {
//                RequestHandler rh = new RequestHandler();
//                String s = rh.sendGetRequest(DbContract.URL_GET_ALL);
//                return s;
//            }
//        }
//        GetJSON gj = new GetJSON();
//        gj.execute();
//    }

    public void getPegawai(){
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, DbContract.URL_GET_ALL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSON_STRING = jsonObject;
                            showEmployee();
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleyConnection.getInstance(tampilSemuaPgw.this).addToRequestQueue(stringRequest);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.cancel();

            }
        }, 2000);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(tampilSemuaPgw.this, tampilPegawai.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String empId = map.get(DbContract.TAG_ID).toString();
        String empName = map.get(DbContract.TAG_NAMA).toString();
        String empPosisi = map.get(DbContract.TAG_POSISI).toString();
        String empgaji = map.get(DbContract.TAG_GAJI).toString();
        intent.putExtra(DbContract.EMP_ID,empId);
        intent.putExtra(DbContract.TAG_NAMA, empName);
        intent.putExtra(DbContract.TAG_POSISI, empPosisi);
        intent.putExtra(DbContract.TAG_GAJI, empgaji);
        startActivity(intent);
    }
}