package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText edtname;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtname = findViewById(R.id.edtlog);
        btn = findViewById(R.id.btndangnhap);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getname = edtname.getText().toString();
                if (getname.isEmpty()){
                    Toast.makeText(Login.this,"Vui lòng nhập đầy đủ ! ", Toast.LENGTH_LONG).show();
                }else{
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("ten",getname);
                    intent.putExtra("dulieu",bundle);
                    startActivity(intent);
                }



            }
        });

    }
}