package com.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.activities.EventosActivity;
import com.example.nft_ticket_andrey.R;
import com.models.Eventos;
import com.models.NFTs;

import java.util.ArrayList;

/**
 * Created by Cristian Mármol cristian.marmol@occamcomunicacion.com on 04/05/2022.
 */
public class recyclerGridNFTsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    //adaptador para sacar eventos tipo grid y mostrarlos en principal
    private Context mContext;
    private ArrayList<NFTs> listNFTs;

    public recyclerGridNFTsAdapter(ArrayList<NFTs> listNFTs, Context mContext) {
        this.listNFTs = listNFTs;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder = new recyclerGridNFTsAdapter.ViewHolder(inflater.inflate(R.layout.item_grid_nfts,parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final NFTs nfts = listNFTs.get(position);
        final recyclerGridNFTsAdapter.ViewHolder nftHolder = (recyclerGridNFTsAdapter.ViewHolder) holder;
        nftHolder.txtNombre.setText(nfts.getNombreNft());
        nftHolder.txtEstado.setText(nfts.getEstado());
        nftHolder.imTipo.setImageResource(nfts.getImgTipo());

        nftHolder.imNft.setImageResource(nfts.getImgNFT());
    }

    @Override
    public int getItemCount() {
        return listNFTs.size();
    } //total number of cells

    @Override
    public void onClick(View view) {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imNft, imTipo;
        TextView txtNombre;
        TextView txtEstado;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imNft = itemView.findViewById(R.id.imNFT);
            imTipo = itemView.findViewById(R.id.imTipoNft);
            txtNombre = itemView.findViewById(R.id.txtTituloNFT);
            txtEstado = itemView.findViewById(R.id.txtTipoNft);
        }
    }
}
