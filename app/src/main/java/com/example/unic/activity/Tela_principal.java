package com.example.unic.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.unic.ClickListener;
import com.example.unic.R;
import com.example.unic.adapter.Adapter;
import com.example.unic.model.Aulas;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Tela_principal extends AppCompatActivity {

    private TextView nomeUsuario, senhaUsuario;
    private Button bt_deslogar, check;
    private Calendar calendar;
    private int anoH, mesH, diaH;
    private String[] mensagens = {"Esta aula ainda nao começou",};


    private Date diaAula;
    private List<Aulas> dataList;
    private RecyclerView recyclerView;
    private static List<Aulas> aulasList = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        recyclerView = findViewById(R.id.recyclerView);


        //evento de click
        recyclerView.addOnItemTouchListener(
                new ClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new ClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                         Tela_aula(position);

                                    }
                                },3000) ;

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )

        );

        //Listagem de Aulas
        this.criarAulas();

        //configurar adapter
        Adapter adapter = new Adapter(aulasList);


        //configurar recycler view
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);//criar um gerenciador de layout
        recyclerView.setHasFixedSize(true);//fixar o tamanho do layoult
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));//adcionar diviso
        recyclerView.setAdapter(adapter);//escolher adaptador

            }


    //criar aulas
    public void criarAulas() {
        Aulas aulas = new Aulas("Desenvolvimento mobile", "12/09/23", "20:00", "bloco c 102");
        this.aulasList.add(aulas);

        aulas = new Aulas("Projeto Integrado ", "13/09/23", "20:00", "bloco c 102");
        this.aulasList.add(aulas);

        aulas = new Aulas("Computação em nuvem", "14/09/23", "20:00", "bloco a 104");
        this.aulasList.add(aulas);

        aulas = new Aulas("Projeto integrado", "16/09/23", "21:00", "bloco c 102");
        this.aulasList.add(aulas);

        aulas = new Aulas("Linguagem de programação", "20/09/23", "20:00", "bloco c 102");
        this.aulasList.add(aulas);


    }
    public static List<Aulas> getListaAulas() {
        return aulasList;
    }
    private void Tela_aula(int position){
        Intent intent = new Intent(Tela_principal.this, Tela_aula.class);
        Intent posicao = new Intent(Tela_principal.this, Tela_aula.class);
        intent.putExtra("position", posicao);
        startActivity(intent);
        finish();
    }
}






