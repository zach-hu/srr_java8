package com.tsa.puridiom.entity.sungard;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Vendor implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.sungard.VendorPK comp_id;
    
    /** nullable persistent field */
    private java.math.BigDecimal internalVendorId;

    /** nullable persistent field */
    private String statusInd;

    /** nullable persistent field */
    private String vendorType;

    /** nullable persistent field */
    private String tin;

    /** nullable persistent field */
    private String tinType;

    /** nullable persistent field */
    private String certifiedTin;

    /** nullable persistent field */
    private java.math.BigDecimal dfltAddrSeqNum;

    /** nullable persistent field */
    private java.math.BigDecimal discPct;

    /** nullable persistent field */
    private java.math.BigDecimal salesTaxPct;

    /** nullable persistent field */
    private java.math.BigDecimal irsCodeId;

    /** nullable persistent field */
    private String takeDiscount;

    /** nullable persistent field */
    private String discountInd;

    /** nullable persistent field */
    private java.math.BigDecimal discountDays;

    /** nullable persistent field */
    private String useTaxInd;

    /** nullable persistent field */
    private String combineInd;

    /** nullable persistent field */
    private String holdInd;

    /** nullable persistent field */
    private String advicePrint;

    /** nullable persistent field */
    private String sortCode;

    /** nullable persistent field */
    private java.util.Date vendorStartDate;

    /** nullable persistent field */
    private java.util.Date vendorEndDate;

    /** nullable persistent field */
    private String minorityInd;

    /** nullable persistent field */
    private String withholdingInd;

    /** nullable persistent field */
    private java.math.BigDecimal whThresholdAmt;

    /** nullable persistent field */
    private java.math.BigDecimal whPct;

    /** nullable persistent field */
    private java.math.BigDecimal netDueDays;

    /** nullable persistent field */
    private String minorityGroup;

    /** nullable persistent field */
    private String vendorName1;

    /** full constructor */
    public Vendor(com.tsa.puridiom.entity.sungard.VendorPK comp_id, java.lang.String statusInd, java.lang.String vendorType, java.lang.String tin, java.lang.String tinType, java.lang.String certifiedTin, java.math.BigDecimal dfltAddrSeqNum, java.math.BigDecimal discPct, java.math.BigDecimal salesTaxPct, java.math.BigDecimal irsCodeId, java.lang.String takeDiscount, java.lang.String discountInd, java.math.BigDecimal discountDays, java.lang.String useTaxInd, java.lang.String combineInd, java.lang.String holdInd, java.lang.String advicePrint, java.lang.String sortCode, java.util.Date vendorStartDate, java.util.Date vendorEndDate, java.lang.String minorityInd, java.lang.String withholdingInd, java.math.BigDecimal whThresholdAmt, java.math.BigDecimal whPct, java.math.BigDecimal netDueDays, java.lang.String minorityGroup, java.lang.String vendorName1) {
        this.comp_id = comp_id;
        this.statusInd = statusInd;
        this.vendorType = vendorType;
        this.tin = tin;
        this.tinType = tinType;
        this.certifiedTin = certifiedTin;
        this.dfltAddrSeqNum = dfltAddrSeqNum;
        this.discPct = discPct;
        this.salesTaxPct = salesTaxPct;
        this.irsCodeId = irsCodeId;
        this.takeDiscount = takeDiscount;
        this.discountInd = discountInd;
        this.discountDays = discountDays;
        this.useTaxInd = useTaxInd;
        this.combineInd = combineInd;
        this.holdInd = holdInd;
        this.advicePrint = advicePrint;
        this.sortCode = sortCode;
        this.vendorStartDate = vendorStartDate;
        this.vendorEndDate = vendorEndDate;
        this.minorityInd = minorityInd;
        this.withholdingInd = withholdingInd;
        this.whThresholdAmt = whThresholdAmt;
        this.whPct = whPct;
        this.netDueDays = netDueDays;
        this.minorityGroup = minorityGroup;
        this.vendorName1 = vendorName1;
    }

    /** default constructor */
    public Vendor() {
    }

    /** minimal constructor */
    public Vendor(com.tsa.puridiom.entity.sungard.VendorPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.sungard.VendorPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.sungard.VendorPK comp_id) {
        this.comp_id = comp_id;
    }
    
    public java.lang.String getStatusInd() {
		return (java.lang.String)HiltonUtility.ckNull(this.statusInd).trim();
    }

    public void setStatusInd(java.lang.String statusInd) {
		if (!HiltonUtility.isEmpty(statusInd) && statusInd.length() > 1) {
			statusInd = statusInd.substring(0, 1);
		}
		this.statusInd = statusInd;
    }

    public java.lang.String getVendorType() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorType).trim();
    }

    public void setVendorType(java.lang.String vendorType) {
		if (!HiltonUtility.isEmpty(vendorType) && vendorType.length() > 1) {
			vendorType = vendorType.substring(0, 1);
		}
		this.vendorType = vendorType;
    }

    public java.lang.String getTin() {
		return (java.lang.String)HiltonUtility.ckNull(this.tin).trim();
    }

    public void setTin(java.lang.String tin) {
		if (!HiltonUtility.isEmpty(tin) && tin.length() > 10) {
			tin = tin.substring(0, 10);
		}
		this.tin = tin;
    }

    public java.lang.String getTinType() {
		return (java.lang.String)HiltonUtility.ckNull(this.tinType).trim();
    }

    public void setTinType(java.lang.String tinType) {
		if (!HiltonUtility.isEmpty(tinType) && tinType.length() > 1) {
			tinType = tinType.substring(0, 1);
		}
		this.tinType = tinType;
    }

    public java.lang.String getCertifiedTin() {
		return (java.lang.String)HiltonUtility.ckNull(this.certifiedTin).trim();
    }

    public void setCertifiedTin(java.lang.String certifiedTin) {
		if (!HiltonUtility.isEmpty(certifiedTin) && certifiedTin.length() > 1) {
			certifiedTin = certifiedTin.substring(0, 1);
		}
		this.certifiedTin = certifiedTin;
    }

    public java.math.BigDecimal getDfltAddrSeqNum() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.dfltAddrSeqNum);
    }

    public void setDfltAddrSeqNum(java.math.BigDecimal dfltAddrSeqNum) {
        this.dfltAddrSeqNum = dfltAddrSeqNum;
    }

    public java.math.BigDecimal getDiscPct() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.discPct);
    }

    public void setDiscPct(java.math.BigDecimal discPct) {
        this.discPct = discPct;
    }

    public java.math.BigDecimal getSalesTaxPct() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.salesTaxPct);
    }

    public void setSalesTaxPct(java.math.BigDecimal salesTaxPct) {
        this.salesTaxPct = salesTaxPct;
    }

    public java.math.BigDecimal getIrsCodeId() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.irsCodeId);
    }

    public void setIrsCodeId(java.math.BigDecimal irsCodeId) {
        this.irsCodeId = irsCodeId;
    }

    public java.lang.String getTakeDiscount() {
		return (java.lang.String)HiltonUtility.ckNull(this.takeDiscount).trim();
    }

    public void setTakeDiscount(java.lang.String takeDiscount) {
		if (!HiltonUtility.isEmpty(takeDiscount) && takeDiscount.length() > 1) {
			takeDiscount = takeDiscount.substring(0, 1);
		}
		this.takeDiscount = takeDiscount;
    }

    public java.lang.String getDiscountInd() {
		return (java.lang.String)HiltonUtility.ckNull(this.discountInd).trim();
    }

    public void setDiscountInd(java.lang.String discountInd) {
		if (!HiltonUtility.isEmpty(discountInd) && discountInd.length() > 1) {
			discountInd = discountInd.substring(0, 1);
		}
		this.discountInd = discountInd;
    }

    public java.math.BigDecimal getDiscountDays() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.discountDays);
    }

    public void setDiscountDays(java.math.BigDecimal discountDays) {
        this.discountDays = discountDays;
    }

    public java.lang.String getUseTaxInd() {
		return (java.lang.String)HiltonUtility.ckNull(this.useTaxInd).trim();
    }

    public void setUseTaxInd(java.lang.String useTaxInd) {
		if (!HiltonUtility.isEmpty(useTaxInd) && useTaxInd.length() > 1) {
			useTaxInd = useTaxInd.substring(0, 1);
		}
		this.useTaxInd = useTaxInd;
    }

    public java.lang.String getCombineInd() {
		return (java.lang.String)HiltonUtility.ckNull(this.combineInd).trim();
    }

    public void setCombineInd(java.lang.String combineInd) {
		if (!HiltonUtility.isEmpty(combineInd) && combineInd.length() > 1) {
			combineInd = combineInd.substring(0, 1);
		}
		this.combineInd = combineInd;
    }

    public java.lang.String getHoldInd() {
		return (java.lang.String)HiltonUtility.ckNull(this.holdInd).trim();
    }

    public void setHoldInd(java.lang.String holdInd) {
		if (!HiltonUtility.isEmpty(holdInd) && holdInd.length() > 1) {
			holdInd = holdInd.substring(0, 1);
		}
		this.holdInd = holdInd;
    }

    public java.lang.String getAdvicePrint() {
		return (java.lang.String)HiltonUtility.ckNull(this.advicePrint).trim();
    }

    public void setAdvicePrint(java.lang.String advicePrint) {
		if (!HiltonUtility.isEmpty(advicePrint) && advicePrint.length() > 1) {
			advicePrint = advicePrint.substring(0, 1);
		}
		this.advicePrint = advicePrint;
    }

    public java.lang.String getSortCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.sortCode).trim();
    }

    public void setSortCode(java.lang.String sortCode) {
		if (!HiltonUtility.isEmpty(sortCode) && sortCode.length() > 4) {
			sortCode = sortCode.substring(0, 4);
		}
		this.sortCode = sortCode;
    }

    public java.util.Date getVendorStartDate() {
        return this.vendorStartDate;
    }

    public void setVendorStartDate(java.util.Date vendorStartDate) {
        this.vendorStartDate = vendorStartDate;
    }

    public java.util.Date getVendorEndDate() {
        return this.vendorEndDate;
    }

    public void setVendorEndDate(java.util.Date vendorEndDate) {
        this.vendorEndDate = vendorEndDate;
    }

    public java.lang.String getMinorityInd() {
		return (java.lang.String)HiltonUtility.ckNull(this.minorityInd).trim();
    }

    public void setMinorityInd(java.lang.String minorityInd) {
		if (!HiltonUtility.isEmpty(minorityInd) && minorityInd.length() > 1) {
			minorityInd = minorityInd.substring(0, 1);
		}
		this.minorityInd = minorityInd;
    }

    public java.lang.String getWithholdingInd() {
		return (java.lang.String)HiltonUtility.ckNull(this.withholdingInd).trim();
    }

    public void setWithholdingInd(java.lang.String withholdingInd) {
		if (!HiltonUtility.isEmpty(withholdingInd) && withholdingInd.length() > 1) {
			withholdingInd = withholdingInd.substring(0, 1);
		}
		this.withholdingInd = withholdingInd;
    }

    public java.math.BigDecimal getWhThresholdAmt() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.whThresholdAmt);
    }

    public void setWhThresholdAmt(java.math.BigDecimal whThresholdAmt) {
        this.whThresholdAmt = whThresholdAmt;
    }

    public java.math.BigDecimal getWhPct() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.whPct);
    }

    public void setWhPct(java.math.BigDecimal whPct) {
        this.whPct = whPct;
    }

    public java.math.BigDecimal getNetDueDays() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.netDueDays);
    }

    public void setNetDueDays(java.math.BigDecimal netDueDays) {
        this.netDueDays = netDueDays;
    }

    public java.lang.String getMinorityGroup() {
		return (java.lang.String)HiltonUtility.ckNull(this.minorityGroup).trim();
    }

    public void setMinorityGroup(java.lang.String minorityGroup) {
		if (!HiltonUtility.isEmpty(minorityGroup) && minorityGroup.length() > 60) {
			minorityGroup = minorityGroup.substring(0, 60);
		}
		this.minorityGroup = minorityGroup;
    }

    public java.lang.String getVendorName1() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorName1).trim();
    }

    public void setVendorName1(java.lang.String vendorName1) {
		if (!HiltonUtility.isEmpty(vendorName1) && vendorName1.length() > 60) {
			vendorName1 = vendorName1.substring(0, 60);
		}
		this.vendorName1 = vendorName1;
    }

    public String toString() {
        return new ToStringBuilder(this)
        .append("comp_id", getComp_id())
        .toString();
    }

}
