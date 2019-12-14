package com.example.primerparcial15;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Ajustes extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    AdminDataBase adminDataBase;

    TextView etnom,etapa,etama,etcor,etpas;
    Button btnnom,btnapa,btnama,btncor,btnetpas;
Administrador administrador;
    private String cor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        adminDataBase = new AdminDataBase(this,"database",null,1);

        etnom=(TextView)findViewById(R.id.idnombre);
        etapa=(TextView)findViewById(R.id.idapepat);
        etama=(TextView)findViewById(R.id.idapemat);
        etcor=(TextView)findViewById(R.id.idcorreo);
        etpas=(TextView)findViewById(R.id.idcontraseña);

        Bundle bolsa = getIntent().getExtras();
        cor = bolsa.getString("correo");
        //Toast.makeText(getApplicationContext(),"Usuario: "+cor,Toast.LENGTH_LONG).show();
        final Administrador administrador=adminDataBase.buscarcoradmi(cor);

        etnom.setText(administrador.getNombre());
        etapa.setText(administrador.getApellido_paterno());
        etama.setText(administrador.getApellido_materno());
        etcor.setText(administrador.getCorreo());
        etpas.setText(administrador.getPassword());
        btnnom=(Button)findViewById(R.id.btnCnom) ;
        btnapa=(Button)findViewById(R.id.btnCap) ;
        btnama=(Button)findViewById(R.id.btnCam) ;
        btncor=(Button)findViewById(R.id.btnCcor) ;
        btncor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiar(administrador.getCorreo(),4,administrador.getCorreo());
            }
        });
        btnetpas=(Button)findViewById(R.id.btnCcon) ;


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }
    public void cambiar(final String dato, final int num, final String correo){
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView1= inflater.inflate(R.layout.cambiar,null);
        final EditText etcambio;

        etcambio=(EditText)subView1.findViewById(R.id.cambio);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cambiar dato");
        builder.setView(subView1);
        builder.setCancelable(false);
        builder.create();
        builder.setPositiveButton("Cambiar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(etcambio.getText().toString()==dato){
                    Toast.makeText(getApplicationContext(),"El dato es el mismo",Toast.LENGTH_LONG).show();
                }else{

                    if(num==4){
                        adminDataBase.actualizarnomadmi(correo,dato);
                        Toast.makeText(getApplicationContext(),"Correo actualizado",Toast.LENGTH_LONG).show();
                    }

                    finish();
                    startActivity(getIntent());

                }

            }
        });
        builder.show();

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
        getMenuInflater().inflate(R.menu.ajustes, menu);
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
            Intent h= new Intent(Ajustes.this,administrador_ventas.class);
            startActivity(h);
        }if (id == R.id.Lista_productos) {
            Intent h= new Intent(Ajustes.this,ListaProductos.class);
            startActivity(h);
        }if (id == R.id.Lista_clientes) {
            Intent h= new Intent(Ajustes.this,ListaClientes.class);
            startActivity(h);
        }if (id==R.id.ComprarProductos){
            Intent h= new Intent(Ajustes.this,ComprarAdmi.class);
            startActivity(h);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
