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
@IdClass(CampsiteServicesKey.class)
@Table(name = "CampsiteServices")  
public class CampsiteServices implements Serializable {

    private static final long serialVersionUID = 1L;
    //AMBOS CAMPSITEID E SERVICEID SAO PRIMARY KEY COMPOSITE
    //CHAVE ESTRANGEIRA
    @Id
    @Column(name = "campsiteID")
    private int campsiteID;
     @Id
    @Column(name = "serviceID")
    private int serviceID;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getCampsiteID() {
        return campsiteID;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setCampsiteID(int campsiteID) {
        this.campsiteID = campsiteID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.campsiteID;
        hash = 53 * hash + this.serviceID;
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
        final CampsiteServices other = (CampsiteServices) obj;
        if (this.campsiteID != other.campsiteID) {
            return false;
        }
        if (this.serviceID != other.serviceID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CampsiteServices{" + "campsiteID=" + campsiteID + ", serviceID=" + serviceID + '}';
    }
    
    
}
