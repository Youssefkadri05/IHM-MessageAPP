package main.java.com.ubo.tp.message.ihm.components.userss;


import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.IViewObservable;
import main.java.com.ubo.tp.message.ihm.components.app.IMainOberserver;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListUserView extends JPanel implements IViewObservable<IListUserObserver> {

    protected Set<User> listUser;
    protected IListUserObserver observer;
    protected Set<User> listUserFiltered;
    protected IMainOberserver mainOberserver;
    JTextField jtfSearchUser;
    Font fontRoboto = new Font("Roboto", Font.BOLD, 12);

    public ListUserView(Set<User> list, IMainOberserver mainOberserver) {
        this.listUser = list;
        this.listUserFiltered = listUser;
        this.mainOberserver = mainOberserver;
        initGUI();
    }

    /**
     * Initialisation de l'IHM
     */
//    protected void initGUI() {
//        this.setBorder(new EmptyBorder(50, 50, 50, 50));
//        this.setLayout(new GridBagLayout());
//        GridBagConstraints c = new GridBagConstraints();
//
//        JLabel jlblTitle = new JLabel("Liste des utilisateurs");
//        jlblTitle.setHorizontalAlignment(SwingConstants.CENTER);
//        jlblTitle.setFont(fontRoboto.deriveFont(Font.BOLD, 16)); // Increase font size and make it bold
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridx = 0;
//        c.gridy = 2;
//        c.gridwidth = 2;
//        c.insets = new Insets(0, 0, 10, 0); // Add some bottom margin
//        this.add(jlblTitle, c);
//
//        jtfSearchUser = new JTextField();
//        jtfSearchUser.setFont(fontRoboto);
//        c.gridy++;
//        c.insets = new Insets(0, 0, 5, 0); // Add some bottom margin
//        this.add(jtfSearchUser, c);
//
//        JButton jbtnSearch = new JButton("Rechercher");
//        jbtnSearch.setFont(fontRoboto.deriveFont(Font.PLAIN, 14)); // Reduce font size a bit
//        c.gridy++;
//        jbtnSearch.addActionListener(e -> ListUserView.this.search());
//        this.add(jbtnSearch, c);
//
//        c.gridy++;
//        c.weighty = 1.0; // Allow vertical stretching
//        this.add(Box.createVerticalGlue(), c);
//        this.loadUsers();
//    }

    @Override
    public void addObserver(IListUserObserver observer) {
        this.observer = observer;
    }

    @Override
    public void deleteObserver(IListUserObserver observer) {
        // TODO document why this method is empty
    }

//    public void loadUsers() {
//        GridBagConstraints c = new GridBagConstraints();
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridy = 2;
//        for (User user : this.listUserFiltered) {
//            c.gridx = 0;
//            c.gridy++;
//            c.gridwidth = 2;
//            this.add(new JSeparator(), c);
//            c.gridwidth = 1;
//            c.gridy++;
//
//            JLabel jlblUser = new JLabel(user.getName());
//            jlblUser.setFont(fontRoboto);
//            this.add(jlblUser, c);
//
//            JButton btnFollow = new JButton("Consulter le profil");
////            btnFollow.addActionListener(e -> ListUserView.this.mainOberserver.goToMessageProfilePage(user.getUserTag()));
//            btnFollow.setFont(fontRoboto);
//            c.gridx++;
//            this.add(btnFollow, c);
//        }
//        this.revalidate();
//    }

//    public void loadUsers() {
//        GridBagConstraints c = new GridBagConstraints();
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridy = 2;
//
//        JPanel userPanel = new JPanel(new GridBagLayout());
//        userPanel.setBackground(Color.WHITE);
//
//        for (User user : this.listUserFiltered) {
//            c.gridx = 0;
//            c.gridy++;
//            c.gridwidth = 2;
//            userPanel.add(new JSeparator(), c);
//            c.gridwidth = 1;
//            c.gridy++;
//
//            JLabel jlblUser = new JLabel(user.getName());
//            jlblUser.setFont(fontRoboto);
//            userPanel.add(jlblUser, c);
//
//            ImageIcon imageIcon = new ImageIcon(user.getAvatarPath()); // Replace with the actual path
//            Image image = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
//            ImageIcon scaledIcon = new ImageIcon(image);
//
//            JButton btnFollow = new JButton(scaledIcon);
//            btnFollow.setPreferredSize(new Dimension(50, 50)); // Set preferred size
//            btnFollow.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    // Add your logic to handle button click
//                    // mainObserver.goToMessageProfilePage(user.getUserTag());
//                }
//            });
//
//            c.gridx++;
//            userPanel.add(btnFollow, c);
//        }
//
//        JScrollPane scrollPane = new JScrollPane(userPanel);
//        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//
//        c.gridx = 0;
//        c.gridy = 0;
//        c.gridwidth = 2;
//        c.fill = GridBagConstraints.BOTH;
//        add(scrollPane, c);
//
//        revalidate();
//    }

    protected void initGUI() {
        this.setBorder(new EmptyBorder(50, 50, 50, 50));
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel jlblTitle = new JLabel("Liste des utilisateurs");
        jlblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        jlblTitle.setFont(fontRoboto.deriveFont(Font.BOLD, 16)); // Increase font size and make it bold
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 10, 0); // Add some bottom margin
        this.add(jlblTitle, c);

        jtfSearchUser = new JTextField();
        jtfSearchUser.setFont(fontRoboto);
        c.gridy++;
        c.insets = new Insets(0, 0, 5, 0); // Add some bottom margin
        this.add(jtfSearchUser, c);

        JButton jbtnSearch = new JButton("Rechercher");
        jbtnSearch.setFont(fontRoboto.deriveFont(Font.PLAIN, 14)); // Reduce font size a bit
        c.gridy++;
        jbtnSearch.addActionListener(e -> ListUserView.this.search());
        this.add(jbtnSearch, c);

        // Create a scroll pane and add a vertical glue
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        c.gridy++;
        c.weighty = 1.0; // Allow vertical stretching
        c.fill = GridBagConstraints.BOTH;
        this.add(scrollPane, c);

        this.loadUsers(scrollPane); // Pass the scroll pane to loadUsers method
    }

    public void loadUsers(JScrollPane scrollPane) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 2;

        JPanel userPanel = new JPanel(new GridBagLayout());
        userPanel.setBackground(Color.WHITE);

        for (User user : this.listUserFiltered) {
            c.gridx = 0;
            c.gridy++;
            c.gridwidth = 2;
            userPanel.add(new JSeparator(), c);
            c.gridwidth = 1;
            c.gridy++;

            JLabel jlblUser = new JLabel(user.getName());
            jlblUser.setFont(fontRoboto);
            userPanel.add(jlblUser, c);
            //jlblTitle.setIcon(new ImageIcon(newimg));
            jlblUser.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    mainOberserver.switchToProfilOrToUserProfil(user.getUserTag());
                    //mainOberserver.switchToMessageProfilePage(user.getUserTag());
                }
            });
            ImageIcon imageIcon = new ImageIcon(user.getAvatarPath()); // Replace with the actual path
            Image image = imageIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(image);

            JButton btnFollow = new JButton(scaledIcon);
            btnFollow.setPreferredSize(new Dimension(30, 30)); // Set preferred size
            btnFollow.addActionListener(e -> {
                // Add your logic to handle button click
//                 mainObserver.goToMessageProfilePage(user.getUserTag());
            });

            c.gridx++;
            userPanel.add(btnFollow, c);
        }

        // Set the userPanel as the view of the scroll pane
        scrollPane.setViewportView(userPanel);
        revalidate();
    }



    public void search() {
        Pattern pattern = Pattern.compile(jtfSearchUser.getText());

        this.listUserFiltered = new HashSet<User>();

        for (User user : this.listUser) {
            if (("@" + user.getUserTag()).equals(jtfSearchUser.getText())) {
                this.listUserFiltered.add(user);
                break;
            }
            Matcher matcher = pattern.matcher(user.getName());
            while (matcher.find()) {
                this.listUserFiltered.add(user);
            }

        }

        this.removeAll();
        this.initGUI();
    }
}
