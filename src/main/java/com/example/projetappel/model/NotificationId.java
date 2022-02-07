package com.example.projetappel.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class NotificationId implements Serializable {

    @Column(name = "scolarite_id")
    private int scolariteId;

    @Column(name = "justificatif_id")
    private  int justificatifId;

    public NotificationId() {
    }

    public NotificationId(int scolariteId, int justificatifId) {
        this.scolariteId = scolariteId;
        this.justificatifId = justificatifId;
    }

    public int getScolariteId() {
        return scolariteId;
    }

    public void setScolariteId(int scolariteId) {
        this.scolariteId = scolariteId;
    }

    public int getJustificatifId() {
        return justificatifId;
    }

    public void setJustificatifId(int justificatifId) {
        this.justificatifId = justificatifId;
    }
}
