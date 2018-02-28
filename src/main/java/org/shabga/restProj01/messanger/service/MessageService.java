package org.shabga.restProj01.messanger.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.shabga.restProj01.messanger.database.DataBase;
import org.shabga.restProj01.messanger.model.Message;

public class MessageService {

	
	private Map<Long, Message> messages = DataBase.getMessages();
	
	public MessageService() {
		
		this.messages.put(1L, new Message(1,"message1", new Date(), "abhi"));
		this.messages.put(2L, new Message(2,"message2 - 2nd message", new Date(), "abhi"));
	}
	
	public List<Message> getAllMessages(){
		
		return new ArrayList<Message>(this.messages.values());
	}
	
	public Message getMessage(long id) {
		
		return this.messages.get(id);
		
	}
	
	public Message addMessage(Message message) {
		long id = this.messages.size()+1;
		message.setId(id);
		this.messages.put(id, message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if(message.getId() <= 0) {
			return null;
		}
		
		this.messages.put(message.getId(), message);
		System.out.println(message.getId() + " ->" + message.getMessage());	
		return message;
		
	}
	
	public Message removeMessage(long id) {
		
		return this.messages.remove(id);
	}
}
