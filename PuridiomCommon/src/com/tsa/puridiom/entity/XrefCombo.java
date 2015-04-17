package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;

/** @author Hibernate CodeGenerator */
public class XrefCombo {

	/** identifier field */
    private java.math.BigDecimal icXref;

    /** nullable persistent field */
    private String xrefType;

    /** nullable persistent field */
    private String code1;

    /** nullable persistent field */
    private String code2;

    /** nullable persistent field */
    private String code3;

    /** nullable persistent field */
    private String code4;

    /** nullable persistent field */
    private String code5;

    /** nullable persistent field */
    private String code6;

    /** nullable persistent field */
    private String code7;

    /** nullable persistent field */
    private String code8;

    /** nullable persistent field */
    private String code9;

    /** nullable persistent field */
    private String code10;

    /** nullable persistent field */
    private String code11;

    /** nullable persistent field */
    private String code12;

    /** nullable persistent field */
    private String code13;

    /** nullable persistent field */
    private String code14;

    /** nullable persistent field */
    private String code15;

    /** identifier field */
    private java.math.BigDecimal xrefInd;

    /** identifier field */
    private java.math.BigDecimal xrefAmt;

    /** identifier field */
    private java.util.Date dateEntered;

    /** identifier field */
    private java.util.Date dateExpires;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String owner;

	/** full constructor */
    public XrefCombo(java.math.BigDecimal icXref, java.lang.String xrefType, java.lang.String code1, java.lang.String code2, java.lang.String code3, java.lang.String code4, java.lang.String code5, java.lang.String code6, java.lang.String code7, java.lang.String code8, java.lang.String code9, java.lang.String code10, java.lang.String code11, java.lang.String code12, java.lang.String code13, java.lang.String code14, java.lang.String code15, java.math.BigDecimal xrefInd, java.math.BigDecimal xrefAmt, java.util.Date dateEntered, java.util.Date dateExpires, java.lang.String status, java.lang.String owner ) {
    	this.icXref=icXref;
    	this.xrefType=xrefType;
    	this.code1=code1;
    	this.code2=code2;
    	this.code3=code3;
    	this.code4=code4;
    	this.code5=code5;
    	this.code6=code6;
    	this.code7=code7;
    	this.code8=code8;
    	this.code9=code9;
    	this.code10=code10;
    	this.code11=code11;
    	this.code12=code12;
    	this.code13=code13;
    	this.code14=code14;
    	this.code15=code15;
    	this.xrefInd=xrefInd;
    	this.xrefAmt=xrefAmt;
    	this.dateEntered=dateEntered;
    	this.dateExpires=dateExpires;
    	this.status=status;
    	this.owner=owner;
    }

    /** default constructor */
    public XrefCombo() {
    }

    /** minimal constructor */
    public XrefCombo(java.math.BigDecimal icXref) {
        this.icXref = icXref;
    }

    public java.math.BigDecimal getIcXref() {
    	return (java.math.BigDecimal)HiltonUtility.ckNull(this.icXref);
	}

	public void setIcXref(java.math.BigDecimal icXref) {
		this.icXref = icXref;
	}

	public String getXrefType() {
		return (java.lang.String)HiltonUtility.ckNull(this.xrefType);
	}

	public void setXrefType(String xrefType) {
		this.xrefType = xrefType;
	}

	public String getCode1() {
		return (java.lang.String)HiltonUtility.ckNull(this.code1);
	}

	public void setCode1(String code1) {
		this.code1 = code1;
	}

	public String getCode2() {
		return (java.lang.String)HiltonUtility.ckNull(this.code2);
	}

	public void setCode2(String code2) {
		this.code2 = code2;
	}
	public String getCode3() {
		return (java.lang.String)HiltonUtility.ckNull(this.code3);
	}

	public void setCode3(String code3) {
		this.code3 = code3;
	}
	public String getCode4() {
		return (java.lang.String)HiltonUtility.ckNull(this.code4);
	}

	public void setCode4(String code4) {
		this.code4 = code4;
	}
	public String getCode5() {
		return (java.lang.String)HiltonUtility.ckNull(this.code5);
	}

	public void setCode5(String code5) {
		this.code5 = code5;
	}
	public String getCode6() {
		return (java.lang.String)HiltonUtility.ckNull(this.code6);
	}

	public void setCode6(String code6) {
		this.code6 = code6;
	}
	public String getCode7() {
		return (java.lang.String)HiltonUtility.ckNull(this.code7);
	}

	public void setCode7(String code7) {
		this.code7 = code7;
	}
	public String getCode8() {
		return (java.lang.String)HiltonUtility.ckNull(this.code8);
	}

	public void setCode8(String code8) {
		this.code8 = code8;
	}
	public String getCode9() {
		return (java.lang.String)HiltonUtility.ckNull(this.code9);
	}

	public void setCode9(String code9) {
		this.code9 = code9;
	}
	public String getCode10() {
		return (java.lang.String)HiltonUtility.ckNull(this.code10);
	}

	public void setCode10(String code10) {
		this.code10 = code10;
	}
	public String getCode11() {
		return (java.lang.String)HiltonUtility.ckNull(this.code11);
	}

	public void setCode11(String code11) {
		this.code11 = code11;
	}
	public String getCode12() {
		return (java.lang.String)HiltonUtility.ckNull(this.code12);
	}

	public void setCode12(String code12) {
		this.code12 = code12;
	}

	public String getCode13() {
		return (java.lang.String)HiltonUtility.ckNull(this.code13);
	}

	public void setCode13(String code13) {
		this.code13 = code13;
	}
	public String getCode14() {
		return (java.lang.String)HiltonUtility.ckNull(this.code14);
	}

	public void setCode14(String code14) {
		this.code14 = code14;
	}
	public String getCode15() {
		return (java.lang.String)HiltonUtility.ckNull(this.code15);
	}

	public void setCode15(String code15) {
		this.code15 = code15;
	}

	public java.math.BigDecimal getXrefInd() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.xrefInd);
	}

	public void setXrefInd(java.math.BigDecimal xrefInd) {
		this.xrefInd = xrefInd;
	}

	public java.math.BigDecimal getXrefAmt() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.xrefAmt);
	}

	public void setXrefAmt(java.math.BigDecimal xrefAmt) {
		this.xrefAmt = xrefAmt;
	}

	public java.util.Date getDateEntered() {
		return this.dateEntered;
	}

	public void setDateEntered(java.util.Date dateEntered) {
		this.dateEntered = dateEntered;
	}

	public java.util.Date getDateExpires() {
		return this.dateExpires;
	}

	public void setDateExpires(java.util.Date dateExpires) {
		this.dateExpires = dateExpires;
	}

	public String getStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.status);
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOwner() {
		return (java.lang.String)HiltonUtility.ckNull(this.owner);
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

}