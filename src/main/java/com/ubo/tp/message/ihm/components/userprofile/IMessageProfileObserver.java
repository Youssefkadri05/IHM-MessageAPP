package main.java.com.ubo.tp.message.ihm.components.userprofile;


import main.java.com.ubo.tp.message.datamodel.User;

public interface IMessageProfileObserver {
    Boolean isAlreadyFollowedByUser(User twitUser);

    void followUser(User followUser);

    void unfollowUser(User unfollowUser);

    int getNbMessagesPostedByUser(User twitterUser);

    int getNbFollowersByUser(User twitterUser);

    User getUserFromTag(String tag);
}
