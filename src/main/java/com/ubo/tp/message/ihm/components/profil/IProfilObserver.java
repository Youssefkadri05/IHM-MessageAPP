package com.ubo.tp.message.ihm.components.profil;


import com.ubo.tp.message.datamodel.User;

public interface IProfilObserver {
    void modifyAccount(User user);
}
