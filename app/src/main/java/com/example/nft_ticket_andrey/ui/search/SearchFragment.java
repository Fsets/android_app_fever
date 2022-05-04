package com.example.nft_ticket_andrey.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;

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

        listEventos2.add(new Eventos("Titulo del Evento1", R.drawable.imgconcierto, 29.99, "domingo, abr 20, 2022", "13:00pm - 20:00pm", "descripcion 1"));
        listEventos2.add(new Eventos("Titulo del Evento5", R.drawable.imgteatro, 39.99, "miercoles, jun 20, 2022", "10:00am - 17:00pm", "descripcion 5"));
        listEventos2.add(new Eventos("Titulo del Evento1", R.drawable.imgconcierto, 29.99, "domingo, abr 20, 2022", "13:00pm - 20:00pm", "descripcion 1"));
        listEventos2.add(new Eventos("Titulo del Evento5", R.drawable.imgteatro, 39.99, "miercoles, jun 20, 2022", "10:00am - 17:00pm", "descripcion 5"));
        listEventos2.add(new Eventos("Titulo del Evento1", R.drawable.imgconcierto, 29.99, "domingo, abr 20, 2022", "13:00pm - 20:00pm", "descripcion 1"));
        listEventos2.add(new Eventos("Titulo del Evento5", R.drawable.imgteatro, 39.99, "miercoles, jun 20, 2022", "10:00am - 17:00pm", "descripcion 5"));
        listEventos2.add(new Eventos("Titulo del Evento1", R.drawable.imgconcierto, 29.99, "domingo, abr 20, 2022", "13:00pm - 20:00pm", "descripcion 1"));
        listEventos2.add(new Eventos("Titulo del Evento5", R.drawable.imgteatro, 39.99, "miercoles, jun 20, 2022", "10:00am - 17:00pm", "descripcion 5"));

        listA= new listAdapter(listEventos2, getContext());

        listaEventos.setLayoutManager(layoutManager);
        listaEventos.setAdapter(listA);

        searchView = root.findViewById(R.id.Sv_buscar_evento);

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onClick(View view) {

    }
}