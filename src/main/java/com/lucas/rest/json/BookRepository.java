package com.lucas.rest.json;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.ws.rs.core.UriInfo;


class BookRepository {

    private ConcurrentHashMap<Integer, Book> map;

    private BookRepository() {
        map = new ConcurrentHashMap <> ( );
    }

    private static BookRepository instance = null;

    static BookRepository getInstance(UriInfo context){
        return instance == null && context != null?
                (instance = new BookRepository()): instance;
    }

    String add(Book b) {
        if (map.get ( b.getId ()) != null ) {
            return "Already exists, please use a different Id value";
        }
        map.put(b.getId(), b.clone ());
        return   "Added successfully" ;

    }

    List<Book> list() {
        Set <Integer> ids = map.keySet ();
        LinkedList <Book> bookList = new LinkedList <> (  );
        for(Integer id : ids){
            bookList.add (map.get(id)) ;
        }
        return bookList;
    }

    String update(Book b) {
        if(map.containsKey(b.getId()))
        {
            map.put(b.getId(), b.clone());
            return ("Update completed");
        }
        return ("key not found");
    }

    String delete(Book b)  {

        if (map.get ( b.getId () ) == null) {
            return "No book found";
        }
        map.remove ( b.getId ());
        return ( "Book deleted successfully" );
    }



}

