package com.ubo.tp.message.ihm;

import java.io.File;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.UIManager;

import com.ubo.tp.message.core.EntityManager;
import com.ubo.tp.message.core.database.IDatabase;
import com.ubo.tp.message.core.database.IDatabaseObserver;
import com.ubo.tp.message.core.directory.IWatchableDirectory;
import com.ubo.tp.message.core.directory.IWatchableDirectoryObserver;
import com.ubo.tp.message.core.directory.WatchableDirectory;
import com.ubo.tp.message.datamodel.Message;
import com.ubo.tp.message.datamodel.User;

/**
 * Classe principale l'application.
 *
 * @author S.Lucas
 */
public class MessageApp1 implements IDatabaseObserver, IWatchableDirectoryObserver{
	/**
	 * Base de données.
	 */
	protected IDatabase mDatabase;

	/**
	 * Gestionnaire des entités contenu de la base de données.
	 */
	protected EntityManager mEntityManager;

	/**
	 * Vue principale de l'application.
	 */
	protected MessageAppMainView1 mMainView;

	/**
	 * Classe de surveillance de répertoire
	 */
	protected IWatchableDirectory mWatchableDirectory;

	/**
	 * Répertoire d'échange de l'application.
	 */
	protected String mExchangeDirectoryPath;

	/**
	 * Nom de la classe de l'UI.
	 */
	protected String mUiClassName;

	/**
	 * Constructeur.
	 *
	 * @param entityManager
	 * @param database
	 */
	public MessageApp1(IDatabase database, EntityManager entityManager) {
		this.mDatabase = database;
		this.mEntityManager = entityManager;
	}

	/**
	 * Initialisation de l'application.
	 */
	public void init() {
		// Init du look and feel de l'application
		this.initLookAndFeel();

		// Initialisation de l'IHM
		this.initGui();
		
		// Initialisation du répertoire d'échange
		this.initDirectory();
	}
	



	/**
	 * Initialisation du look and feel de l'application.
	 */
	protected void initLookAndFeel() {
		try {
	        //Look & Feel Nimbus 
	        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
	            if ("Nimbus".equals(info.getName())) {
	                UIManager.setLookAndFeel(info.getClassName());
	                break;
	            }
	        }
	    } catch (Exception e) {
	        // Si Nimbus n'est pas disponible, définir le look and feel système
	        try {
	            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	}

	/**
	 * Initialisation de l'interface graphique.
	 */
//	protected void initGui() {
//		// this.mMainView...
//		// Initialisation de la vue principale de l'application
//	    mMainView = new MessageAppMainView();
//	    
//	    // Affichage de la vue principale de l'application
//	    mMainView.show();
//	}

	protected void initGui() {
        mMainView = new MessageAppMainView1();
        mMainView.initGUI();
    }
	/**
	 * Initialisation du répertoire d'échange (depuis la conf ou depuis un file
	 * chooser). <br/>
	 * <b>Le chemin doit obligatoirement avoir été saisi et être valide avant de
	 * pouvoir utiliser l'application</b>
	 */
	protected void initDirectory() {
        String directoryPath = mMainView.getFolder();
        if (directoryPath != null && isValideExchangeDirectory(new File(directoryPath))) {
            initDirectory(directoryPath);
        } else {
            // Gestion de l'erreur si le répertoire n'est pas valide ou non sélectionné
            System.out.println("Le répertoire sélectionné n'est pas valide ou n'a pas été choisi.");
        }
    }


	/**
	 * Indique si le fichier donné est valide pour servir de répertoire d'échange
	 *
	 * @param directory , Répertoire à tester.
	 */
	protected boolean isValideExchangeDirectory(File directory) {
		// Valide si répertoire disponible en lecture et écriture
		return directory != null && directory.exists() && directory.isDirectory() && directory.canRead()
				&& directory.canWrite();
	}

	/**
	 * Initialisation du répertoire d'échange.
	 *
	 * @param directoryPath
	 */
	protected void initDirectory(String directoryPath) {
		mExchangeDirectoryPath = directoryPath;
		mWatchableDirectory = new WatchableDirectory(directoryPath);
		mEntityManager.setExchangeDirectory(directoryPath);

		mWatchableDirectory.initWatching();
		mWatchableDirectory.addObserver(mEntityManager);
	}

	public void show() {
		if (mMainView == null) {
	        initGui();
	    }
	}

	@Override
	public void notifyMessageAdded(Message addedMessage) {
		// TODO Auto-generated method stub
        System.out.println("MessageAdded");

	}

	@Override
	public void notifyMessageDeleted(Message deletedMessage) {
		// TODO Auto-generated method stub
        System.out.println("MessageDeleted");

		
	}

	@Override
	public void notifyMessageModified(Message modifiedMessage) {
		// TODO Auto-generated method stub
        System.out.println("MessageModified");

		
	}

	@Override
	public void notifyUserAdded(User addedUser) {
		// TODO Auto-generated method stub
        System.out.println("UserAdded");

		
	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		// TODO Auto-generated method stub
        System.out.println("UserDeleted");

		
	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		// TODO Auto-generated method stub
        System.out.println("UserModified");

		
	}

	@Override
	public void notifyPresentFiles(Set<File> presentFiles) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyNewFiles(Set<File> newFiles) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyDeletedFiles(Set<File> deletedFiles) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyModifiedFiles(Set<File> modifiedFiles) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyLoggedUser(User user) {
		// TODO Auto-generated method stub
		
	}
	
	
}
