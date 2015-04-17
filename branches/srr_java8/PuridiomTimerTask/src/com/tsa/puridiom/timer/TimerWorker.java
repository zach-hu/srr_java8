package com.tsa.puridiom.timer;

import java.util.Calendar;
import java.util.Timer;

public class TimerWorker
{
	private static TimerWorker instance = null;
	private boolean started = false;
	private Timer timer = null;
	private Puridiom4Timer puridiom4Timer = null;
	private String organizationId = "";

	public static TimerWorker getInstance(String organizationId)
	{
		if(TimerWorker.instance == null)
		{
			TimerWorker.instance = new TimerWorker(organizationId);
		}
		return TimerWorker.instance;
	}

	private TimerWorker(String organizationId)
	{
		this.setOrganizationId(organizationId);
		this.timer = new Timer(true);
	}
	private TimerWorker()
	{
		this("test");
	}
	public void start()
	{
	    Calendar date = Calendar.getInstance();
	    date.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
	    date.set(Calendar.HOUR, 0);
	    date.set(Calendar.MINUTE, 0);
	    date.set(Calendar.SECOND, 0);
	    date.set(Calendar.MILLISECOND, 0);
	    // Schedule to run every 3 minutes
	    this.started = true;
	    if(this.puridiom4Timer == null)
	    {
	    	this.puridiom4Timer = new Puridiom4Timer(this.getOrganizationId());
	    }
	    timer.schedule(this.puridiom4Timer, 0, TimerUtility.getSeconds(180));
	}

	public void stop()
	{
		if(this.started)
		{
			System.out.println("stopping");
			this.started = false;
			this.timer.cancel();
		}
	}

	public static void main(String[] args)
	{
		Timer timer = new Timer();
	    Calendar date = Calendar.getInstance();
	    date.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
	    date.set(Calendar.HOUR, 0);
	    date.set(Calendar.MINUTE, 0);
	    date.set(Calendar.SECOND, 0);
	    date.set(Calendar.MILLISECOND, 0);
	    // Schedule to run every Sunday in midnight
	    timer.schedule(new Puridiom4Timer(), date.getTime(), 500);
	    for(int i = 0; i < 200000; i++)
	    {
	    	if((i % 1000) == 0)
	    	{
	    		System.out.println("i: " + i);
	    	}
	    }
	    timer.cancel();
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public boolean isStarted() {
		return started;
	}

	public int getTimesRun()
	{
		int times = 0;
		if(this.puridiom4Timer != null)
		{
			times = this.puridiom4Timer.getTimes();
		}
		return times;
	}
}
