package com.example.nft_ticket_andrey.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.bottomsheet.FiltrosBottomSheet;
import com.example.nft_ticket_andrey.R;

public class HomeFragment extends Fragment implements View.OnClickListener{
    private HomeViewModel homeViewModel;
    private ImageButton btn_filtros;
    private Button btn_todas, btn_cat1, btn_cat2;
    private Context mContext;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        View root =inflater.inflate(R.layout.fragment_home, container, false);
        getElements(root);
        return root;
    }

    public void getElements(View root){
        btn_filtros = root.findViewById(R.id.btn_filtros_home);
        btn_filtros.setOnClickListener(this);

        btn_todas = root.findViewById(R.id.btn_todas);
        btn_todas.setOnClickListener(this);
        btn_cat1= root.findViewById(R.id.btn_cat1);
        btn_cat1.setOnClickListener(this);
        btn_cat2 = root.findViewById(R.id.btm_cat2);
        btn_cat2.setOnClickListener(this);
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