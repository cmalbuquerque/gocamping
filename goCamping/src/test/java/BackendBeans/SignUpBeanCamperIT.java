/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackendBeans;

import backendbeans.NewSessionBean;
import backendbeans.SignUpBean;
import persistencia.Camper;
import persistencia.Manager;
import persistencia.Utilizador;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author andreia
 */
@RunWith(Arquillian.class)
public class SignUpBeanCamperIT {
      
    
    private Camper camper;
    private Manager manager;
    private Utilizador user;
    private static final String CAMPERUSERNAME = "SignUpCamperTest";
    private static final String MANAGERUSERNAME = "SignUpManagerTest";
    private NewSessionBean newSessionBean;
      
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
            .addClasses(NewSessionBean.class, SignUpBean.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    SignUpBean signUpBean;
    
    @Before
    public void setUp() {
        this.signUpBean= new SignUpBean();
        this.camper = new Camper();
        this.manager = new Manager();
        this.user = new Utilizador();  
        signUpBean.init();
        this.newSessionBean = new NewSessionBean();
    }
    
    @Test
    @InSequence(1)
    public void registerCamper() throws Exception{
        System.out.println("Testing resgiterCamper");
        signUpBean.setFlagCamper(true);
        signUpBean.setFlagManager(false);
        signUpBean.setUsername(CAMPERUSERNAME);
        signUpBean.setFullName("teste");
        signUpBean.setEmail("teste");
        signUpBean.setNif(1);
        signUpBean.setCampsiteCard(1);
        signUpBean.setPassword("pass");      
        String result = signUpBean.register();
        Assert.assertEquals("login.xhtml", result);
        
    }
    
    @Test
    @InSequence(2)
    public void deleteCamper() throws Exception{
        boolean u = newSessionBean.deleteUtilizador(CAMPERUSERNAME);
        boolean c = newSessionBean.deleteCamper(CAMPERUSERNAME);
        Assert.assertTrue(u);
        Assert.assertTrue(c);      
        
    }
     
    @Test
    @InSequence(3)
    public void registerManager() throws Exception{
        System.out.println("Testing resgiterManager");
        signUpBean.setFlagCamper(false);
        signUpBean.setFlagManager(true);
        signUpBean.setUsername(MANAGERUSERNAME);
        signUpBean.setFullName("teste");
        signUpBean.setEmail("teste");
        signUpBean.setNif(1);
        signUpBean.setPassword("pass");      
        String result = signUpBean.register();
        Assert.assertEquals("login.xhtml", result);   
    }
    
    @Test
    @InSequence(4)
    public void deleteManager() throws Exception{
       boolean u = newSessionBean.deleteUtilizador(MANAGERUSERNAME);
       boolean m = newSessionBean.deleteManager(MANAGERUSERNAME);
       Assert.assertTrue(u);
       Assert.assertTrue(m);
        
    }
     
    
}
