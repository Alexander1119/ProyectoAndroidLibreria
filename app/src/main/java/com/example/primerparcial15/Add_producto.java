package com.example.primerparcial15;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.zip.Inflater;

public class Add_producto extends AppCompatActivity {

    EditText etnom,etder,etpre,etcan;
    Button btntf,btnei,btncancelar,btnguardar;

    ImageView imageView;
    AdminDataBase adminDataBase;
    private int GALLERY = 1, CAMERA = 2;
    private static final String IMAGE_DIRECTORY = "/byjta";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_producto);
        adminDataBase = new AdminDataBase(this,"database",null,1);

        etnom=(EditText)findViewById(R.id.nom);
        etder=(EditText)findViewById(R.id.det);
        etpre=(EditText)findViewById(R.id.pv);
        etcan=(EditText)findViewById(R.id.can);


        //Elegir imagen de galeria
        btnei=(Button)findViewById(R.id.idEleIma);
        btnei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePhotoFromGallary();
            }
        });

        btntf=(Button)findViewById(R.id.idTomFot);
        btntf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhotoFromCamera();
            }
        });

        //Guardar los datos en la base de datos
        btnguardar=(Button)findViewById(R.id.Bguardar);
        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    String nom=etnom.getText().toString();
                    String der=etder.getText().toString();
                    float pre=Float.parseFloat(etpre.getText().toString());
                    int can=Integer.parseInt(etcan.getText().toString());
                        if(TextUtils.isEmpty(nom) || TextUtils.isEmpty(der) || TextUtils.isEmpty(etpre.getText().toString()) || TextUtils.isEmpty(etcan.getText().toString()) )    {
                            Toast.makeText(getApplicationContext(),"No debe haber campos vacios",Toast.LENGTH_LONG).show();
                        }else {
                            Producto res=adminDataBase.buscaNombreProd(nom);
                            if(res==null){
                                Producto producto = new Producto(nom, der, pre, can, imageViewToByte(imageView));
                                adminDataBase.addProduct(producto);
                                Toast.makeText(getApplicationContext(), "byte "+producto.getImagen(), Toast.LENGTH_LONG).show();
                                etnom.setText("");
                                etder.setText("");
                                etpre.setText("");
                                etcan.setText("");

                                Intent h= new Intent(Add_producto.this,ListaProductos.class);
                                startActivity(h);
                            }else{
                                Toast.makeText(getApplicationContext(),"El producto ya existe",Toast.LENGTH_LONG).show();
                            }
                                     }
                    }catch (Exception e){
                    e.printStackTrace();
                }




            }
        });
        btncancelar=(Button)findViewById(R.id.Bcancelar);
        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Proceso cancelado",Toast.LENGTH_LONG).show();
                Intent invo=new Intent(getApplicationContext(),ListaProductos.class);
                startActivity(invo);

            }
        });
        imageView=(ImageView)findViewById(R.id.imageView);
    }
    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    Toast.makeText(Add_producto.this, "Imagen salvada!", Toast.LENGTH_SHORT).show();
                    imageView.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(Add_producto.this, "Fracaso!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(thumbnail);
            saveImage(thumbnail);
            Toast.makeText(Add_producto.this, "Imagen salvada!", Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("JTA", "Archivo salvado " + f.getAbsolutePath());
            Toast.makeText(getApplicationContext(),"Imagen es "+f.getAbsolutePath(),Toast.LENGTH_LONG).show();
            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }





    private byte[] imageViewToByte(ImageView imageView) {

        Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream= new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);


        byte[] bytearray=stream.toByteArray();
        return bytearray;
    }
}
