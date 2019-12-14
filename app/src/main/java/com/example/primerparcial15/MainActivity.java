package com.example.primerparcial15;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    AdminDataBase adminDataBase;

    EditText cor,pass;
    Button blogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        adminDataBase = new AdminDataBase(this,"database",null,1);
        adminDataBase.addAdministrador(new Administrador("6138703","mamani","poma","alexander","viacha","manuel@gmail.com","1119"));
        cor=(EditText)findViewById(R.id.logincorreo);
        pass=(EditText)findViewById(R.id.loginpassword);
        blogin=(Button) findViewById(R.id.btnlogin);
        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (adminDataBase.logincliente(cor.getText().toString(),pass.getText().toString())!=null){

                    Intent h= new Intent(MainActivity.this,VentaCliente.class);
                    h.putExtra("nomb",(adminDataBase.logincliente(cor.getText().toString(),pass.getText().toString()).getCorreo()));
                    startActivity(h);

                }else{
                    Toast.makeText(MainActivity.this,"El usuario no existe",Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.idRegistrarse) {
            Registrarse();
            return true;
        }if (id==R.id.idLoginAdmi){
            LoginAdmin();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void LoginAdmin()
    {
        LayoutInflater layoutInflater=LayoutInflater.from(this);
        View loginadmin=layoutInflater.inflate(R.layout.iniciaradmin,null);

        final EditText coradmi,pasadmi;
        final Button btnisadmi;

        coradmi=(EditText)loginadmin.findViewById(R.id.idcorAdmi);
        pasadmi=(EditText)loginadmin.findViewById(R.id.idpasAdmi);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ADMINISTRADOR");
        builder.setView(loginadmin);
        builder.setCancelable(false);
        builder.create();
        builder.setPositiveButton("Inisiar sesion", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String correo=coradmi.getText().toString();
                String passw=pasadmi.getText().toString();

                 if(TextUtils.isEmpty(correo) || TextUtils.isEmpty(passw) )    {

                     Toast.makeText(MainActivity.this,"No debe haber campos vacios",Toast.LENGTH_LONG).show();
                 }else{
                     Administrador administrador=adminDataBase.loginadmi(correo,passw);
                     if(administrador!=null){

                         Intent var = new Intent(MainActivity.this, administrador_ventas.class);

                         var.putExtra("correo",administrador.getCorreo());
                         startActivity(var);
                         coradmi.setText("");
                         pasadmi.setText("");

                     }else{
                         Toast.makeText(MainActivity.this,"Usuario no existente",Toast.LENGTH_LONG).show();
                     }

                 }
            }
        });
        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Tarea cancelada", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
    public void Registrarse(){

        LayoutInflater layoutInflater= LayoutInflater.from(this);
        View registrarse=layoutInflater.inflate(R.layout.layout_registrarse,null);
        final EditText ci,ape,nom,cor,tel,pass;
        ci=(EditText)registrarse.findViewById(R.id.ci_cli);
        ape=(EditText)registrarse.findViewById(R.id.ape_cli);
        nom=(EditText)registrarse.findViewById(R.id.nom_cli);
        cor=(EditText)registrarse.findViewById(R.id.correo_cli);
        tel=(EditText)registrarse.findViewById(R.id.tel_cli);
        pass=(EditText)registrarse.findViewById(R.id.pass_cli);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("REGISTRARSE");
        builder.setView(registrarse);
        builder.setCancelable(false);
        builder.create();
        builder.setPositiveButton( "Registrar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {

                String cic=ci.getText().toString();
                String apec=ape.getText().toString();
                String nomc=nom.getText().toString();
                String corc=cor.getText().toString();
                String telc=tel.getText().toString();
                String passc=pass.getText().toString();

                if(TextUtils.isEmpty(cic) || TextUtils.isEmpty(apec) || TextUtils.isEmpty(nomc) || TextUtils.isEmpty(corc) || TextUtils.isEmpty(telc) || TextUtils.isEmpty(passc) )
                {
                    Toast.makeText(MainActivity.this, "No deben existir campos vacios", Toast.LENGTH_LONG).show();
                }else{
                    Cliente nuevo=new Cliente(cic,apec,nomc,corc,Integer.parseInt(telc),passc);

                    adminDataBase.addCliente(nuevo);
                    Toast.makeText(getApplicationContext(), "Registro grabado exitosamente", Toast.LENGTH_LONG).show();

                    finish();
                    startActivity(getIntent());
                }
            }
        });
        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Tarea cancelada", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
}
