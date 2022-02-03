package com.example.projetappel.util;

import com.example.projetappel.dao.EnseignantDao;
import com.example.projetappel.dao.EtudiantDao;
import com.example.projetappel.model.Enseignant;
import com.example.projetappel.model.Etudiant;
import com.github.javafaker.Faker;

import java.util.Locale;

public class FakeData {

    public static final Faker FAKER = new Faker(new Locale("fr"));

    public static void main(String[] args) {

        genererEtudiantsEnseignants();

    }

    public static void genererEtudiantsEnseignants() {

        EtudiantDao etudiantDao = new EtudiantDao();
        EnseignantDao enseignantDao = new EnseignantDao();

        for (int i = 0; i < 10; i++) {
            etudiantDao.create(new Etudiant(
                FAKER.name().firstName(),
                FAKER.name().lastName()
            ));
            enseignantDao.create(new Enseignant(
                    FAKER.name().firstName(),
                    FAKER.name().lastName()
            ));
        }
    }

}
