package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.Entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Account extends Entity implements Serializable {

    /** identifier field */
    private com.tsa.puridiom.entity.AccountPK comp_id;

    /** nullable persistent field */
    private String accountType;

    /** nullable persistent field */
    private String fld1;

    /** nullable persistent field */
    private String fld2;

    /** nullable persistent field */
    private String fld3;

    /** nullable persistent field */
    private String fld4;

    /** nullable persistent field */
    private String fld5;

    /** nullable persistent field */
    private String fld6;

    /** nullable persistent field */
    private String fld7;

    /** nullable persistent field */
    private String fld8;

    /** nullable persistent field */
    private String fld9;

    /** nullable persistent field */
    private String fld10;

    /** nullable persistent field */
    private String fld11;

    /** nullable persistent field */
    private String fld12;

    /** nullable persistent field */
    private String fld13;

    /** nullable persistent field */
    private String fld14;

    /** nullable persistent field */
    private String fld15;

    /** nullable persistent field */
    private java.math.BigDecimal allocPercent;

    /** nullable persistent field */
    private java.math.BigDecimal allocAmount;

    /** nullable persistent field */
    private String accountTitle;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date dateExpires;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private String allocMethod;

    /** nullable persistent field */
    private java.math.BigDecimal allocQty;

    /** nullable persistent field */
    private java.math.BigDecimal recQty;

    /** nullable persistent field */
    private java.math.BigDecimal icLastRec;

    /** nullable persistent field */
    private java.math.BigDecimal imisId;

    /** nullable persistent field */
    private java.math.BigDecimal imisLine;

    /** nullable persistent field */
    private String imisLiquidated;

    /** nullable persistent field */
    private java.math.BigDecimal imisObmo;

    /** nullable persistent field */
    private java.math.BigDecimal imisOblig;

    /** nullable persistent field */
    private java.math.BigDecimal imisSequence;

    /** nullable persistent field, M = Migration, S = System */
    private String source;
    
    /** nullable persistent field */
    private java.math.BigDecimal amountLine;
    
    /** nullable persistent field */
    private String budgetFlag;

    private String acctString = "";
    private String commodity = "" ;
    private String allocationDescription = "";
    
    private String fld2Description = "";
    private String fld4Description = "";
    private String fld5Description = "";

    /** full constructor */
    public Account(com.tsa.puridiom.entity.AccountPK comp_id, java.lang.String accountType, java.lang.String fld1, java.lang.String fld2, java.lang.String fld3, java.lang.String fld4, java.lang.String fld5, java.lang.String fld6, java.lang.String fld7, java.lang.String fld8, java.lang.String fld9, java.lang.String fld10, java.lang.String fld11, java.lang.String fld12, java.lang.String fld13, java.lang.String fld14, java.lang.String fld15, java.math.BigDecimal allocPercent, java.math.BigDecimal allocAmount, java.lang.String accountTitle, java.util.Date dateEntered, java.util.Date dateExpires, java.lang.String status, java.lang.String owner, java.lang.String allocMethod, java.math.BigDecimal allocQty, java.math.BigDecimal recQty, java.math.BigDecimal icLastRec, java.math.BigDecimal imisId, java.math.BigDecimal imisLine, java.lang.String imisLiquidated, java.math.BigDecimal imisObmo, java.math.BigDecimal imisOblig, java.math.BigDecimal imisSequence, java.math.BigDecimal amountLine) {
        this.comp_id = comp_id;
        this.accountType = accountType;
        this.fld1 = fld1;
        this.fld2 = fld2;
        this.fld3 = fld3;
        this.fld4 = fld4;
        this.fld5 = fld5;
        this.fld6 = fld6;
        this.fld7 = fld7;
        this.fld8 = fld8;
        this.fld9 = fld9;
        this.fld10 = fld10;
        this.fld11 = fld11;
        this.fld12 = fld12;
        this.fld13 = fld13;
        this.fld14 = fld14;
        this.fld15 = fld15;
        this.allocPercent = allocPercent;
        this.allocAmount = allocAmount;
        this.accountTitle = accountTitle;
        this.dateEntered = dateEntered;
        this.dateExpires = dateExpires;
        this.status = status;
        this.owner = owner;
        this.allocMethod = allocMethod;
        this.allocQty = allocQty;
        this.recQty = recQty;
        this.icLastRec = icLastRec;
        this.imisId = imisId;
        this.imisLine = imisLine;
        this.imisLiquidated = imisLiquidated;
        this.imisObmo = imisObmo;
        this.imisOblig = imisOblig;
        this.imisSequence = imisSequence;
        this.amountLine = amountLine;
    }

    /** default constructor */
    public Account() {
    }

    /** minimal constructor */
    public Account(com.tsa.puridiom.entity.AccountPK comp_id) {
        this.comp_id = comp_id;
    }

    public com.tsa.puridiom.entity.AccountPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.tsa.puridiom.entity.AccountPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.lang.String getAccountType() {
		return (java.lang.String)HiltonUtility.ckNull(this.accountType).trim();
    }

    public void setAccountType(java.lang.String accountType) {
		if (!HiltonUtility.isEmpty(accountType) && accountType.length() > 3) {
			accountType = accountType.substring(0, 3);
		}
		this.accountType = accountType;
    }

    public java.lang.String getFld1() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld1).trim();
    }

    public void setFld1(java.lang.String fld1) {
		if (!HiltonUtility.isEmpty(fld1) && fld1.length() > 50) {
			fld1 = fld1.substring(0, 50);
		}
		this.fld1 = fld1;
    }

    public java.lang.String getFld2() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld2).trim();
    }

    public void setFld2(java.lang.String fld2) {
		if (!HiltonUtility.isEmpty(fld2) && fld2.length() > 50) {
			fld2 = fld2.substring(0, 50);
		}
		this.fld2 = fld2;
    }

    public java.lang.String getFld3() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld3).trim();
    }

    public void setFld3(java.lang.String fld3) {
		if (!HiltonUtility.isEmpty(fld3) && fld3.length() > 50) {
			fld3 = fld3.substring(0, 50);
		}
		this.fld3 = fld3;
    }

    public java.lang.String getFld4() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld4).trim();
    }

    public void setFld4(java.lang.String fld4) {
		if (!HiltonUtility.isEmpty(fld4) && fld4.length() > 50) {
			fld4 = fld4.substring(0, 50);
		}
		this.fld4 = fld4;
    }

    public java.lang.String getFld5() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld5).trim();
    }

    public void setFld5(java.lang.String fld5) {
		if (!HiltonUtility.isEmpty(fld5) && fld5.length() > 50) {
			fld5 = fld5.substring(0, 50);
		}
		this.fld5 = fld5;
    }

    public java.lang.String getFld6() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld6).trim();
    }

    public void setFld6(java.lang.String fld6) {
		if (!HiltonUtility.isEmpty(fld6) && fld6.length() > 50) {
			fld6 = fld6.substring(0, 50);
		}
		this.fld6 = fld6;
    }

    public java.lang.String getFld7() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld7).trim();
    }

    public void setFld7(java.lang.String fld7) {
		if (!HiltonUtility.isEmpty(fld7) && fld7.length() > 50) {
			fld7 = fld7.substring(0, 50);
		}
		this.fld7 = fld7;
    }

    public java.lang.String getFld8() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld8).trim();
    }

    public void setFld8(java.lang.String fld8) {
		if (!HiltonUtility.isEmpty(fld8) && fld8.length() > 50) {
			fld8 = fld8.substring(0, 50);
		}
		this.fld8 = fld8;
    }

    public java.lang.String getFld9() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld9).trim();
    }

    public void setFld9(java.lang.String fld9) {
		if (!HiltonUtility.isEmpty(fld9) && fld9.length() > 50) {
			fld9 = fld9.substring(0, 50);
		}
		this.fld9 = fld9;
    }

    public java.lang.String getFld10() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld10).trim();
    }

    public void setFld10(java.lang.String fld10) {
		if (!HiltonUtility.isEmpty(fld10) && fld10.length() > 50) {
			fld10 = fld10.substring(0, 50);
		}
		this.fld10 = fld10;
    }

    public java.lang.String getFld11() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld11).trim();
    }

    public void setFld11(java.lang.String fld11) {
		if (!HiltonUtility.isEmpty(fld11) && fld11.length() > 50) {
			fld11 = fld11.substring(0, 50);
		}
		this.fld11 = fld11;
    }

    public java.lang.String getFld12() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld12).trim();
    }

    public void setFld12(java.lang.String fld12) {
		if (!HiltonUtility.isEmpty(fld12) && fld12.length() > 50) {
			fld12 = fld12.substring(0, 50);
		}
		this.fld12 = fld12;
    }

    public java.lang.String getFld13() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld13).trim();
    }

    public void setFld13(java.lang.String fld13) {
		if (!HiltonUtility.isEmpty(fld13) && fld13.length() > 50) {
			fld13 = fld13.substring(0, 50);
		}
		this.fld13 = fld13;
    }

    public java.lang.String getFld14() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld14).trim();
    }

    public void setFld14(java.lang.String fld14) {
		if (!HiltonUtility.isEmpty(fld14) && fld14.length() > 50) {
			fld14 = fld14.substring(0, 50);
		}
		this.fld14 = fld14;
    }

    public java.lang.String getFld15() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld15).trim();
    }

    public void setFld15(java.lang.String fld15) {
		if (!HiltonUtility.isEmpty(fld15) && fld15.length() > 50) {
			fld15 = fld15.substring(0, 50);
		}
		this.fld15 = fld15;
    }

    public java.math.BigDecimal getAllocPercent() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.allocPercent);
    }

    public void setAllocPercent(java.math.BigDecimal allocPercent) {
        this.allocPercent = allocPercent;
    }

    public java.math.BigDecimal getAllocAmount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.allocAmount);
    }

    public void setAllocAmount(java.math.BigDecimal allocAmount) {
        this.allocAmount = allocAmount;
    }

    public java.lang.String getAccountTitle() {
		return (java.lang.String)HiltonUtility.ckNull(this.accountTitle).trim();
    }

    public void setAccountTitle(java.lang.String accountTitle) {
		if (!HiltonUtility.isEmpty(accountTitle) && accountTitle.length() > 45) {
			accountTitle = accountTitle.substring(0, 45);
		}
		this.accountTitle = accountTitle;
    }

    public java.util.Date getDateEntered() {
        return this.dateEntered;
//		return HiltonUtility.ckNull(this.dateEntered);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateEntered);
    }

    public void setDateEntered(java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.util.Date getDateExpires() {
        return this.dateExpires;
//		return HiltonUtility.ckNull(this.dateExpires);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateExpires);
    }

    public void setDateExpires(java.util.Date dateExpires) {
        this.dateExpires = dateExpires;
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

    public java.lang.String getOwner() {
		return (java.lang.String)HiltonUtility.ckNull(this.owner).trim();
    }

    public void setOwner(java.lang.String owner) {
		if (!HiltonUtility.isEmpty(owner) && owner.length() > 15) {
			owner = owner.substring(0, 15);
		}
		this.owner = owner;
    }

    public java.lang.String getAllocMethod() {
		return (java.lang.String)HiltonUtility.ckNull(this.allocMethod).trim();
    }

    public void setAllocMethod(java.lang.String allocMethod) {
		if (!HiltonUtility.isEmpty(allocMethod) && allocMethod.length() > 2) {
			allocMethod = allocMethod.substring(0, 2);
		}
		this.allocMethod = allocMethod;
    }

    public java.math.BigDecimal getAllocQty() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.allocQty);
    }

    public void setAllocQty(java.math.BigDecimal allocQty) {
        this.allocQty = allocQty;
    }

    public java.math.BigDecimal getRecQty() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.recQty);
    }

    public void setRecQty(java.math.BigDecimal recQty) {
        this.recQty = recQty;
    }

    public java.math.BigDecimal getIcLastRec() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icLastRec);
    }

    public void setIcLastRec(java.math.BigDecimal icLastRec) {
        this.icLastRec = icLastRec;
    }

    public java.math.BigDecimal getImisId() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.imisId);
    }

    public void setImisId(java.math.BigDecimal imisId) {
        this.imisId = imisId;
    }

    public java.math.BigDecimal getImisLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.imisLine);
    }

    public void setImisLine(java.math.BigDecimal imisLine) {
        this.imisLine = imisLine;
    }

    public java.lang.String getImisLiquidated() {
		return (java.lang.String)HiltonUtility.ckNull(this.imisLiquidated).trim();
    }

    public void setImisLiquidated(java.lang.String imisLiquidated) {
		if (!HiltonUtility.isEmpty(imisLiquidated) && imisLiquidated.length() > 1) {
			imisLiquidated = imisLiquidated.substring(0, 1);
		}
		this.imisLiquidated = imisLiquidated;
    }

    public java.math.BigDecimal getImisObmo() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.imisObmo);
    }

    public void setImisObmo(java.math.BigDecimal imisObmo) {
        this.imisObmo = imisObmo;
    }

    public java.math.BigDecimal getImisOblig() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.imisOblig);
    }

    public void setImisOblig(java.math.BigDecimal imisOblig) {
        this.imisOblig = imisOblig;
    }

    public java.math.BigDecimal getImisSequence() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.imisSequence);
    }

    public void setImisSequence(java.math.BigDecimal imisSequence) {
        this.imisSequence = imisSequence;
    }

    public java.math.BigDecimal getAmountLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.amountLine);
    }

    public void setAmountLine(java.math.BigDecimal amountLine) {
        this.amountLine = amountLine;
    }

    /**
	 * @return the source
	 */
	public String getSource()
	{
		return (java.lang.String) HiltonUtility.ckNull(this.source).trim();
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source)
	{
		this.source = source;
	}
	
	/**
	 * @return the budgetFlag
	 */
	public String getBudgetFlag()
	{
		return (java.lang.String) HiltonUtility.ckNull(this.budgetFlag).trim();
	}

	/**
	 * @param budgetFlag the budgetFlag to set
	 */
	public void setBudgetFlag(String budgetFlag)
	{
		this.budgetFlag = budgetFlag;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Account) ) return false;
        Account castOther = (Account) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

    /**
     * @return Returns the acctString.
     */
    public String getAcctString() {
		return (java.lang.String)HiltonUtility.ckNull(this.acctString).trim();
    }
    /**
     * @return Returns the acctString.
     */
    public String getAcctString(String accountSeparator) {
        StringBuffer temp = new StringBuffer();
        temp = this.addFld(temp, getFld1(), accountSeparator);
        temp = this.addFld(temp, getFld2(), accountSeparator);
        temp = this.addFld(temp, getFld3(), accountSeparator);
        temp = this.addFld(temp, getFld4(), accountSeparator);
        temp = this.addFld(temp, getFld5(), accountSeparator);
        temp = this.addFld(temp, getFld6(), accountSeparator);
        temp = this.addFld(temp, getFld7(), accountSeparator);
        temp = this.addFld(temp, getFld8(), accountSeparator);
        temp = this.addFld(temp, getFld9(), accountSeparator);
        temp = this.addFld(temp, getFld10(), accountSeparator);
        temp = this.addFld(temp, getFld11(), accountSeparator);
        temp = this.addFld(temp, getFld12(), accountSeparator);
        temp = this.addFld(temp, getFld13(), accountSeparator);
        temp = this.addFld(temp, getFld14(), accountSeparator);
        temp = this.addFld(temp, getFld15(), accountSeparator);
        this.acctString = temp.toString();

        return this.acctString;
    }

    private StringBuffer addFld(StringBuffer temp, String fld, String accountSeparator)
    {
        if(!HiltonUtility.isEmpty(fld))
        {
            if (!HiltonUtility.isEmpty(temp.toString())) {
                temp.append(accountSeparator);
            }
            temp.append(fld);
        }
        return temp;
    }

    /**
     * @return the commodity
     */
    public String getCommodity() {
        return commodity;
    }

    /**
     * @param commodity the commodity to set
     */
    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    /**
	 * @return the allocationDescription
	 */
	public String getAllocationDescription() {
		return HiltonUtility.ckNull(allocationDescription);
	}

	/**
	 * @param acctStringDescription the allocationDescription to set
	 */
	public void setAllocationDescription(String allocationDescription) {
		this.allocationDescription = allocationDescription;
	}
	
	   /**
	 * @return the fld2Description
	 */
	public String getFld2Description() {
		return HiltonUtility.ckNull(fld2Description);
	}

	/**
	 * @param fld2Description the fld2Description to set
	 */
	public void setFld2Description(String fld2Description) {
		this.fld2Description = fld2Description;
	}	
	
	/**
	 * @return the fld4Description
	 */
	public String getFld4Description() {
		return HiltonUtility.ckNull(fld4Description);
	}

	/**
	 * @param fld4Description the fld4Description to set
	 */
	public void setFld4Description(String fld4Description) {
		this.fld4Description = fld4Description;
	}
	
	/**
	 * @return the fld5Description
	 */
	public String getFld5Description() {
		return HiltonUtility.ckNull(fld5Description);
	}

	/**
	 * @param fld5Description the fld5Description to set
	 */
	public void setFld5Description(String fld5Description) {
		this.fld5Description = fld5Description;
	}			
}
