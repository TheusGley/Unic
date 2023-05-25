package com.example.unic.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import com.example.unic.activity.Tela_principal;
import com.example.unic.adapter.Adapter;
import com.example.unic.databinding.FragmentHomeBinding;
import com.example.unic.model.Aulas;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeFragment extends Fragment {

    private TextView nomeUsuario, senhaUsuario;
    private Button bt_deslogar, check;
    private Calendar calendar;
    private int anoH, mesH, diaH;
    private String[] mensagens = {"Esta aula ainda nao começou",};


    private Date diaAula;
    private List<Aulas> dataList;
    private RecyclerView recyclerView;
    public static List<Aulas> aulasList = new ArrayList<>();

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.recyclerView);
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
                                        Tela_aula(position);

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
        this.criarAulas();

        //configurar adapter
        Adapter adapter = new Adapter(aulasList);


        //configurar recycler view
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);//criar um gerenciador de layout
        recyclerView.setHasFixedSize(true);//fixar o tamanho do layoult
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));//adcionar diviso
        recyclerView.setAdapter(adapter);//escolher adaptador

        return root;
    }
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

    private void Tela_aula(int position) {
        Intent intent = new Intent(getActivity(), Tela_aula.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}