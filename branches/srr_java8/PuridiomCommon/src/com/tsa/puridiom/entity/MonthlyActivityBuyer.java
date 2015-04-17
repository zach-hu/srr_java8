package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

/** @author Hibernate CodeGenerator */
public class MonthlyActivityBuyer implements Serializable {

	/** identifier field */
	private String buyerCode;

	/** identifier field */
	private String period;
	
	/** identifier field */
	private String quarter1;

	/** identifier field */
    private java.math.BigDecimal action1;

    /** identifier field */
    private java.math.BigDecimal amount1;

    /** identifier field */
    private String quarter2;

    /** identifier field */
    private java.math.BigDecimal action2;

    /** identifier field */
    private java.math.BigDecimal amount2;

    /** identifier field */
    private String quarter3;

    /** identifier field */
    private java.math.BigDecimal action3;

    /** identifier field */
    private java.math.BigDecimal amount3;

    /** identifier field */
    private String quarter4;

    /** identifier field */
    private java.math.BigDecimal action4;

    /** identifier field */
    private java.math.BigDecimal amount4;

    /** full constructor */
    public MonthlyActivityBuyer
    	(
    		java.lang.String buyerCode,
    		java.lang.String period,
    		java.lang.String quarter1,
    		java.math.BigDecimal action1,
    		java.math.BigDecimal amount1,
    		java.lang.String quarter2,
    		java.math.BigDecimal action2,
    		java.math.BigDecimal amount2,
    		java.lang.String quarter3,
    		java.math.BigDecimal action3,
    		java.math.BigDecimal amount3,
    		java.lang.String quarter4,
    		java.math.BigDecimal action4,
    		java.math.BigDecimal amount4    		
    	) 
    {
    	this.buyerCode = buyerCode;
    	this.quarter1 = quarter1;
    	this.action1 = action1;
        this.amount1 = amount1;
        this.quarter2 = quarter2;
    	this.action2 = action2;
        this.amount2 = amount2;
        this.quarter3 = quarter3;
    	this.action3 = action3;
        this.amount3 = amount3;
        this.quarter4 = quarter4;
    	this.action4 = action4;
        this.amount4 = amount4;
        
     }

    /** default constructor */
    public MonthlyActivityBuyer() {
    }

    public java.lang.String getBuyerCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.buyerCode).trim();
    }    
    
    public void setBuyerCode(java.lang.String buyerCode) {
		this.buyerCode = buyerCode;
    }
    
    public java.lang.String getPeriod() {
		return (java.lang.String)HiltonUtility.ckNull(this.period).trim();
    }

    public void setPeriod(java.lang.String period) {
		this.period = period;
    }

    public java.lang.String getQuarter1() {
		return (java.lang.String)HiltonUtility.ckNull(this.quarter1).trim();
    }

    public void setQuarter1(java.lang.String quarter1) {
		this.quarter1 = quarter1;
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

    public java.lang.String getQuarter2() {
		return (java.lang.String)HiltonUtility.ckNull(this.quarter2).trim();
    }

    public void setQuarter2(java.lang.String quarter2) {
		this.quarter2 = quarter2;
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

    public java.lang.String getQuarter3() {
		return (java.lang.String)HiltonUtility.ckNull(this.quarter3).trim();
    }

    public void setQuarter3(java.lang.String quarter3) {
		this.quarter3 = quarter3;
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

    public java.lang.String getQuarter4() {
		return (java.lang.String)HiltonUtility.ckNull(this.quarter4).trim();
    }

    public void setQuarter4(java.lang.String quarter4) {
		this.quarter4 = quarter4;
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
