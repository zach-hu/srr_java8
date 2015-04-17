package com.tsa.puridiom.timer;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Timer;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.emails.EmailManager;
import com.tsa.puridiom.entity.SendQueue;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.sendqueue.tasks.tests.SendQueueRetrieveByIdTest;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

public class PuridiomTimer
{
	private Timer timer = null;

	private Map jobs;

	private String organizationId;

	private boolean started = false;

	private UpdateJob updateJob = null;

	public PuridiomTimer()
	{
		this("puridiom");
	}

	/**
	 * Schedules the Jobs from the list
	 *
	 * @param schedules
	 */
	public void scheduleJobs(List schedules)
	{
		if (!this.isStarted())
		{

			this.setUpdateJob(new UpdateJob(this.getOrganizationId()));

			if (!this.getUpdateJob().getRunning().equals("Y"))
			{
				try
				{
					// Set 'Y' to indicate that PS is running
					this.getUpdateJob().setRunning("Y");
					// Update the last-run element with the current date
					this.getUpdateJob().setScheduleLastRun();
					this.getUpdateJob().output();

					for (int i = 0; i < schedules.size(); i++)
					{
						Job job = (Job) schedules.get(i);
						this.scheduleJob(job);
					}
					this.autoGenDocType();
					
				}
				catch (Exception e)
				{
					this.autoGenDocType();
					Log.error(this, "PuridiomServices Failed!");
					e.printStackTrace();
				}
				finally
				{
					// Set 'N' to indicate that PS has finished of running
					this.getUpdateJob().setRunning("N");
					this.getUpdateJob().output();
				}


			}
			else
			{
				Log.error(this, "PuridiomServices was not executed. Reason: last instance was found to be running.");
				Log.error(this, "An email will be sent to SYSADMIN user");
				this.sendStatusEmailNotification();
			}

			this.setStarted(true);
		}
	}

	/**
	 * Get Job Name
	 *
	 * @param jobName
	 * @return
	 */
	public Job getJob(String jobName)
	{
		return (Job) this.jobs.get(jobName);
	}

	/**
	 * Stop the given Job
	 *
	 * @param jobName
	 */
	public void stopJob(String jobName)
	{

		if (this.getJob(jobName).getScheduledJob().isStarted())
		{
			this.getJob(jobName).getScheduledJob().stopJob();
			// this.jobs.remove(jobName);
			Log.debug(this, "job [" + jobName + "] has been stopped (PTimer)");
		}
	}

	public PuridiomTimer(String organizationId)
	{
		this.setOrganizationId(organizationId);
		this.timer = new Timer();
		this.jobs = new HashMap();
	}

	/**
	 * Cancel all Jobs
	 */
	public void cancel()
	{
		if (this.timer != null)
		{
			this.setStarted(false);
			this.timer.cancel();
		}
	}

