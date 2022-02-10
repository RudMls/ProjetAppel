package com.example.projetappel.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class FicheAppel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean validee = false;

    @OneToMany(mappedBy = "ficheAppel")
    private Set<Presence> presences = new HashSet<>();

    @OneToMany(mappedBy = "ficheAppel")
    private Set<Absence> absences = new HashSet<>();

    @OneToOne(mappedBy = "ficheAppel")
    private CoursInstance coursInstance;

    public boolean isValidee() {
        return validee;
    }

    public void setValidee(boolean validee) {
        this.validee = validee;
    }

    public Set<Presence> getPresences() {
        return presences;
    }

    public void setPresences(Set<Presence> presences) {
        this.presences = presences;
    }

    public Set<Absence> getAbsences() {
        return absences;
    }

    public void setAbsences(Set<Absence> absences) {
        this.absences = absences;
    }

    public CoursInstance getCoursInstance() {
        return coursInstance;
    }

    public void setCoursInstance(CoursInstance coursInstance) {
        this.coursInstance = coursInstance;
    }

    public int getId() {
        return id;
    }

    public FicheAppel() {}

}
