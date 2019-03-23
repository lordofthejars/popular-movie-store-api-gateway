package org.workspace7.moviestore;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class Gateway {

    @GET
    @Path("{var:.+}")
    //@Consumes(MediaType.TEXT_HTML)
    //@Produces(MediaType.TEXT_HTML)
    public String redirect(@PathParam("var") String originalPath) {
        return getContent("/" + originalPath);
    }

    @GET
    @Path("/")
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public String home() {
        return getContent("/");
    }

    /**@GET
    @Path("/logout")
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public String logout() {
        return getContent("/logout");
    }

    @GET
    @Path("/cart/add")
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public String cartAdd() {
        return getContent("/cart/add");
    }

    @GET
    @Path("/cart/show")
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public String cartShow() {
        return getContent("/cart/show");
    }

    @GET
    @Path("/cart/pay")
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public String cartPay() {
        return getContent("/cart/pay");
    }
    **/

    private String getContent(String location) {
        final Client client = ClientBuilder.newClient();
        final Response res = client.target("http://movies:8080" + location).request(getMime(location)).get();
        return res.readEntity(String.class);
    }

    private String getMime(String location) {

        if (location == null) {
            return "text/html";
        }

        if (location.endsWith(".css")) {
            return "text/css";
        }

        if (location.endsWith(".js")) {
            return "text/javascript";
        }

        if (location.endsWith(".png")) {
            return "image/png";
        }

        return "text/html";

    }
}