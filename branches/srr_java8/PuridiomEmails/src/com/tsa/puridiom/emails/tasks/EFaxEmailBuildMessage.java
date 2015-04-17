/**
 *
 */
package com.tsa.puridiom.emails.tasks;

import java.io.File;
import java.util.Map;

/**
 * @author Johnny
 */
public class EFaxEmailBuildMessage extends EmailBuildMessage
{
	protected void setEmailData(Map incomingRequest, String organizationId)
	{
		String subject = (String) incomingRequest.get("subject");

		if (subject.matches("(.)*Unsuccessful fax transmission (.)+"))
		{
			this.bofEmail = "((.)*<span style=\"font-weight: bold;\">Re: (.)+)|((.)*Re: (.)+)";
			this.eofEmail = "((.)*We suggest you attempt to fax the document again(.)+)|((.)*If you need additional assistance(.)+)|" +
					        "((.)*Please verify the fax number before(.)+)";
			this.auxEmail = "((.)*</span>(.)+)|((.)*</SPAN>(.)+)|((.)+</span>)|((.)+</SPAN>)";
		}
		else
		{
			this.bofEmail = "(Dear efax(.)+)|(Dear efax)|((.)*Dear efax(.)+)";
			this.eofEmail = "((.)*If you need additional assistance,(.)+)";
			this.auxEmail = "((.)*Puridiom,(.)+)|((.)*Puridiom,)";
		}
		this.eofNewEmail = "<p>If you need additional assistance, please email support@puridiom.com. <br><br>" + "Regards,<br>" + "eFax Puridiom.com";
	}
}