package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class RecentOrderItem implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.RecentOrderItemPK comp_id;

    /** nullable persistent field */
    private java.lang.String itemSource;
    
    /** nullable persistent field */
    private java.lang.String description;
    
    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.math.BigDecimal orderCount;;
    
    /** full constructor */
    public RecentOrderItem(com.tsa.puridiom.entity.RecentOrderItemPK comp_id, java.lang.String itemSource, java.lang.String description, java.util.Date dateEntered, java.math.BigDecimal orderCount) {
        this.comp_id = comp_id;
        this.itemSource = itemSource;
        this.description = description;
        this.dateEntered = dateEntered;
        this.orderCount = orderCount;        
    }

    /** default constructor */
    public RecentOrderItem() {
    }

    /** minimal constructor */
    public RecentOrderItem(com.tsa.puridiom.entity.RecentOrderItemPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.RecentOrderItemPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.RecentOrderItemPK comp_id) {
        this.comp_id = comp_id;
    }

	public String getItemSource() {
		return (java.lang.String)HiltonUtility.ckNull(this.itemSource).trim();
	}

	public void setItemSource(String itemSource) {
		if (!HiltonUtility.isEmpty(itemSource) && itemSource.length() > 5) {
			itemSource = itemSource.substring(0, 5);
		}
		this.itemSource = itemSource;
	}

	public String getDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.description).trim();
	}

	public void setDescription(String description) {
	    if (HiltonUtility.ckNull(description).length() > 255) {
	        description = description.substring(0, 255);
	    }
		this.description = description;
	}
	
	public java.util.Date getDateEntered() {
		return this.dateEntered;
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.math.BigDecimal getOrderCount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.orderCount);
    }

    public void setOrderCount(java.math.BigDecimal orderCount) {
        this.orderCount = orderCount;
    }
    
    public String toString() {
        return new ToStringBuilder(this)
        	.append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RecentOrderItem) ) return false;
        RecentOrderItem castOther = (RecentOrderItem) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
        	.append(getComp_id())
            .toHashCode();
    }
}
