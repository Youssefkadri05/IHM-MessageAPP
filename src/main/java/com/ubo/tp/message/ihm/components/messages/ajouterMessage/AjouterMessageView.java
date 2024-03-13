package com.ubo.tp.message.ihm.components.messages.ajouterMessage;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.ubo.tp.message.ihm.IViewObservable;

public class AjouterMessageView extends JPanel implements IViewObservable<IAjouterMessageObserver> {

	    private final JTextArea jtfMessageContent = new JTextArea();
	    private final JButton jbtMessage = new JButton("Ajouter Message");
	    private final JLabel jlblStatus = new JLabel(" ");

	    private IAjouterMessageObserver observer;

	    public AjouterMessageView() {
	        this.showGUI();
	    }

	    /**
	     * Lance l'afficahge de l'IHM.
	     */
	    public void showGUI() {
	        this.initGUI();
	    }

	    /**
	     * Initialisation de l'IHM
	     */
	    protected void initGUI() {
	        this.setBorder(new EmptyBorder(5, 5, 5, 5));
	        this.setLayout(new GridBagLayout());
	        GridBagConstraints c = new GridBagConstraints();
	        this.setBackground(Color.ORANGE);

	        JLabel jlblTitle = new JLabel("Envoyer un message");
	        jlblTitle.setHorizontalAlignment(SwingConstants.CENTER);
	        jlblTitle.setFont(new Font("Roboto", Font.BOLD, 12));
	        c.fill = GridBagConstraints.HORIZONTAL;
	        c.gridx = 0;
	        c.gridy = 0;
	        this.add(jlblTitle, c);

	        jtfMessageContent.setFont(new Font("Roboto", Font.CENTER_BASELINE, 11));
	        jtfMessageContent.setLineWrap(true);
	        c.gridx = 0;
	        c.gridy++;
	        this.add(jtfMessageContent, c);

	        c.gridx = 0;
	        c.gridy++;
	        jlblStatus.setForeground(Color.RED);
	        jlblStatus.setHorizontalAlignment(SwingConstants.CENTER);
	        this.add(jlblStatus, c);


	        c.gridx = 0;
	        c.gridy++;

	        jbtMessage.addActionListener(e -> {
	            if (AjouterMessageView.this.observer.isValidMessage(jtfMessageContent.getText())) {
	            	AjouterMessageView.this.observer.message(jtfMessageContent.getText());
	                jtfMessageContent.setText("");
	                jlblStatus.setForeground(Color.GREEN);
	                jlblStatus.setText("Votre Message a été envoyé");
	            } else {
	                jlblStatus.setForeground(Color.RED);
	                jlblStatus.setText("Votre message fait plus de 250 caractères");
	            }
	        });

	        this.add(jbtMessage, c);
	    }

	    @Override
	    public void addObserver(IAjouterMessageObserver observer) {
	        this.observer = observer;
	    }

	    @Override
	    public void deleteObserver(IAjouterMessageObserver observer) {
	        // TODO document why this method is empty
	    }
	}
