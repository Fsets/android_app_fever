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
 * Created by Cristian Mármol cristian.marmol@occamcomunicacion.com on 31/03/2022.
 */
public class dialogRegistro extends Dialog implements View.OnClickListener{
    private Context mContext;
    private Bundle args;
    private Button btnIniciarSesRegistro,btnCancelarRegistro;
    private ImageButton btnClose;
    private TextView btnIniciaReg;

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

    public void loadElements(){
        btnIniciarSesRegistro = findViewById(R.id.bt_iniciar_sesion_reg);
        btnCancelarRegistro = findViewById(R.id.bt_cancelar_reg);
        btnIniciaReg = findViewById(R.id.txt_inicia_reg);
        btnClose = findViewById(R.id.btnCloseRegistro);

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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_iniciar_sesion_reg:
                //lanzar el intent de inicio
                Toast.makeText(getContext(),"iniciando sesion..", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_cancelar_reg:
                Toast.makeText(getContext(),"cerrando sesion..", Toast.LENGTH_SHORT).show();
                dismiss(); //sale del dialog
                break;
            case R.id.txt_inicia_reg:
                Toast.makeText(getContext(),"creando cuenta..", Toast.LENGTH_SHORT).show();
                dismiss();
                showModalLoginDialog(); //lanza el dialogo de login
                break;
            case R.id.btnCloseRegistro:
                dismiss();
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
