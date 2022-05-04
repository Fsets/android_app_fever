package com.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adapters.CancionesGridAdapter;
import com.adapters.EventGridAdapter;
import com.bottomsheet.FiltrosBottomSheet;
import com.example.nft_ticket_andrey.R;
import com.models.Cancion;
import com.models.Eventos;

import java.util.ArrayList;

/**
 * Created by Cristian Mármol cristian.marmol@occamcomunicacion.com on 04/05/2022.
 */
public class CancionesRecientesActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{
    ImageButton imbt_close, imbt_filtros;

    //para que funcione el spinner
    Spinner spner_categorias;
    ArrayAdapter<String> aaCategorias; //para añadir elementos al spinner
    String [] aCategorias = new String[] {"Selecciona una categoria", "Conciertos", "Festivales", "Musicales", "Deportes"};

    RecyclerView recyclerEvRecientes;
    private ArrayList<Cancion> listCancionesRecietnes;
    private RecyclerView.LayoutManager layoutManager;
    public static final String PRODUCT_KEY = "PRODUCT_KEY2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canciones_recientes);

        getElements();
    }

    public void getElements(){
        imbt_close = findViewById(R.id.imbt_atras_recentSongs);
        imbt_close.setOnClickListener(this);
        imbt_filtros = findViewById(R.id.imbt_filtros_recentSongs);
        imbt_filtros.setOnClickListener(this);

        spner_categorias = findViewById(R.id.spinnerCategorias_SongsRecientes);
        spner_categorias.setOnItemSelectedListener(this); //la clase hereda de AdapterView.OnItemSelectedListener
        aaCategorias= new ArrayAdapter<String>(this, R.layout.spinner_item_custom, aCategorias); //hay q pasarle un layout q asigne el string al textview
        aaCategorias.setDropDownViewResource(R.layout.spinner_item_txt); //al pulsar el spinner sale la lista personalizada
        spner_categorias.setAdapter(aaCategorias);
        spner_categorias.setSelection(0); //selecciona el default

        recyclerEvRecientes = findViewById(R.id.recyclerSongsRecientes);
        recyclerEvRecientes.setHasFixedSize(true); //solo se una cuando el rec es matchparent ambos w/h

        getEvents();
    }

    public void getEvents(){
        Bundle data = getIntent().getExtras();
        listCancionesRecietnes = new ArrayList<>(); //iniciar el array vacio
        listCancionesRecietnes = data.getParcelableArrayList("arraylistCanciones"); //recibe la lista de objetos de MusicaFragment
        CancionesGridAdapter songA= new CancionesGridAdapter(listCancionesRecietnes, this);
        recyclerEvRecientes.setLayoutManager(new GridLayoutManager(this, 2)); //grid utiliza el contexto y el nr de columnas que queremos ver
        recyclerEvRecientes.setAdapter(songA);
    }

    @Override
    public void onClick(View view) {
        Bundle args = new Bundle();
        switch (view.getId()) {
            case R.id.imbt_filtros_recentSongs:
                FiltrosBottomSheet bottomSheet = new FiltrosBottomSheet();
                FragmentManager fragmentManager = getSupportFragmentManager();
                bottomSheet.setArguments(args);
                bottomSheet.show(fragmentManager, "exampleBottomsheet");
                break;
            case R.id.imbt_atras_recentSongs:
                //llevar al intent home
                onBackPressed();;
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
