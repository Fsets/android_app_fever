package com.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nft_ticket_andrey.R;
import com.models.Eventos;

import java.util.ArrayList;

/**
 * Created by Cristian Mármol cristian.marmol@occamcomunicacion.com on 25/04/2022.
 */
public class recyclerEntradasAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Eventos> datos;

    public recyclerEntradasAdapter(ArrayList<Eventos> datos, Context mContext) {
        this.datos = datos;
        this.context = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder = new recyclerEntradasAdapter.ViewHolder(inflater.inflate(R.layout.item_recycler_entradas,parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Eventos ev = datos.get(position);
        final recyclerEntradasAdapter.ViewHolder songHolder = (recyclerEntradasAdapter.ViewHolder) holder;
        songHolder.txtTitulo.setText(ev.getTitulo());
        songHolder.txtTipo.setText(ev.getEstado());
        songHolder.imCancion.setImageResource(ev.getImgEvento());
        songHolder.txtFecha.setText(ev.getFechaEvento());
        if (songHolder.txtTipo.getText() == "sin usar") {
            songHolder.fondo.setBackgroundResource(R.drawable.style_entradas);
        }else {
            songHolder.fondo.setBackgroundResource(R.drawable.style_entradas_usadas);
            songHolder.txtTipo.setTextColor(Color.parseColor("#f54e60"));
        }
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout fondo;
        ImageView imCancion;
        TextView txtTitulo;
        TextView txtTipo;
        TextView txtFecha;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imCancion = itemView.findViewById(R.id.imgEventoCanc);
            txtTitulo = itemView.findViewById(R.id.txtTituloCanc);
            txtTipo = itemView.findViewById(R.id.txtTipoEnt);
            txtFecha = itemView.findViewById(R.id.txtFechalist);
            fondo = itemView.findViewById(R.id.fondoTipoEntrada);
        }
    }

    // method for filtering our recyclerview items.
    public void filterList(ArrayList<Eventos> filterllist) {
        // below line is to add our filtered
        // list in our course array list.
        datos = filterllist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }
}


