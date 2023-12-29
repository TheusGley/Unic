package com.example.unic.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.unic.R;
import com.example.unic.activity.Tela_principal;
import com.example.unic.model.Aulas;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AdapterAula extends RecyclerView.Adapter<AdapterAula.MyViewHolder> {
    private List<Aulas> listaAulas;

    public AdapterAula(List<Aulas> lista) {
        this.listaAulas = lista;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_lista, parent, false);
        return new MyViewHolder(itemLista);
    }


    @Override
    public void onBindViewHolder (@NonNull MyViewHolder holder,int position){
        Aulas aulas = listaAulas.get(position);
        holder.nomeAula.setText(aulas.getMateria());
        holder.horario.setText(aulas.getHoras());
        holder.sala.setText(aulas.getSala());
        holder.data.setText(aulas.getData());
    }

    @Override
    public int getItemCount () {
        return listaAulas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nomeAula, data, horario, sala;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            data = itemView.findViewById(R.id.textData);
            horario = itemView.findViewById(R.id.textHorario);
            nomeAula = itemView.findViewById(R.id.textAula);
            sala = itemView.findViewById(R.id.textSala);
        }
    }

    public void adicionarAula(Aulas aula) {
        listaAulas.add(aula);

        notifyDataSetChanged();
    }






}
