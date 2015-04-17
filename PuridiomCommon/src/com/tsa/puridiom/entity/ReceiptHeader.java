package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ReceiptHeader implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icRecHeader;

    /** nullable persistent field */
    private java.math.BigDecimal icPoHeader;

    /** nullable persistent field */
    private java.math.BigDecimal icReqHeader;

    /** nullable persistent field */
    private java.util.Date receiptDate;

    /** nullable persistent field */
    private String receiptType;

    /** nullable persistent field */
    private String receivedBy;

    /** nullable persistent field */
    private String carrierCode;

    /** nullable persistent field */
    private String packingSlip;

    /** nullable persistent field */
    private String vendorId;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private String receiptStatus;

    /** nullable persistent field */
    private String refNumber;

    /** nullable persistent field */
    private String refRelease;

    /** nullable persistent field */
    private java.util.Date refDate;

    /** nullable persistent field */
    private String refType;

    /** nullable persistent field */
    private String vendorName;

    /** nullable persistent field */
    private String receiptNumber;

    /** nullable persistent field */
    private String fiscalYear;

    /** nullable persistent field */
    private String forwardTo;

    /** nullable persistent field */
    private BigDecimal pkgsReceived;

    /** nullable persistent field */
    private String receiptNotes;

    /** nullable persistent field */
    private BigDecimal releaseNumber;

    /** nullable persistent field */
    private java.util.Date returnDate;

    /** nullable persistent field */
    private String shipToCode;

    /** nullable persistent field */
    private String shipToInv;

    /** nullable persistent field */
    private java.math.BigDecimal icHeaderHistory;

    /** nullable persistent field */
    private String timeZone;

    /** nullable persistent field */
    private String itemLocation;

    /** nullable persistent field */
    private java.math.BigDecimal tempIc;

    /** nullable persistent field */
    private String corrosionEval;

    /** nullable persistent field */
    private String inspectionRequired;

    private List receiptLineList;

    private List accountList;

    private List docCommentList;

    private List docAttachmentList;

    private Address shipToAddress;

    private Address vendorAddress;

    /** full constructor */
    public ReceiptHeader(java.math.BigDecimal icRecHeader, java.math.BigDecimal icPoHeader, java.math.BigDecimal icReqHeader, java.util.Date receiptDate, java.lang.String receiptType, java.lang.String receivedBy, java.lang.String carrierCode, java.lang.String packingSlip, java.lang.String vendorId, java.lang.String owner, java.lang.String receiptStatus, java.lang.String refNumber, java.lang.String refRelease, java.util.Date refDate, java.lang.String refType, java.lang.String vendorName, java.lang.String receiptNumber, java.lang.String fiscalYear, java.lang.String forwardTo, java.math.BigDecimal pkgsReceived, java.lang.String receiptNotes, java.math.BigDecimal releaseNumber, java.util.Date returnDate, String shipToCode, String shipToInv, java.lang.String timeZone, java.math.BigDecimal icHeaderHistory, java.lang.String itemLocation, java.math.BigDecimal tempIc, java.lang.String corrosionEval) {
        this.icRecHeader = icRecHeader;
        this.icPoHeader = icPoHeader;
        this.icReqHeader = icReqHeader;
        this.receiptDate = receiptDate;
        this.receiptType = receiptType;
        this.receivedBy = receivedBy;
        this.carrierCode = carrierCode;
        this.packingSlip = packingSlip;
        this.vendorId = vendorId;
        this.owner = owner;
        this.receiptStatus = receiptStatus;
        this.refNumber = refNumber;
        this.refRelease = refRelease;
        this.refDate = refDate;
        this.refType = refType;
        this.vendorName = vendorName;
        this.receiptNumber = receiptNumber;
        this.fiscalYear = fiscalYear;
        this.forwardTo = forwardTo;
        this.pkgsReceived = pkgsReceived;
        this.receiptNotes = receiptNotes;
        this.releaseNumber = releaseNumber;
        this.returnDate = returnDate;
        this.shipToCode = shipToCode;
        this.shipToInv = shipToInv;
        this.timeZone = timeZone;
        this.icHeaderHistory = icHeaderHistory;
        this.itemLocation = itemLocation;
        this.tempIc = tempIc;
        this.corrosionEval = corrosionEval;
    }

    /** default constructor */
    public ReceiptHeader() {
    }

    /** minimal constructor */
    public ReceiptHeader(java.math.BigDecimal icRecHeader) {
        this.icRecHeader = icRecHeader;
    }

    public java.math.BigDecimal getIcRecHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icRecHeader);
    }

    public void setIcRecHeader(java.math.BigDecimal icRecHeader) {
        this.icRecHeader = icRecHeader;
    }

    public java.math.BigDecimal getIcPoHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icPoHeader);
    }

    public void setIcPoHeader(java.math.BigDecimal icPoHeader) {
        this.icPoHeader = icPoHeader;
    }

    public java.math.BigDecimal getIcReqHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icReqHeader);
    }

    public void setIcReqHeader(java.math.BigDecimal icReqHeader) {
        this.icReqHeader = icReqHeader;
    }

    public java.util.Date getReceiptDate() {
		return this.receiptDate;
//		return HiltonUtility.ckNull(this.receiptDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.receiptDate);
    }

    public void setReceiptDate(java.util.Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public java.lang.String getReceiptType() {
		return (java.lang.String)HiltonUtility.ckNull(this.receiptType).trim();
    }

    public void setReceiptType(java.lang.String receiptType) {
		if (!HiltonUtility.isEmpty(receiptType) && receiptType.length() > 1) {
			receiptType = receiptType.substring(0, 1);
		}
		this.receiptType = receiptType;
    }

    public java.lang.String getReceivedBy() {
		return (java.lang.String)HiltonUtility.ckNull(this.receivedBy).trim();
    }

    public void setReceivedBy(java.lang.String receivedBy) {
		if (!HiltonUtility.isEmpty(receivedBy) && receivedBy.length() > 15) {
			receivedBy = receivedBy.substring(0, 15);
		}
		this.receivedBy = receivedBy;
    }

    public java.lang.String getCarrierCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.carrierCode).trim();
    }

    public void setCarrierCode(java.lang.String carrierCode) {
		if (!HiltonUtility.isEmpty(carrierCode) && carrierCode.length() > 15) {
			carrierCode = carrierCode.substring(0, 15);
		}
		this.carrierCode = carrierCode;
    }

    public java.lang.String getPackingSlip() {
		return (java.lang.String)HiltonUtility.ckNull(this.packingSlip).trim();
    }

    public void setPackingSlip(java.lang.String packingSlip) {
		if (!HiltonUtility.isEmpty(packingSlip) && packingSlip.length() > 15) {
			packingSlip = packingSlip.substring(0, 15);
		}
		this.packingSlip = packingSlip;
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

    public java.lang.String getOwner() {
		return (java.lang.String)HiltonUtility.ckNull(this.owner).trim();
    }

    public void setOwner(java.lang.String owner) {
		if (!HiltonUtility.isEmpty(owner) && owner.length() > 15) {
			owner = owner.substring(0, 15);
		}
		this.owner = owner;
    }

    public java.lang.String getReceiptStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.receiptStatus).trim();
    }

    public void setReceiptStatus(java.lang.String receiptStatus) {
		if (!HiltonUtility.isEmpty(receiptStatus) && receiptStatus.length() > 4) {
			receiptStatus = receiptStatus.substring(0, 4);
		}
		this.receiptStatus = receiptStatus;
    }

    public java.lang.String getRefNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.refNumber).trim();
    }

    public void setRefNumber(java.lang.String refNumber) {
		if (!HiltonUtility.isEmpty(refNumber) && refNumber.length() > 20) {
			refNumber = refNumber.substring(0, 20);
		}
		this.refNumber = refNumber;
    }

    public java.lang.String getRefRelease() {
		return (java.lang.String)HiltonUtility.ckNull(this.refRelease).trim();
    }

    public void setRefRelease(java.lang.String refRelease) {
		if (!HiltonUtility.isEmpty(refRelease) && refRelease.length() > 20) {
			refRelease = refRelease.substring(0, 20);
		}
		this.refRelease = refRelease;
    }

    public java.util.Date getRefDate() {
		return this.refDate;
//		return HiltonUtility.ckNull(this.refDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.refDate);
    }

    public void setRefDate(java.util.Date refDate) {
        this.refDate = refDate;
    }

    public java.lang.String getRefType() {
		return (java.lang.String)HiltonUtility.ckNull(this.refType).trim();
    }

    public void setRefType(java.lang.String refType) {
		if (!HiltonUtility.isEmpty(refType) && refType.length() > 3) {
			refType = refType.substring(0, 3);
		}
		this.refType = refType;
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

    public java.lang.String getReceiptNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.receiptNumber).trim();
    }

    public void setReceiptNumber(java.lang.String receiptNumber) {
		if (!HiltonUtility.isEmpty(receiptNumber) && receiptNumber.length() > 20) {
			receiptNumber = receiptNumber.substring(0, 20);
		}
		this.receiptNumber = receiptNumber;
    }

    public java.lang.String getFiscalYear() {
		return (java.lang.String)HiltonUtility.ckNull(this.fiscalYear).trim();
    }

    public void setFiscalYear(java.lang.String fiscalYear) {
		if (!HiltonUtility.isEmpty(fiscalYear) && fiscalYear.length() > 4) {
			fiscalYear = fiscalYear.substring(0, 4);
		}
		this.fiscalYear = fiscalYear;
    }

    public java.lang.String getForwardTo() {
		return (java.lang.String)HiltonUtility.ckNull(this.forwardTo).trim();
    }

    public void setForwardTo(java.lang.String forwardTo) {
		if (!HiltonUtility.isEmpty(forwardTo) && forwardTo.length() > 15) {
			forwardTo = forwardTo.substring(0, 15);
		}
		this.forwardTo = forwardTo;
    }

    public java.math.BigDecimal getPkgsReceived() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.pkgsReceived);
    }

    public void setPkgsReceived(java.math.BigDecimal pkgsRecived) {
    	this.pkgsReceived = pkgsRecived;
    }

    public java.lang.String getReceiptNotes() {
		return (java.lang.String)HiltonUtility.ckNull(this.receiptNotes).trim();
   	}

    public void setReceiptNotes(java.lang.String receiptNotes) {
		if (!HiltonUtility.isEmpty(receiptNotes) && receiptNotes.length() > 255) {
			receiptNotes = receiptNotes.substring(0, 255);
		}
		this.receiptNotes = receiptNotes;
    }

    public java.math.BigDecimal getReleaseNumber() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.releaseNumber);
    }

    public void setReleaseNumber(java.math.BigDecimal releaseNumber) {
    	this.releaseNumber = releaseNumber;
    }

    public java.util.Date getReturnDate() {
		return this.returnDate;
    }

    public void setReturnDate(java.util.Date returnDate) {
        this.returnDate = returnDate;
    }

    public void setShipToCode(String shipToCode) {
        if (!HiltonUtility.isEmpty(shipToCode) && shipToCode.length() > 15) {
            shipToCode = shipToCode.substring(0, 15);
        }
        this.shipToCode = shipToCode;
    }

    public String getShipToCode() {
        return (java.lang.String)HiltonUtility.ckNull(this.shipToCode).trim();
    }

    public void setShipToInv(String shipToInv) {
        if (!HiltonUtility.isEmpty(shipToInv) && shipToInv.length() > 1) {
            shipToInv = shipToInv.substring(0, 1);
        }
        this.shipToInv = shipToInv;
    }

    public String getShipToInv() {
        return (java.lang.String)HiltonUtility.ckNull(this.shipToInv).trim();
    }

    public String getTimeZone() {
        return (java.lang.String) HiltonUtility.ckNull(this.timeZone).trim();
    }

    public void setTimeZone(String timeZone) {
        if (!HiltonUtility.isEmpty(timeZone) && timeZone.length() > 30) {
            timeZone = timeZone.substring(0, 30);
        }
        this.timeZone = timeZone;
    }

    public java.math.BigDecimal getIcHeaderHistory() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icHeaderHistory);
    }

    public void setIcHeaderHistory(java.math.BigDecimal icHeaderHistory) {
        this.icHeaderHistory = icHeaderHistory;
    }

    public String getItemLocation() {
		return (java.lang.String)HiltonUtility.ckNull(this.itemLocation).trim();
	}

	public void setItemLocation(String itemLocation) {
		if (!HiltonUtility.isEmpty(itemLocation) && itemLocation.length() > 15) {
			itemLocation = itemLocation.substring(0, 15);
		}
		this.itemLocation = itemLocation;
	}

    public java.math.BigDecimal getTempIc() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.tempIc);
    }

    public void setTempIc(java.math.BigDecimal tempIc) {
        this.tempIc = tempIc;
    }

    public StringBuffer getDisplayReceiptNumber() {
        String receipt =  this.getReceiptNumber();
        StringBuffer subject = new StringBuffer("");
        subject.append("Receipt ");
        subject.append(receipt);
        return subject;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icRecHeader", getIcRecHeader())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof ReceiptHeader) ) return false;
        ReceiptHeader castOther = (ReceiptHeader) other;
        return new EqualsBuilder()
            .append(this.getIcRecHeader(), castOther.getIcRecHeader())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcRecHeader())
            .toHashCode();
    }

	/**
	 * Returns the receiptLineList.
	 * @return List
	 */
	public List getReceiptLineList() {
		return receiptLineList;
	}
	
	public ReceiptLine getReceiptLineByLineNumber(int lineNumber) {
		ReceiptLine receiptLine = null;
		if(getReceiptLineList() != null){
			for(int rl = 0 ; rl < getReceiptLineList().size(); rl++){
				ReceiptLine rLine = (ReceiptLine)getReceiptLineList().get(rl);
				if(rLine.getReceiptLine().compareTo(new BigDecimal(lineNumber))==0){
					receiptLine = rLine;
				}
			}
		}
		return receiptLine;
	}

	/**
	 * Sets the receiptLineList.
	 * @param receiptLineList The receiptLineList to set
	 */
	public void setReceiptLineList (List receiptLineList) {
		this.receiptLineList = receiptLineList;
	}

	/**
	 * @return Returns the accountList.
	 */
	public List getAccountList()
	{
		return accountList;
	}

	/**
	 * @param accountList The accountList to set.
	 */
	public void setAccountList(List accountList)
	{
		this.accountList = accountList;
	}

	/**
     * @return Returns the docCommentList.
     */
    public List getDocCommentList()
    {
        return docCommentList;
    }

    /**
     * @param docCommentList The docCommentList to set.
     */
    public void setDocCommentList(List docCommentList)
    {
        this.docCommentList = docCommentList;
    }

	/**
     * @return Returns the docAttachmentList.
     */
    public List getDocAttachmentList()
    {
        return docAttachmentList;
    }

    /**
     * @param docAttachmentList The docAttachmentList to set.
     */
    public void setDocAttachmentList(List docAttachmentList)
    {
        this.docAttachmentList = docAttachmentList;
    }

    /**
     * @return Returns the shipToAddress.
     */
    public Address getShipToAddress()
    {
        return shipToAddress;
    }

    /**
     * @param shipToAddress The shipToAddress to set.
     */
    public void setShipToAddress(Address shipToAddress)
    {
        this.shipToAddress = shipToAddress;
    }

    /**
     * @return Returns the vendorAddress.
     */
    public Address getVendorAddress()
    {
        return vendorAddress;
    }

    /**
     * @param vendorAddress The vendorAddress to set.
     */
    public void setVendorAddress(Address vendorAddress)
    {
        this.vendorAddress = vendorAddress;
    }

    public java.lang.String getCorrosionEval() {
		return (java.lang.String)HiltonUtility.ckNull(this.corrosionEval).trim();
    }

    public void setCorrosionEval(java.lang.String corrosionEval) {
		if (!HiltonUtility.isEmpty(corrosionEval) && corrosionEval.length() > 3) {
			corrosionEval = corrosionEval.substring(0, 3);
		}
		this.corrosionEval = corrosionEval;
    }

    public String getInspectionRequired() {
		return (java.lang.String)HiltonUtility.ckNull(this.inspectionRequired).trim();
	}

	public void setInspectionRequired(String inspectionRequired) {
		if (!HiltonUtility.isEmpty(inspectionRequired) && inspectionRequired.length() > 1) {
			inspectionRequired = inspectionRequired.substring(0, 1);
		}
		this.inspectionRequired = inspectionRequired;
	}
}
