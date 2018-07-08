/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;
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
public class FavouriteListTest {
    
    Camper camper;
    Campsite campsite;
    FavouriteList instance;
    Manager manager;
    
    public FavouriteListTest() {
        
        camper = new Camper();
        campsite = new Campsite();
        instance = new FavouriteList();
        manager = new Manager();    
     }
    
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

        
        instance.setCamperUsername(camper.getUsername());
        instance.setCampsiteID(campsite.getId());
    }
    
    @After
    public void tearDown() {
    }

    
  @Test
  public void typeAnnotations() {
    // assert
    AssertAnnotations.assertType(FavouriteList.class, Entity.class, Table.class, IdClass.class);
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
    Entity a = ReflectTool.getClassAnnotation(Campsite.class, Entity.class);
    // assert
    Assert.assertEquals("", a.name());
  }
  @Test
  public void table() {
    // setup
    Table t = ReflectTool.getClassAnnotation(Campsite.class, Table.class);
    // assert
    Assert.assertEquals("Campsite", t.name());
  }

    /**
     * Test of getCamperUsername method, of class FavouriteList.
     */
    @Test
    public void testCamperUsername() {
        System.out.println("getCamperUsername");
        String expResult = camper.getUsername();
        String result = instance.getCamperUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCampsiteID method, of class FavouriteList.
     */
    @Test
    public void testCampsiteID() {
        System.out.println("getCampsiteID");
        int expResult = campsite.getId();
        int result = instance.getCampsiteID();
        assertEquals(expResult, result);
    }
    
}
