/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 *
 * @author Andreia Patrocínio
 * @author Carolina Albuquerque
 * @author Diogo Jorge
 * @author Pedro Pires
 * 
 */


public class CampsiteImageKey implements Serializable{
    private int campsiteID;
    private String imageName;
    
    public CampsiteImageKey(){
    }
    
    public CampsiteImageKey(int campsiteID, String imageName){
        this.campsiteID=campsiteID;
        this.imageName=imageName;
    }

    public int getCampsiteID() {
        return campsiteID;
    }

    

    public String getImageName() {
        return imageName;
    }

   

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.campsiteID);
        hash = 37 * hash + Objects.hashCode(this.imageName);
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
        final CampsiteImageKey other = (CampsiteImageKey) obj;
        if (!Objects.equals(this.imageName, other.imageName)) {
            return false;
        }
        if (!Objects.equals(this.campsiteID, other.campsiteID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CampsiteImageKey{" + "campsiteID=" + campsiteID + ", imageName=" + imageName + '}';
    }

   
    
         
}
