package org.shabga.restProj01.messanger.database;

import java.util.HashMap;
import java.util.Map;

import org.shabga.restProj01.messanger.model.Message;
import org.shabga.restProj01.messanger.model.Profile;

public class DataBase {
	
	private static Map<Long, Message> messages = new HashMap<>();
	private static Map<Long, Profile> profiles = new HashMap<>();
	
	
	public static Map<Long, Message> getMessages() {
		return messages;
	}
	
	public static Map<Long, Profile> getProfiles() {
		return profiles;
	}
	
	
	
	
}
