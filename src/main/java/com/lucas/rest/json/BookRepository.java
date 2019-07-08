package com.lucas.rest.json;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.ws.rs.core.UriInfo;


public class BookRepository {

    private ConcurrentHashMap<Integer, Book> map;

    private BookRepository() {
        map = new ConcurrentHashMap<Integer, Book>();
    }

    private static BookRepository instance = null;

    public static BookRepository getInstance(UriInfo context){
        return instance == null && context != null?
                (instance = new BookRepository()): instance;
    }

    public String add(Book b) {
        if (map.get ( b.getId ()) != null ) {
            return "Already exists, please use a different Id value";
        }
        map.put(b.getId(), b);
        return   "Added successfully" ;

    }

    public List<Book> list() throws Exception {
        Set <Integer> ids = map.keySet ();
        LinkedList <Book> bookList = new LinkedList <> (  );
        int i=0;
        for(Integer id : ids){
            bookList.add (map.get(id)) ;
            i++;
        }
        return bookList;
    }

    public String update(Book b) throws Exception {
        if(map.containsKey(b.getId()))
        {
            map.put(b.getId(), b.clone());
            return ("Update completed");
        }
        return ("key not found");
    }

    public String delete(Book b)  {

        if (map.get ( b.getId () ) == null) {
            return "No book found";
        }
        map.remove ( b.getId ());
        return ( "Book deleted successfully" );


    }

}

