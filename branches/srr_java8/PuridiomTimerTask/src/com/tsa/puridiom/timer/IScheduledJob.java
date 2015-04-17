package com.tsa.puridiom.timer;

public interface IScheduledJob
{
	public abstract void execute();
	public abstract void run();
	public abstract boolean isRunning();
	public abstract void setRunning(boolean running);
	public abstract String getOrganizationId();
	public abstract void setOrganizationId(String organizationId);
	public abstract int getTimes();

}