package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class AppPool implements Serializable {

    /** identifier field */
    private String poolid;

    /** nullable persistent field */
    private String pooldesc;

    /** nullable persistent field */
    private String poolflag1;

    /** nullable persistent field */
    private String poolflag2;

    /** nullable persistent field */
    private String poolType;

    /** full constructor */
    public AppPool(java.lang.String poolid, java.lang.String pooldesc, java.lang.String poolflag1, java.lang.String poolflag2, java.lang.String poolType) {
        this.poolid = poolid;
        this.pooldesc = pooldesc;
        this.poolflag1 = poolflag1;
        this.poolflag2 = poolflag2;
        this.poolType = poolType;
    }

    /** default constructor */
    public AppPool() {
    }

    /** minimal constructor */
    public AppPool(java.lang.String poolid) {
        this.poolid = poolid;
    }

    public java.lang.String getPoolid() {
		return (java.lang.String)HiltonUtility.ckNull(this.poolid).trim();
    }

    public void setPoolid(java.lang.String poolid) {
		if (!HiltonUtility.isEmpty(poolid) && poolid.length() > 15) {
			poolid = poolid.substring(0, 15);
		}
		this.poolid = poolid;
    }

    public java.lang.String getPooldesc() {
		return (java.lang.String)HiltonUtility.ckNull(this.pooldesc).trim();
    }

    public void setPooldesc(java.lang.String pooldesc) {
		if (!HiltonUtility.isEmpty(pooldesc) && pooldesc.length() > 60) {
			pooldesc = pooldesc.substring(0, 60);
		}
		this.pooldesc = pooldesc;
    }

    public java.lang.String getPoolflag1() {
		return (java.lang.String)HiltonUtility.ckNull(this.poolflag1).trim();
    }

    public void setPoolflag1(java.lang.String poolflag1) {
		if (!HiltonUtility.isEmpty(poolflag1) && poolflag1.length() > 1) {
			poolflag1 = poolflag1.substring(0, 1);
		}
		this.poolflag1 = poolflag1;
    }

    public java.lang.String getPoolflag2() {
		return (java.lang.String)HiltonUtility.ckNull(this.poolflag2).trim();
    }

    public void setPoolflag2(java.lang.String poolflag2) {
		if (!HiltonUtility.isEmpty(poolflag2) && poolflag2.length() > 1) {
			poolflag2 = poolflag2.substring(0, 1);
		}
		this.poolflag2 = poolflag2;
    }

    public String getPoolType() {
		return poolType;
	}

	public void setPoolType(String poolType) {
		if (!HiltonUtility.isEmpty(poolType) && poolType.length() > 1) {
			poolType = poolType.substring(0, 1);
		}
		this.poolType = poolType;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("poolid", getPoolid())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AppPool) ) return false;
        AppPool castOther = (AppPool) other;
        return new EqualsBuilder()
            .append(this.getPoolid(), castOther.getPoolid())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getPoolid())
            .toHashCode();
    }

}
