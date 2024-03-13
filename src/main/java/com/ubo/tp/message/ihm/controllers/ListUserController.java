package com.ubo.tp.message.ihm.controllers;


import com.ubo.tp.message.core.EntityManager;
import com.ubo.tp.message.core.database.IDatabase;
import com.ubo.tp.message.ihm.components.userss.IListUserObserver;
import com.ubo.tp.message.ihm.session.Session;

public class ListUserController extends IController implements IListUserObserver {
    public ListUserController(IDatabase database, EntityManager entityManager, Session session) {
        super(database, entityManager,session);
    }

}
