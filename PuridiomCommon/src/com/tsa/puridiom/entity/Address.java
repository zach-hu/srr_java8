package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.Entity;
import com.tsagate.foundation.utility.Utility;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Address extends Entity implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.AddressPK comp_id;

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

    /** nullable persistent field */
    private String inventory;

    /** nullable persistent field */
    private String shipTo;

    /** nullable persistent field */
    private String billTo;

    /** nullable persistent field */
    private String invoiceTo;

    /** nullable persistent field */
    private String issuedBy;

    /** nullable persistent field */
    private String offerTo;

    /** nullable persistent field */
    private String administeredBy;

    /** nullable persistent field */
    private String paymentBy;

    /** nullable persistent field */
    private String purchaseFor;

    /** nullable persistent field */
    private String addrFld10;

    /** nullable persistent field */
    private String addrFld11;

    /** nullable persistent field */
    private String addrFld12;

    /** nullable persistent field */
    private String addrFld13;

    /** nullable persistent field */
    private String addrFld14;

    /** nullable persistent field */
    private String addrFld15;

    /** nullable persistent field */
    private String addrFld16;

    /** nullable persistent field */
    private String addrFld17;

    /** nullable persistent field */
    private String addrFld18;

    /** nullable persistent field */
    private String addrFld19;

    /** nullable persistent field */
    private String addrFld20;

    /** nullable persistent field */
    private String taxCode;
    
    /** full constructor */
    public Address(com.tsa.puridiom.entity.AddressPK comp_id, java.lang.String addressLine1, java.lang.String addressLine2, java.lang.String addressLine3, java.lang.String addressLine4, java.lang.String city, java.lang.String state, java.lang.String postalCode, java.lang.String country, java.util.Date dateEntered, java.util.Date dateExpires, java.lang.String status, java.lang.String owner, java.lang.String inventory, java.lang.String shipTo, java.lang.String billTo, java.lang.String invoiceTo, java.lang.String issuedBy, java.lang.String offerTo, java.lang.String administeredBy, java.lang.String paymentBy, java.lang.String puchaseFor, java.lang.String addrFld10, java.lang.String addrFld11, java.lang.String addrFld12, java.lang.String addrFld13, java.lang.String addrFld14, java.lang.String addrFld15, java.lang.String addrFld16, java.lang.String addrFld17, java.lang.String addrFld18, java.lang.String addrFld19, java.lang.String addrFld20, String taxCode) {
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
        this.inventory = inventory;
        this.shipTo = shipTo;
        this.billTo = billTo;
        this.invoiceTo = invoiceTo;
        this.issuedBy = issuedBy;
        this.offerTo = offerTo;
        this.administeredBy = administeredBy;
        this.paymentBy = paymentBy;
        this.purchaseFor = puchaseFor;
        this.addrFld10 = addrFld10;
        this.addrFld11 = addrFld11;
        this.addrFld12 = addrFld12;
        this.addrFld13 = addrFld13;
        this.addrFld14 = addrFld14;
        this.addrFld15 = addrFld15;
        this.addrFld16 = addrFld16;
        this.addrFld17 = addrFld17;
        this.addrFld18 = addrFld18;
        this.addrFld19 = addrFld19;
        this.addrFld20 = addrFld20;
        this.taxCode = taxCode;
    }

    /** default constructor */
    public Address() {
    }

    /** minimal constructor */
    public Address(com.tsa.puridiom.entity.AddressPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.AddressPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.AddressPK comp_id) {
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

    public java.lang.String getInventory() {
		return (java.lang.String)HiltonUtility.ckNull(this.inventory).trim();
    }

    public void setInventory(java.lang.String inventory) {
		if (!HiltonUtility.isEmpty(inventory) && inventory.length() > 1) {
			inventory = inventory.substring(0, 1);
		}
		this.inventory = inventory;
    }

    public java.lang.String getShipTo() {
		return (java.lang.String)HiltonUtility.ckNull(this.shipTo).trim();
    }

    public void setShipTo(java.lang.String shipTo) {
		if (!HiltonUtility.isEmpty(shipTo) && shipTo.length() > 1) {
			shipTo = shipTo.substring(0, 1);
		}
		this.shipTo = shipTo;
    }

    public java.lang.String getBillTo() {
		return (java.lang.String)HiltonUtility.ckNull(this.billTo).trim();
    }

    public void setBillTo(java.lang.String billTo) {
		if (!HiltonUtility.isEmpty(billTo) && billTo.length() > 1) {
			billTo = billTo.substring(0, 1);
		}
		this.billTo = billTo;
    }

    public java.lang.String getInvoiceTo() {
		return (java.lang.String)HiltonUtility.ckNull(this.invoiceTo).trim();
    }

    public void setInvoiceTo(java.lang.String invoiceTo) {
		if (!HiltonUtility.isEmpty(invoiceTo) && invoiceTo.length() > 1) {
			invoiceTo = invoiceTo.substring(0, 1);
		}
		this.invoiceTo = invoiceTo;
    }

    public java.lang.String getIssuedBy() {
		return (java.lang.String)HiltonUtility.ckNull(this.issuedBy).trim();
    }

    public void setIssuedBy(java.lang.String issuedBy) {
		if (!HiltonUtility.isEmpty(issuedBy) && issuedBy.length() > 1) {
			issuedBy = issuedBy.substring(0, 1);
		}
		this.issuedBy = issuedBy;
    }

    public java.lang.String getOfferTo() {
		return (java.lang.String)HiltonUtility.ckNull(this.offerTo).trim();
    }

    public void setOfferTo(java.lang.String offerTo) {
		if (!HiltonUtility.isEmpty(offerTo) && offerTo.length() > 1) {
			offerTo = offerTo.substring(0, 1);
		}
		this.offerTo = offerTo;
    }

    public java.lang.String getAdministeredBy() {
		return (java.lang.String)HiltonUtility.ckNull(this.administeredBy).trim();
    }

    public void setAdministeredBy(java.lang.String administeredBy) {
		if (!HiltonUtility.isEmpty(administeredBy) && administeredBy.length() > 1) {
			administeredBy = administeredBy.substring(0, 1);
		}
		this.administeredBy = administeredBy;
    }

    public java.lang.String getPaymentBy() {
		return (java.lang.String)HiltonUtility.ckNull(this.paymentBy).trim();
    }

    public void setPaymentBy(java.lang.String paymentBy) {
		if (!HiltonUtility.isEmpty(paymentBy) && paymentBy.length() > 1) {
			paymentBy = paymentBy.substring(0, 1);
		}
		this.paymentBy = paymentBy;
    }

    public java.lang.String getPurchaseFor() {
		return (java.lang.String)HiltonUtility.ckNull(this.purchaseFor).trim();
    }

    public void setPurchaseFor(java.lang.String puchaseFor) {
        this.purchaseFor = puchaseFor;
    }

    public java.lang.String getAddrFld10() {
		return (java.lang.String)HiltonUtility.ckNull(this.addrFld10).trim();
    }

    public void setAddrFld10(java.lang.String addrFld10) {
		if (!HiltonUtility.isEmpty(addrFld10) && addrFld10.length() > 15) {
			addrFld10 = addrFld10.substring(0, 15);
		}
		this.addrFld10 = addrFld10;
    }

    public java.lang.String getAddrFld11() {
		return (java.lang.String)HiltonUtility.ckNull(this.addrFld11).trim();
    }

    public void setAddrFld11(java.lang.String addrFld11) {
		if (!HiltonUtility.isEmpty(addrFld11) && addrFld11.length() > 15) {
			addrFld11 = addrFld11.substring(0, 15);
		}
		this.addrFld11 = addrFld11;
    }

    public java.lang.String getAddrFld12() {
		return (java.lang.String)HiltonUtility.ckNull(this.addrFld12).trim();
    }

    public void setAddrFld12(java.lang.String addrFld12) {
		if (!HiltonUtility.isEmpty(addrFld12) && addrFld12.length() > 15) {
			addrFld12 = addrFld12.substring(0, 15);
		}
		this.addrFld12 = addrFld12;
    }

    public java.lang.String getAddrFld13() {
		return (java.lang.String)HiltonUtility.ckNull(this.addrFld13).trim();
    }

    public void setAddrFld13(java.lang.String addrFld13) {
		if (!HiltonUtility.isEmpty(addrFld13) && addrFld13.length() > 15) {
			addrFld13 = addrFld13.substring(0, 15);
		}
		this.addrFld13 = addrFld13;
    }

    public java.lang.String getAddrFld14() {
		return (java.lang.String)HiltonUtility.ckNull(this.addrFld14).trim();
    }

    public void setAddrFld14(java.lang.String addrFld14) {
		if (!HiltonUtility.isEmpty(addrFld14) && addrFld14.length() > 15) {
			addrFld14 = addrFld14.substring(0, 15);
		}
		this.addrFld14 = addrFld14;
    }

    public java.lang.String getAddrFld15() {
		return (java.lang.String)HiltonUtility.ckNull(this.addrFld15).trim();
    }

    public void setAddrFld15(java.lang.String addrFld15) {
		if (!HiltonUtility.isEmpty(addrFld15) && addrFld15.length() > 15) {
			addrFld15 = addrFld15.substring(0, 15);
		}
		this.addrFld15 = addrFld15;
    }

    public java.lang.String getAddrFld16() {
		return (java.lang.String)HiltonUtility.ckNull(this.addrFld16).trim();
    }

    public void setAddrFld16(java.lang.String addrFld16) {
		if (!HiltonUtility.isEmpty(addrFld16) && addrFld16.length() > 1) {
			addrFld16 = addrFld16.substring(0, 1);
		}
		this.addrFld16 = addrFld16;
    }

    public java.lang.String getAddrFld17() {
		return (java.lang.String)HiltonUtility.ckNull(this.addrFld17).trim();
    }

    public void setAddrFld17(java.lang.String addrFld17) {
		if (!HiltonUtility.isEmpty(addrFld17) && addrFld17.length() > 1) {
			addrFld17 = addrFld17.substring(0, 1);
		}
		this.addrFld17 = addrFld17;
    }

    public java.lang.String getAddrFld18() {
		return (java.lang.String)HiltonUtility.ckNull(this.addrFld18).trim();
    }

    public void setAddrFld18(java.lang.String addrFld18) {
		if (!HiltonUtility.isEmpty(addrFld18) && addrFld18.length() > 1) {
			addrFld18 = addrFld18.substring(0, 1);
		}
		this.addrFld18 = addrFld18;
    }

    public java.lang.String getAddrFld19() {
		return (java.lang.String)HiltonUtility.ckNull(this.addrFld19).trim();
    }

    public void setAddrFld19(java.lang.String addrFld19) {
		if (!HiltonUtility.isEmpty(addrFld19) && addrFld19.length() > 1) {
			addrFld19 = addrFld19.substring(0, 1);
		}
		this.addrFld19 = addrFld19;
    }

    public java.lang.String getAddrFld20() {
		return (java.lang.String)HiltonUtility.ckNull(this.addrFld20).trim();
    }

    public void setAddrFld20(java.lang.String addrFld20) {
		if (!HiltonUtility.isEmpty(addrFld20) && addrFld20.length() > 1) {
			addrFld20 = addrFld20.substring(0, 1);
		}
		this.addrFld20 = addrFld20;
    }

    public java.lang.String getTaxCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.taxCode).trim();
    }

    public void setTaxCode(java.lang.String taxCode) {
		if (!HiltonUtility.isEmpty(taxCode) && taxCode.length() > 15) {
		    taxCode = taxCode.substring(0, 15);
		}
		this.taxCode = taxCode;
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
