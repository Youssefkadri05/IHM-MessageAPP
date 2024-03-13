package main.java.com.ubo.tp.message.ihm.controllers;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.ihm.components.messages.afficherMessages.IMsgObserver;
import main.java.com.ubo.tp.message.ihm.session.Session;

public class MsgController extends IController implements IMsgObserver {

    public MsgController(IDatabase database, EntityManager entityManager, Session session) {
        super(database, entityManager, session);
    }
}
