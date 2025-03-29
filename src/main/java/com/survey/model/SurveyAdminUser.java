package com.survey.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import org.springframework.data.annotation.LastModifiedDate;

@Entity
public class SurveyAdminUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_admin_users")
    private int idAdminUsers;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "userid", nullable = false)
    private String userId;

    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @Column(name = "email_id", nullable = false)
    private String emailId;

    @Column(name = "mobile_no", nullable = false)
    private String mobileNo;

    @Column(name = "status", nullable = false, columnDefinition = "TINYINT DEFAULT 1")
    private byte status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "add_date", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @LastModifiedDate
    private Date addDate;

    // Getters and Setters

    public int getIdAdminUsers() {
        return idAdminUsers;
    }

    public void setIdAdminUsers(int idAdminUsers) {
        this.idAdminUsers = idAdminUsers;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }
}
