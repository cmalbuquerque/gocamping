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
@Table(name = "Accommodation")
public class Accommodation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "accommodationID")
    private int accommodationID;

    @Column(name = "type")
    private String type;

    //CHAVE ESTRANGEIRA
    @ManyToOne(targetEntity = Campsite.class)
    @JoinColumn(name = "campsiteID")
    private int campsiteID;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getAccommodationID() {
        return accommodationID;
    }

    public String getType() {
        return type;
    }

    public int getCampsiteID() {
        return campsiteID;
    }

    public void setCampsiteID(int campsiteID) {
        this.campsiteID = campsiteID;
    }

    public void setAccommodationID(int accommodationID) {
        this.accommodationID = accommodationID;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accommodation)) {
            return false;
        }
        Accommodation other = (Accommodation) object;
        if (this.accommodationID != other.accommodationID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Accommodation{" + "accommodationID=" + accommodationID + ", type=" + type + ", campsiteID=" + campsiteID + '}';
    }

}
