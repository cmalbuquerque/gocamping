/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author pirex
 */
@Entity
public class Bungalow implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    //CHAVE ESTRANGEIRA E PRIMARIA
    @Column(name = "accommodationID")
    private int accommodationID;
    
    @Column (name ="area")
    private double area;
    
    @Column (name ="price")
    private double price;
    
    @Column (name ="campingCardDiscount")
    private double campingCardDiscount;
    
    @Column(name ="Bedrooms")
    private int bedrooms;
    
    @Column(name ="Capacity")
    private int capacity;

    public int getAccommodationID() {
        return accommodationID;
    }

    public void setAccommodationID(int accommodationID) {
        this.accommodationID = accommodationID;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCampingCardDiscount() {
        return campingCardDiscount;
    }

    public void setCampingCardDiscount(double campingCardDiscount) {
        this.campingCardDiscount = campingCardDiscount;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.accommodationID;
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
        final Bungalow other = (Bungalow) obj;
        if (this.accommodationID != other.accommodationID) {
            return false;
        }
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (this.bedrooms != other.bedrooms) {
            return false;
        }
        if (this.capacity != other.capacity) {
            return false;
        }
        return true;
    }
 @Override
    public String toString() {
        return "Bungalow{" + "accommodationID=" + accommodationID + ", area=" + area + ", price=" + price + ", campingCardDiscount=" + campingCardDiscount + ", bedrooms=" + bedrooms + ", capacity=" + capacity + '}';
    }


    
}
