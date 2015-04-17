package com.tsa.puridiom.timer;

import com.tsa.puridiom.emails.CtbtoProcessIncomingEmails;
import com.tsa.puridiom.emails.SaveEmails;
import com.tsagate.foundation.utility.Log;

public class GetEmailsJob extends ScheduledJob
{

	public void execute()
	{
		Log.debug(this, "now getting emails");
		SaveEmails saveEmails = new SaveEmails();
		saveEmails.getEmails(this.getOrganizationId());
		Log.debug(this, "now processing files");
		CtbtoProcessIncomingEmails processFolder = new CtbtoProcessIncomingEmails();
		processFolder.setOrganizationId(this.getOrganizationId());
		processFolder.processFiles();

		Log.debug(this, "GetEmailsJobDone: " + this.toString());
	}
}
