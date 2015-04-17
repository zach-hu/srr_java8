package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class CatalogSitelist implements Serializable {

    /** identifier field */
    private CatalogSitelistPK comp_id;

    /** nullable persistent field */
    private String catalogLocation;

    /** nullable persistent field */
    private String catalogFilename;

    /** nullable persistent field */
    private String siteUserid;

    /** nullable persistent field */
    private String sitePassword;

    /** nullable persistent field */
    private String ftpFilename;

    /** nullable persistent field */
    private String blanketNumber;

    /** full constructor */
    public CatalogSitelist(CatalogSitelistPK comp_id, java.lang.String catalogLocation, java.lang.String catalogFilename, java.lang.String siteUserid, java.lang.String sitePassword, java.lang.String ftpFilename, java.lang.String blanketNumber) {
        this.comp_id = comp_id;
        this.catalogLocation = catalogLocation;
        this.catalogFilename = catalogFilename;
        this.siteUserid = siteUserid;
        this.sitePassword = sitePassword;
        this.ftpFilename = ftpFilename;
        this.blanketNumber = blanketNumber;
    }

    /** default constructor */
    public CatalogSitelist() {
    }

    /** minimal constructor */
    public CatalogSitelist(CatalogSitelistPK comp_id) {
        this.comp_id = comp_id;
    }

    public CatalogSitelistPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(CatalogSitelistPK comp_id) {
        this.comp_id = comp_id;
    }

    public java.lang.String getCatalogLocation() {
		return (java.lang.String)HiltonUtility.ckNull(this.catalogLocation).trim();
    }

    public void setCatalogLocation(java.lang.String catalogLocation) {
		if (!HiltonUtility.isEmpty(catalogLocation) && catalogLocation.length() > 80) {
			catalogLocation = catalogLocation.substring(0, 80);
		}
		this.catalogLocation = catalogLocation;
    }

    public java.lang.String getCatalogFilename() {
		return (java.lang.String)HiltonUtility.ckNull(this.catalogFilename).trim();
    }

    public void setCatalogFilename(java.lang.String catalogFilename) {
		if (!HiltonUtility.isEmpty(catalogFilename) && catalogFilename.length() > 25) {
			catalogFilename = catalogFilename.substring(0, 25);
		}
		this.catalogFilename = catalogFilename;
    }

    public java.lang.String getSiteUserid() {
		return (java.lang.String)HiltonUtility.ckNull(this.siteUserid).trim();
    }

    public void setSiteUserid(java.lang.String siteUserid) {
		if (!HiltonUtility.isEmpty(siteUserid) && siteUserid.length() > 20) {
			siteUserid = siteUserid.substring(0, 20);
		}
		this.siteUserid = siteUserid;
    }

    public java.lang.String getSitePassword() {
		return (java.lang.String)HiltonUtility.ckNull(this.sitePassword).trim();
    }

    public void setSitePassword(java.lang.String sitePassword) {
		if (!HiltonUtility.isEmpty(sitePassword) && sitePassword.length() > 20) {
			sitePassword = sitePassword.substring(0, 20);
		}
		this.sitePassword = sitePassword;
    }

    public java.lang.String getFtpFilename() {
		return (java.lang.String)HiltonUtility.ckNull(this.ftpFilename).trim();
    }

    public void setFtpFilename(java.lang.String ftpFilename) {
		if (!HiltonUtility.isEmpty(ftpFilename) && ftpFilename.length() > 25) {
			ftpFilename = ftpFilename.substring(0, 25);
		}
		this.ftpFilename = ftpFilename;
    }

    public java.lang.String getBlanketNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.blanketNumber).trim();
    }

    public void setBlanketNumber(java.lang.String blanketNumber) {
		if (!HiltonUtility.isEmpty(blanketNumber) && blanketNumber.length() > 20) {
			blanketNumber = blanketNumber.substring(0, 20);
		}
		this.blanketNumber = blanketNumber;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof CatalogSitelist) ) return false;
        CatalogSitelist castOther = (CatalogSitelist) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

}
