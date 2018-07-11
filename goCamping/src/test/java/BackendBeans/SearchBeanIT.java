/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackendBeans;


import backendbeans.NewSessionBean;
import backendbeans.SearchBean;
import persistencia.Campsite;
import persistencia.Manager;
import java.util.ArrayList;
import java.util.List;
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
public class SearchBeanIT {
      
    
  
    private Manager manager;
    private static final String MANAGERUSERNAME = "SignUpManagerTest";
    private NewSessionBean newSessionBean;
    private Campsite campsite;
    private List<Campsite> listaCampsites = new ArrayList<>();
    private static int campID=0;
      
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
            .addClasses(NewSessionBean.class, SearchBean.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    SearchBean searchBean;
    
    @Before
    public void setUp() {
        this.searchBean= new SearchBean();
        this.manager = new Manager(); 
        searchBean.init();
        this.newSessionBean = new NewSessionBean();
    }
    
    @Test
    @InSequence(1)
    public void search() throws Exception{
        System.out.println("Testing resgiterCamper");
        manager = newSessionBean.saveManager(MANAGERUSERNAME, "teste", "teste", 1);
        campsite = newSessionBean.saveCampsite("test", "Aveiro", 10, 1, 0, "teste", "teste", manager, 20);
        campID=campsite.getId();
        listaCampsites.add(campsite);
        searchBean.setCampsiteLocation("Aveiro");
        searchBean.setListaCampsites(listaCampsites);
        String result = searchBean.search();
        Assert.assertEquals("results.xhtml", result);
        
    }
    
    
    @Test
    @InSequence(2)
    public void deleteCampsite() throws Exception{
       boolean c = newSessionBean.deleteCampsite(campID);
       Assert.assertTrue(c);
        
    }
    
    @Test
    @InSequence(3)
    public void deleteManager() throws Exception{
       boolean m = newSessionBean.deleteManager(MANAGERUSERNAME);
       Assert.assertTrue(m);
        
    }
    
   
     
    
     
    
}
