package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.Entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


public class Punchout implements Serializable {

	/** identifier field */
    private java.math.BigDecimal icPunchout;

    /** nullable persistent field */
    private String fromDomain;

	/** nullable persistent field */
    private String fromIdentity;

	/** nullable persistent field */
    private String toDomain;

	/** nullable persistent field */
    private String toIdentity;

	/** nullable persistent field */
    private String senderDomain;

	/** nullable persistent field */
    private String senderIdentity;

	/** nullable persistent field */
    private String senderSecret;

	/** nullable persistent field */
    private String senderAgent;

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
    private String auxiliary;

	/** nullable persistent field */
    private String shipTo;

	/** nullable persistent field */
    private String url;

    /** nullable persistent field */
    private String defaultEmail;

	/** nullable persistent field */
    private String shipToEmail;

	/** nullable persistent field */
    private String generalInfo;

    /** nullable persistent field */
    private String defaultDate;

    /** full constructor */
    public Punchout(java.math.BigDecimal icPunchout,    java.lang.String fromDomain,
    	    java.lang.String fromIdentity,    java.lang.String toDomain,
    	    java.lang.String toIdentity,    java.lang.String senderDomain,
    	    java.lang.String senderIdentity,    java.lang.String senderSecret,
    	    java.lang.String senderAgent,    java.lang.String fld1,
    	    java.lang.String fld2,    java.lang.String fld3,
    	    java.lang.String fld4,    java.lang.String fld5,
    	    java.lang.String fld6,    java.lang.String fld7,
    	    java.lang.String fld8,    java.lang.String fld9,
    	    java.lang.String fld10,    java.lang.String fld11,
    	    java.lang.String fld12,	    java.lang.String fld13,
    	    java.lang.String fld14,	    java.lang.String fld15,
    	    java.lang.String auxiliary,    java.lang.String shipTo,
    	    java.lang.String url, java.lang.String defaultEmail,
    	    java.lang.String shipToEmail, java.lang.String generalInfo,
    	    java.lang.String defaultDate) {
    	this.icPunchout = icPunchout;
        this.fromDomain = fromDomain;
        this.fromIdentity = fromIdentity;
        this.toDomain = toDomain;
        this.toIdentity = toIdentity;
        this.senderDomain = senderDomain;
        this.senderIdentity = senderIdentity;
        this.senderSecret = senderSecret;
        this.senderAgent = senderAgent;
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
        this.auxiliary = auxiliary;
        this.shipTo = shipTo;
        this.url = url;
        this.defaultEmail = defaultEmail;
        this.shipToEmail = shipToEmail;
        this.generalInfo = generalInfo;
        this.defaultDate = defaultDate;
    }

    /** default constructor */
    public Punchout() {
    }

    /** minimal constructor */
    public Punchout(java.math.BigDecimal icPunchout) {
    	this.icPunchout = icPunchout;
    }

