package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class List extends AppCompatActivity {
    String url = "https://phamnghia98.000webhostapp.com/connect/select.php";
    ListView lv;
    ArrayList<Sinhvien> list;
    sinhvienAdapter adt ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        lv = findViewById(R.id.listsv);
        list = new ArrayList<>();
        adt = new sinhvienAdapter(this,list,R.layout.item_sinhvien);
        lv.setAdapter(adt);
        getData(url);
    }


    public void getData(String url){
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0 ; i<response.length();i++){
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        Sinhvien sv = new Sinhvien();
                        sv.setId(obj.getInt("id"));
                        sv.setHoten(obj.getString("hoten"));
                        sv.setNgaythang(obj.getInt("ngaythang"));
                        sv.setDiachi(obj.getString("diachi"));
                        list.add(sv);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adt.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(List.this, "Lỗi " + error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        queue.add(jsonArrayRequest);

    }
}