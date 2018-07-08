/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
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
public class UtilizadorTest {
    
    Utilizador instance1;
    Utilizador instance2;
    Manager manager;
    Camper camper;
    
    public UtilizadorTest() {
        instance1 = new Utilizador();
        instance2 = new Utilizador();
        manager = new Manager();
        camper = new Camper();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        manager.setUsername("helder");
        manager.setEmail("helder@hotmail.com");
        manager.setFullName("Helder Matos");
        manager.setNIF(222333);
        
        camper.setUsername("gongas");
        camper.setCampsiteCard(123);
        camper.setEmail("gongas@mail.com");
        camper.setNIF(12321);
        camper.setFullName("Gonçalo ALves");
        
        instance1.setCamper(camper);
        instance1.setManager(null);
        instance1.setUsername("gongas");
        instance1.setPassword("pass");
        
        instance2.setCamper(null);
        instance2.setManager(manager);
        instance2.setUsername("helder");
        instance2.setPassword("pass");
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
  public void typeAnnotations() {
    // assert
    AssertAnnotations.assertType(Utilizador.class, Entity.class, Table.class, XmlRootElement.class);
  }
  @Test
  public void fieldAnnotations() {
    
  }
  
  
  @Test
  public void methodAnnotations() {
      AssertAnnotations.assertMethod(Utilizador.class, "getManager", XmlTransient.class);
      AssertAnnotations.assertMethod(Utilizador.class, "getCamper", XmlTransient.class);
  }
  
  
  @Test
  public void entity() {
    // setup
    Entity a = ReflectTool.getClassAnnotation(Utilizador.class, Entity.class);
    // assert
    Assert.assertEquals("", a.name());
  }
  @Test
  public void table() {
    // setup
    Table t = ReflectTool.getClassAnnotation(Utilizador.class, Table.class);
    // assert
    Assert.assertEquals("Utilizador", t.name());
  }

    
    /**
     * Test of getCamper method, of class Utilizador.
     */
    @Test
    public void testCamper() {
        System.out.println("getCamper");
        Camper expResult1 = null;
        Camper result1 = instance2.getCamper();
        Camper expResult2 = camper;
        Camper result2 = instance1.getCamper();
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of getManager method, of class Utilizador.
     */
    @Test
    public void testManager() {
        System.out.println("getManager");
        Manager expResult1 = null;
        Manager result1 = instance1.getManager();
        Manager expResult2 = manager;
        Manager result2 = instance2.getManager();
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
    }


    /**
     * Test of getUsername method, of class Utilizador.
     */
    @Test
    public void testUsername() {
        System.out.println("getUsername");
        String expResult1 = "gongas";
        String result1 = instance1.getUsername();
        String expResult2 = "helder";
        String result2 = instance2.getUsername();
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
    }


//    /**
//     * Test of getPassword method, of class Utilizador.
//     */
//    @Test
//    public void testPassword() {
//        System.out.println("getPassword");
//        String expResult1 = "pass";
//        String result1 = instance1.getPassword().toString();
//        String expResult2 = "pass";
//        String result2 = instance2.getPassword().toString();
//        assertEquals(expResult1, result1);
//        assertEquals(expResult2, result2);
//    }

}
