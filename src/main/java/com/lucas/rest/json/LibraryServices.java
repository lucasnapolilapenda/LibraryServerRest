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

        Book newBook = bookCreation ( book );

        return BookRepository.getInstance ( context ).add ( newBook );
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
    public String deleteBook (Book book) {
        return BookRepository.getInstance ( context ).delete ( book );
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path ( "/list" )
    public List <Book> listBook () {
        return BookRepository.getInstance ( context ).list ();
    }

    private Book bookCreation (Book book){
        Book newBook = new Book ();
        newBook.setId ( book.getId () );
        newBook.setIsbn (book.getIsbn ());
        newBook.setTitle ( book.getTitle () );
        newBook.setAuthor ( book.getAuthor () );
        newBook.setPublisher ( book.getPublisher () );
        newBook.setDescription ( book.getDescription () );

        return newBook;
    }

}
