package main.java.com.ubo.tp.message.ihm.components.profil;



import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.IViewObservable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

public class MessageAppProfil extends JPanel implements IViewObservable<IProfilObserver> {

    protected User user;
    protected IProfilObserver observer;
    protected JLabel jlblStatus;

    public MessageAppProfil(User user) {
        this.user = user;
        initGUI();
    }

    /**
     * Initialisation de l'IHM
     */
    protected void initGUI() {
        this.setBorder(new EmptyBorder(50, 50, 50, 50));
        this.setBackground(Color.ORANGE);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        JLabel jlblTitle = new JLabel("Mon profil");
        jlblTitle.setFont(new Font("Roboto", Font.BOLD, 12));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        this.add(jlblTitle, c);

        //TAG
        JLabel jlblTag = new JLabel("Tag :");
        JTextField jtfieldTag = new JTextField(this.user.getUserTag());
        jtfieldTag.setEditable(false);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.5;
        this.add(jlblTag, c);
        c.gridx = 1;
        this.add(jtfieldTag, c);

        //NOM
        JLabel jlblNom = new JLabel("Nom :");
        JTextField jtfieldNom = new JTextField(this.user.getName());
        c.gridx = 0;
        c.gridy = 2;
        this.add(jlblNom, c);
        c.gridx = 1;
        this.add(jtfieldNom, c);

        //AVATAR
        JLabel jlblAvatar = new JLabel("Avatar :");
        JButton avatarBtn = new JButton();
        ImageIcon imageIcon = new ImageIcon(user.getAvatarPath()); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH); // scale it the smooth way
        avatarBtn.setIcon(new ImageIcon(newimg));
        avatarBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
            fileChooser.setFileFilter(filter);
            if (fileChooser.showOpenDialog(MessageAppProfil.this) == JFileChooser.APPROVE_OPTION) {
                user.setAvatarPath(fileChooser.getSelectedFile().getAbsolutePath());
            }
        });
        c.gridx = 0;
        c.gridy = 3;
        this.add(jlblAvatar, c);
        c.gridx = 1;
        this.add(avatarBtn, c);

        //MOT DE PASSE
        JLabel jlblMdp = new JLabel("Mot de passe :");
        JPasswordField jtfieldPassword = new JPasswordField(user.getUserPassword());
        c.gridx = 0;
        c.gridy = 4;
        this.add(jlblMdp, c);
        c.gridx = 1;
        this.add(jtfieldPassword, c);

        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 0;
        jlblStatus = new JLabel("");
        jlblStatus.setForeground(Color.GREEN);
        this.add(jlblStatus, c);

        //BTN VALIDATE
        JButton submitBtn = new JButton("Modifier");
        submitBtn.addActionListener(e -> {
            user.setName(jtfieldNom.getText());
            user.setUserPassword(new String(jtfieldPassword.getPassword()));
            MessageAppProfil.this.observer.modifyAccount(user);
            MessageAppProfil.this.jlblStatus.setText("Profil modifié");
        });
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        this.add(submitBtn, c);
    }



    @Override
    public void addObserver(IProfilObserver observer) {
        this.observer = observer;
    }

    @Override
    public void deleteObserver(IProfilObserver observer) {
        // TODO document why this method is empty
    }
}
