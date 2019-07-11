# Project LibraryServerRest

Project to deploy a Library services in Json. Project managed with Maven.

## Installation

Project details

```
Tomcat 9.0.19
Maven 3
Java 8
Dependencies: pom.xml
```

Configure your application in the class ApplicationConfig:

```
@ApplicationPath("/service")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        System.out.println("REST configuration starting: getClasses()");
        resources.add(org.glassfish.jersey.jackson.JacksonFeature.class);
        resources.add(com.lucas.rest.json.LibraryServices.class);
        System.out.println("REST configuration ended successfully.");
        return resources;
    }

    @Override
    public Set<Object> getSingletons() {
        return Collections.emptySet();
    }


    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("jersey.config.server.wadl.disableWadl", true);
        return properties;
```

Configure Json repo outside TomCat. URL in the class: Book Repository

```
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
```
## Usage

Once you have configure services root, and Json repository, run the war in Tomcat. 

## Contributing
No Contribution Required. Academic Project McGill University. 

## License
[MIT](https://choosealicense.com/licenses/mit/)