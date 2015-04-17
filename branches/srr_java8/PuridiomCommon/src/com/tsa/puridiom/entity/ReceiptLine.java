package com.tsa.puridiom.entity;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.tsa.puridiom.common.utility.HiltonUtility;


/** @author Hibernate CodeGenerator */
public class ReceiptLine implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icRecLine;

    /** persistent field */
    private java.math.BigDecimal icRecHeader;

    /** persistent field */
    private java.math.BigDecimal receiptLine;

    /** nullable persistent field */
    private java.math.BigDecimal icPoLine;

    /** nullable persistent field */
    private java.math.BigDecimal icReqLine;

    /** nullable persistent field */
    private java.util.Date receiptDate;

    /** nullable persistent field */
    private String receiptNumber;

    /** nullable persistent field */
    private String packingSlip;

    /** nullable persistent field */
    private String lotNumber;

    /** nullable persistent field */
    private String receivedBy;

    /** nullable persistent field */
    private java.math.BigDecimal qtyReceived;

    /** nullable persistent field */
    private java.math.BigDecimal qtyReturned;
    
    /** nullable persistent field */
    private java.math.BigDecimal qtyInspected;
    
    /** nullable persistent field */
    private java.math.BigDecimal qtyMarked;
    
    /** nullable persistent field */
    private java.math.BigDecimal qtyDelivered;

    /** nullable persistent field */
    private String inspectionCode;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private java.math.BigDecimal icPoHeader;

    /** nullable persistent field */
    private java.math.BigDecimal qtyRejected;

    /** nullable persistent field */
    private java.math.BigDecimal convFactor;

    /** nullable persistent field */
    private String udf1Code;

    /** nullable persistent field */
    private String udf2Code;

    /** nullable persistent field */
    private String udf3Code;

    /** nullable persistent field */
    private String udf4Code;

    /** nullable persistent field */
    private String udf5Code;

    /** nullable persistent field */
    private String carrierCode;

    /** nullable persistent field */
    private String linComflag;

    /** nullable persistent field */
    private String receiptType;

    /** nullable persistent field */
    private String apBatchid;

    /** nullable persistent field */
    private String receiptNotes;

    /** nullable persistent field */
    private java.math.BigDecimal releaseNumber;

    /** nullable persistent field */
    private String rejectionCode;

    /** nullable persistent field */
    private String dispositionCode;

    /** nullable persistent field */
    private String timeZone;

    /** nullable persistent field */
    private java.util.List receiptLineAdjustmentList;

    /** nullable persistent field */
    private java.math.BigDecimal qtyAccepted;

    /** nullable persistent field */
    private java.math.BigDecimal icLineHistory;

    /** nullable persistent field */
    private String itemLocation;

    /** nullable persistent field */
    private java.math.BigDecimal duomQtyReceived;

    /** nullable persistent field */
    private String umCode;

    /** nullable persistent field */
    private String duomUmCode;

    /** nullable persistent field */
    private java.math.BigDecimal qtyStep0Received;

    /** nullable persistent field */
    private java.math.BigDecimal qtyStep0Rejected;

    /** nullable persistent field */
    private java.math.BigDecimal qtyStep0Accepted;

    /** nullable persistent field */
    private java.math.BigDecimal qtyStep1Received;

    /** nullable persistent field */
    private java.math.BigDecimal qtyStep1Rejected;

    /** nullable persistent field */
    private java.math.BigDecimal qtyStep1Accepted;

    /** nullable persistent field */
    private java.math.BigDecimal qtyStep2Received;

    /** nullable persistent field */
    private java.math.BigDecimal qtyStep2Rejected;

    /** nullable persistent field */
    private java.math.BigDecimal qtyStep2Accepted;

    /** nullable persistent field */
    private java.math.BigDecimal qtyStep3Received;

    /** nullable persistent field */
    private java.math.BigDecimal qtyStep3Rejected;

    /** nullable persistent field */
    private java.math.BigDecimal qtyStep3Accepted;

    /** nullable persistent field */
    private String inspectorAssigned;

    /** nullable persistent field */
    private String engineerAssigned;

    /** nullable persistent field */
    private String inspectionRequired;

    /** nullable persistent field */
    private String markTagRequired;

    /** nullable persistent field */
    private String inspectionStatus;

    /** nullable persistent field */
    private String inventoryStatus;

    /** nullable persistent field */
    private String inspLocation;

    /** nullable persistent field */
    private String inspArea;

    /** nullable persistent field */
    private String inspStorage;

    /** nullable persistent field */
    private String inventoryFlag;

    /** nullable persistent field */
    private java.util.Date inspectionAssignedDate;
    /** nullable persistent field */
    private java.util.Date inspectionClosedDate;
    /** nullable persistent field */
    private java.util.Date shelfLifeDate;


    private PoLine poLine;

    private PoHeader poHeader ;

    private RequisitionLine requisitionLine;

    private RequisitionLine msrLine;
    
    private RequisitionHeader msrHeader;

	private RequisitionHeader requisitionHeader ;

    private InvBinLocation  invBinLocation ;

    private InvItem invItem;

    private InvLocation invLocation;

    private java.util.List accountList;

    private java.util.List  invPropertyList ;

    private String chemicalItemNumber;

    private String edws;

    /** full constructor */
    public ReceiptLine(java.math.BigDecimal icRecLine, java.math.BigDecimal icRecHeader, java.math.BigDecimal receiptLine, java.math.BigDecimal icPoLine, java.math.BigDecimal icReqLine, java.util.Date receiptDate, java.lang.String receiptNumber, java.lang.String packingSlip, java.lang.String lotNumber, java.lang.String receivedBy, java.math.BigDecimal qtyReceived, java.math.BigDecimal qtyReturned, java.math.BigDecimal qtyInspected, java.math.BigDecimal qtyMarked, java.math.BigDecimal qtyDelivered, java.lang.String inspectionCode, java.lang.String status, java.math.BigDecimal icPoHeader, java.math.BigDecimal qtyRejected, java.math.BigDecimal convFactor, java.lang.String udf1Code, java.lang.String udf2Code, java.lang.String udf3Code, java.lang.String udf4Code, java.lang.String udf5Code, java.lang.String carrierCode, java.lang.String linComflag, java.lang.String receiptType, java.lang.String apBatchid, java.lang.String receiptNotes, java.math.BigDecimal releaseNumber, java.lang.String rejectionCode, java.lang.String dispositionCode, java.math.BigDecimal qtyAccepted, java.lang.String timeZone, java.math.BigDecimal icLineHistory, java.lang.String itemLocation, java.lang.String inspectionStatus,
    		String chemicalItemNumber, java.lang.String edws) {
        this.icRecLine = icRecLine;
        this.icRecHeader = icRecHeader;
        this.receiptLine = receiptLine;
        this.icPoLine = icPoLine;
        this.icReqLine = icReqLine;
        this.receiptDate = receiptDate;
        this.receiptNumber = receiptNumber;
        this.packingSlip = packingSlip;
        this.lotNumber = lotNumber;
        this.receivedBy = receivedBy;
        this.qtyReceived = qtyReceived;
        this.qtyReturned = qtyReturned;
        this.qtyInspected = qtyInspected;
        this.qtyMarked = qtyMarked;
        this.qtyDelivered = qtyDelivered;
        this.inspectionCode = inspectionCode;
        this.status = status;
        this.icPoHeader = icPoHeader;
        this.qtyRejected = qtyRejected;
        this.convFactor = convFactor;
        this.udf1Code = udf1Code;
        this.udf2Code = udf2Code;
        this.udf3Code = udf3Code;
        this.udf4Code = udf4Code;
        this.udf5Code = udf5Code;
        this.carrierCode = carrierCode;
        this.linComflag = linComflag;
        this.receiptType = receiptType;
        this.apBatchid = apBatchid;
        this.receiptNotes = receiptNotes;
        this.releaseNumber = releaseNumber;
        this.rejectionCode = rejectionCode;
        this.dispositionCode = dispositionCode;
        this.qtyAccepted = qtyAccepted;
        this.timeZone = timeZone;
        this.icLineHistory = icLineHistory;
        this.itemLocation = itemLocation;
        this.inspectionStatus =  inspectionStatus ;
        this.chemicalItemNumber = chemicalItemNumber;
        this.edws = edws;
    }

    /** default constructor */
    public ReceiptLine() {
    }

    /** minimal constructor */
    public ReceiptLine(java.math.BigDecimal icRecLine, java.math.BigDecimal icRecHeader, java.math.BigDecimal receiptLine) {
        this.icRecLine = icRecLine;
        this.icRecHeader = icRecHeader;
        this.receiptLine = receiptLine;
    }

    public java.math.BigDecimal getIcRecLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icRecLine);
    }

    public void setIcRecLine(java.math.BigDecimal icRecLine) {
        this.icRecLine = icRecLine;
    }

    public java.math.BigDecimal getIcRecHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icRecHeader);
    }

    public void setIcRecHeader(java.math.BigDecimal icRecHeader) {
        this.icRecHeader = icRecHeader;
    }

    public java.math.BigDecimal getReceiptLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.receiptLine);
    }

    public void setReceiptLine(java.math.BigDecimal receiptLine) {
        this.receiptLine = receiptLine;
    }

    public java.math.BigDecimal getIcPoLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icPoLine);
    }

    public void setIcPoLine(java.math.BigDecimal icPoLine) {
        this.icPoLine = icPoLine;
    }

    public java.math.BigDecimal getIcReqLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icReqLine);
    }

    public void setIcReqLine(java.math.BigDecimal icReqLine) {
        this.icReqLine = icReqLine;
    }

    public java.util.Date getReceiptDate() {
		return this.receiptDate;
//		return HiltonUtility.ckNull(this.receiptDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.receiptDate);
    }

    public void setReceiptDate(java.util.Date receiptDate) {
        this.receiptDate = receiptDate;
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

    public java.lang.String getPackingSlip() {
		return (java.lang.String)HiltonUtility.ckNull(this.packingSlip).trim();
    }

    public void setPackingSlip(java.lang.String packingSlip) {
		if (!HiltonUtility.isEmpty(packingSlip) && packingSlip.length() > 15) {
			packingSlip = packingSlip.substring(0, 15);
		}
		this.packingSlip = packingSlip;
    }

    public java.lang.String getLotNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.lotNumber).trim();
    }

    public void setLotNumber(java.lang.String lotNumber) {
		if (!HiltonUtility.isEmpty(lotNumber) && lotNumber.length() > 15) {
			lotNumber = lotNumber.substring(0, 15);
		}
		this.lotNumber = lotNumber;
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

    public java.math.BigDecimal getQtyReceived() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.qtyReceived);
    }

    public void setQtyReceived(java.math.BigDecimal qtyReceived) {
        this.qtyReceived = qtyReceived;
    }

    public java.math.BigDecimal getQtyReturned() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.qtyReturned);
    }

    public void setQtyReturned(java.math.BigDecimal qtyReturned) {
        this.qtyReturned = qtyReturned;
    }
    
    public java.math.BigDecimal getQtyInspected() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.qtyInspected);
    }

    public void setQtyInspected(java.math.BigDecimal qtyInspected) {
        this.qtyInspected = qtyInspected;
    }
    
    public java.math.BigDecimal getQtyMarked() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.qtyMarked);
    }

    public void setQtyMarked(java.math.BigDecimal qtyMarked) {
        this.qtyMarked = qtyMarked;
    }
    
    public java.math.BigDecimal getQtyDelivered() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.qtyDelivered);
    }

    public void setQtyDelivered(java.math.BigDecimal qtyDelivered) {
        this.qtyDelivered = qtyDelivered;
    }

    public java.lang.String getInspectionCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.inspectionCode).trim();
    }

    public void setInspectionCode(java.lang.String inspectionCode) {
		if (!HiltonUtility.isEmpty(inspectionCode) && inspectionCode.length() > 15) {
			inspectionCode = inspectionCode.substring(0, 15);
		}
		this.inspectionCode = inspectionCode;
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

    public java.math.BigDecimal getIcPoHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icPoHeader);
    }

    public void setIcPoHeader(java.math.BigDecimal icPoHeader) {
        this.icPoHeader = icPoHeader;
    }

    public java.math.BigDecimal getQtyRejected() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.qtyRejected);
    }

    public void setQtyRejected(java.math.BigDecimal qtyRejected) {
        this.qtyRejected = qtyRejected;
    }

    public java.math.BigDecimal getConvFactor() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.convFactor);
    }

    public void setConvFactor(java.math.BigDecimal convFactor) {
        this.convFactor = convFactor;
    }

    public java.lang.String getUdf1Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf1Code).trim();
    }

    public void setUdf1Code(java.lang.String udf1Code) {
		if (!HiltonUtility.isEmpty(udf1Code) && udf1Code.length() > 30) {
			udf1Code = udf1Code.substring(0, 30);
		}
		this.udf1Code = udf1Code;
    }

    public java.lang.String getUdf2Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf2Code).trim();
    }

    public void setUdf2Code(java.lang.String udf2Code) {
		if (!HiltonUtility.isEmpty(udf2Code) && udf2Code.length() > 15) {
			udf2Code = udf2Code.substring(0, 15);
		}
		this.udf2Code = udf2Code;
    }

    public java.lang.String getUdf3Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf3Code).trim();
    }

    public void setUdf3Code(java.lang.String udf3Code) {
		if (!HiltonUtility.isEmpty(udf3Code) && udf3Code.length() > 15) {
			udf3Code = udf3Code.substring(0, 15);
		}
		this.udf3Code = udf3Code;
    }

    public java.lang.String getUdf4Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf4Code).trim();
    }

    public void setUdf4Code(java.lang.String udf4Code) {
		if (!HiltonUtility.isEmpty(udf4Code) && udf4Code.length() > 15) {
			udf4Code = udf4Code.substring(0, 15);
		}
		this.udf4Code = udf4Code;
    }

    public java.lang.String getUdf5Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf5Code).trim();
    }

    public void setUdf5Code(java.lang.String udf5Code) {
		if (!HiltonUtility.isEmpty(udf5Code) && udf5Code.length() > 15) {
			udf5Code = udf5Code.substring(0, 15);
		}
		this.udf5Code = udf5Code;
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

    public java.lang.String getLinComflag() {
		return (java.lang.String)HiltonUtility.ckNull(this.linComflag).trim();
    }

    public void setLinComflag(java.lang.String linComflag) {
		if (!HiltonUtility.isEmpty(linComflag) && linComflag.length() > 1) {
			linComflag = linComflag.substring(0, 1);
		}
		this.linComflag = linComflag;
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

    public java.lang.String getApBatchid() {
		return (java.lang.String)HiltonUtility.ckNull(this.apBatchid).trim();
    }

    public void setApBatchid(java.lang.String apBatchid) {
		if (!HiltonUtility.isEmpty(apBatchid) && apBatchid.length() > 6) {
			apBatchid = apBatchid.substring(0, 6);
		}
		this.apBatchid = apBatchid;
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

    public String getRejectionCode() {
        return (java.lang.String)HiltonUtility.ckNull(this.rejectionCode).trim();
    }

    public void setRejectionCode(String rejectionCode) {
		if (!HiltonUtility.isEmpty(rejectionCode) && rejectionCode.length() > 15) {
		    rejectionCode = rejectionCode.substring(0, 15);
		}
        this.rejectionCode = rejectionCode;
    }

	public String getDispositionCode() {
	    return (java.lang.String)HiltonUtility.ckNull(this.dispositionCode).trim();
    }

	public void setDispositionCode(String dispositionCode) {
		if (!HiltonUtility.isEmpty(dispositionCode) && dispositionCode.length() > 15) {
		    dispositionCode = dispositionCode.substring(0, 15);
		}
        this.dispositionCode = dispositionCode;
    }

	public java.math.BigDecimal getQtyAccepted() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.qtyAccepted);
    }

    public void setQtyAccepted(java.math.BigDecimal qtyAccepted) {
        this.qtyAccepted = qtyAccepted;
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

    public java.math.BigDecimal getIcLineHistory() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icLineHistory);
    }

    public void setIcLineHistory(java.math.BigDecimal icLineHistory) {
        this.icLineHistory = icLineHistory;
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

    public java.math.BigDecimal getDuomQtyReceived() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.duomQtyReceived);
    }

    public void setDuomQtyReceived(java.math.BigDecimal duomQtyReceived) {
        this.duomQtyReceived = duomQtyReceived;
    }

    public java.lang.String getDuomUmCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.duomUmCode).trim();
    }

    public void setDuomUmCode(java.lang.String duomUmCode) {
		if (!HiltonUtility.isEmpty(duomUmCode) && duomUmCode.length() > 15) {
			duomUmCode = duomUmCode.substring(0, 15);
		}
		this.duomUmCode = duomUmCode;
    }

    public java.lang.String getUmCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.umCode).trim();
    }

    public void setUmCode(java.lang.String umCode) {
		if (!HiltonUtility.isEmpty(umCode) && umCode.length() > 15) {
			umCode = umCode.substring(0, 15);
		}
		this.umCode = umCode;
    }

    public java.lang.String getInspectionStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.inspectionStatus).trim();
    }

    public void setInspectionStatus(java.lang.String inspectionStatus) {
		if (!HiltonUtility.isEmpty(inspectionStatus) && inspectionStatus.length() > 15) {
			inspectionStatus = inspectionStatus.substring(0, 15);
		}
		this.inspectionStatus = inspectionStatus;
    }


    public java.util.List getReceiptLineAdjustmentList() {
    	return this.receiptLineAdjustmentList;
    }

    public void setReceiptLineAdjustmentList(List receiptLineAdjustmentList) {
    	this.receiptLineAdjustmentList = receiptLineAdjustmentList;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icRecLine", getIcRecLine())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof ReceiptLine) ) return false;
        ReceiptLine castOther = (ReceiptLine) other;
        return new EqualsBuilder()
            .append(this.getIcRecLine(), castOther.getIcRecLine())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcRecLine())
            .toHashCode();
    }

	public PoLine getPoLine() {
		return poLine;
	}

	public void setPoLine(PoLine poLine) {
		this.poLine = poLine;
	}

	public InvBinLocation getInvBinLocation() {
		return invBinLocation;
	}

	public void setInvBinLocation(InvBinLocation invBinLocation) {
		this.invBinLocation = invBinLocation;
	}

	public InvItem getInvItem() {
		return invItem;
	}

	public void setInvItem(InvItem invItem) {
		this.invItem = invItem;
	}

	public InvLocation getInvLocation() {
		return invLocation;
	}

	public void setInvLocation(InvLocation invLocation) {
		this.invLocation = invLocation;
	}

	public java.util.List getAccountList() {
		return accountList;
	}

	public void setAccountList(java.util.List accountList) {
		this.accountList = accountList;
	}

	public java.util.List getInvPropertyList() {
		return invPropertyList;
	}

	public void setInvPropertyList(java.util.List invPropertyList) {
		this.invPropertyList = invPropertyList;
	}

	public PoHeader getPoHeader() {
		return poHeader;
	}

	public void setPoHeader(PoHeader poHeader) {
		this.poHeader = poHeader;
	}

    public RequisitionLine getRequisitionLine() {
		return requisitionLine;
	}

	public void setRequisitionLine(RequisitionLine requisitionLine) {
		this.requisitionLine = requisitionLine;
	}

	public RequisitionHeader getRequisitionHeader() {
		return requisitionHeader;
	}

	public void setRequisitionHeader(RequisitionHeader requisitionHeader) {
		this.requisitionHeader = requisitionHeader;
	}
	
	public RequisitionLine getMsrLine() {
		return msrLine;
	}

	public void setMsrLine(RequisitionLine msrLine) {
		this.msrLine = msrLine;
	}
	
	public RequisitionHeader getMsrHeader() {
		return msrHeader;
	}

	public void setMsrHeader(RequisitionHeader msrHeader) {
		this.msrHeader = msrHeader;
	}

	public java.math.BigDecimal getQtyStep0Received() {
		return qtyStep0Received;
	}

	public void setQtyStep0Received(java.math.BigDecimal qtyStep0Received) {
		this.qtyStep0Received = qtyStep0Received;
	}

	public java.math.BigDecimal getQtyStep0Rejected() {
		return qtyStep0Rejected;
	}

	public void setQtyStep0Rejected(java.math.BigDecimal qtyStep0Rejected) {
		this.qtyStep0Rejected = qtyStep0Rejected;
	}

	public java.math.BigDecimal getQtyStep0Accepted() {
		return qtyStep0Accepted;
	}

	public void setQtyStep0Accepted(java.math.BigDecimal qtyStep0Accepted) {
		this.qtyStep0Accepted = qtyStep0Accepted;
	}

	public java.math.BigDecimal getQtyStep1Received() {
		return qtyStep1Received;
	}

	public void setQtyStep1Received(java.math.BigDecimal qtyStep1Received) {
		this.qtyStep1Received = qtyStep1Received;
	}

	public java.math.BigDecimal getQtyStep1Rejected() {
		return qtyStep1Rejected;
	}

	public void setQtyStep1Rejected(java.math.BigDecimal qtyStep1Rejected) {
		this.qtyStep1Rejected = qtyStep1Rejected;
	}

	public java.math.BigDecimal getQtyStep1Accepted() {
		return qtyStep1Accepted;
	}

	public void setQtyStep1Accepted(java.math.BigDecimal qtyStep1Accepted) {
		this.qtyStep1Accepted = qtyStep1Accepted;
	}

	public java.math.BigDecimal getQtyStep2Received() {
		return qtyStep2Received;
	}

	public void setQtyStep2Received(java.math.BigDecimal qtyStep2Received) {
		this.qtyStep2Received = qtyStep2Received;
	}

	public java.math.BigDecimal getQtyStep2Rejected() {
		return qtyStep2Rejected;
	}

	public void setQtyStep2Rejected(java.math.BigDecimal qtyStep2Rejected) {
		this.qtyStep2Rejected = qtyStep2Rejected;
	}

	public java.math.BigDecimal getQtyStep2Accepted() {
		return qtyStep2Accepted;
	}

	public void setQtyStep2Accepted(java.math.BigDecimal qtyStep2Accepted) {
		this.qtyStep2Accepted = qtyStep2Accepted;
	}

	public java.math.BigDecimal getQtyStep3Received() {
		return qtyStep3Received;
	}

	public void setQtyStep3Received(java.math.BigDecimal qtyStep3Received) {
		this.qtyStep3Received = qtyStep3Received;
	}

	public java.math.BigDecimal getQtyStep3Rejected() {
		return qtyStep3Rejected;
	}

	public void setQtyStep3Rejected(java.math.BigDecimal qtyStep3Rejected) {
		this.qtyStep3Rejected = qtyStep3Rejected;
	}

	public java.math.BigDecimal getQtyStep3Accepted() {
		return qtyStep3Accepted;
	}

	public void setQtyStep3Accepted(java.math.BigDecimal qtyStep3Accepted) {
		this.qtyStep3Accepted = qtyStep3Accepted;
	}

	public String getInspectorAssigned() {
		return (java.lang.String)HiltonUtility.ckNull(this.inspectorAssigned).trim();
	}

	public void setInspectorAssigned(String inspectorAssigned) {
		if (!HiltonUtility.isEmpty(inspectorAssigned) && inspectorAssigned.length() > 15) {
			inspectorAssigned = inspectorAssigned.substring(0, 15);
		}
		this.inspectorAssigned = inspectorAssigned;
	}

	public String getEngineerAssigned() {
		return (java.lang.String)HiltonUtility.ckNull(this.engineerAssigned).trim();
	}

	public void setEngineerAssigned(String engineerAssigned) {
		if (!HiltonUtility.isEmpty(engineerAssigned) && engineerAssigned.length() > 15) {
			engineerAssigned = engineerAssigned.substring(0, 15);
		}
		this.engineerAssigned = engineerAssigned;
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

	public String getMarkTagRequired() {
		return (java.lang.String)HiltonUtility.ckNull(this.markTagRequired).trim();
	}

	public void setMarkTagRequired(String markTagRequired) {
		if (!HiltonUtility.isEmpty(markTagRequired) && markTagRequired.length() > 1) {
			markTagRequired = markTagRequired.substring(0, 1);
		}
		this.markTagRequired = markTagRequired;
	}

	public java.lang.String getInventoryStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.inventoryStatus).trim();
    }

    public void setInventoryStatus(java.lang.String inventoryStatus) {
		if (!HiltonUtility.isEmpty(inventoryStatus) && inventoryStatus.length() > 4) {
			inventoryStatus = inventoryStatus.substring(0, 4);
		}
		this.inventoryStatus = inventoryStatus;
    }

	public String getInspLocation() {
		return inspLocation;
	}

	public void setInspLocation(String inspLocation) {
		this.inspLocation = inspLocation;
	}

	public String getInspArea() {
		return inspArea;
	}

	public void setInspArea(String inspArea) {
		this.inspArea = inspArea;
	}

	public String getInspStorage() {
		return inspStorage;
	}

	public void setInspStorage(String inspStorage) {
		this.inspStorage = inspStorage;
	}

	public String getInventoryFlag() {
		return (java.lang.String)HiltonUtility.ckNull(this.inventoryFlag).trim();
	}

	public void setInventoryFlag(String inventoryFlag) {
		if (!HiltonUtility.isEmpty(inventoryFlag) && inventoryFlag.length() > 1) {
			inventoryFlag = inventoryFlag.substring(0, 1);
		}
		this.inventoryFlag = inventoryFlag;
	}

    public String getChemicalItemNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.chemicalItemNumber).trim();
	}

	public void setChemicalItemNumber(String chemicalItemNumber) {
		if (!HiltonUtility.isEmpty(chemicalItemNumber) && chemicalItemNumber.length() > 30) {
			chemicalItemNumber = chemicalItemNumber.substring(0, 30);
		}
		this.chemicalItemNumber = chemicalItemNumber;
	}

	public java.util.Date getInspectionAssignedDate() {
		return inspectionAssignedDate;
	}

	public void setInspectionAssignedDate(java.util.Date inspectionAssignedDate) {
		this.inspectionAssignedDate = inspectionAssignedDate;
	}

	public java.util.Date getInspectionClosedDate() {
		return inspectionClosedDate;
	}

	public void setInspectionClosedDate(java.util.Date inspectionClosedDate) {
		this.inspectionClosedDate = inspectionClosedDate;
	}

	public java.util.Date getShelfLifeDate() {
		return this.shelfLifeDate;
	}

	public void setShelfLifeDate(java.util.Date shelfLifeDate) {
		this.shelfLifeDate = shelfLifeDate;
	}

	public java.lang.String getEdws() {
		return (java.lang.String)HiltonUtility.ckNull(this.edws).trim();
    }

    public void setEdws(java.lang.String edws) {
		if (!HiltonUtility.isEmpty(edws) && edws.length() > 30) {
			edws = edws.substring(0, 30);
		}
		this.edws = edws;
    }

}
