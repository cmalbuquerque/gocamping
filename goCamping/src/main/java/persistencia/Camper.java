/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
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
@Table(name = "Camper")
@XmlRootElement
public class Camper implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "username")
    private String username;
    @Column(name = "fullname")
    private String fullName;
    @Column(name = "email")
    private String email;

    @Column(name = "nif")
    private int nif;
    @Column(name = "campsiteCard")
    private int campsiteCard;
    

    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "camper")
    private Utilizador user;

    @OneToMany(targetEntity = Reservation.class, mappedBy = "camper")
    @JoinColumn()
    private Set<Campsite> reservations;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getNif() {
        return nif;
    }

    public int getCampsiteCard() {
        return campsiteCard;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public void setCampsiteCard(int campsiteCard) {
        this.campsiteCard = campsiteCard;
    }

    @XmlTransient
    public Utilizador getUser() {
        return user;
    }

    public void setUser(Utilizador user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fullName != null ? fullName.hashCode() : 0);
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
        final Camper other = (Camper) obj;
        if (this.nif != other.nif) {
            return false;
        }
        return Objects.equals(this.username, other.username);
    }

    @Override
    public String toString() {
        return "Persistencia.Camper[ full name=" + fullName + " ]";
    }

}
