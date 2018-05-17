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
@Table(name = "CampsiteImages")  
public class CampsiteImages implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    //AMBAS SAO PRIMARY KEY COMPOSITE
    //CHAVE ESTRANGEIRA
    @Column (name = "campsiteID")
    private int campsiteID;
    @Column (name = "imageName")
    private String imageName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getCampsiteID() {
        return campsiteID;
    }

    public String getImageName() {
        return imageName;
    }

    public void setCampsiteID(int campsiteID) {
        this.campsiteID = campsiteID;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.campsiteID;
        hash = 71 * hash + Objects.hashCode(this.imageName);
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
        final CampsiteImages other = (CampsiteImages) obj;
        if (this.campsiteID != other.campsiteID) {
            return false;
        }
        if (!Objects.equals(this.imageName, other.imageName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CampsiteImages{" + "campsiteID=" + campsiteID + ", imageName=" + imageName + '}';
    }
    
       
    
}
