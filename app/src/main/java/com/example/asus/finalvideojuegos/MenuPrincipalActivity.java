package com.example.asus.finalvideojuegos;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.asus.finalvideojuegos.entidades.Producto;
import com.example.asus.finalvideojuegos.fragments.AgregarProductosFragment;
import com.example.asus.finalvideojuegos.fragments.CarritoDeComprasFragment;
import com.example.asus.finalvideojuegos.fragments.ConsultarListaProductosFragment;
import com.example.asus.finalvideojuegos.fragments.ConsultarListaUsuariosFragment;
import com.example.asus.finalvideojuegos.fragments.ConsultarProductoFragment;
import com.example.asus.finalvideojuegos.fragments.DetalleProductoFragment;
import com.example.asus.finalvideojuegos.interfaces.IComunicaFragment;

public class MenuPrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IComunicaFragment {
String prueba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        FragmentManager fragmentManager = getSupportFragmentManager();//PARA Q SE EJECUTE EL FRAGMENT CUANDO ABRES AL APP
        fragmentManager.beginTransaction().replace(R.id.id_contenedor,new AgregarProductosFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();//PARA Q SE EJECUTE EL FRAGMENT CUANDO ABRES AL APP


        if (id == R.id.nav_CatalogoDeProductos) {
            fragmentManager.beginTransaction().replace(R.id.id_contenedor,new ConsultarListaProductosFragment()).commit();
            // Handle the camera action
        } else if (id == R.id.nav_CarritoDeCompras) {
            fragmentManager.beginTransaction().replace(R.id.id_contenedor,new CarritoDeComprasFragment()).commit();


        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_consultarListaUsuarios) {
            fragmentManager.beginTransaction().replace(R.id.id_contenedor,new ConsultarListaUsuariosFragment()).commit();


        }else if (id == R.id.nav_crearAdminsitradores){

        }else if(id == R.id.nav_agregarProductos){
            fragmentManager.beginTransaction().replace(R.id.id_contenedor,new AgregarProductosFragment()).commit();

        }else if(id == R.id.nav_ModificarInventario){
            fragmentManager.beginTransaction().replace(R.id.id_contenedor,new ConsultarProductoFragment()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void enviarProducto(Producto producto) {
        DetalleProductoFragment detallefragment = new DetalleProductoFragment();
        Bundle bundleEnvio= new Bundle();
        bundleEnvio.putSerializable("objeto",producto);
        detallefragment.setArguments(bundleEnvio);

        //Cargar el gfragment en activity
        getSupportFragmentManager().beginTransaction().
                replace(R.id.id_contenedor,detallefragment).addToBackStack(null).commit();

    }
}
