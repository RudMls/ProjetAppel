package com.example.projetappel.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Justificatif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String texte;

    private Date date;

    private boolean validee;

    @OneToMany(
            mappedBy = "justificatif",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @MapKeyColumn (name = "scolarite_id", updatable = false, insertable = false)
    private Map<Scolarite, Notification> scolariteNotification = new HashMap<>(0);

    public Justificatif() {
    }

    public Justificatif(int id, String texte, Date date, boolean validee, Map<Scolarite, Notification> scolariteNotification) {
        this.id = id;
        this.texte = texte;
        this.date = date;
        this.validee = validee;
        this.scolariteNotification = scolariteNotification;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isValidee() {
        return validee;
    }

    public void setValidee(boolean validee) {
        this.validee = validee;
    }

    public Map<Scolarite, Notification> getScolariteNotification() {
        return scolariteNotification;
    }

    public void setScolariteNotification(Map<Scolarite, Notification> scolariteNotification) {
        this.scolariteNotification = scolariteNotification;
    }


}
