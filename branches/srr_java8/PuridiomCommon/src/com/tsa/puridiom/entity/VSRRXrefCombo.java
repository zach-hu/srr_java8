package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;

/** @author Hibernate CodeGenerator */
public class VSRRXrefCombo {

	/** identifier field */
    private java.math.BigDecimal icXref;


    private String organizationID;

    private String accountID;

    private String projectID;





	/** full constructor */
    public VSRRXrefCombo(java.math.BigDecimal icXref) {
    	this.icXref=icXref;

    }

    /** default constructor */
    public VSRRXrefCombo() {
    }


    public java.math.BigDecimal getIcXref() {
    	return (java.math.BigDecimal)HiltonUtility.ckNull(this.icXref);
	}

	public void setIcXref(java.math.BigDecimal icXref) {
		this.icXref = icXref;
	}


	public String getOrganizationID() {
		return organizationID;
	}

	public void setOrganizationID(String organizationID) {
		this.organizationID = organizationID;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getProjectID() {
		return projectID;
	}

	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}

}