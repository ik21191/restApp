package com.mkyong.rest;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.util.Base64;
import org.jboss.resteasy.util.HttpServletRequestDelegate;

import com.google.gson.JsonObject;

@Path("/json/product")
public class JSONService {

	@GET
	@Path("/get")
	@Produces("application/json")
	public Product getProductInJSON() {
		System.out.println("getProductInJSON() is called.");
		Address address = new Address();
		address.setCity("New Delhi");
		Product product = new Product();
		product.setName("iPad 3");
		product.setQty(999);
		product.setAddress(address);
		return product; 

	}

	@POST
	@Path("/post/{clientProfile}")
	@Consumes("application/json")
	public Response createProductInJSON(@PathParam("clientProfile") String clientProfile, 
			@HeaderParam("Authorization") String authString, @Valid Product product, @Context HttpServletRequest request) throws Exception {
		System.out.println("createProductInJSON(Product product) is called.");
		Response response = checkClientAuthentication(request);
		if(response != null) {
			return response;
		}
		String base64Credentials = authString.substring("Basic".length()).trim();            
		String credentials = new String(Base64.decode(base64Credentials.getBytes()), Charset.forName("UTF-8"));            
		String[] values = credentials.split(":", 2);            
		System.out.println("name: " + values[0]);            
		System.out.println("name: " + values[1]); 
		String result = "Product created : " + product;
		authenticateUser(request);
		return Response.status(201).entity(result).build();
		
	}
	
	boolean authenticateUser(HttpServletRequest request) throws IOException {
		boolean status = false;
		final String authString = request.getHeader("Authorization");
		String base64Credentials = authString.substring("Basic".length()).trim();            
		String credentials = new String(Base64.decode(base64Credentials.getBytes()), Charset.forName("UTF-8"));            
		String[] values = credentials.split(":", 2);
		System.out.println("authenticateUser() method is called.");
		System.out.println("name: " + values[0]);            
		System.out.println("name: " + values[1]); 
		return status;
	}
	
	Response checkClientAuthentication(HttpServletRequest request) throws IOException {
		final String authString = request.getHeader("Authorization");
		int STATUS_CODE = HttpServletResponse.SC_UNAUTHORIZED;
		String MESSAGE = "message";
		JsonObject jsonObject = createJsonObject();
		String base64Credentials = authString.substring("Basic".length()).trim();            
		String credentials = new String(Base64.decode(base64Credentials.getBytes()), Charset.forName("UTF-8"));            
		String[] values = credentials.split(":", 2);
		System.out.println("authenticateUser() method is called.");
		System.out.println("name: " + values[0]);            
		System.out.println("name: " + values[1]);
		if(values[0].equals("Imran") && values[1].equals("Khan")) {
			return null;
		}
		jsonObject.addProperty(MESSAGE, "Invalid Authorization Token.");
		return Response.status(STATUS_CODE).entity(jsonObject.toString()).
		header("WWW-Authenticate:", "realm=" + "Invalid Token").build();
	}

	private JsonObject createJsonObject() {
		return new JsonObject();
	}
	
	
	
}