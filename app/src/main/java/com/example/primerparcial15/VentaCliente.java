package com.example.primerparcial15;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.util.Calendar;
import java.util.List;

public class VentaCliente extends AppCompatActivity {

    AdminDataBase adminDataBase;
    ListView listView;
    static List<Producto> allpro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta_cliente);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        adminDataBase = new AdminDataBase(this,"database",null,1);

        Bundle bolsa = getIntent().getExtras();
        String cor = bolsa.getString("nomb");

        Cliente clie=adminDataBase.buscarcorreocliente(cor);

        Toast.makeText(getApplicationContext(),"El usuario es "+clie.getNombre()+" "+clie.getApellido(),Toast.LENGTH_LONG).show();

        listView=(ListView)findViewById(R.id.VentaCliente);
        allpro = adminDataBase.listProdVenta();

        if(allpro.size() > 0){
            AdaptadorVenta lada = new AdaptadorVenta(this, allpro);

            listView.setAdapter(lada);
        }else {
            Toast.makeText(this, "Sin productos", Toast.LENGTH_LONG).show();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
               compracliente(position);
            }
        });


    }

    public void compracliente( final int i){
        Toast.makeText(VentaCliente.this,"El producto es "+allpro.get(i).getNombre_producto_(),Toast.LENGTH_LONG).show();

        LayoutInflater inflater=LayoutInflater.from(VentaCliente.this);
        final View view=inflater.inflate(R.layout.comprarcliente,null);

        final TextView etnombre,etprecio;
        final EditText Canti,meses;
        final ImageView imageView;

        Bundle bolsa = getIntent().getExtras();
        String cor = bolsa.getString("nomb");

        final Cliente clie=adminDataBase.buscarcorreocliente(cor);

        //etnombre=(TextView) view.findViewById(R.id.tvnombrecompra);
        //etprecio=(TextView) view.findViewById(R.id.tvpreciocompra);
        Canti=(EditText)view.findViewById(R.id.etcantidadcompra);
        //imageView=(ImageView)view.findViewById(R.id.imageViewcomprar);
        meses=(EditText)view.findViewById(R.id.mes);
        AlertDialog.Builder builder = new AlertDialog.Builder(VentaCliente.this);
        builder.setTitle("Comprar: "+allpro.get(i).getNombre_producto_()+"  Precio: "+allpro.get(i).getPrecio_venta()+" Bs");
        builder.setView(view);
        builder.setCancelable(false);
        builder.create();

        builder.setPositiveButton("Comprar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                final int can = Integer.parseInt(Canti.getText().toString());
                final int mess = Integer.parseInt(meses.getText().toString());
                int nuevacantidad = allpro.get(i).getStock() - can;
                adminDataBase.actualizarcompra(allpro.get(i).getNombre_producto_(), nuevacantidad);
                final Calendar calendario = Calendar.getInstance();
                int dia, mes, año;
                dia = calendario.get(Calendar.DAY_OF_MONTH);
                mes = calendario.get(Calendar.MONTH);
                año = calendario.get(Calendar.YEAR);
                final String fecha = "" + dia + " : " + mes + " : " + año;

                if (mes < 0 || mes > 12) {
                    Toast.makeText(getApplicationContext(),"Solo se puede de 1 a 12 meses",Toast.LENGTH_LONG).show();
                } else {

                    Venta venta = new Venta(fecha, (can * allpro.get(i).precio_venta), clie.getId_cliente(), mess);
                    adminDataBase.addVenta(venta);
                    Toast.makeText(getApplicationContext(), "Producto comprado", Toast.LENGTH_LONG).show();
                }
                finish();
                startActivity(getIntent());
            }

        });
        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(VentaCliente.this, "Tarea cancelada", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();

    }


}
