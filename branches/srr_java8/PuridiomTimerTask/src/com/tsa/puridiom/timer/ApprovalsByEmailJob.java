package com.tsa.puridiom.timer;

import com.tsa.puridiom.emails.ApprovalsByEmail;
import com.tsagate.foundation.utility.Log;

public class ApprovalsByEmailJob extends ReadInboxJob
{

	public void execute()
	{
		Log.debug(this, "ApprovalsByEmailJob Start execute...");
		ApprovalsByEmail inbox = new ApprovalsByEmail();
		inbox.readInbox(this.getOrganizationId(), this.getJobType());
		Log.debug(this, "ApprovalsByEmailJob Job End execute...");
	}

	public void onStart()
	{
		this.setJobType("approvals");
	}

}
