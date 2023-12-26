package com.example.unic.ui.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.unic.R;
import com.example.unic.databinding.FragmentCalendarBinding;

import org.checkerframework.checker.nullness.qual.NonNull;


public class Calendar_fragment extends Fragment {

    private FragmentCalendarBinding binding;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Calendar_viewModel Calendar_viewModel =
                new ViewModelProvider(this).get(Calendar_viewModel.class);

        binding = FragmentCalendarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        CalendarView calendarView = root.findViewById(R.id.calendarView);

        // Configurar um listener para lidar com alterações na data selecionada
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Lidar com a data selecionada
                String dataSelecionada = year + "/" + (month + 1) + "/" + dayOfMonth;
                Toast.makeText(requireContext(), "Data selecionada: " + dataSelecionada, Toast.LENGTH_SHORT).show();
            }
        });


        return root ;







        }

}
