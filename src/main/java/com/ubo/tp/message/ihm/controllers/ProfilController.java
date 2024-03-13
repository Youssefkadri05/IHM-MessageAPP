package com.ubo.tp.message.ihm.controllers;


import com.ubo.tp.message.core.EntityManager;
import com.ubo.tp.message.core.database.IDatabase;
import com.ubo.tp.message.datamodel.User;
import com.ubo.tp.message.ihm.components.profil.IProfilObserver;
import com.ubo.tp.message.ihm.session.Session;

public class ProfilController extends IController implements IProfilObserver {

    public ProfilController(IDatabase database, EntityManager entityManager, Session session) {
        super(database, entityManager,session);
    }

    @Override
    public void modifyAccount(User newUser) {
        this.entityManager.writeUserFile(newUser);
    }
}
