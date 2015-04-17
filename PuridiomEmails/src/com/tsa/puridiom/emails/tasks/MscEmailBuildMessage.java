/**
 *
 */
package com.tsa.puridiom.emails.tasks;

import java.util.Map;

/**
 * @author Johnny
 */
public class MscEmailBuildMessage extends EmailBuildMessage
{
	protected void setEmailData(Map incomingRequest, String organizationId)
	{
		String subject = (String) incomingRequest.get("subject");

		String docType = (String) incomingRequest.put("docType", "MSC");
	}

}