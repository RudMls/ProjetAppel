package com.example.projetappel.util;

import com.example.projetappel.dao.*;
import com.example.projetappel.enumtype.TypeEtudiant;
import com.example.projetappel.model.*;
import com.github.javafaker.Faker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class FakeData {

    public static final Faker FAKER = new Faker(new Locale("fr"));
    public static final SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    public static FormationDao formationDao = new FormationDao();
    public static GroupeDao groupeDao = new GroupeDao();
    public static UtilisateurDao utilisateurDao = new UtilisateurDao();
    public static EtudiantDao etudiantDao = new EtudiantDao();
    public static EnseignantDao enseignantDao = new EnseignantDao();
    public static ScolariteDao scolariteDao = new ScolariteDao();
    public static CoursDao coursDao = new CoursDao();
    public static FicheAppelDao ficheAppelDao = new FicheAppelDao();
    public static CoursInstanceDao coursInstanceDao = new CoursInstanceDao();

    public static void main(String[] args) {

        genererCours();
        genererFormations();
        genererGroupes();
        genererEnseignant();
        genererScolarite();
        genererEtudiant();
        genererFicheAppel();
        genererCoursInstance();

    }

    public static void genererFormations() {
        ArrayList<Formation> formations = new ArrayList<>(Arrays.asList(
                new Formation("Master 2 IPM"),
                new Formation("Master 2 ISIAD")
        ));
        formations.forEach(formationDao::create);
    }

    public static void genererGroupes() {
        ArrayList<Groupe> groupes = new ArrayList<>(Arrays.asList(
                new Groupe("FA", formationDao.find(1)),
                new Groupe("FI", formationDao.find(1))
        ));
        groupes.forEach(groupeDao::create);
    }

    public static void genererEtudiant() {
        String prenom, nom, email;
        for (int i = 0; i < 50; i++) {
            do {
                prenom = FAKER.name().firstName();
                nom = FAKER.name().lastName();
                email = prenom + "." + nom + "@ut-capitole.fr";
            } while (utilisateurDao.emailExiste(email));
            etudiantDao.create(new Etudiant(prenom, nom, email, "pwd",TypeEtudiant.ALTERNANT));
        }
    }


    public static void genererEnseignant() {
        ArrayList<Enseignant> enseignants = new ArrayList<>(Arrays.asList(
                new Enseignant("Nathalie", "Valles", "nathalie.valles@ut-capitole.fr", "pwd"),
                new Enseignant("Franck", "Ravat", "franck.ravat@ut-capitole.fr", "pwd"),
                new Enseignant("Eric", "Andonoff", "eric.andonoff@ut-capitole.fr", "pwd")
        ));
        enseignants.forEach(enseignantDao::create);
    }

    public static void genererScolarite() {
        String prenom, nom, email;
        for (int i = 0; i < 2; i++) {
            do {
                prenom = FAKER.name().firstName();
                nom = FAKER.name().lastName();
                email = prenom + "." + nom + "@ut-capitole.fr";
            } while (utilisateurDao.emailExiste(email));
            scolariteDao.create(new Scolarite(prenom, nom, email, "pwd"));
        }
    }

    public static void genererCours() {
        ArrayList<Cours> cours = new ArrayList<>(Arrays.asList(
                new Cours("Démarche de développement agile"),
                new Cours("Accompagnement Client"),
                new Cours("Développement d'application internet"),
                new Cours("Anglais"),
                new Cours("Ingénierie des Processus Métiers"),
                new Cours("Management Agile"),
                new Cours("Données, conception, manipulation"),
                new Cours("Programmation Objet"),
                new Cours("Analyse et conception objet des SI")
        ));
        cours.forEach(coursDao::create);
    }

    public static void genererFicheAppel() {
        for (int i = 0; i < 10; i++) {
            ficheAppelDao.create(new FicheAppel());
        }
    }

    public static void genererCoursInstance() {
        ArrayList<CoursInstance> coursInstances = null;
        try {
            coursInstances = new ArrayList<>(Arrays.asList(
                    new CoursInstance(SDF.parse("07-02-2022 09:30:00"), SDF.parse("07-02-2022 12:30:00"), coursDao.find(1), enseignantDao.find(1), groupeDao.find(1), ficheAppelDao.find(1))
            ));
            coursInstances.forEach(coursInstanceDao::create);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
