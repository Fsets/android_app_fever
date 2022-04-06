package com.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;

import com.example.nft_ticket_andrey.R;


/**
 * Created by Cristian Mármol cristian.marmol@occamcomunicacion.com on 29/03/2022.
 */
public class dialogFragmentLogin extends Dialog implements View.OnClickListener {
    private Context mContext;
    private Bundle args;
    private Button btnIniciarSesLogin,btnCancelarLogin;
    private ImageButton btnClose;
    private TextView btnCrearCuenta,btnPassOlvLogin;


    dialogFragmentLogin.OnMyDialogResult mDialogResult;

    public dialogFragmentLogin(Bundle bundle, @NonNull Context context, int myLoginDialog) {
        super(context);
        mContext = context;
        args = bundle;
    }

    public void loadElements(){
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


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_iniciar_sesion_login:
                //lanzar el intent de inicio
                Toast.makeText(getContext(),"iniciando sesion..", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_cancelar_login:
                Toast.makeText(getContext(),"cerrando sesion..", Toast.LENGTH_SHORT).show();
                dismiss(); //sale del dialog
                break;
            case R.id.btnCrearCuenta:
                Toast.makeText(getContext(),"creando cuenta..", Toast.LENGTH_SHORT).show();
                dismiss();
                showdialogRegistro();//lanza el dialogo de registro
                break;
            case R.id.btnPasswOlvidada:
                Toast.makeText(getContext(),"Contraseña olvidada..", Toast.LENGTH_SHORT).show();
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
