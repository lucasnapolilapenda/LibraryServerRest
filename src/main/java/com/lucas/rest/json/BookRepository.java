package com.lucas.rest.json;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
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

    public List<Book> list()  {
        Set <Integer> ids = map.keySet ();
        LinkedList <Book> bookList = new LinkedList <> (  );
        for(Integer id : ids){
            bookList.add (map.get(id).clone ()) ;
        }
        return bookList;
    }

    public String update(Book b) {
        if(map.containsKey(b.getId()))
        {
            map.put(b.getId(), b.clone());
            return ("Update completed");
        }
        return ("key not found");
    }

    public String delete(int id)  {

        if (map.get ( id ) == null) {
            return "No book found";
        }
        map.remove ( id);
        return ( "Book deleted successfully" );
    }

    public Book get(int id)  {
            return map.get ( id );
    }

}


