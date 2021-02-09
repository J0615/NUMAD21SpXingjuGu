package edu.neu.madcourse.numad21sp_xingjugu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class SecondFragment extends Fragment {

    private TextView response;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second,container,false);
        response = view.findViewById(R.id.pressed_response);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        view.findViewById(R.id.button_A).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                response.setText("You pressed: button A");
            }
        });


        view.findViewById(R.id.button_B).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                response.setText("You pressed: button B");
            }
        });

        view.findViewById(R.id.button_C).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                response.setText("You pressed: button C");
            }
        });

        view.findViewById(R.id.button_D).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                response.setText("You pressed: button D");
            }
        });

        view.findViewById(R.id.button_E).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                response.setText("You pressed: button E");
            }
        });

        view.findViewById(R.id.button_F).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                response.setText("You pressed: button F");;
            }
        });

    }
}