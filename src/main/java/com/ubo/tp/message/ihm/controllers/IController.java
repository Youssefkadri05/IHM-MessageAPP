package main.java.com.ubo.tp.message.ihm.controllers;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.ihm.session.Session;

public abstract class IController {
    public IDatabase database;
    public EntityManager entityManager;
    public Session session;

    protected IController(IDatabase database, EntityManager entityManager, Session session) {
        this.database = database;
        this.entityManager = entityManager;
        this.session = session ;
    }
}
