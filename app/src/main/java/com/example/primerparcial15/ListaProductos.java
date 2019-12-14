package com.example.primerparcial15;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.KeyEvent;
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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ListaProductos extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    AdminDataBase adminDataBase;
    ListView listView;
    List<Producto> allProd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        adminDataBase = new AdminDataBase(this,"database",null,1);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent invo=new Intent(getApplicationContext(),Add_producto.class);
                startActivity(invo);
                //addproducto();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        listView=(ListView)findViewById(R.id.listpro);
         allProd= adminDataBase.listProducts();

        if(allProd.size() > 0){
            AdaptadorProducto lada = new AdaptadorProducto(this, allProd);
            listView.setAdapter(lada);
        }else {
            Toast.makeText(this, "Sin productos", Toast.LENGTH_LONG).show();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent h= new Intent(ListaProductos.this,ActualizarProducto.class);
                String nombreproducto=allProd.get(position).getNombre_producto_();
                h.putExtra("nombreproducto",nombreproducto);
                startActivity(h);
            }
        });

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
        getMenuInflater().inflate(R.menu.lista_productos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cerses) {
            cerrarsesion();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public  void cerrarsesion(){

        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        try {
            onBackPressed();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Home) {
            Intent h= new Intent(ListaProductos.this,administrador_ventas.class);
            startActivity(h);
        }if (id == R.id.Lista_productos) {
            Intent h= new Intent(ListaProductos.this,ListaProductos.class);
            startActivity(h);
        }if (id == R.id.Lista_clientes) {
            Intent h= new Intent(ListaProductos.this,ListaClientes.class);
            startActivity(h);
        }if (id==R.id.ComprarProductos){
            Intent h= new Intent(ListaProductos.this,ComprarAdmi.class);
            startActivity(h);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*
    public void addproducto()
    {

        LayoutInflater inflater = LayoutInflater.from(this);
        View subView1= inflater.inflate(R.layout.addproducto,null);
        final EditText etnom,etdet,etpre,etcan;

        etnom=(EditText)subView1.findViewById(R.id.nom);
        etdet=(EditText)subView1.findViewById(R.id.det);
        etpre=(EditText)subView1.findViewById(R.id.pv);
        etcan=(EditText)subView1.findViewById(R.id.can);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Añadir producto");
        builder.setView(subView1);
        builder.setCancelable(false);
        builder.create();

        builder.setPositiveButton("Añadir productos", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String no= etnom.getText().toString();
                String de=etdet.getText().toString();
                String pr=etpre.getText().toString();
                String ca=etcan.getText().toString();


                if(TextUtils.isEmpty(no) || TextUtils.isEmpty(de) || TextUtils.isEmpty(pr) || TextUtils.isEmpty(ca)  )
                {
                    Toast.makeText(ListaProductos.this, "No deben existir campos vacios", Toast.LENGTH_LONG).show();
                }else{
                        Producto nuevoproducto=new Producto(no,de,Float.parseFloat(pr),Integer.parseInt(ca));
                        adminDataBase.addProduct(nuevoproducto);
                        Toast.makeText(getApplicationContext(), "Registro nuevo producto grabado exitosamente", Toast.LENGTH_LONG).show();



                    finish();
                    startActivity(getIntent());
                }

            }
        });
        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ListaProductos.this, "Tarea cancelada", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }*/

}
