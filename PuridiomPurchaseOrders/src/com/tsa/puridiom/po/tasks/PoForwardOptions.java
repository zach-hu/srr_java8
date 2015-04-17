/**
 * Created on Feb 19, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoForwardOptions.java
 *
 */
package com.tsa.puridiom.po.tasks;


import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class PoForwardOptions extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		this.setStatus(Status.SUCCEEDED);
		return "A";
	}
}
