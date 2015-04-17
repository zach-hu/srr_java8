package com.tsa.puridiom.timer;

public class AlertsSchedule extends ScheduledJob
{

	public void execute()
	{
		TimerUtility.executeProcess("process-emails.xml", this.getOrganizationId());
	}

}
