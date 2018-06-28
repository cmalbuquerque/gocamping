/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Andreia Patrocínio
 * @author Carolina Albuquerque
 * @author Diogo Jorge
 * @author Pedro Pires
 *
 */
public class JPAExample {

    private EntityManager entityManager = EntityManagerUtil.getEntityManager();

    public static void main(String[] args) {
        JPAExample example = new JPAExample();
        
        Camper camper1 = example.saveCamper("kiko","Francisco Salvador", "kikinho@ua.pt", 123456789, 23456);
        Manager manager1 = example.saveManager("joana", "Joana Maria", "joana@ua.pt", 987654321);
        Utilizador user1 = example.saveUtilizador(camper1, null, "kiko", "password");
        Utilizador user2 = example.saveUtilizador(null, manager1, "joana", "password");
        
        Campsite campsite1= example.saveCampsite("Parque de Campismo da Fajã do Ouvidor", "São Jorge, Açores", 15, 5, 0, "9191919191", "A ilha ideal para umas férias perfeitas! Um parque muito adequado para crianças cheias de energia.", manager1);
        Campsite campsite2 = example.saveCampsite("Garfe Parque", "Garfe, Póvoa de Lanhoso", 20, 10, 5, "934765273", "O lugar ideal para umas férias calmas.", manager1);
        

    }

    public Camper saveCamper(String username, String fullname, String email, int NIF, int campsiteCard) {
        Camper camper = new Camper();
        System.out.println("new camper");
        try {
            entityManager.getTransaction().begin();
            System.out.println("at the start of transaction");
            camper.setUsername(username);
            camper.setFullName(fullname);
            camper.setEmail(email);
            camper.setNIF(NIF);
            camper.setCampsiteCard(campsiteCard);
            entityManager.persist(camper);
            entityManager.getTransaction().commit();
            System.out.println("just after comit");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("save didn't work on camper");
        }
        return camper;
    }
        
     public Camper searchCamper(String name) {
        Camper user = new Camper();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from Camper c where c.username = :name");
            query.setParameter("name", name);
            List<Camper> utilizador = query.getResultList();
            for (Iterator<Camper> iterator = utilizador.iterator(); iterator.hasNext();) {
                user = (Camper) iterator.next();
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("camper search didn't work");
        }
        return user;
    }

    public void listCamper() {
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from Camper c");
            List<Camper> Campers = query.getResultList();
            for (Iterator<Camper> iterator = Campers.iterator(); iterator.hasNext();) {
                Camper camper = (Camper) iterator.next();
                System.out.println(camper.getUsername() + " \t" + camper.getFullName());
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("camper listing didn't work");
        }
    }

    public Camper updateCamper(String username, String fullname, String email, int campingCard) {
        Camper camper = new Camper();
        try {
            entityManager.getTransaction().begin();
            camper = (Camper) entityManager.find(Camper.class, username);
            camper.setFullName(fullname);
            camper.setEmail(email);
            camper.setCampsiteCard(campingCard);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("update camper didn't work");
        }
        return camper;
    }   
    

    public void deleteCamper(String username) {
        try {
            entityManager.getTransaction().begin();
            Camper camper = (Camper) entityManager.find(Camper.class, username);
            entityManager.remove(camper);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("delete camper didn't work");
        }
    }

    public Manager saveManager(String username, String fullname, String email, int NIF) {
        Manager manager = new Manager();
        System.out.println("new manager");
        try {
            entityManager.getTransaction().begin();
            //System.out.println("at the start of transaction");
            manager.setUsername(username);
            manager.setFullName(fullname);
            manager.setEmail(email);
            manager.setNIF(NIF);
            entityManager.persist(manager);
            entityManager.getTransaction().commit();
            //System.out.println("just after comit");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("save didn't work on manager");
        }
        return manager;
    }
    
