package com.tsa.puridiom.timer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;

public class ScheduledJob extends TimerTask
{
	private int times = 0;
	private boolean running = false;
	private boolean started = false;
	private String organizationId = "";
	private Calendar created = null;
	private Calendar nextRun = null;
	private Calendar lastRun = null;
	private long schedule = 0;
	public final static SimpleDateFormat QUEUE_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");
	public final static SimpleDateFormat QUEUE_TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");

	public ScheduledJob(String organizationId)
	{
		this.setOrganizationId(organizationId);
		this.setCreated(Calendar.getInstance());
	}

	public ScheduledJob()
	{
		//System.out.println("starting job");
		this.setCreated(Calendar.getInstance());
	}

	public void onEnd()
	{
		return;
	}

	public void onStart()
	{
		return;
	}

	public void execute()
	{
		//TODO to overwrite
	}

	public void stopJob(){
		this.setStarted(false);
		this.cancel();
	}

	public void setTimes()
	{
		this.addTimes();
		Calendar nextTime = Calendar.getInstance();
		nextTime.setTime(this.getLastRun().getTime());
		nextTime.add(Calendar.MINUTE, Integer.parseInt(String.valueOf(this.getSchedule())));
		this.setNextRun(nextTime);
	}
	public void run()
	{
		this.setLastRun(Calendar.getInstance());
		if(!this.isRunning())
		{
			this.setRunning(true);
			try
			{
				this.onStart();
				this.execute();
			}
			finally
			{
				this.onEnd();
				this.setTimes();
				this.setRunning(false);
			}

		}
		this.setRunning(false);
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
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
	protected void addTimes()
	{
		this.times++;
	}

	public Calendar getCreated() {
		return created;
	}

	public void setCreated(Calendar firstRun) {
		this.created = firstRun;
	}

	public Calendar getNextRun() {
		return nextRun;
	}

	public void setNextRun(Calendar nextRun) {
		this.nextRun = nextRun;
	}

	public long getSchedule() {
		return schedule;
	}

	public void setSchedule(long schedule) {
		this.schedule = schedule;
	}

	public Calendar getLastRun() {
		return lastRun;
	}

	public void setLastRun(Calendar lastRun) {
		this.lastRun = lastRun;
	}

	protected void setAction(Map incomingRequest)
	{
		//do nothing
	}

	public List today()
	{
		List list = new ArrayList();
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.getOrganizationId());
			PuridiomProcess process = processLoader.loadProcess("sendqueue-retrieve-by-action-date.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", this.getOrganizationId());
			incomingRequest.put("SendQueue_dateadded", Dates.today("yyyy/MM/dd", ""));
			this.setAction(incomingRequest);

			process.executeProcess(incomingRequest);
			if(process.getStatus() == Status.SUCCEEDED)
			{
				list = (List)incomingRequest.get("daysList");
			}
		}
		catch (Exception e)
		{
			Log.error(this, e.getMessage() + " -getting today's list");
		}

		return list;
	}

	public String toString()
	{
		String newLine = "<BR>";
			StringBuffer buffer = new StringBuffer();
			buffer.append("[");
			buffer.append(this.getClass().getName());
			buffer.append(" ");
			buffer.append("has run: ");
			buffer.append(this.getTimes());
			buffer.append(newLine);
			buffer.append("\ncreated on: ");
			buffer.append(QUEUE_DATE_FORMAT.format(this.getCreated().getTime()));
			buffer.append(" ");
			buffer.append(QUEUE_TIME_FORMAT.format(this.getCreated().getTime()));
			buffer.append(newLine);
			buffer.append("\nlast known time it run: ");
			if(this.getLastRun() != null)
			{
				buffer.append(QUEUE_DATE_FORMAT.format(this.getLastRun().getTime()));
				buffer.append(" ");
				buffer.append(QUEUE_TIME_FORMAT.format(this.getLastRun().getTime()));
				buffer.append(" ");
			}
			buffer.append(newLine);
			buffer.append("\nnext time scheduled to run: ");
			if(this.getNextRun() != null)
			{
				buffer.append(QUEUE_DATE_FORMAT.format(this.getNextRun().getTime()));
				buffer.append(" ");
				buffer.append(QUEUE_TIME_FORMAT.format(this.getNextRun().getTime()));
				buffer.append(" ");
			}
			buffer.append("]" + newLine);
			buffer.append("Status: ");
			if(!this.isRunning())
			{
				buffer.append("Sleeping");
			}
			else
			{
				buffer.append("Running");
			}
			return buffer.toString();
		}


}
