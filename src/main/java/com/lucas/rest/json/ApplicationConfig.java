package com.lucas.rest.json;

/** Library Solos Rest.
 * @author Lucas Napoli
 * @author https://github.com/lucasnapolilapenda/LibraryServerRest
 * @version 1.3
 * @since 1.0
 */

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**App Config
 */

@ApplicationPath("/service")
public class ApplicationConfig extends Application {

    /**
     * Get classe for configuration
     * @return resourses
     */

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        System.out.println("REST configuration starting: getClasses()");
        resources.add(org.glassfish.jersey.jackson.JacksonFeature.class);
        resources.add(com.lucas.rest.json.LibraryServices.class);
        System.out.println("REST configuration ended successfully.");
        return resources;
    }

    /**
     * Get classes for configuration
     * @return Collection to Singleton
     */

    @Override
    public Set<Object> getSingletons() {
        return Collections.emptySet();
    }

    /**
     * Get classe for configuration
     * @return properties in a Map
     */

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("jersey.config.server.wadl.disableWadl", true);
        return properties;
    }
}
