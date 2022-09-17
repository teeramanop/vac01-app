/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author teeramanop
 */
@Entity
@Table(name = "user_data")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserData.findAll", query = "SELECT u FROM UserData u"),
    @NamedQuery(name = "UserData.findByUserId", query = "SELECT u FROM UserData u WHERE u.userId = :userId"),
    @NamedQuery(name = "UserData.findByUserFname", query = "SELECT u FROM UserData u WHERE u.userFname = :userFname"),
    @NamedQuery(name = "UserData.findByUserLname", query = "SELECT u FROM UserData u WHERE u.userLname = :userLname"),
    @NamedQuery(name = "UserData.findByPassword", query = "SELECT u FROM UserData u WHERE u.password = :password"),
    @NamedQuery(name = "UserData.findByCompCode", query = "SELECT u FROM UserData u WHERE u.compCode = :compCode"),
    @NamedQuery(name = "UserData.findByBranchCode", query = "SELECT u FROM UserData u WHERE u.branchCode = :branchCode"),
    @NamedQuery(name = "UserData.findByEmail", query = "SELECT u FROM UserData u WHERE u.email = :email"),
    @NamedQuery(name = "UserData.findByPhone1", query = "SELECT u FROM UserData u WHERE u.phone1 = :phone1"),
    @NamedQuery(name = "UserData.findByPhone2", query = "SELECT u FROM UserData u WHERE u.phone2 = :phone2"),
    @NamedQuery(name = "UserData.findByVersion", query = "SELECT u FROM UserData u WHERE u.version = :version")})
public class UserData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "UserId")
    private String userId;
    @Size(max = 100)
    @Column(name = "UserFname")
    private String userFname;
    @Size(max = 100)
    @Column(name = "UserLname")
    private String userLname;
    @Size(max = 100)
    @Column(name = "Password")
    private String password;
    @Size(max = 50)
    @Column(name = "CompCode")
    private String compCode;
    @Size(max = 50)
    @Column(name = "BranchCode")
    private String branchCode;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "Email")
    private String email;
    @Size(max = 50)
    @Column(name = "Phone1")
    private String phone1;
    @Size(max = 50)
    @Column(name = "Phone2")
    private String phone2;
    @Column(name = "Version")
    private Integer version;

    public UserData() {
    }

    public UserData(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserFname() {
        return userFname;
    }

    public void setUserFname(String userFname) {
        this.userFname = userFname;
    }

    public String getUserLname() {
        return userLname;
    }

    public void setUserLname(String userLname) {
        this.userLname = userLname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompCode() {
        return compCode;
    }

    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserData)) {
            return false;
        }
        UserData other = (UserData) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.UserData[ userId=" + userId + " ]";
    }
    
}
