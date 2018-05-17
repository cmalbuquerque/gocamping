/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.io.Serializable;
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
    
    @Column(name = "companyNIF")
    private int companyNIF;
    
    @Column(name = "servicesID")
    private int servicesID;
    
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
    
    //OPCIONAL
    @Column(name = "mapsLocation")
    private String mapsLocation;
    
    //CHAVE ESTRANGEIRA
    @ManyToOne(targetEntity = Manager.class)
    @JoinColumn(name="managerUsername")
    private String managerUsername;

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

    public String getMapsLocation() {
        return mapsLocation;
    }

    public int getCompanyNIF() {
        return companyNIF;
    }

    public int getServicesID() {
        return servicesID;
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

    public String getManagerUsername() {
        return managerUsername;
    }

    public void setManagerUsername(String managerUsername) {
        this.managerUsername = managerUsername;
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

    public void setMapsLocation(String mapsLocation) {
        this.mapsLocation = mapsLocation;
    }

    public void setCompanyNIF(int companyNIF) {
        this.companyNIF = companyNIF;
    }

    public void setServicesID(int servicesID) {
        this.servicesID = servicesID;
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
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Campsite other = (Campsite) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.companyNIF != other.companyNIF) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Campsite{" + "id=" + id + ", title=" + title + ", location=" + location + ", mapsLocation=" + mapsLocation + ", companyNIF=" + companyNIF + ", servicesID=" + servicesID + ", adultPrice=" + adultPrice + ", childPrice=" + childPrice + ", babyPrice=" + babyPrice + ", contact=" + contact + ", description=" + description + '}';
    }

    

    
    
}
