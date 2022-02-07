package com.example.projetappel.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AppartenirId implements Serializable {

    @Column(name = "etudiant_id", nullable = false)
    private int etudiantId;

    @Column(name = "groupe_id", nullable = false)
    private int groupeId;

    @Column(name = "formation_id", nullable = false)
    private int formationId;

    public AppartenirId() {
    }

    public AppartenirId(int etudiantId, int groupeId, int formationId) {
        this.etudiantId = etudiantId;
        this.groupeId = groupeId;
        this.formationId = formationId;
    }
}
