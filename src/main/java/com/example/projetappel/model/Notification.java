package com.example.projetappel.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Notification implements Serializable {

    @EmbeddedId
    private NotificationId id;

    @Column(name = "valider_id", insertable = false, updatable = false)
    private boolean valider;

    @ManyToOne
    @JoinColumn (name = "scolarite_id", insertable = false, updatable = false)
    private Scolarite scolarite;

    @ManyToOne
    @JoinColumn (name = "justificat_id", insertable = false, updatable = false)
    private Justificatif justificatif;

    public Notification() {
    }

    public Notification(NotificationId id, boolean valider, Scolarite scolarite, Justificatif justificatif) {
        this.id = id;
        this.valider = valider;
        this.scolarite = scolarite;
        this.justificatif = justificatif;
    }

    public NotificationId getId() {
        return id;
    }

    public void setId(NotificationId id) {
        this.id = id;
    }

    public boolean isValider() {
        return valider;
    }

    public void setValider(boolean valider) {
        this.valider = valider;
    }

    public Scolarite getScolarite() {
        return scolarite;
    }

    public void setScolarite(Scolarite scolarite) {
        this.scolarite = scolarite;
    }

    public Justificatif getJustificatif() {
        return justificatif;
    }

    public void setJustificatif(Justificatif justificatif) {
        this.justificatif = justificatif;
    }
}
