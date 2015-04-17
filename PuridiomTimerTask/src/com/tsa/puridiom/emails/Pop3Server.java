/*
 * Created on Aug 9, 2004
 */
package com.tsa.puridiom.emails;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.properties.DictionaryManager;

/**
 * @author renzo
 *
 */
public class Pop3Server extends EmailServer
{
	public Pop3Server(String _organizationId)
	{
		this.setOrganizationId(_organizationId);

		this.setServer(DictionaryManager.getInstance("emails", this.getOrganizationId()).getProperty("mail.pop3server", "mail.tsagate.com"));
	}

	public Pop3Server(String _organizationId, String jobType)
	{

		this.setOrganizationId(_organizationId);
		if (!HiltonUtility.isEmpty(jobType))
		{
			this.setServer(DictionaryManager.getInstance("emails", this.getOrganizationId()).getProperty(jobType + ".pop3server", ""));
		}
		else
		{
			this.setServer(DictionaryManager.getInstance("emails", this.getOrganizationId()).getProperty("mail.pop3server", ""));
		}
	}
}




