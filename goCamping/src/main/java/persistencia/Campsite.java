/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
@XmlRootElement
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
    
    @Column(name = "campingCardDiscount")
    private double campingCardDiscount;

    //CHAVE ESTRANGEIRA
    @ManyToOne()
    @JoinColumn(name = "manager")
    private Manager manager;


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

    @XmlTransient
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

    public double getCampingCardDiscount() {
        return campingCardDiscount;
    }

    public void setCampingCardDiscount(double campingCardDiscount) {
        this.campingCardDiscount = campingCardDiscount;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Campsite other = (Campsite) obj;
        if (Double.doubleToLongBits(this.adultPrice) != Double.doubleToLongBits(other.adultPrice)) {
            return false;
        }
        if (Double.doubleToLongBits(this.childPrice) != Double.doubleToLongBits(other.childPrice)) {
            return false;
        }
        if (Double.doubleToLongBits(this.babyPrice) != Double.doubleToLongBits(other.babyPrice)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.contact, other.contact)) {
            return false;
        }
        return true;
    }



    @Override
    public String toString() {
        return "Campsite{" + "id=" + id + ", title=" + title + ", location=" + location + ", adultPrice=" + adultPrice + ", childPrice=" + childPrice + ", babyPrice=" + babyPrice + ", contact=" + contact + ", description=" + description + ", manager=" + manager +  "campingCardDiscount"+ campingCardDiscount + '}';
    }

}
