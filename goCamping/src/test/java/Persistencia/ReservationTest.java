/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.util.Date;
import javax.persistence.Entity;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author andreia
 */
public class ReservationTest {
    
    Reservation instance;
    Campsite campsite;
    Manager manager;
    Camper camper;
    
    public ReservationTest() {
        
        instance=new Reservation();
        campsite=new Campsite();
        manager=new Manager();
        camper=new Camper();    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        campsite.setLocation("Ericeira, Lisboa");
        campsite.setContact("21345422");
        campsite.setAdultPrice(30.0);
        campsite.setDescription("Ideal para umas férias perto da praia");
        campsite.setBabyPrice(0.0);
        campsite.setChildPrice(12.0);
        campsite.setCampingCardDiscount(10.0);
        campsite.setTitle("Parque de Campismo da Ericeira");
        manager.setUsername("helder");
        manager.setEmail("helder@ua.pt");
        manager.setNIF(1543);
        manager.setFullName("Helder Matos");
        campsite.setManager(manager);
        camper.setUsername("gongas");
        camper.setCampsiteCard(123);
        camper.setEmail("gongas@mail.com");
        camper.setNIF(12321);
        camper.setFullName("Gonçalo ALves");
        
        instance.setCamper(camper);
        instance.setCampsite(campsite);
        instance.setCellfone(981234);
        instance.setNrAdults(2);
        instance.setNrBabies(0);
        instance.setNrChildren(1);
        instance.setEndDate(new Date(2019,2,15));
        instance.setStartDate(new Date(2019,2,4));
        instance.setTotalPrice(24.3);
    }
    
    @After
    public void tearDown() {
    }
    
     @Test
  public void typeAnnotations() {
    // assert
    AssertAnnotations.assertType(Reservation.class, Entity.class);
  }
  @Test
  public void fieldAnnotations() {
    
  }
  
  
  @Test
  public void methodAnnotations() {
     
  }
  
  
  @Test
  public void entity() {
    // setup
    Entity a = ReflectTool.getClassAnnotation(Utilizador.class, Entity.class);
    // assert
    Assert.assertEquals("", a.name());
  }

    /**
     * Test of getNrBabies method, of class Reservation.
     */
    @Test
    public void testNrBabies() {
        System.out.println("getNrBabies");
        int expResult = 0;
        int result = instance.getNrBabies();
        assertEquals(expResult, result);
    }


    /**
     * Test of getTotalPrice method, of class Reservation.
     */
    @Test
    public void testTotalPrice() {
        System.out.println("getTotalPrice");
        double expResult = 24.3;
        double result = instance.getTotalPrice();
        assertEquals(expResult, result, 0.0);
    }


    /**
     * Test of getCellfone method, of class Reservation.
     */
    @Test
    public void testCellfone() {
        System.out.println("getCellfone");
        int expResult = 981234;
        int result = instance.getCellfone();
        assertEquals(expResult, result);
    }


    /**
     * Test of getNrAdults method, of class Reservation.
     */
    @Test
    public void testNrAdults() {
        System.out.println("getNrAdults");
        int expResult = 2;
        int result = instance.getNrAdults();
        assertEquals(expResult, result);
    }


    /**
     * Test of getNrChildren method, of class Reservation.
     */
    @Test
    public void testNrChildren() {
        System.out.println("getNrChildren");
        int expResult = 1;
        int result = instance.getNrChildren();
        assertEquals(expResult, result);
    }


    /**
     * Test of getCamper method, of class Reservation.
     */
    @Test
    public void testCamper() {
        System.out.println("getCamper");
        Camper expResult = camper;
        Camper result = instance.getCamper();
        assertEquals(expResult, result);
    }


    /**
     * Test of getCampsite method, of class Reservation.
     */
    @Test
    public void testCampsite() {
        System.out.println("getCampsite");
        Campsite expResult = campsite;
        Campsite result = instance.getCampsite();
        assertEquals(expResult, result);
    }


    /**
     * Test of getStartDate method, of class Reservation.
     */
    @Test
    public void testStartDate() {
        System.out.println("getStartDate");
        Date expResult = new Date(2019,2,4);
        Date result = instance.getStartDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEndDate method, of class Reservation.
     */
    @Test
    public void testEndDate() {
        System.out.println("getEndDate");
        Date expResult = new Date(2019,2,15);
        Date result = instance.getEndDate();
        assertEquals(expResult, result);
    }
}
