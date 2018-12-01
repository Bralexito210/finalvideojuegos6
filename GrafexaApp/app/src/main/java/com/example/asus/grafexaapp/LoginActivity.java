package com.example.asus.grafexaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText txt_nick,txt_contraseña;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//ESTO ES PARA Q NO ESTE EL TITLE BAR
        setContentView(R.layout.activity_login);//CON ESTO DICES Q ACTIVITY ABRE
        txt_nick= (EditText)findViewById(R.id.txt_nick);
        txt_contraseña= (EditText)findViewById(R.id.txt_contraseña);
        btnLogin=(Button) findViewById(R.id.btnRegistrar);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"exito",Toast.LENGTH_SHORT).show();
                txt_nick.setEnabled(false);

               // Login();
            }
        });

        setContentView(R.layout.activity_login);
    }

   private void Login() {

        String url="http://192.168.1.4:81/BD_APP/wsJSONLogin3.php";
        RequestQueue requestQueue =Volley.newRequestQueue(this);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String rpta=response.trim();
                if(rpta.equals("a")){
                    Toast.makeText(getApplicationContext(),"exito",Toast.LENGTH_SHORT).show();

                }else{

                    Toast.makeText(getApplicationContext(),rpta+"fallo"+rpta,Toast.LENGTH_SHORT).show();


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"error: "+error,Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("username",txt_nick.getText().toString().trim());
                params.put("password",txt_contraseña.getText().toString().trim());


                return params;
            }

        };


        requestQueue.add(stringRequest);




    }
}
