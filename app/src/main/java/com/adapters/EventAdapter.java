package com.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.activities.EventosActivity;
import com.example.nft_ticket_andrey.R;
import com.example.nft_ticket_andrey.ui.home.HomeFragment;
import com.models.Eventos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Cristian Mármol cristian.marmol@occamcomunicacion.com on 07/04/2022.
 */
public class EventAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
//adaptador para sacar eventos y mostrarlos en principal
    private Context mContext;
    private ArrayList<Eventos> listEventos;

    public EventAdapter(ArrayList<Eventos> listEventos, Context mContext) {
        this.listEventos = listEventos;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder = new EventAdapter.ViewHolder(inflater.inflate(R.layout.item_evento,parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Eventos events = listEventos.get(position);
        final EventAdapter.ViewHolder eventHolder = (EventAdapter.ViewHolder) holder;
        eventHolder.txtTitulo.setText(events.getTitulo());
        eventHolder.txtPrecio.setText(String.valueOf(events.getPrecio()));
        eventHolder.imEvento.setImageResource(events.getImgEvento());

        eventHolder.imEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //a donde lleva el evento
                //Toast.makeText(mContext.getApplicationContext(),"Detalles evento.." + eventHolder.txtTitulo.getText(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(view.getContext(), EventosActivity.class); //nos lleva al evento
                intent.putExtra("evento", events); //pasar objeto seleccionado
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listEventos.size();
    }

    @Override
    public void onClick(View view) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imEvento;
        TextView txtTitulo;
        TextView txtPrecio;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imEvento = itemView.findViewById(R.id.imEvento);
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
            txtPrecio = itemView.findViewById(R.id.txtPrecio);
        }
    }
}
