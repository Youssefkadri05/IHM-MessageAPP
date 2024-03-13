package com.ubo.tp.message.ihm.controllers;

import com.ubo.tp.message.core.EntityManager;
import com.ubo.tp.message.core.database.IDatabase;
import com.ubo.tp.message.ihm.components.messages.afficherMessages.IMsgObserver;
import com.ubo.tp.message.ihm.session.Session;

public class MsgController extends IController implements IMsgObserver {

    public MsgController(IDatabase database, EntityManager entityManager, Session session) {
        super(database, entityManager, session);
    }
}
