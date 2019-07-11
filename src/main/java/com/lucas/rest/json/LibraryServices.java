package com.lucas.rest.json;

/** Library Solos Rest.
 * @author Lucas Napoli
 * @author https://github.com/lucasnapolilapenda/LibraryServerRest
 * @version 1.3
 * @since 1.0
 */

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;

/**
 * Represents Services Calls Context Called to create new instance in every service
 */

@Path("/book")
public class LibraryServices {

    @Context
    private UriInfo context;

    public LibraryServices() {
    }

    /**
     * Posting Book
     * @param book object
     * @return Instance map
     */

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/posting")
    public Messages postBook(Book book) {
        return BookRepository.getInstance ( context ).add ( book );
    }

    /**
     * Updating Book
     * @param book object
     * @return Instance map
     */

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/updating")
    public Messages updateBook(Book book) {
        return BookRepository.getInstance ( context ).update ( book );
    }

    /**
     * Deleting Book
     * @param id Integer
     * @return Instance map
     */

    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path ( "/delete" )
    public Messages deleteBook (int id) {
        return BookRepository.getInstance ( context ).delete ( id );
    }

    /**
     * List Books
     * @return Instance map
     */

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path ( "/list" )
    public ArrayList <Book> listBook () {
        return BookRepository.getInstance ( context ).list ();
    }

    /**
     * Get id
     * @param id Integer
     * @return Instance map
     */

    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path ( "/getid" )
    public Object getIdBook (int id){
        return BookRepository.getInstance ( context ).get (id);
    }
}
