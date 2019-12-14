package com.example.primerparcial15;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class AdaptadorCompraAdmi extends BaseAdapter {

    Context context;
    List<Compras> list;

    public AdaptadorCompraAdmi(Context context, List<Compras> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Compras item=(Compras) getItem(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.layout_vista_listacomprasadmi,null);

        TextView tvno=(TextView)convertView.findViewById(R.id.idnompro);
        TextView tvde=(TextView)convertView.findViewById(R.id.idcancom);
        TextView tvpv=(TextView)convertView.findViewById(R.id.idprecom);
        TextView tvca=(TextView)convertView.findViewById(R.id.idTotcom);

        tvno.setText(item.getNombre_producto());
        tvde.setText(String.valueOf(item.getCantidad()));
        tvpv.setText(String.valueOf(item.getPrecio_compra()));

        tvca.setText(String.valueOf(item.getCosto_total()));

        return convertView;
    }
}