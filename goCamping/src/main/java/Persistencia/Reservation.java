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
import java.util.Date;
import java.util.Objects;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;

/**
 *
 * @author Andreia Patrocínio
 * @author Carolina Albuquerque
 * @author Diogo Jorge
 * @author Pedro Pires
 * 
 */
@Entity
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Temporal(DATE)
    @Column(name = "startDate")
    private Date startDate;

    @Temporal(DATE)
    @Column(name = "endDate")
    private Date endDate;
    
    @Column (name = "cellfone")
    private int cellfone;
    
    @Column (name="nrAdults")
    private int nrAdults;
    
    @Column (name="nrChildren")
    private int nrChildren;
    
    @Column (name="nrBabies")
    private int nrBabies;
    
    @Column (name="totalPrice")
    private double totalPrice;
    
    
    //chaves estrangeiras
    @ManyToOne()
    @JoinColumn(name = "camper")
    private Camper camper;
    
    @ManyToOne()
    @JoinColumn(name = "campsite")
    private Campsite campsite;

    public int getNrBabies() {
        return nrBabies;
    }

    public void setNrBabies(int nrBabies) {
        this.nrBabies = nrBabies;
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getCellfone() {
        return cellfone;
    }

    public void setCellfone(int cellfone) {
        this.cellfone = cellfone;
    }

    public int getNrAdults() {
        return nrAdults;
    }

    public void setNrAdults(int nrAdults) {
        this.nrAdults = nrAdults;
    }

    public int getNrChildren() {
        return nrChildren;
    }

    public void setNrChildren(int nrChildren) {
        this.nrChildren = nrChildren;
    }
   
      

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Camper getCamper() {
        return camper;
    }

    public void setCamper(Camper camper) {
        this.camper = camper;
    }

    public Campsite getCampsite() {
        return campsite;
    }

    public void setCampsite(Campsite campsite) {
        this.campsite = campsite;
    }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.startDate);
        hash = 97 * hash + Objects.hashCode(this.endDate);
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
        final Reservation other = (Reservation) obj;
        if (this.cellfone != other.cellfone) {
            return false;
        }
        if (this.nrAdults != other.nrAdults) {
            return false;
        }
        if (this.nrChildren != other.nrChildren) {
            return false;
        }
        if (this.nrBabies != other.nrBabies) {
            return false;
        }
        if (Double.doubleToLongBits(this.totalPrice) != Double.doubleToLongBits(other.totalPrice)) {
            return false;
        }
        if (!Objects.equals(this.camper, other.camper)) {
            return false;
        }
        if (!Objects.equals(this.campsite, other.campsite)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", cellfone=" + cellfone + ", nrAdults=" + nrAdults + ", nrChildren=" + nrChildren + ", nrBabies=" + nrBabies + ", totalPrice=" + totalPrice + ", camper=" + camper + ", campsite=" + campsite + '}';
    }

   
    
    

    
}
