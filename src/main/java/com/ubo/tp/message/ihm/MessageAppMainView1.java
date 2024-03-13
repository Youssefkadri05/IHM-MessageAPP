package main.java.com.ubo.tp.message.ihm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.awt.*;

public class MessageAppMainView1 {

    private JFrame mFrame;
    private JMenuBar menuBar;
    private JMenu fileMenu, helpMenu;
    private JMenuItem exitItem, aboutItem;

    public MessageAppMainView1() {
        // Initialisation de la vue principale
       // initGUI();
    }

    protected void initGUI() {
        // Création de la fenêtre principale
        mFrame = new JFrame("MessageApp");
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mFrame.setLayout(new BorderLayout());

        // Configuration du logo
        ImageIcon imgIcon = new ImageIcon("src/main/resources/images/logo_50.png"); 
	    mFrame.setIconImage(imgIcon.getImage());

        // Création et configuration de la barre de menu
        menuBar = new JMenuBar();

        // Menu Fichier
        fileMenu = new JMenu("Fichier");
        exitItem = new JMenuItem("Quitter");
        exitItem.addActionListener((ActionEvent event) -> {
            mFrame.dispose(); // Ferme la fenêtre et termine le programme
        });
        fileMenu.add(exitItem);

        // Menu Aide
        helpMenu = new JMenu("Aide");
        aboutItem = new JMenuItem("À propos");
        aboutItem.addActionListener((ActionEvent event) -> {
            showAboutDialog(); // Affiche la boîte de dialogue "À propos"
        });
        helpMenu.add(aboutItem);

        // Ajout des menus à la barre de menu
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        // Ajouter la barre de menu à la fenêtre
        mFrame.setJMenuBar(menuBar);

        // Configurer la taille et la position initiales de la fenêtre
        mFrame.setSize(600, 400);
        mFrame.setLocationRelativeTo(null); // Centre la fenêtre sur l'écran
        this.show();
    }

    
 // Méthode pour afficher la boîte de dialogue "À propos"
    public void showAboutDialog() {
        // Créez une icône avec l'image désirée
        ImageIcon aboutIcon = new ImageIcon("src/main/resources/images/logo_50.png");
        //ImageIcon aboutIcon = new ImageIcon(getClass().getClassLoader().getResource("images/logo_50.png")); // Assurez-vous que le chemin est correct


     // Créez le message à afficher en utilisant un JPanel pour une mise en page personnalisée
        JPanel messagePanel = new JPanel(new BorderLayout(10, 10));
        messagePanel.add(new JLabel(aboutIcon), BorderLayout.WEST);
        
        // Créez un autre JPanel pour le texte, en utilisant des balises HTML pour centrer le texte
        JPanel textPanel = new JPanel(new GridLayout(0, 1));
        textPanel.add(new JLabel("<html><center>UBO M2-TIIL</center>Département Informatique</html> "));
        messagePanel.add(textPanel, BorderLayout.CENTER);

        // Affichez la boîte de dialogue 'À propos'
        JOptionPane.showMessageDialog(mFrame, messagePanel, "À propos", JOptionPane.PLAIN_MESSAGE);
    }
    public void show() {
        // Affiche la fenêtre principale de l'application
        SwingUtilities.invokeLater(() -> mFrame.setVisible(true));
    }

    public String getFolder() {
        JFileChooser directoryChooser = new JFileChooser();
        directoryChooser.setDialogTitle("Choisissez le répertoire d'échange");
        directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        directoryChooser.setAcceptAllFileFilterUsed(false);

        int result = directoryChooser.showOpenDialog(mFrame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = directoryChooser.getSelectedFile();
            return selectedDirectory.getAbsolutePath();
        } else {
            JOptionPane.showMessageDialog(mFrame, "Aucun répertoire sélectionné.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