    public Manager searchManager(String name) {
        Manager manager = new Manager();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from Manager c where c.username = :name");
            query.setParameter("name", name);
            List<Manager> managers = query.getResultList();
            for (Iterator<Manager> iterator = managers.iterator(); iterator.hasNext();) {
                manager = (Manager) iterator.next();
                System.out.println(manager.getUsername());
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("manager search didn't work");
        }
        return manager;
    }

    public void listManager() {
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from Manager c");
            List<Manager> Managers = query.getResultList();
            for (Iterator<Manager> iterator = Managers.iterator(); iterator.hasNext();) {
                Manager manager = (Manager) iterator.next();
                System.out.println(manager.getUsername() + " \t" + manager.getFullName());
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("manager listing didn't work");
        }
    }

    public Manager updateManager(String username, String fullname, String email) {
        Manager manager = new Manager ();
        try {
            entityManager.getTransaction().begin();
            manager = (Manager) entityManager.find(Manager.class, username);
            manager.setFullName(fullname);
            manager.setEmail(email);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("update manager didn't work");
        }
        return manager;
    }
    

    public void deleteManager(String username) {
        try {
            entityManager.getTransaction().begin();
            Manager manager = (Manager) entityManager.find(Manager.class, username);
            entityManager.remove(manager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("delete manager didn't work");
        }
    }
    
    

    public Utilizador saveUtilizador(Camper camper, Manager manager, String username, String password) {
        Utilizador user = new Utilizador();
        System.out.println("new user");
        try {
            entityManager.getTransaction().begin();
            System.out.println("at the start of transaction");
            if (camper != null) {
                System.out.println("camper is not null");
                user.setCamper(camper);
            } else {
                System.out.println("manager is not null");
                user.setManager(manager);
            }
            user.setUsername(username);
            user.setPassword(password);
            System.out.println(user.toString());
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            System.out.println("just after comit, saved a user");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("save didn't work on camper");
        }
        return user;
    }

    public Utilizador searchUtilizador(String name) {
        Utilizador user = new Utilizador();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from Utilizador c where c.username = :name");
            query.setParameter("name", name);
            List<Utilizador> utilizador = query.getResultList();
            for (Iterator<Utilizador> iterator = utilizador.iterator(); iterator.hasNext();) {
                user = (Utilizador) iterator.next();
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("utilizador search didn't work");
        }
        return user;
    }

    public Campsite saveCampsite(String title, String location, double adultPrice, double childPrice, double babyPrice, String contact, String desc, Manager manager) {
        Campsite campsite = new Campsite();
        System.out.println("new campsite");
        try {
            entityManager.getTransaction().begin();
            //System.out.println("at the start of transaction");
            campsite.setTitle(title);
            campsite.setLocation(location);
            campsite.setAdultPrice(adultPrice);
            campsite.setChildPrice(childPrice);
            campsite.setBabyPrice(babyPrice);
            campsite.setContact(contact);
            campsite.setDescription(desc);
            campsite.setManager(manager);

            entityManager.persist(campsite);

            entityManager.getTransaction().commit();
            System.out.println("just after comit");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("save didnt' work on campsite");
        }
        return campsite;
    }
    
    public Campsite searchCampsite(int id) {
        Campsite campsite = new Campsite();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from Campsite c where c.id = :id");
            query.setParameter("id", id);
            List<Campsite> campsites = query.getResultList();
            for (Iterator<Campsite> iterator = campsites.iterator(); iterator.hasNext();) {
                campsite = (Campsite) iterator.next();   
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("utilizador search didn't work");
        }
        return campsite;
    }
    
    public void listCampsite() {
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from Campsite c");
            List<Campsite> Campsites = query.getResultList();
            for (Iterator<Campsite> iterator = Campsites.iterator(); iterator.hasNext();) {
                Campsite campsite = (Campsite) iterator.next();
                System.out.println(campsite.getManager() + "\t" + campsite.getId() + " \t " + campsite.getTitle());
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("campsite listing didn't work");
        }
    }
    
    public List<Campsite> listarCampsite(Manager manager) {
        List<Campsite> campsites = new ArrayList<Campsite>();
        List<Campsite> list = new ArrayList<Campsite>();
        try {
            entityManager.getTransaction().begin();         
            Query query = entityManager.createQuery("select c from Campsite as c where c.manager = :manager");
            query.setParameter("manager", manager);
            campsites = query.getResultList();
            for (Iterator<Campsite> iterator = campsites.iterator(); iterator.hasNext();) {
                Campsite campsite = (Campsite) iterator.next();
                list.add(campsite);
            }
            System.out.println("just before commit");
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("listar campsite with manager didn't work");
        }
        return list;
    }
    
    public List<Campsite> listarTodosCampsites() {
        List<Campsite> campsites = new ArrayList<Campsite>();
        List<Campsite> list = new ArrayList<Campsite>();

        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from Campsite c");
            
            campsites = query.getResultList();
            for (Iterator<Campsite> iterator = campsites.iterator(); iterator.hasNext();) {
                Campsite campsite = (Campsite) iterator.next();
                list.add(campsite);
            }

            System.out.println("just before commit");
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("listar campsite didn't work");
        }
        return list;
    }
 
