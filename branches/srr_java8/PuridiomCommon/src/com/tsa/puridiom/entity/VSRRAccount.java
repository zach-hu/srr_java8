package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class VSRRAccount implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.VSRRAccountPK comp_id;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date dateExpires;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private java.util.Date dateChanged;

    private java.lang.String tableInd;

    private java.math.BigDecimal tableNum;

    private java.lang.String tableOther;

    /** nullable persistent field */
    private String udf1Code;

    /** nullable persistent field */
    private String udf2Code;

    /** nullable persistent field */
    private String udf3Code;

    /** full constructor */
    public VSRRAccount(com.tsa.puridiom.entity.VSRRAccountPK comp_id, java.lang.String description, java.util.Date dateEntered, java.util.Date dateExpires, java.lang.String status, java.lang.String owner, java.util.Date dateChanged, java.lang.String tableInd, java.math.BigDecimal tableNum, java.lang.String tableOther
    		) {
        this.comp_id = comp_id;
        this.description = description;
        this.dateEntered = dateEntered;
        this.dateExpires = dateExpires;
        this.status = status;
        this.owner = owner;
        this.dateChanged = dateChanged;
        this.tableInd = tableInd;
        this.tableNum = tableNum;
        this.tableOther = tableOther;
    }

    /** default constructor */
    public VSRRAccount() {
    }

    /** minimal constructor */
    public VSRRAccount(com.tsa.puridiom.entity.VSRRAccountPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.VSRRAccountPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.VSRRAccountPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.lang.String getDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.description).trim();
    }

    public void setDescription(java.lang.String description) {
		if (!HiltonUtility.isEmpty(description) && description.length() > 255) {
			description = description.substring(0, 255);
		}
		this.description = description;
    }

    public java.util.Date getDateEntered() {
		return this.dateEntered;
//		return HiltonUtility.ckNull(this.dateEntered);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateEntered);
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.util.Date getDateExpires() {
		return this.dateExpires;
//		return HiltonUtility.ckNull(this.dateExpires);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateExpires);
    }

    public void setDateExpires(java.util.Date dateExpires) {
        this.dateExpires = dateExpires;
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

    public java.lang.String getOwner() {
		return (java.lang.String)HiltonUtility.ckNull(this.owner).trim();
    }

    public void setOwner(java.lang.String owner) {
		if (!HiltonUtility.isEmpty(owner) && owner.length() > 15) {
			owner = owner.substring(0, 15);
		}
		this.owner = owner;
    }

    public java.util.Date getDateChanged() {
		return this.dateChanged;
//		return HiltonUtility.ckNull(this.dateChanged);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateChanged);
    }

    public void setDateChanged(java.util.Date dateChanged) {
        this.dateChanged = dateChanged;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof StdTable) ) return false;
        StdTable castOther = (StdTable) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

    public String getTableType()
    {
    	return this.getComp_id().getTableType();
    }
    public String getTableKey()
    {
    	return this.getComp_id().getTableKey();
    }

	public java.lang.String getTableInd() {
		return tableInd;
	}

	public void setTableInd(java.lang.String tableInd) {
		if (!HiltonUtility.isEmpty(owner) && owner.length() > 15) {
			this.tableInd = tableInd.substring(0, 15);
		}
		this.tableInd = tableInd;
	}

	public java.math.BigDecimal getTableNum() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.tableNum);
	}

	public void setTableNum(java.math.BigDecimal tableNum) {
		this.tableNum = tableNum;
	}

	public java.lang.String getTableOther() {
		return tableOther;
	}

	public void setTableOther(java.lang.String tableOther) {
		if (!HiltonUtility.isEmpty(tableOther) && tableOther.length() > 15) {
			this.tableOther = tableOther.substring(0, 15);
		}
		this.tableOther = tableOther;
	}

    public java.lang.String getUdf1Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf1Code).trim();
    }

    public void setUdf1Code(java.lang.String udf1Code) {
		if (!HiltonUtility.isEmpty(udf1Code) && udf1Code.length() > 30) {
			udf1Code = udf1Code.substring(0, 30);
		}
		this.udf1Code = udf1Code;
    }

    public java.lang.String getUdf2Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf2Code).trim();
    }

    public void setUdf2Code(java.lang.String udf2Code) {
		if (!HiltonUtility.isEmpty(udf2Code) && udf2Code.length() > 30) {
			udf2Code = udf2Code.substring(0, 30);
		}
		this.udf2Code = udf2Code;
    }

    public java.lang.String getUdf3Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf3Code).trim();
    }

    public void setUdf3Code(java.lang.String udf3Code) {
		if (!HiltonUtility.isEmpty(udf3Code) && udf3Code.length() > 30) {
			udf3Code = udf3Code.substring(0, 30);
		}
		this.udf3Code = udf3Code;
    }
}
