package com.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.activities.MainActivity;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nft_ticket_andrey.R;
import com.example.nft_ticket_andrey.ui.home.HomeFragment;
import com.google.gson.JsonObject;
import com.restapi.RestAPIWebServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Cristian Mármol cristian.marmol@occamcomunicacion.com on 31/03/2022.
 */
public class dialogRegistro extends Dialog implements View.OnClickListener{
    private Context mContext;
    private Bundle args;
    private Button btnIniciarSesRegistro,btnCancelarRegistro;
    private ImageButton btnClose;
    private TextView btnIniciaReg, btnPrivacyPolicy;
    private CheckBox cboxPolicy;
    private String url, email = "", password = "", confirmPassword = "", name = "", apellidos = "";
    EditText edEmail, edPassword, edConfirmPassword, edNombre, edApellidos;
    public static final int MY_DEFAULT_TIMEOUT = 15000;

    boolean error = false, error2 = false, error3 = false, error4 = false;

    dialogRegistro.OnMyDialogResult mDialogResultR;

    public dialogRegistro(Bundle bundle,@NonNull Context context,int myRegDialog) {
        super(context);
        mContext = context;
        args = bundle;
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_registro); //visualiza el dialogo que queremos sacar
        loadElements();
    }
//
    //
    public void loadElements(){
        edEmail = findViewById(R.id.ed_email_reg);
        edConfirmPassword = findViewById((R.id.ed_rep_password_reg));
        edPassword = findViewById(R.id.ed_pasword_reg);
        edNombre = findViewById(R.id.ed_nombre_reg);
        edApellidos = findViewById(R.id.ed_apellido_reg);

        btnIniciarSesRegistro = findViewById(R.id.bt_iniciar_sesion_reg);
        btnCancelarRegistro = findViewById(R.id.bt_cancelar_reg);
        btnIniciaReg = findViewById(R.id.txt_inicia_reg);
        btnClose = findViewById(R.id.btnCloseRegistro);
        cboxPolicy = (CheckBox)findViewById(R.id.cboxPolicy);
        cboxPolicy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    btnIniciarSesRegistro.setEnabled(true);
                }else{
                    btnIniciarSesRegistro.setEnabled(false);
                }

            }
        });

        btnPrivacyPolicy = (TextView) findViewById(R.id.btn_terminos_reg);
        url= "https://bitstartups.es/politica-de-privacidad/";
        btnPrivacyPolicy.setOnClickListener(this);

        btnIniciarSesRegistro.setOnClickListener(this);
        btnCancelarRegistro.setOnClickListener(this);
        btnIniciaReg.setOnClickListener(this);
        btnClose.setOnClickListener(this);
    }

    public void setDialogResult(dialogRegistro.OnMyDialogResult dialogResult) {
        mDialogResultR = dialogResult;
    }

    public interface OnMyDialogResult {
        void fin(boolean fin);
    }

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public static boolean isValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!¡%*¿?&#+-{}^-_%&()ºª.,;<>]).{6,})";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }

    private void obtainValues(){
        error = false;
        error2 = false;
        error3 = false;
        error4 = false;
        email = edEmail.getText().toString();
        password = edPassword.getText().toString();
        confirmPassword = edConfirmPassword.getText().toString();
        name = edNombre.getText().toString();
        apellidos = edApellidos.getText().toString();

        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
            error = true;
        }else if (!password.equalsIgnoreCase(confirmPassword)){
            error2 = true;
        }else if(!isValidPassword(password)){
            error3 = true;
        }else if(!validarEmail(email)){
            error4 = true;
        }
    }

    private void register(){
        obtainValues();
        if (error) {
                /*Bundle params = new Bundle();
                params.putString("error", "error_empty");
                mFirebaseAnalytics.logEvent("signup_error", params);*/
            Toast.makeText(mContext, "rellena todos los campos", Toast.LENGTH_SHORT).show();
        } else if (error2) {
                /*Bundle params = new Bundle();
                params.putString("error", "error_equal_password");
                mFirebaseAnalytics.logEvent("signup_error", params);*/
            Toast.makeText(mContext, "las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        } else if (error3) {
                /*Bundle params = new Bundle();
                params.putString("error", "error_fail_password");
                mFirebaseAnalytics.logEvent("signup_error", params);*/
            Toast.makeText(mContext, "La contraseña debe al menos tener una mayúscula, una minúscula, un número, un caracter especial y mayor de 6 caractéres", Toast.LENGTH_SHORT).show();
        }else if (error4) {
            Toast.makeText(mContext, "Emails no coinciden", Toast.LENGTH_SHORT).show();
        }else {
            //addDataToDatabase(email, apellidos, name, password); //enviamos los datos que queremos en este caso los del dialog
            //php artisan serve --host=somedomain.com --port=8001   arrancar esto para q funcione en la ip local
            String url ="http://192.168.0.41:8001/api/nuevoUser";
            //String url ="http://127.0.0.1:8000/nuevoUser";

            HashMap<String, String> params = new HashMap<String, String>();
            params.put("email", email);
            params.put("name", name);
            params.put("surname", apellidos);
            params.put("password", password);

            RestAPIWebServices restAPIWebServices = new RestAPIWebServices(dialogRegistro.this.mContext, params, url);

            restAPIWebServices.postResponseApi(new RestAPIWebServices.VolleyCallback() {
                @Override
                public void onSuccess(String result) {
                    JSONObject jsonObject = null;
                    try{
                        jsonObject = new JSONObject(result);
                        int code = jsonObject.getInt("code");
                        if (code == 1000) {
                            Toast.makeText(mContext, "success", Toast.LENGTH_SHORT).show();
                            dismiss();
                        }else if(code == 1001){
                            Toast.makeText(mContext,"email ya registrado", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mContext,"error General del json", Toast.LENGTH_SHORT).show();
                        }
                    }catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(mContext, "error en el servidor1", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    if (error == null || error.networkResponse == null) {
                        Toast.makeText(mContext,"error", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (error.networkResponse.statusCode == 500) {
                        Toast.makeText(mContext,"error en el servidor2", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mContext, "Code: " + String.valueOf(error.networkResponse.statusCode), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
/*
    private void addDataToDatabase(String email, String apellidos, String name, String contraseña) {

        // url to post our data
        String url ="http://192.168.0.41:8001/nuevoUser";
        //String url ="http://127.0.0.1:8000/nuevoUser";
        //String url ="http://192.168.0.41:8000/nuevoUser";
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(mContext);

        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // on below line we are displaying a success toast message.
                    Toast.makeText(mContext, jsonObject.getString("success"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // and setting data to edit text as empty
                edEmail.setText("");
                edApellidos.setText("");
                edNombre.setText("");
                edPassword.setText("");
                edConfirmPassword.setText("");
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(mContext, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                // below line we are creating a map for storing
                // our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                // on below line we are passing our
                // key and value pair to our parameters.
                params.put("email", email);
                params.put("apellidos", apellidos);
                params.put("name", name);
                params.put("contraseña", contraseña);

                // at last we are returning our params.
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy( //para darle un tiempo
                MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // below line is to make
        // a json object request.
        queue.add(request);
    }*/

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_iniciar_sesion_reg:
                //lanzar el intent de inicio
                if(cboxPolicy.isChecked()) {
                    register();
                }else{
                    Toast.makeText(getContext(), "Tiene que aceptar las politicas de privacidad..", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_cancelar_reg:
                dismiss(); //sale del dialog
                break;
            case R.id.txt_inicia_reg:
                dismiss();
                showModalLoginDialog(); //lanza el dialogo de login
                break;
            case R.id.btnCloseRegistro:
                dismiss();
                break;
            case R.id.btn_terminos_reg:
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                getContext().startActivity(intent);
                break;
        }
    }

    public void showModalLoginDialog(){
        Bundle args = new Bundle(); //PASAR DATOS DE UN ACTIVITY A OTRO

        final dialogFragmentLogin dialogLogin = new dialogFragmentLogin(args,mContext, R.layout.dialog_login);
        dialogLogin.show();
        dialogLogin.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogLogin.setDialogResult(new dialogFragmentLogin.OnMyDialogResult() {
            @Override
            public void fin(boolean fin) {
            }

        });
    }
}
