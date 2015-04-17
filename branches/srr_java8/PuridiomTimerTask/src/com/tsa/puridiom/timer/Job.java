package com.tsa.puridiom.timer;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.utility.Log;

public class Job
{
	private String name = "";
	private String className = "";
	private long schedule = 0;
	private String organizationId = "";
	private ScheduledJob scheduledJob = null;

	private String lastRun = "";
	private int order;

	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId)
	{
		this.organizationId = organizationId;
	}
	public String getClassName()
	{
		return className;
	}

	public ScheduledJob getJobToSchedule()
	{
		ScheduledJob job = null;

		try
		{
			job = (ScheduledJob)Class.forName(this.getClassName()).newInstance();
			job.setOrganizationId(this.getOrganizationId());
			job.setSchedule(this.schedule);
		}
		catch (InstantiationException e)
		{
			Log.error(this, e.getMessage());
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			Log.error(this, e.getMessage());
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			Log.error(this, e.getMessage());
			e.printStackTrace();
		}

		return job;
	}
	public void setClassName(String className)
	{
		if(!HiltonUtility.isEmpty(className))
		{
			this.className = className;
		}
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}

	public long getSeconds()
	{
		return TimerUtility.getSeconds(schedule);
	}

	public long getMinutes()
	{
		return TimerUtility.getMinutes(schedule);
	}


	public void setSchedule(String schedule)
	{
		if(!HiltonUtility.isEmpty(schedule))
		{
			this.schedule = Long.parseLong(schedule);
		}
	}
	public String toString() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("[Job: " + this.getName());
			buffer.append(" schedule: ");
			buffer.append(schedule);
			buffer.append(" organizationId: ");
			buffer.append(organizationId);

			buffer.append(" last Run: ");
			buffer.append(lastRun);
			buffer.append(" Orden es: ");
			buffer.append(String.valueOf(order));

			buffer.append("<BR>");
			buffer.append(this.getScheduledJob());
			buffer.append("]");
			return buffer.toString();
		}

	public ScheduledJob getScheduledJob() {
		return scheduledJob;
	}
	public void setScheduledJob(ScheduledJob scheduledJob) {
		this.scheduledJob = scheduledJob;
	}
	/**
	 * @return the lastRun
	 */
	public String getLastRun() {
		return lastRun;
	}
	/**
	 * @param lastRun the lastRun to set
	 */
	public void setLastRun(String lastRun) {
		this.lastRun = lastRun;
	}
	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}

}
