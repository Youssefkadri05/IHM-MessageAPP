package main.java.com.ubo.tp.message.ihm.components.messages.ajouterMessage;

public interface IAjouterMessageObserver {

    Boolean isValidMessage(String message);
    void message(String message);
}
