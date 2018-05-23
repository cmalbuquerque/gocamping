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
@Table(name = "TentPitch")
public class TentPitch implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    //CHAVE ESTRANGEIRA E PRIMARIA
    @Column(name = "accommodationID")
    private int accommodationID;

    @Column(name = "area")
    private double area;

    @Column(name = "price")
    private double price;

    @Column(name = "campingCardDiscount")
    private double campingCardDiscount;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getAccommodationID() {
        return accommodationID;
    }

    public double getArea() {
        return area;
    }

    public double getPrice() {
        return price;
    }

    public double getCampingCardDiscount() {
        return campingCardDiscount;
    }

    public void setAccommodationID(int accommodationID) {
        this.accommodationID = accommodationID;
    }


    public void setArea(double area) {
        this.area = area;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCampingCardDiscount(double campingCardDiscount) {
        this.campingCardDiscount = campingCardDiscount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) accommodationID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TentPitch)) {
            return false;
        }
        TentPitch other = (TentPitch) object;
        if (this.accommodationID != other.accommodationID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TentPitch{" + "accommodationID=" + accommodationID + ", area=" + area + ", price=" + price + ", campingCardDiscount=" + campingCardDiscount + '}';
    }

  

}
