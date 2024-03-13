package main.java.com.ubo.tp.message.ihm.controllers;


import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.ihm.components.userss.IListUserObserver;
import main.java.com.ubo.tp.message.ihm.session.Session;

public class ListUserController extends IController implements IListUserObserver {
    public ListUserController(IDatabase database, EntityManager entityManager, Session session) {
        super(database, entityManager,session);
    }

}
