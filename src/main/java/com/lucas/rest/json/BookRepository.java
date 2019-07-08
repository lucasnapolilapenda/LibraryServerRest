package com.lucas.rest.json;


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

    public Messages add(Book b) {
        if (map.get ( b.getId ()) != null ) {
            Messages message = new Messages ();
            message.setMessage ("Already exists, please use a different Id value");
            message.setEstatus ( "Process no completed" );
            return message;
        }
        map.put(b.getId(), b);
        Messages message = new Messages ();
        message.setMessage ("Added successfully");
        message.setEstatus ( "Process completed" );
        return message;

    }

    public List<Book> list()  {
        Set <Integer> ids = map.keySet ();
        LinkedList <Book> bookList = new LinkedList <> (  );
        for(Integer id : ids){
            bookList.add (map.get(id).clone ()) ;
        }
        return bookList;
    }

    public Messages update(Book b) {
        if(map.containsKey(b.getId()))
        {
            map.put(b.getId(), b.clone());
            Messages message = new Messages ();
            message.setMessage ("Update completed");
            message.setEstatus ( "Process completed" );
            return message;

        }
        Messages message = new Messages ();
        message.setMessage ("key not found");
        message.setEstatus ( "Process no completed" );
        return message;
    }

    public Messages delete(int id)  {

        if (map.get ( id ) == null) {
            Messages message = new Messages ();
            message.setMessage ("No record found");
            message.setEstatus ( "Process no completed" );
            return message;
        }

        map.remove ( id);
        Messages message = new Messages ();
        message.setMessage ("Record deleted successfully");
        message.setEstatus ( "Process completed" );
        return message;
    }

    public Book get(int id)  {
            return map.get ( id );
    }

}


