package com.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nft_ticket_andrey.R;
import com.google.android.gms.maps.SupportMapFragment;
import com.models.Cancion;
import com.models.Eventos;

import org.w3c.dom.Text;

/**
 * Created by Cristian Mármol cristian.marmol@occamcomunicacion.com on 04/05/2022.
 */
public class CancionesActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton imbtClose;
    LinearLayout layoutFondoCancion;
    TextView txtvTitulo, txtNombreArtista, txtFecha, txtDescripcion, btLeerMas;
    Button bt_comprarCancion;
    double precio = 0, precioI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_canciones);

        getElements();
    }
    public void getElements(){
        Bundle data = getIntent().getExtras();
        Cancion song = (Cancion) data.getParcelable("canciones"); //from cancionesAdapter / cancionesgridadapter

        imbtClose = findViewById(R.id.imgBtCloseCancion);
        imbtClose.setOnClickListener(this);

        layoutFondoCancion = findViewById(R.id.layoutFondoCancion);
        layoutFondoCancion.setBackgroundResource(song.getImgCancion()); //cambia el fondo del evento

        txtvTitulo = findViewById(R.id.txtTituloCancion);
        txtvTitulo.setText(song.getTitulo());
        txtNombreArtista = findViewById(R.id.txtNombreArtista);
        txtNombreArtista.setText(song.getNombreArtista());
        txtFecha = findViewById(R.id.txtFechaCancion);
        txtFecha.setText(song.getFechaCancion());
        txtDescripcion = findViewById(R.id.txtDescripcionCancion);
        txtDescripcion.setText(song.getDescripcion());

        btLeerMas = findViewById(R.id.btLeerMasCancion);
        btLeerMas.setOnClickListener(this);

        bt_comprarCancion = findViewById(R.id.bt_comprarCancion);
        bt_comprarCancion.setOnClickListener(this);
        precioI = song.getPrecio();
        precio = precioI;
        bt_comprarCancion.setText("Comprar Ahora - " + precio + "€");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBtCloseCancion:
                onBackPressed(); //al pulsar atras vuelve al inicio
                break;
            case R.id.btLeerMasCancion:
                if(txtDescripcion.getMaxLines()<=2) {
                    btLeerMas.setText("Leer Menos");
                    txtDescripcion.setMaxLines(10);
                }else if(txtDescripcion.getMaxLines() > 2){
                    btLeerMas.setText("Leer Mas");
                    txtDescripcion.setMaxLines(2);
                }
                break;
        }
    }
}
