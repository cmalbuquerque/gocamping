/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andreia Patrocínio
 * @author Carolina Albuquerque
 * @author Diogo Jorge
 * @author Pedro Pires
 *
 */
@Entity
@Table(name = "Utilizador")
@XmlRootElement
public class Utilizador implements Serializable {

    private static final long serialVersionUID = 1L;

    public Utilizador() {
        //default contructor
    }

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @JoinColumn(name = "camper")
    @OneToOne(fetch = FetchType.EAGER)
    private Camper camper;

    
    @JoinColumn(name = "manager")
    @OneToOne(fetch = FetchType.EAGER)
    private Manager manager;

    @XmlTransient
    public Camper getCamper() {
        return camper;
    }

    public void setCamper(Camper camper) {
        this.camper = camper;
    }
    
    @XmlTransient
    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = GeneralStuff.Hash.getmd5Hash(password);
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.username);
        hash = 53 * hash + Objects.hashCode(this.camper);
        hash = 53 * hash + Objects.hashCode(this.manager);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            System.out.println("segundo é null");
            return false;
        }
        if (getClass() != obj.getClass()) {
            System.out.println("classes diferentes");
            return false;
        }
        final Utilizador other = (Utilizador) obj;
        if (!Objects.equals(this.username, other.username)) {
            System.out.println("nomes diferentes: " + this.username + "  " + other.username);
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            
            System.out.println("passwords diferentes\n" + this.password + " != " + other.password);
            return false;
        }
        return true;
    }



    @Override
    public String toString() {
        return "Utilizador com username " + username + ", camper=" + camper + ", manager=" + manager + '}';
    }

}
