package com.example.nft_ticket_andrey.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adapters.listAdapter;

import com.example.nft_ticket_andrey.R;
import com.models.Eventos;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements View.OnClickListener {
    private SearchViewModel notificationsViewModel;
    private View root;
    LinearLayout edBuscarEvento;
    Button btCancelar;
    SearchView searchView;

    RecyclerView listaEventos;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Eventos> listEventos2;
    listAdapter listA;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        root = inflater.inflate(R.layout.fragment_search, container, false);

        getElements(root);
        return root;
    }

    public void getElements(View root){

        edBuscarEvento = root.findViewById(R.id.ed_buscar_evento);

        btCancelar = root.findViewById(R.id.bt_cancelar_search);
        btCancelar.setOnClickListener(this);

        listaEventos = root.findViewById(R.id.listEventos);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);

        listEventos2 = new ArrayList<>();

        listEventos2.add(new Eventos("Viaje al fin de la noche", R.drawable.imgconcierto, 29.99, "domingo, abr 20, 2022", "13:00pm - 20:00pm", "descripcion 1"));
        listEventos2.add(new Eventos("Decamerón", R.drawable.imgteatro, 39.99, "miercoles, jun 20, 2022", "10:00am - 17:00pm", "descripcion 5"));
        listEventos2.add(new Eventos("Cumbres Borrascosas", R.drawable.img5, 29.99, "domingo, abr 20, 2022", "13:00pm - 20:00pm", "descripcion 1"));
        listEventos2.add(new Eventos("Gente independiente", R.drawable.imgmusica, 29.99, "lunes, may 13, 2022", "17:00pm - 22:00pm", "descripcion 3"));
        listEventos2.add(new Eventos("Casa de muñecas", R.drawable.imgorquestra, 39.99, "martes, may 25, 2022", "11:00am - 15:00pm", "descripcion 4"));
        listEventos2.add(new Eventos("Vida y opiniones del caballero Tristram Shandy", R.drawable.img3, 29.99, "miercoles, jun 20, 2022", "10:00am - 17:00pm", "descripcion 5"));
        listEventos2.add(new Eventos("Guerra y paz", R.drawable.img4, 29.99, "domingo, abr 20, 2022", "13:00pm - 20:00pm", "descripcion 1"));
        listEventos2.add(new Eventos("Memorias de Adriano", R.drawable.img2, 39.99, "miercoles, jun 20, 2022", "10:00am - 17:00pm", "descripcion 5"));

        listA= new listAdapter(listEventos2, getContext());

        listaEventos.setLayoutManager(layoutManager);
        listaEventos.setAdapter(listA);

        searchView = root.findViewById(R.id.Sv_buscar_evento);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(s);
                return false;
            }
        });
    }

    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<Eventos> filteredlist = new ArrayList<>();
        // running a for loop to compare elements.
        for (Eventos item : listEventos2) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getTitulo().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(getContext(), "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            listA.filterList(filteredlist);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onClick(View view) {

    }
}