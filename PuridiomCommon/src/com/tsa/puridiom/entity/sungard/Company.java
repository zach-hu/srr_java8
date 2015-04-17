package com.tsa.puridiom.entity.sungard;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Company implements Serializable {

    /** nullable persistent field */
    private String companyCode;

    /** nullable persistent field */
    private String coShortDesc;

    /** nullable persistent field */
    private String companyName;

    /** nullable persistent field */
    private String naicCompanyCode;

    /** nullable persistent field */
    private String dfltFunctCrncy;

    /** nullable persistent field */
    private String companyType;

    /** nullable persistent field */
    private String coAddrs1;

    /** nullable persistent field */
    private String coAddrs2;

    /** nullable persistent field */
    private String coAddrs3;

    /** nullable persistent field */
    private String coAddrs4;

    /** nullable persistent field */
    private String cashBasis;

    /** nullable persistent field */
    private String accrualBasis;

    /** nullable persistent field */
    private String fisProcInd;

    /** nullable persistent field */
    private String fisStartMm;

    /** nullable persistent field */
    private String postIntrvl;

    /** nullable persistent field */
    private String detailDescFlag;

    /** nullable persistent field */
    private String nonZeroAmtFlag;

    /** nullable persistent field */
    private String autoClearingFlag;

    /** nullable persistent field */
    private BigDecimal calIntPlusNum;

    /** nullable persistent field */
    private BigDecimal calIntMinusNum;

    /** nullable persistent field */
    private BigDecimal tranDtPlusNum;

    /** nullable persistent field */
    private BigDecimal tranDtMinusNum;

    /** nullable persistent field */
    private String dfltCashAcct;

    /** nullable persistent field */
    private String dfltAccrualAcct;

    /** nullable persistent field */
    private String dfltDiscountAcct;

    /** nullable persistent field */
    private String dfltSalestaxAcct;

    /** nullable persistent field */
    private String dfltShippingAcct;

    /** nullable persistent field */
    private String dfltWithholdAcct;

    /** full constructor */
    public Company(java.lang.String companyCode, java.lang.String coShortDesc, java.lang.String companyName, java.lang.String naicCompanyCode, java.lang.String dfltFunctCrncy, java.lang.String companyType, java.lang.String coAddrs1, java.lang.String coAddrs2, java.lang.String coAddrs3, java.lang.String coAddrs4, java.lang.String cashBasis, java.lang.String accrualBasis, java.lang.String fisProcInd, java.lang.String fisStartMm, java.lang.String postIntrvl, java.lang.String detailDescFlag, java.lang.String nonZeroAmtFlag, java.lang.String autoClearingFlag, java.math.BigDecimal calIntPlusNum, java.math.BigDecimal calIntMinusNum, java.math.BigDecimal tranDtPlusNum, java.math.BigDecimal tranDtMinusNum, java.lang.String dfltCashAcct, java.lang.String dfltAccrualAcct, java.lang.String dfltDiscountAcct, java.lang.String dfltSalestaxAcct, java.lang.String dfltShippingAcct, java.lang.String dfltWithholdAcct) {
        this.companyCode = companyCode;
        this.coShortDesc = coShortDesc;
        this.companyName = companyName;
        this.naicCompanyCode = naicCompanyCode;
        this.dfltFunctCrncy = dfltFunctCrncy;
        this.companyType = companyType;
        this.coAddrs1 = coAddrs1;
        this.coAddrs2 = coAddrs2;
        this.coAddrs3 = coAddrs3;
        this.coAddrs4 = coAddrs4;
        this.cashBasis = cashBasis;
        this.accrualBasis = accrualBasis;
        this.fisProcInd = fisProcInd;
        this.fisStartMm = fisStartMm;
        this.postIntrvl = postIntrvl;
        this.detailDescFlag = detailDescFlag;
        this.nonZeroAmtFlag = nonZeroAmtFlag;
        this.autoClearingFlag = autoClearingFlag;
        this.calIntPlusNum = calIntPlusNum;
        this.calIntMinusNum = calIntMinusNum;
        this.tranDtPlusNum = tranDtPlusNum;
        this.tranDtMinusNum = tranDtMinusNum;
        this.dfltCashAcct = dfltCashAcct;
        this.dfltAccrualAcct = dfltAccrualAcct;
        this.dfltDiscountAcct = dfltDiscountAcct;
        this.dfltSalestaxAcct = dfltSalestaxAcct;
        this.dfltShippingAcct = dfltShippingAcct;
        this.dfltWithholdAcct = dfltWithholdAcct;
    }

    /** default constructor */
    public Company() {
    }

    public java.lang.String getCompanyCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.companyCode).trim();
    }

    public void setCompanyCode(java.lang.String companyCode) {
		if (!HiltonUtility.isEmpty(companyCode) && companyCode.length() > 15) {
			companyCode = companyCode.substring(0, 15);
		}
		this.companyCode = companyCode;
    }

    public java.lang.String getCoShortDesc() {
		return (java.lang.String)HiltonUtility.ckNull(this.coShortDesc).trim();
    }

    public void setCoShortDesc(java.lang.String coShortDesc) {
		if (!HiltonUtility.isEmpty(coShortDesc) && coShortDesc.length() > 20) {
			coShortDesc = coShortDesc.substring(0, 20);
		}
		this.coShortDesc = coShortDesc;
    }

    public java.lang.String getCompanyName() {
		return (java.lang.String)HiltonUtility.ckNull(this.companyName).trim();
    }

    public void setCompanyName(java.lang.String companyName) {
		if (!HiltonUtility.isEmpty(companyName) && companyName.length() > 50) {
			companyName = companyName.substring(0, 50);
		}
		this.companyName = companyName;
    }

    public java.lang.String getNaicCompanyCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.naicCompanyCode).trim();
    }

    public void setNaicCompanyCode(java.lang.String naicCompanyCode) {
		if (!HiltonUtility.isEmpty(naicCompanyCode) && naicCompanyCode.length() > 5) {
			naicCompanyCode = naicCompanyCode.substring(0, 5);
		}
		this.naicCompanyCode = naicCompanyCode;
    }

    public java.lang.String getDfltFunctCrncy() {
		return (java.lang.String)HiltonUtility.ckNull(this.dfltFunctCrncy).trim();
    }

    public void setDfltFunctCrncy(java.lang.String dfltFunctCrncy) {
		if (!HiltonUtility.isEmpty(dfltFunctCrncy) && dfltFunctCrncy.length() > 5) {
			dfltFunctCrncy = dfltFunctCrncy.substring(0, 5);
		}
		this.dfltFunctCrncy = dfltFunctCrncy;
    }

    public java.lang.String getCompanyType() {
		return (java.lang.String)HiltonUtility.ckNull(this.companyType).trim();
    }

    public void setCompanyType(java.lang.String companyType) {
		if (!HiltonUtility.isEmpty(companyType) && companyType.length() > 1) {
			companyType = companyType.substring(0, 1);
		}
		this.companyType = companyType;
    }

    public java.lang.String getCoAddrs1() {
		return (java.lang.String)HiltonUtility.ckNull(this.coAddrs1).trim();
    }

    public void setCoAddrs1(java.lang.String coAddrs1) {
		if (!HiltonUtility.isEmpty(coAddrs1) && coAddrs1.length() > 50) {
			coAddrs1 = coAddrs1.substring(0, 50);
		}
		this.coAddrs1 = coAddrs1;
    }

    public java.lang.String getCoAddrs2() {
		return (java.lang.String)HiltonUtility.ckNull(this.coAddrs2).trim();
    }

    public void setCoAddrs2(java.lang.String coAddrs2) {
		if (!HiltonUtility.isEmpty(coAddrs2) && coAddrs2.length() > 50) {
			coAddrs2 = coAddrs2.substring(0, 50);
		}
		this.coAddrs2 = coAddrs2;
    }

    public java.lang.String getCoAddrs3() {
		return (java.lang.String)HiltonUtility.ckNull(this.coAddrs3).trim();
    }

    public void setCoAddrs3(java.lang.String coAddrs3) {
		if (!HiltonUtility.isEmpty(coAddrs3) && coAddrs3.length() > 50) {
			coAddrs3 = coAddrs3.substring(0, 50);
		}
		this.coAddrs3 = coAddrs3;
    }

    public java.lang.String getCoAddrs4() {
		return (java.lang.String)HiltonUtility.ckNull(this.coAddrs4).trim();
    }

    public void setCoAddrs4(java.lang.String coAddrs4) {
		if (!HiltonUtility.isEmpty(coAddrs4) && coAddrs4.length() > 50) {
			coAddrs4 = coAddrs4.substring(0, 50);
		}
		this.coAddrs4 = coAddrs4;
    }

    public java.lang.String getCashBasis() {
		return (java.lang.String)HiltonUtility.ckNull(this.cashBasis).trim();
    }

    public void setCashBasis(java.lang.String cashBasis) {
		if (!HiltonUtility.isEmpty(cashBasis) && cashBasis.length() > 5) {
			cashBasis = cashBasis.substring(0, 5);
		}
		this.cashBasis = cashBasis;
    }

    public java.lang.String getAccrualBasis() {
		return (java.lang.String)HiltonUtility.ckNull(this.accrualBasis).trim();
    }

    public void setAccrualBasis(java.lang.String accrualBasis) {
		if (!HiltonUtility.isEmpty(accrualBasis) && accrualBasis.length() > 5) {
			accrualBasis = accrualBasis.substring(0, 5);
		}
		this.accrualBasis = accrualBasis;
    }

    public java.lang.String getFisProcInd() {
		return (java.lang.String)HiltonUtility.ckNull(this.fisProcInd).trim();
    }

    public void setFisProcInd(java.lang.String fisProcInd) {
		if (!HiltonUtility.isEmpty(fisProcInd) && fisProcInd.length() > 1) {
			fisProcInd = fisProcInd.substring(0, 1);
		}
		this.fisProcInd = fisProcInd;
    }

    public java.lang.String getFisStartMm() {
		return (java.lang.String)HiltonUtility.ckNull(this.fisStartMm).trim();
    }

    public void setFisStartMm(java.lang.String fisStartMm) {
		if (!HiltonUtility.isEmpty(fisStartMm) && fisStartMm.length() > 2) {
			fisStartMm = fisStartMm.substring(0, 2);
		}
		this.fisStartMm = fisStartMm;
    }

    public java.lang.String getPostIntrvl() {
		return (java.lang.String)HiltonUtility.ckNull(this.postIntrvl).trim();
    }

    public void setPostIntrvl(java.lang.String postIntrvl) {
		if (!HiltonUtility.isEmpty(postIntrvl) && postIntrvl.length() > 15) {
			postIntrvl = postIntrvl.substring(0, 15);
		}
		this.postIntrvl = postIntrvl;
    }

    public java.lang.String getDetailDescFlag() {
		return (java.lang.String)HiltonUtility.ckNull(this.detailDescFlag).trim();
    }

    public void setDetailDescFlag(java.lang.String detailDescFlag) {
		if (!HiltonUtility.isEmpty(detailDescFlag) && detailDescFlag.length() > 1) {
			detailDescFlag = detailDescFlag.substring(0, 1);
		}
		this.detailDescFlag = detailDescFlag;
    }

    public java.lang.String getNonZeroAmtFlag() {
		return (java.lang.String)HiltonUtility.ckNull(this.nonZeroAmtFlag).trim();
    }

    public void setNonZeroAmtFlag(java.lang.String nonZeroAmtFlag) {
		if (!HiltonUtility.isEmpty(nonZeroAmtFlag) && nonZeroAmtFlag.length() > 1) {
			nonZeroAmtFlag = nonZeroAmtFlag.substring(0, 1);
		}
		this.nonZeroAmtFlag = nonZeroAmtFlag;
    }

    public java.lang.String getAutoClearingFlag() {
		return (java.lang.String)HiltonUtility.ckNull(this.autoClearingFlag).trim();
    }

    public void setAutoClearingFlag(java.lang.String autoClearingFlag) {
		if (!HiltonUtility.isEmpty(autoClearingFlag) && autoClearingFlag.length() > 1) {
			autoClearingFlag = autoClearingFlag.substring(0, 1);
		}
		this.autoClearingFlag = autoClearingFlag;
    }

    public java.math.BigDecimal getCalIntPlusNum() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.calIntPlusNum);
    }

    public void setCalIntPlusNum(java.math.BigDecimal calIntPlusNum) {
        this.calIntPlusNum = calIntPlusNum;
    }

    public java.math.BigDecimal getCalIntMinusNum() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.calIntMinusNum);
    }

    public void setCalIntMinusNum(java.math.BigDecimal calIntMinusNum) {
        this.calIntMinusNum = calIntMinusNum;
    }

    public java.math.BigDecimal getTranDtPlusNum() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.tranDtPlusNum);
    }

    public void setTranDtPlusNum(java.math.BigDecimal tranDtPlusNum) {
        this.tranDtPlusNum = tranDtPlusNum;
    }

    public java.math.BigDecimal getTranDtMinusNum() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.tranDtMinusNum);
    }

    public void setTranDtMinusNum(java.math.BigDecimal tranDtMinusNum) {
        this.tranDtMinusNum = tranDtMinusNum;
    }

    public java.lang.String getDfltCashAcct() {
		return (java.lang.String)HiltonUtility.ckNull(this.dfltCashAcct).trim();
    }

    public void setDfltCashAcct(java.lang.String dfltCashAcct) {
		if (!HiltonUtility.isEmpty(dfltCashAcct) && dfltCashAcct.length() > 15) {
			dfltCashAcct = dfltCashAcct.substring(0, 15);
		}
		this.dfltCashAcct = dfltCashAcct;
    }

    public java.lang.String getDfltAccrualAcct() {
		return (java.lang.String)HiltonUtility.ckNull(this.dfltAccrualAcct).trim();
    }

    public void setDfltAccrualAcct(java.lang.String dfltAccrualAcct) {
		if (!HiltonUtility.isEmpty(dfltAccrualAcct) && dfltAccrualAcct.length() > 15) {
			dfltAccrualAcct = dfltAccrualAcct.substring(0, 15);
		}
		this.dfltAccrualAcct = dfltAccrualAcct;
    }

    public java.lang.String getDfltDiscountAcct() {
		return (java.lang.String)HiltonUtility.ckNull(this.dfltDiscountAcct).trim();
    }

    public void setDfltDiscountAcct(java.lang.String dfltDiscountAcct) {
		if (!HiltonUtility.isEmpty(dfltDiscountAcct) && dfltDiscountAcct.length() > 15) {
			dfltDiscountAcct = dfltDiscountAcct.substring(0, 15);
		}
		this.dfltDiscountAcct = dfltDiscountAcct;
    }

    public java.lang.String getDfltSalestaxAcct() {
		return (java.lang.String)HiltonUtility.ckNull(this.dfltSalestaxAcct).trim();
    }

    public void setDfltSalestaxAcct(java.lang.String dfltSalestaxAcct) {
		if (!HiltonUtility.isEmpty(dfltSalestaxAcct) && dfltSalestaxAcct.length() > 15) {
			dfltSalestaxAcct = dfltSalestaxAcct.substring(0, 15);
		}
		this.dfltSalestaxAcct = dfltSalestaxAcct;
    }

    public java.lang.String getDfltShippingAcct() {
		return (java.lang.String)HiltonUtility.ckNull(this.dfltShippingAcct).trim();
    }

    public void setDfltShippingAcct(java.lang.String dfltShippingAcct) {
		if (!HiltonUtility.isEmpty(dfltShippingAcct) && dfltShippingAcct.length() > 15) {
			dfltShippingAcct = dfltShippingAcct.substring(0, 15);
		}
		this.dfltShippingAcct = dfltShippingAcct;
    }

    public java.lang.String getDfltWithholdAcct() {
		return (java.lang.String)HiltonUtility.ckNull(this.dfltWithholdAcct).trim();
    }

    public void setDfltWithholdAcct(java.lang.String dfltWithholdAcct) {
		if (!HiltonUtility.isEmpty(dfltWithholdAcct) && dfltWithholdAcct.length() > 15) {
			dfltWithholdAcct = dfltWithholdAcct.substring(0, 15);
		}
		this.dfltWithholdAcct = dfltWithholdAcct;
    }

    public String toString() {
        return new ToStringBuilder(this)
        .append("companyCode", getCompanyCode())
        .toString();
    }

}
