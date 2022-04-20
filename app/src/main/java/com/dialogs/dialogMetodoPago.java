package com.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;

import com.example.nft_ticket_andrey.R;

/**
 * Created by Cristian Mármol cristian.marmol@occamcomunicacion.com on 20/04/2022.
 */
public class dialogMetodoPago extends Dialog implements View.OnClickListener{
    private Context mContext;
    private Bundle args;

    dialogMetodoPago.OnMyDialogResult mDialogResult;

    public dialogMetodoPago(Bundle bundle, @NonNull Context context, int myLoginDialog) {
        super(context);
        mContext = context;
        args = bundle;
    }
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_metodo_pago); //visualiza el dialogo que queremos sacar
        loadElements();
    }

    public void setDialogResult(dialogMetodoPago.OnMyDialogResult dialogResult) {
        mDialogResult = dialogResult;
    }

    public interface OnMyDialogResult {
        void fin(boolean fin);
    }
    public void loadElements(){

    }
    @Override
    public void onClick(View view) {

    }
}
