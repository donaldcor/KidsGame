package com.example.proyecto2.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto2.DataAccess.Repository;
import com.example.proyecto2.Models.Juego;
import com.example.proyecto2.R;

public class MainActivity2Nivel4 extends AppCompatActivity {
    TextView tv_nombre, tv_score;
    ImageView iv_imgUno, iv_imgDos, iv_vidas, iv_signoOperacion;
    EditText et_respuesta;
    MediaPlayer mp, mpGreat, mpBad;

    int numAleatorio_uno, numAleatorio_dos, resultado, vidas = 3;
    public Juego juego;

    String string_score, string_vidas;


    String numero[] = {"cero", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve"};


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_nivel4);

        Toast.makeText(this, "Nivel 4 - Sumas y Restas", Toast.LENGTH_SHORT).show();
        juego = new Juego();
        tv_nombre = findViewById(R.id.textViewNombre);
        tv_score = findViewById(R.id.textViewScore);
        iv_vidas = findViewById(R.id.imageViewVidas);
        iv_imgUno = findViewById(R.id.imageViewNumUno);
        iv_imgDos = findViewById(R.id.imageViewNumDos);
        iv_signoOperacion = findViewById(R.id.imageViewSigno);
        et_respuesta = findViewById(R.id.editTextRespuesta);

        juego.setNombreJugardor(getIntent().getStringExtra("jugador"));
        tv_nombre.setText("Jugador " + juego.getNombreJugardor());

        string_score = getIntent().getStringExtra("score");
        juego.setScore(Integer.parseInt(string_score));
        tv_score.setText("Score " + juego.getScore());

        string_vidas = getIntent().getStringExtra("vidas");
        vidas = Integer.parseInt(string_vidas);


        switch (vidas) {
            case 3:
                iv_vidas.setImageResource(R.drawable.tresvidas);
                break;
            case 2:
                iv_vidas.setImageResource(R.drawable.dosvidas);
                break;
            case 1:
                iv_vidas.setImageResource(R.drawable.unavida);
                break;
        }

        setSupportActionBar(findViewById(R.id.toolbarNivelUno));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_icono);

        mp = MediaPlayer.create(this, R.raw.alphabet_song);
        mp.start();
        mp.setLooping(true);

        mpGreat = MediaPlayer.create(this, R.raw.wonderful);
        mpBad = MediaPlayer.create(this, R.raw.bad);

        numeroAleatorio();


    }

    public void comparar(View vista) {
        String respuesta = et_respuesta.getText().toString();
        if (!respuesta.equals("")) {
            int respuestaJuegador = Integer.parseInt(respuesta);

            if (respuestaJuegador == resultado) {
                mpGreat.start();
                juego.setScore(juego.getScore()+1);
                tv_score.setText("Score: " + juego.getScore());
            } else {
                mpBad.start();
                vidas--;
                switch (vidas) {
                    case 2:
                        iv_vidas.setImageResource(R.drawable.dosvidas);
                        Toast.makeText(this, "Quedan dos Manzanas", Toast.LENGTH_SHORT).show();
                        break;

                    case 1:
                        iv_vidas.setImageResource(R.drawable.unavida);
                        Toast.makeText(this, "Quedan una Manzana", Toast.LENGTH_SHORT).show();
                        break;

                    case 0:

                        Toast.makeText(this, "Has perdido todas tus Manzadas!", Toast.LENGTH_SHORT).show();
                        mp.stop();
                        mp.release();
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }

            baseDeDatos();
            et_respuesta.setText("");
            numeroAleatorio();

        } else {
            Toast.makeText(this, "Debes dar una respuesta", Toast.LENGTH_SHORT).show();
        }
    }

    public void numeroAleatorio() {

        if (juego.getScore() <= 40) {

            numAleatorio_uno = (int) (Math.random() * 10);
            numAleatorio_dos = (int) (Math.random() * 10);

            if (numAleatorio_uno >= 0 && numAleatorio_dos <= 4) {
                iv_signoOperacion.setImageResource(R.drawable.adicion);
                resultado = numAleatorio_uno + numAleatorio_dos;
                if (resultado >= 0) {
                    for (int i = 0; i < numero.length; i++) {
                        int id = getResources().getIdentifier(numero[i], "drawable", getPackageName());
                        if (numAleatorio_uno == i) {
                            iv_imgUno.setImageResource(id);
                        }
                        if (numAleatorio_dos == i) {
                            iv_imgDos.setImageResource(id);
                        }
                    }

                } else {
                    numeroAleatorio();
                }
            } else if (numAleatorio_uno >= numAleatorio_dos) {
                iv_signoOperacion.setImageResource(R.drawable.resta);
                resultado = numAleatorio_uno - numAleatorio_dos;
                for (int i = 0; i < numero.length; i++) {
                    int id = getResources().getIdentifier(numero[i], "drawable", getPackageName());
                    if (numAleatorio_uno == i) {
                        iv_imgUno.setImageResource(id);
                    }
                    if (numAleatorio_dos == i) {
                        iv_imgDos.setImageResource(id);
                    }
                }
            } else {
                numeroAleatorio();
            }
        } else {
            Intent intent = new Intent(this, MainActivity2Nivel5.class);

            string_score = String.valueOf(juego.getScore());
            string_vidas = String.valueOf(vidas);

            intent.putExtra("jugador", juego.getNombreJugardor());
            intent.putExtra("score", string_score);
            intent.putExtra("vidas", string_vidas);

            mp.stop();
            mp.release();
            startActivity(intent);
            finish();

        }

    }

    public void baseDeDatos() {
        Repository repo = new Repository();
        repo.add(juego);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}