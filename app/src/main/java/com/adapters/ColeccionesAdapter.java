package com.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.activities.CancionesActivity;
import com.activities.EventosActivity;
import com.example.nft_ticket_andrey.R;
import com.models.Cancion;
import com.models.Eventos;

import java.util.ArrayList;

/**
 * Created by Cristian Mármol cristian.marmol@occamcomunicacion.com on 04/05/2022.
 */
public class ColeccionesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    private Context mContext;
    private ArrayList<Cancion> listCanciones;

    public ColeccionesAdapter(ArrayList<Cancion> listCanciones, Context mContext) {
        this.listCanciones = listCanciones;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder = new ColeccionesAdapter.ViewHolder(inflater.inflate(R.layout.item_coleccion,parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Cancion canciones = listCanciones.get(position);
        final ColeccionesAdapter.ViewHolder songHolder = (ColeccionesAdapter.ViewHolder) holder;
        songHolder.txtTitulo.setText(canciones.getTitulo());
        //songHolder.txtPrecio.setText(String.valueOf(canciones.getPrecio()) + "€");
        songHolder.imCancion.setImageResource(canciones.getImgCancion());

        songHolder.imCancion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //a donde lleva el evento
                //Toast.makeText(mContext.getApplicationContext(),"Detalles evento.." + eventHolder.txtTitulo.getText(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent( view.getContext(), CancionesActivity.class); //nos lleva al evento
                intent.putExtra("canciones", canciones); //pasar objeto seleccionado
                view.getContext().startActivity(intent); //envia info a eventosActivity
            }
        });
    }

    @Override
    public int getItemCount() {
        return listCanciones.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imCancion;
        TextView txtTitulo;
        LinearLayout btVerColeccion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imCancion = itemView.findViewById(R.id.imEventoColecc);
            txtTitulo = itemView.findViewById(R.id.txtTituloColecc);
            btVerColeccion = itemView.findViewById(R.id.btVerColeccion);
        }
    }

    @Override
    public void onClick(View view) {

    }
}
