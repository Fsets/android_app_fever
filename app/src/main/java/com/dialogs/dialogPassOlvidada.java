package com.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.nft_ticket_andrey.R;

/**
 * Created by Cristian Mármol cristian.marmol@occamcomunicacion.com on 31/03/2022.
 */
public class dialogPassOlvidada extends Dialog implements View.OnClickListener{
    private Context mContext;
    private Bundle args;
    private Button btnIniciarSesPassOlv,btnCancelarPassOlv;
    private ImageButton btnClosePassOlv;

    dialogPassOlvidada.OnMyDialogResult mDialogResult;

    public dialogPassOlvidada(Bundle bundle,@NonNull Context context,int myForgotDialog) {
        super(context);
        mContext = context;
        args = bundle;
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_forgot_passw); //visualiza el dialogo que queremos sacar
        loadElements();
    }

    public void loadElements(){
        btnIniciarSesPassOlv = findViewById(R.id.bt_iniciar_sesion_forgotPassw);
        btnCancelarPassOlv = findViewById(R.id.bt_cancelar2_forgotPassw);
        btnClosePassOlv = findViewById(R.id.btnClosePassOlv);

        btnIniciarSesPassOlv.setOnClickListener(this);
        btnCancelarPassOlv.setOnClickListener(this);
        btnClosePassOlv.setOnClickListener(this);
    }

    public void setDialogResult(dialogPassOlvidada.OnMyDialogResult dialogResult) {
        mDialogResult = dialogResult;
    }

    public interface OnMyDialogResult {
        void fin(boolean fin);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_iniciar_sesion_forgotPassw:
                //lanzar el intent de inicio
                break;
            case R.id.bt_cancelar2_forgotPassw:
                dismiss(); //sale del dialog
                break;
            case R.id.btnClosePassOlv:
                dismiss();
                break;
        }
    }
}
