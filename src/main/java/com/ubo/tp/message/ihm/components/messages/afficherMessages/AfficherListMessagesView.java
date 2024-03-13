package main.java.com.ubo.tp.message.ihm.components.messages.afficherMessages;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.ihm.IViewObservable;
import main.java.com.ubo.tp.message.ihm.components.app.IMainOberserver;
import main.java.com.ubo.tp.message.ihm.controllers.ListMessageController;
import main.java.com.ubo.tp.message.ihm.controllers.MsgController;
import main.java.com.ubo.tp.message.ihm.session.ISession;

	
	
	public class AfficherListMessagesView extends JPanel implements IViewObservable<IListMessagesObserver> {

	    protected Set<Message> listMessage;
	    protected IListMessagesObserver observer;
	    protected Set<Message> listMessageFiltered;
	    protected MsgController messageController;
	    protected IMainOberserver mainOberserver;
	    JTextField jtfSearch;

		public AfficherListMessagesView(Set<Message> list, MsgController messageController, IMainOberserver mainOberserver) {
	        this.listMessage = list;
	        this.listMessageFiltered = listMessage;
	        this.messageController = messageController;
	        this.mainOberserver = mainOberserver;
	        jtfSearch = new JTextField();
	        initGUI();
	        System.out.println("liste des message  : " + this.listMessage.size());
	    }

	    /**
	     * Initialisation de l'IHM
	     */
	    protected void initGUI() {
	        Font fontRoboto = new Font("Roboto", Font.BOLD, 12);
	        this.removeAll();

	        this.setBorder(new EmptyBorder(5, 5, 5, 5));
	        this.setLayout(new GridBagLayout());
	        GridBagConstraints c = new GridBagConstraints();


	        JLabel jlblTitle = new JLabel("Liste des Message");
	        jlblTitle.setHorizontalAlignment(SwingConstants.CENTER);
	        jlblTitle.setFont(fontRoboto);
	        c.fill = GridBagConstraints.HORIZONTAL;
	        c.gridx = 0;
	        c.gridwidth = 2;
	        c.gridy = 0;
	        this.add(jlblTitle, c);

	        jtfSearch.setFont(fontRoboto);
	        c.gridy++;
	        this.add(jtfSearch, c);

	        JButton jbtnSearch = new JButton("Rechercher");
	        jbtnSearch.setFont(fontRoboto);
	        jbtnSearch.addActionListener(e -> AfficherListMessagesView.this.search());
	        c.gridy++;
	        this.add(jbtnSearch, c);


	        c.gridwidth = 2;
	        c.gridx = 0;

	        for (Message message : this.listMessageFiltered) {
	            c.gridy++;
	            this.add(new JSeparator(), c);
	            c.gridy++;

	            Msg messageView = new Msg(message, this.messageController, this.mainOberserver);
	            this.add(messageView, c);

	        }


	        this.setBackground(Color.PINK);
	        this.revalidate();
	    }

	    @Override
	    public void addObserver(IListMessagesObserver observer) {
	        this.observer = observer;
	    }

	    @Override
	    public void deleteObserver(IListMessagesObserver observer) {
	        // TODO document why this method is empty
	    }
	    
	    protected void setListMessage(Set<Message> list) {
	    	this.listMessage = list;
	    	System.out.println(list.size() + " setList");
	    }

	    public void search() {
	        Pattern pattern = Pattern.compile(jtfSearch.getText());

	        this.listMessageFiltered = new HashSet<Message>();

	        for (Message message : this.listMessage) {
	            Matcher matcher = pattern.matcher(message.getText());
	            while (matcher.find()) {
	                this.listMessageFiltered.add(message);
	            }
	        }

	        this.listMessageFiltered.addAll(this.observer.getUserMessages(jtfSearch.getText()));

	        this.initGUI();
	    }
	}
