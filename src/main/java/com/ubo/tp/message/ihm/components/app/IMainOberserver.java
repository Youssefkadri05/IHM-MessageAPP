package com.ubo.tp.message.ihm.components.app;

import java.io.File;

import com.ubo.tp.message.core.database.IDatabaseObserver;

public interface IMainOberserver extends IDatabaseObserver {

    void notifyDirectoryChanged(File file);

    void switchToConnexionCompenent();

    void switchToRegesterCompenent();

    void deconnectUser();
    
    void switchToSendMessageComponent();
    
    void switchToListMessageComponent();
    
    void switchToMessagerieComponent();

    void switchToListUserPage();
    void switchToMessageProfilePage(String UserTag);
    void switchToProfilOrToUserProfil(String UserTag);
    void switchToProfilPage();

}
