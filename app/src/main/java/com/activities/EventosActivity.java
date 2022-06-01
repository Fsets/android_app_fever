package com.activities;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dialogs.dialogMetodoPago;
import com.example.nft_ticket_andrey.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.models.Eventos;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * Created by Cristian Mármol cristian.marmol@occamcomunicacion.com on 19/04/2022.
 */
public class EventosActivity extends AppCompatActivity implements View.OnClickListener, Serializable, AdapterView.OnItemSelectedListener, OnMapReadyCallback  {
    //declarar variables globales
    Calendar C = Calendar.getInstance();
    EditText edFechadesde, edFechaHasta;
    int contador = 1;
    double precio = 0, precioI;
    Button btComprarEvento;
    ImageButton imgClose, imBtMenosEntradas, imBtMasEntradas;
    TextView txtTituloEvento, txtFechaEvento, txtDuracionEvento, txtDescripcion, txtLeerMas, txtnrEntradas;
    LinearLayout layoutFondoEvento;

    GoogleMap mapa;

    //para que funcione el spinner
    Spinner spinnerEntradas;
    ArrayAdapter<String> aaEntradas; //para añadir elementos al spinner
    String [] aEntradas = new String[] {"Selecciona una categoria", "Entrada 1", "Entrada 2", "Entrada 3", "Entrada 4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_evento);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        getElements();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mapa = map;
        LatLng fuenla = new LatLng(40.289165697435664, -3.7974519454817934);
        mapa.addMarker(new MarkerOptions().position(fuenla).title("Marker")); //añade el marcador
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(fuenla, 15.0f)); //hace zoom y mustra la zona que queremos
    }

    public void getElements(){
        Bundle data = getIntent().getExtras();
        Eventos ev = (Eventos) data.getParcelable("evento");

        layoutFondoEvento = findViewById(R.id.layoutFondoEvento);
        layoutFondoEvento.setBackgroundResource(ev.getImgEvento()); //cambia el fondo del evento

        imgClose = findViewById(R.id.imgBtCloseEvento);
        imgClose.setOnClickListener(this);
        imBtMasEntradas = findViewById(R.id.imbtMasEntradas);
        imBtMasEntradas.setOnClickListener(this);
        imBtMenosEntradas = findViewById(R.id.imbtMenosEntradas);
        imBtMenosEntradas.setOnClickListener(this);

        txtTituloEvento = findViewById(R.id.txtTituloEvento);
        txtTituloEvento.setText(ev.getTitulo());
        txtFechaEvento = findViewById(R.id.txtFechaEvento);
        txtFechaEvento.setText(ev.getFechaEvento());
        txtDuracionEvento = findViewById(R.id.txtDuracionEvento);
        txtDuracionEvento.setText(ev.getDuracion());
        txtDescripcion = findViewById(R.id.txtDescripcionEvento);
        txtDescripcion.setText(ev.getDescripcion());
        txtLeerMas = findViewById(R.id.btLeerMasEvento);
        txtLeerMas.setOnClickListener(this);
        txtnrEntradas = findViewById(R.id.txtnrEntradas);

        edFechadesde = findViewById(R.id.ed_fechaDesde_evento);
        edFechadesde.setOnClickListener(this);

        edFechaHasta = findViewById(R.id.ed_fechaHasta_evento);
        edFechaHasta.setOnClickListener(this);

        btComprarEvento = findViewById(R.id.bt_comprarEvento);
        btComprarEvento.setOnClickListener(this);
        precioI = ev.getPrecio();
        precio = precioI;
        btComprarEvento.setText("Comprar Ahora - " + precio + "€");

        spinnerEntradas = findViewById(R.id.spinnerTipoEntradas);
        spinnerEntradas.setOnItemSelectedListener(this);
        aaEntradas= new ArrayAdapter<String>(this, R.layout.spinner_item_custom, aEntradas); //hay q pasarle un layout q asigne el string al textview
        aaEntradas.setDropDownViewResource(R.layout.spinner_item_txt); //al pulsar el spinner sale la lista personalizada
        spinnerEntradas.setAdapter(aaEntradas);
        spinnerEntradas.setSelection(0); //selecciona el default
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBtCloseEvento:
                onBackPressed(); //al pulsar atras vuelve al inicio
                break;
            case R.id.btLeerMasEvento:
                if(txtDescripcion.getMaxLines()<=2) {
                    txtLeerMas.setText("Leer Menos");
                    txtDescripcion.setMaxLines(10);
                }else if(txtDescripcion.getMaxLines() > 2){
                    txtLeerMas.setText("Leer Mas");
                    txtDescripcion.setMaxLines(2);
                }
                break;
            case R.id.ed_fechaDesde_evento: // recoge la fecha seleccionada
                new DatePickerDialog(this, dateD, C.get(Calendar.YEAR), C.get(Calendar.MONTH), C.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.ed_fechaHasta_evento:
                new DatePickerDialog(this,dateH, C.get(Calendar.YEAR), C.get(Calendar.MONTH), C.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.imbtMasEntradas:
                contador++;
                precio = contador * precioI;
                DecimalFormat df = new DecimalFormat("#.##");
                btComprarEvento.setText("Comprar Ahora - " + df.format(precio) + "€");
                txtnrEntradas.setText(contador + " Entradas");
                break;
            case R.id.imbtMenosEntradas:
                if(contador > 0) { //si el contador es > 0 entonces calcula el precio de las entradas
                    contador--;
                    precio = contador * precioI;
                    DecimalFormat df2 = new DecimalFormat("#.##");
                        btComprarEvento.setText("Comprar Ahora - " + df2.format(precio) + "€");
                    txtnrEntradas.setText(contador + " Entradas");
                }else if(contador == 0){
                    txtnrEntradas.setText("0 Entradas");
                    }
                break;
            case R.id.bt_comprarEvento:
                showModalDialog(); //lanza el dialogo de metodos de pago
                break;
        }
    }

    public void showModalDialog(){
        Bundle args = new Bundle(); //PASAR DATOS DE UN ACTIVITY A OTRO
        args.putDouble("totalEntradas", precio); //pasa el precio total para comprar
        final dialogMetodoPago dialog = new dialogMetodoPago(args, EventosActivity.this, R.layout.dialog_metodo_pago);
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setDialogResult(new dialogMetodoPago.OnMyDialogResult() {
            @Override
            public void fin(boolean fin) {
            }
        });
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
