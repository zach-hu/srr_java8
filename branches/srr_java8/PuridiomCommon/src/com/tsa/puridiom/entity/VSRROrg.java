package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.Entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class VSRROrg extends Entity implements Serializable {

    /** persistent field */
    private String orgID;

    /**  persistent field */
    private String orgName;

    /** persistent field */
    private String levelNumber;

    /** nullable persistent field */
    private String parentOrgID;

    /** nullable persistent field */
    private String managerID;

    /** full constructor */
    public VSRROrg(java.lang.String orgID, java.lang.String orgName, String levelNumber, java.lang.String parentOrgID, String managerID) {
        this.orgID = orgID;
        this.orgName = orgName;
        this.levelNumber = levelNumber;
        this.parentOrgID = parentOrgID;
        this.managerID = managerID;
    }

    /** default constructor */
    public VSRROrg() {
    }

    /** minimal constructor */
    public VSRROrg(String orgID, String orgName, String levelNumber) {
    	this.orgID = orgID;
        this.orgName = orgName;
        this.levelNumber = levelNumber;
    }

    public String getOrgID() {
        return this.orgID;
    }

    public void setOrgID(String orgID) {
        this.orgID = orgID;
    }

    public java.lang.String getOrgName() {
		return (java.lang.String)HiltonUtility.ckNull(this.orgName).trim();
    }

    public void setOrgName(java.lang.String orgName) {
		this.orgName = orgName;
    }

    public java.lang.String getLevelNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.levelNumber).trim();
    }

    public void setLevelNumber(java.lang.String levelNumber) {
		this.levelNumber = levelNumber;
    }

    public java.lang.String getParentOrgID() {
		return (java.lang.String)HiltonUtility.ckNull(this.parentOrgID).trim();
    }

    public void setParentOrgID(java.lang.String parentOrgID) {
		this.parentOrgID = parentOrgID;
    }

    public java.lang.String getManagerID() {
		return (java.lang.String)HiltonUtility.ckNull(this.managerID).trim();
    }

    public void setManagerID(java.lang.String managerID) {
		this.managerID = managerID;
    }
}
