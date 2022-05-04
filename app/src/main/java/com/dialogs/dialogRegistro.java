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
import com.example.nft_ticket_andrey.R;
import com.example.nft_ticket_andrey.ui.home.HomeFragment;

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
    private String url, email = "", password = "", confirmPassword = "";
    EditText edEmail, edPassword, edConfirmPassword;

    boolean error = false, error2 = false, error3 = false;

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
        edEmail = findViewById(R.id.ed_email_reg);
        edConfirmPassword = findViewById((R.id.ed_rep_password_reg));
        edPassword = findViewById(R.id.ed_pasword_reg);

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
        email = edEmail.getText().toString();
        password = edPassword.getText().toString();
        confirmPassword = edConfirmPassword.getText().toString();

        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
            error = true;

        }else if (!password.equalsIgnoreCase(confirmPassword)){
            error2 = true;

        }else if(!isValidPassword(password)){
            error3 = true;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_iniciar_sesion_reg:
                //lanzar el intent de inicio
                if(cboxPolicy.isChecked()) {
                    Toast.makeText(getContext(), "iniciando sesion..", Toast.LENGTH_SHORT).show();
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
