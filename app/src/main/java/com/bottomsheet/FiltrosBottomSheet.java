package com.bottomsheet;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nft_ticket_andrey.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Cristian Mármol cristian.marmol@occamcomunicacion.com on 31/03/2022.
 */
public class FiltrosBottomSheet extends BottomSheetDialogFragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    //declarar variables globales
    Calendar C = Calendar.getInstance();

    ImageButton btnCloseFiltros;
    EditText edFechadesde, edFechaHasta;

    //para que funcione el spinner
    Spinner spinnerCategorias;
    ArrayAdapter<String> aaCategorias; //para añadir elementos al spinner
    String [] aCategorias = new String[] {"Selecciona una categoria", "Conciertos", "Festivales", "Musicales", "Deportes"};

    SeekBar seekBarFiltros;
    TextView txtCargarPrecio;
    Button bt_aplicarFiltros, bt_restablecer_filtros;
    private SeekBar seekBar;
    private TextView showPrice;

    //para ocultar el bottomsheet
    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if(newState == BottomSheetBehavior.STATE_HIDDEN){
                dismiss();
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };

    //para ver el bottomsheet
    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style){ //para ver el bottomsheet
        super.setupDialog(dialog, style);
        //get the content view
        View contentView =View.inflate(getContext(), R.layout.bottomsheet_filtros2, null);
        dialog.setContentView(contentView);

        getElements(contentView);
    }

    //
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.AppBottomSheetDialogTheme); //personalizamos el estilo

    }

    @SuppressLint("ResourceType")
    public void getElements(View contentView){ //recogemos los elementos q vamos a utilizar
        btnCloseFiltros = contentView.findViewById(R.id.btn_closeFiltros2);
        btnCloseFiltros.setOnClickListener(this);

        spinnerCategorias = contentView.findViewById(R.id.spinnerCategorias2);
        spinnerCategorias.setOnItemSelectedListener(this); //la clase hereda de AdapterView.OnItemSelectedListener
        aaCategorias= new ArrayAdapter<String>(getContext(), R.layout.spinner_item_custom, aCategorias); //hay q pasarle un layout q asigne el string al textview
        aaCategorias.setDropDownViewResource(R.layout.spinner_item_txt); //al pulsar el spinner sale la lista personalizada
        spinnerCategorias.setAdapter(aaCategorias);
        spinnerCategorias.setSelection(0); //selecciona el default


        edFechadesde = contentView.findViewById(R.id.ed_fechaDesde_filtros);
        edFechadesde.setOnClickListener(this);

        edFechaHasta = contentView.findViewById(R.id.ed_fechaHasta_filtros);
        edFechaHasta.setOnClickListener(this);

        seekBarFiltros = contentView.findViewById(R.id.seekBarFiltros2);

        txtCargarPrecio = contentView.findViewById(R.id.txtCargarPrecio2);

        bt_aplicarFiltros = contentView.findViewById(R.id.bt_aplicarFiltros2);
        bt_aplicarFiltros.setOnClickListener(this);
        bt_restablecer_filtros = contentView.findViewById(R.id.bt_restablecer_filtros2);
        bt_restablecer_filtros.setOnClickListener(this);

        seekbar();
    }

    private void reestablecer(){
        spinnerCategorias.setSelection(0); //selecciona el default
        seekBarFiltros.setProgress(0);
        edFechaHasta.setText(String.valueOf("dd/mm/aaaa"));
        edFechadesde.setText(String.valueOf("dd/mm/aaaa"));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_closeFiltros2:
                dismiss();
                break;

            case R.id.bt_aplicarFiltros2:
                Toast.makeText(getContext(),"Aplicando Filtros..", Toast.LENGTH_SHORT).show();
                dismiss();
                break;

            case R.id.bt_restablecer_filtros2:
                reestablecer();
                Toast.makeText(getContext(),"Reestableciendo Filtros..", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ed_fechaDesde_filtros:
                new DatePickerDialog(getContext(), dateD, C.get(Calendar.YEAR), C.get(Calendar.MONTH), C.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.ed_fechaHasta_filtros:
                new DatePickerDialog(getContext(),dateH, C.get(Calendar.YEAR), C.get(Calendar.MONTH), C.get(Calendar.DAY_OF_MONTH)).show();
                break;
        }
    }
    DatePickerDialog.OnDateSetListener dateD = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            C.set(Calendar.YEAR, year);
            C.set(Calendar.MONTH, month);
            C.set(Calendar.DAY_OF_MONTH, day);
            actualizarInputD();
        }
    };
    DatePickerDialog.OnDateSetListener dateH = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            C.set(Calendar.YEAR, year);
            C.set(Calendar.MONTH, month);
            C.set(Calendar.DAY_OF_MONTH, day);
            actualizarInputH();
        }
    };

    private void actualizarInputH(){
        String formatoFecha = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(formatoFecha, Locale.US);

        edFechaHasta.setText(sdf.format(C.getTime()));
    }

    private void actualizarInputD(){
        String formatoFecha = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(formatoFecha, Locale.US);

        edFechadesde.setText(sdf.format(C.getTime()));
    }

    public void seekbar(){
        //valor inicial
        seekBarFiltros.setProgress(0);
        //valor Final
        seekBarFiltros.setMax(100);
        seekBarFiltros.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //hace un llamado a la perilla cuando se arrastra
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                txtCargarPrecio.setText(String.valueOf(i) + " %");
            }
            //hace un llamado  cuando se toca la perilla
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            //hace un llamado  cuando se detiene la perilla
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String valueSelected = aaCategorias.getItem(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
