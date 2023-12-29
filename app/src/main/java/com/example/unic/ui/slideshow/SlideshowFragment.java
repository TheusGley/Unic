package com.example.unic.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unic.ClickListener;
import com.example.unic.R;
import com.example.unic.activity.Tela_aula;
import com.example.unic.adapter.AdapterAula;
import com.example.unic.databinding.FragmentSlideshowBinding;
import com.example.unic.model.Aulas;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment {

    private String  idDocumento;


    private RecyclerView recyclerView;
    public static List<Aulas> historicoList = new ArrayList<>();

    private FragmentSlideshowBinding binding;
    private TextView textUsu , textEMAIL;
    private Button btn_deslogar;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel SlideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        recyclerView = root.findViewById(R.id.recyclerView);

        AdapterAula adapter = new AdapterAula(historicoList);
        adapter.notifyDataSetChanged();

        DataSource dataSource =  new DataSource();
        // Chamar o método para buscar dados e atualizar a lista
        dataSource.fetchDataAndUpdateList(adapter);


        //evento de click.

        recyclerView.addOnItemTouchListener(
                new ClickListener(
                        getContext(),
                        recyclerView,
                        new ClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {


                                    }
                                }, 1000);

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
//        this.ListarAulas();

        //configurar adapter


        //configurar recycler view
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);//criar um gerenciador de layout
        recyclerView.setHasFixedSize(true);//fixar o tamanho do layoult
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));//adcionar diviso
        recyclerView.setAdapter(adapter);//escolher adaptador

        return root;
    }


    public class DataSource {

        private static final String TAG = "DataSource";

        private FirebaseFirestore db = FirebaseFirestore.getInstance();
        private CollectionReference historicoCollection = db.collection("Historico");

        // Método para buscar dados do Firestore e atualizar a lista
        public void fetchDataAndUpdateList(AdapterAula adapter) {
            historicoCollection.get().addOnSuccessListener(querySnapshot -> {
                if (querySnapshot.isEmpty()) {

                    Log.d("colecaoVazia", "Nao ha registros ");

                } else {

                    historicoList.clear();

                    // Iterar pelos documentos, supondo que seja uma lista de aulas
                    for (QueryDocumentSnapshot aulaSnapshot : querySnapshot) {
                        // Extrair dados do documento
                        String materia = aulaSnapshot.getString("materia");
                        String data = aulaSnapshot.getString("data");
                        String horas = aulaSnapshot.getString("horas");
                        String sala = aulaSnapshot.getString("sala");
                        idDocumento = aulaSnapshot.getId();
                        Log.d(TAG, "Dados do Firestore: materia=" + materia + ", data=" + data + ", hora=" + horas + ", sala=" + sala);


                        // Criar uma instância de Aulas e adicioná-la à lista
                        Aulas aulas = new Aulas(materia, data, horas, sala, idDocumento);
                        historicoList.add(aulas);

                        Log.d(TAG, "Aula adicionada: " + aulas.getMateria());
                    }

                    // Imprimir a lista no log
                    for (Aulas aula : historicoList) {
                        Log.d(TAG, "Aula na lista: " + aula.getMateria());
                    }


                    // Notificar que os dados foram atualizados (você pode usar um listener, EventBus, etc.)
                    adapter.notifyDataSetChanged();
                }
            }).addOnFailureListener(e -> {
                // Lidar com erros de leitura
                Log.w(TAG, "Erro ao ler documentos", e);
            });
        }


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
