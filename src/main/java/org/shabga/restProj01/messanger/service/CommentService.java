package org.shabga.restProj01.messanger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.shabga.restProj01.messanger.database.DataBase;
import org.shabga.restProj01.messanger.model.Comment;
import org.shabga.restProj01.messanger.model.Message;

public class CommentService {

	private Map<Long, Message> messages = DataBase.getMessages();
	
	public List<Comment> getAllComments(long messageId){
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		//System.out.println(comments.values().toString());
		return new ArrayList<Comment>(comments.values());
	}
	
	public Comment getComment(long messageId, long commentId) {
		Message message = messages.get(messageId);
		return message.getComments().get(commentId);
		
	}
	
	public Comment updateComment(long messageId, Comment comment) {
		if(comment.getId()<=0) {
			return null;
		}
		Message message = messages.get(messageId);
		message.getComments().put(comment.getId(), comment);
		return comment;
		
	}
	
	public Comment addComment(long messageId, Comment comment) {
		Message message = messages.get(messageId);
		long id = message.getComments().size()+1;
		comment.setId(id);
		message.getComments().put(id, comment);
		return comment;
	}
	
	public Comment removeComment(long messageId, long commentId) {
		Message message = messages.get(messageId);
		return message.getComments().remove(commentId);
	}

}
