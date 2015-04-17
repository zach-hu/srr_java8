package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class AssetCostPK implements Serializable {

	 /** identifier field */
    private java.math.BigDecimal sequenceNo;

    /** identifier field */
    private String tagNumber;

    /** full constructor */
    public AssetCostPK(BigDecimal sequenceNo, java.lang.String tagNumber)
    {
        this.sequenceNo = sequenceNo;
        this.tagNumber = tagNumber;
    }

    /** default constructor */
    public AssetCostPK() {
    }

	public java.math.BigDecimal getSequenceNo() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.sequenceNo);
	}

	public void setSequenceNo(java.math.BigDecimal sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public String getTagNumber() {
		return this.tagNumber;
	}

	public void setTagNumber(String tagNumber) {
		this.tagNumber = tagNumber;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("tagNumber", getTagNumber())
            .append("SequenceNo", getSequenceNo())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof AssetCostPK) ) return false;
        AssetCostPK castOther = (AssetCostPK) other;
        return new EqualsBuilder()
            .append(this.getSequenceNo(), castOther.getSequenceNo())
            .append(this.getTagNumber(), castOther.getTagNumber())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getSequenceNo())
            .append(getTagNumber())
            .toHashCode();
    }
}
