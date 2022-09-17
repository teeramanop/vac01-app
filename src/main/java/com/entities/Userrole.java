/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author teeramanop
 */
@Entity
@Table(name = "userrole")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userrole.findAll", query = "SELECT u FROM Userrole u"),
    @NamedQuery(name = "Userrole.findByUserId", query = "SELECT u FROM Userrole u WHERE u.userrolePK.userId = :userId"),
    @NamedQuery(name = "Userrole.findByRole", query = "SELECT u FROM Userrole u WHERE u.userrolePK.role = :role")})
public class Userrole implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserrolePK userrolePK;

    public Userrole() {
    }

    public Userrole(UserrolePK userrolePK) {
        this.userrolePK = userrolePK;
    }

    public Userrole(String userId, String role) {
        this.userrolePK = new UserrolePK(userId, role);
    }

    public UserrolePK getUserrolePK() {
        return userrolePK;
    }

    public void setUserrolePK(UserrolePK userrolePK) {
        this.userrolePK = userrolePK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userrolePK != null ? userrolePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userrole)) {
            return false;
        }
        Userrole other = (Userrole) object;
        if ((this.userrolePK == null && other.userrolePK != null) || (this.userrolePK != null && !this.userrolePK.equals(other.userrolePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Userrole[ userrolePK=" + userrolePK + " ]";
    }
    
}
