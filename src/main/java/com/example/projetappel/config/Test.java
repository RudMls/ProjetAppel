package com.example.projetappel.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Test {

    public static void main(String[] args) {
        File file=new File("./credential.json");
        try {
            FileInputStream fis=new FileInputStream(file);     //opens a connection to an actual file
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("file content: ");
    }
}
