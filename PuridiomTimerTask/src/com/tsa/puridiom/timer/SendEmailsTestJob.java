package com.tsa.puridiom.timer;

import com.tsa.puridiom.emails.SendCTBTOTestEmail;
import com.tsagate.foundation.utility.Log;

public class SendEmailsTestJob extends ScheduledJob
{

	public void execute()
	{
		Log.debug(this, "now getting emails");
		SendCTBTOTestEmail testEmail = new SendCTBTOTestEmail();
		testEmail.sendTestEmail("This is a test email. Repeast itself every so often", this.getOrganizationId(), "PO 2002-0025 title" + this.getTimes());

		Log.debug(this, "SendEmailsTestJob: " + this.toString());
	}
}
