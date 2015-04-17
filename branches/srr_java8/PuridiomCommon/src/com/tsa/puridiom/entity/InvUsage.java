package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InvUsage implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.InvUsagePK comp_id;

    /** nullable persistent field */
    private java.math.BigDecimal quantity;

    /** full constructor */
    public InvUsage(com.tsa.puridiom.entity.InvUsagePK comp_id, java.math.BigDecimal quantity) {
        this.comp_id = comp_id;
        this.quantity = quantity;
    }

    /** default constructor */
    public InvUsage() {
    }

    /** minimal constructor */
    public InvUsage(com.tsa.puridiom.entity.InvUsagePK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.InvUsagePK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.InvUsagePK comp_id) {
        this.comp_id = comp_id;
    }

    public java.math.BigDecimal getQuantity() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.quantity);
    }

    public void setQuantity(java.math.BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvUsage) ) return false;
        InvUsage castOther = (InvUsage) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }
    
    public BigDecimal getUsageMonth()
	{
    	if(this.comp_id != null)
    	{	
    		return this.comp_id.getUsageMonth();
    	}
    	else
    	{
    		return null;
    	}
	}
    
    public BigDecimal getUsageYear()
	{
    	if(this.comp_id != null)
    	{	
    		return this.comp_id.getUsageYear();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    public String getMonthName(java.math.BigDecimal bd_month)
    {
		String month = "";
		final String monthNames[] = {"January", "February", "March", 
				"April", "May", "June", "July", "August", "September", 
			"October", "November", "December"};
		if (bd_month != null)
		{
			month = monthNames[bd_month.intValue()];
		}
		
		return month;
	}
}
