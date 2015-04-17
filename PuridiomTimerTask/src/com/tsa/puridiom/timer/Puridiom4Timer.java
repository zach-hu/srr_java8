package com.tsa.puridiom.timer;

import java.util.Date;
import java.util.TimerTask;

public class Puridiom4Timer extends TimerTask
{
	private int times = 0;
	private boolean running = false;
	private String organizationId = "";


	public Puridiom4Timer(String organizationId)
	{
		this.setOrganizationId(organizationId);
	}

	public Puridiom4Timer()
	{
		this("test");
	}

	public void run()
	{
		if(!this.isRunning())
		{
			this.setRunning(true);
			//TimerUtility.executeProcess("puridiomservices.xml");
			TimerUtility.executeProcess("process-emails.xml", this.getOrganizationId());
			System.out.println("time: " + (new Date()).toString() + " times: " + times);
			times++;
		}
		this.setRunning(false);
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public int getTimes() {
		return times;
	}


}
