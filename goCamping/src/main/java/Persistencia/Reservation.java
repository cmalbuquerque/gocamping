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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;

/**
 *
 * @author pirex
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
    
        //chaves estrangeiras

    @ManyToOne(targetEntity = Camper.class)
    @JoinColumn(name = "camperUsername")
    private String camperUsername;
    
    @ManyToOne(targetEntity = Campsite.class)
    @JoinColumn(name = "campsiteID")
    private int campsiteID;

    @OneToOne(targetEntity = Accommodation.class) 
    @JoinColumn(name = "accommodationID")
    private int accommodationID;
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCamperUsername() {
        return camperUsername;
    }

    public void setCamperUsername(String camperUsername) {
        this.camperUsername = camperUsername;
    }

    public int getCampsiteID() {
        return campsiteID;
    }

    public void setCampsiteID(int campsiteID) {
        this.campsiteID = campsiteID;
    }

    public int getAccommodationID() {
        return accommodationID;
    }

    public void setAccommodationID(int accommodationID) {
        this.accommodationID = accommodationID;
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
        if (this.id != other.id) {
            return false;
        }
        if (this.campsiteID != other.campsiteID) {
            return false;
        }
        if (this.accommodationID != other.accommodationID) {
            return false;
        }
        if (!Objects.equals(this.camperUsername, other.camperUsername)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", camperUsername=" + camperUsername + ", campsiteID=" + campsiteID + ", accommodationID=" + accommodationID + ", startDate=" + startDate + ", endDate=" + endDate + '}';
    }
    
    

    
}
