package com.example.primerparcial15;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdaptadorVenta extends BaseAdapter {

    Context context;
    List<Producto> lista;

    public AdaptadorVenta(Context context, List<Producto> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Producto item=(Producto)getItem(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.layout_vista_ventacliente,null);

        TextView tvno=(TextView)convertView.findViewById(R.id.producto);
        TextView tvde=(TextView)convertView.findViewById(R.id.Detalle);
        TextView tvpv=(TextView)convertView.findViewById(R.id.Precio);
        TextView tvca=(TextView)convertView.findViewById(R.id.Cantidad);
        ImageView imageView=(ImageView)convertView.findViewById(R.id.imageView3) ;

        tvno.setText(item.getNombre_producto_());
        tvde.setText(item.getDescripcion_producto());
        tvpv.setText(String.valueOf(item.getPrecio_venta()));
        tvca.setText(String.valueOf(item.getStock()));
        byte[] proimagen = item.getImagen();
        Bitmap bitmap = BitmapFactory.decodeByteArray(proimagen, 0, proimagen.length);
        imageView.setImageBitmap(bitmap);

        return convertView;
    }
}
