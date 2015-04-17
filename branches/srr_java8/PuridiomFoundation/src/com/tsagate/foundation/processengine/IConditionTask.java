/*
 * Created on Sept. 29, 2003
 */
package com.tsagate.foundation.processengine;

/**
 * @author Kelli Knisely
 */
public interface IConditionTask
{
	public String getTaskObjectName();
	public void setTaskObjectName(String string);
	public int getStatus();
	public void setStatus(int status);
	public boolean executeTask(Object object) throws Exception;
}