    public List<Campsite> listarCampsite(String location) {
        List<Campsite> campsites = new ArrayList<Campsite>();
        List<Campsite> list = new ArrayList<Campsite>();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from Campsite as c where c.location like :location");
            query.setParameter("location", "%" + location +"%");
            campsites = query.getResultList();
            for (Iterator<Campsite> iterator = campsites.iterator(); iterator.hasNext();) {
                Campsite campsite = (Campsite) iterator.next();
                System.out.println(campsite.getId() + " \t " + campsite.getTitle() + "\t" + campsite.getLocation());
                list.add(campsite);
            }
            System.out.println("just before commit");
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("listar campsite didn't work");
        }
        return list;
    }
    
    public void updateCampsite(int id, String location) {
        try {
            entityManager.getTransaction().begin();
            Campsite campsite = (Campsite) entityManager.find(Campsite.class, id);
            campsite.setLocation(location);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("update campsite didn't work");
        }
    }

    public void deleteCampsite(int id) {
        try {
            entityManager.getTransaction().begin();
            Campsite campsite = (Campsite) entityManager.find(Campsite.class, id);
            entityManager.remove(campsite);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("delete campsite didn't work");
        }
    }

    public CampsiteImage saveCampsiteImage(Campsite campsite, String imageName) {
        CampsiteImage images = new CampsiteImage();
        System.out.println("new Campsite image");
        try {
            entityManager.getTransaction().begin();
            //System.out.println("at the start of transaction");
            images.setCampsite(campsite.getId());
            images.setImageName(imageName);
            entityManager.persist(images);
            entityManager.getTransaction().commit();
            //System.out.println("just after comit");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("save didn't work on images");
        }
        return images;
    }

    public void listCampsiteImage() {
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from CampsiteImage c");
            List<CampsiteImage> campsiteImages = query.getResultList();
            System.out.println("query done images");
            for (Iterator<CampsiteImage> iterator = campsiteImages.iterator(); iterator.hasNext();) {
                CampsiteImage images = (CampsiteImage) iterator.next();
                System.out.println(images.getCampsite() + " \t" + images.getImageName());
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("campsite images listing didn't work");
        }
    }

    public void deleteCampsiteImage(CampsiteImageKey imageKey) {
        try {
            entityManager.getTransaction().begin();
            CampsiteImage campsiteImages = (CampsiteImage) entityManager.find(CampsiteImage.class, imageKey);
            entityManager.remove(campsiteImages);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("delete campsite images didn't work");
        }
    }
  
 

    public Reservation saveReservation(Date startDate, Date endDate, Camper camper, Campsite campsite, int nrAdults, int nrChildren, int nrBabies, int cellphone, double totalPrice) {
        Reservation reservation = new Reservation();
        System.out.println("new Reservation");
        try {
            entityManager.getTransaction().begin();
            System.out.println("at the start of transaction");
            reservation.setStartDate(startDate);
            reservation.setEndDate(endDate);
            reservation.setCamper(camper);
            reservation.setCampsite(campsite);
            reservation.setCellfone(cellphone);
            reservation.setNrAdults(nrAdults);
            reservation.setNrBabies(nrBabies);
            reservation.setNrChildren(nrChildren);
            reservation.setTotalPrice(totalPrice);
            entityManager.persist(reservation);
            entityManager.getTransaction().commit();
            System.out.println("just after comit");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("save  didnt' work on reservation");
        }
        return reservation;
    }

    public void listReservation() {
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from Reservation c");
            List<Reservation> reservations = query.getResultList();
            for (Iterator<Reservation> iterator = reservations.iterator(); iterator.hasNext();) {
                Reservation reservation = (Reservation) iterator.next();
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("reservation listing didn't work");
        }
    }

    public void updateReservation(int id, Date startDate, Date endDate) {
        try {
            entityManager.getTransaction().begin();
            Reservation reservation = (Reservation) entityManager.find(Reservation.class, id);
            reservation.setStartDate(startDate);
            reservation.setEndDate(endDate);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("update reservation didn't work");
        }
    }

    public void deleteReservation(int id) {
        try {
            entityManager.getTransaction().begin();
            Reservation reservation = (Reservation) entityManager.find(Reservation.class, id);
            entityManager.remove(reservation);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("delete reservation didn't work");
        }
    }

    public FavouriteList saveFavouriteList(String camperUsername, int campsiteID) {
        FavouriteList favouriteList = new FavouriteList();
        System.out.println("new fav list");
        try {
            entityManager.getTransaction().begin();
            System.out.println("at the start of transaction");
            favouriteList.setCamperUsername(camperUsername);
            favouriteList.setCampsiteID(campsiteID);
            
            entityManager.persist(favouriteList);

            entityManager.getTransaction().commit();
            System.out.println("just after comit");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("save didnt' work on campsite");
        }
        return favouriteList;   
    }
    
    public List<Campsite> listarCampsitesFavList(String username) {
        List<FavouriteList> favouriteLists = new ArrayList<FavouriteList>();
        List<Integer> list = new ArrayList<Integer>();
        List<Campsite> campsites = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();         
            Query query = entityManager.createQuery("select c from FavouriteList as c where c.camperUsername = :username");
            query.setParameter("username", username);
            favouriteLists = query.getResultList();
            for (Iterator<FavouriteList> iterator = favouriteLists.iterator(); iterator.hasNext();) {
                Integer campsiteID = iterator.next().getCampsiteID();
                list.add(campsiteID);
            }
            System.out.println("just before commit");
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("listar campsite with manager didn't work");
        }
        for (int elem: list){
            campsites.add(searchCampsite(elem));
        }
        
        return campsites;
    }
        
    public void listFavouriteList() {
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from FavouriteList c");
            List<FavouriteList> favouriteLists = query.getResultList();
            for (Iterator<FavouriteList> iterator = favouriteLists.iterator(); iterator.hasNext();) {
                FavouriteList favouriteList = (FavouriteList) iterator.next();
                System.out.println(favouriteList.getCamperUsername() + "\t" + favouriteList.getCampsiteID());
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("favourite list listing didn't work");
        }
    }

    public void deleteFavouriteList(String camperUsername, int campsiteID) {
        FavouriteListKey favKey = new FavouriteListKey(camperUsername, campsiteID);
        try {
            entityManager.getTransaction().begin();
            FavouriteList favouriteList = (FavouriteList) entityManager.find(FavouriteList.class, favKey);
            entityManager.remove(favouriteList);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("delete favourite lit pitch didn't work");
        }
    }
    
    

}
