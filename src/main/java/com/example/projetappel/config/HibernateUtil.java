package com.example.projetappel.config;

import com.example.projetappel.model.Utilisateur;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Chargement de la configuration et création de la SessionFactory.
 * (hibernate.cfg.xml)
 */
public class HibernateUtil {

	private static final SessionFactory SESSION_FACTORY;

	static {
		try	{
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            System.out.println("Hibernate Configuration loaded");

			// Entités
			configuration.addAnnotatedClass(Utilisateur.class);
			configuration.addAnnotatedClass(com.example.projetappel.model.Cours.class);
			configuration.addAnnotatedClass(com.example.projetappel.model.CoursInstance.class);
			configuration.addAnnotatedClass(com.example.projetappel.model.Justificatif.class);
			configuration.addAnnotatedClass(com.example.projetappel.model.Utilisateur.class);
			configuration.addAnnotatedClass(com.example.projetappel.model.Absence.class);
			configuration.addAnnotatedClass(com.example.projetappel.model.Groupe.class);
			configuration.addAnnotatedClass(com.example.projetappel.model.Formation.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate serviceRegistry created");

            SESSION_FACTORY = configuration.buildSessionFactory(serviceRegistry);
		} catch (HibernateException ex) {
			System.err.println("Initial SessionFactory creation failed.\n" + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory () {
		return SESSION_FACTORY;
	}

}