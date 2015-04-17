package com.tsa.puridiom.entity.sungard;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class VendorContacts implements Serializable {

    /** persistent field */
    private java.math.BigDecimal internalVendorId;

    /** persistent field */
    private java.math.BigDecimal associatedAddrId;

    /** persistent field */
    private String contactPhoneNum;

    /** persistent field */
    private String contactFaxNum;

    /** persistent field */
    private String contactName;

    /** persistent field */
    private String contactEmailAddr;

    /** full constructor */
    public VendorContacts(java.math.BigDecimal internalVendorId, java.math.BigDecimal associatedAddrId, java.lang.String contactPhoneNum, java.lang.String contactFaxNum, java.lang.String contactName, java.lang.String contactEmailAddr) {
        this.internalVendorId = internalVendorId;
        this.associatedAddrId = associatedAddrId;
        this.contactPhoneNum = contactPhoneNum;
        this.contactFaxNum = contactFaxNum;
        this.contactName = contactName;
        this.contactEmailAddr = contactEmailAddr;
    }

    /** default constructor */
    public VendorContacts() {
    }

    public java.math.BigDecimal getInternalVendorId() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.internalVendorId);
    }

    public void setInternalVendorId(java.math.BigDecimal internalVendorId) {
        this.internalVendorId = internalVendorId;
    }

    public java.math.BigDecimal getAssociatedAddrId() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.associatedAddrId);
    }

    public void setAssociatedAddrId(java.math.BigDecimal associatedAddrId) {
        this.associatedAddrId = associatedAddrId;
    }

    public java.lang.String getContactPhoneNum() {
		return (java.lang.String)HiltonUtility.ckNull(this.contactPhoneNum).trim();
    }

    public void setContactPhoneNum(java.lang.String contactPhoneNum) {
		if (!HiltonUtility.isEmpty(contactPhoneNum) && contactPhoneNum.length() > 25) {
			contactPhoneNum = contactPhoneNum.substring(0, 25);
		}
		this.contactPhoneNum = contactPhoneNum;
    }

    public java.lang.String getContactFaxNum() {
		return (java.lang.String)HiltonUtility.ckNull(this.contactFaxNum).trim();
    }

    public void setContactFaxNum(java.lang.String contactFaxNum) {
		if (!HiltonUtility.isEmpty(contactFaxNum) && contactFaxNum.length() > 25) {
			contactFaxNum = contactFaxNum.substring(0, 25);
		}
		this.contactFaxNum = contactFaxNum;
    }

    public java.lang.String getContactName() {
		return (java.lang.String)HiltonUtility.ckNull(this.contactName).trim();
    }

    public void setContactName(java.lang.String contactName) {
		if (!HiltonUtility.isEmpty(contactName) && contactName.length() > 60) {
			contactName = contactName.substring(0, 60);
		}
		this.contactName = contactName;
    }

    public java.lang.String getContactEmailAddr() {
		return (java.lang.String)HiltonUtility.ckNull(this.contactEmailAddr).trim();
    }

    public void setContactEmailAddr(java.lang.String contactEmailAddr) {
		if (!HiltonUtility.isEmpty(contactEmailAddr) && contactEmailAddr.length() > 60) {
			contactEmailAddr = contactEmailAddr.substring(0, 60);
		}
		this.contactEmailAddr = contactEmailAddr;
    }

    public String toString() {
        return new ToStringBuilder(this)
        .append("internalVendorId", getInternalVendorId())
        .append("associatedAddrId", getAssociatedAddrId())
        .toString();
    }

}
