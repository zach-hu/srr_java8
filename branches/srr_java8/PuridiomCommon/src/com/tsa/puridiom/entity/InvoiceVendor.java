package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class InvoiceVendor implements Serializable {

    /** identifier field */
    private String vendorId;

    /** nullable persistent field */
    private String vendorName;

    /** nullable persistent field */
    private String fobCode;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String ownerEmail;

    /** nullable persistent field */
    private String notes;

    /** nullable persistent field */
    private String apReference;

    /** nullable persistent field */
    private String vendorAccount;

    /** nullable persistent field */
    private String eftBankName;
    
    /** nullable persistent field */
    private String eftAccountNumber;
    
    /** nullable persistent field */
    private String eftRoutingNumber;
    
    /** nullable persistent field */
    private String eftWireAccount;
    
    /** nullable persistent field */
    private String eftPersonName;
    
    /** nullable persistent field */
    private String eftAccountType;

    /** full constructor */
    public InvoiceVendor(java.lang.String vendorId, java.lang.String vendorName, String fobCode, java.util.Date dateEntered, java.lang.String status, java.lang.String ownerEmail, java.lang.String notes, java.lang.String apReference, java.lang.String vendorAccount) {
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.fobCode = fobCode;
        this.dateEntered = dateEntered;
        this.status = status;
        this.ownerEmail = ownerEmail;
        this.notes = notes;
        this.apReference = apReference;
        this.vendorAccount = vendorAccount;
    }

    /** default constructor */
    public InvoiceVendor() {
    }

    /** minimal constructor */
    public InvoiceVendor(java.lang.String vendorId) {
        this.vendorId = vendorId;
    }

    public java.lang.String getVendorId() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorId).trim();
    }

    public void setVendorId(java.lang.String vendorId) {
		if (!HiltonUtility.isEmpty(vendorId) && vendorId.length() > 15) {
			vendorId = vendorId.substring(0, 15);
		}
		this.vendorId = vendorId;
    }

    public java.lang.String getVendorName() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorName).trim();
    }

    public void setVendorName(java.lang.String vendorName) {
		if (!HiltonUtility.isEmpty(vendorName) && vendorName.length() > 40) {
			vendorName = vendorName.substring(0, 40);
		}
		this.vendorName = vendorName;
    }

    public java.lang.String getFobCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.fobCode).trim();
    }

    public void setFobCode(java.lang.String fobCode) {
		if (!HiltonUtility.isEmpty(fobCode) && fobCode.length() > 15) {
			fobCode = fobCode.substring(0, 15);
		}
		this.fobCode = fobCode;
    }

    public java.util.Date getDateEntered() {
        return this.dateEntered;
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
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

    public java.lang.String getOwnerEmail() {
		return (java.lang.String)HiltonUtility.ckNull(this.ownerEmail).trim();
    }

    public void setOwnerEmail(java.lang.String ownerEmail) {
		if (!HiltonUtility.isEmpty(ownerEmail) && ownerEmail.length() > 60) {
			ownerEmail = ownerEmail.substring(0, 60);
		}
		this.ownerEmail = ownerEmail;
    }

    public java.lang.String getNotes() {
		return (java.lang.String)HiltonUtility.ckNull(this.notes).trim();
    }

    public void setNotes(java.lang.String notes) {
		if (!HiltonUtility.isEmpty(notes) && notes.length() > 255) {
			notes = notes.substring(0, 255);
		}
		this.notes = notes;
    }

    public String getApReference() {
		return (java.lang.String)HiltonUtility.ckNull(this.apReference).trim();
    }

    public void setApReference(String apReference) {
		if (!HiltonUtility.isEmpty(apReference) && apReference.length() > 20) {
			apReference = apReference.substring(0, 20);
		}
		this.apReference = apReference;
    }

    public java.lang.String getVendorAccount() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorAccount).trim();
    }

    public void setVendorAccount(java.lang.String vendorAccount) {
		if (!HiltonUtility.isEmpty(vendorAccount) && vendorAccount.length() > 20) {
			vendorAccount = vendorAccount.substring(0, 20);
		}
		this.vendorAccount = vendorAccount;
    }
    
    public void setEftBankName(String eftBankName) {
    	if (!HiltonUtility.isEmpty(eftBankName) && eftBankName.length() > 40) {
    		eftBankName = eftBankName.substring(0, 40);
		}
		this.eftBankName = eftBankName;
	}

	public String getEftBankName() {
		return (java.lang.String)HiltonUtility.ckNull(this.eftBankName).trim();
	}

	public void setEftAccountNumber(String eftAccountNumber) {
		if (!HiltonUtility.isEmpty(eftAccountNumber) && eftAccountNumber.length() > 20) {
			eftAccountNumber = eftAccountNumber.substring(0, 20);
		}
		this.eftAccountNumber = eftAccountNumber;
	}

	public String getEftAccountNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.eftAccountNumber).trim();
	}

	public void setEftRoutingNumber(String eftRoutingNumber) {
		if (!HiltonUtility.isEmpty(eftRoutingNumber) && eftRoutingNumber.length() > 20) {
			eftRoutingNumber = eftRoutingNumber.substring(0, 20);
		}
		this.eftRoutingNumber = eftRoutingNumber;
	}

	public String getEftRoutingNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.eftRoutingNumber).trim();
	}

	public void setEftWireAccount(String eftWireAccount) {
		if (!HiltonUtility.isEmpty(eftWireAccount) && eftWireAccount.length() > 20) {
			eftWireAccount = eftWireAccount.substring(0, 20);
		}
		this.eftWireAccount = eftWireAccount;
	}

	public String getEftWireAccount() {
		return (java.lang.String)HiltonUtility.ckNull(this.eftWireAccount).trim();
	}

	public void setEftPersonName(String eftPersonName) {
		if (!HiltonUtility.isEmpty(eftPersonName) && eftPersonName.length() > 20) {
			eftPersonName = eftPersonName.substring(0, 20);
		}
		this.eftPersonName = eftPersonName;
	}

	public String getEftPersonName() {
		return (java.lang.String)HiltonUtility.ckNull(this.eftPersonName).trim();
	}

	public void setEftAccountType(String eftAccountType) {
		if (!HiltonUtility.isEmpty(eftAccountType) && eftAccountType.length() > 1) {
			eftAccountType = eftAccountType.substring(0, 1);
		}
		this.eftAccountType = eftAccountType;
	}

	public String getEftAccountType() {
		return (java.lang.String)HiltonUtility.ckNull(this.eftAccountType).trim();
	}

    public String toString() {
        return new ToStringBuilder(this)
            .append("vendorId", getVendorId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvoiceVendor) ) return false;
        InvoiceVendor castOther = (InvoiceVendor) other;
        return new EqualsBuilder()
            .append(this.getVendorId(), castOther.getVendorId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getVendorId())
            .toHashCode();
    }

}
