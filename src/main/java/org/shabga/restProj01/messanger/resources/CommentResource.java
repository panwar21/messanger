package org.shabga.restProj01.messanger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.shabga.restProj01.messanger.model.Comment;
import org.shabga.restProj01.messanger.service.CommentService;

@Path("/")
public class CommentResource {
	
	private CommentService commentService = new CommentService();
	
	
	@GET
	@Path("/{commentId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Comment getCommentbyId(@PathParam("messageId") long messageId,
								 @PathParam("commentId") long commentId) {
		
		return commentService.getComment(messageId, commentId);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Comment addComment(@PathParam("messageId") long messageId, Comment comment) {
		
		return commentService.addComment(messageId, comment);
	}
	
	@PUT
	@Path("/{commentId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Comment updateComment(@PathParam("messageId") long messageId,
								 @PathParam("commentId") long commentId,
								 Comment comment) {
		comment.setId(commentId);
		return commentService.updateComment(messageId, comment);
	}
	
	@DELETE
	@Path("/{commentId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Comment removeComment(@PathParam("messageId") long messageId,
								 @PathParam("commentId") long commentId) {
		
		return commentService.removeComment(messageId, commentId);
	}

}
