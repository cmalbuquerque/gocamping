/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restservice;

import backendbeans.NewSessionBean;
import persistencia.Manager;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
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
@Path("manager")
public class ManagerFacadeREST {

    @EJB
    NewSessionBean nsb;

    public ManagerFacadeREST() {
        // default constructor

    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Manager find(@PathParam("id") String id) {
        return nsb.searchManager(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Manager> findAll() {
        return nsb.listarTodosManagers();
    }
}
