package main.java.com.ubo.tp.message.ihm.components.register;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import main.java.com.ubo.tp.message.ihm.IViewObservable;

public class RegisterView extends JPanel implements IViewObservable<IRegisterObserver> {

	    protected JTextField nom;
	    protected JTextField tag;
	    protected String avatar;
	    protected JPasswordField password;
	    protected JLabel jlblStatus;
	    protected IRegisterObserver observer;

	    /**
	     * Gestionnaire de bdd et de fichier.
	     */

	    public RegisterView() {
	        initGUI();
	    }

	    public void createAccount() {
	        String tNom = this.nom.getText();
	        String tTag = this.tag.getText();
	        String tAvatar = this.avatar;
	        String tPassword = new String(this.password.getPassword());
	        jlblStatus.setForeground(Color.RED);

	        if (tNom.equals("") || tTag.equals("")) {
	            this.jlblStatus.setText("Tag | Nom | Mdp requis");
	        } else if (observer.isAccountExist(tTag)) {
	            this.jlblStatus.setText("Tag déjà existant");
	        } else if (!observer.isPasswordValid(tPassword)) {
	            this.jlblStatus.setText("Le mot de passe n'est pas assez sécurisé");
	        } else {
	            observer.notifyRegister(tTag, tPassword, tNom, null, tAvatar);
	        }

	    }

	    /**
	     * Initialisation de l'IHM
	     */
	    protected void initGUI() {
	        this.setBorder(new EmptyBorder(5, 5, 5, 5));
	        this.setBackground(Color.ORANGE);
	        this.setLayout(new GridBagLayout());
	        GridBagConstraints c = new GridBagConstraints();


	        JLabel jlblTitle = new JLabel("Inscription");
	        jlblTitle.setHorizontalAlignment(SwingConstants.CENTER);
	        jlblTitle.setFont(new Font("Roboto", Font.BOLD, 12));
	        c.fill = GridBagConstraints.HORIZONTAL;
	        c.gridx = 0;
	        c.gridwidth = 2;
	        c.gridy = 0;
	        this.add(jlblTitle, c);

	        c.gridwidth = 1;
	        JLabel jlblNom = new JLabel("nom");
	        c.gridy++;
	        this.add(jlblNom, c);
	        this.nom = new JTextField();
	        c.gridx++;
	        this.add(nom, c);
	        JLabel jlblTag = new JLabel("tag");
	        c.gridy++;
	        c.gridx = 0;
	        this.add(jlblTag, c);
	        this.tag = new JTextField();
	        c.gridx++;
	        this.add(tag, c);
	        JLabel jlblAvatar = new JLabel("avatar");
	        c.gridy++;
	        c.gridx = 0;
	        this.add(jlblAvatar, c);
	        JButton avatarBtn = new JButton("Choisir");
	        avatarBtn.addActionListener(e -> {
	            JFileChooser fileChooser = new JFileChooser();
	            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
	            fileChooser.setFileFilter(filter);
	            if (fileChooser.showOpenDialog(RegisterView.this) == JFileChooser.APPROVE_OPTION) {
	            	RegisterView.this.avatar = fileChooser.getSelectedFile().toString();
	                avatarBtn.setText(fileChooser.getSelectedFile().getName());
	            }
	        });

	        c.gridx++;
	        this.add(avatarBtn, c);

	        JLabel jlblMdp = new JLabel("mot de passe");
	        c.gridy++;
	        c.gridx = 0;
	        this.add(jlblMdp, c);

	        this.password = new JPasswordField();
	        c.gridx++;
	        this.add(password, c);

	        jlblStatus = new JLabel("");
	        jlblStatus.setForeground(Color.RED);
	        c.gridy++;
	        c.gridx = 0;
	        this.add(jlblStatus, c);

	        JButton btn = new JButton("Créer un compte");
	        btn.addActionListener(e -> RegisterView.this.createAccount());
	        c.gridx = 0;
	        c.gridy++;
	        c.gridwidth = 2;
	        this.add(btn, c);

	    }

	  

		@Override
		public void addObserver(IRegisterObserver observer) {
			// TODO Auto-generated method stub
	        this.observer = observer;

			
		}

		@Override
		public void deleteObserver(IRegisterObserver observer) {
			// TODO Auto-generated method stub
			
		}
	}
