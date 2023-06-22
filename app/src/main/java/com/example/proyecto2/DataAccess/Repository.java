package com.example.proyecto2.DataAccess;

import android.widget.TextView;

import com.example.proyecto2.Models.Juego;

public class Repository implements IRepository {

    private DAO dao;

    public Repository() {
        dao = new DAO();
    }

    @Override
    public void add(Juego j) {
        dao.addScoreJuego(j);
    }

    @Override
    public void get(TextView score) {
        dao.getScoreJuego(score);
    }
}
