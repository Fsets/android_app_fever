package com.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nft_ticket_andrey.R;
import com.models.Cancion;
import com.models.Eventos;

import java.util.ArrayList;

/**
 * Created by Cristian Mármol cristian.marmol@occamcomunicacion.com on 25/04/2022.
 */
public class recyclerMusicaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Cancion> datos;

    public recyclerMusicaAdapter(ArrayList<Cancion> datos, Context mContext) {
        this.datos = datos;
        this.context = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder = new recyclerMusicaAdapter.ViewHolder(inflater.inflate(R.layout.item_recycler_musica,parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Cancion song = datos.get(position);
        final recyclerMusicaAdapter.ViewHolder songHolder = (recyclerMusicaAdapter.ViewHolder) holder;
        songHolder.txtTitulo.setText(song.getTitulo());
        songHolder.txtFecha.setText(song.getFechaCancion());
        songHolder.txtNombre.setText(song.getNombreArtista());
        songHolder.imCancion.setImageResource(song.getImgCancion());
        //songHolder.imPlaySong.setOnClickListener();
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imCancion;
        ImageButton imPlaySong;
        TextView txtTitulo;
        TextView txtNombre;
        TextView txtFecha;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imCancion = itemView.findViewById(R.id.imbt_cancion);
            txtTitulo = itemView.findViewById(R.id.txt_titulo_cancion);
            txtNombre = itemView.findViewById(R.id.txt_nombre_artista);
            txtFecha = itemView.findViewById(R.id.txtAñoCancion);
            imPlaySong = itemView.findViewById(R.id.imgReproducirCancion);
        }
    }

    // method for filtering our recyclerview items.
    public void filterList(ArrayList<Cancion> filterllist) {
        datos = filterllist;
        notifyDataSetChanged();
    }
}


