package com.example.projetappel.dao;

import com.example.projetappel.model.Notification;

public class NotificationDao extends DAO<Notification> {

    public NotificationDao() {
        super.setEntity(Notification.class);
    }

}
