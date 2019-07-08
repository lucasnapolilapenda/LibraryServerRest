package com.lucas.rest.json;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/book")
public class LibraryServices {

    @Context
    private UriInfo context;

    public LibraryServices() {
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/posting")
    public String postBook(Book book) {
        return BookRepository.getInstance ( context ).add ( book );
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/updating")
    public String updateBook(Book book) {
        return BookRepository.getInstance ( context ).update ( book );
    }

    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path ( "/delete" )
    public String deleteBook (int id) {
        return BookRepository.getInstance ( context ).delete ( id );
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path ( "/list" )
    public List <Book> listBook () {
        return BookRepository.getInstance ( context ).list ();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path ( "/getid" )
    public Book getIdBook (int id){
        return BookRepository.getInstance ( context ).get (id);
    }
}
