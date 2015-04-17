package com.tsa.puridiom.entity.sungard;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ApBstrnHeader implements Serializable {

    /** persistent field */
    private java.math.BigDecimal tranId;

    /** persistent field */
    private String statusInd;

    /** persistent field */
    private String advicePrint;

    /** persistent field */
    private String checkNumber;

    /** persistent field */
    private String combineInd;

    /** persistent field */
    private String entrydate;

    /** persistent field */
    private String holdInd;

    /** persistent field */
    private java.util.Date invoiceDate;

    /** persistent field */
    private String memoCheckInd;

    /** persistent field */
    private java.util.Date payDate;

    /** persistent field */
    private String companyCode;

    /** persistent field */
    private String paymentMode;

    /** persistent field */
    private String sortCode;

    /** persistent field */
    private java.math.BigDecimal irsCodeId;

    /** persistent field */
    private String tin;

    /** persistent field */
    private String tinType;

    /** persistent field */
    private String useTaxInd;

    /** persistent field */
    private String vendorId;

    /** persistent field */
    private String vendorType;

    /** persistent field */
    private String checkReconStatus;

    /** persistent field */
    private java.util.Date checkReconDate;

    /** persistent field */
    private java.math.BigDecimal discountPct;

    /** persistent field */
    private java.math.BigDecimal discountAmt;

    /** persistent field */
    private java.math.BigDecimal discountDays;

    /** persistent field */
    private java.math.BigDecimal salesTaxPct;

    /** persistent field */
    private java.math.BigDecimal salesTaxAmt;

    /** persistent field */
    private java.math.BigDecimal shippingAmt;

    /** persistent field */
    private java.math.BigDecimal withholdingAmt;

    /** persistent field */
    private java.math.BigDecimal invoiceAmt;

    /** persistent field */
    private java.math.BigDecimal netInvoiceAmt;

    /** persistent field */
    private java.math.BigDecimal checkAmt;

    /** persistent field */
    private String creditMemoInd;

    /** persistent field */
    private String whOverrideInd;

    /** persistent field */
    private java.util.Date checkDate;

    /** persistent field */
    private String eftAdviceNum;

    /** persistent field */
    private String eftTraceNum;

    /** persistent field */
    private java.math.BigDecimal eftAmt;

    /** persistent field */
    private String poNumber;

    /** persistent field */
    private String rpNumber;

    /** persistent field */
    private String entryOperator;

    /** persistent field */
    private String approvalOperator;

    /** persistent field */
    private String bank;

    /** persistent field */
    private java.math.BigDecimal accrAcctgTranId;

    /** persistent field */
    private String bankAccount;

    /** persistent field */
    private String vendorName1;

    /** full constructor */
    public ApBstrnHeader(java.math.BigDecimal tranId, java.lang.String statusInd, java.lang.String advicePrint, java.lang.String checkNumber, java.lang.String combineInd, java.lang.String entrydate, java.lang.String holdInd, java.util.Date invoiceDate, java.lang.String memoCheckInd, java.util.Date payDate, java.lang.String companyCode, java.lang.String paymentMode, java.lang.String sortCode, java.math.BigDecimal irsCodeId, java.lang.String tin, java.lang.String tinType, java.lang.String useTaxInd, java.lang.String vendorId, java.lang.String vendorType, java.lang.String checkReconStatus, java.util.Date checkReconDate, java.math.BigDecimal discountPct, java.math.BigDecimal discountAmt, java.math.BigDecimal discountDays, java.math.BigDecimal salesTaxPct, java.math.BigDecimal salesTaxAmt, java.math.BigDecimal shippingAmt, java.math.BigDecimal withholdingAmt, java.math.BigDecimal invoiceAmt, java.math.BigDecimal netInvoiceAmt, java.math.BigDecimal checkAmt, java.lang.String creditMemoInd, java.lang.String whOverrideInd, java.util.Date checkDate, java.lang.String eftAdviceNum, java.lang.String eftTraceNum, java.math.BigDecimal eftAmt, java.lang.String poNumber, java.lang.String rpNumber, java.lang.String entryOperator, java.lang.String approvalOperator, java.lang.String bank, java.math.BigDecimal accrAcctgTranId, java.lang.String bankAccount, java.lang.String vendorName1) {
        this.tranId = tranId;
        this.statusInd = statusInd;
        this.advicePrint = advicePrint;
        this.checkNumber = checkNumber;
        this.combineInd = combineInd;
        this.entrydate = entrydate;
        this.holdInd = holdInd;
        this.invoiceDate = invoiceDate;
        this.memoCheckInd = memoCheckInd;
        this.payDate = payDate;
        this.companyCode = companyCode;
        this.paymentMode = paymentMode;
        this.sortCode = sortCode;
        this.irsCodeId = irsCodeId;
        this.tin = tin;
        this.tinType = tinType;
        this.useTaxInd = useTaxInd;
        this.vendorId = vendorId;
        this.vendorType = vendorType;
        this.checkReconStatus = checkReconStatus;
        this.checkReconDate = checkReconDate;
        this.discountPct = discountPct;
        this.discountAmt = discountAmt;
        this.discountDays = discountDays;
        this.salesTaxPct = salesTaxPct;
        this.salesTaxAmt = salesTaxAmt;
        this.shippingAmt = shippingAmt;
        this.withholdingAmt = withholdingAmt;
        this.invoiceAmt = invoiceAmt;
        this.netInvoiceAmt = netInvoiceAmt;
        this.checkAmt = checkAmt;
        this.creditMemoInd = creditMemoInd;
        this.whOverrideInd = whOverrideInd;
        this.checkDate = checkDate;
        this.eftAdviceNum = eftAdviceNum;
        this.eftTraceNum = eftTraceNum;
        this.eftAmt = eftAmt;
        this.poNumber = poNumber;
        this.rpNumber = rpNumber;
        this.entryOperator = entryOperator;
        this.approvalOperator = approvalOperator;
        this.bank = bank;
        this.accrAcctgTranId = accrAcctgTranId;
        this.bankAccount = bankAccount;
        this.vendorName1 = vendorName1;
    }

    /** default constructor */
    public ApBstrnHeader() {
    }

    public java.math.BigDecimal getTranId() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.tranId);
    }

    public void setTranId(java.math.BigDecimal tranId) {
        this.tranId = tranId;
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

    public java.lang.String getAdvicePrint() {
		return (java.lang.String)HiltonUtility.ckNull(this.advicePrint).trim();
    }

    public void setAdvicePrint(java.lang.String advicePrint) {
		if (!HiltonUtility.isEmpty(advicePrint) && advicePrint.length() > 1) {
			advicePrint = advicePrint.substring(0, 1);
		}
		this.advicePrint = advicePrint;
    }

    public java.lang.String getCheckNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.checkNumber).trim();
    }

    public void setCheckNumber(java.lang.String checkNumber) {
		if (!HiltonUtility.isEmpty(checkNumber) && checkNumber.length() > 11) {
			checkNumber = checkNumber.substring(0, 11);
		}
		this.checkNumber = checkNumber;
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

    public java.lang.String getEntrydate() {
		return (java.lang.String)HiltonUtility.ckNull(this.entrydate).trim();
    }

    public void setEntrydate(java.lang.String entrydate) {
		if (!HiltonUtility.isEmpty(entrydate) && entrydate.length() > 26) {
			entrydate = entrydate.substring(0, 26);
		}
		this.entrydate = entrydate;
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

    public java.util.Date getInvoiceDate() {
        return this.invoiceDate;
    }

    public void setInvoiceDate(java.util.Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public java.lang.String getMemoCheckInd() {
		return (java.lang.String)HiltonUtility.ckNull(this.memoCheckInd).trim();
    }

    public void setMemoCheckInd(java.lang.String memoCheckInd) {
		if (!HiltonUtility.isEmpty(memoCheckInd) && memoCheckInd.length() > 1) {
			memoCheckInd = memoCheckInd.substring(0, 1);
		}
		this.memoCheckInd = memoCheckInd;
    }

    public java.util.Date getPayDate() {
        return this.payDate;
    }

    public void setPayDate(java.util.Date payDate) {
        this.payDate = payDate;
    }

    public java.lang.String getCompanyCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.companyCode).trim();
    }

    public void setCompanyCode(java.lang.String companyCode) {
		if (!HiltonUtility.isEmpty(companyCode) && companyCode.length() > 5) {
			companyCode = companyCode.substring(0, 5);
		}
		this.companyCode = companyCode;
    }

    public java.lang.String getPaymentMode() {
		return (java.lang.String)HiltonUtility.ckNull(this.paymentMode).trim();
    }

    public void setPaymentMode(java.lang.String paymentMode) {
		if (!HiltonUtility.isEmpty(paymentMode) && paymentMode.length() > 1) {
			paymentMode = paymentMode.substring(0, 1);
		}
		this.paymentMode = paymentMode;
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

    public java.math.BigDecimal getIrsCodeId() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.irsCodeId);
    }

    public void setIrsCodeId(java.math.BigDecimal irsCodeId) {
        this.irsCodeId = irsCodeId;
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

    public java.lang.String getUseTaxInd() {
		return (java.lang.String)HiltonUtility.ckNull(this.useTaxInd).trim();
    }

    public void setUseTaxInd(java.lang.String useTaxInd) {
		if (!HiltonUtility.isEmpty(useTaxInd) && useTaxInd.length() > 1) {
			useTaxInd = useTaxInd.substring(0, 1);
		}
		this.useTaxInd = useTaxInd;
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

    public java.lang.String getVendorType() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorType).trim();
    }

    public void setVendorType(java.lang.String vendorType) {
		if (!HiltonUtility.isEmpty(vendorType) && vendorType.length() > 1) {
			vendorType = vendorType.substring(0, 1);
		}
		this.vendorType = vendorType;
    }

    public java.lang.String getCheckReconStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.checkReconStatus).trim();
    }

    public void setCheckReconStatus(java.lang.String checkReconStatus) {
		if (!HiltonUtility.isEmpty(checkReconStatus) && checkReconStatus.length() > 1) {
			checkReconStatus = checkReconStatus.substring(0, 1);
		}
		this.checkReconStatus = checkReconStatus;
    }

    public java.util.Date getCheckReconDate() {
        return this.checkReconDate;
    }

    public void setCheckReconDate(java.util.Date checkReconDate) {
        this.checkReconDate = checkReconDate;
    }

    public java.math.BigDecimal getDiscountPct() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.discountPct);
    }

    public void setDiscountPct(java.math.BigDecimal discountPct) {
        this.discountPct = discountPct;
    }

    public java.math.BigDecimal getDiscountAmt() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.discountAmt);
    }

    public void setDiscountAmt(java.math.BigDecimal discountAmt) {
        this.discountAmt = discountAmt;
    }

    public java.math.BigDecimal getDiscountDays() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.discountDays);
    }

    public void setDiscountDays(java.math.BigDecimal discountDays) {
        this.discountDays = discountDays;
    }

    public java.math.BigDecimal getSalesTaxPct() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.salesTaxPct);
    }

    public void setSalesTaxPct(java.math.BigDecimal salesTaxPct) {
        this.salesTaxPct = salesTaxPct;
    }

    public java.math.BigDecimal getSalesTaxAmt() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.salesTaxAmt);
    }

    public void setSalesTaxAmt(java.math.BigDecimal salesTaxAmt) {
        this.salesTaxAmt = salesTaxAmt;
    }

    public java.math.BigDecimal getShippingAmt() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.shippingAmt);
    }

    public void setShippingAmt(java.math.BigDecimal shippingAmt) {
        this.shippingAmt = shippingAmt;
    }

    public java.math.BigDecimal getWithholdingAmt() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.withholdingAmt);
    }

    public void setWithholdingAmt(java.math.BigDecimal withholdingAmt) {
        this.withholdingAmt = withholdingAmt;
    }

    public java.math.BigDecimal getInvoiceAmt() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.invoiceAmt);
    }

    public void setInvoiceAmt(java.math.BigDecimal invoiceAmt) {
        this.invoiceAmt = invoiceAmt;
    }

    public java.math.BigDecimal getNetInvoiceAmt() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.netInvoiceAmt);
    }

    public void setNetInvoiceAmt(java.math.BigDecimal netInvoiceAmt) {
        this.netInvoiceAmt = netInvoiceAmt;
    }

    public java.math.BigDecimal getCheckAmt() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.checkAmt);
    }

    public void setCheckAmt(java.math.BigDecimal checkAmt) {
        this.checkAmt = checkAmt;
    }

    public java.lang.String getCreditMemoInd() {
		return (java.lang.String)HiltonUtility.ckNull(this.creditMemoInd).trim();
    }

    public void setCreditMemoInd(java.lang.String creditMemoInd) {
		if (!HiltonUtility.isEmpty(creditMemoInd) && creditMemoInd.length() > 1) {
			creditMemoInd = creditMemoInd.substring(0, 1);
		}
		this.creditMemoInd = creditMemoInd;
    }

    public java.lang.String getWhOverrideInd() {
		return (java.lang.String)HiltonUtility.ckNull(this.whOverrideInd).trim();
    }

    public void setWhOverrideInd(java.lang.String whOverrideInd) {
		if (!HiltonUtility.isEmpty(whOverrideInd) && whOverrideInd.length() > 1) {
			whOverrideInd = whOverrideInd.substring(0, 1);
		}
		this.whOverrideInd = whOverrideInd;
    }

    public java.util.Date getCheckDate() {
        return this.checkDate;
    }

    public void setCheckDate(java.util.Date checkDate) {
        this.checkDate = checkDate;
    }

    public java.lang.String getEftAdviceNum() {
		return (java.lang.String)HiltonUtility.ckNull(this.eftAdviceNum).trim();
    }

    public void setEftAdviceNum(java.lang.String eftAdviceNum) {
		if (!HiltonUtility.isEmpty(eftAdviceNum) && eftAdviceNum.length() > 11) {
			eftAdviceNum = eftAdviceNum.substring(0, 11);
		}
		this.eftAdviceNum = eftAdviceNum;
    }

    public java.lang.String getEftTraceNum() {
		return (java.lang.String)HiltonUtility.ckNull(this.eftTraceNum).trim();
    }

    public void setEftTraceNum(java.lang.String eftTraceNum) {
		if (!HiltonUtility.isEmpty(eftTraceNum) && eftTraceNum.length() > 15) {
			eftTraceNum = eftTraceNum.substring(0, 15);
		}
		this.eftTraceNum = eftTraceNum;
    }

    public java.math.BigDecimal getEftAmt() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.eftAmt);
    }

    public void setEftAmt(java.math.BigDecimal eftAmt) {
        this.eftAmt = eftAmt;
    }

    public java.lang.String getPoNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.poNumber).trim();
    }

    public void setPoNumber(java.lang.String poNumber) {
		if (!HiltonUtility.isEmpty(poNumber) && poNumber.length() > 10) {
			poNumber = poNumber.substring(0, 10);
		}
		this.poNumber = poNumber;
    }

    public java.lang.String getRpNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.rpNumber).trim();
    }

    public void setRpNumber(java.lang.String rpNumber) {
		if (!HiltonUtility.isEmpty(rpNumber) && rpNumber.length() > 10) {
			rpNumber = rpNumber.substring(0, 10);
		}
		this.rpNumber = rpNumber;
    }

    public java.lang.String getEntryOperator() {
		return (java.lang.String)HiltonUtility.ckNull(this.entryOperator).trim();
    }

    public void setEntryOperator(java.lang.String entryOperator) {
		if (!HiltonUtility.isEmpty(entryOperator) && entryOperator.length() > 8) {
			entryOperator = entryOperator.substring(0, 8);
		}
		this.entryOperator = entryOperator;
    }

    public java.lang.String getApprovalOperator() {
		return (java.lang.String)HiltonUtility.ckNull(this.approvalOperator).trim();
    }

    public void setApprovalOperator(java.lang.String approvalOperator) {
		if (!HiltonUtility.isEmpty(approvalOperator) && approvalOperator.length() > 8) {
			approvalOperator = approvalOperator.substring(0, 8);
		}
		this.approvalOperator = approvalOperator;
    }

    public java.lang.String getBank() {
		return (java.lang.String)HiltonUtility.ckNull(this.bank).trim();
    }

    public void setBank(java.lang.String bank) {
		if (!HiltonUtility.isEmpty(bank) && bank.length() > 10) {
			bank = bank.substring(0, 10);
		}
		this.bank = bank;
    }

    public java.math.BigDecimal getAccrAcctgTranId() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.accrAcctgTranId);
    }

    public void setAccrAcctgTranId(java.math.BigDecimal accrAcctgTranId) {
        this.accrAcctgTranId = accrAcctgTranId;
    }

    public java.lang.String getBankAccount() {
		return (java.lang.String)HiltonUtility.ckNull(this.bankAccount).trim();
    }

    public void setBankAccount(java.lang.String bankAccount) {
		if (!HiltonUtility.isEmpty(bankAccount) && bankAccount.length() > 15) {
			bankAccount = bankAccount.substring(0, 15);
		}
		this.bankAccount = bankAccount;
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
            .toString();
    }

}
