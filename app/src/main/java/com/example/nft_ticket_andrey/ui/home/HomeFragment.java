package com.example.nft_ticket_andrey.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adapters.EventAdapter;
import com.bottomsheet.FiltrosBottomSheet;
import com.example.nft_ticket_andrey.R;
import com.models.Eventos;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener{
    private HomeViewModel homeViewModel;
    private ImageButton btn_filtros, btnEvento;
    private Button btn_todas, btn_cat1, btn_cat2;
    private Context mContext;
    private View root;
    private RecyclerView recyclerEvents;
    private ArrayList<Eventos> listEventos;
    private RecyclerView.LayoutManager layoutManager;

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

        btn_todas = root.findViewById(R.id.btn_todas);
        btn_todas.setOnClickListener(this);
        btn_cat1= root.findViewById(R.id.btn_cat1);
        btn_cat1.setOnClickListener(this);
        btn_cat2 = root.findViewById(R.id.btm_cat2);
        btn_cat2.setOnClickListener(this);

        recyclerEvents= root.findViewById(R.id.recyclerEventos);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);

        getEvents();
    }

    public void getEvents(){
        listEventos = new ArrayList<>();
        listEventos.add(new Eventos("Titulo del Evento1", R.drawable.event, 29.99));
        listEventos.add(new Eventos("Titulo del Evento2", R.drawable.profile_menu, 39.99));
        listEventos.add(new Eventos("Titulo del Evento3", R.drawable.music, 29.99));
        listEventos.add(new Eventos("Titulo del Evento4", R.drawable.event_active, 39.99));
        listEventos.add(new Eventos("Titulo del Evento5", R.drawable.profile_menu_active, 29.99));
        EventAdapter eventA= new EventAdapter(listEventos, getContext());
        recyclerEvents.setLayoutManager(layoutManager);
        recyclerEvents.setAdapter(eventA);
    }
    @Override
    public void onClick(View v) {
        Bundle args = new Bundle();
        switch (v.getId()) {
            case R.id.btn_filtros_home:
                FiltrosBottomSheet bottomSheet = new FiltrosBottomSheet();
                FragmentManager fragmentManager = getFragmentManager();
                bottomSheet.setArguments(args);
                bottomSheet.show(fragmentManager, "exampleBottomsheet");
                break;
            case R.id.btn_todas:
                Toast.makeText(getContext(),"todas categorias..", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_cat1:
                Toast.makeText(getContext(),"categoria1", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}