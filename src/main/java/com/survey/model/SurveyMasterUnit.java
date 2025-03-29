package com.survey.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class SurveyMasterUnit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_units")
    private long idUnits;  // Changed to primitive 'long'

    @Column(name = "id_industrial", nullable = false)
    private long idIndustrial;  // Changed to primitive 'long'

    @Column(name = "units_name", nullable = false)
    private String unitsName;

    @Column(name = "status", nullable = false, columnDefinition = "TINYINT DEFAULT 1")
    private byte status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "add_date", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @LastModifiedDate
    private Date addDate;

    // Getters and Setters

    public long getIdUnits() {
        return idUnits;
    }

    public void setIdUnits(long idUnits) {
        this.idUnits = idUnits;
    }

    public long getIdIndustrial() {
        return idIndustrial;
    }

    public void setIdIndustrial(long idIndustrial) {
        this.idIndustrial = idIndustrial;
    }

    public String getUnitsName() {
        return unitsName;
    }

    public void setUnitsName(String unitsName) {
        this.unitsName = unitsName;
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
