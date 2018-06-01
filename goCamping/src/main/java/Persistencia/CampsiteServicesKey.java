/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.io.Serializable;

/**
 *
 * @author Andreia Patrocínio
 * @author Carolina Albuquerque
 * @author Diogo Jorge
 * @author Pedro Pires
 * 
 */
public class CampsiteServicesKey implements Serializable{
    private int campsiteID;
    private int serviceID;

    public CampsiteServicesKey(int campsiteID, int serviceID) {
        this.campsiteID = campsiteID;
        this.serviceID = serviceID;
    }

    
    public int getCampsiteID() {
        return campsiteID;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.campsiteID;
        hash = 29 * hash + this.serviceID;
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
        final CampsiteServicesKey other = (CampsiteServicesKey) obj;
        if (this.campsiteID != other.campsiteID) {
            return false;
        }
        if (this.serviceID != other.serviceID) {
            return false;
        }
        return true;
    }

    public int getServiceID() {
        return serviceID;
    }

    @Override
    public String toString() {
        return "CampsiteServicesKey{" + "campsiteID=" + campsiteID + ", serviceID=" + serviceID + '}';
    }
    
    
}
