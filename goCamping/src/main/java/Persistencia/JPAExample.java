/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

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
        System.out.println("After Sucessfully insertion ");
        Camper camper1 = example.saveCamper("Andreia123");
        Camper camper2 = example.saveCamper("ptpires");

        example.listCamper();

        System.out.println("After Sucessfully modification ");
        example.updateStudent(camper1.getUsername(), "Andreia Patrocínio");
        example.updateStudent(camper2.getUsername(), "Pedro Pires");
        example.listCamper();

        System.out.println("After Sucessfully deletion ");
        example.deleteCamper(camper2.getUsername());
        example.listCamper();

    }

    public Camper saveCamper(String username) {
        Camper camper = new Camper();
        System.out.println("new camper");
        try {
            entityManager.getTransaction().begin();
            System.out.println("at the start of transaction");
            camper.setUsername(username);
            entityManager.persist(camper);
            entityManager.getTransaction().commit();
            System.out.println("just after comit");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("idn' work on camper");
        }
        return camper;
    }

    public void listCamper() {
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select c from Camper c");
            List<Camper> Campers = query.getResultList();
            for (Iterator<Camper> iterator = Campers.iterator(); iterator.hasNext();) {
                Camper camper = (Camper) iterator.next();
                System.out.println(camper.getUsername());
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("camper listing didn't work");
        }
    }

    public void updateStudent(String username, String fullname) {
        try {
            entityManager.getTransaction().begin();
            Camper camper = (Camper) entityManager.find(Camper.class, username);
            camper.setFullName(fullname);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void deleteCamper(String username) {
        try {
            entityManager.getTransaction().begin();
            Camper camper = (Camper) entityManager.find(Camper.class, username);
            entityManager.remove(username);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }
    
    
    
}
