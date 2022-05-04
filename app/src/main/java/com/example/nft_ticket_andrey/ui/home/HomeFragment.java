package com.example.nft_ticket_andrey.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.activities.EventosRecientesActivity;
import com.activities.QRActivity;
import com.adapters.EventAdapter;
import com.bottomsheet.FiltrosBottomSheet;
import com.example.nft_ticket_andrey.R;
import com.models.Eventos;

import java.io.Serializable;
import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private HomeViewModel homeViewModel;
    private ImageButton btn_filtros, btnEvento;
    private Button btn_todas, btn_cat1, btn_cat2;
    private Context mContext;
    private View root;
    private TextView btn_eventos_recientes;
    private RecyclerView recyclerEvents, recylcerEventsCercanos;
    private ArrayList<Eventos> listEventos, listEventosCercanos;
    private RecyclerView.LayoutManager layoutManager, layoutManager2;
    public static final String PRODUCT_KEY = "PRODUCT_KEY";

    private HomeFragment listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        root =inflater.inflate(R.layout.fragment_home, container, false);
        mContext= getContext();

        getElements(root);

        return root;
    }

    public void getElements(View root){
        btn_filtros = root.findViewById(R.id.btn_filtros_home);
        btn_filtros.setOnClickListener(this);

        //btnEvento = root.findViewById(R.id.imEvento);
        //btnEvento.setOnClickListener(this);
        btn_eventos_recientes = root.findViewById(R.id.btn_eventos_recientes);
        btn_eventos_recientes.setOnClickListener(this);

        btn_todas = root.findViewById(R.id.btn_todas);
        btn_todas.setOnClickListener(this);
        btn_cat1= root.findViewById(R.id.btn_cat1);
        btn_cat1.setOnClickListener(this);
        btn_cat2 = root.findViewById(R.id.btm_cat2);
        btn_cat2.setOnClickListener(this);

        recyclerEvents= root.findViewById(R.id.recyclerEventos);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);

        recylcerEventsCercanos= root.findViewById(R.id.recyclerEventosCercanos);
        layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);

        getEvents();
    }

    public void getEvents(){
        Bundle args = new Bundle();

        listEventos = new ArrayList<>();
        listEventos.add(new Eventos("Titulo del Evento 1 y otrs titulos aparteee de la aplicacion del movil", R.drawable.imgconcierto, 29.99, "domingo, abr 20, 2022", "13:00pm - 20:00pm", "descripcion 1"));
        listEventos.add(new Eventos("Titulo del Evento2", R.drawable.imgconcierto2, 39.99, "sabado, abr 19, 2022", "18:00pm - 00:00pm", getString(R.string.descripcion)));
        listEventos.add(new Eventos("Titulo del Evento3", R.drawable.imgmusica, 29.99, "lunes, may 13, 2022", "17:00pm - 22:00pm", "descripcion 3"));
        listEventos.add(new Eventos("Titulo del Evento4", R.drawable.imgorquestra, 39.99, "martes, may 25, 2022", "11:00am - 15:00pm", "descripcion 4"));
        listEventos.add(new Eventos("Titulo del Evento5", R.drawable.imgteatro, 29.99, "miercoles, jun 20, 2022", "10:00am - 17:00pm", "descripcion 5"));
        EventAdapter eventA= new EventAdapter(listEventos, getContext());

        recyclerEvents.setLayoutManager(layoutManager);
        recyclerEvents.setAdapter(eventA);

        listEventosCercanos = new ArrayList<>();
        listEventosCercanos.add(new Eventos("Evento Cercano 1", R.drawable.imgconcierto2, 29.99, "domingo, jun 10, 2022", "19:00pm - 00:30pm", "descripcion 6"));
        listEventosCercanos.add(new Eventos("Evento Cercano 2", R.drawable.imgmusica, 49.99, "jueves, jul 24, 2022", "17:30pm - 23:30pm", "descripcion 7"));
        listEventosCercanos.add(new Eventos("Evento Cercano 3", R.drawable.imgteatro, 29.99, "viernes, abr 27, 2022", "14:00pm - 20:30pm", "descripcion 8"));
        EventAdapter eventA2= new EventAdapter(listEventosCercanos, getContext());
        recylcerEventsCercanos.setLayoutManager(layoutManager2);
        recylcerEventsCercanos.setAdapter(eventA2);
    }

    @Override
    public void onClick(View v) {
        Bundle args = new Bundle();
        switch (v.getId()) {
            case R.id.btn_filtros_home:
                FiltrosBottomSheet bottomSheet = new FiltrosBottomSheet();
                FragmentManager fragmentManager = getParentFragmentManager();
                bottomSheet.setArguments(args);
                bottomSheet.show(fragmentManager, "exampleBottomsheet");
                break;
            case R.id.btn_todas:
                Toast.makeText(getContext(),"todas categorias..", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(v.getContext(), QRActivity.class);
                v.getContext().startActivity(i);
                break;
            case R.id.btn_cat1:
                Toast.makeText(getContext(),"categoria1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_eventos_recientes:
                Intent i2 = new Intent(v.getContext(), EventosRecientesActivity.class);
                i2.putParcelableArrayListExtra(PRODUCT_KEY, listEventos); //envia la lista parseada a eventosrecientesactivity
                v.getContext().startActivity(i2);
        }
    }
}