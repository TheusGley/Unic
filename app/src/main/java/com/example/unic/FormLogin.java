package com.example.unic;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.material.snackbar.Snackbar;

public class FormLogin extends AppCompatActivity {
    private EditText edit_email,  edit_senha;
    private Button bt_entrar;
    private ProgressBar progressBar;
    private String[] mensagens = {"Preencha todos os campos", "Login Realizado com Sucesso" };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);
        getSupportActionBar().hide();

        bt_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= edit_email.getText().toString();
                String senha = edit_senha.getText().toString();

                if (email.isEmpty() ||  senha.isEmpty()){

                    Snackbar snackbar = Snackbar.make(v,mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                }
            }
        });
    }
    private void IniciarComponentes(){
        edit_email = findViewById(R.id.EditEmail);
        edit_senha = findViewById(R.id.Edit_senha);
        bt_entrar  = findViewById(R.id.bt_entrar);
        progressBar = findViewById(R.id.progressBar);


    }
}