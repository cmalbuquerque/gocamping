/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andreia Patrocínio
 * @author Carolina Albuquerque
 * @author Diogo Jorge
 * @author Pedro Pires
 *
 */
public class JPAExampleTest {
    
    private JPAExample instance;
    private Camper camper;
    private Manager manager;
    private Utilizador user;
    
    public JPAExampleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.instance = new JPAExample();
        this.camper = new Camper();
        this.manager = new Manager();
        this.user = new Utilizador();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of saveCamper method, of class JPAExample.
     */
    @Test
    public void testSaveCamper() {
        System.out.println("Testing saveCamper");
        String username = "jota";
        String fullname = "João T Pais";
        String email = "jpais@mail.com";
        int NIF = 2318473;
        int campsiteCard = 34;
        camper.setCampsiteCard(campsiteCard);
        camper.setFullName(fullname);
        camper.setUsername(username);
        camper.setEmail(email);
        camper.setNIF(NIF);
        Camper expResult = camper;
        Camper result = instance.saveCamper(username, fullname, email, NIF, campsiteCard);
        assertEquals(expResult, result);
        instance.deleteCamper(username);
    }

//    /**
//     * Test of searchCamper method, of class JPAExample.
//     */
//    @Test
//    public void testSearchCamper() {
//        System.out.println("searchCamper");
//        String name = "";
//        JPAExample instance = new JPAExample();
//        Camper expResult = null;
//        Camper result = instance.searchCamper(name);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    /**
//     * Test of updateCamper method, of class JPAExample.
//     */
//    @Test
//    public void testUpdateCamper() {
//        System.out.println("Testing updateCamper");
//        String username = "kiko";
//        String fullname = "Francisco Salvador";
//        String email = "kikinho@mail.com";
//        int NIF = 2318473;
//        int campingCard = 34;
//        camper.setEmail(email);
//        Camper expResult = camper;
//        Camper result = instance.updateCamper(username, fullname, email, campingCard);
//        assertEquals(expResult, result);
//    }
    /**
     * Test of deleteCamper method, of class JPAExample.
     */
    @Test
    public void testDeleteCamper() {
        System.out.println("Testing deleteCamper");
        String username = "jota";
        String fullname = "João J Pais";
        String email = "jpais@mail.com";
        boolean expResult = true;
        instance.saveCamper(username, fullname, email, 0, 0);
        boolean result = instance.deleteCamper(username);
        assertEquals(result, expResult);
        instance.deleteCamper(username);
    }

    /**
     * Test of saveManager method, of class JPAExample.
     */
    @Test
    public void testSaveManager() {
        System.out.println("Testing saveManager");
        String username = "gongas";
        String fullname = "Goncalo Marques";
        String email = "gongas@gmail.com";
        int NIF = 123;
        manager.setEmail(email);
        manager.setFullName(fullname);
        manager.setUsername(username);
        manager.setNIF(NIF);
        Manager expResult = manager;
        Manager result = instance.saveManager(username, fullname, email, NIF);
        assertEquals(expResult, result);
        instance.deleteManager(username);
        
    }

//    /**
//     * Test of searchManager method, of class JPAExample.
//     */
//    @Test
//    public void testSearchManager() {
//        System.out.println("searchManager");
//        String name = "";
//        JPAExample instance = new JPAExample();
//        Manager expResult = null;
//        Manager result = instance.searchManager(name);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
//    /**
//     * Test of updateManager method, of class JPAExample.
//     */
//    @Test
//    public void testUpdateManager() {
//        System.out.println("updateManager");
//        String username = "";
//        String fullname = "";
//        String email = "";
//        JPAExample instance = new JPAExample();
//        Manager expResult = null;
//        Manager result = instance.updateManager(username, fullname, email);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of deleteManager method, of class JPAExample.
     */
    @Test
    public void testDeleteManager() {
        System.out.println("Testing deleteManager");
        String username = "gongas";
        String fullname = "Goncalo Marques";
        String email = "gongas@gmail.com";
        int NIF = 123;
        boolean expResult = true;
        instance.saveManager(username, fullname, email, NIF);
        boolean result = instance.deleteManager(username);
        assertEquals(result, expResult);
        instance.deleteManager(username);
    }

