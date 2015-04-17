package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class VendorInsurance implements Serializable {

    /** identifier field */
	private java.math.BigDecimal icPoHeader;

    private String contNumber;

    /** nullable persistent field */
    private String vendorId;

    /** nullable persistent field */
    private String contType;

    /** nullable persistent field */
    private java.util.Date contEffective;

    /** nullable persistent field */
    private java.util.Date contExpires;

    /** nullable persistent field */
    private String contAdmin;

    /** nullable persistent field */
    private String contModFlag;

    /** nullable persistent field */
    private String contModUserid;

    /** nullable persistent field */
    private java.util.Date contModDate;

    /** nullable persistent field */
    private String contOwner;

    /** nullable persistent field */
    private String contStatus;

    /** nullable persistent field */
    private java.util.Date contRequestDate;

    /** nullable persistent field */
    private String contDescription;

    /** nullable persistent field */
    private String contUdf1;

    /** nullable persistent field */
    private String contUdf2;

    /** nullable persistent field */
    private String contUdf3;

    /** nullable persistent field */
    private String contUdf4;

    /** nullable persistent field */
    private String contUdf5;

    /** nullable persistent field */
    private String insuranceStatus;

    /** nullable persistent field */
    private String insuranceContact;

    /** nullable persistent field */
    private java.util.Date expiredLetter;

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
    private java.util.Date certifiedDate1;

    /** nullable persistent field */
    private String certStatus1;

    /** nullable persistent field */
    private java.util.Date certifiedDate2;

    /** nullable persistent field */
    private String certStatus2;

    /** nullable persistent field */
    private java.util.Date certifiedDate3;

    /** nullable persistent field */
    private String certStatus3;

    /** nullable persistent field */
    private java.util.Date certifiedDate4;

    /** nullable persistent field */
    private String certStatus4;

    /** nullable persistent field */
    private java.util.Date certifiedDate5;

    /** nullable persistent field */
    private String certStatus5;

    /** nullable persistent field */
    private String certUdf1;

    /** nullable persistent field */
    private String certUdf2;

    /** nullable persistent field */
    private String certUdf3;

    /** nullable persistent field */
    private String certUdf4;

    /** nullable persistent field */
    private String certUdf5;

    /** nullable persistent field */
    private String certContact;

    /** nullable persistent field */
    private String contractNotes;

    /** nullable persistent field */
    private String complianceNotes;

    /** nullable persistent field */
    private String insuranceNotes;

    /** nullable persistent field */
    private java.util.Date notifiedDate;

    /** nullable persistent field */
    private java.math.BigDecimal dollarValue;

    /** nullable persistent field */
    private java.util.Date certifiedDate6;

    /** nullable persistent field */
    private String certStatus6;

    /** nullable persistent field */
    private String certUdf6;

    private java.util.Date certifiedDate7;

    /** nullable persistent field */
    private String certStatus7;

    /** nullable persistent field */
    private String certUdf7;

    /** nullable persistent field */
    private java.util.Date dateExpires1;

    /** nullable persistent field */
    private java.util.Date dateExpires2;

    /** nullable persistent field */
    private java.util.Date dateExpires3;

    /** nullable persistent field */
    private java.util.Date dateExpires4;

    /** nullable persistent field */
    private java.util.Date dateExpires5;

    /** nullable persistent field */
    private java.util.Date dateExpires6;

    /** nullable persistent field */
    private java.util.Date dateExpires7;

    /** full constructor */
    public VendorInsurance(java.math.BigDecimal icPoHeader, java.lang.String contNumber, java.lang.String vendorId, java.lang.String contType, java.util.Date contEffective, java.util.Date contExpires, java.lang.String contAdmin, java.lang.String contModFlag, java.lang.String contModUserid, java.util.Date contModDate, java.lang.String contOwner, java.lang.String contStatus, java.util.Date contRequestDate, java.lang.String contDescription, java.lang.String contUdf1, java.lang.String contUdf2, java.lang.String contUdf3, java.lang.String contUdf4, java.lang.String contUdf5, java.lang.String insuranceStatus, java.lang.String insuranceContact, java.util.Date expiredLetter, java.lang.String coverage1, java.util.Date expires1, java.lang.String named1, java.lang.String waiver1, java.math.BigDecimal limit1, java.lang.String coverage2, java.util.Date expires2, java.lang.String named2, java.lang.String waiver2, java.math.BigDecimal limit2, java.lang.String coverage3, java.util.Date expires3, java.lang.String named3, java.lang.String waiver3, java.math.BigDecimal limit3, java.lang.String coverage4, java.util.Date expires4, java.lang.String named4, java.lang.String waiver4, java.math.BigDecimal limit4, java.lang.String coverage5, java.util.Date expires5, java.lang.String named5, java.lang.String waiver5, java.math.BigDecimal limit5, java.lang.String coverage6, java.util.Date expires6, java.lang.String named6, java.lang.String waiver6, java.math.BigDecimal limit6, java.util.Date certifiedDate1, java.lang.String certStatus1, java.util.Date certifiedDate2, java.lang.String certStatus2, java.util.Date certifiedDate3, java.lang.String certStatus3, java.util.Date certifiedDate4, java.lang.String certStatus4, java.util.Date certifiedDate5, java.lang.String certStatus5, java.lang.String certUdf1, java.lang.String certUdf2, java.lang.String certUdf3, java.lang.String certUdf4, java.lang.String certUdf5, java.lang.String certContact, java.lang.String contractNotes, java.lang.String complianceNotes, java.lang.String insuranceNotes, java.util.Date notifiedDate, java.math.BigDecimal dollarValue, java.util.Date certifiedDate6, java.lang.String certStatus6, java.lang.String certUdf6, java.util.Date certifiedDate7, java.lang.String certStatus7, java.lang.String certUdf7, java.util.Date dateExpires1, java.util.Date dateExpires2, java.util.Date dateExpires3, java.util.Date dateExpires4, java.util.Date dateExpires5, java.util.Date dateExpires6, java.util.Date dateExpires7) {
    	this.icPoHeader = icPoHeader;
    	this.vendorId = vendorId;
        this.contNumber = contNumber;
        this.contType = contType;
        this.contEffective = contEffective;
        this.contExpires = contExpires;
        this.contAdmin = contAdmin;
        this.contModFlag = contModFlag;
        this.contModUserid = contModUserid;
        this.contModDate = contModDate;
        this.contOwner = contOwner;
        this.contStatus = contStatus;
        this.contRequestDate = contRequestDate;
        this.contDescription = contDescription;
        this.contUdf1 = contUdf1;
        this.contUdf2 = contUdf2;
        this.contUdf3 = contUdf3;
        this.contUdf4 = contUdf4;
        this.contUdf5 = contUdf5;
        this.insuranceStatus = insuranceStatus;
        this.insuranceContact = insuranceContact;
        this.expiredLetter = expiredLetter;
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
        this.certifiedDate1 = certifiedDate1;
        this.certStatus1 = certStatus1;
        this.certifiedDate2 = certifiedDate2;
        this.certStatus2 = certStatus2;
        this.certifiedDate3 = certifiedDate3;
        this.certStatus3 = certStatus3;
        this.certifiedDate4 = certifiedDate4;
        this.certStatus4 = certStatus4;
        this.certifiedDate5 = certifiedDate5;
        this.certStatus5 = certStatus5;
        this.certUdf1 = certUdf1;
        this.certUdf2 = certUdf2;
        this.certUdf3 = certUdf3;
        this.certUdf4 = certUdf4;
        this.certUdf5 = certUdf5;
        this.certContact = certContact;
        this.contractNotes = contractNotes;
        this.complianceNotes = complianceNotes;
        this.insuranceNotes = insuranceNotes;
        this.notifiedDate = notifiedDate;
        this.dollarValue = dollarValue;
        this.certifiedDate6 = certifiedDate6;
        this.certStatus6 = certStatus6;
        this.certUdf6 = certUdf6;
        this.certifiedDate7 = certifiedDate7;
        this.certStatus7 = certStatus7;
        this.certUdf7 = certUdf7;
        this.dateExpires1 = dateExpires1;
        this.dateExpires2 = dateExpires2;
        this.dateExpires3 = dateExpires3;
        this.dateExpires4 = dateExpires4;
        this.dateExpires5 = dateExpires5;
        this.dateExpires6 = dateExpires6;
        this.dateExpires7 = dateExpires7;
    }

    /** default constructor */
    public VendorInsurance() {
    }

    /** minimal constructor */
    public VendorInsurance(java.math.BigDecimal icPoHeader) {
        this.icPoHeader = icPoHeader;
    }

    public java.math.BigDecimal getIcPoHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icPoHeader);
    }

    public void setIcPoHeader(java.math.BigDecimal icPoHeader) {
        this.icPoHeader = icPoHeader;
    }

    public java.lang.String getContNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.contNumber).trim();
    }

    public void setContNumber(java.lang.String contNumber) {
		if (!HiltonUtility.isEmpty(contNumber) && contNumber.length() > 20) {
			contNumber = contNumber.substring(0, 20);
		}
		this.contNumber = contNumber;
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

    public java.lang.String getContType() {
		return (java.lang.String)HiltonUtility.ckNull(this.contType).trim();
    }

    public void setContType(java.lang.String contType) {
		if (!HiltonUtility.isEmpty(contType) && contType.length() > 15) {
			contType = contType.substring(0, 15);
		}
		this.contType = contType;
    }

    public java.util.Date getContEffective() {
		return this.contEffective;
//		return HiltonUtility.ckNull(this.contEffective);
//		return (java.sql.Date)HiltonUtility.ckNull(this.contEffective);
    }

    public void setContEffective(java.util.Date contEffective) {
        this.contEffective = contEffective;
    }

    public java.util.Date getContExpires() {
		return this.contExpires;
//		return HiltonUtility.ckNull(this.contExpires);
//		return (java.sql.Date)HiltonUtility.ckNull(this.contExpires);
    }

    public void setContExpires(java.util.Date contExpires) {
        this.contExpires = contExpires;
    }

    public java.lang.String getContAdmin() {
		return (java.lang.String)HiltonUtility.ckNull(this.contAdmin).trim();
    }

    public void setContAdmin(java.lang.String contAdmin) {
		if (!HiltonUtility.isEmpty(contAdmin) && contAdmin.length() > 15) {
			contAdmin = contAdmin.substring(0, 15);
		}
		this.contAdmin = contAdmin;
    }

    public java.lang.String getContModFlag() {
		return (java.lang.String)HiltonUtility.ckNull(this.contModFlag).trim();
    }

    public void setContModFlag(java.lang.String contModFlag) {
		if (!HiltonUtility.isEmpty(contModFlag) && contModFlag.length() > 1) {
			contModFlag = contModFlag.substring(0, 1);
		}
		this.contModFlag = contModFlag;
    }

    public java.lang.String getContModUserid() {
		return (java.lang.String)HiltonUtility.ckNull(this.contModUserid).trim();
    }

    public void setContModUserid(java.lang.String contModUserid) {
		if (!HiltonUtility.isEmpty(contModUserid) && contModUserid.length() > 15) {
			contModUserid = contModUserid.substring(0, 15);
		}
		this.contModUserid = contModUserid;
    }

    public java.util.Date getContModDate() {
		return this.contModDate;
//		return HiltonUtility.ckNull(this.contModDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.contModDate);
    }

    public void setContModDate(java.util.Date contModDate) {
        this.contModDate = contModDate;
    }

    public java.lang.String getContOwner() {
		return (java.lang.String)HiltonUtility.ckNull(this.contOwner).trim();
    }

    public void setContOwner(java.lang.String contOwner) {
		if (!HiltonUtility.isEmpty(contOwner) && contOwner.length() > 15) {
			contOwner = contOwner.substring(0, 15);
		}
		this.contOwner = contOwner;
    }

    public java.lang.String getContStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.contStatus).trim();
    }

    public void setContStatus(java.lang.String contStatus) {
		if (!HiltonUtility.isEmpty(contStatus) && contStatus.length() > 1) {
			contStatus = contStatus.substring(0, 1);
		}
		this.contStatus = contStatus;
    }

    public java.util.Date getContRequestDate() {
		return this.contRequestDate;
//		return HiltonUtility.ckNull(this.contRequestDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.contRequestDate);
    }

    public void setContRequestDate(java.util.Date contRequestDate) {
        this.contRequestDate = contRequestDate;
    }

    public java.lang.String getContDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.contDescription).trim();
    }

    public void setContDescription(java.lang.String contDescription) {
		if (!HiltonUtility.isEmpty(contDescription) && contDescription.length() > 30) {
			contDescription = contDescription.substring(0, 30);
		}
		this.contDescription = contDescription;
    }

    public java.lang.String getContUdf1() {
		return (java.lang.String)HiltonUtility.ckNull(this.contUdf1).trim();
    }

    public void setContUdf1(java.lang.String contUdf1) {
        this.contUdf1 = contUdf1;
    }

    public java.lang.String getContUdf2() {
		return (java.lang.String)HiltonUtility.ckNull(this.contUdf2).trim();
    }

    public void setContUdf2(java.lang.String contUdf2) {
        this.contUdf2 = contUdf2;
    }

    public java.lang.String getContUdf3() {
		return (java.lang.String)HiltonUtility.ckNull(this.contUdf3).trim();
    }

    public void setContUdf3(java.lang.String contUdf3) {
        this.contUdf3 = contUdf3;
    }

    public java.lang.String getContUdf4() {
		return (java.lang.String)HiltonUtility.ckNull(this.contUdf4).trim();
    }

    public void setContUdf4(java.lang.String contUdf4) {
        this.contUdf4 = contUdf4;
    }

    public java.lang.String getContUdf5() {
		return (java.lang.String)HiltonUtility.ckNull(this.contUdf5).trim();
    }

    public void setContUdf5(java.lang.String contUdf5) {
        this.contUdf5 = contUdf5;
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

    public java.lang.String getInsuranceContact() {
		return (java.lang.String)HiltonUtility.ckNull(this.insuranceContact).trim();
    }

    public void setInsuranceContact(java.lang.String insuranceContact) {
		if (!HiltonUtility.isEmpty(insuranceContact) && insuranceContact.length() > 30) {
			insuranceContact = insuranceContact.substring(0, 30);
		}
		this.insuranceContact = insuranceContact;
    }

    public java.util.Date getExpiredLetter() {
		return this.expiredLetter;
//		return HiltonUtility.ckNull(this.expiredLetter);
//		return (java.sql.Date)HiltonUtility.ckNull(this.expiredLetter);
    }

    public void setExpiredLetter(java.util.Date expiredLetter) {
        this.expiredLetter = expiredLetter;
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
//		return HiltonUtility.ckNull(this.expires1);
//		return (java.sql.Date)HiltonUtility.ckNull(this.expires1);
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
//		return HiltonUtility.ckNull(this.expires2);
//		return (java.sql.Date)HiltonUtility.ckNull(this.expires2);
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
//		return HiltonUtility.ckNull(this.expires3);
//		return (java.sql.Date)HiltonUtility.ckNull(this.expires3);
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
//		return HiltonUtility.ckNull(this.expires4);
//		return (java.sql.Date)HiltonUtility.ckNull(this.expires4);
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
//		return HiltonUtility.ckNull(this.expires5);
//		return (java.sql.Date)HiltonUtility.ckNull(this.expires5);
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
//		return HiltonUtility.ckNull(this.expires5);
//		return (java.sql.Date)HiltonUtility.ckNull(this.expires5);
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

    public java.util.Date getCertifiedDate1() {
		return this.certifiedDate1;
//		return HiltonUtility.ckNull(this.certifiedDate1);
//		return (java.sql.Date)HiltonUtility.ckNull(this.certifiedDate1);
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

    public java.util.Date getCertifiedDate2() {
		return this.certifiedDate2;
//		return HiltonUtility.ckNull(this.certifiedDate2);
//		return (java.sql.Date)HiltonUtility.ckNull(this.certifiedDate2);
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

    public java.util.Date getCertifiedDate3() {
		return this.certifiedDate3;
//		return HiltonUtility.ckNull(this.certifiedDate3);
//		return (java.sql.Date)HiltonUtility.ckNull(this.certifiedDate3);
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

    public java.util.Date getCertifiedDate4() {
		return this.certifiedDate4;
//		return HiltonUtility.ckNull(this.certifiedDate4);
//		return (java.sql.Date)HiltonUtility.ckNull(this.certifiedDate4);
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

    public java.util.Date getCertifiedDate5() {
		return this.certifiedDate5;
//		return HiltonUtility.ckNull(this.certifiedDate5);
//		return (java.sql.Date)HiltonUtility.ckNull(this.certifiedDate5);
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

    public java.lang.String getCertUdf1() {
		return (java.lang.String)HiltonUtility.ckNull(this.certUdf1).trim();
    }

    public void setCertUdf1(java.lang.String certUdf1) {
		if (!HiltonUtility.isEmpty(certUdf1) && certUdf1.length() > 15) {
			certUdf1 = certUdf1.substring(0, 15);
		}
		this.certUdf1 = certUdf1;
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

    public java.lang.String getCertUdf3() {
		return (java.lang.String)HiltonUtility.ckNull(this.certUdf3).trim();
    }

    public void setCertUdf3(java.lang.String certUdf3) {
		if (!HiltonUtility.isEmpty(certUdf3) && certUdf3.length() > 15) {
			certUdf3 = certUdf3.substring(0, 15);
		}
		this.certUdf3 = certUdf3;
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

    public java.lang.String getCertUdf5() {
		return (java.lang.String)HiltonUtility.ckNull(this.certUdf5).trim();
    }

    public void setCertUdf5(java.lang.String certUdf5) {
		if (!HiltonUtility.isEmpty(certUdf5) && certUdf5.length() > 15) {
			certUdf5 = certUdf5.substring(0, 15);
		}
		this.certUdf5 = certUdf5;
    }

    public java.lang.String getCertContact() {
		return (java.lang.String)HiltonUtility.ckNull(this.certContact).trim();
    }

    public void setCertContact(java.lang.String certContact) {
		if (!HiltonUtility.isEmpty(certContact) && certContact.length() > 30) {
			certContact = certContact.substring(0, 30);
		}
		this.certContact = certContact;
    }

    public java.lang.String getContractNotes() {
		return (java.lang.String)HiltonUtility.ckNull(this.contractNotes).trim();
    }

    public void setContractNotes(java.lang.String contractNotes) {
		if (!HiltonUtility.isEmpty(contractNotes) && contractNotes.length() > 60) {
			contractNotes = contractNotes.substring(0, 60);
		}
		this.contractNotes = contractNotes;
    }

    public java.lang.String getComplianceNotes() {
		return (java.lang.String)HiltonUtility.ckNull(this.complianceNotes).trim();
    }

    public void setComplianceNotes(java.lang.String complianceNotes) {
		if (!HiltonUtility.isEmpty(complianceNotes) && complianceNotes.length() > 60) {
			complianceNotes = complianceNotes.substring(0, 60);
		}
		this.complianceNotes = complianceNotes;
    }

    public java.lang.String getInsuranceNotes() {
		return (java.lang.String)HiltonUtility.ckNull(this.insuranceNotes).trim();
    }

    public void setInsuranceNotes(java.lang.String insuranceNotes) {
		if (!HiltonUtility.isEmpty(insuranceNotes) && insuranceNotes.length() > 60) {
			insuranceNotes = insuranceNotes.substring(0, 60);
		}
		this.insuranceNotes = insuranceNotes;
    }

    public java.util.Date getNotifiedDate() {
		return this.notifiedDate;
//		return HiltonUtility.ckNull(this.notifiedDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.notifiedDate);
    }

    public void setNotifiedDate(java.util.Date notifiedDate) {
        this.notifiedDate = notifiedDate;
    }

    public java.math.BigDecimal getDollarValue() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.dollarValue);
    }

    public void setDollarValue(java.math.BigDecimal dollarValue) {
        this.dollarValue = dollarValue;
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

    public java.util.Date getDateExpires1() {
		return this.dateExpires1;
    }

    public void setDateExpires1(java.util.Date dateExpires1) {
        this.dateExpires1 = dateExpires1;
    }

    public java.util.Date getDateExpires2() {
		return this.dateExpires2;
    }

    public void setDateExpires2(java.util.Date dateExpires2) {
        this.dateExpires2 = dateExpires2;
    }

    public java.util.Date getDateExpires3() {
		return this.dateExpires3;
    }

    public void setDateExpires3(java.util.Date dateExpires3) {
        this.dateExpires3 = dateExpires3;
    }

    public java.util.Date getDateExpires4() {
		return this.dateExpires4;
    }

    public void setDateExpires4(java.util.Date dateExpires4) {
        this.dateExpires4 = dateExpires4;
    }

    public java.util.Date getDateExpires5() {
		return this.dateExpires5;
    }

    public void setDateExpires5(java.util.Date dateExpires5) {
        this.dateExpires5 = dateExpires5;
    }

    public java.util.Date getDateExpires6() {
		return this.dateExpires6;
    }

    public void setDateExpires6(java.util.Date dateExpires6) {
        this.dateExpires6 = dateExpires6;
    }

    public java.util.Date getDateExpires7() {
		return this.dateExpires7;
    }

    public void setDateExpires7(java.util.Date dateExpires7) {
        this.dateExpires7 = dateExpires7;
    }

    //-------- ///
    public java.util.Date getCertifiedDate7() {
        return this.certifiedDate7;
    }
    public void setCertifiedDate7(java.util.Date certifiedDate7) {
        this.certifiedDate7 = certifiedDate7;
    }

    public java.lang.String getCertStatus7() {
        return (java.lang.String)HiltonUtility.ckNull(this.certStatus7).trim();
    }
    public void setCertStatus7(java.lang.String certStatus7) {
        if (!HiltonUtility.isEmpty(certStatus7) && certStatus7.length() > 1) {
            certStatus7 = certStatus7.substring(0, 1);
        }
        this.certStatus7 = certStatus7;
    }

    public java.lang.String getCertUdf7() {
        return (java.lang.String)HiltonUtility.ckNull(this.certUdf7).trim();
    }
    public void setCertUdf7(java.lang.String certUdf7) {
        if (!HiltonUtility.isEmpty(certUdf7) && certUdf7.length() > 15) {
            certUdf7 = certUdf7.substring(0, 15);
        }
        this.certUdf7 = certUdf7;
    }

    //-------  ///


    public String toString() {
        return new ToStringBuilder(this)
            .append("contNumber", getContNumber())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof VendorInsurance) ) return false;
        VendorInsurance castOther = (VendorInsurance) other;
        return new EqualsBuilder()
            .append(this.getContNumber(), castOther.getContNumber())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getContNumber())
            .toHashCode();
    }

}
