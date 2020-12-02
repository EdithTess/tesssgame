package com.example.tesssgame;


import android.content.Intent;
//        import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//        import com.android.volley.AuthFailureError;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String LG = "https://uniqueandrocode.000webhostapp.com/hiren/androidtutorial/login.php";
    EditText editemail,editpass,etpass,etemail;
    Button btnlogin;
    TextView tv;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.createnewac);
        editemail=(EditText)findViewById(R.id.etemail);
        editpass=(EditText)findViewById(R.id.etpass);
        btnlogin=(Button)findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email=editemail.getText().toString().trim();
                final String pass=editpass.getText().toString().trim();

                StringRequest stringRequest=new StringRequest(Request.Method.POST, LG, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object=new JSONObject(response);
                            if (!object.getBoolean("sucsses")){
                                final String name=object.getString("name");
                                final String emaildata=object.getString("email");
                                Intent intent=new Intent(MainActivity.this,UserActivity.class);
                                intent.putExtra("username",name);
                                intent.putExtra("email",emaildata);
                                startActivity(intent);
                            }else {
                                Toast.makeText(getApplicationContext(),"User Login UnSucssesFull", Toast.LENGTH_LONG).show();
                            }
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
                    protected Map<String, String> getParams()  {
                        Map<String,String>parms=new HashMap<String, String>();
                        parms.put("email",email);
                        parms.put("password",pass);
                        return parms;
                    }
                };
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SignUpActivity.class));
            }
        });
    }
}