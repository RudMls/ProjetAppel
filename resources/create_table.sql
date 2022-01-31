DROP TABLE IF EXISTS presence;
DROP TABLE IF EXISTS absence;
DROP TABLE IF EXISTS cours_instance;
DROP TABLE IF EXISTS cours;
DROP TABLE IF EXISTS etudiant;
DROP TABLE IF EXISTS enseignant;
DROP TABLE IF EXISTS justificatif;

CREATE TABLE etudiant (
    etudiant_id INT AUTO_INCREMENT,
    etudiant_nom VARCHAR(50) NOT NULL,
    etudiant_prenom VARCHAR(50) NOT NULL,
    etudiant_email VARCHAR(50) NOT NULL,
    etudiant_mdp VARCHAR(255) NOT NULL,
    PRIMARY KEY(etudiant_id)
);

CREATE TABLE enseignant (
    enseignant_id INT AUTO_INCREMENT,
    enseignant_nom VARCHAR(50) NOT NULL,
    enseignant_prenom VARCHAR(50) NOT NULL,
    enseignant_email VARCHAR(50) NOT NULL,
    enseignant_mdp VARCHAR(50) NOT NULL,
    PRIMARY KEY(enseignant_id)
);

CREATE TABLE cours (
    cours_id INT AUTO_INCREMENT,
    cours_libelle VARCHAR(50) NOT NULL,
    PRIMARY KEY(cours_id)
);

CREATE TABLE justificatif (
    justificatif_id INT AUTO_INCREMENT,
    justificatif_texte VARCHAR(255) NOT NULL,
    justificatif_date DATETIME,
    justificatif_validee TINYINT(1),
    PRIMARY KEY(justificatif_id)
);

CREATE TABLE cours_instance (
    cours_instance_id INT AUTO_INCREMENT,
    cours_instance_date DATE NOT NULL,
    cours_instance_duree TIME NOT NULL,
    cours_id INT NOT NULL ,
    enseignant_id INT NOT NULL,
    PRIMARY KEY(cours_instance_id),
    FOREIGN KEY(cours_id) REFERENCES cours (cours_id),
    FOREIGN KEY(enseignant_id) REFERENCES enseignant(enseignant_id)
);

CREATE TABLE presence
(
    cours_instance_id INT NOT NULL,
    etudiant_id INT NOT NULL,
    retard TINYINT(1),
    PRIMARY KEY(cours_instance_id, etudiant_id),
    FOREIGN KEY(cours_instance_id) REFERENCES cours_instance(cours_instance_id),
    FOREIGN KEY(etudiant_id) REFERENCES etudiant(etudiant_id)
);

CREATE TABLE absence (
    cours_instance_id INT NOT NULL,
    etudiant_id INT NOT NULL,
    justificatif_id INT,
    PRIMARY KEY(cours_instance_id, etudiant_id, justificatif_id),
    FOREIGN KEY(cours_instance_id) REFERENCES cours_instance(cours_instance_id),
    FOREIGN KEY(etudiant_id) REFERENCES etudiant(etudiant_id),
    FOREIGN KEY(justificatif_id) REFERENCES justificatif(justificatif_id)
);