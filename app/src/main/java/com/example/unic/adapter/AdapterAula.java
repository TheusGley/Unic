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

    public void excluirAula(String idDocumento, int position, Context context) {
        listaAulas.remove(position);
        deleteBd(idDocumento);
        notifyDataSetChanged();

        new Handler().postDelayed(() -> {
            // Iniciar a atividade desejada (tela inicial)
            Intent intent = new Intent(context, Tela_principal.class);
            context.startActivity(intent);
        }, 200); // Ajuste o valor do atraso conforme necessário
    }


    public void deleteBd(String idDocumento) {


            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("Aulas").document(idDocumento).delete().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Log.d("db_delete", "Sucesso ao excluir os dados");
                } else {
                    Log.e("db_delete", "Erro ao excluir os dados", task.getException());
                }
            });



    }

    public void historico(String idDocumento, int position, Context context){

        Log.d("db_delete", "Sucesso ao excluir os dados");

        Aulas aulas = listaAulas.get(position);
        String nomeAula = aulas.getMateria();
        String  horario = aulas.getHoras();
        String  sala = aulas.getSala();
        String  data = aulas.getData();

        Log.e("historico", "" +nomeAula + horario+sala+data);

        FirebaseFirestore bd = FirebaseFirestore.getInstance();

        Map <String,String> aulasMap = new HashMap<>() ;
                aulasMap.put("materia",nomeAula);
                aulasMap.put("data",data);
                aulasMap.put("horas",horario);
                aulasMap.put("sala",sala);


       // Map<String,Object> historicoMap = new HashMap<>();
         //       historicoMap.put("materia", nomeAula);
           //     historicoMap.put("data", data);
             //   historicoMap.put("horas", horario);
               // historicoMap.put("sala", sala);

        bd.collection("Historico").add(aulasMap).addOnSuccessListener(
                documentReference -> {
                    Log.d("historico", "Histórico adicionado com sucesso, ID do documento: " + documentReference.getId());
                })
                .addOnFailureListener(e -> {
                    Log.e("historico", "Erro ao adicionar o histórico", e);
                });
//        excluirAula(idDocumento,position ,context);
    }



}
