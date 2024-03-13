package com.ubo.tp.message.ihm.controllers;

import java.util.Optional;

import com.ubo.tp.message.core.EntityManager;
import com.ubo.tp.message.core.database.IDatabase;
import com.ubo.tp.message.datamodel.User;
import com.ubo.tp.message.ihm.components.app.MessageApp;
import com.ubo.tp.message.ihm.components.login.IConnexionObserver;
import com.ubo.tp.message.ihm.session.ISessionObserver;
import com.ubo.tp.message.ihm.session.Session;

public class ConnexionController extends IController implements IConnexionObserver, ISessionObserver {
    
	public ConnexionController(IDatabase database, EntityManager entityManager, Session session) {
        super(database, entityManager, session);
    }

    @Override
	public boolean checkConnexion(String tag, String password) {
    	
    	
    	 Optional<User> user = this.database.getUsers().stream().filter((u -> u.getUserTag().equals(tag))).findFirst();

			if (!user.isPresent()) return false;

			if (user.get().getUserPassword().equals(password)) {
				this.session.connect(user.get());
				System.out.println("				this.mainController.toSendMessageComponent();\r\n"
						+ "");
				return true;
			}
			else return false;
    }

	@Override
	public void notifyLogin(User connectedUser) {
		
		
	}

	@Override
	public void notifyLogout() {
		// TODO Auto-generated method stub
		
	}
}
