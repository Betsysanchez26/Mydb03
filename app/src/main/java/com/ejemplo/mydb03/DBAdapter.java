package com.ejemplo.mydb03;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by BetsySanchez on 05/03/2018.
 */

public class DBAdapter extends RecyclerView.Adapter<DBAdapter.RecyclerViewHolder> {
    private String data[][];

    public DBAdapter(String[][] data) {
        this.data = data;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.clave.setText(data[position][1]);
        holder.nombre.setText(data[position][2]);
        holder.salario.setText(data[position][3]);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView nombre,salario,clave;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            clave=itemView.findViewById(R.id.clave);
            nombre=itemView.findViewById(R.id.nombre);
            salario=itemView.findViewById(R.id.salario);
        }
    }
}
