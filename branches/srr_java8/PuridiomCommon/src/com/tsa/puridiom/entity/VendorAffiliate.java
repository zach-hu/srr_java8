package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class VendorAffiliate implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.VendorAffiliatePK comp_id;
    
    private com.tsa.puridiom.entity.Address address;
    
    private com.tsa.puridiom.entity.Contact contact;

    /** full constructor */
    public VendorAffiliate(com.tsa.puridiom.entity.VendorAffiliatePK comp_id) {
        this.comp_id = comp_id;
    }

    /** default constructor */
    public VendorAffiliate() {
    }

    public com.tsa.puridiom.entity.VendorAffiliatePK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.VendorAffiliatePK comp_id) {
        this.comp_id = comp_id;
    }
    
    public Address getAddress(){
    	if (this.address == null) {
    		this.address = new Address();
    	}
    	return this.address;
    }
    
    public void setAddress(Address address) {
    	this.address = address;
    }

    public Contact getContact(){
    	if (this.contact == null) {
    		this.contact = new Contact();
    	}
    	return this.contact;
    }
    
    public void setContact(Contact contact) {
    	this.contact = contact;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof VendorAffiliate) ) return false;
        VendorAffiliate castOther = (VendorAffiliate) other;
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
