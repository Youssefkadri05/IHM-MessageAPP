package com.ubo.tp.message.ihm.components.userprofile;



import com.ubo.tp.message.datamodel.User;
import com.ubo.tp.message.ihm.IViewObservable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.Clock;

public class MessageAppUserProfile extends JPanel implements IViewObservable<IMessageProfileObserver> {

    protected User user;
    protected IMessageProfileObserver observer;
    JButton jbtnFollow = new JButton();

    public MessageAppUserProfile(String userTag, IMessageProfileObserver observer) {
        this.addObserver(observer);
        this.user = this.observer.getUserFromTag(userTag);
        initGUI();
    }

    /**
     * Initialisation de l'IHM
     */
    protected void initGUI() {
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setBackground(Color.ORANGE);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c;

        JLabel jlblTitle = new JLabel("Profil de " + user.getName());
        jlblTitle.setFont(new Font("Roboto", Font.BOLD, 12));
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        this.add(jlblTitle, c);

        JLabel jlblTag = new JLabel("@" + user.getUserTag());
        jlblTag.setFont(new Font("Roboto", Font.BOLD + Font.ITALIC, 12));

        ImageIcon imageIcon = new ImageIcon(user.getAvatarPath()); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH); // scale it the smooth way

        jlblTag.setIcon(new ImageIcon(newimg));
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 1;
        this.add(jlblTag, c);

        JLabel jlblNbMessages = new JLabel(this.observer.getNbMessagesPostedByUser(user) + " messages envoyés");
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridy = 2;
        this.add(jlblNbMessages, c);

        JLabel jlblNbFollowers = new JLabel(this.observer.getNbFollowersByUser(user) + " follower(s)");
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridy = 4;
        this.add(jlblNbFollowers, c);

        // Assurez-vous que jbtnFollow est initialisé
        jbtnFollow = new JButton(); // Assurez-vous que cela est fait avant de l'ajouter au panneau
        initButtonFollow(); // Configure le texte et les actions du bouton

        // Configuration de GridBagConstraints pour le bouton
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5; // Ajustez le GridY selon l'endroit où vous souhaitez placer le bouton
        this.add(jbtnFollow, c); // Ajoutez le bouton au panneau

        this.revalidate();
        this.repaint(); // Assurez-vous que les changements sont bien appliqués

    }

    public void initButtonFollow() {
        // Mise à jour de l'état de suivi
        updateFollowState();

        // Rafraîchir l'affichage pour refléter le changement d'état
        this.revalidate();
        this.repaint();
    }

    private void updateFollowState() {
        boolean isAlreadyFollowed = observer.isAlreadyFollowedByUser(user);
        jbtnFollow.setText(isAlreadyFollowed ? "Ne plus suivre" : "Suivre");
        // Retirez les précédents ActionListener pour éviter les actions multiples
        for (ActionListener al : jbtnFollow.getActionListeners()) {
            jbtnFollow.removeActionListener(al);
        }
        // Ajoutez le bon ActionListener en fonction de l'état actuel de suivi
        if (isAlreadyFollowed) {
            jbtnFollow.addActionListener(e -> {
                observer.unfollowUser(user);
                updateFollowState(); // Mettez à jour immédiatement le texte du bouton
            });
        } else {
            jbtnFollow.addActionListener(e -> {
                observer.followUser(user);
                updateFollowState(); // Mettez à jour immédiatement le texte du bouton
            });
        }
    }


    @Override
    public void addObserver(IMessageProfileObserver observer) {
        this.observer = observer;
    }

    @Override
    public void deleteObserver(IMessageProfileObserver observer) {
        // TODO document why this method is empty
    }
}