    public java.math.BigDecimal getIcPunchout() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icPunchout);
    }

    public void setIcPunchout(java.math.BigDecimal icPunchout) {
        this.icPunchout = icPunchout;
    }

    public java.lang.String getFromDomain() {
		return (java.lang.String)HiltonUtility.ckNull(this.fromDomain).trim();
    }

    public void setFromDomain(java.lang.String fromDomain) {
		if (!HiltonUtility.isEmpty(fromDomain) && fromDomain.length() > 20) {
			fromDomain = fromDomain.substring(0, 20);
		}
		this.fromDomain = fromDomain;
    }

    public java.lang.String getFromIdentity() {
		return (java.lang.String)HiltonUtility.ckNull(this.fromIdentity).trim();
    }

    public void setFromIdentity(java.lang.String fromIdentity) {
		if (!HiltonUtility.isEmpty(fromIdentity) && fromIdentity.length() > 20) {
			fromIdentity = fromIdentity.substring(0, 20);
		}
		this.fromIdentity = fromIdentity;
    }

    public java.lang.String getToDomain() {
		return (java.lang.String)HiltonUtility.ckNull(this.toDomain).trim();
    }

    public void setToDomain(java.lang.String toDomain) {
		if (!HiltonUtility.isEmpty(toDomain) && toDomain.length() > 20) {
			toDomain = toDomain.substring(0, 20);
		}
		this.toDomain = toDomain;
    }

    public java.lang.String getToIdentity() {
		return (java.lang.String)HiltonUtility.ckNull(this.toIdentity).trim();
    }

    public void setToIdentity(java.lang.String toIdentity) {
		if (!HiltonUtility.isEmpty(toIdentity) && toIdentity.length() > 20) {
			toIdentity = toIdentity.substring(0, 20);
		}
		this.toIdentity = toIdentity;
    }

    public java.lang.String getSenderDomain() {
		return (java.lang.String)HiltonUtility.ckNull(this.senderDomain).trim();
    }

    public void setSenderDomain(java.lang.String senderDomain) {
		if (!HiltonUtility.isEmpty(senderDomain) && senderDomain.length() > 30) {
			senderDomain = senderDomain.substring(0, 30);
		}
		this.senderDomain = senderDomain;
    }

    public java.lang.String getSenderIdentity() {
		return (java.lang.String)HiltonUtility.ckNull(this.senderIdentity).trim();
    }

    public void setSenderIdentity(java.lang.String senderIdentity) {
		if (!HiltonUtility.isEmpty(senderIdentity) && senderIdentity.length() > 30) {
			senderIdentity = senderIdentity.substring(0, 30);
		}
		this.senderIdentity = senderIdentity;
    }

    public java.lang.String getSenderSecret() {
		return (java.lang.String)HiltonUtility.ckNull(this.senderSecret).trim();
    }

    public void setSenderSecret(java.lang.String senderSecret) {
		if (!HiltonUtility.isEmpty(senderSecret) && senderSecret.length() > 30) {
			senderSecret = senderSecret.substring(0, 30);
		}
		this.senderSecret = senderSecret;
    }

    public java.lang.String getSenderAgent() {
		return (java.lang.String)HiltonUtility.ckNull(this.senderAgent).trim();
    }

    public void setSenderAgent(java.lang.String senderAgent) {
		if (!HiltonUtility.isEmpty(senderAgent) && senderAgent.length() > 30) {
			senderAgent = senderAgent.substring(0, 30);
		}
		this.senderAgent = senderAgent;
    }

    public java.lang.String getFld1() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld1).trim();
    }

    public void setFld1(java.lang.String fld1) {
		if (!HiltonUtility.isEmpty(fld1) && fld1.length() > 30) {
			fld1 = fld1.substring(0, 30);
		}
		this.fld1 = fld1;
    }

    public java.lang.String getFld2() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld2).trim();
    }

    public void setFld2(java.lang.String fld2) {
		if (!HiltonUtility.isEmpty(fld2) && fld2.length() > 30) {
			fld2 = fld2.substring(0, 30);
		}
		this.fld2 = fld2;
    }

    public java.lang.String getFld3() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld3).trim();
    }

    public void setFld3(java.lang.String fld3) {
		if (!HiltonUtility.isEmpty(fld3) && fld3.length() > 30) {
			fld3 = fld3.substring(0, 30);
		}
		this.fld3 = fld3;
    }

    public java.lang.String getFld4() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld4).trim();
    }

    public void setFld4(java.lang.String fld4) {
		if (!HiltonUtility.isEmpty(fld4) && fld4.length() > 30) {
			fld4 = fld4.substring(0, 30);
		}
		this.fld4 = fld4;
    }

    public java.lang.String getFld5() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld5).trim();
    }

    public void setFld5(java.lang.String fld5) {
		if (!HiltonUtility.isEmpty(fld5) && fld5.length() > 30) {
			fld5 = fld5.substring(0, 30);
		}
		this.fld5 = fld5;
    }

    public java.lang.String getFld6() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld6).trim();
    }

    public void setFld6(java.lang.String fld6) {
		if (!HiltonUtility.isEmpty(fld6) && fld6.length() > 30) {
			fld6 = fld6.substring(0, 30);
		}
		this.fld6 = fld6;
    }

    public java.lang.String getFld7() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld7).trim();
    }

    public void setFld7(java.lang.String fld7) {
		if (!HiltonUtility.isEmpty(fld7) && fld7.length() > 30) {
			fld7 = fld7.substring(0, 30);
		}
		this.fld7 = fld7;
    }

    public java.lang.String getFld8() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld8).trim();
    }

    public void setFld8(java.lang.String fld8) {
		if (!HiltonUtility.isEmpty(fld8) && fld8.length() > 30) {
			fld8 = fld8.substring(0, 30);
		}
		this.fld8 = fld8;
    }

    public java.lang.String getFld9() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld9).trim();
    }

    public void setFld9(java.lang.String fld9) {
		if (!HiltonUtility.isEmpty(fld9) && fld9.length() > 30) {
			fld9 = fld9.substring(0, 30);
		}
		this.fld9 = fld9;
    }

    public java.lang.String getFld10() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld10).trim();
    }

    public void setFld10(java.lang.String fld10) {
		if (!HiltonUtility.isEmpty(fld10) && fld10.length() > 30) {
			fld10 = fld10.substring(0, 30);
		}
		this.fld10 = fld10;
    }

    public java.lang.String getFld11() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld11).trim();
    }

    public void setFld11(java.lang.String fld11) {
		if (!HiltonUtility.isEmpty(fld11) && fld11.length() > 30) {
			fld11 = fld11.substring(0, 30);
		}
		this.fld11 = fld11;
    }

    public java.lang.String getFld12() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld12).trim();
    }

    public void setFld12(java.lang.String fld12) {
		if (!HiltonUtility.isEmpty(fld12) && fld12.length() > 30) {
			fld12 = fld12.substring(0, 30);
		}
		this.fld12 = fld12;
    }

    public java.lang.String getFld13() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld13).trim();
    }

    public void setFld13(java.lang.String fld13) {
		if (!HiltonUtility.isEmpty(fld13) && fld13.length() > 30) {
			fld13 = fld13.substring(0, 30);
		}
		this.fld13 = fld13;
    }

    public java.lang.String getFld14() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld14).trim();
    }

    public void setFld14(java.lang.String fld14) {
		if (!HiltonUtility.isEmpty(fld14) && fld14.length() > 30) {
			fld14 = fld14.substring(0, 30);
		}
		this.fld14 = fld14;
    }

    public java.lang.String getFld15() {
		return (java.lang.String)HiltonUtility.ckNull(this.fld15).trim();
    }

    public void setFld15(java.lang.String fld15) {
		if (!HiltonUtility.isEmpty(fld15) && fld15.length() > 30) {
			fld15 = fld15.substring(0, 30);
		}
		this.fld15 = fld15;
    }

    public java.lang.String getAuxiliary() {
		return (java.lang.String)HiltonUtility.ckNull(this.auxiliary).trim();
    }

    public void setAuxiliary(java.lang.String auxiliary) {
		if (!HiltonUtility.isEmpty(auxiliary) && auxiliary.length() > 30) {
			auxiliary = auxiliary.substring(0, 30);
		}
		this.auxiliary = auxiliary;
    }

    public java.lang.String getShipTo() {
		return (java.lang.String)HiltonUtility.ckNull(this.shipTo).trim();
    }

    public void setShipTo(java.lang.String shipTo) {
		if (!HiltonUtility.isEmpty(shipTo) && shipTo.length() > 1) {
			shipTo = shipTo.substring(0, 1);
		}
		this.shipTo = shipTo;
    }

    public java.lang.String getUrl() {
		return (java.lang.String)HiltonUtility.ckNull(this.url).trim();
    }

    public void setUrl(java.lang.String url) {
		if (!HiltonUtility.isEmpty(url) && url.length() > 150) {
			url = url.substring(0, 150);
		}
		this.url = url;
    }

    public java.lang.String getDefaultEmail() {
		return (java.lang.String)HiltonUtility.ckNull(this.defaultEmail).trim();
    }

    public void setDefaultEmail(java.lang.String defaultEmail) {
		if (!HiltonUtility.isEmpty(defaultEmail) && defaultEmail.length() > 30) {
			defaultEmail = defaultEmail.substring(0, 30);
		}
		this.defaultEmail = defaultEmail;
    }

    public java.lang.String getShipToEmail() {
		return (java.lang.String)HiltonUtility.ckNull(this.shipToEmail).trim();
    }

    public void setShipToEmail(java.lang.String shipToEmail) {
		if (!HiltonUtility.isEmpty(shipToEmail) && shipToEmail.length() > 30) {
			shipToEmail = shipToEmail.substring(0, 30);
		}
		this.shipToEmail = shipToEmail;
    }

    public java.lang.String getGeneralInfo() {
		return (java.lang.String)HiltonUtility.ckNull(this.generalInfo).trim();
    }

    public void setGeneralInfo(java.lang.String generalInfo) {
		if (!HiltonUtility.isEmpty(generalInfo) && generalInfo.length() > 1) {
			generalInfo = generalInfo.substring(0, 1);
		}
		this.generalInfo = generalInfo;
    }

    public java.lang.String getDefaultDate() {
		return (java.lang.String)HiltonUtility.ckNull(this.defaultDate).trim();
    }

    public void setDefaultDate(java.lang.String defaultDate) {
		if (!HiltonUtility.isEmpty(defaultDate) && defaultDate.length() > 20) {
			defaultDate = defaultDate.substring(0, 20);
		}
		this.defaultDate = defaultDate;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icPunchout", getIcPunchout())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Punchout) ) return false;
        Punchout castOther = (Punchout) other;
        return new EqualsBuilder()
            .append(this.getIcPunchout(), castOther.getIcPunchout())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcPunchout())
            .toHashCode();
    }

    public int fieldNum() {
    	int	num	= 0;

    	if (getFld1() != null)
    		num++;

    	if (getFld2() != null)
    		num++;

    	if (getFld3() != null)
    		num++;

    	if (getFld4() != null)
    		num++;

    	if (getFld5() != null)
    		num++;

    	if (getFld6() != null)
    		num++;

    	if (getFld7() != null)
    		num++;

    	if (getFld8() != null)
    		num++;

    	if (getFld9() != null)
    		num++;

    	if (getFld10() != null)
    		num++;

    	if (getFld11() != null)
    		num++;

    	if (getFld12() != null)
    		num++;

    	if (getFld13() != null)
    		num++;

    	if (getFld14() != null)
    		num++;

    	if (getFld15() != null)
    		num++;

    	return num;
    }

    public String getGeneralFld(int num){
    	String name = "";

    	switch (num){
    	case (1): name = getFld1(); break;
    	case (2): name = getFld2(); break;
    	case (3): name = getFld3(); break;
    	case (4): name = getFld4(); break;
    	case (5): name = getFld5(); break;
    	case (6): name = getFld6(); break;
    	case (7): name = getFld7(); break;
    	case (8): name = getFld8(); break;
    	case (9): name = getFld9(); break;
    	case (10): name = getFld10(); break;
    	case (11): name = getFld11(); break;
    	case (12): name = getFld12(); break;
    	case (13): name = getFld13(); break;
    	case (14): name = getFld14(); break;
    	case (15): name = getFld15(); break;
    	}

    	return name;
    }

}
