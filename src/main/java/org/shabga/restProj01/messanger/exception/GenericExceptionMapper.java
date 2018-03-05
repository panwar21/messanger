package org.shabga.restProj01.messanger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.shabga.restProj01.messanger.model.ErrorMessage;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 40489, "www.google.ca");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
		.entity(errorMessage)
		.build();
	}

}
