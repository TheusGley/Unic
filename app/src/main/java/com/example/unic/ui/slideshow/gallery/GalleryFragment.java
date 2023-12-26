package com.example.unic.ui.slideshow.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.unic.R;
import com.example.unic.activity.MainActivity;
import com.example.unic.databinding.FragmentGalleryBinding;
import com.google.firebase.auth.FirebaseAuth;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private TextView textUsu , textEMAIL;
    private Button btn_deslogar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        textUsu = root.findViewById(R.id.textusuario);
        textEMAIL = root.findViewById(R.id.textEMAIL);
        btn_deslogar = root.findViewById(R.id.deslogar);

        btn_deslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}