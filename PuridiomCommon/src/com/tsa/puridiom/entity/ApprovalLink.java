/**
 *
 */
package com.tsa.puridiom.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.tsa.puridiom.common.utility.HiltonUtility;

/**
 * @author Johnny Zapana
 */
public class ApprovalLink implements Serializable
{

	/** nullable persistent field */
	private java.math.BigDecimal icApprovalLink;

	/** nullable persistent field */
	private String doctype;

	/** nullable persistent field */
	private java.math.BigDecimal icHeader;

	/** nullable persistent field */
	private String documentNumber;

	/** nullable persistent field */
	private String userId;

	/** nullable persistent field */
	private String organizationId;
	
    /** nullable persistent field */
    private String action;
    
    /** nullable persistent field */
    private String logDate;

    /** nullable persistent field */
    private String logTime;

	/**
	 * @return the doctype
	 */
	public String getDoctype()
	{
		return (String) HiltonUtility.ckNull(this.doctype).trim();
	}

	/**
	 * @param doctype
	 *            the doctype to set
	 */
	public void setDoctype(String doctype)
	{
		this.doctype = doctype;
	}

	/**
	 * @return the documentNumber
	 */
	public String getDocumentNumber()
	{
		return (String) HiltonUtility.ckNull(this.documentNumber).trim();
	}

	/**
	 * @param documentNumber
	 *            the documentNumber to set
	 */
	public void setDocumentNumber(String documentNumber)
	{
		this.documentNumber = documentNumber;
	}

	/**
	 * @return the icApprovalLink
	 */
	public java.math.BigDecimal getIcApprovalLink()
	{
		return (BigDecimal) HiltonUtility.ckNull(this.icApprovalLink);
	}

	/**
	 * @param icApprovalLink
	 *            the icApprovalLink to set
	 */
	public void setIcApprovalLink(java.math.BigDecimal icApprovalLink)
	{
		this.icApprovalLink = icApprovalLink;
	}

	/**
	 * @return the icHeader
	 */
	public java.math.BigDecimal getIcHeader()
	{
		return (BigDecimal) HiltonUtility.ckNull(this.icHeader);
	}

	/**
	 * @param icHeader
	 *            the icHeader to set
	 */
	public void setIcHeader(java.math.BigDecimal icHeader)
	{
		this.icHeader = icHeader;
	}

	/**
	 * @return the organizationId
	 */
	public String getOrganizationId()
	{
		return (String) HiltonUtility.ckNull(this.organizationId).trim();
	}

	/**
	 * @param organizationId
	 *            the organizationId to set
	 */
	public void setOrganizationId(String organizationId)
	{
		this.organizationId = organizationId;
	}

	/**
	 * @return the userId
	 */
	public String getUserId()
	{
		return (String) HiltonUtility.ckNull(this.userId).trim();
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	/**
	 * @return the action
	 */
	public String getAction()
	{
		return (String) HiltonUtility.ckNull(this.action).trim();
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action)
	{
		this.action = action;
	}

	/**
	 * @return the logDate
	 */
	public String getLogDate()
	{
		return (String) HiltonUtility.ckNull(this.logDate).trim();
	}

	/**
	 * @param logDate the logDate to set
	 */
	public void setLogDate(String logDate)
	{
		if (!HiltonUtility.isEmpty(logDate) && logDate.length() > 10)
		{
			logDate = logDate.substring(0, 10);
		}

		this.logDate = logDate;
	}

	/**
	 * @return the logTime
	 */
	public String getLogTime()
	{
		return (String) HiltonUtility.ckNull(this.logTime).trim();
	}

	/**
	 * @param logTime the logTime to set
	 */
	public void setLogTime(String logTime)
	{
		if (!HiltonUtility.isEmpty(logTime) && logTime.length() > 15)
		{
			logTime = logTime.substring(0, 15);
		}

		this.logTime = logTime;
	}
}
