package main.java.com.ubo.tp.message.ihm.components.app;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.Database;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.core.database.IDatabaseObserver;
import main.java.com.ubo.tp.message.core.directory.IWatchableDirectory;
import main.java.com.ubo.tp.message.core.directory.WatchableDirectory;
import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.components.dashMessagerie.MessageriePage;
import main.java.com.ubo.tp.message.ihm.components.login.MessageConnexionView;
import main.java.com.ubo.tp.message.ihm.components.messages.afficherMessages.AfficherListMessagesView;
import main.java.com.ubo.tp.message.ihm.components.messages.ajouterMessage.AjouterMessageView;
import main.java.com.ubo.tp.message.ihm.components.profil.MessageAppProfil;
import main.java.com.ubo.tp.message.ihm.components.register.RegisterView;
import main.java.com.ubo.tp.message.ihm.components.userprofile.MessageAppUserProfile;
import main.java.com.ubo.tp.message.ihm.components.userss.ListUserView;
import main.java.com.ubo.tp.message.ihm.controllers.*;
import main.java.com.ubo.tp.message.ihm.session.ISessionObserver;
import main.java.com.ubo.tp.message.ihm.session.Session;
import mock.MessageAppMock;

import javax.swing.*;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class MessageApp implements IMainOberserver, ISessionObserver, IDatabaseObserver {
    /**
     * Base de données.
     */
    protected IDatabase mDatabase;

    
    protected Session mSession;

    /**
     * Gestionnaire des entités contenu de la base de données.
     */
    protected EntityManager mEntityManager;

    /**
     * Vue principale de l'application.
     */
    protected MessageAppMainView mMainView;

    /**
     * Classe de surveillance de répertoire
     */
    protected IWatchableDirectory mWatchableDirectory;

    /**
     * Répertoire d'échange de l'application.
     */
    protected String mExchangeDirectoryPath;

    /**
     * Idnique si le mode bouchoné est activé.
     */
    protected boolean mIsMockEnabled = true;

    /**
     * Nom de la classe de l'UI.
     */
    protected String mUiClassName;

    protected JPanel currentPanel;


    /**
     * Constructeur.
     */
    public MessageApp() {
    	
    	this.mSession = new Session();
        this.mSession.addObserver(this);
    			// Init du look and feel de l'application
        this.initLookAndFeel();
        // Initialisation de la base de données
        this.initDatabase();
//        if (this.mIsMockEnabled) {
//            // Initialisation du bouchon de travail
//            this.initMock();
//        }

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
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.err.println("Error" + e);
        }

    }

    /**
     * Initialisation de l'interface graphique.
     */
    protected void initGui() {
        this.mMainView = new MessageAppMainView(mSession);
        this.mMainView.addObserver(this);
        mDatabase.addObserver(this);
    }

    /**
     * Initialisation du répertoire d'échange (depuis la conf ou depuis un file
     * chooser). <br/>
     * <b>Le chemin doit obligatoirement avoir été saisi et être valide avant de
     * pouvoir utiliser l'application</b>
     */
    protected void initDirectory() {
        this.initDirectory("src/main/resources");
    }

    /**
     * Indique si le fichier donné est valide pour servire de répertoire
     * d'échange
     *
     * @param directory , Répertoire à tester.
     */
    protected boolean isValideExchangeDirectory(File directory) {
        // Valide si répertoire disponible en lecture et écriture
        return directory != null && directory.exists() && directory.isDirectory() && directory.canRead()
                && directory.canWrite();
    }

    /**
     * Initialisation du mode bouchoné de l'application
     */
    protected void initMock() {
    	MessageAppMock mock = new MessageAppMock(this.mDatabase, this.mEntityManager);
        mock.showGUI();
    }

    /**
     * Initialisation de la base de données
     */
    protected void initDatabase() {
        mDatabase = new Database();
        mEntityManager = new EntityManager(mDatabase);
    }

    /**
     * Initialisation du répertoire d'échange.
     *
     * @param directoryPath
     */
    public void initDirectory(String directoryPath) {
        mExchangeDirectoryPath = directoryPath;
        mWatchableDirectory = new WatchableDirectory(directoryPath);
        mEntityManager.setExchangeDirectory(directoryPath);

        mWatchableDirectory.initWatching();
        mWatchableDirectory.addObserver(mEntityManager);
    }

    public void show() {
        this.mMainView.showGUI();
    }

    public void changeCurrentPanelMainView(JPanel panel) {
        if (this.currentPanel != null) this.mMainView.remove(this.currentPanel);
        this.currentPanel = panel;
        this.mMainView.add(currentPanel);
        this.mMainView.revalidate();
        this.mMainView.repaint();
    }

   

    @Override
    public void notifyDirectoryChanged(File file) {
        System.out.println("Répertoire changé");
        MessageApp.this.initDirectory(file.getAbsolutePath());
    }

    @Override
    public void deconnectUser() {
        this.mMainView.initMenuBar(null);
        this.switchToConnexionCompenent();
    }


    @Override
    public void notifyLoggedUser(User user) {
        System.out.println("User logged");

    }

    @Override
	public void notifyMessageAdded(Message addedMessage) {

        // TODO Auto-generated method stub

		if(this.mSession.getConnectedUser()!=null) {
            if (this.mSession.getConnectedUser().getFollows().contains(addedMessage.getSender().getUserTag())) {
                JOptionPane.showMessageDialog(null, "L'utilisateur Tag :  " + addedMessage.getSender().getUserTag() + " vient de publier un message");
                System.out.println("Un messaga vient de s'ajouter par : " + addedMessage.getSender().getName());
            } else {

            }
            this.switchToMessagerieComponent();
        }

	}

	@Override
	public void notifyMessageDeleted(Message deletedMessage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyMessageModified(Message modifiedMessage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyUserAdded(User addedUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
    public void notifyUserModified(User modifiedUser) {
        System.out.println("User modified :++");



    }

    private void updateConnectedUserInfo(User modifiedUser) {
        User connectedUser = this.mSession.getConnectedUser();
        connectedUser.setUserPassword(modifiedUser.getUserPassword());
        connectedUser.setName(modifiedUser.getName());
        connectedUser.setAvatarPath(modifiedUser.getAvatarPath());

    }


    @Override
	public void switchToConnexionCompenent() {
		// TODO Auto-generated method stub
		MessageConnexionView connexionView = new MessageConnexionView();
        this.changeCurrentPanelMainView(connexionView);
        connexionView.addObserver(new ConnexionController(this.mDatabase, this.mEntityManager, this.mSession));
		
	}

	@Override
	public void switchToRegesterCompenent() {
		// TODO Auto-generated method stub
		RegisterView registerView = new RegisterView();
		this.changeCurrentPanelMainView(registerView);
		registerView.addObserver(new RegisterController(this.mDatabase, this.mEntityManager, this.mSession));
	}

	@Override
	public void switchToSendMessageComponent() {
		// TODO Auto-generated method stub
		AjouterMessageView ajouterMessageView = new AjouterMessageView();
		this.changeCurrentPanelMainView(ajouterMessageView);
		ajouterMessageView.addObserver(new AjouterMessageController(mDatabase, mEntityManager, mSession));
		
	}
	
	@Override
	public void switchToListMessageComponent() {
		// TODO Auto-generated method stub
		AfficherListMessagesView afficherListMessageView = new AfficherListMessagesView(this.mDatabase.getMessages(), new MsgController(this.mDatabase, this.mEntityManager, this.mSession), this);
		this.changeCurrentPanelMainView(afficherListMessageView);
		afficherListMessageView.addObserver(new ListMessageController(mDatabase, mEntityManager, mSession));
		
	}


    @Override
    public void switchToListUserPage() {
        ListUserView ListUser = new ListUserView(this.mDatabase.getUsers(), this);
        this.changeCurrentPanelMainView(ListUser);
        ListUser.addObserver(new ListUserController(this.mDatabase, this.mEntityManager,this.mSession));

    }

    public void switchToMessageProfilePage(String MessageUserTag) {
        System.out.println("MessageUserTag :" + MessageUserTag );
        MessageAppUserProfile messageApprProfile = new MessageAppUserProfile(MessageUserTag, new MessageProfileController(this.mDatabase, this.mEntityManager,this.mSession , this));
        this.changeCurrentPanelMainView(messageApprProfile);
        messageApprProfile.addObserver(new MessageProfileController(this.mDatabase,this.mEntityManager,this.mSession,this));

    }

    @Override
    public void switchToProfilOrToUserProfil(String UserTag) {
        if(UserTag.equals(this.mSession.getConnectedUser().getUserTag()))
            this.switchToProfilPage();
        else
            this.switchToMessageProfilePage(UserTag);
    }

    @Override
    public void switchToProfilPage() {
        MessageAppProfil messageAppProfil = new MessageAppProfil(this.mSession.getConnectedUser());
        this.changeCurrentPanelMainView(messageAppProfil);
        messageAppProfil.addObserver(new ProfilController(this.mDatabase, this.mEntityManager,this.mSession));
    }
	
	@Override
	public void switchToMessagerieComponent() {
//		this.currentPanel.removeAll();
		// TODO Auto-generated method stub
//		Messa afficherListMessageView = new AfficherListMessagesView(this.mDatabase.getMessages(), new MsgController(this.mDatabase, this.mEntityManager, this.mSession), this);
//		this.changeCurrentPanelMainView(afficherListMessageView);
//		afficherListMessageView.addObserver(new ListMessageController(mDatabase, mEntityManager, mSession));
		
		
		// TODO Auto-generated method stub
				AfficherListMessagesView afficherListMessageView = new AfficherListMessagesView(this.mDatabase.getMessages(), new MsgController(this.mDatabase, this.mEntityManager, this.mSession), this);
				this.changeCurrentPanelMainView(afficherListMessageView);
				afficherListMessageView.addObserver(new ListMessageController(mDatabase, mEntityManager, mSession));
				
				AjouterMessageView ajouterMessageView = new AjouterMessageView();
				this.changeCurrentPanelMainView(ajouterMessageView);
				ajouterMessageView.addObserver(new AjouterMessageController(mDatabase, mEntityManager, mSession));
				
		MessageriePage messPage =  new MessageriePage(ajouterMessageView, afficherListMessageView);
		this.changeCurrentPanelMainView(messPage);
		
	}

	@Override
	public void notifyLogin(User connectedUser) {
		// TODO Auto-generated method stub
		System.out.println("un utilsateur a connecté");
		this.switchToMessagerieComponent();
        this.mMainView.menuListUser.setEnabled(true);
        this.mMainView.profileMenu.setEnabled(true);
        this.mMainView.messagesListMenu.setEnabled(true);

    }

	@Override
	public void notifyLogout() {
		// TODO Auto-generated method stub
		
	}
}

    