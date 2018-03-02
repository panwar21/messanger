package org.shabga.restProj01.messanger.resources;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.shabga.restProj01.messanger.resources.beans.MessageFilterBean;

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
	
	@GET
	@Path("/beanParam")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String displayParamss(@BeanParam MessageFilterBean messageFilterBean) {
		
		
		return messageFilterBean.toString();
	}
	
	
	@GET
	@Path("/contextDemo")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String useContextForParams(@Context UriInfo uriInfo, @Context HttpHeaders headers) {
		String res = uriInfo.getQueryParameters().toString();
		res = res + " --- "+headers.getRequestHeaders().toString();
		return res;
	}

}
