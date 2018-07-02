/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author Andreia Patrocínio
 * @author Carolina Albuquerque
 * @author Diogo Jorge
 * @author Pedro Pires
 *
 */
@Entity
@Table(name = "Campsite")
public class Campsite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "location")
    private String location;

    @Column(name = "adultPrice")
    private double adultPrice;

    @Column(name = "childPrice")
    private double childPrice;

    @Column(name = "babyPrice")
    private double babyPrice;

    @Column(name = "contact")
    private String contact;

    @Column(name = "description")
    private String description;
    

    //CHAVE ESTRANGEIRA
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "manager")
    private Manager manager;
    
    @OneToMany(cascade = CascadeType.PERSIST, targetEntity = Reservation.class, mappedBy = "campsite")
    @JoinColumn()
    private Set<Campsite> reservations;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public double getAdultPrice() {
        return adultPrice;
    }

    public double getChildPrice() {
        return childPrice;
    }

    public double getBabyPrice() {
        return babyPrice;
    }

    public String getContact() {
        return contact;
    }

    public String getDescription() {
        return description;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setAdultPrice(double adultPrice) {
        this.adultPrice = adultPrice;
    }

    public void setChildPrice(double childPrice) {
        this.childPrice = childPrice;
    }

    public void setBabyPrice(double babyPrice) {
        this.babyPrice = babyPrice;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setDescription(String description) {
        this.description = description;
    }
 
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            System.out.println("Object Null");
            return false;
        }
        if (getClass() != obj.getClass()) {
            System.out.println("Object Different Class");
            return false;
        }
        final Campsite other = (Campsite) obj;
        if (Double.doubleToLongBits(this.adultPrice) != Double.doubleToLongBits(other.adultPrice)) {
            System.out.println("Object Different adult Price");
            return false;
        }
        if (Double.doubleToLongBits(this.childPrice) != Double.doubleToLongBits(other.childPrice)) {
            System.out.println("Object Different child Price");
            return false;
        }
        if (Double.doubleToLongBits(this.babyPrice) != Double.doubleToLongBits(other.babyPrice)) {
            System.out.println("Object Different baby Price");
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            System.out.println("Object Different title");
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            System.out.println("Object Different location");
            return false;
        }
        if (!Objects.equals(this.contact, other.contact)) {
            System.out.println("Object Different contact");
            return false;
        }
        return true;
    }



    @Override
    public String toString() {
        return "Campsite{" + "id=" + id + ", title=" + title + ", location=" + location + ", adultPrice=" + adultPrice + ", childPrice=" + childPrice + ", babyPrice=" + babyPrice + ", contact=" + contact + ", description=" + description + ", manager=" + manager + '}';
    }

}
