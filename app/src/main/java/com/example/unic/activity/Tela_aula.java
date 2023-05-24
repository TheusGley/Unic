package com.example.unic.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.unic.R;
import com.example.unic.model.Aulas;
import com.example.unic.ui.home.HomeFragment;

import java.util.List;

public class Tela_aula extends AppCompatActivity {

    private TextView tituloAula, dataAula, horarioAula, salaA;
    private List<Aulas> listaAulas;

    private int position;
    private Button btn_presenca;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_aula);


        position = getIntent().getIntExtra("position", 0);

        tituloAula = findViewById(R.id.textTitulo);
        dataAula = findViewById(R.id.textDataA);
        horarioAula = findViewById(R.id.textHora);
        salaA = findViewById(R.id.textSalaA);
        btn_presenca = findViewById(R.id.presenca);

// Obtenha a lista de aulas diretamente do objeto HomeFragment
        List<Aulas> listaAulas = HomeFragment.aulasList;

// Verifique se a posição está dentro dos limites da lista
        if (position >= 0 && position < listaAulas.size()) {
            Aulas aula = listaAulas.get(position);

            String materia = aula.getMateria();
            String data = aula.getData();
            String horario = aula.getHorario();
            String sala = aula.getSala();

            // Use os valores obtidos conforme necessário
            tituloAula.setText(materia);
            dataAula.setText(data);
            horarioAula.setText(horario);
            salaA.setText(sala);

        }
    }
}
//    public void registrar(){
//
//
//    }




