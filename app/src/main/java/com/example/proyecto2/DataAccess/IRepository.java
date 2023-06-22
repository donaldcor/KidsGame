package com.example.proyecto2.DataAccess;

import android.widget.TextView;

import com.example.proyecto2.Models.Juego;

public interface IRepository {

    void add(Juego j);
    void get(TextView score);
}
