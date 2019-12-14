package com.example.primerparcial15;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class ComprarAdmi extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    AdminDataBase adminDataBase;
ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprar_admi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        adminDataBase = new AdminDataBase(this,"database",null,1);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               comprarproducto();



            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        listView=(ListView)findViewById(R.id.listcompadmin);
        List<Compras> allCompra = adminDataBase.listComprasADmi();

        if(allCompra.size() > 0){
            AdaptadorCompraAdmi lada = new AdaptadorCompraAdmi(this, allCompra);
            listView.setAdapter(lada);
        }else {
            Toast.makeText(this, "Sin Compras", Toast.LENGTH_LONG).show();
        }



    }


    public void comprarproducto()
    {

        LayoutInflater inflater = LayoutInflater.from(this);
        View subView1= inflater.inflate(R.layout.comprarproducto,null);
        final EditText etnomcom,etcancom,etprecom,ettotcom;
        final ImageView ima;

        etnomcom=(EditText)subView1.findViewById(R.id.nomprocompra);
        etcancom=(EditText)subView1.findViewById(R.id.cantidadcompra);
        etprecom=(EditText)subView1.findViewById(R.id.preciocompra);
       // ettotcom=(EditText)subView1.findViewById(R.id.costototal);

        ima=(ImageView)subView1.findViewById(R.id.imagen100);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Comprar producto");
        builder.setView(subView1);
        builder.setCancelable(false);
        builder.create();

        builder.setPositiveButton("Comprar productos", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

               String nocom= etnomcom.getText().toString();
               String cancom=etcancom.getText().toString();
               String precom=etprecom.getText().toString();

             //  final byte[] i=imageViewToByte(ima);
             //  String totcom=ettotcom.getText().toString();
                double total=Integer.parseInt(cancom)*Double.parseDouble(precom);

                if(TextUtils.isEmpty(nocom) || TextUtils.isEmpty(cancom) || TextUtils.isEmpty(precom)  )
                {
                    Toast.makeText(ComprarAdmi.this, "No deben existir campos vacios", Toast.LENGTH_LONG).show();
                }else{

                    //if(adminDataBase.buscaNombreProd(nocom)==null){

                        //Compras nuevo=new Compras(nocom,Integer.parseInt(cancom),Double.parseDouble(precom),total);
                        //adminDataBase.addCompras(nuevo);
                       // Producto nuevoproducto=new Producto(nocom,"",Float.parseFloat(precom),Integer.parseInt(cancom),i);
                        //adminDataBase.addProduct(nuevoproducto);
                       // Toast.makeText(getApplicationContext(), "Registro nuevo producto grabado exitosamente", Toast.LENGTH_LONG).show();

                    }if(adminDataBase.buscaNombreProd(nocom)!=null){

                        Compras nuevo=new Compras(nocom,Integer.parseInt(cancom),Double.parseDouble(precom),total);
                        adminDataBase.addCompras(nuevo);
                        Producto producto=adminDataBase.buscaNombreProd(nocom);
                        int nuevacantidad=producto.getStock()+Integer.parseInt(cancom);
                        adminDataBase.actualizarcompra(nocom,nuevacantidad);
                        Toast.makeText(getApplicationContext(), "Stock actualizado", Toast.LENGTH_LONG).show();

                    }

                    finish();
                    startActivity(getIntent());


            }
        });
        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ComprarAdmi.this, "Tarea cancelada", Toast.LENGTH_LONG).show();
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
        getMenuInflater().inflate(R.menu.comprar_admi, menu);
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
            Intent h= new Intent(ComprarAdmi.this,administrador_ventas.class);
            startActivity(h);
        }if (id == R.id.Lista_productos) {
            Intent h= new Intent(ComprarAdmi.this,ListaProductos.class);
            startActivity(h);
        }if (id == R.id.Lista_clientes) {
            Intent h= new Intent(ComprarAdmi.this,ListaClientes.class);
            startActivity(h);
        }if (id==R.id.ComprarProductos){
            Intent h= new Intent(ComprarAdmi.this,ComprarAdmi.class);
            startActivity(h);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
/*
    private byte[] imageViewToByte(ImageView imageView) {

        Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream= new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);


        byte[] bytearray=stream.toByteArray();
        return bytearray;
    }*/
}
