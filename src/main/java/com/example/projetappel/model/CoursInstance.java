package com.example.projetappel.model;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class CoursInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebut;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFin;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cours cours;

    @ManyToOne
    private Enseignant enseignant;

    @ManyToOne
    private Groupe groupe;

    @OneToOne
    private FicheAppel ficheAppel;

    public CoursInstance() {}

    public CoursInstance(int id, Date dateDebut, Date dateFin, Cours cours) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.cours = cours;
    }

    public CoursInstance(Date dateDebut, Date dateFin, Cours cours, Enseignant enseignant, Groupe groupe, FicheAppel ficheAppel) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.cours = cours;
        this.enseignant = enseignant;
        this.groupe = groupe;
        this.ficheAppel = ficheAppel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public FicheAppel getFicheAppel() {
        return ficheAppel;
    }

    public void setFicheAppel(FicheAppel ficheAppel) {
        this.ficheAppel = ficheAppel;
    }

    public String getParseDateDebut(){
       return  new SimpleDateFormat("dd-MMM-yyyy à hh:mm").format(this.getDateDebut());
    }
    public String getParseDateFin(){
        return  "- "+ new SimpleDateFormat("hh:mm").format(this.getDateFin());
    }
}
