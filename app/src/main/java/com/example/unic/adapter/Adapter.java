package com.example.unic.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unic.R;
import com.example.unic.model.Aulas;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyviewHolder> {
    public static List<Aulas> listaAulas;
    public Adapter(List<Aulas> lista ) {
        this.listaAulas = lista;

    }

    @NonNull
    @Override
    //criar visualizações
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // converter layoult do adapter para uam view
       View itemLista = LayoutInflater.from( parent.getContext()).inflate(R.layout.adapter_lista, parent , false);


        return new MyviewHolder(itemLista);
    }

    @Override
    //Exibir os elementos no activity
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        Aulas aulas = listaAulas.get(position);
        holder.nomeAula.setText(aulas.getMateria());
        holder.horario.setText(aulas.getHorario());
        holder.sala.setText(aulas.getSala());
        holder.data.setText(aulas.getData());



    }


    @Override
    //exibe a quantidade de item em listas

    public int getItemCount() {
        return listaAulas.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder{

        TextView nomeAula, data , horario, sala ;


        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            data = itemView.findViewById(R.id.textData);
            horario = itemView.findViewById(R.id.textHorario);
            nomeAula = itemView.findViewById(R.id.textAula);
            sala = itemView.findViewById(R.id.textSala);

        }
    }

}
