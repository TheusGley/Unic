package com.example.unic.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.unic.R;
import com.example.unic.model.Aulas;

import java.util.List;

public class Tela_aula extends AppCompatActivity {

    private TextView tituloAula, dataAula, horarioAula,salaA;
    private List<Aulas> listaAulas;

    private  int position;
    private Button btn_presenca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_aula);
        position = getIntent().getIntExtra("position", -1);



        iniciar_componentes();

    }
    private void iniciar_componentes(){

        tituloAula = findViewById(R.id.textTitulo);
        dataAula =findViewById(R.id.textDataA);
        horarioAula = findViewById(R.id.textHora);

        salaA = findViewById(R.id.textSala);
        btn_presenca = findViewById(R.id.presenca);

//

        Tela_principal tela_principal = new Tela_principal();
        List<Aulas> listaAulas = Tela_principal.getListaAulas();
        tituloAula.setText(listaAulas.());
//        dataAula.setText(aulas.getData());
//        horarioAula.setText(aulas.getHorario());
//        salaA.setText(aulas.getSala());
//
//
    }
//    public void registrar(){
//
//
    }




