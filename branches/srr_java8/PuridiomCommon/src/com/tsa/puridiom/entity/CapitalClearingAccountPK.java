package com.tsa.puridiom.entity;
import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class CapitalClearingAccountPK {

	/** identifier field */
    private String entity;

    /** nullable persistent field */
    private String department;

    /** nullable persistent field */
    private String account;

    /** full constructor */
    public CapitalClearingAccountPK(java.lang.String entity, java.lang.String department, java.lang.String account) {
        this.entity = entity;
        this.department = department;
        this.account = account;
    }

    /** default constructor */
    public CapitalClearingAccountPK() {
    }

    public java.lang.String getEntity() {
		return (java.lang.String)HiltonUtility.ckNull(this.entity);
    }

    public void setEntity(java.lang.String entity) {
        this.entity = entity;
    }

	public String getDepartment() {
		return (java.lang.String)HiltonUtility.ckNull(this.department);
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getAccount() {
		return (java.lang.String)HiltonUtility.ckNull(this.account);
	}

	public void setAccount(String account) {
		this.account = account;
	}


}
