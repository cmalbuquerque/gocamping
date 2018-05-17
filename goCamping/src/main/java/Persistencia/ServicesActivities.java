/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.io.Serializable;
import java.util.Objects;
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
@Table(name = "ServicesActivities")
public class ServicesActivities implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    //AMBOS serviceDesc E SERVICEID SAO PRIMARY KEY COMPOSITE
    //CHAVE ESTRANGEIRA
    @Column (name = "serviceID")
    private int serviceID;
    @Column (name = "servicesDesc")
    private String servicesDesc;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getServiceID() {
        return serviceID;
    }

    public String getServicesDesc() {
        return servicesDesc;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public void setServicesDesc(String servicesDesc) {
        this.servicesDesc = servicesDesc;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.serviceID;
        hash = 53 * hash + Objects.hashCode(this.servicesDesc);
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
        final ServicesActivities other = (ServicesActivities) obj;
        if (this.serviceID != other.serviceID) {
            return false;
        }
        if (!Objects.equals(this.servicesDesc, other.servicesDesc)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ServicesActivities{" + "serviceID=" + serviceID + ", servicesDesc=" + servicesDesc + '}';
    }
    
    
    
}
