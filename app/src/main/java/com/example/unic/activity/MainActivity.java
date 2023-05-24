package com.example.unic.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.unic.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private EditText EditEmail,  Edit_senha;
    private Button bt_entrar;
    private ProgressBar progressBar;
    private String[] mensagens = {"Preencha todos os campos", "Login Realizado com Sucesso" };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IniciarComponentes();


        bt_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = EditEmail.getText().toString();
                String senha = Edit_senha.getText().toString();

                if (email.isEmpty() || senha.isEmpty()) {

                    Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                }
                else {
                    Autenticar_usuario(v);
                }
            }
        });
    }

        private void Autenticar_usuario(View v){
            String email = EditEmail.getText().toString();
            String senha = Edit_senha.getText().toString();
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){
                        progressBar.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Tela_principal();

                            }
                        },3000) ;
                    }else {
                        String erro;
                        try {
                            throw task.getException();
                        }
                        catch (Exception e){

                            erro = "Erro ao logar usuario";

                        }
                        Snackbar snackbar = Snackbar.make(v, erro, Snackbar.LENGTH_SHORT);
                        snackbar.setBackgroundTint(Color.WHITE);
                        snackbar.setTextColor(Color.BLACK);
                        snackbar.show();

                    }
                }
            });
        }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser  usuario_atual = FirebaseAuth.getInstance().getCurrentUser();
        if(usuario_atual != null){
            Tela_principal();
        }
       
    }

    private void Tela_principal(){
        Intent intent = new Intent(MainActivity.this, Tela_principal.class);
        startActivity(intent);
        finish();
        }
        private void IniciarComponentes(){
            EditEmail = findViewById(R.id.EditEmail);
            Edit_senha = findViewById(R.id.Edit_senha);
            bt_entrar  = findViewById(R.id.bt_entrar);
            progressBar = findViewById(R.id.progressBar);


        }
    }


