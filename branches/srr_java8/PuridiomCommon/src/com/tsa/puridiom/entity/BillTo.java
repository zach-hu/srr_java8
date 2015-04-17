package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class BillTo implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.BillToPK comp_id;

    /** nullable persistent field */
    private java.math.BigDecimal quantity;

    /** nullable persistent field */
    private String attention;

    private Address billToAddress;

    /** full constructor */
    public BillTo(com.tsa.puridiom.entity.BillToPK comp_id, java.math.BigDecimal quantity, java.lang.String attention) {
        this.comp_id = comp_id;
        this.quantity = quantity;
        this.attention = attention;
    }

    /** default constructor */
    public BillTo() {
    }

    /** minimal constructor */
    public BillTo(com.tsa.puridiom.entity.BillToPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.BillToPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.BillToPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.math.BigDecimal getQuantity() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.quantity);
    }

    public void setQuantity(java.math.BigDecimal quantity) {
        this.quantity = quantity;
    }

    public java.lang.String getAttention() {
		return (java.lang.String)HiltonUtility.ckNull(this.attention).trim();
    }

    public void setAttention(java.lang.String attention) {
		if (!HiltonUtility.isEmpty(attention) && attention.length() > 40) {
			attention = attention.substring(0, 40);
		}
		this.attention = attention;
    }

	public Address getBillToAddress() {



		return this.billToAddress;
	}

	public void setBillToAddress(Address billToAddress) {
		this.billToAddress = billToAddress;
	}

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof BillTo) ) return false;
        BillTo castOther = (BillTo) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

    public String getBillToCode()
    {
    	return this.getComp_id().getBillToCode();
    }

}
