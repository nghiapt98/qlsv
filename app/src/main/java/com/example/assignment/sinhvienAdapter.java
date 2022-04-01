package com.example.assignment;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class sinhvienAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Sinhvien> list;
    private int layout;

    public sinhvienAdapter() {
    }

    public sinhvienAdapter(Context context, ArrayList<Sinhvien> list, int layout) {
        this.context = context;
        this.list = list;
        this.layout = layout;
    }

    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        Sinhvien sv = list.get(position);
        return sv;
    }

    @Override
    public long getItemId(int position) {
        Sinhvien sv = list.get(position);
        return sv.getId();
    }
    private class ViewHoler{
        TextView txtHoten,txtNgaythang,txtDiachi;
        ImageView imgDelete,imgEdit;
    }


    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHoler holder;
        if (convertView == null){
            holder = new ViewHoler();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder.txtHoten= convertView.findViewById(R.id.txthoten);
            holder.txtNgaythang= convertView.findViewById(R.id.txtngaythang);
            holder.txtDiachi= convertView.findViewById(R.id.txtdiachi);
            holder.imgDelete = convertView.findViewById(R.id.imgdelete);
            holder.imgEdit = convertView.findViewById(R.id.imgedit);
            convertView.setTag(holder);

        }else{
            holder = (ViewHoler) convertView.getTag();
        }
        Sinhvien sv = list.get(i);
        final Sinhvien obj = list.get(i);
        holder.txtHoten.setText(sv.getHoten());
        holder.txtNgaythang.setText(""+sv.getNgaythang());
        holder.txtDiachi.setText(sv.getDiachi());
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(context,androidx.appcompat.R.style.Base_ThemeOverlay_AppCompat_Dialog_Alert);
                dialog.setContentView(R.layout.edit_sinhvien);
                dialog.show();
                String getten = holder.txtHoten.getText().toString();
                String getngay = holder.txtNgaythang.getText().toString();
                String getdiachi = holder.txtDiachi.getText().toString();

                EditText ten = dialog.findViewById(R.id.edttensv1);
                EditText ngaythang = dialog.findViewById(R.id.edtngaysinh1);
                EditText diachi = dialog.findViewById(R.id.edtdiachi);

                ten.setText(getten);
                ngaythang.setText(getngay);
                diachi.setText(getdiachi);

                Button btnluu = dialog.findViewById(R.id.btnluu);
                btnluu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        update();
                    }
                    public void update(){
                        RequestQueue requestQueue = Volley.newRequestQueue(context);
                        String url ="https://phamnghia98.000webhostapp.com/connect/update.php";
                        StringRequest str = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(context,response.substring(0),Toast.LENGTH_LONG).show();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        })
                        {


                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String,String> myData = new HashMap<>();
                                myData.put("id",String.valueOf(obj.getId()));
                                myData.put("hoten",ten.getText().toString());
                                myData.put("ngaythang",ngaythang.getText().toString());
                                myData.put("diachi",diachi.getText().toString());
                                list.set(i,obj);
                                return myData;
                            }
                        };

                        notifyDataSetChanged();
                        requestQueue.add(str);
                        dialog.cancel();
                    }


                });


            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
            public void delete(){
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                String url = "https://phamnghia98.000webhostapp.com/connect/delete.php";
                StringRequest str = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context,response.substring(0),Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                })
                {
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> myData = new HashMap<>();
                        myData.put("id",String.valueOf(obj.getId()));
                        list.remove(i);
                        return myData;
                    }
                };
                notifyDataSetChanged();
                requestQueue.add(str);
            }
        });

        return convertView;
    }
}
