package com.example.unic.activity;

import static android.app.PendingIntent.getActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.location.FusedLocationProviderClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.unic.R;
import com.example.unic.model.Aulas;
import com.example.unic.model.Localizacao;
import com.example.unic.ui.home.HomeFragment;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.integrity.v;

import java.util.Calendar;
import java.util.List;

public class Tela_aula extends AppCompatActivity  {



    private TextView tituloAula, dataAula, horarioAula, salaA;
    private List<Aulas> listaAulas;
    private CheckBox checkBoxPosition;
    private int position;
    private String idDocumento;
    private Button btn_presenca;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Localizacao localizacao ;
    private  FusedLocationProviderClient fusedLocationClient ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_aula);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        boolean ativarCheckbox = getIntent().getBooleanExtra("ativarCheckbox", false);

        // Atualize o estado do checkbox conforme necessário


        position = getIntent().getIntExtra("position", 0);
        idDocumento = getIntent().getStringExtra("idDocumento");

        tituloAula = findViewById(R.id.textTitulo);
        dataAula = findViewById(R.id.textDataA);
        horarioAula = findViewById(R.id.textHora);
        salaA = findViewById(R.id.textSalaA);
        btn_presenca = findViewById(R.id.presenca);




        btn_presenca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startProcess();

            }
        });

// Obtenha a lista de aulas diretamente do objeto HomeFragment
                List<Aulas> listaAulas = HomeFragment.aulasList;

// Verifique se a posição está dentro dos limites da lista
                if (position >= 0 && position < listaAulas.size()) {
                    Aulas aula = listaAulas.get(position);

                    String materia = aula.getMateria();
                    String data = aula.getData();
                    String horario = aula.getHoras();
                    String sala = aula.getSala();

                    // Use os valores obtidos conforme necessário
                    tituloAula.setText(materia);
                    dataAula.setText(data);
                    horarioAula.setText(horario);
                    salaA.setText(sala);


                }
            }

    // Chame este método para iniciar o processo de captura de imagem
    private void startImageCaptureProcess() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Se a permissão não foi concedida, solicite-a
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_IMAGE_CAPTURE);
        } else {
            // Se a permissão foi concedida, inicie a câmera
            initiateImageCapture();
        }
    }

    // Este método inicia a câmera para captura de imagem
    private void initiateImageCapture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    // Este método é chamado quando a captura de imagem é concluída
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // A imagem foi capturada com sucesso, você pode lidar com ela aqui
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                String imagePath = saveImageLocally(imageBitmap);

                // Inicie a próxima atividade (Tela_sucess) passando o caminho da imagem como extra
                navigateToSuccessScreen(imagePath);
            }
        }
    }

    // Este método salva a imagem localmente e retorna o caminho da imagem
    private String saveImageLocally(Bitmap bitmap) {
        // Implemente a lógica para salvar a imagem localmente e retorne o caminho da imagem
        // Por exemplo, você pode salvar no armazenamento externo ou interno do dispositivo
        // Certifique-se de ter as permissões necessárias se estiver salvando no armazenamento externo
        // Retorna o caminho da imagem (pode ser o caminho absoluto ou relativo)
        return "caminho/da/imagem.jpg";
    }

    // Este método inicia a próxima tela (Tela_sucess) e encerra a atividade atual se necessário
    private void navigateToSuccessScreen(String imagePath) {
      getLocation();
    }

    // Chame este método para iniciar o processo
    private void startProcess() {
        startImageCaptureProcess();
    }



    private void getLocation() {


        // Obtenha a localização aqui
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Verifique as permissões antes de obter a localização
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            return;
        }


        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {


                long currentTimeMillis = System.currentTimeMillis();

                // Criar uma instância de Calendar e configurá-la com o tempo atual
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(currentTimeMillis);

                // Obter os valores de hora, minuto e segundo
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);

                // Exibir a hora exata
                String time = hour + ":" + minute + ":" + second;
                if (location != null) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();

                    // Faça o que desejar com a latitude e longitude obtidas
                    // Por exemplo, exiba em um Toast
                    String message = "Latitude: " + latitude + "\nLongitude: " + longitude;
                    Toast.makeText(Tela_aula.this, message, Toast.LENGTH_SHORT).show();
                    Toast.makeText(Tela_aula.this, "Registrado às " + time, Toast.LENGTH_SHORT).show();

                } else {
                    // A localização não está disponível
                    Toast.makeText(Tela_aula.this, "Localização não disponível", Toast.LENGTH_SHORT).show();


                }
                Tela_sucess(idDocumento,position);


            }
        });



    }

    private void Tela_sucess(String idDocumento,int position) {
            Intent intent = new Intent(this, Tela_sucess.class);
            intent.putExtra("position", position);
            intent.putExtra("idDocumento", idDocumento);

            startActivity(intent);
        }
    }















//
//    }




