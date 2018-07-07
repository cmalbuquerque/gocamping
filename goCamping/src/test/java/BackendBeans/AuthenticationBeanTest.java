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
//import javax.ejb.EJB;
//import javax.inject.Inject;
//import javax.transaction.UserTransaction;
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.arquillian.junit.InSequence;
//import org.jboss.arquillian.test.api.ArquillianResource;
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
//public class AuthenticationBeanTest {
//    private Camper camper;
//    private Utilizador utilizador;
//    private final  String usernameCamper = "simaoIT";
//    
//    @Deployment
//    public static JavaArchive createDeployment() {
//        return ShrinkWrap.create(JavaArchive.class)
//            .addClasses(AuthenticationBean.class, SignUpBean.class)
//            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
//    }
//
//    @Inject
//    AuthenticationBean authenticationBean;
//    @Inject
//    SignUpBean signUpBean;
//
//    
//    @Before
//    public void setUp(){
//        this.camper = new Camper();
//        this.utilizador = new Utilizador();
//    }
//    
//    @Test
//    public void validate(){
////        camper = signUpBean.saveCamper(usernameCamper, "Simão Arrais", "simao@mail.pt", 987654321, 54321);
////        utilizador = signUpBean.saveUtilizador(camper, null, usernameCamper, "password");
//        Assert.assertEquals("index.xhtml", authenticationBean.teste());
//    }
//    
//}    