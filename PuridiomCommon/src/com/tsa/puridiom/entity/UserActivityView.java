package com.tsa.puridiom.entity;

import java.io.Serializable;

import com.tsa.puridiom.common.utility.HiltonUtility;

/** @author Hibernate CodeGenerator */
public class UserActivityView implements Serializable
{
    /** identifier field */
    private String userId;

    /** nullable persistent field */
    private String firstName;

    /** nullable persistent field */
    private String lastName;

    /** nullable persistent field */
    private java.util.Date lastLoggedOn;

    /** nullable persistent field */
    private java.util.Date loggedOn;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String department;

    /** nullable persistent field */
    private java.util.Date lastChangeDate;

    /** nullable persistent field */
    private java.util.Date dateApproved;

    /** nullable persistent field */
    private String ruleAssign;

    /** full constructor */
    public UserActivityView(java.lang.String userId, java.lang.String firstName, java.lang.String lastName,  java.util.Date lastLoggedOn,  java.util.Date loggedOn, java.util.Date dateEntered, java.lang.String status, java.lang.String department, java.util.Date dateApproved, java.util.Date lastChangeDate, java.lang.String ruleAssign)
    {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastLoggedOn = lastLoggedOn;
        this.loggedOn = loggedOn;
        this.dateEntered = dateEntered;
        this.status = status;
        this.department = department;
        this.lastChangeDate = lastChangeDate;
        this.dateApproved = dateApproved;
        this.ruleAssign = ruleAssign;

    }


    /** default constructor */
    public UserActivityView() {
    }

    public java.lang.String getUserId() {
		return (java.lang.String)HiltonUtility.ckNull(this.userId).trim();
    }

    public void setUserId(java.lang.String userId) {
        if (!HiltonUtility.isEmpty(userId)) {
            userId = userId.toUpperCase();
        }
        this.userId = userId;
    }

    public java.lang.String getFirstName() {
		return (java.lang.String)HiltonUtility.ckNull(this.firstName).trim();
    }

    public void setFirstName(java.lang.String firstName) {
		if (!HiltonUtility.isEmpty(firstName) && firstName.length() > 20) {
			firstName = firstName.substring(0, 20);
		}
		this.firstName = firstName;
    }


    public java.lang.String getLastName() {
		return (java.lang.String)HiltonUtility.ckNull(this.lastName).trim();
    }

    public void setLastName(java.lang.String lastName) {
		if (!HiltonUtility.isEmpty(lastName) && lastName.length() > 20) {
			lastName = lastName.substring(0, 20);
		}
		this.lastName = lastName;
    }

    public java.util.Date getLastLoggedOn() {
        return this.lastLoggedOn;
    }

    public void setLastLoggedOn(java.util.Date lastLoggedOn) {
        this.lastLoggedOn = lastLoggedOn;
    }
    
    public java.util.Date getLoggedOn() {
    	return this.loggedOn;
    }
    
    public void setLoggedOn(java.util.Date loggedOn) {
    	this.loggedOn = loggedOn;
    }

    public java.util.Date getDateEntered() {
		return this.dateEntered;
    }

    public void setDateEntered(final java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
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

    public java.lang.String getDepartment() {
		return (java.lang.String)HiltonUtility.ckNull(this.department).trim();
    }

    public void setDepartment(java.lang.String department) {
		if (!HiltonUtility.isEmpty(department) && department.length() > 15) {
			department = department.substring(0, 15);
		}
		this.department = department;
    }

    public java.util.Date getLastChangeDate() {
        return this.lastChangeDate;
    }

    public void setLastChangeDate(java.util.Date lastChangeDate) {
        this.lastChangeDate = lastChangeDate;
    }

    public java.util.Date getDateApproved() {
		return this.dateApproved;
//		return HiltonUtility.ckNull(this.dateApproved);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateApproved);
    }

    public void setDateApproved(java.util.Date dateApproved) {
        this.dateApproved = dateApproved;
    }

	public String getRuleAssign() {
		return (java.lang.String)HiltonUtility.ckNull(this.ruleAssign).trim();
	}

	public void setRuleAssign(String ruleAssign) {
		this.ruleAssign = ruleAssign;
	}

}
