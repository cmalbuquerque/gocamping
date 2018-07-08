/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
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
public class CampsiteTest {
    
    Campsite instance;
    Manager manager;
    
    public CampsiteTest() {
        
        instance = new Campsite();
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
        
        instance.setLocation("Ericeira, Lisboa");
        instance.setContact("21345422");
        instance.setAdultPrice(30.0);
        instance.setDescription("Ideal para umas férias perto da praia");
        instance.setBabyPrice(0.0);
        instance.setChildPrice(12.0);
        instance.setCampingCardDiscount(10.0);
        instance.setTitle("Parque de Campismo da Ericeira");
        manager.setUsername("helder");
        manager.setEmail("helder@ua.pt");
        manager.setNIF(1543);
        manager.setFullName("Helder Matos");
        instance.setManager(manager);
        
        
    }
    
    @After
    public void tearDown() {
    }

    
     @Test
  public void typeAnnotations() {
    // assert
    AssertAnnotations.assertType(Campsite.class, Entity.class, Table.class, XmlRootElement.class);
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
     * Test of getTitle method, of class Campsite.
     */
    @Test
    public void testTitle() {
        System.out.println("getTitle");
        String expResult = "Parque de Campismo da Ericeira";
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLocation method, of class Campsite.
     */
    @Test
    public void testLocation() {
        System.out.println("getLocation");
        String expResult = "Ericeira, Lisboa";
        String result = instance.getLocation();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAdultPrice method, of class Campsite.
     */
    @Test
    public void testAdultPrice() {
        System.out.println("getAdultPrice");
        double expResult = 30.0;
        double result = instance.getAdultPrice();
        assertEquals(expResult, result, 30.0);
    }

    /**
     * Test of getChildPrice method, of class Campsite.
     */
    @Test
    public void testChildPrice() {
        System.out.println("getChildPrice");
        double expResult = 12.0;
        double result = instance.getChildPrice();
        assertEquals(expResult, result, 12.0);
    }

    /**
     * Test of getBabyPrice method, of class Campsite.
     */
    @Test
    public void testBabyPrice() {
        System.out.println("getBabyPrice");
        double expResult = 0.0;
        double result = instance.getBabyPrice();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getContact method, of class Campsite.
     */
    @Test
    public void testContact() {
        System.out.println("getContact");
        String expResult = "21345422";
        String result = instance.getContact();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescription method, of class Campsite.
     */
    @Test
    public void testDescription() {
        System.out.println("getDescription");
        String expResult = "Ideal para umas férias perto da praia";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of getManager method, of class Campsite.
     */
    @Test
    public void testManager() {
        System.out.println("getManager");
        Manager expResult = manager;
        Manager result = instance.getManager();
        assertEquals(expResult, result);
    }

    
}
