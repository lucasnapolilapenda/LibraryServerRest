package com.lucas.rest.json;

/** Library Solos Rest.
 * @author Lucas Napoli
 * @author https://github.com/lucasnapolilapenda/LibraryServerRest
 * @version 1.3
 * @since 1.0
 */


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.ws.rs.core.UriInfo;

/**
 * Represents Repository behaviour
 */


public class BookRepository {

    private ConcurrentHashMap<Integer, Book> map;

    /**
     * Book Repository Instance
     */

    private BookRepository() {
            map = new ConcurrentHashMap <Integer, Book> ( );
    }

    private static BookRepository instance = null;

    /**
     * Book Repository Instance
     * @param context UriInfo for Instance
     * @return Instance
     */

    public static BookRepository getInstance(UriInfo context){
        return instance == null && context != null?
                (instance = new BookRepository()): instance;
    }

    /**
     * To map Book Object
     * @param b to ass to add Book to map
     * @return message to Client
     */

    public Messages add(Book b) {
        repoReader ();
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
        repoSaver (map);
        map.clear ();
        return message;

    }

    /**
     * To map Book Object
     * @return Array list to Client
     */

    public ArrayList<Book> list()  {
        repoReader ();
        Set <Integer> ids = map.keySet ();
        ArrayList <Book> bookList = new ArrayList <> (  );
        for(Integer id : ids){
            bookList.add (map.get(id).clone ()) ;
        }
        map.clear ();
        return bookList;
    }

    /**
     * To map Book Object
     * @param b book
     * @return Message to Client
     */

    public Messages update(Book b) {
        repoReader ();
        if(map.containsKey(b.getId()))
        {
            map.put(b.getId(), b.clone());
            Messages message = new Messages ();
            message.setMessage ("Update completed");
            message.setEstatus ( "Process completed" );
            repoSaver ( map );
            map.clear ();
            return message;

        }
        Messages message = new Messages ();
        message.setMessage ("key not found");
        message.setEstatus ( "Process no completed" );
        return message;
    }

    /**
     * To map Book Object
     * @param id ID information
     * @return Message to Client
     */

    public Messages delete(int id)  {
        repoReader ();
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
        repoSaver (map);
        map.clear ();
        return message;
    }

    /**
     * To map Book Object
     * @param id ID information
     * @return Message to Client
     * @return book if book exists
     */

    public Object get(int id)  {
        repoReader ();
        if (map.get ( id ) == null) {
            Messages message = new Messages ();
            message.setMessage ("No record found");
            message.setEstatus ( "Process no completed" );
            return message;
        }
            Object book = map.get ( id );
            map.clear ();
            return book;
    }

    /**
     * Save in a json Document
     * @param map map creation
     */

    public void repoSaver (ConcurrentHashMap<Integer, Book> map) {


        String jsonString = "";

        Set <Integer> ids = map.keySet ();
        ArrayList <Book> bookList = new ArrayList <> (  );
        for(Integer id : ids){
            bookList.add (map.get(id).clone ()) ;
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable( SerializationFeature.INDENT_OUTPUT);

            try {
                jsonString = mapper.writeValueAsString ( bookList );
            } catch (JsonProcessingException e) {
                e.printStackTrace ( );
            }

        try {
            FileWriter file = new FileWriter ("/Volumes/FILES/Projects/LibraryServerRest/src/main/jsondb/db.json");
            file.write(jsonString );
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace ( );
        }
        System.out.println ( jsonString );
    }

    /**
     * Read from a json Document
     */

    public void repoReader () {
        try {
            File file = new File ("/Volumes/FILES/Projects/LibraryServerRest/src/main/jsondb/db.json");

            ObjectMapper objectMapper = new ObjectMapper ( );
            Book [] arrayBook = objectMapper.readValue (file ,Book[].class);
            for (Book book : arrayBook) map.put ( book.getId ( ), book );
        }catch (IOException e) {
            e.printStackTrace ( );
        }
    }

}


