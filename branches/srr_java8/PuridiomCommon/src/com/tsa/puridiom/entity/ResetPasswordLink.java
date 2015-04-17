package com.tsa.puridiom.entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import com.tsa.puridiom.common.utility.HiltonUtility;

public class ResetPasswordLink implements Serializable
{
	/** identifier field */
	private java.math.BigDecimal icLink;

	private String userId;
	private String mailId;
    private java.util.Date linkDate;
	private String used;

	/** full constructor */
	public ResetPasswordLink(java.math.BigDecimal icLink, String userId, String mailId, java.util.Date linkDate, String used)
	{
		this.icLink = icLink;
		this.userId = userId;
		this.mailId = mailId;
		this.linkDate = linkDate;
		this.used = used;
	}

	/** default constructor */
	public ResetPasswordLink()	{	}

	/** minimal constructor */
	public ResetPasswordLink(java.math.BigDecimal icLink) {
		this.icLink = icLink;
	}

	public java.math.BigDecimal getIcLink() {
		return icLink;
	}

	public void setIcLink(java.math.BigDecimal icLink) {
		this.icLink = icLink;
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
    
    public java.lang.String getMailId() {
		return (java.lang.String)HiltonUtility.ckNull(this.mailId).trim();
    }

    public void setMailId(java.lang.String mailId) {
        if (!HiltonUtility.isEmpty(mailId)) {
            mailId = mailId.toLowerCase();
        }
    	this.mailId = mailId;
    }
    
    public java.util.Date getLinkDate() {
		return this.linkDate;
    }

    public void setLinkDate(java.util.Date linkDate) {
        this.linkDate = linkDate;
    }

	public String getUsed()	{
		return (java.lang.String)HiltonUtility.ckNull(this.used).trim();
	}
	
	public void setUsed(String used)	{
		this.used = used;
	}
	
    public String toString() {
        return new ToStringBuilder(this)
        	.append("icLink: " + getIcLink())
        	.append("userId: " + getUserId())
        	.append("mailId: " + getMailId())
            .append("linkDate: " +  getLinkDate())
            .append("used: " +  getUsed())            
            .toString();
    }
}
