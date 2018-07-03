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
    private Campsite campsite;
    private Reservation reservation;
    
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
        this.campsite = new Campsite();
        this.reservation = new Reservation();
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

    /**
     * Test of searchCamper method, of class JPAExample.
     */
    @Test
    public void testSearchCamper() {
        System.out.println("Testing searchCamper");
        String name = "jota";
        String fullname = "João T Pais";
        String email = "jpais@mail.com";
        int NIF = 2318473;
        int campsiteCard = 34;
        camper.setCampsiteCard(campsiteCard);
        camper.setFullName(fullname);
        camper.setUsername(name);
        camper.setEmail(email);
        camper.setNIF(NIF);
        Camper expResult = instance.saveCamper(name,fullname, email, NIF, campsiteCard);
        Camper result = instance.searchCamper(name);
        assertEquals(expResult, result);
        instance.deleteCamper(name);
    }
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

    /**
     * Test of searchManager method, of class JPAExample.
     */
    @Test
    public void testSearchManager() {
        System.out.println("Testing searchManager");
        String username = "gongas";
        String fullname = "Goncalo Marques";
        String email = "gongas@gmail.com";
        int NIF = 123;
        manager.setEmail(email);
        manager.setFullName(fullname);
        manager.setUsername(username);
        manager.setNIF(NIF);
        Manager expResult = instance.saveManager(username, fullname, email, NIF);
        Manager result = instance.searchManager(username);
        assertEquals(expResult, result);
        instance.deleteManager(username);
    }
    
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
        Camper c1 = new Camper();
        int NIF = 28473;
        int campsiteCard = 4;
        String username= "babs";
        String fullname = "Bárbara Brito";
        String email = "bb@mail.com";
        camper.setNIF(0);
        Camper c = new Camper(); 
        c = (Camper) instance.saveCamper(username, fullname, email, 0, 0);
        Manager manager = null;
        String password = "fff";
        user.setCamper(c);
        user.setManager(manager);
        user.setPassword(password);
        user.setUsername(username);
        Utilizador expResult = user;
        Utilizador result = instance.saveUtilizador(c, manager, username, password);
        assertEquals(expResult, result);
        instance.deleteUtilizador(username);
        instance.deleteCamper(username);
        
    }

    /**
     * Test of searchUtilizador method, of class JPAExample.
     */
    @Test
    public void testSearchUtilizador() {
        System.out.println("Testing searchUtilizador");
        String username = "gongas";
        String fullname = "Goncalo Marques";
        String email = "gongas@gmail.com";
        int NIF = 123;
        manager.setEmail(email);
        manager.setFullName(fullname);
        manager.setUsername(username);
        manager.setNIF(NIF);
        Camper camper = null;
        String password = "fff";
        Manager m = new Manager();
        m = instance.saveManager(username, fullname, email, NIF);
        Utilizador expResult = instance.saveUtilizador(camper, m, username, password);
        Utilizador result = instance.searchUtilizador(username);
        assertEquals(expResult, result);
        instance.deleteUtilizador(username);
        instance.deleteManager(username);
    }

    /**
     * Test of saveCampsite method, of class JPAExample.
     */
    @Test
    public void testSaveCampsite() {
        System.out.println("Testing saveCampsite");
        String title = "Parque Campismo Marinha Grande";
        String location = "Marinha Grande, Leiria";
        double adultPrice = 14.0;
        double childPrice = 7.0;
        double babyPrice = 0.0;
        String contact = "923111222";
        String desc = "Agradável para umas férias tranquilas em família";
        String username = "helder";
        String fullname = "Helder Marques";
        String email = "h@gmail.com";
        int NIF = 231;
        double campingCardDiscount = 10; 
        Manager m = new Manager();
        m = (Manager) instance.saveManager(username, fullname, email, NIF);
        campsite.setManager(m);
        campsite.setAdultPrice(adultPrice);
        campsite.setBabyPrice(babyPrice);
        campsite.setChildPrice(childPrice);
        campsite.setContact(contact);
        campsite.setDescription(desc);
        campsite.setLocation(location);
        campsite.setTitle(title);
        campsite.setCampingCardDiscount(campingCardDiscount);
        Campsite expResult = campsite;
        Campsite result = instance.saveCampsite(title, location, adultPrice, childPrice, babyPrice, contact, desc, m, campingCardDiscount);
        assertEquals(expResult, result);
        instance.deleteCampsite(result.getId());
        instance.deleteManager(username);
    }

    /**
     * Test of searchCampsite method, of class JPAExample.
     */
    @Test
    public void testSearchCampsite() {
        System.out.println("Testing searchCampsite");
        String title = "Parque Campismo Marinha Grande";
        String location = "Marinha Grande, Leiria";
        double adultPrice = 14.0;
        double childPrice = 7.0;
        double babyPrice = 0.0;
        String contact = "923111222";
        String desc = "Agradável para umas férias tranquilas em família";
        double campingCardDiscount=10;
        String username = "helder";
        String fullname = "Helder Marques";
        String email = "h@gmail.com";
        int NIF = 231;
        Manager m = new Manager();
        m = (Manager) instance.saveManager(username, fullname, email, NIF);
        Campsite expResult = instance.saveCampsite(title, location, adultPrice, childPrice, babyPrice, contact, desc, m, campingCardDiscount);
        Campsite result = instance.searchCampsite(expResult.getId());
        assertEquals(expResult, result);
        instance.deleteCampsite(expResult.getId());
        instance.deleteManager(username);   
    }

    /**
     * Test of listarCampsite method, of class JPAExample.
     */
    @Test
    public void testListarCampsite_Manager() {
        System.out.println("Testing listarCampsite_Manager");
        manager = instance.saveManager("helder", "Helder Marques", "h@gmail.com", 231);
        String title = "Parque Campismo Marinha Grande";
        String location = "Marinha Grande, Leiria";
        double adultPrice = 14.0;
        double childPrice = 7.0;
        double babyPrice = 0.0;
        String contact = "923111222";
        String desc = "Agradável para umas férias tranquilas em família";
        double campingCardDiscount=10;
        Campsite myCampsite = instance.saveCampsite(title, location, adultPrice, childPrice, babyPrice, contact, desc, manager, campingCardDiscount);
        List<Campsite> result = instance.listarCampsite(manager);
        assertTrue(result.contains(myCampsite));
        instance.deleteCampsite(myCampsite.getId());
        instance.deleteManager(manager.getUsername());
    }
    
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
    /**
     * Test of listarCampsite method, of class JPAExample.
     */
    @Test
    public void testListarCampsite_Location() {
        System.out.println("Testing listarCampsite_Manager");
        manager = instance.saveManager("helder", "Helder Marques", "h@gmail.com", 231);
        System.out.println("testar listarCampsite_Location");
        String title = "Parque Campismo Marinha Grande";
        String location = "Marinha Grande, Leiria";
        double adultPrice = 14.0;
        double childPrice = 7.0;
        double babyPrice = 0.0;
        String contact = "923111222";
        String desc = "Agradável para umas férias tranquilas em família";
        double campingCardDiscount=10;
        Campsite myCampsite = instance.saveCampsite(title, location, adultPrice, childPrice, babyPrice, contact, desc, manager, campingCardDiscount);
        List<Campsite> result = instance.listarCampsite("Marinha Grande");
        assertTrue(result.contains(myCampsite));
        instance.deleteCampsite(myCampsite.getId());
        instance.deleteManager(manager.getUsername());
    }
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

    /**
     * Test of deleteCampsite method, of class JPAExample.
     */
    @Test
    public void testDeleteCampsite() {
        System.out.println("Testing deleteCampsite");
        String title = "Parque Campismo Praia da Vagueira";
        String location = "Vagueira, Aveiro";
        double adultPrice = 12.0;
        double childPrice = 6.0;
        double babyPrice = 1.0;
        String contact = "342777521";
        String desc = "Proximo da praia, bom ambiente.";
        String username = "nadia";
        String fullname = "Nadia Marques";
        String email = "nadia@gmail.com";
        int NIF = 231;
        double campingCardDiscount=10;
        Manager m = new Manager();
        m = (Manager) instance.saveManager(username, fullname, email, NIF);
        campsite.setManager(m);
        campsite.setAdultPrice(adultPrice);
        campsite.setBabyPrice(babyPrice);
        campsite.setChildPrice(childPrice);
        campsite.setContact(contact);
        campsite.setDescription(desc);
        campsite.setLocation(location);
        campsite.setTitle(title);
        campsite.setCampingCardDiscount(campingCardDiscount);
        Campsite camp = instance.saveCampsite(title, location, adultPrice, childPrice, babyPrice, contact, desc, m, campingCardDiscount);
        boolean expResult = true;
        boolean result = instance.deleteCampsite(camp.getId());
        assertEquals(result, expResult);
        instance.deleteCampsite(camp.getId());
        instance.deleteManager(username);
        
    }

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
    /**
     * Test of saveReservation method, of class JPAExample.
     */
    @Test
    public void testSaveReservation() {
        System.out.println("Testing saveReservation");
        String title = "Parque Campismo Marinha Grande";
        String location = "Marinha Grande, Leiria";
        double adultPrice = 14.0;
        double childPrice = 7.0;
        double babyPrice = 0.0;
        String contact = "923111222";
        String desc = "Agradável para umas férias tranquilas em família";
        double campingCardDiscount = 10;
        manager =  instance.saveManager("helio", "Helio Marques", "h@gmail.com", 231);      
        Camper camper = instance.saveCamper("vasquinho", "Vasco Inho", "v@mail.com", 1232, 123754);
        Campsite campsite = instance.saveCampsite(title, location, adultPrice, childPrice, babyPrice, contact, desc, manager, campingCardDiscount);
        Date startDate = new Date(2018,07,01);
        Date endDate = new Date(2018,07,15);
        int nrAdults = 2; int nrChildren = 1; int nrBabies=0; int cellphone = 234123123;
        double totalPrice = 40.0;
        reservation.setCamper(camper);
        reservation.setCampsite(campsite);
        reservation.setEndDate(startDate);
        reservation.setStartDate(endDate);
        reservation.setNrAdults(nrAdults);
        reservation.setNrChildren(nrChildren);
        reservation.setNrBabies(nrBabies);
        reservation.setCellfone(cellphone);
        reservation.setTotalPrice(totalPrice);
        Reservation expResult = reservation;
        Reservation result = instance.saveReservation(startDate, endDate, camper, campsite, nrAdults, nrChildren, nrBabies, cellphone, totalPrice);
        assertEquals(expResult, result);
        instance.deleteReservation(result.getId());
        instance.deleteCampsite(campsite.getId());
        instance.deleteManager(manager.getUsername());
        instance.deleteCamper(camper.getUsername());
        
    }

    /**
     * Test of listarReservations method, of class JPAExample.
     */
    @Test
    public void testListarReservations() {
        System.out.println("Testing listarReservations");
        camper = instance.saveCamper("leono", "Leonor Oliveira", "lei@ua.pt", 123233456, 987654);
        Date startDate = new Date(2018,07,01);
        Date endDate = new Date(2018,07,15);
        int nrAdults = 2; int nrChildren = 1; int nrBabies=0; int cellphone = 234123123;
        double totalPrice = 40.0;
        reservation = instance.saveReservation(startDate, endDate, camper, campsite, nrAdults, nrChildren, nrBabies, cellphone, totalPrice);
        List<Reservation> result = instance.listarReservations(camper);
        assertTrue(result.contains(reservation));
        instance.deleteReservation(reservation.getId());
        instance.deleteCamper(camper.getUsername());
    }
    
    
    
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
