package com.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nft_ticket_andrey.R;
import com.models.Eventos;

import java.util.ArrayList;

/**
 * Created by Cristian Mármol cristian.marmol@occamcomunicacion.com on 25/04/2022.
 */
public class listAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Eventos> datos;

    public listAdapter(ArrayList<Eventos> datos, Context mContext) {
        this.datos = datos;
        this.context = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder = new listAdapter.ViewHolder(inflater.inflate(R.layout.item_evento_list,parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Eventos events = datos.get(position);
        final listAdapter.ViewHolder eventHolder = (listAdapter.ViewHolder) holder;
        eventHolder.txtTitulo.setText(events.getTitulo());
        eventHolder.txtPrecio.setText(String.valueOf(events.getPrecio()) + "€");
        eventHolder.imEvento.setImageResource(events.getImgEvento());
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imEvento;
        TextView txtTitulo;
        TextView txtPrecio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imEvento = itemView.findViewById(R.id.imgEvento);
            txtTitulo = itemView.findViewById(R.id.txtTituloSearch);
            txtPrecio = itemView.findViewById(R.id.txtPrecioSearch);
        }
    }
}


