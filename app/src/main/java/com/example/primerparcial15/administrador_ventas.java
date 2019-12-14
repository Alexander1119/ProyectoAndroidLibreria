package com.example.primerparcial15;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class administrador_ventas extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    AdminDataBase adminDataBase;
    FloatingActionButton fab;
    DrawerLayout drawer;
    NavigationView navigationView;
    ListView listView;
    TextView correo,nombre;
    Administrador administrador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador_ventas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        adminDataBase = new AdminDataBase(this,"database",null,1);



        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //correo=(TextView)findViewById(R.id.nombreusuario);
        //nombre=(TextView)findViewById(R.id.correousuario);

       // Bundle bolsa = getIntent().getExtras();
       // String cor = bolsa.getString("correo");
       // administrador=adminDataBase.buscarcoradmi(cor);
        //Toast.makeText(getApplicationContext(),"Administrador "+administrador.getApellido_paterno()+"  "+administrador.getNombre()+" - "+administrador.getCorreo(),Toast.LENGTH_LONG).show();

        //correo.setText((adminDataBase.buscarisadmi(cor)).correo);
        //nombre.setText((adminDataBase.buscarisadmi(cor)).nombre);

        listView=(ListView)findViewById(R.id.idLisVent);
        List<Venta> allventa = adminDataBase.listVenta();

        if(allventa.size() > 0){
            AdapterListaVenta lada = new AdapterListaVenta(this, allventa);
            listView.setAdapter(lada);
        }else {
            Toast.makeText(this, "Sin ventas", Toast.LENGTH_LONG).show();
        }

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
        getMenuInflater().inflate(R.menu.administrador_ventas, menu);
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

        if (id == R.id.Home) {
            Intent h= new Intent(administrador_ventas.this,administrador_ventas.class);

            startActivity(h);
        }if (id == R.id.Lista_productos) {

            Intent h= new Intent(administrador_ventas.this,ListaProductos.class);

            startActivity(h);
        }if (id == R.id.Lista_clientes) {

            Intent h= new Intent(administrador_ventas.this,ListaClientes.class);

            startActivity(h);
        }if (id==R.id.ComprarProductos){


            Intent h= new Intent(administrador_ventas.this,ComprarAdmi.class);
            startActivity(h);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
