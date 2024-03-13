package main.java.com.ubo.tp.message.ihm.components.app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.IViewObservable;
import main.java.com.ubo.tp.message.ihm.components.login.MessageConnexionView;
import main.java.com.ubo.tp.message.ihm.session.Session;

/**
 * Classe de la vue principale de l'application.
 */
public class MessageAppMainView extends JFrame implements IViewObservable<IMainOberserver> {

    /**
     * Liste des observers
     */
    protected IMainOberserver observer;
    protected JMenuItem  menuListUser;
    protected JMenuItem profileMenu;
    protected JMenuItem messagesListMenu;
    /**
     * User courant
     */
    protected User mUser;
    protected Session mSession;

    public MessageAppMainView(Session session) {
        // Init auto de l'IHM
        this.mSession=session;
        this.initGUI();

    }

    /**
     * Initialisation de l'IHM
     */
    protected void initGUI() {
        this.setTitle("MessApp");
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        ImageIcon aboutIcon = createImageIcon("/ressources/images/logo_50.png");
        this.setIconImage(aboutIcon.getImage());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(new Dimension(screenSize.width / 2, screenSize.height / 2));
        this.setLayout(new GridBagLayout()); // Utilisation de GridBagLayout

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH; // Permet au composant de s'étendre à la fois horizontalement et verticalement
        gbc.weightx = 1.0; // Donne un poids extra horizontal
        gbc.weighty = 1.0; // Donne un poids extra vertical


        this.initMenuBar(null);
        this.pack();
    }



    public ImageIcon createImageIcon(String path) {
        ImageIcon icon = new ImageIcon();
        URL imgUrl = getClass().getClassLoader().getResource(path);

        if (imgUrl != null) {
            icon = new ImageIcon(imgUrl);
        } else {
            System.err.println("Couldn't find file: " + path);
        }

        return icon;
    }

    public void initMenuBar(User user) {

        JMenuBar menuBar = new JMenuBar();
        JMenu jMenuCompte = new JMenu("Compte");
        if (user == null) {
            JMenuItem menuInscription = new JMenuItem("Inscription");
            JMenuItem menuConnexion = new JMenuItem("Connexion");
            menuInscription.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MessageAppMainView.this.observer.switchToRegesterCompenent();
                }
            });

            menuConnexion.addActionListener(e -> MessageAppMainView.this.observer.switchToConnexionCompenent());

            jMenuCompte.add(menuInscription);
            jMenuCompte.add(menuConnexion);
        }
        menuBar.add(jMenuCompte);

        menuListUser = new JMenuItem ("Liste utilisateurs");
        profileMenu = new JMenuItem ("Mon profile");
        messagesListMenu = new JMenuItem("Liste de messgaes");
        menuListUser.addActionListener(e -> MessageAppMainView.this.observer.switchToListUserPage());
        profileMenu.addActionListener(e -> MessageAppMainView.this.observer.switchToProfilPage());
        messagesListMenu.addActionListener(e -> MessageAppMainView.this.observer.switchToMessagerieComponent());
        if (this.mSession.getConnectedUser() != null){
            menuListUser.setEnabled(true);
            profileMenu.setEnabled(true);
            messagesListMenu.setEnabled(true);
        }else {
            menuListUser.setEnabled(false);
            profileMenu.setEnabled(false);
            messagesListMenu.setEnabled(false);
        }
        menuBar.add(menuListUser);
        menuBar.add(profileMenu);
        menuBar.add(messagesListMenu);



        ImageIcon aboutIcon = createImageIcon("/images/logo_50.png");
        JMenu menuOther = new JMenu("?");
        JMenuItem menuAbout = new JMenuItem("A Propos");
        menuAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JOptionPane.showMessageDialog(null,
                        new JLabel("<html><h5 class='text-align:'center''>UBO M2 TILL-A<br/>Département Informatique</h5></html>"),
                        "A propos",
                        JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon(aboutIcon.getImage()));
            }

        });
        menuOther.add(menuAbout);
        menuOther.addSeparator();
        JMenuItem menuLeave = new JMenuItem("Quitter");
        ImageIcon menuIcon = createImageIcon("images/exitIcon_20.png");
        menuLeave.setIcon(menuIcon);
        menuLeave.addActionListener(arg0 -> MessageAppMainView.this.leave());
        menuOther.add(menuLeave);


        menuBar.add(menuOther);

        this.setJMenuBar(menuBar);
    }

    /**
     * Lance l'afficahge de l'IHM.
     */
    public void showGUI() {
        // Affichage dans l'EDT
        SwingUtilities.invokeLater(() -> {
            // Custom de l'affichage
            JFrame frame = MessageAppMainView.this;
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setLocation((screenSize.width - frame.getWidth()) / 6,
                    (screenSize.height - frame.getHeight()) / 4);
            frame.setBackground(Color.BLUE);

            // Affichage
            frame.setVisible(true);

            frame.pack();
        });
    }

    /**
     * Quitte l'application
     */
    protected void leave() {
        System.exit(0);
    }

    @Override
    public void addObserver(IMainOberserver observer) {
        this.observer = observer;
    }

    @Override
    public void deleteObserver(IMainOberserver observer) {
        this.observer = null;
    }
}
