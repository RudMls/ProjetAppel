package com.example.projetappel.config;

import com.example.projetappel.dao.AbsenceDao;
import com.example.projetappel.dao.CoursInstanceDao;
import com.example.projetappel.model.CoursInstance;
import com.example.projetappel.model.Fichier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        final GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();

        final HashMap<String, Object> hashMapResponse = new HashMap<>();
        hashMapResponse.put("hello", 1);
        System.out.println("liste -> " + gson.toJson(hashMapResponse));

    }
}
