package com.example.unic.activity;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.unic.ClickListener;
import com.example.unic.R;
import com.example.unic.adapter.AdapterAula;
import com.example.unic.model.Aulas;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Tela_sucess extends AppCompatActivity {

    private int position;
    private String idDocumento;
    private Button voltar ;
    private TextView text_success ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_succes);
        voltar  = findViewById(R.id.voltar_b);
        text_success = findViewById(R.id.text_confirmado);

        position = getIntent().getIntExtra("position", 0);
        idDocumento = getIntent().getStringExtra("idDocumento");
        Text();

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmado();
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


    private void confirmado() {
        List<Aulas> aulasList = new ArrayList<>();
        AdapterAula adapter = new AdapterAula(aulasList);
        Context context = this;

        Intent intent = new Intent(this, Tela_principal.class);
        intent.putExtra("idDocumento", idDocumento);
        intent.putExtra("position", position);

        // Inicia a nova atividade após um pequeno atraso
        new Handler().postDelayed(() -> {
            startActivity(intent);

            // Chama o método historico após a nova atividade ser iniciada
            adapter.excluirAula(idDocumento, position, context);
        }, 200); //
    }



}