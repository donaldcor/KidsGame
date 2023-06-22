package com.example.proyecto2.Models;

public class Juego {

    private int score;
    private String nombreJugardor;

    public Juego() {
    }

    public Juego(int score, String nombreJugardor) {
        this.score = score;
        this.nombreJugardor = nombreJugardor;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getNombreJugardor() {
        return nombreJugardor;
    }

    public void setNombreJugardor(String nombreJugardor) {
        this.nombreJugardor = nombreJugardor;
    }

    @Override
    public String toString() {
        return "Juego{" +
                "score=" + score +
                ", nombreJugardor='" + nombreJugardor + '\'' +
                '}';
    }
}
