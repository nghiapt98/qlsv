package com.example.assignment;


import static android.text.TextUtils.isEmpty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment.insert.SeverRespone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Add extends AppCompatActivity {

    EditText hoten,ngaythang,diachi;
    Button btnthem;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        hoten = findViewById(R.id.hoten);
        ngaythang = findViewById(R.id.ngaythang);
        diachi = findViewById(R.id.diachi);
        btnthem = findViewById(R.id.btnthem);
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (hoten.getText().toString().isEmpty() && ngaythang.getText().toString().isEmpty() && diachi.getText().toString().isEmpty() ){
                   Toast.makeText(Add.this,"Vui lòng nhập đầy đủ !",Toast.LENGTH_LONG).show();
                }
               else{
                   insert();
               }
            }
        });
    }
    public void insert(){
        Sinhvien sv = new Sinhvien();
        sv.setHoten(hoten.getText().toString());
        sv.setNgaythang(Integer.parseInt(ngaythang.getText().toString()));
        sv.setDiachi(diachi.getText().toString());
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://phamnghia98.000webhostapp.com/connect/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        com.example.assignment.insert.interfaceSV interfaceSV = retrofit.create(com.example.assignment.insert.interfaceSV.class);
        Call<SeverRespone> call = interfaceSV.insert(sv.getHoten(),sv.getNgaythang(),sv.getDiachi());
        call.enqueue(new Callback<SeverRespone>() {
            @Override
            public void onResponse(Call<SeverRespone> call, Response<SeverRespone> response) {
                SeverRespone sv = response.body();
                hoten.setText("");
                ngaythang.setText("");
                diachi.setText("");
                Toast.makeText(Add.this,""+sv.getMessage(),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<SeverRespone> call, Throwable t) {
                Toast.makeText(Add.this,""+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}