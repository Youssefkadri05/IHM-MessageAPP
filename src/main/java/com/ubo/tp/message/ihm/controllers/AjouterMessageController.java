package main.java.com.ubo.tp.message.ihm.controllers;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.components.messages.ajouterMessage.IAjouterMessageObserver;
import main.java.com.ubo.tp.message.ihm.session.Session;

public class AjouterMessageController extends IController implements IAjouterMessageObserver {

	    public AjouterMessageController(IDatabase database, EntityManager entityManager, Session session) {
	        super(database, entityManager, session);
	    }

	    @Override
	    public Boolean isValidMessage(String message) {
	        return message.length() <= 200 && message.length() != 0;
	    }

	    @Override
	    public void message(String message) {
	    	//this.database.addMessage(new Message(session.getConnectedUser(), message));
			this.entityManager.writeMessageFile(new Message(session.getConnectedUser(), message));
	    }
	}
