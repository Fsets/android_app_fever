package com.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;

import com.android.volley.VolleyError;
import com.example.nft_ticket_andrey.R;
import com.restapi.RestAPIWebServices;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


/**
 * Created by Cristian Mármol cristian.marmol@occamcomunicacion.com on 29/03/2022.
 */
public class dialogFragmentLogin extends Dialog implements View.OnClickListener {
    private Context mContext;
    private Bundle args;
    private Button btnIniciarSesLogin,btnCancelarLogin;
    private ImageButton btnClose;
    private TextView btnCrearCuenta,btnPassOlvLogin;
    private EditText ed_email, ed_passwrd;
    private boolean error = false;
    private String email = "", password = "";
    private int id_user;


    public static String tokenUser = "";

    dialogFragmentLogin.OnMyDialogResult mDialogResult;

    public dialogFragmentLogin(Bundle bundle, @NonNull Context context, int myLoginDialog) {
        super(context);
        mContext = context;
        args = bundle;
    }

    public void loadElements(){
        ed_email = findViewById(R.id.ed_email_login);
        ed_passwrd = findViewById(R.id.ed_Password_login);

        btnIniciarSesLogin = findViewById(R.id.bt_iniciar_sesion_login);
        btnCancelarLogin = findViewById(R.id.bt_cancelar_login);
        btnCrearCuenta = findViewById(R.id.btnCrearCuenta);
        btnPassOlvLogin = findViewById(R.id.btnPasswOlvidada);
        btnClose = findViewById(R.id.btnClosePassOlv);

        btnPassOlvLogin.setOnClickListener(this);
        btnCrearCuenta.setOnClickListener(this);
        btnCancelarLogin.setOnClickListener(this);
        btnIniciarSesLogin.setOnClickListener(this);
        btnClose.setOnClickListener(this);
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_login); //visualiza el dialogo que queremos sacar
        loadElements();
    }
    public void setDialogResult(dialogFragmentLogin.OnMyDialogResult dialogResult) {
        mDialogResult = dialogResult;
    }

    public interface OnMyDialogResult {
        void fin(boolean fin);
    }

    private void obtainValues() {
        error = false;
        email = ed_email.getText().toString();
        password = ed_passwrd.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            error = true;
        }
    }

    private void login(){
        obtainValues();
        if(error){
            Toast.makeText(getContext(), "Rellena todos los campos", Toast.LENGTH_SHORT).show();
        }else{
            //somedomain.com es nuestra ip local del ordeandor
            //php artisan serve --host=somedomain.com --port=8001   arrancar esto para q funcione en la ip local
            String url ="http://192.168.0.41:8001/api/loguear";

            //para guardar las claves y valor
            HashMap<String, String> params = new HashMap<>();
            params.put("email", email);
            params.put("password", password);

            final RestAPIWebServices restAPIWebServices = new RestAPIWebServices(mContext, params, url);

            restAPIWebServices.postResponseApi(new RestAPIWebServices.VolleyCallback() {
                @Override
                public void onSuccess(String result) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(result);
                        int code = jsonObject.getInt("code");
                        if (code == 1000){

                            /*//Shared preferences
                            JSONObject userObject = jsonObject.getJSONObject("usuario"); //guardamos el usuario, asi se llama mi tabla
                            id_user = userObject.getInt("id");
                            email = userObject.getString("email");

                            SharedPreferences preferences = mContext.getSharedPreferences("preferences", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putInt("id_user", id_user);
                            editor.putString("email", email);
                            editor.commit();
                            editor.apply();*/

                            //iniciado sesion -> hacer cosas
                            Toast.makeText(getContext(), "Sesion Iniciada correctamente", Toast.LENGTH_SHORT).show();
                            dismiss();

                        }else if(code == 1001){
                            Toast.makeText(getContext(), "Error al escribir el correo o la contraseña", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(), "Error nose de que", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "Ha habido un error al hacer login", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_iniciar_sesion_login:
                //hacer login
                login();
                break;
            case R.id.bt_cancelar_login:
                dismiss(); //sale del dialog
                break;
            case R.id.btnCrearCuenta:
                dismiss();
                showdialogRegistro();//lanza el dialogo de registro
                break;
            case R.id.btnPasswOlvidada:
                dismiss();
                showdialogForgotPass();//lanza el dialogo de forgotPasw
                break;
            case R.id.btnClosePassOlv:
                dismiss();
                break;
        }
    }

    public void showdialogForgotPass(){
        Bundle args = new Bundle(); //PASAR DATOS DE UN ACTIVITY A OTRO
        final dialogPassOlvidada dialogForgot = new dialogPassOlvidada(args, mContext, R.layout.dialog_forgot_passw);
        dialogForgot.show();
        dialogForgot.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogForgot.setDialogResult(new dialogPassOlvidada.OnMyDialogResult() {
            @Override
            public void fin(boolean fin) {

            }
        });
    }

    public void showdialogRegistro(){
        Bundle args = new Bundle(); //PASAR DATOS DE UN ACTIVITY A OTRO
        final dialogRegistro dialogReg = new dialogRegistro(args, mContext, R.layout.dialog_registro);
        dialogReg.show();
        dialogReg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogReg.setDialogResult(new dialogRegistro.OnMyDialogResult() {
            @Override
            public void fin(boolean fin) {

            }
        });
    }
}
