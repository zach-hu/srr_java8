package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.utility.Utility;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class InvoiceAddress implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.InvoiceAddressPK comp_id;

    /** nullable persistent field */
    private String addressLine1;

    /** nullable persistent field */
    private String addressLine2;

    /** nullable persistent field */
    private String addressLine3;

    /** nullable persistent field */
    private String addressLine4;

    /** nullable persistent field */
    private String city;

    /** nullable persistent field */
    private String state;

    /** nullable persistent field */
    private String postalCode;

    /** nullable persistent field */
    private String country;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date dateExpires;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String owner;

    /** full constructor */
    public InvoiceAddress(com.tsa.puridiom.entity.InvoiceAddressPK comp_id, java.lang.String addressLine1, java.lang.String addressLine2, java.lang.String addressLine3, java.lang.String addressLine4, java.lang.String city, java.lang.String state, java.lang.String postalCode, java.lang.String country, java.util.Date dateEntered, java.util.Date dateExpires, java.lang.String status, java.lang.String owner) {
        this.comp_id = comp_id;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.addressLine4 = addressLine4;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.dateEntered = dateEntered;
        this.dateExpires = dateExpires;
        this.status = status;
        this.owner = owner;
    }

    /** default constructor */
    public InvoiceAddress() {
    }

    /** minimal constructor */
    public InvoiceAddress(com.tsa.puridiom.entity.InvoiceAddressPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.InvoiceAddressPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.InvoiceAddressPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.lang.String getAddressLine1() {
		return (java.lang.String)HiltonUtility.ckNull(this.addressLine1).trim();
    }

    public void setAddressLine1(java.lang.String addressLine1) {
		if (!HiltonUtility.isEmpty(addressLine1) && addressLine1.length() > 40) {
			addressLine1 = addressLine1.substring(0, 40);
		}
		this.addressLine1 = addressLine1;
    }

    public java.lang.String getAddressLine2() {
		return (java.lang.String)HiltonUtility.ckNull(this.addressLine2).trim();
    }

    public void setAddressLine2(java.lang.String addressLine2) {
		if (!HiltonUtility.isEmpty(addressLine2) && addressLine2.length() > 40) {
			addressLine2 = addressLine2.substring(0, 40);
		}
		this.addressLine2 = addressLine2;
    }

    public java.lang.String getAddressLine3() {
		return (java.lang.String)HiltonUtility.ckNull(this.addressLine3).trim();
    }

    public void setAddressLine3(java.lang.String addressLine3) {
		if (!HiltonUtility.isEmpty(addressLine3) && addressLine3.length() > 40) {
			addressLine3 = addressLine3.substring(0, 40);
		}
		this.addressLine3 = addressLine3;
    }

    public java.lang.String getAddressLine4() {
		return (java.lang.String)HiltonUtility.ckNull(this.addressLine4).trim();
    }

    public void setAddressLine4(java.lang.String addressLine4) {
		if (!HiltonUtility.isEmpty(addressLine4) && addressLine4.length() > 40) {
			addressLine4 = addressLine4.substring(0, 40);
		}
		this.addressLine4 = addressLine4;
    }

    public java.lang.String getCity() {
		return (java.lang.String)HiltonUtility.ckNull(this.city).trim();
    }

    public void setCity(java.lang.String city) {
		if (!HiltonUtility.isEmpty(city) && city.length() > 30) {
			city = city.substring(0, 30);
		}
		this.city = city;
    }

    public java.lang.String getState() {
		return (java.lang.String)HiltonUtility.ckNull(this.state).trim();
    }

    public void setState(java.lang.String state) {
		if (!HiltonUtility.isEmpty(state) && state.length() > 15) {
			state = state.substring(0, 15);
		}
		this.state = state;
    }

    public java.lang.String getPostalCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.postalCode).trim();
    }

    public void setPostalCode(java.lang.String postalCode) {
		if (!HiltonUtility.isEmpty(postalCode) && postalCode.length() > 15) {
			postalCode = postalCode.substring(0, 15);
		}
		this.postalCode = postalCode;
    }

    public java.lang.String getCountry() {
		return (java.lang.String)HiltonUtility.ckNull(this.country).trim();
    }

    public void setCountry(java.lang.String country) {
		if (!HiltonUtility.isEmpty(country) && country.length() > 25) {
			country = country.substring(0, 25);
		}
		this.country = country;
    }

    public java.util.Date getDateEntered() {
        return this.dateEntered;
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.util.Date getDateExpires() {
        return this.dateExpires;
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

    public String getCityStatePostal()
    {
        StringBuffer sb = new StringBuffer();
        if(!Utility.isEmpty(this.getCity()) )
        {
            sb.append(this.getCity());
        }
        if(!Utility.isEmpty(this.getState()) )
        {
            if(sb.length() > 0)
            {
                sb.append(", ");
            }
            sb.append(this.getState());
        }
        if(!Utility.isEmpty(this.getPostalCode()) )
        {
            if(sb.length() > 0)
            {
                sb.append(" ");
            }
            sb.append(this.getPostalCode());
        }
        return sb.toString();
    }


    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Address) ) return false;
        Address castOther = (Address) other;
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
