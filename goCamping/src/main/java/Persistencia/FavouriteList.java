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
@IdClass(FavouriteListKey.class)
@Table(name = "FavouriteList")
public class FavouriteList implements Serializable {
    //chaves estrangeiras
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "camperUsername")
    private String camperUsername;
    @Id
    @Column(name = "campsiteID")
    private int campsiteID;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCamperUsername() {
        return camperUsername;
    }

    public int getCampsiteID() {
        return campsiteID;
    }

    public void setCamperUsername(String camperUsername) {
        this.camperUsername = camperUsername;
    }

    public void setCampsiteID(int campsiteID) {
        this.campsiteID = campsiteID;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.camperUsername);
        hash = 61 * hash + this.campsiteID;
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
        final FavouriteList other = (FavouriteList) obj;
        if (this.campsiteID != other.campsiteID) {
            return false;
        }
        return Objects.equals(this.camperUsername, other.camperUsername);
    }

    @Override
    public String toString() {
        return "FavouriteList{" + "camperUsername=" + camperUsername + ", campsiteID=" + campsiteID + '}';
    }
    
   
}
