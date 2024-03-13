package com.ubo.tp.message.ihm.controllers;

import com.ubo.tp.message.core.EntityManager;
import com.ubo.tp.message.core.database.IDatabase;
import com.ubo.tp.message.ihm.session.Session;

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
