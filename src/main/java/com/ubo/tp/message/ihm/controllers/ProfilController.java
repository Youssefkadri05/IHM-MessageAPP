package main.java.com.ubo.tp.message.ihm.controllers;


import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.components.profil.IProfilObserver;
import main.java.com.ubo.tp.message.ihm.session.Session;

public class ProfilController extends IController implements IProfilObserver {

    public ProfilController(IDatabase database, EntityManager entityManager, Session session) {
        super(database, entityManager,session);
    }

    @Override
    public void modifyAccount(User newUser) {
        this.entityManager.writeUserFile(newUser);
    }
}
