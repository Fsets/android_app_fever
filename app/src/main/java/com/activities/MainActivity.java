package com.activities;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.dialogs.dialogFragmentLogin;
import com.example.nft_ticket_andrey.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //llamada al metodo seekbar

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_eventos, R.id.navigation_musica, R.id.navigation_search, R.id.navigation_profile, R.id.navigation_collections)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        //navView.setItemIconTintList(null);

        //para pulsar los botones (home, profile etc..)
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_profile) {
                    showModalLoginDialog(); //lanza el dialogo de login
                }
                if (item.getItemId() == R.id.navigation_eventos) {
                    //muestra el eventos
                    navController.navigate(R.id.navigation_eventos);
                    Toast.makeText(getApplicationContext(),"Eventos", Toast.LENGTH_SHORT).show();
                }
                if (item.getItemId() == R.id.navigation_musica) {
                    //muestra el musica
                    navController.navigate(R.id.navigation_musica);
                    Toast.makeText(getApplicationContext(),"Musica", Toast.LENGTH_SHORT).show();
                }
                if (item.getItemId() == R.id.navigation_search) {
                    //muestra el search
                    navController.navigate(R.id.navigation_search);
                }
                if (item.getItemId() == R.id.navigation_collections) {
                    //muestra el colleciones
                    navController.navigate(R.id.navigation_collections);
                }
                return false;
            }
});
    }

    public void showModalLoginDialog(){
        Bundle args = new Bundle(); //PASAR DATOS DE UN ACTIVITY A OTRO

        final dialogFragmentLogin dialogLogin = new dialogFragmentLogin(args, MainActivity.this, R.layout.dialog_login);
        dialogLogin.show();
        dialogLogin.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogLogin.setDialogResult(new dialogFragmentLogin.OnMyDialogResult() {
            @Override
            public void fin(boolean fin) {
            }
        });
    }


}