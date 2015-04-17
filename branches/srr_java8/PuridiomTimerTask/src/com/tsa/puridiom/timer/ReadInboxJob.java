package com.tsa.puridiom.timer;

import java.io.File;

import com.tsa.puridiom.emails.ReadInbox;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Log;

public class ReadInboxJob extends ScheduledJob
{
	private String jobType = null;

	public void execute()
	{
		Log.debug(this, "ReadInboxlJob Start execute...");
		ReadInbox readInbox = new ReadInbox(this);
		readInbox.readInbox(this.getOrganizationId(), this.getJobType());

		Log.debug(this, "ReadInboxlJob Job End execute...");
	}
	
	public int processEmail(File emailFile, String subject, String sendFrom, String organizationId)
	{
		return Status.READY;
	}

	public String getJobType()
	{
		return this.jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
}