    /**
     * Test of saveUtilizador method, of class JPAExample.
     */
    @Test
    public void testSaveUtilizador() {
        System.out.println("Testing saveUtilizador");
        Camper camper = new Camper();
        camper.setCampsiteCard(0);
        camper.setUsername("diogof");
        camper.setFullName("Diogo Filipe");
        camper.setEmail("diogof@mail.com");
        camper.setNIF(0);
        Manager manager = null;
        String username = "diogof";
        String password = "fff";
        user.setCamper(camper);
        user.setManager(manager);
        user.setPassword(password);
        user.setUsername(username);
        Utilizador expResult = user;
        Utilizador result = instance.saveUtilizador(camper, manager, username, password);
        assertEquals(expResult, result);
        instance.deleteUtilizador(username);
        
    }

//    /**
//     * Test of searchUtilizador method, of class JPAExample.
//     */
//    @Test
//    public void testSearchUtilizador() {
//        System.out.println("searchUtilizador");
//        String name = "";
//        JPAExample instance = new JPAExample();
//        Utilizador expResult = null;
//        Utilizador result = instance.searchUtilizador(name);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of saveCampsite method, of class JPAExample.
//     */
//    @Test
//    public void testSaveCampsite() {
//        System.out.println("saveCampsite");
//        String title = "";
//        String location = "";
//        double adultPrice = 0.0;
//        double childPrice = 0.0;
//        double babyPrice = 0.0;
//        String contact = "";
//        String desc = "";
//        Manager manager = null;
//        JPAExample instance = new JPAExample();
//        Campsite expResult = null;
//        Campsite result = instance.saveCampsite(title, location, adultPrice, childPrice, babyPrice, contact, desc, manager);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of searchCampsite method, of class JPAExample.
//     */
//    @Test
//    public void testSearchCampsite() {
//        System.out.println("searchCampsite");
//        int id = 0;
//        JPAExample instance = new JPAExample();
//        Campsite expResult = null;
//        Campsite result = instance.searchCampsite(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

//    /**
//     * Test of listarCampsite method, of class JPAExample.
//     */
//    @Test
//    public void testListarCampsite_Manager() {
//        System.out.println("listarCampsite");
//        Manager manager = null;
//        JPAExample instance = new JPAExample();
//        List<Campsite> expResult = null;
//        List<Campsite> result = instance.listarCampsite(manager);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    /**
//     * Test of listarTodosCampsites method, of class JPAExample.
//     */
//    @Test
//    public void testListarTodosCampsites() {
//        System.out.println("listarTodosCampsites");
//        JPAExample instance = new JPAExample();
//        List<Campsite> expResult = null;
//        List<Campsite> result = instance.listarTodosCampsites();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of listarCampsite method, of class JPAExample.
//     */
//    @Test
//    public void testListarCampsite_String() {
//        System.out.println("listarCampsite");
//        String location = "";
//        JPAExample instance = new JPAExample();
//        List<Campsite> expResult = null;
//        List<Campsite> result = instance.listarCampsite(location);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updateCampsite method, of class JPAExample.
//     */
//    @Test
//    public void testUpdateCampsite() {
//        System.out.println("updateCampsite");
//        int id = 0;
//        String title = "";
//        String location = "";
//        double adultPrice = 0.0;
//        double childPrice = 0.0;
//        double babyPrice = 0.0;
//        String contact = "";
//        String desc = "";
//        JPAExample instance = new JPAExample();
//        instance.updateCampsite(id, title, location, adultPrice, childPrice, babyPrice, contact, desc);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of deleteCampsite method, of class JPAExample.
//     */
//    @Test
//    public void testDeleteCampsite() {
//        System.out.println("deleteCampsite");
//        int id = 0;
//        JPAExample instance = new JPAExample();
//        instance.deleteCampsite(id);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of saveCampsiteImage method, of class JPAExample.
//     */
//    @Test
//    public void testSaveCampsiteImage() {
//        System.out.println("saveCampsiteImage");
//        Campsite campsite = null;
//        String imageName = "";
//        JPAExample instance = new JPAExample();
//        CampsiteImage expResult = null;
//        CampsiteImage result = instance.saveCampsiteImage(campsite, imageName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of listCampsiteImage method, of class JPAExample.
//     */
//    @Test
//    public void testListCampsiteImage() {
//        System.out.println("listCampsiteImage");
//        JPAExample instance = new JPAExample();
//        instance.listCampsiteImage();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of deleteCampsiteImage method, of class JPAExample.
//     */
//    @Test
//    public void testDeleteCampsiteImage() {
//        System.out.println("deleteCampsiteImage");
//        CampsiteImageKey imageKey = null;
//        JPAExample instance = new JPAExample();
//        instance.deleteCampsiteImage(imageKey);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of saveReservation method, of class JPAExample.
//     */
//    @Test
//    public void testSaveReservation() {
//        System.out.println("saveReservation");
//        Date startDate = null;
//        Date endDate = null;
//        Camper camper = null;
//        Campsite campsite = null;
//        int nrAdults = 0;
//        int nrChildren = 0;
//        int nrBabies = 0;
//        int cellphone = 0;
//        double totalPrice = 0.0;
//        JPAExample instance = new JPAExample();
//        Reservation expResult = null;
//        Reservation result = instance.saveReservation(startDate, endDate, camper, campsite, nrAdults, nrChildren, nrBabies, cellphone, totalPrice);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of listarReservations method, of class JPAExample.
//     */
//    @Test
//    public void testListarReservations() {
//        System.out.println("listarReservations");
//        Camper camper = null;
//        JPAExample instance = new JPAExample();
//        List<Reservation> expResult = null;
//        List<Reservation> result = instance.listarReservations(camper);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of listReservation method, of class JPAExample.
//     */
//    @Test
//    public void testListReservation() {
//        System.out.println("listReservation");
//        JPAExample instance = new JPAExample();
//        instance.listReservation();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updateReservation method, of class JPAExample.
//     */
//    @Test
//    public void testUpdateReservation() {
//        System.out.println("updateReservation");
//        int id = 0;
//        Date startDate = null;
//        Date endDate = null;
//        JPAExample instance = new JPAExample();
//        instance.updateReservation(id, startDate, endDate);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of deleteReservation method, of class JPAExample.
//     */
//    @Test
//    public void testDeleteReservation() {
//        System.out.println("deleteReservation");
//        int id = 0;
//        JPAExample instance = new JPAExample();
//        instance.deleteReservation(id);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of saveFavouriteList method, of class JPAExample.
//     */
//    @Test
//    public void testSaveFavouriteList() {
//        System.out.println("saveFavouriteList");
//        String camperUsername = "";
//        int campsiteID = 0;
//        JPAExample instance = new JPAExample();
//        FavouriteList expResult = null;
//        FavouriteList result = instance.saveFavouriteList(camperUsername, campsiteID);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of listarCampsitesFavList method, of class JPAExample.
//     */
//    @Test
//    public void testListarCampsitesFavList() {
//        System.out.println("listarCampsitesFavList");
//        String username = "kiko";
//        JPAExample instance = new JPAExample();
//        List<Campsite> expResult = null;
//        List<Campsite> result = instance.listarCampsitesFavList(username);
//        System.out.println(result);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of listFavouriteList method, of class JPAExample.
//     */
//    @Test
//    public void testListFavouriteList() {
//        System.out.println("listFavouriteList");
//        JPAExample instance = new JPAExample();
//        instance.listFavouriteList();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of deleteFavouriteList method, of class JPAExample.
//     */
//    @Test
//    public void testDeleteFavouriteList() {
//        System.out.println("deleteFavouriteList");
//        String camperUsername = "";
//        int campsiteID = 0;
//        JPAExample instance = new JPAExample();
//        instance.deleteFavouriteList(camperUsername, campsiteID);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
