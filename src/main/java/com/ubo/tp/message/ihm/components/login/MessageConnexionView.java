package main.java.com.ubo.tp.message.ihm.components.login;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.java.com.ubo.tp.message.ihm.IViewObservable;

public class MessageConnexionView extends JPanel implements IViewObservable<IConnexionObserver> {

    private final JLabel jlblUsername = new JLabel("Tag");
    private final JLabel jlblPassword = new JLabel("Mot de passe");
    private final JTextField jtfUsername = new JTextField(15);
    private final JPasswordField jpfPassword = new JPasswordField();
    private final JButton jbtOk = new JButton("Connexion");
    private final JButton jbtCancel = new JButton("Retour");
    private final JLabel jlblStatus = new JLabel(" ");

    private IConnexionObserver observer;

    public MessageConnexionView() {
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
    	// Mettre la fenêtre en plein écran
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(new Dimension(screenSize.width / 2, screenSize.height / 2));
        this.setBorder(new EmptyBorder(10,   10,10,10));
        this.setBackground(Color.CYAN);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel jlblTitle = new JLabel("Connexion");
        jlblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        jlblTitle.setFont(new Font("Roboto", Font.BOLD, 12));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 0;
        this.add(jlblTitle, c);

        c.gridwidth = 1;
        c.gridy++;
        this.add(jlblUsername, c);
        c.gridx++;
        this.add(jtfUsername, c);
        c.gridx = 0;
        c.gridy++;
        this.add(jlblPassword, c);
        c.gridx++;
        this.add(jpfPassword, c);
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy++;
        this.add(jlblStatus, c);
        c.gridy++;
        c.gridwidth = 1;
        this.add(jbtCancel, c);
        c.gridx++;
        this.add(jbtOk, c);

        jlblStatus.setForeground(Color.RED);
        jlblStatus.setHorizontalAlignment(SwingConstants.CENTER);


        jbtOk.addActionListener(e -> MessageConnexionView.this.connexion());

        jbtCancel.addActionListener(e -> setVisible(false));

        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // TODO document why this method is empty
            }

            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) MessageConnexionView.this.connexion();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO document why this method is empty
            }
        };
        jpfPassword.addKeyListener(keyListener);

    }

    @Override
    public void addObserver(IConnexionObserver observer) {
        this.observer = observer;
    }

    @Override
    public void deleteObserver(IConnexionObserver observer) {
        // TODO document why this method is empty
    }

    public void connexion() {
        if (jtfUsername.getText().length() == 0 || jpfPassword.getPassword().length == 0) {
            jlblStatus.setText("Veuillez entrer un tag et un mot de passe");
        } else {
            if (!observer.checkConnexion(jtfUsername.getText(), new String(jpfPassword.getPassword()))) {
                jlblStatus.setText("Tag ou mot de passe invalide");
            }
        }
    }
}
