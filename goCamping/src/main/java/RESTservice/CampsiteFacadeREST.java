/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RESTservice;

import BackendBeans.NewSessionBean;
import Persistencia.Campsite;
import Persistencia.Manager;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Andreia Patrocínio
 * @author Carolina Albuquerque
 * @author Diogo Jorge
 * @author Pedro Pires
 *
 */
@Stateless
@Path("campsite")
public class CampsiteFacadeREST {

    
    private EntityManager em;
    
    @EJB
    NewSessionBean nsb;
    
    public CampsiteFacadeREST() {
        // default constructor
    }
    
    @GET
    @Path("{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Campsite> find(@PathParam("name") String name) {
    return nsb.listarCampsiteREST(name);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Campsite> findAll() {
        return nsb.listarTodosCampsites();
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
