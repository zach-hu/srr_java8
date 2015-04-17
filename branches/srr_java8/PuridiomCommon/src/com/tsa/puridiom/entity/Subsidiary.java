package com.tsa.puridiom.entity;

import java.io.Serializable;

import com.tsa.puridiom.common.utility.HiltonUtility;

/** @author Hibernate CodeGenerator */
public class Subsidiary implements Serializable {

	/** identifier field */
    private java.math.BigDecimal icHeader;

	/** identifier field */
    private String countryCode;

    /** identifier field */
    private String subsidiaryLanguaje;

    /** nullable persistent field */
    private String subsidiaryName;

    /** nullable persistent field */
    private String status;

    /** full constructor */
    public Subsidiary(java.math.BigDecimal icHeader, java.lang.String countryCode, java.lang.String subsidiaryLanguaje, java.lang.String subsidiaryName, java.lang.String status) {
        this.icHeader = icHeader;
    	this.countryCode = countryCode;
    	this.subsidiaryLanguaje = subsidiaryLanguaje;
        this.subsidiaryName = subsidiaryName;
        this.status = status;
    }

    /** default constructor */
    public Subsidiary() {
    }

    /** minimal constructor */
    public Subsidiary( java.math.BigDecimal icHeader) {
        this.icHeader = icHeader;
    }

    public java.math.BigDecimal getIcHeader() {
    	return (java.math.BigDecimal)HiltonUtility.ckNull(this.icHeader);
	}

	public void setIcHeader(java.math.BigDecimal icHeader) {
		this.icHeader = icHeader;
	}

    public java.lang.String getCountryCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.countryCode).trim();
    }

    public void setCountryCode(java.lang.String countryCode) {
		this.countryCode = countryCode;
    }

    public java.lang.String getSubsidiaryLanguaje() {
		return (java.lang.String)HiltonUtility.ckNull(this.subsidiaryLanguaje).trim();
    }

    public void setSubsidiaryLanguaje(java.lang.String subsidiaryLanguaje) {
		this.subsidiaryLanguaje = subsidiaryLanguaje;
    }

    public java.lang.String getSubsidiaryName() {
		return (java.lang.String)HiltonUtility.ckNull(this.subsidiaryName).trim();
    }

    public void setSubsidiaryName(java.lang.String subsidiaryName) {
		if (!HiltonUtility.isEmpty(subsidiaryName) && subsidiaryName.length() > 255) {
			subsidiaryName = subsidiaryName.substring(0, 4);
		}
    	this.subsidiaryName = subsidiaryName;
    }


    public java.lang.String getStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.status).trim();
    }

    public void setStatus(java.lang.String status) {
		this.status = status;
    }

}
