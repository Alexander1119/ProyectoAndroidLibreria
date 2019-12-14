package com.example.primerparcial15;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AdminDataBase extends SQLiteOpenHelper {

    public AdminDataBase(Context context, String str,
                          SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(context, str, cursorFactory, i);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("PRAGMA user_version = 2");
        db.execSQL("PRAGMA foreign_keys = ON");
        String dbPath = db.getPath();
        android.util.Log.d(this.getClass().getSimpleName(),"******************dbpath: "+dbPath);
        String sql="CREATE\tTABLE administrador("+
                "id_admi INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "ci_admi TEXT ,"+
                "apellido_paterno TEXT,"+
                "apellido_materno TEXT,"+
                "nombre TEXT,"+
                "direccion TEXT,"+
                "correo TEXT,"+
                "password TEXT)";
        db.execSQL(sql);
        sql="CREATE\tTABLE cliente("+
                "id_cliente INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "ci_cliente TEXT,"+
                "apellido TEXT,"+
                "nombre TEXT,"+
                "correo TEXT,"+
                "telefono INTEGER," +
                "password TEXT)";
        db.execSQL(sql);


        sql = "CREATE\tTABLE producto(" +
                "item_producto integer primary key autoincrement," +
                "nombre_producto text," +
                "descripcion_producto text," +
                "precio_venta FLOAT," +
                "stock integer," +
                "imagen BLOB)";
        db.execSQL(sql);

        sql="create table compra(" +
                "id_compra integer primary key autoincrement," +
                "nombre_producto text," +
                "cantidad integer," +
                "precio_compra real," +
                "costo_total_compra real)";
        db.execSQL(sql)	;

        db.execSQL("PRAGMA foreign_keys = ON");
        sql="create table venta(" +
                "id_venta integer primary key autoincrement,"+
                "fecha text ,"+
                "total FLOAT,"+
                "id_cliente integer," +
                "tiempo integer," +
                "FOREIGN KEY(id_cliente) REFERENCES cliente(id_cliente))";
        db.execSQL(sql);


        db.execSQL("PRAGMA foreign_keys = ON");
        sql="create table amigo(" +
                "id_num integer primary key autoincrement,"+
                "nombre ,"+
                "imagen BLOB,"+
                "FOREIGN KEY(id_cliente) REFERENCES cliente(id_cliente))";
        db.execSQL(sql);


        db.execSQL("PRAGMA foreign_keys = ON");
        sql="create table familia(" +
                "id_num integer primary key autoincrement,"+
                "nombre ,"+
                "familia ,"+
                "imagen BLOB,"+
                "FOREIGN KEY(id_cliente) REFERENCES cliente(id_cliente))";
        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS cliente");
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS administrador");
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS producto");
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS compra");
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS venta");
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS amigo");
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS familia");
        onCreate(db);
    }


    public void addAmigo(Producto producto){
        ContentValues values = new ContentValues();
        values.put("id_num", producto.getNombre_producto_());
        values.put("nombre", producto.getDescripcion_producto());
        values.put("imagen",producto.getImagen());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("amigo", null, values);
    }


    public void addVenta(Venta venta){
        ContentValues values=new ContentValues();

        values.put("fecha",venta.getFecha());
        values.put("total",venta.getCantidadTotal());
        values.put("id_cliente",venta.getId_cliente());
        values.put("tiempo",venta.getTiempo());

        SQLiteDatabase db=this.getWritableDatabase();
        db.insert("venta",null,values);
    }
    public void addAdministrador(Administrador administrador){

        ContentValues values=new ContentValues();

        values.put("ci_admi",administrador.getCi_admi());
        values.put("apellido_paterno",administrador.getApellido_paterno());
        values.put("apellido_materno",administrador.getApellido_materno());
        values.put("nombre",administrador.getNombre());
        values.put("direccion",administrador.getDireccion());
        values.put("correo",administrador.getCorreo());
        values.put("password",administrador.getPassword());

        SQLiteDatabase db=this.getWritableDatabase();
        db.insert("administrador",null,values);
    }

    public void actualizarcompra(String nombre,int nuevacantidad){

      //  String[] parametros={nombre};
        ContentValues values=new ContentValues();

        values.put("stock", nuevacantidad);

        SQLiteDatabase db=this.getWritableDatabase();
        db.update("producto",values,"nombre_producto =?",new String[]{nombre});
     //   db.close();
    }

    public void actualizarnomadmi(String correo,String nuevocorreo){

        //  String[] parametros={nombre};
        ContentValues values=new ContentValues();

        values.put("correo", nuevocorreo);

        SQLiteDatabase db=this.getWritableDatabase();
        db.update("administrador",values,"correo =?",new String[]{nuevocorreo});
        //   db.close();
    }

    public  void actualizarProducto(String nombrepro,Producto producto){

        ContentValues values=new ContentValues();

        values.put("nombre_producto",producto.getNombre_producto_());
        values.put("descripcion_producto",producto.getDescripcion_producto());
        values.put("precio_venta",producto.getPrecio_venta());
        values.put("stock",producto.getStock());
        values.put("imagen",producto.getImagen());

        SQLiteDatabase db=this.getWritableDatabase();
        db.update("producto",values,"nombre_producto =?",new String[]{nombrepro});

    }
    public void addCliente(Cliente cliente){
        ContentValues values=new ContentValues();

        values.put("ci_cliente",cliente.getCi_cliente());
        values.put("apellido",cliente.getApellido());
        values.put("nombre",cliente.getNombre());
        values.put("correo",cliente.getCorreo());
        values.put("telefono",cliente.getTelefono());
        values.put("password",cliente.getPassword());

        SQLiteDatabase db=this.getWritableDatabase();
        db.insert("cliente",null,values);
    }

    public void addProduct(Producto producto){
        ContentValues values = new ContentValues();
        values.put("nombre_producto", producto.getNombre_producto_());
        values.put("descripcion_producto", producto.getDescripcion_producto());
        values.put("precio_venta", producto.getPrecio_venta());
        values.put("stock", producto.getStock());
        values.put("imagen",producto.getImagen());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("producto", null, values);
    }

    public void addCompras(Compras compras){
        ContentValues values=new ContentValues();

        values.put("nombre_producto",compras.getNombre_producto());
        values.put("cantidad",compras.getCantidad());
        values.put("precio_compra",compras.getPrecio_compra());
        values.put("costo_total_compra",compras.getCosto_total());


        SQLiteDatabase db=this.getWritableDatabase();
        db.insert("compra",null,values);


    }

    public Cliente logincliente(String cor,String pas)
    {

        String args[] = new String[]{cor, pas};
        SQLiteDatabase db = this.getReadableDatabase();
        Cliente cli=null;
        Cursor cursor = db.rawQuery("Select * FROM  cliente  WHERE correo = ? and password = ?",args);
         if (cursor.moveToFirst()){

             int id_cliente=Integer.parseInt(cursor.getString(0));
             String ci_cliente=cursor.getString(1);
             String apellido =cursor.getString(2);
             String nombre =cursor.getString(3);
             String correo = cursor.getString(4);
             int telefono = Integer.parseInt(cursor.getString(5));
             String password = cursor.getString(6);

             cli=new Cliente(id_cliente,ci_cliente,apellido,nombre,correo,telefono,password);
        }
        cursor.close();

        return  cli;
    }

    public Cliente buscarcorreocliente(String cor)
    {

        String args[] = new String[]{cor};
        SQLiteDatabase db = this.getReadableDatabase();
        Cliente cli=null;
        Cursor cursor = db.rawQuery("Select * FROM  cliente  WHERE correo = ?",args);
        if (cursor.moveToFirst()){

            int id_cliente=Integer.parseInt(cursor.getString(0));
            String ci_cliente=cursor.getString(1);
            String apellido =cursor.getString(2);
            String nombre =cursor.getString(3);
            String correo = cursor.getString(4);
            int telefono = Integer.parseInt(cursor.getString(5));
            String password = cursor.getString(6);

            cli=new Cliente(id_cliente,ci_cliente,apellido,nombre,correo,telefono,password);
        }
        cursor.close();

        return  cli;
    }

    public Administrador loginadmi(String cor,String pas)
    {

        String args[] = new String[]{cor, pas};
        SQLiteDatabase db = this.getReadableDatabase();
        Administrador admi=null;
        Cursor cursor = db.rawQuery("Select * FROM  administrador  WHERE correo = ? and password = ?",args);
        if (cursor.moveToFirst()){

            int id_admi=Integer.parseInt(cursor.getString(0));
            String ci_admi=cursor.getString(1);
            String apellido_paterno =cursor.getString(2);
            String apellido_materno =cursor.getString(3);
            String nombre = cursor.getString(4);
            String direccion = cursor.getString(5);
            String correo = cursor.getString(6);
            String password = cursor.getString(7);

            admi=new Administrador(id_admi,ci_admi,apellido_paterno,apellido_materno,nombre,direccion,correo,password);
        }
        cursor.close();

        return  admi;
    }

    public Administrador buscarcoradmi(String  corre)
    {

        String args[] = new String[]{corre};
        SQLiteDatabase db = this.getReadableDatabase();
        Administrador admi=null;
        Cursor cursor = db.rawQuery("Select * FROM  administrador  WHERE correo = ?",args);
        if (cursor.moveToFirst()){

            int id_admi=Integer.parseInt(cursor.getString(0));
            String ci_admi=cursor.getString(1);
            String apellido_paterno =cursor.getString(2);
            String apellido_materno =cursor.getString(3);
            String nombre = cursor.getString(4);
            String direccion = cursor.getString(5);
            String correo = cursor.getString(6);
            String password = cursor.getString(7);

            admi=new Administrador(id_admi,ci_admi,apellido_paterno,apellido_materno,nombre,direccion,correo,password);
        }
        cursor.close();

        return  admi;
    }

    public List<Producto> listProducts(){
        String sql = "select * from producto";
        SQLiteDatabase db = this.getReadableDatabase();
        List<Producto> storeProducts = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String nombre = cursor.getString(1);
                String descripcion = cursor.getString(2);
                float precioventa = Float.parseFloat(cursor.getString(3));
                int stock = Integer.parseInt(cursor.getString(4));
                byte[] image=cursor.getBlob(5);

                storeProducts.add(new Producto(id, nombre, descripcion,precioventa,stock,image));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeProducts;
    }

    public List<Producto> listProdVenta(){
        String sql = "select * from producto";
        SQLiteDatabase db = this.getReadableDatabase();
        List<Producto> storeProducts = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String nombre = cursor.getString(1);
                String descripcion = cursor.getString(2);
                float precioventa = Float.parseFloat(cursor.getString(3));
                int stock = Integer.parseInt(cursor.getString(4));
                byte[] imagen=cursor.getBlob(5);

                storeProducts.add(new Producto(nombre, descripcion,precioventa,stock,imagen));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeProducts;
    }

    public Producto buscaNombreProd(String nombreBuscado){
        String args[] = new String[]{ nombreBuscado};
        SQLiteDatabase db = this.getReadableDatabase();
        Producto mProduct = null;
        Cursor cursor = db.rawQuery("Select * FROM  producto  WHERE nombre_producto = ?",    args);
        if (cursor.moveToFirst()){
            int id = Integer.parseInt(cursor.getString(0));
            String nombre = cursor.getString(1);
            String detalle = cursor.getString(2);
            float precio =Float.parseFloat(cursor.getString(3));
            int stock = Integer.parseInt(cursor.getString(4));
            byte[] imagen=cursor.getBlob(5);
            mProduct = new Producto(id, nombre, detalle, precio,stock,imagen);
        }
        cursor.close();
        return mProduct;
    }


/*
    public Producto buscaIdProd(int  item){

        String args[] = new String[]{ String.valueOf(item)};
        SQLiteDatabase db = this.getReadableDatabase();
        Producto mProduct = null;
        Cursor cursor = db.rawQuery("Select * FROM  producto  WHERE item_producto = "+item,    null);
        if (cursor.moveToFirst()){
            int id = Integer.parseInt(cursor.getString(0));
            String nombre = cursor.getString(1);
            String detalle = cursor.getString(2);
            float precio =Float.parseFloat(cursor.getString(4));
            int stock = Integer.parseInt(cursor.getString(5));
            String imagen=cursor.getString(6);

            mProduct = new Producto(id, nombre, detalle, precio,stock,imagen);
        }
        cursor.close();
        return mProduct;
    }*/

    public List<Cliente> listClientes(){
        String sql = "select * from cliente";
        SQLiteDatabase db = this.getReadableDatabase();
        List<Cliente> storeProducts = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id_cliente=Integer.parseInt(cursor.getString(0));
                String ci_cliente=cursor.getString(1);
                String apellido=cursor.getString(2);
                String nombre=cursor.getString(3);
                String correo=cursor.getString(4);
                int telefono=Integer.parseInt(cursor.getString(5));
                String password=cursor.getString(6);

                storeProducts.add(new Cliente(id_cliente, ci_cliente,apellido,nombre, correo,telefono,password));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeProducts;
    }

    public List<Venta> listVenta(){
        String sql = "select * from  venta";
        SQLiteDatabase db = this.getReadableDatabase();
        List<Venta> storeVenta = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id_venta=Integer.parseInt(cursor.getString(0));
                String fecha=cursor.getString(1);
                float total=Float.parseFloat(cursor.getString(2));
                int id_cliente= Integer.parseInt(cursor.getString(3));
                int tiempo= Integer.parseInt(cursor.getString(4));


                storeVenta.add(new Venta(id_venta, fecha,total,id_cliente,tiempo));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeVenta;
    }


    public List<Compras> listComprasADmi(){
        String sql = "select * from compra";
        SQLiteDatabase db = this.getReadableDatabase();
        List<Compras> storeCompras = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id_compra = Integer.parseInt(cursor.getString(0));
                String nombre_producto = cursor.getString(1);
                int cantidad = Integer.parseInt(cursor.getString(2));
                double precio_compra = Double.parseDouble(cursor.getString(3));
                double costo_total_compra = Double.parseDouble(cursor.getString(4));

                storeCompras.add(new Compras(id_compra, nombre_producto,cantidad,precio_compra,costo_total_compra));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeCompras;
    }
}
