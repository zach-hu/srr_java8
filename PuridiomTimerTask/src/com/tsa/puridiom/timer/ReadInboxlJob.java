package com.tsa.puridiom.timer;

import com.tsagate.foundation.utility.Log;

public class ReadInboxlJob extends ScheduledJob
{

	public void execute()
	{
		Log.debug(this, "ReadInboxlJob Start execute...");
		//ReadInbox readInbox = new ReadInbox();
		//readInbox.readInbox(this.getOrganizationId(), "test");
		//app.getEmails(this.getOrganizationId());
		//uploadImages.uploadItemImageEmail(this.getOrganizationId());
		Log.debug(this, "ReadInboxlJob Job End execute...");
	}

}
