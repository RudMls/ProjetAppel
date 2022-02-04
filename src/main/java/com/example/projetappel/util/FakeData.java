package com.example.projetappel.util;

import com.example.projetappel.dao.FormationDao;
import com.example.projetappel.dao.GroupeDao;
import com.example.projetappel.dao.UtilisateurDao;
import com.example.projetappel.enumtype.Role;
import com.example.projetappel.model.Formation;
import com.example.projetappel.model.Groupe;
import com.example.projetappel.model.Utilisateur;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class FakeData {

    public static final Faker FAKER = new Faker(new Locale("fr"));
    public static FormationDao formationDao = new FormationDao();
    public static GroupeDao groupeDao = new GroupeDao();
    public static UtilisateurDao utilisateurDao = new UtilisateurDao();

    public static void main(String[] args) {

        genererFormations();
        genererGroupes();
        genererUtilisateurs();
        utilisateurDao.verifierUtilisatuer("cindy@gmail.com","pwd");

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
                new Groupe("FI", formationDao.find(1)),
                new Groupe("FA", formationDao.find(1))
        ));
        groupes.forEach(groupeDao::create);
    }

    public static void genererUtilisateurs() {
        ArrayList<Utilisateur> utilisateurs = new ArrayList<>(Arrays.asList(
                new Utilisateur("Cindy", "Bosques", "cindy@gmail.com" , "pwd", Role.ETUDIANT_ALTERNANT),
                new Utilisateur("Ruddy", "Monlouis", "ruddy@gmail.com" , "pwd", Role.ETUDIANT_ALTERNANT),
                new Utilisateur("Annie", "Dago", "annie@gmail.com" , "pwd", Role.ETUDIANT_ALTERNANT),
                new Utilisateur("Vlada", "Stegarescu", "vlada@gmail.com" , "pwd", Role.ETUDIANT_ALTERNANT),
                new Utilisateur("Manel", "El Nouali", "manel@gmail.com" , "pwd", Role.ETUDIANT_ALTERNANT)
        ));

        utilisateurs.forEach(utilisateurDao::create);
    }

}
