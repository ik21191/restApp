package mypack;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mypack.beans.UserBean;

@Path("/publish")
public class RestEasyExample {
 
    @GET
    @Path("/{message}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserBean publishMessage(@PathParam("message") String msgStr){
         
        UserBean bean = new UserBean();
        bean.setName(msgStr);
        bean.setCity("Delhi");
        bean.setRoll(19);
        return bean;
    }
}