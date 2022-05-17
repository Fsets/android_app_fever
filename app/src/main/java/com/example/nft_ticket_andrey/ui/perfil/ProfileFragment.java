package com.example.nft_ticket_andrey.ui.perfil;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.activities.QRActivity;
import com.example.nft_ticket_andrey.R;
import com.example.nft_ticket_andrey.ui.home.HomeViewModel;


public class ProfileFragment extends Fragment implements View.OnClickListener{
    private HomeViewModel homeViewModel;
    private View root;
    private Context mContext;
    ImageView qrcode;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        root =inflater.inflate(R.layout.fragment_profile, container, false);
        mContext= getContext();

        getElements(root);

        return root;
    }

    public void getElements(View root){
        qrcode = root.findViewById(R.id.imVQrCode);
        qrcode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bundle args = new Bundle();
        switch (v.getId()) {
            case R.id.imVQrCode:
                Intent i = new Intent(v.getContext(), QRActivity.class);
                v.getContext().startActivity(i);
                break;
        }
    }
}