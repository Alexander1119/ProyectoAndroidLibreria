package com.example.primerparcial15;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorCliente extends BaseAdapter {

    Context context;
    List<Cliente> list;

    public AdaptadorCliente(Context context, List<Cliente> list) {
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
        Cliente item=(Cliente) getItem(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.layout_vista_listacliente,null);

        TextView tvno=(TextView)convertView.findViewById(R.id.nomcli);
        TextView tvap=(TextView)convertView.findViewById(R.id.apecli);
        TextView tvco=(TextView)convertView.findViewById(R.id.corcli);
        TextView tvte=(TextView)convertView.findViewById(R.id.telcli);

        tvno.setText(item.getNombre());
        tvap.setText(item.getApellido());
        tvco.setText((item.getCorreo()));
        tvte.setText(String.valueOf(item.getTelefono()));

        return convertView;
    }
}