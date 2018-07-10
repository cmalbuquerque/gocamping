///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package BackendBeans;
//
//import Persistencia.Camper;
//import Persistencia.Campsite;
//import Persistencia.FavouriteList;
//import Persistencia.Manager;
//import Persistencia.Reservation;
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
//public class NewSessionBeanIT {
//    
//    
//    private Camper camper;
//    private Manager manager;
//    private Utilizador user;
//    private Campsite campsite;
//    private Reservation reservation;
//    private FavouriteList favouriteList;
//    private static int campID;
//    private static int reservationID;
//    private final String usernameManager = "josefinaIT";
//    private final  String usernameCamper = "simaoIT";
//    
//    @Deployment
//    public static JavaArchive createDeployment() {
//        return ShrinkWrap.create(JavaArchive.class)
//            .addClasses(NewSessionBean.class, Camper.class, Utilizador.class)
//            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
//            .addAsResource("META-INF/persistence.xml");
//    }
//
//    @Inject
//    NewSessionBean example;
//    //SearchBean searchBean;
//    
//    @Before
//    public void setUp() {
//        this.camper = new Camper();
//        this.manager = new Manager();
//        this.user = new Utilizador();
//        this.campsite = new Campsite();
//        this.reservation = new Reservation();
//        this.favouriteList = new FavouriteList();
//        
//    }
//    
//    @Test
//    @InSequence(1)
//    public void saveCamperUtilizador() throws Exception{
//        System.out.println("Testing saveCamperUtilizador");
//        camper = example.saveCamper(usernameCamper, "Simão Arrais", "simao@mail.pt", 987654321, 54321);
//        Assert.assertEquals(camper, example.searchCamper(usernameCamper));
//        user = example.saveUtilizador(camper, null, usernameCamper, "password");
//        Assert.assertEquals(user, example.searchUtilizador(camper.getUsername()));
//    }
//    
//    @Test 
//    @InSequence(2)
//    public void saveManagerUtilizador() throws Exception{
//        System.out.println("Testing saveManagerUtilizador");
//        manager = example.saveManager(usernameManager, "Josefina Maria Luís", "josefina@mail.pt", 987654321);
//        Assert.assertEquals(manager, example.searchManager(usernameManager));
//        user = example.saveUtilizador(null, manager, usernameManager, "password");
//        Assert.assertEquals(user , example.searchUtilizador(usernameManager));     
//    }
//    
//    @Test
//    @InSequence(3)
//    public void saveCampsite() throws Exception{
//        System.out.println("Testing saveCampsite");
//        manager = example.searchManager(usernameManager);
//        campsite = example.saveCampsite("Parque de Campismo do Sabugal", "Sabugal", 12.0, 5.0, 0.0, "964567893", "O melhor parque de campismo do interior do país, em perfeita comunhão com a natureza", manager, 20.0);
//        this.campID = campsite.getId();
//        List<Campsite> listaCampsites = example.listarCampsite(manager);
//        Assert.assertTrue(listaCampsites.contains(campsite));
//    }
//    
//    @Test
//    @InSequence(4)
//    public void searchCampsite() throws  Exception{
//        System.out.println("Testing searchCampsite");
//        String campsiteLocation = "Sabugal"; 
//        List<Campsite> listaCampsites = example.listarCampsite(campsiteLocation);
//        campsite = example.searchCampsite(campID);
//        Assert.assertTrue(listaCampsites.contains(campsite));
//    } 
//    
//    @Test
//    @InSequence(5)
//    public void addFavouriteList() throws Exception{
//        System.out.println("Testing addFavouriteList");
//        favouriteList = example.saveFavouriteList(usernameCamper, campID);
//        List<Campsite> listaCampsites = example.listarCampsitesFavList(usernameCamper);
//        campsite= example.searchCampsite(campID);
//        Assert.assertTrue(listaCampsites.contains(campsite));
//    }
//    
//    @Test
//    @InSequence(6)
//    public void makeReservation() throws Exception{
//        System.out.println("Testing makeReservation");
//        Date startDate = new Date(2018,07,01);
//        Date endDate = new Date(2018,07,15); 
//        camper = example.searchCamper(usernameCamper);
//        campsite= example.searchCampsite(campID);
//        reservation = example.saveReservation(startDate, endDate, camper, campsite, 3, 1, 0, 934765273, 54.5);
//        this.reservationID = reservation.getId();
//        List <Reservation> listaReservations = example.listarReservations(camper);
//        Assert.assertTrue(listaReservations.contains(reservation));
//    }
//    
//    @Test
//    @InSequence(7)
//    public void updateCamperPersonalInformation() throws Exception{
//        System.out.println("Testing updateCamperPersonalInformation");
//        camper = example.updateCamper(usernameCamper, "Simão Arrais Teles", "simao@mail.pt", 54321);
//        Camper camper1 = example.searchCamper(usernameCamper);
//        Assert.assertEquals(camper, camper1);
//    }
//    
//    @Test
//    @InSequence(7)
//    public void updateManagerPersonalInformation() throws Exception{
//        System.out.println("Testing updateManagerPersonalInformation");
//        manager = example.updateManager(usernameManager, "Josefina Maria Luís Patrocínio", "simao@mail.pt");
//        Manager m = example.searchManager(usernameManager);
//        Assert.assertEquals(manager, m);
//    }
//
//    @Test
//    @InSequence(8)
//    public void updateCampsite() throws Exception{
//        System.out.println("Testing updateCampsite");
//        manager = example.searchManager(usernameManager);
//        campsite = example.updateCampsite(campID, "Parque de Campismo do Sabugal", "Sabugal",13.0, 6.0, 2.0, "964567893", "O melhor parque de campismo do interior do país, em perfeita comunhão com a natureza", 20.0);
//        List<Campsite> listaCampsites = example.listarCampsite(manager);
//        Assert.assertTrue(listaCampsites.contains(campsite));
//    }
//    
//    
//    @Test
//    @InSequence(9)
//    public void removeReservation() throws Exception{
//        System.out.println("Testing removeReservation");
//        boolean result = example.deleteReservation(reservationID);
//        Assert.assertTrue(result);
//    }
//    
//    @Test
//    @InSequence(10)
//    public void removeFavouriteList() throws Exception{
//        System.out.println("Testing removeFavouriteList");
//        boolean result = example.deleteFavouriteList(usernameCamper, campID);
//        Assert.assertTrue(result);
//    }
//    
//    @Test
//    @InSequence(11)
//    public void deleteCampsite() throws Exception{
//        System.out.println("Testing deleteCampsite");
//        boolean result = example.deleteCampsite(campID);
//        Assert.assertTrue(result);
//    }
//    
//    @Test
//    @InSequence(12)
//    public void deleteManager() throws Exception{
//        System.out.println("Testing deleteManager");
//        boolean resultU = example.deleteUtilizador(usernameManager);
//        boolean resultM = example.deleteManager(usernameManager);
//        Assert.assertTrue(resultU);
//        Assert.assertTrue(resultM);
//    }
//    
//    @Test
//    @InSequence(13)
//    public void deleteCamper() throws Exception{
//        System.out.println("Testing deleteCamper");
//        boolean resultU = example.deleteUtilizador(usernameCamper);
//        boolean resultC = example.deleteCamper(usernameCamper);
//        Assert.assertTrue(resultU);
//        Assert.assertTrue(resultC);
//    }
//   
//}