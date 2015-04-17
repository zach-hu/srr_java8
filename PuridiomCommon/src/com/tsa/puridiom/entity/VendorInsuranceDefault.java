package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class VendorInsuranceDefault implements Serializable
{
    /** nullable persistent field */
    private String vendorId;

    /** nullable persistent field */
    private String insuranceContact;

    /** nullable persistent field */
    private String insuranceOverride;

    /** nullable persistent field */
    private String insuranceNotes;

    /** nullable persistent field */
    private String coverage1;

    /** nullable persistent field */
    private java.util.Date expires1;

    /** nullable persistent field */
    private String named1;

    /** nullable persistent field */
    private String waiver1;

    /** nullable persistent field */
    private java.math.BigDecimal limit1;

    /** nullable persistent field */
    private String coverage2;

    /** nullable persistent field */
    private java.util.Date expires2;

    /** nullable persistent field */
    private String named2;

    /** nullable persistent field */
    private String waiver2;

    /** nullable persistent field */
    private java.math.BigDecimal limit2;

    /** nullable persistent field */
    private String coverage3;

    /** nullable persistent field */
    private java.util.Date expires3;

    /** nullable persistent field */
    private String named3;

    /** nullable persistent field */
    private String waiver3;

    /** nullable persistent field */
    private java.math.BigDecimal limit3;

    /** nullable persistent field */
    private String coverage4;

    /** nullable persistent field */
    private java.util.Date expires4;

    /** nullable persistent field */
    private String named4;

    /** nullable persistent field */
    private String waiver4;

    /** nullable persistent field */
    private java.math.BigDecimal limit4;

    /** nullable persistent field */
    private String coverage5;

    /** nullable persistent field */
    private java.util.Date expires5;

    /** nullable persistent field */
    private String named5;

    /** nullable persistent field */
    private String waiver5;

    /** nullable persistent field */
    private java.math.BigDecimal limit5;

    /** nullable persistent field */
    private String coverage6;

    /** nullable persistent field */
    private java.util.Date expires6;

    /** nullable persistent field */
    private String named6;

    /** nullable persistent field */
    private String waiver6;

    /** nullable persistent field */
    private java.math.BigDecimal limit6;

    /** nullable persistent field */
    private String insuranceStatus;

    /** nullable persistent field */
    private String complianceContact;

    /** nullable persistent field */
    private String complianceNotes;

    /** nullable persistent field */
    private java.util.Date certifiedDate1;

    /** nullable persistent field */
    private String certStatus1;

    /** nullable persistent field */
    private String certUdf1;

    /** nullable persistent field */
    private java.util.Date certifiedDate2;

    /** nullable persistent field */
    private String certStatus2;

    /** nullable persistent field */
    private String certUdf2;

    /** nullable persistent field */
    private java.util.Date certifiedDate3;

    /** nullable persistent field */
    private String certStatus3;

    /** nullable persistent field */
    private String certUdf3;

    /** nullable persistent field */
    private java.util.Date certifiedDate4;

    /** nullable persistent field */
    private String certStatus4;

    /** nullable persistent field */
    private String certUdf4;

    /** nullable persistent field */
    private java.util.Date certifiedDate5;

    /** nullable persistent field */
    private String certStatus5;

    /** nullable persistent field */
    private String certUdf5;

    /** nullable persistent field */
    private java.util.Date certifiedDate6;

    /** nullable persistent field */
    private String certStatus6;

    /** nullable persistent field */
    private String certUdf6;

    /** nullable persistent field */
    private java.util.Date expiredLetter;

    /** nullable persistent field */
    private java.util.Date notifiedDate;

	/** nullable persistent field */
	private java.util.Date lastChangeDate;

	/** nullable persistent field */
	private String lastChangeBy;

    /** full constructor */
    public VendorInsuranceDefault(String vendorId, String insuranceContact, String insuranceOverride, String insuranceNotes, String coverage1, java.util.Date expires1, String named1, String waiver1, java.math.BigDecimal limit1, String coverage2, java.util.Date expires2, String named2, String waiver2, java.math.BigDecimal limit2, String coverage3, java.util.Date expires3, String named3, String waiver3, java.math.BigDecimal limit3, String coverage4, java.util.Date expires4, String named4, String waiver4, java.math.BigDecimal limit4, String coverage5, java.util.Date expires5, String named5, String waiver5, java.math.BigDecimal limit5, String coverage6, java.util.Date expires6, String named6, String waiver6, java.math.BigDecimal limit6, String insuranceStatus, String complianceContact, String complianceNotes, java.util.Date certifiedDate1, String certStatus1, String certUdf1, java.util.Date certifiedDate2, String certStatus2, String certUdf2, java.util.Date certifiedDate3, String certStatus3, String certUdf3, java.util.Date certifiedDate4, String certStatus4, String certUdf4, java.util.Date certifiedDate5, String certStatus5, String certUdf5, java.util.Date certifiedDate6, String certStatus6, String certUdf6, java.util.Date expiredLetter, java.util.Date notifiedDate, java.util.Date lastChangeDate, String lastChangeBy) {
    	this.vendorId = vendorId;
    	this.insuranceContact = insuranceContact;
        this.insuranceOverride = insuranceOverride;
        this.insuranceNotes = insuranceNotes;
        this.coverage1 = coverage1;
        this.expires1 = expires1;
        this.named1 = named1;
        this.waiver1 = waiver1;
        this.limit1 = limit1;
        this.coverage2 = coverage2;
        this.expires2 = expires2;
        this.named2 = named2;
        this.waiver2 = waiver2;
        this.limit2 = limit2;
        this.coverage3 = coverage3;
        this.expires3 = expires3;
        this.named3 = named3;
        this.waiver3 = waiver3;
        this.limit3 = limit3;
        this.coverage4 = coverage4;
        this.expires4 = expires4;
        this.named4 = named4;
        this.waiver4 = waiver4;
        this.limit4 = limit4;
        this.coverage5 = coverage5;
        this.expires5 = expires5;
        this.named5 = named5;
        this.waiver5 = waiver5;
        this.limit5 = limit5;
        this.coverage6 = coverage6;
        this.expires6 = expires6;
        this.named6 = named6;
        this.waiver6 = waiver6;
        this.limit6 = limit6;
        this.insuranceStatus = insuranceStatus;
        this.complianceContact = complianceContact;
        this.complianceNotes = complianceNotes;
        this.certifiedDate1 = certifiedDate1;
        this.certStatus1 = certStatus1;
        this.certUdf1 = certUdf1;
        this.certifiedDate2 = certifiedDate2;
        this.certStatus2 = certStatus2;
        this.certUdf2 = certUdf2;
        this.certifiedDate3 = certifiedDate3;
        this.certStatus3 = certStatus3;
        this.certUdf3 = certUdf3;
        this.certifiedDate4 = certifiedDate4;
        this.certStatus4 = certStatus4;
        this.certUdf4 = certUdf4;
        this.certifiedDate5 = certifiedDate5;
        this.certStatus5 = certStatus5;
        this.certUdf5 = certUdf5;
        this.certifiedDate6 = certifiedDate6;
        this.certStatus6 = certStatus6;
        this.certUdf6 = certUdf6;
        this.expiredLetter = expiredLetter;
        this.notifiedDate = notifiedDate;
        this.lastChangeDate = lastChangeDate;
        this.lastChangeBy = lastChangeBy;
    }

    /** default constructor */
    public VendorInsuranceDefault() {
    }

    /** minimal constructor */
    public VendorInsuranceDefault(java.lang.String vendorId) {
        this.vendorId = vendorId;
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

    public java.lang.String getInsuranceContact() {
		return (java.lang.String)HiltonUtility.ckNull(this.insuranceContact).trim();
    }

    public void setInsuranceContact(java.lang.String insuranceContact) {
		if (!HiltonUtility.isEmpty(insuranceContact) && insuranceContact.length() > 15) {
			insuranceContact = insuranceContact.substring(0, 15);
		}
		this.insuranceContact = insuranceContact;
    }

    public java.lang.String getInsuranceOverride() {
		return (java.lang.String)HiltonUtility.ckNull(this.insuranceOverride).trim();
    }

    public void setInsuranceOverride(java.lang.String insuranceOverride) {
		if (!HiltonUtility.isEmpty(insuranceOverride) && insuranceOverride.length() > 1) {
			insuranceOverride = insuranceOverride.substring(0, 1);
		}
		this.insuranceOverride = insuranceOverride;
    }

    public java.lang.String getInsuranceNotes() {
		return (java.lang.String)HiltonUtility.ckNull(this.insuranceNotes).trim();
    }

    public void setInsuranceNotes(java.lang.String insuranceNotes) {
		if (!HiltonUtility.isEmpty(insuranceNotes) && insuranceNotes.length() > 255) {
			insuranceNotes = insuranceNotes.substring(0, 255);
		}
		this.insuranceNotes = insuranceNotes;
    }

    public java.lang.String getCoverage1() {
		return (java.lang.String)HiltonUtility.ckNull(this.coverage1).trim();
    }

    public void setCoverage1(java.lang.String coverage1) {
		if (!HiltonUtility.isEmpty(coverage1) && coverage1.length() > 1) {
			coverage1 = coverage1.substring(0, 1);
		}
		this.coverage1 = coverage1;
    }

    public java.util.Date getExpires1() {
		return this.expires1;
    }

    public void setExpires1(java.util.Date expires1) {
        this.expires1 = expires1;
    }

    public java.lang.String getNamed1() {
		return (java.lang.String)HiltonUtility.ckNull(this.named1).trim();
    }

    public void setNamed1(java.lang.String named1) {
		if (!HiltonUtility.isEmpty(named1) && named1.length() > 1) {
			named1 = named1.substring(0, 1);
		}
		this.named1 = named1;
    }

    public java.lang.String getWaiver1() {
		return (java.lang.String)HiltonUtility.ckNull(this.waiver1).trim();
    }

    public void setWaiver1(java.lang.String waiver1) {
		if (!HiltonUtility.isEmpty(waiver1) && waiver1.length() > 1) {
			waiver1 = waiver1.substring(0, 1);
		}
		this.waiver1 = waiver1;
    }

    public java.math.BigDecimal getLimit1() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.limit1);
    }

    public void setLimit1(java.math.BigDecimal limit1) {
        this.limit1 = limit1;
    }

    public java.lang.String getCoverage2() {
		return (java.lang.String)HiltonUtility.ckNull(this.coverage2).trim();
    }

    public void setCoverage2(java.lang.String coverage2) {
		if (!HiltonUtility.isEmpty(coverage2) && coverage2.length() > 1) {
			coverage2 = coverage2.substring(0, 1);
		}
		this.coverage2 = coverage2;
    }

    public java.util.Date getExpires2() {
		return this.expires2;
    }

    public void setExpires2(java.util.Date expires2) {
        this.expires2 = expires2;
    }

    public java.lang.String getNamed2() {
		return (java.lang.String)HiltonUtility.ckNull(this.named2).trim();
    }

    public void setNamed2(java.lang.String named2) {
		if (!HiltonUtility.isEmpty(named2) && named2.length() > 1) {
			named2 = named2.substring(0, 1);
		}
		this.named2 = named2;
    }

    public java.lang.String getWaiver2() {
		return (java.lang.String)HiltonUtility.ckNull(this.waiver2).trim();
    }

    public void setWaiver2(java.lang.String waiver2) {
		if (!HiltonUtility.isEmpty(waiver2) && waiver2.length() > 1) {
			waiver2 = waiver2.substring(0, 1);
		}
		this.waiver2 = waiver2;
    }

    public java.math.BigDecimal getLimit2() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.limit2);
    }

    public void setLimit2(java.math.BigDecimal limit2) {
        this.limit2 = limit2;
    }

    public java.lang.String getCoverage3() {
		return (java.lang.String)HiltonUtility.ckNull(this.coverage3).trim();
    }

    public void setCoverage3(java.lang.String coverage3) {
		if (!HiltonUtility.isEmpty(coverage3) && coverage3.length() > 1) {
			coverage3 = coverage3.substring(0, 1);
		}
		this.coverage3 = coverage3;
    }

    public java.util.Date getExpires3() {
		return this.expires3;
    }

    public void setExpires3(java.util.Date expires3) {
        this.expires3 = expires3;
    }

    public java.lang.String getNamed3() {
		return (java.lang.String)HiltonUtility.ckNull(this.named3).trim();
    }

    public void setNamed3(java.lang.String named3) {
		if (!HiltonUtility.isEmpty(named3) && named3.length() > 1) {
			named3 = named3.substring(0, 1);
		}
		this.named3 = named3;
    }

    public java.lang.String getWaiver3() {
		return (java.lang.String)HiltonUtility.ckNull(this.waiver3).trim();
    }

    public void setWaiver3(java.lang.String waiver3) {
		if (!HiltonUtility.isEmpty(waiver3) && waiver3.length() > 1) {
			waiver3 = waiver3.substring(0, 1);
		}
		this.waiver3 = waiver3;
    }

    public java.math.BigDecimal getLimit3() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.limit3);
    }

    public void setLimit3(java.math.BigDecimal limit3) {
        this.limit3 = limit3;
    }

    public java.lang.String getCoverage4() {
		return (java.lang.String)HiltonUtility.ckNull(this.coverage4).trim();
    }

    public void setCoverage4(java.lang.String coverage4) {
		if (!HiltonUtility.isEmpty(coverage4) && coverage4.length() > 1) {
			coverage4 = coverage4.substring(0, 1);
		}
		this.coverage4 = coverage4;
    }

    public java.util.Date getExpires4() {
		return this.expires4;
    }

    public void setExpires4(java.util.Date expires4) {
        this.expires4 = expires4;
    }

    public java.lang.String getNamed4() {
		return (java.lang.String)HiltonUtility.ckNull(this.named4).trim();
    }

    public void setNamed4(java.lang.String named4) {
		if (!HiltonUtility.isEmpty(named4) && named4.length() > 1) {
			named4 = named4.substring(0, 1);
		}
		this.named4 = named4;
    }

    public java.lang.String getWaiver4() {
		return (java.lang.String)HiltonUtility.ckNull(this.waiver4).trim();
    }

    public void setWaiver4(java.lang.String waiver4) {
		if (!HiltonUtility.isEmpty(waiver4) && waiver4.length() > 1) {
			waiver4 = waiver4.substring(0, 1);
		}
		this.waiver4 = waiver4;
    }

    public java.math.BigDecimal getLimit4() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.limit4);
    }

    public void setLimit4(java.math.BigDecimal limit4) {
        this.limit4 = limit4;
    }

    public java.lang.String getCoverage5() {
		return (java.lang.String)HiltonUtility.ckNull(this.coverage5).trim();
    }

    public void setCoverage5(java.lang.String coverage5) {
		if (!HiltonUtility.isEmpty(coverage5) && coverage5.length() > 1) {
			coverage5 = coverage5.substring(0, 1);
		}
		this.coverage5 = coverage5;
    }

    public java.util.Date getExpires5() {
		return this.expires5;
    }

    public void setExpires5(java.util.Date expires5) {
        this.expires5 = expires5;
    }

    public java.lang.String getNamed5() {
		return (java.lang.String)HiltonUtility.ckNull(this.named5).trim();
    }

    public void setNamed5(java.lang.String named5) {
		if (!HiltonUtility.isEmpty(named5) && named5.length() > 1) {
			named5 = named5.substring(0, 1);
		}
		this.named5 = named5;
    }

    public java.lang.String getWaiver5() {
		return (java.lang.String)HiltonUtility.ckNull(this.waiver5).trim();
    }

    public void setWaiver5(java.lang.String waiver5) {
		if (!HiltonUtility.isEmpty(waiver5) && waiver5.length() > 1) {
			waiver5 = waiver5.substring(0, 1);
		}
		this.waiver5 = waiver5;
    }

    public java.math.BigDecimal getLimit5() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.limit5);
    }

    public void setLimit5(java.math.BigDecimal limit5) {
        this.limit5 = limit5;
    }

    public java.lang.String getCoverage6() {
		return (java.lang.String)HiltonUtility.ckNull(this.coverage6).trim();
    }

    public void setCoverage6(java.lang.String coverage6) {
		if (!HiltonUtility.isEmpty(coverage6) && coverage6.length() > 1) {
			coverage6 = coverage6.substring(0, 1);
		}
		this.coverage6 = coverage6;
    }

    public java.util.Date getExpires6() {
		return this.expires6;
    }

    public void setExpires6(java.util.Date expires6) {
        this.expires6 = expires6;
    }

    public java.lang.String getNamed6() {
		return (java.lang.String)HiltonUtility.ckNull(this.named6).trim();
    }

    public void setNamed6(java.lang.String named6) {
		if (!HiltonUtility.isEmpty(named6) && named6.length() > 1) {
			named6 = named6.substring(0, 1);
		}
		this.named6 = named6;
    }

    public java.lang.String getWaiver6() {
		return (java.lang.String)HiltonUtility.ckNull(this.waiver6).trim();
    }

    public void setWaiver6(java.lang.String waiver6) {
		if (!HiltonUtility.isEmpty(waiver6) && waiver6.length() > 1) {
			waiver6 = waiver6.substring(0, 1);
		}
		this.waiver6 = waiver6;
    }

    public java.math.BigDecimal getLimit6() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.limit6);
    }

    public void setLimit6(java.math.BigDecimal limit6) {
        this.limit6 = limit6;
    }

    public java.lang.String getInsuranceStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.insuranceStatus).trim();
    }

    public void setInsuranceStatus(java.lang.String insuranceStatus) {
		if (!HiltonUtility.isEmpty(insuranceStatus) && insuranceStatus.length() > 1) {
			insuranceStatus = insuranceStatus.substring(0, 1);
		}
		this.insuranceStatus = insuranceStatus;
    }

    public java.lang.String getComplianceContact() {
		return (java.lang.String)HiltonUtility.ckNull(this.complianceContact).trim();
    }

    public void setComplianceContact(java.lang.String complianceContact) {
		if (!HiltonUtility.isEmpty(complianceContact) && complianceContact.length() > 15) {
			complianceContact = complianceContact.substring(0, 15);
		}
		this.complianceContact = complianceContact;
    }

    public java.lang.String getComplianceNotes() {
		return (java.lang.String)HiltonUtility.ckNull(this.complianceNotes).trim();
    }

    public void setComplianceNotes(java.lang.String complianceNotes) {
		if (!HiltonUtility.isEmpty(complianceNotes) && complianceNotes.length() > 255) {
			complianceNotes = complianceNotes.substring(0, 255);
		}
		this.complianceNotes = complianceNotes;
    }

    public java.util.Date getCertifiedDate1() {
		return this.certifiedDate1;
    }

    public void setCertifiedDate1(java.util.Date certifiedDate1) {
        this.certifiedDate1 = certifiedDate1;
    }

    public java.lang.String getCertStatus1() {
		return (java.lang.String)HiltonUtility.ckNull(this.certStatus1).trim();
    }

    public void setCertStatus1(java.lang.String certStatus1) {
		if (!HiltonUtility.isEmpty(certStatus1) && certStatus1.length() > 1) {
			certStatus1 = certStatus1.substring(0, 1);
		}
		this.certStatus1 = certStatus1;
    }

    public java.lang.String getCertUdf1() {
		return (java.lang.String)HiltonUtility.ckNull(this.certUdf1).trim();
    }

    public void setCertUdf1(java.lang.String certUdf1) {
		if (!HiltonUtility.isEmpty(certUdf1) && certUdf1.length() > 15) {
			certUdf1 = certUdf1.substring(0, 15);
		}
		this.certUdf1 = certUdf1;
    }

    public java.util.Date getCertifiedDate2() {
		return this.certifiedDate2;
    }

    public void setCertifiedDate2(java.util.Date certifiedDate2) {
        this.certifiedDate2 = certifiedDate2;
    }

    public java.lang.String getCertStatus2() {
		return (java.lang.String)HiltonUtility.ckNull(this.certStatus2).trim();
    }

    public void setCertStatus2(java.lang.String certStatus2) {
		if (!HiltonUtility.isEmpty(certStatus2) && certStatus2.length() > 1) {
			certStatus2 = certStatus2.substring(0, 1);
		}
		this.certStatus2 = certStatus2;
    }

    public java.lang.String getCertUdf2() {
		return (java.lang.String)HiltonUtility.ckNull(this.certUdf2).trim();
    }

    public void setCertUdf2(java.lang.String certUdf2) {
		if (!HiltonUtility.isEmpty(certUdf2) && certUdf2.length() > 15) {
			certUdf2 = certUdf2.substring(0, 15);
		}
		this.certUdf2 = certUdf2;
    }

    public java.util.Date getCertifiedDate3() {
		return this.certifiedDate3;
    }

    public void setCertifiedDate3(java.util.Date certifiedDate3) {
        this.certifiedDate3 = certifiedDate3;
    }

    public java.lang.String getCertStatus3() {
		return (java.lang.String)HiltonUtility.ckNull(this.certStatus3).trim();
    }

    public void setCertStatus3(java.lang.String certStatus3) {
		if (!HiltonUtility.isEmpty(certStatus3) && certStatus3.length() > 1) {
			certStatus3 = certStatus3.substring(0, 1);
		}
		this.certStatus3 = certStatus3;
    }

    public java.lang.String getCertUdf3() {
		return (java.lang.String)HiltonUtility.ckNull(this.certUdf3).trim();
    }

    public void setCertUdf3(java.lang.String certUdf3) {
		if (!HiltonUtility.isEmpty(certUdf3) && certUdf3.length() > 15) {
			certUdf3 = certUdf3.substring(0, 15);
		}
		this.certUdf3 = certUdf3;
    }

    public java.util.Date getCertifiedDate4() {
		return this.certifiedDate4;
    }

    public void setCertifiedDate4(java.util.Date certifiedDate4) {
        this.certifiedDate4 = certifiedDate4;
    }

    public java.lang.String getCertStatus4() {
		return (java.lang.String)HiltonUtility.ckNull(this.certStatus4).trim();
    }

    public void setCertStatus4(java.lang.String certStatus4) {
		if (!HiltonUtility.isEmpty(certStatus4) && certStatus4.length() > 1) {
			certStatus4 = certStatus4.substring(0, 1);
		}
		this.certStatus4 = certStatus4;
    }

    public java.lang.String getCertUdf4() {
		return (java.lang.String)HiltonUtility.ckNull(this.certUdf4).trim();
    }

    public void setCertUdf4(java.lang.String certUdf4) {
		if (!HiltonUtility.isEmpty(certUdf4) && certUdf4.length() > 15) {
			certUdf4 = certUdf4.substring(0, 15);
		}
		this.certUdf4 = certUdf4;
    }

    public java.util.Date getCertifiedDate5() {
		return this.certifiedDate5;
    }

    public void setCertifiedDate5(java.util.Date certifiedDate5) {
        this.certifiedDate5 = certifiedDate5;
    }

    public java.lang.String getCertStatus5() {
		return (java.lang.String)HiltonUtility.ckNull(this.certStatus5).trim();
    }

    public void setCertStatus5(java.lang.String certStatus5) {
		if (!HiltonUtility.isEmpty(certStatus5) && certStatus5.length() > 1) {
			certStatus5 = certStatus5.substring(0, 1);
		}
		this.certStatus5 = certStatus5;
    }

    public java.lang.String getCertUdf5() {
		return (java.lang.String)HiltonUtility.ckNull(this.certUdf5).trim();
    }

    public void setCertUdf5(java.lang.String certUdf5) {
		if (!HiltonUtility.isEmpty(certUdf5) && certUdf5.length() > 15) {
			certUdf5 = certUdf5.substring(0, 15);
		}
		this.certUdf5 = certUdf5;
    }

    public java.util.Date getCertifiedDate6() {
        return this.certifiedDate6;
    }

    public void setCertifiedDate6(java.util.Date certifiedDate6) {
        this.certifiedDate6 = certifiedDate6;
    }

    public java.lang.String getCertStatus6() {
        return (java.lang.String)HiltonUtility.ckNull(this.certStatus6).trim();
    }

    public void setCertStatus6(java.lang.String certStatus6) {
        if (!HiltonUtility.isEmpty(certStatus6) && certStatus6.length() > 1) {
            certStatus6 = certStatus6.substring(0, 1);
        }
        this.certStatus6 = certStatus6;
    }

    public java.lang.String getCertUdf6() {
        return (java.lang.String)HiltonUtility.ckNull(this.certUdf6).trim();
    }

    public void setCertUdf6(java.lang.String certUdf6) {
        if (!HiltonUtility.isEmpty(certUdf6) && certUdf6.length() > 15) {
            certUdf6 = certUdf6.substring(0, 15);
        }
        this.certUdf6 = certUdf6;
    }

    public java.util.Date getExpiredLetter() {
		return this.expiredLetter;
    }

    public void setExpiredLetter(java.util.Date expiredLetter) {
        this.expiredLetter = expiredLetter;
    }

    public java.util.Date getNotifiedDate() {
		return this.notifiedDate;
    }

    public void setNotifiedDate(java.util.Date notifiedDate) {
        this.notifiedDate = notifiedDate;
    }

    public java.util.Date getLastChangeDate() {
		return this.lastChangeDate;
	}

	public void setLastChangeDate(java.util.Date lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}

	public java.lang.String getLastChangeBy() {
		return (java.lang.String)HiltonUtility.ckNull(this.lastChangeBy).trim();
	}

	public void setLastChangeBy(java.lang.String lastChangeBy) {
		if (!HiltonUtility.isEmpty(lastChangeBy) && lastChangeBy.length() > 15) {
			lastChangeBy = lastChangeBy.substring(0, 15);
		}
		this.lastChangeBy = lastChangeBy;
	}

    public String toString() {
        return new ToStringBuilder(this)
            .append("vendorId", getVendorId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof VendorInsuranceDefault) ) return false;
        VendorInsuranceDefault castOther = (VendorInsuranceDefault) other;
        return new EqualsBuilder()
            .append(this.getVendorId(), castOther.getVendorId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getVendorId())
            .toHashCode();
    }
}
