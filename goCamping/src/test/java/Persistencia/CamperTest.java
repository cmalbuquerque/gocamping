/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Andreia Patrocínio
 * @author Carolina Albuquerque
 * @author Diogo Jorge
 * @author Pedro Pires
 *
 */
public class CamperTest {
    
    Camper instance;
    
    public CamperTest() {
        instance = new Camper();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance.setUsername("gongas");
        instance.setCampsiteCard(123);
        instance.setEmail("gongas@mail.com");
        instance.setNIF(12321);
        instance.setFullName("Gonçalo ALves");
    }
    
    @After
    public void tearDown() {
    }
    
    
  @Test
  public void typeAnnotations() {
    // assert
    AssertAnnotations.assertType(Camper.class, Entity.class, Table.class, XmlRootElement.class);
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
    Entity a = ReflectTool.getClassAnnotation(Camper.class, Entity.class);
    // assert
    Assert.assertEquals("", a.name());
  }
  @Test
  public void table() {
    // setup
    Table t = ReflectTool.getClassAnnotation(Camper.class, Table.class);
    // assert
    Assert.assertEquals("Camper", t.name());
  }


    /**
     * Test of getFullName method, of class Camper.
     */
    @Test
    public void testFullName() {
        System.out.println("Testing getFullName");
        String expResult = "Gonçalo ALves";
        String result = instance.getFullName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsername method, of class Camper.
     */
    @Test
    public void testUsername() {
        System.out.println("Testing getUsername");
        String expResult = "gongas";
        String result = instance.getUsername();
    }

    /**
     * Test of getEmail method, of class Camper.
     */
    @Test
    public void testEmail() {
        System.out.println("Testing getEmail");
        String expResult = "gongas@mail.com";
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNIF method, of class Camper.
     */
    @Test
    public void testNIF() {
        System.out.println("Testing getNIF");
        int expResult = 12321;
        int result = instance.getNIF();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCampsiteCard method, of class Camper.
     */
    @Test
    public void testCampsiteCard() {
        System.out.println("Testing getCampsiteCard");
        int expResult = 123;
        int result = instance.getCampsiteCard();
        assertEquals(expResult, result);
    }
    
}
