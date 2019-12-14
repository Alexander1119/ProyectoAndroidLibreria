package com.example.primerparcial15;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterListaVenta extends BaseAdapter {

    Context context;
    List<Venta> list;

    public AdapterListaVenta(Context context, List<Venta> list) {
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

        Venta item=(Venta) getItem(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.layout_vista_listaventa,null);

        TextView tvno=(TextView)convertView.findViewById(R.id.idVenta);
        TextView tvde=(TextView)convertView.findViewById(R.id.idfecha);
        TextView tvpv=(TextView)convertView.findViewById(R.id.idTot);
        TextView tvnom=(TextView)convertView.findViewById(R.id.idnombrecliente);
        TextView tvpago=(TextView)convertView.findViewById(R.id.tipopago);

        tvno.setText(String.valueOf(item.getId_venta()));
        tvde.setText((item.getFecha()));
        tvpv.setText(String.valueOf(item.getCantidadTotal()));
        tvnom.setText(String.valueOf(item.getId_cliente()));
        if(item.getTiempo()==0){
            tvpago.setText("Pago al contado ");

        }else{
            float totalmes=item.getCantidadTotal()/item.getTiempo();
            tvpago.setText("Pagar "+redondearDecimales(totalmes,2)+" en "+String.valueOf(item.getTiempo()));
        }
        return convertView;
    }





































    public static double redondearDecimales(double valorInicial, int numeroDecimales) {
        double parteEntera, resultado;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        resultado=(resultado-parteEntera)*Math.pow(10, numeroDecimales);
        resultado=Math.round(resultado);
        resultado=(resultado/Math.pow(10, numeroDecimales))+parteEntera;
        return resultado;
    }
}
