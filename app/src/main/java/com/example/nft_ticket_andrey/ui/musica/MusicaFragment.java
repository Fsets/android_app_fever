package com.example.nft_ticket_andrey.ui.musica;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.activities.CancionesRecientesActivity;
import com.activities.EventosRecientesActivity;
import com.activities.QRActivity;
import com.adapters.CancionesAdapter;
import com.adapters.ColeccionesAdapter;
import com.adapters.EventAdapter;
import com.bottomsheet.FiltrosBottomSheet;
import com.example.nft_ticket_andrey.R;
import com.example.nft_ticket_andrey.ui.home.HomeViewModel;
import com.models.Cancion;
import com.models.Eventos;

import java.util.ArrayList;


public class MusicaFragment extends Fragment implements View.OnClickListener {
    private HomeViewModel homeViewModel;
    private View root;
    private Context mContext;

    private ImageButton btn_filtros;
    private TextView btn_canciones_recientes, btn_colecciones;
    private RecyclerView recyclerCanciones, recylcerColecciones;
    private ArrayList<Cancion> listCanciones, listColecciones;
    private RecyclerView.LayoutManager layoutManager, layoutManager2;
    public static final String PRODUCT_KEY = "PRODUCT_KEY2";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        root =inflater.inflate(R.layout.fragment_musica, container, false);
        mContext= getContext();

        getElements(root);

        return root;
    }

    public void getElements(View root){
        btn_filtros = root.findViewById(R.id.btn_filtros_musica);
        btn_filtros.setOnClickListener(this);

        btn_canciones_recientes = root.findViewById(R.id.btn_canciones_recientes);
        btn_canciones_recientes.setOnClickListener(this);
        btn_colecciones = root.findViewById(R.id.btn_colecciones_musica);
        btn_colecciones.setOnClickListener(this);

        recyclerCanciones = root.findViewById(R.id.recyclerCancionesRecientes);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);

        recylcerColecciones = root.findViewById(R.id.recyclerColeccionesMusica);
        layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);

        getEvents();
    }

    public void getEvents(){
        Bundle args = new Bundle(); //para enviar o  guardar las canciones
        listCanciones = new ArrayList<>();
        listCanciones.add(new Cancion(R.drawable.img1, "Titulo del Evento 1 y otrs titulos aparteee de la aplicacion del movil", 9.99, "2022", getString(R.string.descripcion), "Daddy Yankee"));
        listCanciones.add(new Cancion(R.drawable.img2, "Titulo del Evento 2 y otrs titulos aparteee de la aplicacion del movil", 4.99, "2023", getString(R.string.descripcion), "Ozuna"));
        listCanciones.add(new Cancion(R.drawable.img3, "Titulo del Evento 3 y otrs titulos aparteee de la aplicacion del movil", 6.99, "2020", getString(R.string.descripcion), "Kaze"));
        listCanciones.add(new Cancion(R.drawable.img4, "Titulo del Evento 4 y otrs titulos aparteee de la aplicacion del movil", 12.99, "2021", getString(R.string.descripcion), "Rauw Alejandro"));
        listCanciones.add(new Cancion(R.drawable.img5, "Titulo del Evento 5 y otrs titulos aparteee de la aplicacion del movil", 3.99, "2023", getString(R.string.descripcion), "Bad Bunny"));
        CancionesAdapter songA= new CancionesAdapter(listCanciones, getContext());

        recyclerCanciones.setLayoutManager(layoutManager);
        recyclerCanciones.setAdapter(songA);

        listColecciones = new ArrayList<>();
        listColecciones.add(new Cancion(R.drawable.img5, "Titulo del Evento 2 y otrs titulos aparteee de la aplicacion del movil", 4.99, "2023", getString(R.string.descripcion), "Ozuna"));
        listColecciones.add(new Cancion(R.drawable.img1, "Titulo del Evento 3 y otrs titulos aparteee de la aplicacion del movil", 6.99, "2020", getString(R.string.descripcion), "Kaze"));
        listColecciones.add(new Cancion(R.drawable.img4, "Titulo del Evento 4 y otrs titulos aparteee de la aplicacion del movil", 12.99, "2021", getString(R.string.descripcion), "Rauw Alejandro"));
        listColecciones.add(new Cancion(R.drawable.img2, "Titulo del Evento 5 y otrs titulos aparteee de la aplicacion del movil", 3.99, "2023", getString(R.string.descripcion), "Bad Bunny"));
        ColeccionesAdapter songA2= new ColeccionesAdapter(listColecciones, getContext());
        recylcerColecciones.setLayoutManager(layoutManager2);
        recylcerColecciones.setAdapter(songA2);

    }
    @Override
    public void onClick(View view) {
        Bundle args = new Bundle();
        switch (view.getId()) {
            case R.id.btn_filtros_musica:
                FiltrosBottomSheet bottomSheet = new FiltrosBottomSheet();
                FragmentManager fragmentManager = getParentFragmentManager();
                bottomSheet.setArguments(args);
                bottomSheet.show(fragmentManager, "exampleBottomsheet");
                break;
            case R.id.btn_canciones_recientes:
                Intent i2 = new Intent(view.getContext(), CancionesRecientesActivity.class);
                i2.putParcelableArrayListExtra("arraylistCanciones", listCanciones); //envia la lista parseada a CancionesrecientesActivity
                view.getContext().startActivity(i2);
                break;
        }
    }
    
}