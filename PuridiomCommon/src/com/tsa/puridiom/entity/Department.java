package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Department implements Serializable {

    /** identifier field */
    private String departmentCode;

    /** nullable persistent field */
    private String departmentName;

    /** nullable persistent field */
    private String deptManager;

    /** nullable persistent field */
    private String division;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date dateExpires;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String deptApprv1;

    /** nullable persistent field */
    private String deptApprv2;

    /** nullable persistent field */
    private String deptApprv3;

    /** nullable persistent field */
    private String deptApprv4;

    /** nullable persistent field */
    private String deptApprv5;

    /** persistent field */
    private Set departmentBuyers;

    /** nullable persistent field */
    private java.math.BigDecimal managerAmount;

    /** nullable persistent field */
    private java.math.BigDecimal apprv1Amount;

    /** nullable persistent field */
    private java.math.BigDecimal apprv2Amount;

    /** nullable persistent field */
    private java.math.BigDecimal apprv3Amount;

    /** nullable persistent field */
    private java.math.BigDecimal apprv4Amount;

    /** nullable persistent field */
    private java.math.BigDecimal apprv5Amount;

    /** full constructor */
    public Department(java.lang.String departmentCode, java.lang.String departmentName, java.lang.String deptManager, java.lang.String division, java.util.Date dateEntered, java.util.Date dateExpires, java.lang.String owner, java.lang.String status, java.lang.String deptApprv1, java.lang.String deptApprv2, java.lang.String deptApprv3, java.lang.String deptApprv4, java.lang.String deptApprv5, Set departmentBuyers, java.math.BigDecimal managerAmount, java.math.BigDecimal apprv1Amount, java.math.BigDecimal apprv2Amount, java.math.BigDecimal apprv3Amount, java.math.BigDecimal apprv4Amount, java.math.BigDecimal apprv5Amount) {
        this.departmentCode = departmentCode;
        this.departmentName = departmentName;
        this.deptManager = deptManager;
        this.division = division;
        this.dateEntered = dateEntered;
        this.dateExpires = dateExpires;
        this.owner = owner;
        this.status = status;
        this.deptApprv1 = deptApprv1;
        this.deptApprv2 = deptApprv2;
        this.deptApprv3 = deptApprv3;
        this.deptApprv4 = deptApprv4;
        this.deptApprv5 = deptApprv5;
        this.departmentBuyers = departmentBuyers;
        this.managerAmount = managerAmount;
        this.apprv1Amount = apprv1Amount;
        this.apprv2Amount = apprv2Amount;
        this.apprv3Amount = apprv3Amount;
        this.apprv4Amount = apprv4Amount;
        this.apprv5Amount = apprv5Amount;
    }

    /** default constructor */
    public Department() {
    }

    /** minimal constructor */
    public Department(java.lang.String departmentCode, Set departmentBuyers) {
        this.departmentCode = departmentCode;
        this.departmentBuyers = departmentBuyers;
    }

    public java.lang.String getDepartmentCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.departmentCode).trim();
    }

    public void setDepartmentCode(java.lang.String departmentCode) {
		if (!HiltonUtility.isEmpty(departmentCode) && departmentCode.length() > 15) {
			departmentCode = departmentCode.substring(0, 15);
		}
		this.departmentCode = departmentCode;
    }

    public java.lang.String getDepartmentName() {
		return (java.lang.String)HiltonUtility.ckNull(this.departmentName).trim();
    }

    public void setDepartmentName(java.lang.String departmentName) {
		if (!HiltonUtility.isEmpty(departmentName) && departmentName.length() > 60) {
			departmentName = departmentName.substring(0, 60);
		}
		this.departmentName = departmentName;
    }

    public java.lang.String getDeptManager() {
		return (java.lang.String)HiltonUtility.ckNull(this.deptManager).trim();
    }

    public void setDeptManager(java.lang.String deptManager) {
		if (!HiltonUtility.isEmpty(deptManager) && deptManager.length() > 15) {
			deptManager = deptManager.substring(0, 15);
		}
		this.deptManager = deptManager;
    }

    public java.lang.String getDivision() {
		return (java.lang.String)HiltonUtility.ckNull(this.division).trim();
    }

    public void setDivision(java.lang.String division) {
		if (!HiltonUtility.isEmpty(division) && division.length() > 15) {
			division = division.substring(0, 15);
		}
		this.division = division;
    }

    public java.util.Date getDateEntered() {
		return this.dateEntered;
//		return HiltonUtility.ckNull(this.dateEntered);
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.util.Date getDateExpires() {
		return this.dateExpires;
//		return HiltonUtility.ckNull(this.dateExpires);
    }

    public void setDateExpires(java.util.Date dateExpires) {
        this.dateExpires = dateExpires;
    }

    public java.lang.String getOwner() {
		return (java.lang.String)HiltonUtility.ckNull(this.owner).trim();
    }

    public void setOwner(java.lang.String owner) {
		if (!HiltonUtility.isEmpty(owner) && owner.length() > 15) {
			owner = owner.substring(0, 15);
		}
		this.owner = owner;
    }

    public java.lang.String getStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.status).trim();
    }

    public void setStatus(java.lang.String status) {
		if (!HiltonUtility.isEmpty(status) && status.length() > 4) {
			status = status.substring(0, 4);
		}
		this.status = status;
    }

    public java.lang.String getDeptApprv1() {
		return (java.lang.String)HiltonUtility.ckNull(this.deptApprv1).trim();
    }

    public void setDeptApprv1(java.lang.String deptApprv1) {
		if (!HiltonUtility.isEmpty(deptApprv1) && deptApprv1.length() > 15) {
			deptApprv1 = deptApprv1.substring(0, 15);
		}
		this.deptApprv1 = deptApprv1;
    }

    public java.lang.String getDeptApprv2() {
		return (java.lang.String)HiltonUtility.ckNull(this.deptApprv2).trim();
    }

    public void setDeptApprv2(java.lang.String deptApprv2) {
		if (!HiltonUtility.isEmpty(deptApprv2) && deptApprv2.length() > 15) {
			deptApprv2 = deptApprv2.substring(0, 15);
		}
		this.deptApprv2 = deptApprv2;
    }

    public java.lang.String getDeptApprv3() {
		return (java.lang.String)HiltonUtility.ckNull(this.deptApprv3).trim();
    }

    public void setDeptApprv3(java.lang.String deptApprv3) {
		if (!HiltonUtility.isEmpty(deptApprv3) && deptApprv3.length() > 15) {
			deptApprv3 = deptApprv3.substring(0, 15);
		}
		this.deptApprv3 = deptApprv3;
    }

    public java.lang.String getDeptApprv4() {
		return (java.lang.String)HiltonUtility.ckNull(this.deptApprv4).trim();
    }

    public void setDeptApprv4(java.lang.String deptApprv4) {
		if (!HiltonUtility.isEmpty(deptApprv4) && deptApprv4.length() > 15) {
			deptApprv4 = deptApprv4.substring(0, 15);
		}
		this.deptApprv4 = deptApprv4;
    }

    public java.lang.String getDeptApprv5() {
		return (java.lang.String)HiltonUtility.ckNull(this.deptApprv5).trim();
    }

    public void setDeptApprv5(java.lang.String deptApprv5) {
		if (!HiltonUtility.isEmpty(deptApprv5) && deptApprv5.length() > 15) {
			deptApprv5 = deptApprv5.substring(0, 15);
		}
		this.deptApprv5 = deptApprv5;
    }

    public java.math.BigDecimal getManagerAmount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.managerAmount);
    }

    public void setManagerAmount(java.math.BigDecimal managerAmount) {
        this.managerAmount = managerAmount;
    }

    public java.math.BigDecimal getApprv1Amount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.apprv1Amount);
    }

    public void setApprv1Amount(java.math.BigDecimal apprv1Amount) {
        this.apprv1Amount = apprv1Amount;
    }

    public java.math.BigDecimal getApprv2Amount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.apprv2Amount);
    }

    public void setApprv2Amount(java.math.BigDecimal apprv2Amount) {
        this.apprv2Amount = apprv2Amount;
    }

    public java.math.BigDecimal getApprv3Amount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.apprv3Amount);
    }

    public void setApprv3Amount(java.math.BigDecimal apprv3Amount) {
        this.apprv3Amount = apprv3Amount;
    }

    public java.math.BigDecimal getApprv4Amount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.apprv4Amount);
    }

    public void setApprv4Amount(java.math.BigDecimal apprv4Amount) {
        this.apprv4Amount = apprv4Amount;
    }

    public java.math.BigDecimal getApprv5Amount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.apprv5Amount);
    }

    public void setApprv5Amount(java.math.BigDecimal apprv5Amount) {
        this.apprv5Amount = apprv5Amount;
    }

    public java.util.Set getDepartmentBuyers() {
        return this.departmentBuyers;
    }

    public void setDepartmentBuyers(java.util.Set departmentBuyers) {
        this.departmentBuyers = departmentBuyers;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("departmentCode", getDepartmentCode())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Department) ) return false;
        Department castOther = (Department) other;
        return new EqualsBuilder()
            .append(this.getDepartmentCode(), castOther.getDepartmentCode())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getDepartmentCode())
            .toHashCode();
    }

}
