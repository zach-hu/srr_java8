package com.tsa.puridiom.timer;

import java.util.Calendar;
import java.util.TimeZone;

import com.tsagate.foundation.utility.Dates;

public class PSStart implements Runnable
{
	private boolean done = false;
	private boolean running = false;
	private String currentOid = "NONE";
	private int psInterval = 10;
	private String oids[];


	private static final PSStart instance = new PSStart();

	private PSStart() {	}

	public static final PSStart getInstance()
	{
		return instance;
	}

	private void startPuridiomServices()
	{
		if(oids == null){		return;		}
		this.running = true;
		for(int i = 0; i < oids.length; i++)
		{
			String oid = (String)oids[i];
			this.currentOid = oid;
			System.out.println("Schedule starts at " + Calendar.getInstance().getTime() + " for oid: " + oid);
			try
			{
				ScheduleManager.getInstance().start(oid);
			}
			catch (Exception e)
			{
				System.err.println("Puridiom Services for " + oid + " could not be executed[" + e.getMessage() + "]") ;
			}
			System.out.println("Schedule done: " + Dates.getNow(""));
		}
		this.running = false;
		this.currentOid = "NONE";
	}//END startPuridiomServices

	public void run()
	{
		int count = 0;

		while (!done)
		{
			try
			{
				if ((count % this.psInterval) == 0)
				{
					startPuridiomServices();
				}

				Thread.sleep(1000L * 60);
				count++;
			}
			catch (Throwable t)
			{
				System.out.println("Error Starting PuridiomServices." + t.getMessage());
				t.printStackTrace(System.out);
			}

		}
	}//END run

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		if(args == null || args.length < 1)
		{
			args = new String[1];
			args[0] = "bly07p";
		}
		if(args != null && args.length > 0)
		{
			Runtime runtime = Runtime.getRuntime();
			long heapMaxSize = runtime.maxMemory() / 1024;
			System.err.println("Allocated memory: " + heapMaxSize) ;
			System.err.println("free memory: " + runtime.freeMemory()/ 1024) ;

			for (int i = 0; i < args.length; i++)
			{
				System.out.println("Schedule starts at " + Calendar.getInstance().getTime() + "for oid: " + args[i]);
				System.err.println("Initial used memory: " + (runtime.totalMemory()  - runtime.freeMemory())/ 1024) ;

				ScheduleManager.getInstance().start(args[i]);
				System.out.println("Schedule done: " + Dates.getNow("", "") + " " + TimeZone.getDefault().getID());
				System.err.println("Used memory: " + (runtime.totalMemory()  - runtime.freeMemory())/ 1024) ;
				System.err.println("free memory: " + runtime.freeMemory()/ 1024) ;
				runtime.gc();
				runtime.gc();
				System.err.println("after gc: " + runtime.freeMemory()/ 1024) ;
				System.err.println("after gc used memory: " + (runtime.totalMemory()  - runtime.freeMemory())/ 1024) ;
			}
		}
		System.exit(0);
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public String[] getOids() {
		return oids;
	}

	public void setOids(String[] oids) {
		this.oids = oids;
	}

	public int getPsInterval() {
		return psInterval;
	}

	public void setPsInterval(int psInterval) {
		this.psInterval = psInterval;
	}
	public void setPsInterval(String psInterval)
	{
		try
		{
			this.psInterval = Integer.parseInt(psInterval);
		}
		catch (Exception e)
		{
			System.err.println("Error setting PuridiomServices interval. " + e.getMessage());
			this.psInterval = 10;
		}
	}

	public boolean isRunning() {
		return running;
	}

	public String getCurrentOid() {
		return currentOid;
	}
}
