package com.example.projetappel.model;

import com.example.projetappel.dao.EtudiantDao;
import com.example.projetappel.dao.UtilisateurDao;

public class Main {

    public static void main(String[] args) {

        UtilisateurDao utilisateurDao = new UtilisateurDao();

        utilisateurDao.loginUtilisateur("cindy@gmail.com","pwd");


    }
}
