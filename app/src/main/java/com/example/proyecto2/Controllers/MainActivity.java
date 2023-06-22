package com.example.proyecto2.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto2.DataAccess.Repository;
import com.example.proyecto2.Models.Juego;
import com.example.proyecto2.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    ImageView imageViewPersonaje;
    Button btnJugar;
    Juego juego;
    EditText editTextNombre;
    TextView textViewScore;
    MediaPlayer mp;
    int numAleatorio = (int) (Math.random() * 10);

    // private DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        juego = new Juego();
        imageViewPersonaje = findViewById(R.id.imageView_Personaje);
        btnJugar = findViewById(R.id.btn_Jugar);
        editTextNombre = findViewById(R.id.et_Nombre);
        textViewScore = findViewById(R.id.textView_BestScore);

        //userRef = FirebaseDatabase.getInstance().getReference().child("Puntaje");

        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_icono);

        mp = MediaPlayer.create(this, R.raw.alphabet_song);
        mp.start();
        mp.setLooping(true);

        Repository repo=new Repository();
        repo.get(textViewScore);

        int id;
        switch (numAleatorio) {
            case 0:
            case 10: {
                id = getResources().getIdentifier("mango", "drawable", getPackageName());
                imageViewPersonaje.setImageResource(id);
                break;
            }
            case 1:
            case 9: {
                id = getResources().getIdentifier("fresa", "drawable", getPackageName());
                imageViewPersonaje.setImageResource(id);
                break;
            }
            case 2:
            case 8: {
                id = getResources().getIdentifier("manzana", "drawable", getPackageName());
                imageViewPersonaje.setImageResource(id);
                break;
            }
            case 3:
            case 7: {
                id = getResources().getIdentifier("sandia", "drawable", getPackageName());
                imageViewPersonaje.setImageResource(id);
                break;
            }
            case 4:
            case 5:
            case 6: {
                id = getResources().getIdentifier("naranja", "drawable", getPackageName());
                imageViewPersonaje.setImageResource(id);
                break;
            }
        }
    }

    public void jugar(View view) {

        juego.setNombreJugardor(editTextNombre.getText().toString());
        if (!juego.getNombreJugardor().equals("")) {
            mp.stop();
            mp.release();
            Intent intent = new Intent(this, MainActivity2Nivel1.class);
            intent.putExtra("jugador", juego.getNombreJugardor());
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Debe ingresar su nombre", Toast.LENGTH_SHORT).show();
            editTextNombre.requestFocus();
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            inputMethodManager.showSoftInput(editTextNombre, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    @Override
    public void onBackPressed() {

    }
}