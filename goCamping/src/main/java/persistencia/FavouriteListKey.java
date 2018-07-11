/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import java.util.Objects;

/**
 *
* @author Andreia Patrocínio
 * @author Carolina Albuquerque
 * @author Diogo Jorge
 * @author Pedro Pires
 * 
 */
public class FavouriteListKey implements Serializable{
    private String camperUsername;
    private int campsiteID;

    public FavouriteListKey(String camperUsername, int campsiteID) {
        this.camperUsername = camperUsername;
        this.campsiteID = campsiteID;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.camperUsername);
        hash = 41 * hash + this.campsiteID;
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
        final FavouriteListKey other = (FavouriteListKey) obj;
        if (this.campsiteID != other.campsiteID) {
            return false;
        }
        return Objects.equals(this.camperUsername, other.camperUsername);
    }

    public String getCamperUsername() {
        return camperUsername;
    }

    public int getCampsiteID() {
        return campsiteID;
    }
    
   
    @Override
    public String toString() {
        return "FavouriteListKey{" + "camperUsername=" + camperUsername + ", campsiteID=" + campsiteID + '}';
    }
    
    
}
