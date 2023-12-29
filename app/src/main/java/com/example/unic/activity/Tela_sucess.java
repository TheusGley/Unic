package com.example.unic.activity;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.unic.ClickListener;
import com.example.unic.R;
import com.example.unic.adapter.AdapterAula;
import com.example.unic.model.Aulas;
import com.example.unic.ui.home.HomeFragment;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tela_sucess extends AppCompatActivity {

    private int position;
    private List<Aulas> listaAulas;
    private String idDocumento,materia,horario,data,sala;
    private Button voltar ;
    private TextView text_success ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_succes);

        List<Aulas> listaAulas = HomeFragment.aulasList;

        voltar  = findViewById(R.id.voltar_b);
        text_success = findViewById(R.id.text_confirmado);

        position = getIntent().getIntExtra("position", 0);
        Aulas aula = listaAulas.get(position);
        materia = aula.getMateria();
        data = aula.getData();
        horario = aula.getHoras();
        sala = aula.getSala();
        idDocumento = aula.getIdDocumento();

        Text();

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historico(idDocumento,materia,horario,data,sala);
            }
        });





    }

    private void Text (){

        long currentTimeMillis = System.currentTimeMillis();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTimeMillis);

        // Obtendo o ano, mês e dia
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH) + 1; // Adicionando 1 ao mês, pois ele é baseado em zero
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        // Obtendo a hora e o minuto
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        text_success.setText("Presença confirmada ás " + hour +":"+ minute + " na Data de "+ dia +"/"+ mes +"/" +ano );
    }



    public void historico(String idDocumento, String materia, String horario, String data, String sala) {

        FirebaseFirestore bd = FirebaseFirestore.getInstance();
        Map<String, Object> dados = new HashMap<>();
        dados.put("materia", materia);
        dados.put("horas", horario);
        dados.put("data", data);
        dados.put("sala", sala);

        bd.collection("Historico").document(materia).set(dados)
                .addOnSuccessListener(aVoid -> {
                    Log.d("Firestore", "Documento adicionado com sucesso! Matéria: " + materia);
                    dbDelete(idDocumento);

                })
                .addOnFailureListener(e -> {
                    Log.e("Firestore", "Erro ao adicionar o documento", e);
                });



    }


    public void dbDelete (String idDocumento){
        FirebaseFirestore bd_delete = FirebaseFirestore.getInstance();
        bd_delete.collection("Aulas").document(idDocumento).delete().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d("db_delete", "Sucesso ao excluir os dados");
                listaAulas.remove(idDocumento);

                // Use a referência correta para a atividade
                Intent intent = new Intent(Tela_sucess.this, Tela_principal.class);
                startActivity(intent);
            } else {
                Log.e("db_delete", "Erro ao excluir os dados", task.getException());
            }
        });

    }


}





