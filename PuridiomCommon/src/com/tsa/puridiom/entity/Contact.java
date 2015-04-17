package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.Entity;
import com.tsagate.foundation.utility.Utility;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Contact extends Entity implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.ContactPK comp_id;

    /** nullable persistent field */
    private String lastName;

    /** nullable persistent field */
    private String firstName;

    /** nullable persistent field */
    private String middleInit;

    /** nullable persistent field */
    private String salutation;

    /** nullable persistent field */
    private String contactTitle;

    /** nullable persistent field */
    private String phoneNumber;

    /** nullable persistent field */
    private String phoneFormat;

    /** nullable persistent field */
    private String mobileNumber;

    /** nullable persistent field */
    private String mobileFormat;

    /** nullable persistent field */
    private String faxNumber;

    /** nullable persistent field */
    private String faxFormat;

    /** nullable persistent field */
    private String addressCode;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date dateExpires;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private String emailAddr;

    /** nullable persistent field */
    private String info1;

    /** nullable persistent field */
    private String info2;

    /** nullable persistent field */
    private String info3;

    /** nullable persistent field */
    private String info4;

    /** nullable persistent field */
    private String contactPassword;

    /** nullable persistent field */
    private String passChanged;

    /** nullable persistent field */
    private String priorPassword;

    /** nullable persistent field */
    private String lockLogin;

    /** full constructor */
    public Contact(com.tsa.puridiom.entity.ContactPK comp_id, java.lang.String lastName, java.lang.String firstName, java.lang.String middleInit, java.lang.String salutation, java.lang.String contactTitle, java.lang.String phoneNumber, java.lang.String phoneFormat, java.lang.String mobileNumber, java.lang.String mobileFormat, java.lang.String faxNumber, java.lang.String faxFormat, java.lang.String addressCode, java.util.Date dateEntered, java.util.Date dateExpires, java.lang.String status, java.lang.String owner, java.lang.String emailAddr, java.lang.String info1, java.lang.String info2, java.lang.String info3, java.lang.String info4, java.lang.String contactPassword, String passChanged, java.lang.String priorPassword, java.lang.String lockLogin) {
        this.comp_id = comp_id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleInit = middleInit;
        this.salutation = salutation;
        this.contactTitle = contactTitle;
        this.phoneNumber = phoneNumber;
        this.phoneFormat = phoneFormat;
        this.mobileNumber = mobileNumber;
        this.mobileFormat = mobileFormat;
        this.faxNumber = faxNumber;
        this.faxFormat = faxFormat;
        this.addressCode = addressCode;
        this.dateEntered = dateEntered;
        this.dateExpires = dateExpires;
        this.status = status;
        this.owner = owner;
        this.emailAddr = emailAddr;
        this.info1 = info1;
        this.info2 = info2;
        this.info3 = info3;
        this.info4 = info4;
        this.contactPassword = contactPassword;
        this.passChanged = passChanged;
        this.priorPassword = priorPassword;
        this.lockLogin = lockLogin;
    }

    /** default constructor */
    public Contact() {
    }

    /** minimal constructor */
    public Contact(com.tsa.puridiom.entity.ContactPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.ContactPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.ContactPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.lang.String getLastName() {
		return (java.lang.String)HiltonUtility.ckNull(this.lastName).trim();
    }

    public void setLastName(java.lang.String lastName) {
		if (!HiltonUtility.isEmpty(lastName) && lastName.length() > 20) {
			lastName = lastName.substring(0, 20);
		}
		this.lastName = lastName;
    }

    public java.lang.String getFirstName() {
		return (java.lang.String)HiltonUtility.ckNull(this.firstName).trim();
    }

    public void setFirstName(java.lang.String firstName) {
		if (!HiltonUtility.isEmpty(firstName) && firstName.length() > 20) {
			firstName = firstName.substring(0, 20);
		}
		this.firstName = firstName;
    }

    public java.lang.String getMiddleInit() {
		return (java.lang.String)HiltonUtility.ckNull(this.middleInit).trim();
    }

    public void setMiddleInit(java.lang.String middleInit) {
		if (!HiltonUtility.isEmpty(middleInit) && middleInit.length() > 2) {
			middleInit = middleInit.substring(0, 2);
		}
		this.middleInit = middleInit;
    }

    public java.lang.String getSalutation() {
		return (java.lang.String)HiltonUtility.ckNull(this.salutation).trim();
    }

    public void setSalutation(java.lang.String salutation) {
		if (!HiltonUtility.isEmpty(salutation) && salutation.length() > 5) {
			salutation = salutation.substring(0, 5);
		}
		this.salutation = salutation;
    }

    public java.lang.String getContactTitle() {
		return (java.lang.String)HiltonUtility.ckNull(this.contactTitle).trim();
    }

    public void setContactTitle(java.lang.String contactTitle) {
		if (!HiltonUtility.isEmpty(contactTitle) && contactTitle.length() > 30) {
			contactTitle = contactTitle.substring(0, 30);
		}
		this.contactTitle = contactTitle;
    }

    public java.lang.String getPhoneNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.phoneNumber).trim();
    }

    public void setPhoneNumber(java.lang.String phoneNumber) {
		if (!HiltonUtility.isEmpty(phoneNumber) && phoneNumber.length() > 30) {
			phoneNumber = phoneNumber.substring(0, 30);
		}
		this.phoneNumber = phoneNumber;
    }

    public java.lang.String getPhoneFormat() {
		return (java.lang.String)HiltonUtility.ckNull(this.phoneFormat).trim();
    }

    public void setPhoneFormat(java.lang.String phoneFormat) {
		if (!HiltonUtility.isEmpty(phoneFormat) && phoneFormat.length() > 2) {
			phoneFormat = phoneFormat.substring(0, 2);
		}
		this.phoneFormat = phoneFormat;
    }

    public java.lang.String getMobileNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.mobileNumber).trim();
    }

    public void setMobileNumber(java.lang.String mobileNumber) {
		if (!HiltonUtility.isEmpty(mobileNumber) && mobileNumber.length() > 30) {
			mobileNumber = mobileNumber.substring(0, 30);
		}
		this.mobileNumber = mobileNumber;
    }

    public java.lang.String getMobileFormat() {
		return (java.lang.String)HiltonUtility.ckNull(this.mobileFormat).trim();
    }

    public void setMobileFormat(java.lang.String mobileFormat) {
		if (!HiltonUtility.isEmpty(mobileFormat) && mobileFormat.length() > 2) {
			mobileFormat = mobileFormat.substring(0, 2);
		}
		this.mobileFormat = mobileFormat;
    }

    public java.lang.String getFaxNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.faxNumber).trim();
    }

    public void setFaxNumber(java.lang.String faxNumber) {
		if (!HiltonUtility.isEmpty(faxNumber) && faxNumber.length() > 30) {
			faxNumber = faxNumber.substring(0, 30);
		}
		this.faxNumber = faxNumber;
    }

    public java.lang.String getFaxFormat() {
		return (java.lang.String)HiltonUtility.ckNull(this.faxFormat).trim();
    }

    public void setFaxFormat(java.lang.String faxFormat) {
		if (!HiltonUtility.isEmpty(faxFormat) && faxFormat.length() > 2) {
			faxFormat = faxFormat.substring(0, 2);
		}
		this.faxFormat = faxFormat;
    }

    public java.lang.String getAddressCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.addressCode).trim();
    }

    public void setAddressCode(java.lang.String addressCode) {
		if (!HiltonUtility.isEmpty(addressCode) && addressCode.length() > 15) {
			addressCode = addressCode.substring(0, 15);
		}
		this.addressCode = addressCode;
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
		return (java.lang.String)Utility.ckNull(this.owner).trim();
    }

    public void setOwner(java.lang.String owner) {
		if (!HiltonUtility.isEmpty(owner) && owner.length() > 15) {
			owner = owner.substring(0, 15);
		}
		this.owner = owner;
    }

    public java.lang.String getEmailAddr() {
		return (java.lang.String)Utility.ckNull(this.emailAddr).trim();
    }

    public void setEmailAddr(java.lang.String emailAddr) {
		if (!HiltonUtility.isEmpty(emailAddr) && emailAddr.length() > 50) {
			emailAddr = emailAddr.substring(0, 50);
		}
		this.emailAddr = emailAddr;
    }

    public java.lang.String getInfo1() {
		return (java.lang.String)HiltonUtility.ckNull(this.info1).trim();
    }

    public void setInfo1(java.lang.String info1) {
		if (!HiltonUtility.isEmpty(info1) && info1.length() > 50) {
			info1 = info1.substring(0, 50);
		}
		this.info1 = info1;
    }

    public java.lang.String getInfo2() {
		return (java.lang.String)HiltonUtility.ckNull(this.info2).trim();
    }

    public void setInfo2(java.lang.String info2) {
		if (!HiltonUtility.isEmpty(info2) && info2.length() > 50) {
			info2 = info2.substring(0, 50);
		}
		this.info2 = info2;
    }

    public java.lang.String getInfo3() {
		return (java.lang.String)HiltonUtility.ckNull(this.info3).trim();
    }

    public void setInfo3(java.lang.String info3) {
		if (!HiltonUtility.isEmpty(info3) && info3.length() > 50) {
			info3 = info3.substring(0, 50);
		}
		this.info3 = info3;
    }

    public java.lang.String getInfo4() {
		return (java.lang.String)HiltonUtility.ckNull(this.info4).trim();
    }

    public void setInfo4(java.lang.String info4) {
		if (!HiltonUtility.isEmpty(info4) && info4.length() > 50) {
			info4 = info4.substring(0, 50);
		}
		this.info4 = info4;
    }

    public java.lang.String getContactPassword() {
		return (java.lang.String)Utility.ckNull(this.contactPassword).trim();
    }

    public void setContactPassword(java.lang.String contactPassword) {
		if (!HiltonUtility.isEmpty(contactPassword) && contactPassword.length() > 12) {
			contactPassword = contactPassword.substring(0, 12);
		}
		this.contactPassword = contactPassword;
    }

    public java.lang.String getPassChanged() {
		return (java.lang.String)Utility.ckNull(this.passChanged).trim();
    }

    public void setPassChanged(java.lang.String passChanged) {
		if (!HiltonUtility.isEmpty(passChanged) && passChanged.length() > 10) {
			passChanged = passChanged.substring(0, 10);
		}
		this.passChanged = passChanged;
    }

    public java.lang.String getPriorPassword() {
		return (java.lang.String)Utility.ckNull(this.priorPassword).trim();
    }

    public void setPriorPassword(java.lang.String priorPassword) {
		if (!HiltonUtility.isEmpty(priorPassword) && priorPassword.length() > 255) {
			priorPassword = priorPassword.substring(0, 255);
		}
		this.priorPassword = priorPassword;
    }

    public java.lang.String getLockLogin() {
		return (java.lang.String)HiltonUtility.ckNull(this.lockLogin).trim();
    }

    public void setLockLogin(java.lang.String lockLogin) {
		if (!HiltonUtility.isEmpty(lockLogin) && lockLogin.length() > 1) {
			lockLogin = lockLogin.substring(0, 1);
		}
		this.lockLogin = lockLogin;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Contact) ) return false;
        Contact castOther = (Contact) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

    public String getDisplayName() {
    	StringBuffer name = new StringBuffer();
    	if (!HiltonUtility.isEmpty(this.firstName)) {
    		name.append(this.firstName.trim());
    	}
    	if (!HiltonUtility.isEmpty(this.middleInit)) {
    		if (name.length() > 0) {
    			name.append(" " + this.middleInit.trim());
    		} else {
    			name.append(this.middleInit.trim());
    		}
    	}
    	if (!HiltonUtility.isEmpty(this.lastName)) {
    		if (name.length() > 0) {
    			name.append(" " + this.lastName.trim());
    		} else {
    			name.append(this.lastName.trim());
    		}
    	}
    	if (HiltonUtility.isEmpty(name.toString())) {
    		return "";
    	}
    	return name.toString();
   	}

}
