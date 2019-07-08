package com.lucas.rest.json;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.List;



@Path("/books")
public class LibraryServices {

    @Context
    private UriInfo context;

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/posting")
    public String postBook(Book book) {
        return BookRepository.getInstance ( context ).add ( book );
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/updating")
    public String updateBook(Book book) throws Exception {
        return BookRepository.getInstance ( context ).update ( book );

    }

    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path ( "/delete" )
    public String deleteBook (Book book) {
        return BookRepository.getInstance ( context ).delete ( book );
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path ( "/list" )
    public List <Book> listBook () throws Exception {
        return BookRepository.getInstance ( context ).list ();
    }

}
