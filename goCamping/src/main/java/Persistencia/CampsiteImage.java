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
@IdClass(CampsiteImageKey.class)
@Table(name = "CampsiteImage")  
public class CampsiteImage implements Serializable {

    private static final long serialVersionUID = 1L;
    
    //AMBAS SAO PRIMARY KEY COMPOSITE
    //CHAVE ESTRANGEIRA
    @Id
    private int campsiteID;
    @Id
    private String imageName;
    
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getCampsite() {
        return campsiteID;
    }

    public void setCampsite(int campsiteID) {
        this.campsiteID = campsiteID;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.campsiteID);
        hash = 59 * hash + Objects.hashCode(this.imageName);
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
        final CampsiteImage other = (CampsiteImage) obj;
        if (!Objects.equals(this.imageName, other.imageName)) {
            return false;
        }
        return Objects.equals(this.campsiteID, other.campsiteID);
    }

    @Override
    public String toString() {
        return "CampsiteImage{" + "campsiteID=" + campsiteID + ", imageName=" + imageName + '}';
    }

    
       
    
}
