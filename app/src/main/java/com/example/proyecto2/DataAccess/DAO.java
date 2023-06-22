package com.example.proyecto2.DataAccess;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.proyecto2.Models.Juego;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DAO {

    private static DatabaseReference userRef;

    public DAO() {
        userRef = FirebaseDatabase.getInstance().getReference().child("Puntaje");
    }

    public void addScoreJuego(Juego juego) {
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Juego bestScore = snapshot.getValue(Juego.class);
                    if (juego.getScore() > bestScore.getScore()) {
                        //Insercion
                        userRef.setValue(new Juego(juego.getScore(), juego.getNombreJugardor()));
                    }
                } else {
                    userRef.setValue(new Juego(juego.getScore(), juego.getNombreJugardor()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void getScoreJuego(TextView textViewScore) {
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Juego juegoAux = snapshot.getValue(Juego.class);
                    textViewScore.setText("Record: " + juegoAux.getScore() + " de " + juegoAux.getNombreJugardor());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
