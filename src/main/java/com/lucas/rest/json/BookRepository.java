package com.lucas.rest.json;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.ws.rs.core.UriInfo;


public class BookRepository {

    private ConcurrentHashMap<Integer, Book> map;

    private BookRepository() {
            map = new ConcurrentHashMap <Integer, Book> ( );
    }

    private static BookRepository instance = null;

    public static BookRepository getInstance(UriInfo context){
        return instance == null && context != null?
                (instance = new BookRepository()): instance;
    }

    public Messages add(Book b) {
        repoReader ();
        if (map.get ( b.getId ()) != null ) {
            Messages message = new Messages ();
            message.setMessage ("Already exists, please use a different Id value");
            message.setEstatus ( "Process no completed" );
            return message;
        }

        map.put(b.getId(), b);
        repoSaver (map);
        Messages message = new Messages ();
        message.setMessage ("Added successfully");
        message.setEstatus ( "Process completed" );
        System.out.println ( map.get ( b.getId () ).getId () );

        return message;

    }

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

    public Messages update(Book b) {
        repoReader ();
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
        map.clear ();
        return message;
    }

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
        return message;
    }

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

    public void repoSaver (ConcurrentHashMap<Integer, Book> map) {

        Book [] arrayBook = new Book[map.size ()];
        String jsonString = "";

        for (int i = 0; i <arrayBook.length ; i++) {
            arrayBook [i] = map.get ( i );
            System.out.println ( map.get ( i ) );
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable( SerializationFeature.INDENT_OUTPUT);
            try {
                jsonString = mapper.writeValueAsString ( arrayBook );
            } catch (JsonProcessingException e) {
                e.printStackTrace ( );
            }

        try {
            FileWriter file = new FileWriter ("/Volumes/FILES/Projects/LibraryServerRest/src/main/library.json");
            file.write(jsonString );
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace ( );
        }
        System.out.println ( jsonString );
    }

    public void repoReader () {
        try {
            File file = new File ("/Volumes/FILES/Projects/LibraryServerRest/src/main/library.json");

            ObjectMapper objectMapper = new ObjectMapper ( );
            Book [] arrayBook = objectMapper.readValue (file ,Book[].class);
            for (Book book : arrayBook) map.put ( book.getId ( ), book );
        }catch (IOException e) {
            e.printStackTrace ( );
        }
    }

}


