package com.example.nft_ticket_andrey.ui.collections;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import com.adapters.CancionesGridAdapter;
import com.adapters.recyclerEntradasAdapter;
import com.adapters.recyclerGridNFTsAdapter;
import com.adapters.recyclerMusicaAdapter;
import com.example.nft_ticket_andrey.R;
import com.example.nft_ticket_andrey.ui.home.HomeViewModel;
import com.models.Cancion;
import com.models.Eventos;
import com.models.NFTs;

import java.util.ArrayList;


public class CollectionsFragment extends Fragment implements View.OnClickListener{
    private HomeViewModel homeViewModel;
    private View root;
    private Context mContext;

    private Button btEntradas, btMusica, btNFTs;
    private SearchView SvColeccion;

    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Eventos> listEventos;
    private ArrayList<Cancion> listCanciones;
    private ArrayList<NFTs> listNFTs;

    recyclerEntradasAdapter adapterEntradas;
    recyclerMusicaAdapter adapterMusica;
    recyclerGridNFTsAdapter adapterNFTs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        root =inflater.inflate(R.layout.fragment_collections, container, false);
        mContext= getContext();
        getElements(root);
        return root;
    }

    public void getElements(View root){
        btEntradas = root.findViewById(R.id.btEntradas);
        btEntradas.setOnClickListener(this);

        btMusica = root.findViewById(R.id.btMusica);
        btMusica.setOnClickListener(this);

        btNFTs = root.findViewById(R.id.btNFTs);
        btNFTs.setOnClickListener(this);

        recyclerView = root.findViewById(R.id.recyclerCollections);

        SvColeccion = root.findViewById(R.id.sv_coleccion);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btEntradas:
                if(!btEntradas.isPressed()){
                    btEntradas.setPressed(true);
                }
                rellenarRecylcerEntradas();
                SvColeccion.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        return false;
                    }
                    @Override
                    public boolean onQueryTextChange(String s) {
                        filterTicket(s);
                        return false;
                    }
                });
                break;
            case R.id.btMusica:
                rellenarRecyclerMusica();
                SvColeccion.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        return false;
                    }
                    @Override
                    public boolean onQueryTextChange(String s) {
                        filterSongs(s);
                        return false;
                    }
                });
                break;
            case R.id.btNFTs:
                rellenarGridNfts();
                SvColeccion.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        return false;
                    }
                    @Override
                    public boolean onQueryTextChange(String s) {
                        filterNft(s);
                        return false;
                    }
                });
                break;
        }
    }

    private void filterTicket(String text) { //fitltra el texto en el recycler view
        ArrayList<Eventos> filteredlist = new ArrayList<>();
        for (Eventos item : listEventos) {
            if (item.getTitulo().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
            }
        }
        adapterEntradas.filterList(filteredlist);
    }

    private void filterSongs(String text) { //fitltra el texto en el recycler view
        ArrayList<Cancion> filteredlist = new ArrayList<>();
        for (Cancion item : listCanciones) {
            if (item.getTitulo().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
            }
        }
        adapterMusica.filterList(filteredlist);
    }

    private void filterNft(String text) { //fitltra el texto en el recycler view
        ArrayList<NFTs> filteredlist = new ArrayList<>();
        for (NFTs item : listNFTs) {
            if (item.getNombreNft().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
            }
        }
        adapterNFTs.filterList(filteredlist);
    }

    public void rellenarRecylcerEntradas(){
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        listEventos = new ArrayList<>();
        listEventos.add(new Eventos("Dos notas", R.drawable.imgconcierto, 29.99, "domingo, abr 20, 2022", "13:00pm - 20:00pm", "descripcion 1"));
        listEventos.add(new Eventos("Fondo Flamenco", R.drawable.imgteatro, 39.99, "miercoles, jun 20, 2022", "10:00am - 17:00pm", "descripcion 5"));
        listEventos.add(new Eventos("Alice Cooper", R.drawable.img5, 29.99, "domingo, abr 20, 2022", "13:00pm - 20:00pm", "descripcion 1"));
        listEventos.add(new Eventos("White Lies", R.drawable.imgmusica, 29.99, "lunes, may 13, 2022", "17:00pm - 22:00pm", "descripcion 3"));
        listEventos.add(new Eventos("Harry Styles", R.drawable.imgorquestra, 39.99, "martes, may 25, 2022", "11:00am - 15:00pm", "descripcion 4"));
        listEventos.add(new Eventos("Prince Royce - Noches del Botánico 2022", R.drawable.img3, 29.99, "miercoles, jun 20, 2022", "10:00am - 17:00pm", "descripcion 5"));
        listEventos.add(new Eventos("Pablo Alborán - Noches del Botánico 2022", R.drawable.img4, 29.99, "domingo, abr 20, 2022", "13:00pm - 20:00pm", "descripcion 1"));
        listEventos.add(new Eventos("Madrid Puro Reggaeton Festival", R.drawable.img2, 39.99, "miercoles, jun 20, 2022", "10:00am - 17:00pm", "descripcion 5"));
        adapterEntradas= new recyclerEntradasAdapter(listEventos, getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterEntradas);
    }

    public void rellenarRecyclerMusica(){
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        listCanciones = new ArrayList<>();
        listCanciones.add(new Cancion(R.drawable.img1, "Y nos dieron las diez (Joaquín Sabina)", 9.99, "2022", getString(R.string.descripcion), "Daddy Yankee"));
        listCanciones.add(new Cancion(R.drawable.img2, "I Never Loved a Man the Way I Love You", 4.99, "2023", getString(R.string.descripcion), "Ozuna"));
        listCanciones.add(new Cancion(R.drawable.img3, "The Kingsmen In Person", 6.99, "2020", getString(R.string.descripcion), "Kaze"));
        listCanciones.add(new Cancion(R.drawable.img4, "Layla and Other Assorted Love Songs", 12.99, "2021", getString(R.string.descripcion), "Rauw Alejandro"));
        listCanciones.add(new Cancion(R.drawable.img5, "Are You Experienced", 3.99, "2023", getString(R.string.descripcion), "Bad Bunny"));
        adapterMusica= new recyclerMusicaAdapter(listCanciones, getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterMusica);
    }

    public void rellenarGridNfts(){
        listNFTs = new ArrayList<>();
        listNFTs.add(new NFTs(R.drawable.recurso_9, R.drawable.music_white, "Cute Alien Riding UFO", "Reclamado"));
        listNFTs.add(new NFTs(R.drawable.recurso_dino, R.drawable.music_white, "Cute Blue Tyrannosaurus", "Reclamado"));
        listNFTs.add(new NFTs(R.drawable.recurso_panda, R.drawable.music_white, "Cute panda drink coffe", "Reclamado"));
        listNFTs.add(new NFTs(R.drawable.recurso_osomiel, R.drawable.ticket_white, "Cute honey bear hug", "Reclamado"));
        adapterNFTs = new recyclerGridNFTsAdapter(listNFTs, mContext);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2)); //grid utiliza el contexto y el nr de columnas que queremos ver
        recyclerView.setAdapter(adapterNFTs);
    }
}