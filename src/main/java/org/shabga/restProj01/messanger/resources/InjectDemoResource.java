package org.shabga.restProj01.messanger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/injectDemo")
public class InjectDemoResource {
	
	
	@GET
	@Path("/params")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String displayParams(@MatrixParam("paramMatrix") String matrixParam,
								@HeaderParam("authSessionId") String headerAuthSessionId,
								@CookieParam("cookieValue") String cookie) {
		String result = "";
		result = result + " " + matrixParam + ", " + headerAuthSessionId + ", " + cookie;
		return result;
	}

}