	/**
	 * Schedule/Run the Job. Updates last time Job was executed.
	 *
	 * @param job
	 * @return
	 */
	public Job scheduleJob(Job job)
	{
		ScheduledJob scheduledJob = job.getJobToSchedule();
		boolean runToday = true;

		try
		{
			if (job.getLastRun() != null)
			{
				//job.getMinutes()
				if (job.getLastRun().compareTo(Dates.today("yyyy/MM/dd HH:mm:ss", "")) > -1)
				{
					runToday = false;
				}
			}
				try
				{
					this.getUpdateJob().setLastRun(job.getOrder());
				}
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


			if (!scheduledJob.isStarted() && runToday)
			{
				// schedule task for execution multiple times
				// this.getTimer().schedule(scheduledJob, 0, job.getMinutes());
				// schedule task for execution after "10 miliseconds"
				// this.getTimer().schedule(scheduledJob, 10);
				// just execute the job
				System.out.println("executing: " + scheduledJob.toString());
				scheduledJob.run();
				scheduledJob.setStarted(true);
				job.setScheduledJob(scheduledJob);
				jobs.put(job.getClassName(), job);
				System.out.println("job done");
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}


		return job;
	}

	public void scheduleJob(String jobName)
	{
		if (this.jobs.containsKey(jobName))
		{
			this.scheduleJob((Job) this.jobs.get(jobName));
		}
	}

	public Map getJobs()
	{
		return jobs;
	}

	public void setJobs(Map jobs)
	{
		this.jobs = jobs;
	}

	public Timer getTimer()
	{
		return timer;
	}

	public void setTimer(Timer timer)
	{
		this.timer = timer;
	}

	public String getOrganizationId()
	{
		return organizationId;
	}

	public void setOrganizationId(String organizationId)
	{
		this.organizationId = organizationId;
	}

	/**
	 * Check if Job is running -WEB
	 *
	 * @return
	 */
	public String isRunning()
	{
		String running = "Stopped";
		Set jobNames = jobs.keySet();
		for (Iterator iter = jobNames.iterator(); iter.hasNext();)
		{
			String jobName = (String) iter.next();
			Job job = (Job) jobs.get(jobName);
			running = "Started";
		}
		return running;
	}

	public String toString()
	{
		String newLine = "<br>";
		StringBuffer buffer = new StringBuffer();
		buffer.append("[PuridiomTimer:");
		buffer.append(" has the following jobs: " + newLine);
		Set jobNames = jobs.keySet();
		for (Iterator iter = jobNames.iterator(); iter.hasNext();)
		{
			String jobName = (String) iter.next();
			Job job = (Job) jobs.get(jobName);
			buffer.append(job);
		}

		buffer.append(" organizationId: ");
		buffer.append(organizationId);
		buffer.append("]");
		return buffer.toString();
	}

	public boolean isStarted()
	{
		return started;
	}

	public void setStarted(boolean started)
	{
		this.started = started;
	}

	/**
	 * @return the updateJob
	 */
	public UpdateJob getUpdateJob()
	{
		return updateJob;
	}

	/**
	 * @param updateJob
	 *            the updateJob to set
	 */
	public void setUpdateJob(UpdateJob updateJob)
	{
		this.updateJob = updateJob;
	}

	private void sendStatusEmailNotification()
	{
		String sendFrom = "";
		String sendTo = "";
		String sendCc = "";
		String sendBcc = "";
		String subject = "";
		String emailBody = "";

		Date lastDate = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss z");

		try
		{
			lastDate = dateFormat.parse(this.getUpdateJob().getScheduleLastRun());
		} catch (ParseException ex)
		{
			Log.error(this, "It is not possible to parse the specified time ... \n" + ex.getMessage());
		}

		try
		{
			sendFrom = "support@puridiom.com";
			sendTo = UserManager.getInstance().getUser(this.organizationId, "SYSADM").getMailId();
			sendBcc = PropertiesManager.getInstance(organizationId).getProperty("EMAILPROPS", "BCC", "");
			subject = this.organizationId + " PS did not finish [" + dateFormat.format(new Date()) + "]";
			emailBody = "A PS process has been running since " + this.getUpdateJob().getScheduleLastRun() + " (" + this.getFormatedTimeDifference(new Date(), lastDate) + "). \r\n" + "There are " + this.getNumberOfUnprocessedEmails() + " unprocessed records in SendQueue. \r\n" + "You should check the process as it may have been abruptly terminated. \r\n";

			EmailManager.getInstance().sendEmailTo(sendFrom, new String[] {sendTo}, sendCc, sendBcc, subject, emailBody, null, this.organizationId);

		} catch (Exception exception)
		{
			Log.error(this, "The email notification could not been sent");
			exception.printStackTrace();
		}
	}

	private int getNumberOfUnprocessedEmails()
	{
		Log.debug(this, "retrieving number of records in SendQueue not processed yet... ");

		String status = "00";
		int recordsNotProcessed = 0;

		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.getOrganizationId());
			PuridiomProcess process = processLoader.loadProcess("sendqueue-retrieve-by-status.xml");

			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", this.getOrganizationId());
			incomingRequest.put("SendQueue_status", status);

			process.executeProcess(incomingRequest);
			if (process.getStatus() == Status.SUCCEEDED)
			{
				List sendQueueList = (List) incomingRequest.get("sendQueueList");
				recordsNotProcessed = sendQueueList.size();
				Log.debug(this, "size: " + recordsNotProcessed);
			}
		} catch (Exception e)
		{
			Log.error(this, "getNumberOfEmailsNotProcessed failed: \n" + e.getMessage());
		}

		return recordsNotProcessed;
	}
	
	private int autoGenDocType()
	{
		String status = "03";
		int recordsNotProcessed = 0;

		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.getOrganizationId());
			PuridiomProcess process = processLoader.loadProcess("autogen-update-ps.xml");
			
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", this.getOrganizationId());
			incomingRequest.put("AutoGen_documentType", "PC");
			incomingRequest.put("AutoGen_genYear", "2011");
			incomingRequest.put("AutoGen_activeYear","Y");
			incomingRequest.put("AutoGen_status","03");
			incomingRequest.put("AutoGen_owner","Puridiom");

			process.executeProcess(incomingRequest);
		} catch (Exception e)
		{
			Log.error(this, "can't insert in autogen failed: \n" + e.getMessage());
		}

		return recordsNotProcessed;
	}

	public String getFormatedTimeDifference(Date recentDate, Date oldDate)
	{
		String formattedDifference = "";

		double days = (double) (recentDate.getTime() - oldDate.getTime()) / (1000 * 60 * 60 * 24);
		double hours = (days - (int) days) * 24;
		double minutes = (hours - (int) hours) * 60;
		double seconds = (minutes - (int) minutes) * 60;

		formattedDifference = (days == 0) ? "" : ((int) days + " days, ");
		formattedDifference += (hours == 0) ? "" : ((int) hours + " hours, ");
		formattedDifference += (minutes == 0) ? "" : ((int) minutes + " minutes, ");
		formattedDifference += (seconds == 0) ? "" : ((int) seconds + " seconds");

		if (formattedDifference.endsWith(", "))
			formattedDifference = formattedDifference.substring(0, formattedDifference.length() - 2);

		return formattedDifference;
	}
}