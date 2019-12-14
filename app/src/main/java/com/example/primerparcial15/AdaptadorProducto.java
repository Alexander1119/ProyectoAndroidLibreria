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
import java.util.zip.Inflater;

public class AdaptadorProducto extends BaseAdapter {
    Context context;
    List<Producto> lista;
    private int layout;

    public AdaptadorProducto(Context context, List<Producto> lista) {
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

    private class ViewHolder{
        TextView tvno,tvde,tvpv,tvca;
        ImageView imageView;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        /*
        View row=convertView;
        ViewHolder holder=new ViewHolder();
        if(row==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(layout,null);

            holder.imageView=(ImageView)row.findViewById(R.id.imageView);
            holder.tvno=(TextView)row.findViewById(R.id.nom);
            holder.tvde=(TextView)row.findViewById(R.id.det);
            holder.tvpv=(TextView)row.findViewById(R.id.pv);
            holder.tvca=(TextView)row.findViewById(R.id.can);
            row.setTag(holder);

        }else{

            holder=(ViewHolder)row.getTag();
        }
        Producto producto=lista.get(position);
        holder.tvno.setText(producto.getNombre_producto_());
        holder.tvde.setText(producto.getDescripcion_producto());
        holder.tvpv.setText(String.valueOf(producto.getPrecio_venta()));
        holder.tvca.setText(producto.getStock());


        byte[] imagpro=producto.getImagen();
        Bitmap bitmap= BitmapFactory.decodeByteArray(imagpro,0,imagpro.length);
        holder.imageView.setImageBitmap(bitmap);

*/


        Producto item=(Producto)getItem(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.layout_vista_listaproducto,null);

        TextView tvno=(TextView)convertView.findViewById(R.id.nombre);
        TextView tvde=(TextView)convertView.findViewById(R.id.descripcion);
        TextView tvpv=(TextView)convertView.findViewById(R.id.precio);
        TextView tvca=(TextView)convertView.findViewById(R.id.cantidad);

        ImageView imageView3=(ImageView)convertView.findViewById(R.id.imageView3);

        tvno.setText(item.getNombre_producto_());
        tvde.setText(item.getDescripcion_producto());
        tvpv.setText(String.valueOf(item.getPrecio_venta()));
        tvca.setText(String.valueOf(item.getStock()));
        byte[] proimagen = item.getImagen();
        Bitmap bitmap = BitmapFactory.decodeByteArray(proimagen, 0, proimagen.length);
        imageView3.setImageBitmap(bitmap);


        return convertView;

    }
}
