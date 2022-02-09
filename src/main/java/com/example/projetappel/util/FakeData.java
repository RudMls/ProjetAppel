package com.example.projetappel.util;

import com.example.projetappel.dao.*;
import com.example.projetappel.enumtype.TypeEtudiant;
import com.example.projetappel.model.*;
import com.github.javafaker.Faker;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

@WebListener
public class FakeData implements ServletContextListener {

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
    public static AppartenirDao appartenirDao = new AppartenirDao();
    public static JustificatifDao justificatifDao = new JustificatifDao();
    public static AbsenceDao absenceDao = new AbsenceDao();
    public static PresenceDao presenceDao = new PresenceDao();


    public static void main(String[] args) {
        generer();
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        generer();
    }

    public static void generer() {

        genererFormations();
        genererCours();
        genererGroupes();
        genererEnseignant();
        genererScolarite();
        genererEtudiant();
        genererFicheAppel();
        genererCoursInstance();
        genererAppartenir();
        genererAbsence();
        genererPresence();
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
                new Groupe("FA"),
                new Groupe("FI")
        ));
        groupes.forEach(groupeDao::create);
    }

    public static void genererEtudiant() {
        String prenom, nom, email;
        TypeEtudiant typeEtudiant;
        for (int i = 0; i < 50; i++) {
            if (i % 3 == 0) {
                typeEtudiant = TypeEtudiant.ALTERNANT;
            } else {
                typeEtudiant = TypeEtudiant.INITIAL;
            }
            do {
                prenom = FAKER.name().firstName();
                nom = FAKER.name().lastName();
                email = prenom + "." + nom + "@ut-capitole.fr";
            } while (utilisateurDao.emailExiste(email));
            etudiantDao.create(new Etudiant(prenom, nom, email, "pwd", typeEtudiant));
        }
    }

    public static void genererEnseignant() {
        ArrayList<Enseignant> enseignants = new ArrayList<>(Arrays.asList(
                new Enseignant("Nathalie", "Valles", "nathalie.valles@ut-capitole.fr", "pwd"),
                new Enseignant("Franck", "Ravat", "franck.ravat@ut-capitole.fr", "pwd"),
                new Enseignant("Eric", "Andonoff", "eric.andonoff@ut-capitole.fr", "pwd"),
                new Enseignant("Bour", "Raphaëlle", "raphaelle.bour@ut-capitole.fr", "pwd")
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

    public static void genererAppartenir() {

        for(int i = 7; i < 15; i++){

            Etudiant etudiant = etudiantDao.find(i);
            Groupe groupe1 = groupeDao.find(1);
            Formation formation1 = formationDao.find(1);
            appartenirDao.create(new Appartenir(etudiant,groupe1,formation1));
        }

        for(int i = 15; i < 30; i++){

            Etudiant etudiant = etudiantDao.find(i);
            Groupe groupe1 = groupeDao.find(2);
            Formation formation1 = formationDao.find(1);
            appartenirDao.create(new Appartenir(etudiant,groupe1,formation1));
        }

        for(int i = 30; i < 45; i++){

            Etudiant etudiant = etudiantDao.find(i);
            Groupe groupe1 = groupeDao.find(1);
            Formation formation1 = formationDao.find(2);
            appartenirDao.create(new Appartenir(etudiant,groupe1,formation1));
        }

        for(int i = 45; i < 56; i++){

            Etudiant etudiant = etudiantDao.find(i);
            Groupe groupe1 = groupeDao.find(2);
            Formation formation1 = formationDao.find(2);
            appartenirDao.create(new Appartenir(etudiant,groupe1,formation1));
        }

    }

    public static void genererCours() {

        Formation formation1 = formationDao.find(1);
        Formation formation2 = formationDao.find(2);

        ArrayList<Cours> cours = new ArrayList<>(Arrays.asList(
                new Cours("Démarche de développement agile", formation1),
                new Cours("Accompagnement Client", formation1),
                new Cours("Développement d'application internet,", formation1),
                new Cours("Anglais", formation1),
                new Cours("Ingénierie des Processus Métiers", formation2),
                new Cours("Management Agile", formation2),
                new Cours("Données, conception, manipulation", formation1),
                new Cours("Programmation Objet", formation2),
                new Cours("Analyse et conception objet des SI", formation2)
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
                    new CoursInstance(SDF.parse("03-02-2022 09:30:00"), SDF.parse("07-02-2022 12:30:00"), coursDao.find(1), enseignantDao.find(1), groupeDao.find(1), ficheAppelDao.find(1)),
                    new CoursInstance(SDF.parse("04-02-2022 09:30:00"), SDF.parse("07-02-2022 12:30:00"), coursDao.find(1), enseignantDao.find(1), groupeDao.find(1), ficheAppelDao.find(2)),
                    new CoursInstance(SDF.parse("07-02-2022 09:30:00"), SDF.parse("07-02-2022 12:30:00"), coursDao.find(1), enseignantDao.find(1), groupeDao.find(1), ficheAppelDao.find(3)),
                    new CoursInstance(SDF.parse("08-02-2022 09:30:00"), SDF.parse("08-02-2022 12:30:00"), coursDao.find(6), enseignantDao.find(2), groupeDao.find(2), ficheAppelDao.find(4)),
                    new CoursInstance(SDF.parse("09-02-2022 14:00:00"), SDF.parse("09-02-2022 17:00:00"), coursDao.find(2), enseignantDao.find(3), groupeDao.find(2), ficheAppelDao.find(5)),
                    new CoursInstance(SDF.parse("10-02-2022 09:30:00"), SDF.parse("10-02-2022 12:30:00"), coursDao.find(7), enseignantDao.find(4), groupeDao.find(1), ficheAppelDao.find(6)),
                    new CoursInstance(SDF.parse("08-02-2022 09:30:00"), SDF.parse("08-02-2022 12:30:00"), coursDao.find(1), enseignantDao.find(1), groupeDao.find(1), ficheAppelDao.find(7))
            ));
            coursInstances.forEach(coursInstanceDao::create);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void genererAbsence(){
        ArrayList<Absence> absences = null;

        absences = new ArrayList<>(Arrays.asList(
                new Absence(etudiantDao.find(7),ficheAppelDao.find(1)),
                new Absence(etudiantDao.find(45),ficheAppelDao.find(2)),
                new Absence(etudiantDao.find(47),ficheAppelDao.find(2))
        ));
        absences.forEach(absenceDao::create);

        }

    public static void genererPresence(){
        ArrayList<Presence> presences = null;

        presences = new ArrayList<>(Arrays.asList(
                new Presence(true, etudiantDao.find(7),ficheAppelDao.find(1)),
                new Presence(true, etudiantDao.find(45),ficheAppelDao.find(2)),
                new Presence(true, etudiantDao.find(47),ficheAppelDao.find(2))
                //new Absence(etudiantDao.find(8),ficheAppelDao.find(2))
        ));
        presences.forEach(presenceDao::create);

    }


}
