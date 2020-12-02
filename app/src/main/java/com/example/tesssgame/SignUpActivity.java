package com.example.tesssgame;


//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.android.volley.AuthFailureError;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    private static final String RG ="https://uniqueandrocode.000webhostapp.com/hiren/androidtutorial/signup.php";
    EditText etname,etage,etemail,etpass;
    Button signupbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        etname=(EditText)findViewById(R.id.editname);
        etage=(EditText)findViewById(R.id.editAge);
        etemail=(EditText)findViewById(R.id.editEmail);
        etpass=(EditText)findViewById(R.id.editPass);
        signupbtn=(Button)findViewById(R.id.btnsignup);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=etname.getText().toString().trim();
                String age=etage.getText().toString().trim();
                String email=etemail.getText().toString().trim();
                String pass=etpass.getText().toString().trim();

                createSignup(name,age,email,pass);

            }
        });

    }

    private void createSignup(final String name,final String age, final String email,final String pass) {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, RG, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams()  {
                Map<String,String>parms=new HashMap<String, String>();
                parms.put("name",name);
                parms.put("age",age);
                parms.put("email",email);
                parms.put("password",pass);


                return parms;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}