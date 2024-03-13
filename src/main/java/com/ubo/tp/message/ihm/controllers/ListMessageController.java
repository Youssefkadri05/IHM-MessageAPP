package main.java.com.ubo.tp.message.ihm.controllers;

import java.util.HashSet;
import java.util.Set;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.components.messages.afficherMessages.IListMessagesObserver;
import main.java.com.ubo.tp.message.ihm.session.Session;

public class ListMessageController extends IController implements IListMessagesObserver {
    public ListMessageController(IDatabase database, EntityManager entityManager, Session session) {
        super(database, entityManager, session);
    }

	@Override
	public Set<Message> getAllMessages() {
		// TODO Auto-generated method stub
		return this.database.getMessages();
	}
	
	  @Override
	    public Set<Message> getUserMessages(String userTag) {
	        for (User user : this.database.getUsers()) {
	            if (("@" + user.getUserTag()).equals(userTag)) {
	                return this.database.getUserMessages(user);
	            }
	        }

	        return new HashSet<>();
	    }

    
    
}