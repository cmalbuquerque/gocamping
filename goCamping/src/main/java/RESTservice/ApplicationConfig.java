/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RESTservice;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author pirex
 */
@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(RESTservice.CamperFacadeREST.class);
        resources.add(RESTservice.CampsiteFacadeREST.class);
        resources.add(RESTservice.ManagerFacadeREST.class);
        resources.add(RESTservice.UtilizadorFacadeREST.class);
    }
    
}
