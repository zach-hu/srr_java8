package com.tsa.puridiom.entity;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.tsa.puridiom.common.utility.HiltonUtility;

/** @author Hibernate CodeGenerator */
public class InspectionHeader implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.InspectionHeaderPK comp_id;

    /** nullable persistent field */
    private String inspectType;

    /** nullable persistent field */
    private String inspectorId;

    /** nullable persistent field */
    private String engineerId;

    /** nullable persistent field */
    private String remoteInspId;

    /** nullable persistent field */
    private String purchaseSpecs;

    /** nullable persistent field */
    private String area;

    /** nullable persistent field */
    private String storage;

    /** nullable persistent field */
    private String location;

    /** nullable persistent field */
    private String standardCode;

    /** nullable persistent field */
    private String udf01;

    /** nullable persistent field */
    private String udf02;

    /** nullable persistent field */
    private String udf03;

    /** nullable persistent field */
    private String udf04;

    /** nullable persistent field */
    private String udf05;

    /** nullable persistent field */
    private String inspType;

    /** nullable persistent field */
    private java.util.Date apprDt;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private java.util.Date lastChange;

    /** nullable persistent field */
    private String lastChangeBy;

    /** nullable persistent field */
    private java.util.Date assignedDate ;

    /** nullable persistent field */
    private String cgdNo;

    /** nullable persistent field */
    private java.math.BigDecimal cgdRev;

    private java.math.BigDecimal icRecLine ;

    private String poNumber ;
    private java.math.BigDecimal icPoLine ;

    /** nullable persistent field */
    private java.util.Date shelfLifeDate;

    /** nullable persistent field */
    private String inspectionNumber;

    /** nullable persistent field */
    private String internalComments;

    private java.math.BigDecimal qtySignoff ;
    private java.math.BigDecimal qtyAccepted ;
    private java.math.BigDecimal qtyRejected ;

    private List inspectionLineList;

    private InspectionStd inspectionStd;

    private List inspectionMfrList;

    private List inspectionDiscrepList;
    
    private List inspectionDisposList;

    private List inspectionHistoryList;

    /** full constructor */
    public InspectionHeader(com.tsa.puridiom.entity.InspectionHeaderPK comp_id, java.lang.String inspectType, java.lang.String inspectorId, java.lang.String engineerId, java.lang.String remoteInspId, java.lang.String purchaseSpecs, java.lang.String area, java.lang.String storage, java.lang.String location, java.lang.String standardCode, java.lang.String udf01, java.lang.String udf02, java.lang.String udf03, java.lang.String udf04, java.lang.String udf05, java.lang.String inspType, java.util.Date apprDt, java.util.Date dateEntered, java.lang.String status, java.lang.String owner, java.util.Date lastChange, java.lang.String lastChangeBy, java.util.Date assignedDate, java.lang.String cgdNo, java.math.BigDecimal cgdRev, java.lang.String internalComments) {
        this.comp_id = comp_id;
        this.inspectType = inspectType;
        this.inspectorId = inspectorId;
        this.engineerId = engineerId;
        this.remoteInspId = remoteInspId;
        this.purchaseSpecs = purchaseSpecs;
        this.area = area;
        this.storage = storage;
        this.location = location;
        this.standardCode = standardCode;
        this.udf01 = udf01;
        this.udf02 = udf02;
        this.udf03 = udf03;
        this.udf04 = udf04;
        this.udf05 = udf05;
        this.inspType = inspType;
        this.apprDt = apprDt;
        this.dateEntered = dateEntered;
        this.status = status;
        this.owner = owner;
        this.lastChange = lastChange;
        this.lastChangeBy = lastChangeBy;
        this.assignedDate = assignedDate ;
        this.cgdNo = cgdNo;
        this.cgdRev = cgdRev;
        this.internalComments = internalComments;
    }

    /** default constructor */
    public InspectionHeader() {
    }

    /** minimal constructor */
    public InspectionHeader(com.tsa.puridiom.entity.InspectionHeaderPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.InspectionHeaderPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.InspectionHeaderPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.lang.String getInspectType() {
        return this.inspectType;
    }

    public void setInspectType(java.lang.String inspectType) {
        this.inspectType = inspectType;
    }

    public java.lang.String getInspectorId() {
        return this.inspectorId;
    }

    public void setInspectorId(java.lang.String inspectorId) {
        this.inspectorId = inspectorId;
    }

    public java.lang.String getEngineerId() {
        return this.engineerId;
    }

    public void setEngineerId(java.lang.String engineerId) {
        this.engineerId = engineerId;
    }

    public java.lang.String getRemoteInspId() {
        return this.remoteInspId;
    }

    public void setRemoteInspId(java.lang.String remoteInspId) {
        this.remoteInspId = remoteInspId;
    }

    public java.lang.String getPurchaseSpecs() {
        return this.purchaseSpecs;
    }

    public void setPurchaseSpecs(java.lang.String purchaseSpecs) {
        this.purchaseSpecs = purchaseSpecs;
    }

    public java.lang.String getArea() {
        return this.area;
    }

    public void setArea(java.lang.String area) {
        this.area = area;
    }

    public java.lang.String getStorage() {
        return this.storage;
    }

    public void setStorage(java.lang.String storage) {
        this.storage = storage;
    }

    public java.lang.String getLocation() {
        return this.location;
    }

    public void setLocation(java.lang.String location) {
        this.location = location;
    }

    public java.lang.String getStandardCode() {
        return this.standardCode;
    }

    public void setStandardCode(java.lang.String standardCode) {
        this.standardCode = standardCode;
    }

    public java.lang.String getUdf01() {
        return this.udf01;
    }

    public void setUdf01(java.lang.String udf01) {
        this.udf01 = udf01;
    }

    public java.lang.String getUdf02() {
        return this.udf02;
    }

    public void setUdf02(java.lang.String udf02) {
        this.udf02 = udf02;
    }

    public java.lang.String getUdf03() {
        return this.udf03;
    }

    public void setUdf03(java.lang.String udf03) {
        this.udf03 = udf03;
    }

    public java.lang.String getUdf04() {
        return this.udf04;
    }

    public void setUdf04(java.lang.String udf04) {
        this.udf04 = udf04;
    }

    public java.lang.String getUdf05() {
        return this.udf05;
    }

    public void setUdf05(java.lang.String udf05) {
        this.udf05 = udf05;
    }

    public java.lang.String getInspType() {
        return this.inspType;
    }

    public void setInspType(java.lang.String inspType) {
        this.inspType = inspType;
    }

    public java.util.Date getApprDt() {
        return this.apprDt;
    }

    public void setApprDt(java.util.Date apprDt) {
        this.apprDt = apprDt;
    }

    public java.util.Date getDateEntered() {
        return this.dateEntered;
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.lang.String getStatus() {
        return this.status;
    }

    public void setStatus(java.lang.String status) {
        this.status = status;
    }

    public java.lang.String getOwner() {
        return this.owner;
    }

    public void setOwner(java.lang.String owner) {
        this.owner = owner;
    }

    public java.util.Date getLastChange() {
        return this.lastChange;
    }

    public void setLastChange(java.util.Date lastChange) {
        this.lastChange = lastChange;
    }

    public java.lang.String getLastChangeBy() {
        return this.lastChangeBy;
    }

    public void setLastChangeBy(java.lang.String lastChangeBy) {
        this.lastChangeBy = lastChangeBy;
    }

    public java.util.Date getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(java.util.Date assignedDate) {
		this.assignedDate = assignedDate;
	}

	public java.math.BigDecimal getIcRecLine() {
		return icRecLine;
	}

	public void setIcRecLine(java.math.BigDecimal icRecLine) {
		this.icRecLine = icRecLine;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public java.math.BigDecimal getIcPoLine() {
		return icPoLine;
	}

	public void setIcPoLine(java.math.BigDecimal icPoLine) {
		this.icPoLine = icPoLine;
	}

	public java.util.Date getShelfLifeDate() {
		return shelfLifeDate;
	}

	public void setShelfLifeDate(java.util.Date shelfLifeDate) {
		this.shelfLifeDate = shelfLifeDate;
	}

	public List getInspectionLineList() {
		return inspectionLineList;
	}

	public void setInspectionLineList(List inspectionLineList) {
		this.inspectionLineList = inspectionLineList;
	}

	public InspectionStd getInspectionStd() {
		return inspectionStd;
	}

	public void setInspectionStd(InspectionStd inspectionStd) {
		this.inspectionStd = inspectionStd;
	}

	public List getInspectionMfrList() {
		return inspectionMfrList;
	}

	public void setInspectionMfrList(List inspectionMfrList) {
		this.inspectionMfrList = inspectionMfrList;
	}

	public List getInspectionDiscrepList() {
		return inspectionDiscrepList;
	}

	public void setInspectionDiscrepList(List inspectionDiscrepList) {
		this.inspectionDiscrepList = inspectionDiscrepList;
	}
	
	public void setInspectionDisposList(List inspectionDisposList) {
		this.inspectionDisposList = inspectionDisposList;
	}

	public List getInspectionDisposList() {
		return inspectionDisposList;
	}

	public List getInspectionHistoryList() {
		return inspectionHistoryList;
	}

	public void setInspectionHistoryList(List inspectionHistoryList) {
		this.inspectionHistoryList = inspectionHistoryList;
	}

	public java.lang.String getCgdNo() {
		return (java.lang.String)HiltonUtility.ckNull(this.cgdNo).trim();
    }

    public void setCgdNo(java.lang.String cgdNo) {
		if (!HiltonUtility.isEmpty(cgdNo) && cgdNo.length() > 20) {
			cgdNo = cgdNo.substring(0, 20);
		}
		this.cgdNo = cgdNo;
    }

	public java.math.BigDecimal getCgdRev() {
		return cgdRev;
	}

	public void setCgdRev(java.math.BigDecimal cgdRev) {
		this.cgdRev = cgdRev;
	}

	public String getInspectionNumber() {
		return HiltonUtility.ckNull(inspectionNumber);
	}

	public java.math.BigDecimal getQtySignoff() {
		return qtySignoff;
	}

	public void setQtySignoff(java.math.BigDecimal qtySignoff) {
		this.qtySignoff = qtySignoff;
	}

	public java.math.BigDecimal getQtyAccepted() {
		return qtyAccepted;
	}

	public void setQtyAccepted(java.math.BigDecimal qtyAccepted) {
		this.qtyAccepted = qtyAccepted;
	}

	public java.math.BigDecimal getQtyRejected() {
		return qtyRejected;
	}

	public void setQtyRejected(java.math.BigDecimal qtyRejected) {
		this.qtyRejected = qtyRejected;
	}


	public void setInspectionNumber(String inspectionNumber) {
		if(!HiltonUtility.isEmpty(inspectionNumber) && inspectionNumber.length() > 30){
			inspectionNumber = inspectionNumber.substring(0,30);
		}
		this.inspectionNumber = inspectionNumber;
	}

	public java.lang.String getInternalComments() {
		return (java.lang.String)HiltonUtility.ckNull(this.internalComments).trim();
    }

    public void setInternalComments(java.lang.String internalComments) {
		if (!HiltonUtility.isEmpty(internalComments) && internalComments.length() > 255) {
			internalComments = internalComments.substring(0, 255);
		}
		this.internalComments = internalComments;
    }

	public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InspectionHeader) ) return false;
        InspectionHeader castOther = (InspectionHeader) other;
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
