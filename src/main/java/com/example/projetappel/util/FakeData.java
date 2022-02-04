package com.example.projetappel.util;

import com.example.projetappel.dao.UtilisateurDao;
import com.example.projetappel.model.Utilisateur;
import com.github.javafaker.Faker;

import java.util.Locale;

public class FakeData {

    public static final Faker FAKER = new Faker(new Locale("fr"));

    public static void main(String[] args) {

        genererEtudiantsEnseignants();

    }

    public static void genererEtudiantsEnseignants() {

        UtilisateurDao utilisateurDao = new UtilisateurDao();

        for (int i = 0; i < 10; i++) {
            utilisateurDao.create(new Utilisateur(
                FAKER.name().firstName(),
                FAKER.name().lastName()
            ));
        }
    }

}
