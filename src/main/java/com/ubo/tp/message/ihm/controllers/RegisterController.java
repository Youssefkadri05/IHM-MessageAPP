package com.ubo.tp.message.ihm.controllers;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.ubo.tp.message.core.EntityManager;
import com.ubo.tp.message.core.database.IDatabase;
import com.ubo.tp.message.datamodel.User;
import com.ubo.tp.message.ihm.components.register.IRegisterObserver;
import com.ubo.tp.message.ihm.session.Session;

public class RegisterController extends IController implements IRegisterObserver {

	    public RegisterController(IDatabase database, EntityManager entityManager , Session session) {
	        super(database, entityManager, session);
	    }

	    @Override
	    public boolean isPasswordValid(String tPassword) {
	        return tPassword.length() > 3;
	    }	  

	    @Override
	    public boolean isAccountExist(String tag) {
			for (User user : this.database.getUsers()) {
				if (user.getUserTag().equals(tag)) return true;
			}
			return false;
	    }



		@Override
		public void notifyRegister(String tTag, String tPassword, String tNom, Set<String> follows, String tAvatar) {
			// TODO Auto-generated method stub
			User newUser = new User(UUID.randomUUID(), tTag, tPassword, tNom, new HashSet<>(), tAvatar);
	        this.entityManager.writeUserFile(newUser);
			//this.database.addUser(newUser);
			
		}
	}
