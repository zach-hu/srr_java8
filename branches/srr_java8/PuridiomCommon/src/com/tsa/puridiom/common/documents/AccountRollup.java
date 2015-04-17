/*
 * Created on November 15, 2006
 */
package com.tsa.puridiom.common.documents;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.math.BigDecimal;

/**
 * @author Kathleen
 */
public class AccountRollup
{
	private BigDecimal allocAmount = new BigDecimal(0);
    private String fld1;
    private String fld2;
    private String fld3;
    private String fld4;
    private String fld5;
    private String fld6;
    private String fld7;
    private String fld8;
    private String fld9;
    private String fld10;
    private String fld11;
    private String fld12;
    private String fld13;
    private String fld14;
    private String fld15;

    /**
     * @return Returns the allocAmount.
     */
    public BigDecimal getAllocAmount() {
        return (java.math.BigDecimal) HiltonUtility.ckNull(allocAmount);
    }
    /**
     * @param allocAmount The allocAmount to set.
     */
    public void setAllocAmount(BigDecimal allocAmount) {
        this.allocAmount = allocAmount;
    }

    public java.lang.String getFld1() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld1).trim();
    }

    public void setFld1(java.lang.String fld1) {
		if (!HiltonUtility.isEmpty(fld1) && fld1.length() > 18) {
			fld1 = fld1.substring(0, 18);
		}
		this.fld1 = fld1;
    }

    public java.lang.String getFld2() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld2).trim();
    }

    public void setFld2(java.lang.String fld2) {
		if (!HiltonUtility.isEmpty(fld2) && fld2.length() > 15) {
			fld2 = fld2.substring(0, 15);
		}
		this.fld2 = fld2;
    }

    public java.lang.String getFld3() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld3).trim();
    }

    public void setFld3(java.lang.String fld3) {
		if (!HiltonUtility.isEmpty(fld3) && fld3.length() > 20) {
			fld3 = fld3.substring(0, 20);
		}
		this.fld3 = fld3;
    }

    public java.lang.String getFld4() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld4).trim();
    }

    public void setFld4(java.lang.String fld4) {
		if (!HiltonUtility.isEmpty(fld4) && fld4.length() > 15) {
			fld4 = fld4.substring(0, 15);
		}
		this.fld4 = fld4;
    }

    public java.lang.String getFld5() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld5).trim();
    }

    public void setFld5(java.lang.String fld5) {
		if (!HiltonUtility.isEmpty(fld5) && fld5.length() > 15) {
			fld5 = fld5.substring(0, 15);
		}
		this.fld5 = fld5;
    }

    public java.lang.String getFld6() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld6).trim();
    }

    public void setFld6(java.lang.String fld6) {
		if (!HiltonUtility.isEmpty(fld6) && fld6.length() > 15) {
			fld6 = fld6.substring(0, 15);
		}
		this.fld6 = fld6;
    }

    public java.lang.String getFld7() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld7).trim();
    }

    public void setFld7(java.lang.String fld7) {
		if (!HiltonUtility.isEmpty(fld7) && fld7.length() > 15) {
			fld7 = fld7.substring(0, 15);
		}
		this.fld7 = fld7;
    }

    public java.lang.String getFld8() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld8).trim();
    }

    public void setFld8(java.lang.String fld8) {
		if (!HiltonUtility.isEmpty(fld8) && fld8.length() > 15) {
			fld8 = fld8.substring(0, 15);
		}
		this.fld8 = fld8;
    }

    public java.lang.String getFld9() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld9).trim();
    }

    public void setFld9(java.lang.String fld9) {
		if (!HiltonUtility.isEmpty(fld9) && fld9.length() > 15) {
			fld9 = fld9.substring(0, 15);
		}
		this.fld9 = fld9;
    }

    public java.lang.String getFld10() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld10).trim();
    }

    public void setFld10(java.lang.String fld10) {
		if (!HiltonUtility.isEmpty(fld10) && fld10.length() > 15) {
			fld10 = fld10.substring(0, 15);
		}
		this.fld10 = fld10;
    }

    public java.lang.String getFld11() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld11).trim();
    }

    public void setFld11(java.lang.String fld11) {
		if (!HiltonUtility.isEmpty(fld11) && fld11.length() > 15) {
			fld11 = fld11.substring(0, 15);
		}
		this.fld11 = fld11;
    }

    public java.lang.String getFld12() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld12).trim();
    }

    public void setFld12(java.lang.String fld12) {
		if (!HiltonUtility.isEmpty(fld12) && fld12.length() > 15) {
			fld12 = fld12.substring(0, 15);
		}
		this.fld12 = fld12;
    }

    public java.lang.String getFld13() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld13).trim();
    }

    public void setFld13(java.lang.String fld13) {
		if (!HiltonUtility.isEmpty(fld13) && fld13.length() > 15) {
			fld13 = fld13.substring(0, 15);
		}
		this.fld13 = fld13;
    }

    public java.lang.String getFld14() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld14).trim();
    }

    public void setFld14(java.lang.String fld14) {
		if (!HiltonUtility.isEmpty(fld14) && fld14.length() > 15) {
			fld14 = fld14.substring(0, 15);
		}
		this.fld14 = fld14;
    }

    public java.lang.String getFld15() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld15).trim();
    }

    public void setFld15(java.lang.String fld15) {
		if (!HiltonUtility.isEmpty(fld15) && fld15.length() > 15) {
			fld15 = fld15.substring(0, 15);
		}
		this.fld15 = fld15;
    }

	public String toString()
	{
		return "AccountRollup - " + this.getFld1() + " - " + this.getFld2() + " - " + this.getFld3() + " - " + this.getFld4() + " - " + this.getFld5() + " - " + this.getAllocAmount();
	}
}
