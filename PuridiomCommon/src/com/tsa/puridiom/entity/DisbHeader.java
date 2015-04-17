package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class DisbHeader implements Serializable
{

	/** identifier field */
	private java.math.BigDecimal icDsbHeader;

	/** nullable persistent field */
	private String disbNumber;

	/** nullable persistent field */
	private java.util.Date disbDate;

	/** nullable persistent field */
	private String status;

	/** nullable persistent field */
	private String internalComments;

	/** nullable persistent field */
	private String fiscalYear;

	/** nullable persistent field */
	private String owner;

	/** nullable persistent field */
	private java.util.Date dateEntered;

	/** nullable persistent field */
	private String itemLocation;

	/** nullable persistent field */
	private java.math.BigDecimal icAccount;

	/** nullable persistent field */
	private java.math.BigDecimal subtotal;

	/** nullable persistent field */
	private String approved;

	/** nullable persistent field */
	private String appBy;

	/** nullable persistent field */
	private java.util.Date appDate;

	/** nullable persistent field */
	private String appSigned;

	/** nullable persistent field */
	private String lastChgBy;

	/** nullable persistent field */
	private java.util.Date lastChgDate;

	/** nullable persistent field */
	private String disbType;

	/** nullable persistent field */
	private java.math.BigDecimal icReqHeader;

	/** nullable persistent field */
	private String requisitionNumber;

	/** nullable persistent field */
	private String requisitionerCode;

	/** nullable persistent field */
	private String printed;

	/** nullable persistent field */
	private java.math.BigDecimal icHeaderHistory;

	/** nullable persistent field */
	private String shipToCode;

    /** nullable persistent field */
    private String timeZone;

	private List accountList;

	private List docCommentList;

	private List docAttachmentList;

	private List disbLineList;

	private Address shipToAddress;

	private Address inventoryAddress;

	/** full constructor */
	public DisbHeader(java.math.BigDecimal icDsbHeader, java.lang.String disbNumber, java.util.Date disbDate, java.lang.String status, java.lang.String internalComments, java.lang.String fiscalYear, java.lang.String owner, java.util.Date dateEntered, java.lang.String itemLocation, java.math.BigDecimal icAccount, java.math.BigDecimal subtotal, java.lang.String approved, java.lang.String appBy, java.util.Date appDate, java.lang.String appSigned, java.lang.String lastChgBy, java.util.Date lastChgDate, java.lang.String disbType, java.math.BigDecimal icReqHeader, java.lang.String requisitionNumber, java.lang.String requisitionerCode, java.lang.String printed, java.math.BigDecimal icHeaderHistory, java.lang.String timeZone)
	{
		this.icDsbHeader = icDsbHeader;
		this.disbNumber = disbNumber;
		this.disbDate = disbDate;
		this.status = status;
		this.internalComments = internalComments;
		this.fiscalYear = fiscalYear;
		this.owner = owner;
		this.dateEntered = dateEntered;
		this.itemLocation = itemLocation;
		this.icAccount = icAccount;
		this.subtotal = subtotal;
		this.approved = approved;
		this.appBy = appBy;
		this.appDate = appDate;
		this.appSigned = appSigned;
		this.lastChgBy = lastChgBy;
		this.lastChgDate = lastChgDate;
		this.disbType = disbType;
		this.icReqHeader = icReqHeader;
		this.requisitionNumber = requisitionNumber;
		this.requisitionerCode = requisitionerCode;
		this.printed = printed;
		this.icHeaderHistory = icHeaderHistory;
        this.timeZone = timeZone;
	}

	/** default constructor */
	public DisbHeader()
	{
	}

	/** minimal constructor */
	public DisbHeader(java.math.BigDecimal icDsbHeader)
	{
		this.icDsbHeader = icDsbHeader;
	}

	public java.math.BigDecimal getIcDsbHeader()
	{
		return (java.math.BigDecimal) HiltonUtility.ckNull(this.icDsbHeader);
	}

	public void setIcDsbHeader(java.math.BigDecimal icDsbHeader)
	{
		this.icDsbHeader = icDsbHeader;
	}

	public java.lang.String getDisbNumber()
	{
		return (java.lang.String) HiltonUtility.ckNull(this.disbNumber).trim();
	}

	public void setDisbNumber(java.lang.String disbNumber)
	{
		if (!HiltonUtility.isEmpty(disbNumber) && disbNumber.length() > 20)
		{
			disbNumber = disbNumber.substring(0, 20);
		}
		this.disbNumber = disbNumber;
	}

	public java.util.Date getDisbDate()
	{
		return this.disbDate;
		// return HiltonUtility.ckNull(this.disbDate);
		// return (java.sql.Date)HiltonUtility.ckNull(this.disbDate);
	}

	public void setDisbDate(java.util.Date disbDate)
	{
		this.disbDate = disbDate;
	}

	public java.lang.String getStatus()
	{
		return (java.lang.String) HiltonUtility.ckNull(this.status).trim();
	}

	public void setStatus(java.lang.String status)
	{
		if (!HiltonUtility.isEmpty(status) && status.length() > 4)
		{
			status = status.substring(0, 4);
		}
		this.status = status;
	}

	public java.lang.String getInternalComments()
	{
		return (java.lang.String) HiltonUtility.ckNull(this.internalComments).trim();
	}

	public void setInternalComments(java.lang.String internalComments)
	{
		if (!HiltonUtility.isEmpty(internalComments) && internalComments.length() > 150)
		{
			internalComments = internalComments.substring(0, 150);
		}
		this.internalComments = internalComments;
	}

	public java.lang.String getFiscalYear()
	{
		return (java.lang.String) HiltonUtility.ckNull(this.fiscalYear).trim();
	}

	public void setFiscalYear(java.lang.String fiscalYear)
	{
		if (!HiltonUtility.isEmpty(fiscalYear) && fiscalYear.length() > 4)
		{
			fiscalYear = fiscalYear.substring(0, 4);
		}
		this.fiscalYear = fiscalYear;
	}

	public java.lang.String getOwner()
	{
		return (java.lang.String) HiltonUtility.ckNull(this.owner).trim();
	}

	public void setOwner(java.lang.String owner)
	{
		if (!HiltonUtility.isEmpty(owner) && owner.length() > 15)
		{
			owner = owner.substring(0, 15);
		}
		this.owner = owner;
	}

	public java.util.Date getDateEntered()
	{
		return this.dateEntered;
		// return HiltonUtility.ckNull(this.dateEntered);
		// return (java.sql.Date)HiltonUtility.ckNull(this.dateEntered);
	}

	public void setDateEntered(java.util.Date dateEntered)
	{
		this.dateEntered = dateEntered;
	}

	public java.lang.String getItemLocation()
	{
		return (java.lang.String) HiltonUtility.ckNull(this.itemLocation).trim();
	}

	public void setItemLocation(java.lang.String itemLocation)
	{
		if (!HiltonUtility.isEmpty(itemLocation) && itemLocation.length() > 15)
		{
			itemLocation = itemLocation.substring(0, 15);
		}
		this.itemLocation = itemLocation;
	}

	public java.math.BigDecimal getIcAccount()
	{
		return (java.math.BigDecimal) HiltonUtility.ckNull(this.icAccount);
	}

	public void setIcAccount(java.math.BigDecimal icAccount)
	{
		this.icAccount = icAccount;
	}

	public java.math.BigDecimal getSubtotal()
	{
		return (java.math.BigDecimal) HiltonUtility.ckNull(this.subtotal);
	}

	public void setSubtotal(java.math.BigDecimal subtotal)
	{
		this.subtotal = subtotal;
	}

	public java.lang.String getApproved()
	{
		return (java.lang.String) HiltonUtility.ckNull(this.approved).trim();
	}

	public void setApproved(java.lang.String approved)
	{
		if (!HiltonUtility.isEmpty(approved) && approved.length() > 1)
		{
			approved = approved.substring(0, 1);
		}
		this.approved = approved;
	}

	public java.lang.String getAppBy()
	{
		return (java.lang.String) HiltonUtility.ckNull(this.appBy).trim();
	}

	public void setAppBy(java.lang.String appBy)
	{
		if (!HiltonUtility.isEmpty(appBy) && appBy.length() > 15)
		{
			appBy = appBy.substring(0, 15);
		}
		this.appBy = appBy;
	}

	public java.util.Date getAppDate()
	{
		return this.appDate;
		// return HiltonUtility.ckNull(this.appDate);
		// return (java.sql.Date)HiltonUtility.ckNull(this.appDate);
	}

	public void setAppDate(java.util.Date appDate)
	{
		this.appDate = appDate;
	}

	public java.lang.String getAppSigned()
	{
		return (java.lang.String) HiltonUtility.ckNull(this.appSigned).trim();
	}

	public void setAppSigned(java.lang.String appSigned)
	{
		if (!HiltonUtility.isEmpty(appSigned) && appSigned.length() > 1)
		{
			appSigned = appSigned.substring(0, 1);
		}
		this.appSigned = appSigned;
	}

	public java.lang.String getLastChgBy()
	{
		return (java.lang.String) HiltonUtility.ckNull(this.lastChgBy).trim();
	}

	public void setLastChgBy(java.lang.String lastChgBy)
	{
		if (!HiltonUtility.isEmpty(lastChgBy) && lastChgBy.length() > 15)
		{
			lastChgBy = lastChgBy.substring(0, 15);
		}
		this.lastChgBy = lastChgBy;
	}

	public java.util.Date getLastChgDate()
	{
		return this.lastChgDate;
		// return HiltonUtility.ckNull(this.lastChgDate);
		// return (java.sql.Date)HiltonUtility.ckNull(this.lastChgDate);
	}

	public void setLastChgDate(java.util.Date lastChgDate)
	{
		this.lastChgDate = lastChgDate;
	}

	public java.lang.String getDisbType()
	{
		return (java.lang.String) HiltonUtility.ckNull(this.disbType).trim();
	}

	public void setDisbType(java.lang.String disbType)
	{
		if (!HiltonUtility.isEmpty(disbType) && disbType.length() > 2)
		{
			disbType = disbType.substring(0, 2);
		}
		this.disbType = disbType;
	}

	public java.math.BigDecimal getIcReqHeader()
	{
		return (java.math.BigDecimal) HiltonUtility.ckNull(this.icReqHeader);
	}

	public void setIcReqHeader(java.math.BigDecimal icReqHeader)
	{
		this.icReqHeader = icReqHeader;
	}

	public java.lang.String getRequisitionNumber()
	{
		return (java.lang.String) HiltonUtility.ckNull(this.requisitionNumber).trim();
	}

	public void setRequisitionNumber(java.lang.String requisitionNumber)
	{
		if (!HiltonUtility.isEmpty(requisitionNumber) && requisitionNumber.length() > 20)
		{
			requisitionNumber = requisitionNumber.substring(0, 20);
		}
		this.requisitionNumber = requisitionNumber;
	}

	public java.lang.String getRequisitionerCode()
	{
		return (java.lang.String) HiltonUtility.ckNull(this.requisitionerCode).trim();
	}

	public void setRequisitionerCode(java.lang.String requisitionerCode)
	{
		if (!HiltonUtility.isEmpty(requisitionerCode) && requisitionerCode.length() > 15)
		{
			requisitionerCode = requisitionerCode.substring(0, 15);
		}
		this.requisitionerCode = requisitionerCode;
	}

	public java.lang.String getPrinted()
	{
		return (java.lang.String) HiltonUtility.ckNull(this.printed).trim();
	}

	public void setPrinted(java.lang.String printed)
	{
		if (!HiltonUtility.isEmpty(printed) && printed.length() > 1)
		{
			printed = printed.substring(0, 1);
		}
		this.printed = printed;
	}

	public java.math.BigDecimal getIcHeaderHistory()
	{
		return (java.math.BigDecimal) HiltonUtility.ckNull(this.icHeaderHistory);
	}

	public void setIcHeaderHistory(java.math.BigDecimal icHeaderHistory)
	{
		this.icHeaderHistory = icHeaderHistory;
	}

    public String getTimeZone()
    {
        return (java.lang.String) HiltonUtility.ckNull(this.timeZone).trim();
    }

    public void setTimeZone(String timeZone)
    {
        if (!HiltonUtility.isEmpty(timeZone) && timeZone.length() > 30) {
            timeZone = timeZone.substring(0, 30);
        }
        this.timeZone = timeZone;
    }

	public String toString()
	{
		return new ToStringBuilder(this).append("icDsbHeader", getIcDsbHeader()).toString();
	}

	public boolean equals(Object other)
	{
		if (!(other instanceof DisbHeader))
			return false;
		DisbHeader castOther = (DisbHeader) other;
		return new EqualsBuilder().append(this.getIcDsbHeader(), castOther.getIcDsbHeader()).isEquals();
	}

	public int hashCode()
	{
		return new HashCodeBuilder().append(getIcDsbHeader()).toHashCode();
	}

	/**
	 * @return Returns the accountList.
	 */
	public List getAccountList()
	{
		return accountList;
	}

	/**
	 * @param accountList
	 *            The accountList to set.
	 */
	public void setAccountList(List accountList)
	{
		this.accountList = accountList;
	}

	/**
	 * @return Returns the docAttachmentList.
	 */
	public List getDocAttachmentList()
	{
		return docAttachmentList;
	}

	/**
	 * @param docAttachmentList
	 *            The docAttachmentList to set.
	 */
	public void setDocAttachmentList(List docAttachmentList)
	{
		this.docAttachmentList = docAttachmentList;
	}

	/**
	 * @return Returns the docCommentList.
	 */
	public List getDocCommentList()
	{
		return docCommentList;
	}

	/**
	 * @param docCommentList
	 *            The docCommentList to set.
	 */
	public void setDocCommentList(List docCommentList)
	{
		this.docCommentList = docCommentList;
	}

	/**
	 * @return Returns the shipToAddress.
	 */
	public Address getShipToAddress()
	{
		return shipToAddress;
	}

	/**
	 * @param shipToAddress
	 *            The shipToAddress to set.
	 */
	public void setShipToAddress(Address shipToAddress)
	{
		this.shipToAddress = shipToAddress;
	}

	public Address getInventoryAddress()
	{
		return inventoryAddress;
	}

	public void setInventoryAddress(Address inventoryAddress)
	{
		this.inventoryAddress = inventoryAddress;
	}

	/**
	 * @return Returns the disbLineList.
	 */
	public List getDisbLineList()
	{
		return disbLineList;
	}

	/**
	 * @param disbLineList
	 *            The disbLineList to set.
	 */
	public void setDisbLineList(List disbLineList)
	{
		this.disbLineList = disbLineList;
	}

	/**
	 * @return Returns the shipToCode.
	 */
	public String getShipToCode()
	{
		return shipToCode;
	}

	/**
	 * @param shipToCode
	 *            The shipToCode to set.
	 */
	public void setShipToCode(String shipToCode)
	{
		this.shipToCode = shipToCode;
	}

}
