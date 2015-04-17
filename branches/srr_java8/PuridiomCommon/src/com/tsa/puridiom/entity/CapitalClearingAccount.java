package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;

/** @author Hibernate CodeGenerator */
public class CapitalClearingAccount {

	/** identifier field */
    private java.math.BigDecimal icHeader;

    /** identifier field */
    private String entity;

    /** nullable persistent field */
    private String department;

    /** nullable persistent field */
    private String account;

	/** full constructor */
    public CapitalClearingAccount(java.math.BigDecimal icHeader, java.lang.String entity, java.lang.String department, java.lang.String account) {
    	this.icHeader = icHeader;
    	this.entity = entity;
        this.department = department;
        this.account = account;
    }

    /** default constructor */
    public CapitalClearingAccount() {
    }

    /** minimal constructor */
    public CapitalClearingAccount(java.math.BigDecimal icHeader) {
        this.icHeader = icHeader;
    }

    public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public java.math.BigDecimal getIcHeader() {
		return icHeader;
	}

	public void setIcHeader(java.math.BigDecimal icHeader) {
		this.icHeader = icHeader;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}


}