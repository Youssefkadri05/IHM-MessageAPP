package main.java.com.ubo.tp.message.ihm.components.messages.afficherMessages;

import java.util.List;
import java.util.Set;

import main.java.com.ubo.tp.message.datamodel.Message;

public interface IListMessagesObserver {

	Set<Message> getAllMessages();
	Set<Message> getUserMessages(String userTag);
}
