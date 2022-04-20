package com.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.example.nft_ticket_andrey.R;
import com.models.Eventos;

import java.text.DecimalFormat;

/**
 * Created by Cristian Mármol cristian.marmol@occamcomunicacion.com on 20/04/2022.
 */
public class dialogMetodoPago extends Dialog implements View.OnClickListener{
    private Context mContext;
    private Bundle args;
    ImageButton btClosepopup;
    LinearLayout btTarjetaCred, btCoinBase;
    Button btComprarEntrada, btCancelar;
    Double totalEntradas;

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
        totalEntradas =args.getDouble("totalEntradas"); //recoge datos del bundle de eventosActivit

        btClosepopup = findViewById(R.id.btClosePago);
        btClosepopup.setOnClickListener(this);

        btTarjetaCred = findViewById(R.id.bttarjetaCredito);
        btTarjetaCred.setOnClickListener(this);
        btCoinBase = findViewById(R.id.btCoinbaseCommerce);
        btCoinBase.setOnClickListener(this);

        btComprarEntrada = findViewById(R.id.btComprarEntrada);
        btComprarEntrada.setOnClickListener(this);

        DecimalFormat df2 = new DecimalFormat("#.##");
        btComprarEntrada.setText("Comprar - " + df2.format(totalEntradas) + "€");

        btCancelar = findViewById(R.id.btCancelar_Pago);
        btCancelar.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btClosePago:
                dismiss();
                break;
            case R.id.btCancelar_Pago:
                dismiss();
                break;
        }
    }
}
