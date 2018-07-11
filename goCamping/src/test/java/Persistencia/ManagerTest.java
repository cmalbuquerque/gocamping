/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import persistencia.Manager;
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
public class ManagerTest {
    
    Manager instance;
    
    public ManagerTest() {
        
        instance = new Manager();
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        instance.setUsername("helder");
        instance.setEmail("helder@hotmail.com");
        instance.setFullName("Helder Matos");
        instance.setNif(222333);
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void typeAnnotations() {
        // assert
        AssertAnnotations.assertType(Manager.class, Entity.class, Table.class, XmlRootElement.class);
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
      Entity a = ReflectTool.getClassAnnotation(Manager.class, Entity.class);
      // assert
      Assert.assertEquals("", a.name());
    }
    
    
    @Test
    public void table() {
      // setup
      Table t = ReflectTool.getClassAnnotation(Manager.class, Table.class);
      // assert
      Assert.assertEquals("Manager", t.name());
    }

    
    /**
     * Test of getFullName method, of class Manager.
     */
    @Test
    public void testFullName() {
        System.out.println("getFullName");
        String expResult = "Helder Matos";
        String result = instance.getFullName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsername method, of class Manager.
     */
    @Test
    public void testUsername() {
        System.out.println("getUsername");
        String expResult = "helder";
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmail method, of class Manager.
     */
    @Test
    public void testEmail() {
        System.out.println("getEmail");
        String expResult = "helder@hotmail.com";
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNIF method, of class Manager.
     */
    @Test
    public void testNIF() {
        System.out.println("getNIF");
        int expResult = 222333;
        int result = instance.getNif();
        assertEquals(expResult, result);
    }

    
}
