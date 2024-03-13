package com.ubo.tp.message.ihm.components.messages.afficherMessages;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.ubo.tp.message.datamodel.Message;
import com.ubo.tp.message.ihm.IViewObservable;
import com.ubo.tp.message.ihm.components.app.IMainOberserver;

public class Msg extends JPanel implements IViewObservable<IMsgObserver> {

    protected Message message;
    protected IMsgObserver observer;
    protected IMainOberserver mainOberserver;

    public Msg(Message message, IMsgObserver observe, IMainOberserver mainOberserver) {
        this.message = message;
        this.mainOberserver = mainOberserver;
        this.addObserver(observe);
        initGUI();
    }

    /**
     * Initialisation de l'IHM
     */
//    protected void initGUI() {
//        Font font = new Font("Roboto", Font.BOLD, 12);
//        this.removeAll();
//
//        this.setBorder(new EmptyBorder(5, 5, 5, 5));
//        this.setLayout(new GridBagLayout());
//        GridBagConstraints c = new GridBagConstraints();
//
//        this.setBackground(Color.WHITE);
//
//
//        JLabel jlblTitle = new JLabel(this.message.getSender().getName());
//        jlblTitle.setFont(font);
//        ImageIcon imageIcon = new ImageIcon(this.message.getSender().getAvatarPath()); // load the image to a imageIcon
//        Image image = imageIcon.getImage(); // transform it
//        Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
//        jlblTitle.setIcon(new ImageIcon(newimg));
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridx = 0;
//        c.gridy = 0;
//        this.add(jlblTitle, c);
//
//        JLabel jlblDate = new JLabel(new Date(this.message.getEmissionDate()).toString(), SwingConstants.RIGHT);
//        jlblDate.setFont(font);
//        c.gridx = 1;
//        c.gridy = 0;
//        this.add(jlblDate, c);
//
//        JLabel jlblText = new JLabel(this.message.getText(), SwingConstants.CENTER);
//        jlblText.setFont(font);
//        c.gridx = 0;
//        c.gridy = 2;
//        this.add(jlblText, c);
//
//        JButton profilBtn = new JButton("Consulter le profil");
//
//        this.add(profilBtn, c);
//}


    protected void initGUI() {
        Font font = new Font("Roboto", Font.BOLD, 12);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        this.removeAll();
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        this.setBackground(Color.WHITE);

        JLabel jlblTitle = new JLabel(this.message.getSender().getName());
        jlblTitle.setFont(font);

        ImageIcon imageIcon = new ImageIcon(this.message.getSender().getAvatarPath());
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH); // Set size to 50px
        jlblTitle.setIcon(new ImageIcon(newimg));

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        this.add(jlblTitle, c);

        JLabel jlblDate = new JLabel(dateFormat.format(new Date(this.message.getEmissionDate())), SwingConstants.RIGHT);
        jlblDate.setFont(font);
        c.gridx = 1;
        c.gridy = 0;
        this.add(jlblDate, c);

        JLabel jlblText = new JLabel("<html><div style='text-align: center;'>" + this.message.getText() + "</div></html>", SwingConstants.CENTER);
        jlblText.setFont(font);
        c.gridx = 0;
        c.gridy = 2;
        this.add(jlblText, c);


        ImageIcon profileIcon = new ImageIcon(this.message.getSender().getAvatarPath());
        Image profileImage = profileIcon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH); // Set size to 50px
        JButton profilBtn = new JButton("Consulter le profil");
        profilBtn.addActionListener(e -> Msg.this.mainOberserver.switchToMessageProfilePage(message.getSender().getUserTag()));
        //profilBtn.addActionListener(e -> System.out.println(message.getSender().getUserTag()));
        profilBtn.setFont(font.deriveFont(Font.PLAIN));
        profilBtn.setToolTipText("Click to view user profile");

        c.gridx = 0;
        c.gridy = 3;
        this.add(profilBtn, c);

        profilBtn.addActionListener(e -> {
            // Handle button click action here
        });
    }


//    protected void initGUI() {
//        Font font = new Font("Roboto", Font.BOLD, 12);
//        this.removeAll();
//
//        this.setBorder(new EmptyBorder(5, 5, 5, 5));
//        this.setLayout(new GridBagLayout());
//        GridBagConstraints c = new GridBagConstraints();
//
//        this.setBackground(Color.WHITE);
//
//        // Configure les contraintes de base
//        c.insets = new Insets(5, 5, 5, 5); // Ajoute un espacement autour des composants
//        c.fill = GridBagConstraints.HORIZONTAL;
//
//        // Titre avec l'icône de l'expéditeur
//        JLabel jlblTitle = new JLabel(this.message.getSender().getName());
//        jlblTitle.setFont(font);
//        ImageIcon imageIcon = new ImageIcon(this.message.getSender().getAvatarPath());
//        Image image = imageIcon.getImage();
//        Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
//        jlblTitle.setIcon(new ImageIcon(newimg));
//        c.gridx = 0;
//        c.gridy = 0;
//        c.gridwidth = 2; // Permet au titre de s'étendre sur deux colonnes
//        this.add(jlblTitle, c);
//
//        // Date du message, alignée à droite
//        JLabel jlblDate = new JLabel(new Date(this.message.getEmissionDate()).toString(), SwingConstants.RIGHT);
//        jlblDate.setFont(font);
//        c.gridx = 1;
//        c.gridy = 1;
//        c.gridwidth = 1; // Remet la largeur à 1 pour les autres composants
//        this.add(jlblDate, c);
//
//        // Texte du message, centré
//        JLabel jlblText = new JLabel("<html><div style='text-align: center;'>" + this.message.getText() + "</div></html>", SwingConstants.CENTER);
//        jlblText.setFont(font);
//        c.gridx = 0;
//        c.gridy = 2;
//        c.gridwidth = 2; // Permet au texte de s'étendre sur deux colonnes pour une meilleure lecture
//        this.add(jlblText, c);
//
//        // Bouton pour consulter le profil, placé sous le texte du message
//        JButton profilBtn = new JButton("Consulter le profil");
//        c.gridx = 0;
//        c.gridy = 3;
//        c.gridwidth = 2; // Le bouton s'étend également sur deux colonnes
//        this.add(profilBtn, c);
//    }

    @Override
    public void addObserver(IMsgObserver observer) {
        this.observer = observer;
    }

    @Override
    public void deleteObserver(IMsgObserver observer) {
        this.observer = null;
    }
}
