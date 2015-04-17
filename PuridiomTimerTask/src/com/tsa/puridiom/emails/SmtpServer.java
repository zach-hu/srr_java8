package com.tsa.puridiom.emails;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.properties.DictionaryManager;

public class SmtpServer extends EmailServer
{
	public SmtpServer(String _organizationId)
    {
    	this.setOrganizationId(_organizationId);

        this.setServer(DictionaryManager.getInstance("emails", this.getOrganizationId()).getProperty("mail.smtpserver", "mail.tsagate.com"));
    }

	public SmtpServer(String _organizationId, String jobType)
    {
    	this.setOrganizationId(_organizationId);
    	String smtpServer = "";
    	if (!HiltonUtility.isEmpty(jobType))
		{
    		smtpServer = DictionaryManager.getInstance("emails", this.getOrganizationId()).getProperty(jobType + ".smtpserver", "");
    		if(HiltonUtility.isEmpty(smtpServer))
    		{
    			smtpServer = DictionaryManager.getInstance("emails", this.getOrganizationId()).getProperty("mail.smtpserver", "");
    		}
		}
    	else
    	{
    		smtpServer = DictionaryManager.getInstance("emails", this.getOrganizationId()).getProperty("mail.smtpserver", "");
    	}

        this.setServer(smtpServer);
    }
}
