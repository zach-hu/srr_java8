package com.tsa.puridiom.entity;

import java.io.Serializable;

import com.tsa.puridiom.common.utility.HiltonUtility;

/** @author Hibernate CodeGenerator */
public class MonthlyDetailActivityBuyer implements Serializable {

	/** identifier field */
	private String buyerCode;

	/** identifier field */
	private String period1;

	/** identifier field */
    private java.math.BigDecimal action1;

    /** identifier field */
    private java.math.BigDecimal amount1;

    /** identifier field */
	private String period2;

    /** identifier field */
    private java.math.BigDecimal action2;

    /** identifier field */
    private java.math.BigDecimal amount2;

    /** identifier field */
	private String period3;

    /** identifier field */
    private java.math.BigDecimal action3;

    /** identifier field */
    private java.math.BigDecimal amount3;

    /** identifier field */
	private String period4;

    /** identifier field */
    private java.math.BigDecimal action4;

    /** identifier field */
    private java.math.BigDecimal amount4;

    /** full constructor */
    public MonthlyDetailActivityBuyer
    	(
    		java.lang.String buyerCode,
    		java.lang.String period1,
    		java.math.BigDecimal action1,
    		java.math.BigDecimal amount1,
    		java.lang.String period2,
    		java.math.BigDecimal action2,
    		java.math.BigDecimal amount2,
    		java.lang.String period3,
    		java.math.BigDecimal action3,
    		java.math.BigDecimal amount3,
    		java.lang.String period4,
    		java.math.BigDecimal action4,
    		java.math.BigDecimal amount4
    	)
    {
    	this.buyerCode = buyerCode;
    	this.period1 = period1;
    	this.action1 = action1;
        this.amount1 = amount1;
        this.period2 = period2;
    	this.action2 = action2;
        this.amount2 = amount2;
        this.period3 = period3;
    	this.action3 = action3;
        this.amount3 = amount3;
        this.period4 = period4;
    	this.action4 = action4;
        this.amount4 = amount4;

     }

    /** default constructor */
    public MonthlyDetailActivityBuyer() {
    }

    public java.lang.String getBuyerCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.buyerCode).trim();
    }

    public void setBuyerCode(java.lang.String buyerCode) {
		this.buyerCode = buyerCode;
    }

    public java.lang.String getPeriod1() {
		return (java.lang.String)HiltonUtility.ckNull(this.period1).trim();
    }

    public void setPeriod1(java.lang.String period1) {
		this.period1 = period1;
    }

    public java.math.BigDecimal getAction1() {
    	return (java.math.BigDecimal)HiltonUtility.ckNull(this.action1);
	}

	public void setAction1(java.math.BigDecimal action1) {
		this.action1 = action1;
	}

	public java.math.BigDecimal getAmount1() {
    	return (java.math.BigDecimal)HiltonUtility.ckNull(this.amount1);
	}

	public void setAmount1(java.math.BigDecimal amount1) {
		this.amount1 = amount1;
	}

	public java.lang.String getPeriod2() {
		return (java.lang.String)HiltonUtility.ckNull(this.period2).trim();
    }

    public void setPeriod2(java.lang.String period2) {
		this.period2 = period2;
    }

    public java.math.BigDecimal getAction2() {
    	return (java.math.BigDecimal)HiltonUtility.ckNull(this.action2);
	}

	public void setAction2(java.math.BigDecimal action2) {
		this.action2 = action2;
	}

	public java.math.BigDecimal getAmount2() {
    	return (java.math.BigDecimal)HiltonUtility.ckNull(this.amount2);
	}

	public void setAmount2(java.math.BigDecimal amount2) {
		this.amount2 = amount2;
	}

	public java.lang.String getPeriod3() {
		return (java.lang.String)HiltonUtility.ckNull(this.period3).trim();
    }

    public void setPeriod3(java.lang.String period3) {
		this.period3 = period3;
    }

    public java.math.BigDecimal getAction3() {
    	return (java.math.BigDecimal)HiltonUtility.ckNull(this.action3);
	}

	public void setAction3(java.math.BigDecimal action3) {
		this.action3 = action3;
	}

	public java.math.BigDecimal getAmount3() {
    	return (java.math.BigDecimal)HiltonUtility.ckNull(this.amount3);
	}

	public void setAmount3(java.math.BigDecimal amount3) {
		this.amount3 = amount3;
	}

	public java.lang.String getPeriod4() {
		return (java.lang.String)HiltonUtility.ckNull(this.period4).trim();
    }

    public void setPeriod4(java.lang.String period4) {
		this.period4 = period4;
    }

    public java.math.BigDecimal getAction4() {
    	return (java.math.BigDecimal)HiltonUtility.ckNull(this.action4);
	}

	public void setAction4(java.math.BigDecimal action4) {
		this.action4 = action4;
	}

	public java.math.BigDecimal getAmount4() {
    	return (java.math.BigDecimal)HiltonUtility.ckNull(this.amount4);
	}

	public void setAmount4(java.math.BigDecimal amount4) {
		this.amount4 = amount4;
	}

}
