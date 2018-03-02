package org.shabga.restProj01.messanger.resources;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.shabga.restProj01.messanger.model.Message;
import org.shabga.restProj01.messanger.resources.beans.MessageFilterBean;
import org.shabga.restProj01.messanger.service.MessageService;

@Path("/messages")
public class MesageResource {
	
	
	MessageService messageService = new MessageService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> myMessages(@BeanParam MessageFilterBean messageFilterBean){
	/*public List<Message> myMessages(@QueryParam("year") int year,
									@QueryParam("start") int start,
									@QueryParam("size") int size) {
	*/	
		int year = messageFilterBean.getYear();
		int start = messageFilterBean.getStart();
		int size = messageFilterBean.getSize();
		if(year!=0) {
			return messageService.getMessagesForYear(year);
		}
		if(start >=0 && size >0) {
			return messageService.getMessagesPagination(start, size);
		}
		return messageService.getAllMessages();
	}
	
	
	
	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("messageId") long id) {
		
		return messageService.getMessage(id);
		
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message addMessage(Message message) {
		
		return messageService.addMessage(message);
		
	}
	
	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		return messageService.updateMessage(message);
		
	}
	
	@DELETE
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message deleteMessage(@PathParam("messageId") long id) {
		
		return messageService.removeMessage(id);
		
	}
	
	
	@Path("/{messageId}/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public CommentResource getCommentResource() {
		
		return new CommentResource();
		
	}
	

}
