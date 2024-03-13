package main.java.com.ubo.tp.message;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.Database;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.ihm.MessageApp1;
import main.java.com.ubo.tp.message.ihm.components.app.MessageApp;
import mock.MessageAppMock;

/**
 * Classe de lancement de l'application.
 *
 * @author S.Lucas
 */
public class MessageAppLauncher {

	/**
	 * Indique si le mode bouchoné est activé.
	 */
	protected static boolean IS_MOCK_ENABLED = true;

	/**
	 * Launcher.
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		IDatabase database = new Database();
		EntityManager entityManager = new EntityManager(database);

		if (IS_MOCK_ENABLED) {
			//MessageAppMock mock = new MessageAppMock(database, entityManager);
			//mock.showGUI();
			
		}

//		MessageApp1 messageApp = new MessageApp1(database, entityManager);
//		database.addObserver(messageApp);
//		messageApp.init();
//		messageApp.show();
		 MessageApp messApp = new MessageApp();
	        messApp.show();
	}
}
