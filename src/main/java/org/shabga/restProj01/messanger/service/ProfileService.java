package org.shabga.restProj01.messanger.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.shabga.restProj01.messanger.database.DataBase;
import org.shabga.restProj01.messanger.model.Profile;

public class ProfileService {
	
	
private Map<String, Profile> profiles = DataBase.getProfiles();
	
	public ProfileService() {
		
		this.profiles.put("abhi", new Profile(1,"dogg", "abhi" ,"panwar", new Date()));
		this.profiles.put("abhi", new Profile(2,"dogg2", "abhi" ,"panwar", new Date()));
		
	}
	
	public List<Profile> getAllProfiles(){
		
		return new ArrayList<Profile>(this.profiles.values());
	}
	
	public Profile getProfile(String profileName) {
		
		return this.profiles.get(profileName);
		
	}
	
	public Profile addProfile(Profile profile) {
		long id = this.profiles.size()+1;
		profile.setId(id);
		this.profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile) {
		if(profile.getProfileName().isEmpty()) {
			return null;
		}
		
		this.profiles.put(profile.getProfileName(), profile);
		//System.out.println(message.getId() + " ->" + message.getMessage());	
		return profile;
		
	}
	
	public Profile removeProfile(String profileName) {
		
		return this.profiles.remove(profileName);
	}

}
