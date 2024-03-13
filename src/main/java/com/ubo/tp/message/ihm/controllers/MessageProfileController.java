package main.java.com.ubo.tp.message.ihm.controllers;


import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.components.app.IMainOberserver;
import main.java.com.ubo.tp.message.ihm.components.userprofile.IMessageProfileObserver;
import main.java.com.ubo.tp.message.ihm.session.Session;

import java.util.Optional;

public class MessageProfileController extends IController implements IMessageProfileObserver {
    protected IMainOberserver mainController;

    public MessageProfileController(IDatabase database, EntityManager entityManager, Session session, IMainOberserver mainController) {
        super(database, entityManager,session);
//        if(session.getConnectedUser() != null) this.currentUser = new User(currentUser.getUuid(), currentUser.getUserTag(), currentUser.getUserPassword(), currentUser.getName(), currentUser.getFollows(), currentUser.getAvatarPath());
//        else this.currentUser = null;
        this.mainController = mainController;
    }

    @Override
    public Boolean isAlreadyFollowedByUser(User user) {
        return this.session.getConnectedUser().isFollowing(user);
    }

    @Override
    public void followUser(User user) {
        this.session.getConnectedUser().addFollowing(user.getUserTag());
        this.entityManager.writeUserFile(this.session.getConnectedUser());
        //if(!user.getUserTag().equals(this.session.getConnectedUser().getUserTag()))
           mainController.switchToMessageProfilePage(user.getUserTag());

    }

    @Override
    public void unfollowUser(User user) {

        this.session.getConnectedUser().removeFollowing(user.getUserTag());
        this.entityManager.writeUserFile(this.session.getConnectedUser());

        //if(!user.getUserTag().equals(this.session.getConnectedUser().getUserTag()))
            mainController.switchToMessageProfilePage(user.getUserTag());
        //this.entityManager.

    }



    @Override
    public int getNbMessagesPostedByUser(User user) {
        return this.database.getUserMessages(user).size();
    }

    @Override
    public int getNbFollowersByUser(User User) {
        return this.database.getFollowersCount(User);
        //return 0;
    }

    @Override
    public User getUserFromTag(String tag) {
        //return null;
//        return this.entityManager.getUserFromTag(tag);
        Optional<User> user = this.database.getUsers().stream().filter((u -> u.getUserTag().equals(tag))).findFirst();

        return user.orElse(null);
    }
}
