/*
 * Created on Sept. 29, 2003
 */
package com.tsagate.foundation.processengine;

/**
 * @author Kelli Knisely
 */
public class ConditionTask implements IConditionTask
{
	protected String taskObjectName = "";
	protected int status = Status.READY;
	
		
	public String getTaskObjectName()
	{
		return this.taskObjectName;
	}
	public void setTaskObjectName(String string)
	{
		this.taskObjectName = string;
	}
	public int getStatus()
	{
		return this.status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	public boolean executeTask(Object object) throws Exception
	{
		return true;
	}
}
