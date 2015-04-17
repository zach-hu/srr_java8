/**
 * 
 */
package com.tsa.puridiom.entity;

import java.io.Serializable;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.Entity;

/**
 * @author Johnny
 */
public class ApprovalUsersView extends Entity implements Serializable
{
	/** identifier field */
	private String approverId;

	/** nullable persistent field */
	private String name;

	/** nullable persistent field */
	private String title;

	/** nullable persistent field */
	private String approver;

	/** nullable persistent field */
	private java.math.BigDecimal approvalAmount;

	/** nullable persistent field */
	private String userType;

	/** nullable persistent field */
	private String status;

	/**
	 * @return the approverId
	 */
	public String getApproverId()
	{
		return (java.lang.String) HiltonUtility.ckNull(this.approverId).trim();
	}

	/**
	 * @param approverId
	 *            the approverId to set
	 */
	public void setApproverId(String approverId)
	{
		this.approverId = approverId;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return (java.lang.String) HiltonUtility.ckNull(this.name).trim();
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the title
	 */
	public String getTitle()
	{
		return (java.lang.String) HiltonUtility.ckNull(this.title).trim();
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * @return the approver
	 */
	public String getApprover()
	{
		return (java.lang.String) HiltonUtility.ckNull(this.approver).trim();
	}

	/**
	 * @param approver
	 *            the approver to set
	 */
	public void setApprover(String approver)
	{
		this.approver = approver;
	}

	/**
	 * @return the approvalAmount
	 */
	public java.math.BigDecimal getApprovalAmount()
	{
		return (java.math.BigDecimal) HiltonUtility.ckNull(this.approvalAmount);
	}

	/**
	 * @param approvalAmount
	 *            the approvalAmount to set
	 */
	public void setApprovalAmount(java.math.BigDecimal approvalAmount)
	{
		this.approvalAmount = approvalAmount;
	}

	/**
	 * @return the userType
	 */
	public String getUserType()
	{
		return (java.lang.String) HiltonUtility.ckNull(this.userType).trim();
	}

	/**
	 * @param userType
	 *            the userType to set
	 */
	public void setUserType(String userType)
	{
		this.userType = userType;
	}

	/**
	 * @return the status
	 */
	public String getStatus()
	{
		return (java.lang.String) HiltonUtility.ckNull(this.status).trim();
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status)
	{
		this.status = status;
	}

}
