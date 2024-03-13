package com.ubo.tp.message.ihm.components.register;

import java.util.Set;

public interface IRegisterObserver {

	void notifyRegister(String tTag, String tPassword, String tNom, Set<String> follows, String tAvatar);

    boolean isAccountExist(String tag);

    boolean isPasswordValid(String tPassword);
}
