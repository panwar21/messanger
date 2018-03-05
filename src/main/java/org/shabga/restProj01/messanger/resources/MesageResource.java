package org.shabga.restProj01.messanger.resources;

import java.net.URI;
import java.net.URISyntaxException;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.shabga.restProj01.messanger.exception.DataNotFoundException;
import org.shabga.restProj01.messanger.model.Link;
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
	@Produces(MediaType.TEXT_XML)
	public Message getMessageXML(@PathParam("messageId") long id, @Context UriInfo uriInfo) throws DataNotFoundException {
		System.out.println("XML method responding");

		Message message = messageService.getMessage(id);
		
		message.addLink(getSelfURIString(uriInfo, message), "self");
		message.addLink(getAuthorURIString(uriInfo, message), "author");
		message.addLink(getCommentsURIString(uriInfo, message), "comments");
		return message;
	}
	
	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessageJSON(@PathParam("messageId") long id, @Context UriInfo uriInfo) throws DataNotFoundException {
		System.out.println("JSON method responding");
		Message message = messageService.getMessage(id);
		
		message.addLink(getSelfURIString(uriInfo, message), "self");
		message.addLink(getAuthorURIString(uriInfo, message), "author");
		message.addLink(getCommentsURIString(uriInfo, message), "comments");
		return message;
	}



	private String getCommentsURIString(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(MesageResource.class)
				.path(MesageResource.class, "getCommentResource")
				.path(CommentResource.class)
				.resolveTemplate("messageId", message.getId())
				.build().
				toString();
				return uri;
	}



	private String getSelfURIString(UriInfo uriInfo, Message message) {
		String uriSelf = uriInfo.getBaseUriBuilder()
		.path(MesageResource.class)
		.path(Long.toString(message.getId()))
		.build().
		toString();
		return uriSelf;
	}
	
	private String getAuthorURIString(UriInfo uriInfo, Message message) {
		
		String uri = uriInfo.getBaseUriBuilder()
		.path(ProfileResource.class)
		.path(message.getAuthor())
		.build().
		toString();
		return uri;
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMessageJSON(Message message, @Context UriInfo uriInfo) {
		System.out.println("Post-> accept = json response = json");
		Message newMessage = messageService.addMessage(message);
		URI uri = uriInfo.getAbsolutePathBuilder().path("/" + newMessage.getId()).build(); 
		return Response.created(uri)
				.entity(newMessage)
				.build();
		
	}
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMessageXML(Message message, @Context UriInfo uriInfo) {
		System.out.println("Post-> accept = xml response = json");
		Message newMessage = messageService.addMessage(message);
		URI uri = uriInfo.getAbsolutePathBuilder().path("/" + newMessage.getId()).build(); 
		return Response.created(uri)
				.entity(newMessage)
				.build();
		
	}
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response addMessageAcceptXMLProduceXML(Message message, @Context UriInfo uriInfo) {
		System.out.println("Post-> accept = xml response = xml");
		Message newMessage = messageService.addMessage(message);
		URI uri = uriInfo.getAbsolutePathBuilder().path("/" + newMessage.getId()).build(); 
		return Response.created(uri)
				.entity(newMessage)
				.build();
		
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
	public CommentResource getCommentResource() {
		
		return new CommentResource();
		
	}
	

}
