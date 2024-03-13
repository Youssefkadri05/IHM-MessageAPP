package com.ubo.tp.message.ihm.components.dashMessagerie;


import javax.swing.*;

import com.ubo.tp.message.ihm.IViewObservable;
import com.ubo.tp.message.ihm.components.login.IConnexionObserver;
import com.ubo.tp.message.ihm.components.messages.afficherMessages.AfficherListMessagesView;
import com.ubo.tp.message.ihm.components.messages.ajouterMessage.AjouterMessageView;
import com.ubo.tp.message.ihm.components.messages.ajouterMessage.IAjouterMessageObserver;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageriePage extends JPanel implements IViewObservable<IMessageriePage> {
	 private JTextField textField;
	private JButton sendButton;
	private JTextArea messageArea;
	private JScrollPane scrollPane;
	    


//	    public MessageriePage(AjouterMessageView panelAjouterMsg, AfficherListMessagesView afficherListMessageView ) {
//	        super(new BorderLayout());
//
//	        // Left panel for text field and send button
//	        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//
//	        //leftPanel.add(panelAjouterMsg);
//
//	        // Right panel for message area with scroll pane
//	        messageArea = new JTextArea();
//	        messageArea.setEditable(false); // Prevent user editing the message area
//	        messageArea.setBackground(Color.RED); // Light grey background
//	        scrollPane = new JScrollPane(messageArea);
//	        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove default border
//
//	        // Add panels to main panel
//	        add(panelAjouterMsg, BorderLayout.WEST);
//	        add(afficherListMessageView, BorderLayout.CENTER);
//
//	        // Set preferred size for the panel
//	        setPreferredSize(new Dimension(600, 400));
//
//
//	    }
public MessageriePage(AjouterMessageView panelAjouterMsg, AfficherListMessagesView afficherListMessageView) {
	super(new BorderLayout());

	// Left panel for text field and send button
	JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	//leftPanel.add(panelAjouterMsg); // Supposant que vous vouliez ajouter quelque chose ici plus tard

	// Création d'un JScrollPane autour de afficherListMessageView pour permettre le défilement
	JScrollPane listMessagesScrollPane = new JScrollPane(afficherListMessageView);
	listMessagesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	listMessagesScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	listMessagesScrollPane.setBorder(BorderFactory.createEmptyBorder()); // Retirer la bordure pour un look épuré

	// Ajout du panelAjouterMsg et du JScrollPane contenant afficherListMessageView au layout principal
	add(panelAjouterMsg, BorderLayout.WEST);
	add(listMessagesScrollPane, BorderLayout.CENTER); // Modifier ici pour ajouter le JScrollPane

	// Définition de la taille préférée pour le panneau principal
	setPreferredSize(new Dimension(600, 400));
}

	    private String getTimeStamp() {
	        // You can use a library like Joda-Time for better formatting
	        return new java.util.Date().toString(); // Simple timestamp
	    }

	    private void addMessage(String timeStamp, ImageIcon icon, String message) {
	        StringBuilder sb = new StringBuilder();
	        if (icon != null) {
	            // Add icon logic here (you might need a JLabel with the icon)
	            sb.append("[Icon] ");
	        }
	        sb.append("[").append(timeStamp).append("] ").append(message).append("\n");
	        messageArea.append(sb.toString());
	        // Scroll to the bottom after adding a new message
	        scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
	    }

	

	@Override
	public void addObserver(IMessageriePage observer) {
		// TODO Auto-generated method stub

		
	}

	@Override
	public void deleteObserver(IMessageriePage observer) {
		// TODO Auto-generated method stub

		
	}

  
}