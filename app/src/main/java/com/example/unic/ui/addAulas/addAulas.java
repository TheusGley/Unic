package com.example.unic.ui.addAulas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.unic.R;
import com.example.unic.activity.Tela_principal;
import com.example.unic.databinding.FragmentAddAulasBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class addAulas extends Fragment {

    private FragmentAddAulasBinding binding;
    private EditText editMateria, editHorario, editData, editSala;
    private String strMateria, strHorario,strData, strSala;
    private Button enviar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        addAulasViewModel  addAulasViewModel =
                new ViewModelProvider(this).get(addAulasViewModel.class);

        binding = FragmentAddAulasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        editMateria =  root.findViewById(R.id.editNomeMateria);
        editHorario = root.findViewById(R.id.editHorario);
        editData = root.findViewById(R.id.editData);
        editSala = root.findViewById(R.id.editSala);
        enviar = root.findViewById(R.id.enviarAulas);



        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                strMateria = editMateria.getText().toString();
                strHorario = editHorario.getText().toString();
                strSala = editSala.getText().toString();
                strData =  editData.getText().toString();


                adicionarAulas(strMateria,strMateria,strHorario,strData,strSala);


            }
        });




        return root;



    }


    public void adicionarAulas (String idDocumento,  String materia , String horario, String data, String sala ){


        FirebaseFirestore bd =  FirebaseFirestore.getInstance();
        Map<String, Object> dados = new HashMap<>();
            dados.put("materia",materia);
            dados.put("horas",horario);
            dados.put("data",data);
            dados.put("sala",sala);


        bd.collection("Aulas").document(materia).set(dados)
                .addOnSuccessListener(aVoid -> {
                    Log.d("Firestore", "Documento adicionado com sucesso! Matéria: " + materia);
                })
                .addOnFailureListener(e -> {
                    Log.e("Firestore", "Erro ao adicionar o documento", e);
                });

        Context context = requireActivity();
        Intent intent = new Intent(context, Tela_principal.class);
        startActivity(intent);



    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


 }
