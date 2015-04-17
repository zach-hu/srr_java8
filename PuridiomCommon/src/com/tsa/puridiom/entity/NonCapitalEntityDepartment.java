package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;


/** @author Hibernate CodeGenerator */
public class NonCapitalEntityDepartment implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icHeader;

    /** nullable persistent field */
    private String division;

    /** identifier field */
    private String entity;

    /** nullable persistent field */
    private String department;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String description;


	/** full constructor */
    public NonCapitalEntityDepartment(java.math.BigDecimal icHeader, java.lang.String entity, java.lang.String department, java.lang.String status, java.lang.String description) {
    	this.icHeader = icHeader;
    	this.entity = entity;
        this.department = department;
        this.status = status;
        this.description = description;
    }

    /** default constructor */
    public NonCapitalEntityDepartment() {
    }

    /** minimal constructor */
    public NonCapitalEntityDepartment(java.math.BigDecimal icHeader) {
        this.icHeader = icHeader;
    }

    public java.math.BigDecimal getIcHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icHeader);
    }

    public void setIcHeader(java.math.BigDecimal icHeader) {
		this.icHeader = icHeader;
    }

    public java.lang.String getDivision() {
		return (java.lang.String)HiltonUtility.ckNull(this.division).trim();
    }

    public void setDivision(java.lang.String division) {
		this.division = division;
    }

    public java.lang.String getEntity() {
		return (java.lang.String)HiltonUtility.ckNull(this.entity).trim();
    }

    public void setEntity(java.lang.String entity) {
		this.entity = entity;
    }

    public java.lang.String getDepartment() {
		return (java.lang.String)HiltonUtility.ckNull(this.department).trim();
    }

    public void setDepartment(java.lang.String department) {
		this.department = department;
    }

    public java.lang.String getStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.status).trim();
    }

    public void setStatus(java.lang.String status) {
		this.status = status;
    }

    public java.lang.String getDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.description).trim();
    }

    public void setDescription(java.lang.String description) {
		this.description = description;
    }
}
