/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.account.tasks;
import java.util.*;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
/**
 * @author Administrator
 */
public class AccountListUpdate extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest= (Map) object;
		Object result= null;
		this.setStatus(Status.SUCCEEDED);
		try
		{
			List accountList= (List) incomingRequest.get("accountList");
			Iterator it= accountList.iterator();
			while (it.hasNext())
			{
				incomingRequest.put("account", it.next());
				AccountUpdate aTask= new AccountUpdate();
				aTask.executeTask(incomingRequest);
				if (aTask.getStatus() != Status.SUCCEEDED)
				{
					this.setStatus(Status.FAILED);
					break;
				}
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
