package com.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.fragment.app.FragmentManager;
import com.adapters.EventAdapter;
import com.adapters.EventGridAdapter;
import com.bottomsheet.FiltrosBottomSheet;
import com.example.nft_ticket_andrey.R;
import com.example.nft_ticket_andrey.ui.home.HomeFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.models.Eventos;

import java.util.ArrayList;

/**
 * Created by Cristian Mármol cristian.marmol@occamcomunicacion.com on 26/04/2022.
 */
public class EventosRecientesActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    ImageButton imbt_close, imbt_filtros;


    //para que funcione el spinner
    Spinner spner_categorias;
    ArrayAdapter<String> aaCategorias; //para añadir elementos al spinner
    String [] aCategorias = new String[] {"Selecciona una categoria", "Conciertos", "Festivales", "Musicales", "Deportes"};

    RecyclerView recyclerEvRecientes;
    private ArrayList<Eventos> listEventosRecietnes;
    private RecyclerView.LayoutManager layoutManager;
    public static final String PRODUCT_KEY = "PRODUCT_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos_recientes);

        getElements();
    }

    public void getElements(){
        imbt_close = findViewById(R.id.imbt_atras_recientes);
        imbt_close.setOnClickListener(this);

        imbt_filtros = findViewById(R.id.imbt_filtros_recientes);
        imbt_filtros.setOnClickListener(this);

        spner_categorias = findViewById(R.id.spinnerCategorias_recientes);
        spner_categorias.setOnItemSelectedListener(this); //la clase hereda de AdapterView.OnItemSelectedListener
        aaCategorias= new ArrayAdapter<String>(this, R.layout.spinner_item_custom, aCategorias); //hay q pasarle un layout q asigne el string al textview
        aaCategorias.setDropDownViewResource(R.layout.spinner_item_txt); //al pulsar el spinner sale la lista personalizada
        spner_categorias.setAdapter(aaCategorias);
        spner_categorias.setSelection(0); //selecciona el default

        recyclerEvRecientes = findViewById(R.id.recyclerEventosRecientes);
        recyclerEvRecientes.setHasFixedSize(true); //solo se una cuando el rec es matchparent ambos w/h

        getEvents();
    }

    public void getEvents(){
        Bundle data = getIntent().getExtras();
        listEventosRecietnes = new ArrayList<>(); //iniciar el array vacio
        listEventosRecietnes = data.getParcelableArrayList(PRODUCT_KEY); //recibe la lista de objetos de Homefragment
        EventGridAdapter eventA= new EventGridAdapter(listEventosRecietnes, this);
        recyclerEvRecientes.setLayoutManager(new GridLayoutManager(this, 2)); //grid utiliza el contexto y el nr de columnas que queremos ver
        recyclerEvRecientes.setAdapter(eventA);
    }

    @Override
    public void onClick(View view) {
        Bundle args = new Bundle();
        switch (view.getId()) {
            case R.id.imbt_filtros_recientes:
                FiltrosBottomSheet bottomSheet = new FiltrosBottomSheet();
                FragmentManager fragmentManager = getSupportFragmentManager();
                bottomSheet.setArguments(args);
                bottomSheet.show(fragmentManager, "exampleBottomsheet");
                break;
            case R.id.imbt_atras_recientes:
                //llevar al intent home
                onBackPressed();;
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String valueSelected = aaCategorias.getItem(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
