///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package BackendBeans;
//
//import Persistencia.Camper;
//import Persistencia.Manager;
//import Persistencia.Utilizador;
//import java.util.Date;
//import java.util.List;
//import javax.inject.Inject;
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.arquillian.junit.InSequence;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.asset.EmptyAsset;
//import org.jboss.shrinkwrap.api.spec.JavaArchive;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
///**
// *
// * @author Andreia Patrocínio
// * @author Carolina Albuquerque
// * @author Diogo Jorge
// * @author Pedro Pires
// * 
// */
//@RunWith(Arquillian.class)
//public class SignUpBeanIT {
//    
//    
//    private Camper camper;
//    private Manager manager;
//    private Utilizador user;
//    private final String usernameManager = "josefinaIT";
//    private final  String usernameCamper = "simaoIT";
//    
//    @Deployment
//    public static JavaArchive createDeployment() {
//        return ShrinkWrap.create(JavaArchive.class)
//            .addClass(SignUpBean.class)
//            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
//    }
//
//    @Inject
//    SignUpBean signUpBean;
//    //SearchBean searchBean;
//    
//    @Before
//    public void setUp() {
//        this.camper = new Camper();
//        this.manager = new Manager();
//        this.user = new Utilizador();        
//    }
//
//    /**
//     * Test of saveCamper method, of class SignUpBean.
//     */
//    @Test
//    @InSequence(1)
//    public void testSaveCamper() {
//        System.out.println("Testing saveCamper");
//        camper = signUpBean.saveCamper(usernameCamper, "Simão Arrais", "simao@mail.pt", 987654321, 54321);
//        Utilizador expResult = signUpBean.saveUtilizador(camper, null, usernameCamper, "password");
//        Utilizador result = signUpBean.SearchUtilizador(usernameCamper);
//        Assert.assertEquals(expResult, result);
//        
//    }
//    
//}
