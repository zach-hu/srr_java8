package com.tsa.puridiom.timer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ScheduleManager
{
	private static ScheduleManager instance = null;
	private boolean started = false;

	private Map timers = null;
	private Map schedules = null;
	private List organizations = null;

	public static ScheduleManager getInstance()
	{
		if(ScheduleManager.instance == null)
		{
			ScheduleManager.instance = new ScheduleManager();
		}
		return ScheduleManager.instance;
	}

	private void reScheduleJob(String jobName, String organizationId)
	{
		this.stopJob(jobName, organizationId);
		this.getTimer(organizationId).scheduleJob(jobName);
	}

	public PuridiomTimer getTimer(String organizationId)
	{
		if(this.timers == null)
		{
			this.timers = new HashMap();
		}
		PuridiomTimer timer = null;
		if(!this.timers.containsKey(organizationId.toUpperCase()))
		{
			timer = new PuridiomTimer(organizationId.toUpperCase());
			this.timers.put(organizationId.toUpperCase(), timer);
		}
		else
		{
			timer = (PuridiomTimer)this.timers.get(organizationId.toUpperCase());
		}

		return timer;
	}

	private String getJobStatus(String organizationId, String jobName)
	{
		return this.getTimer(organizationId).getJob(jobName).toString();
	}

	private void clearTimer(String organizationId)
	{
		if(this.timers.containsKey(organizationId))
		{
			this.timers.remove(organizationId);
		}
	}

	private List getSchedules(String organizationId)
	{
		List schedule = null;
		if(this.schedules == null)
		{
			this.schedules = new HashMap();
		}
		if(this.schedules.containsKey(organizationId))
		{
			schedule = (List)this.schedules.get(organizationId);
		}
		else
		{
			LoadSchedule loadSchedule = new LoadSchedule();
			schedule = loadSchedule.loadMe(organizationId);
			this.schedules.put(organizationId, schedule);
			this.organizations.add(organizationId);
		}

		return schedule;
	}

	private void executeSchedules(String organizationId)
	{
		this.getTimer(organizationId).scheduleJobs(this.getSchedules(organizationId));
	}

	private ScheduleManager()
	{
		this.organizations = new ArrayList();
	}

	public void start(String organizationId)
	{
	    System.out.println("starting:" + organizationId);
	    this.started = true;

	    this.executeSchedules(organizationId);
	}

	public void stop(String organizationId)
	{
		//if(this.started)
		//{
			System.out.println("stopping:" + organizationId);
			this.getTimer(organizationId).cancel();
			this.clearTimer(organizationId);
			this.started = false;
		//}
	}

	public void stopMe()
	{
		if(this.timers != null)
		{
			Set timersSet = this.timers.keySet();
			for (Iterator iter = timersSet.iterator(); iter.hasNext();)
			{
				String organizationId = (String)iter.next();
				this.stop(organizationId);
			}
		}
	}

	public boolean isAllStoped()
	{
		if(this.timers != null)
		{
			Set timersSet = this.timers.keySet();
			for (Iterator iter = timersSet.iterator(); iter.hasNext();)
			{
				String organizationId = (String)iter.next();

				Map jobs = ScheduleManager.getInstance().getTimer(organizationId).getJobs();
				Set jobNames = jobs.keySet();
				for (Iterator iterJob = jobNames.iterator(); iter.hasNext();)
				{
					String jobName = (String)iterJob.next();
					Job job = (Job)jobs.get(jobName);

					if(job.getScheduledJob().isStarted())
					{
						if(job.getScheduledJob().isRunning())
						{
							return false;
						}
					}
				}
			}
		}
		return true;
	}


	public String getStatus()
	{
		List temp = this.organizations;
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < temp.size(); i++)
		{

			//sb.append(this.getSchedules(temp.get(i).toString()) + "\n");
			sb.append(this.getTimer(temp.get(i).toString()));
		}

		return sb.toString();
	}

	public void startJob(String jobName, String organizationId)
	{
		this.getTimer(organizationId).scheduleJob(jobName);
	}

	public void stopJob(String jobName, String organizationId)
	{
		PuridiomTimer timer = this.getTimer(organizationId);
		timer.stopJob(jobName);
	}

	public static void main(String[] args)
	{
		System.out.println("Start scheduler");
		ScheduleManager.getInstance().start("qri06p");
		System.out.println("list jobs");
		/*Map jobs = ScheduleManager.getInstance().listJobs("test");
		Set jobsSet = jobs.keySet();
		for (Iterator iter = jobsSet.iterator(); iter.hasNext();)
		{
			String jobName = (String) iter.next();
			System.out.println("JobName: " + jobName);
			Job sJob = (Job)jobs.get(jobName);
			System.out.println("running: " + sJob.getScheduledJob().isRunning());
		}*/

		//ScheduleManager.getInstance().stop("qri06p");

	}
	public static void main1(String[] args)
	{
		System.out.println("Start scheduler");
		if(args[0].equalsIgnoreCase("stop"))
		{
			ScheduleManager.getInstance().stop("ctbto");
		}
		else
		{
			ScheduleManager.getInstance().start("ctbto");
		}

		System.out.println("done");
	}

	public boolean isStarted() {
		return started;
	}
}
