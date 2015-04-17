/**
 *
 * Created on Jan 20, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoLineCreateSetup.java
 *
 */
package com.tsa.puridiom.bomtask.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.UniqueKeyGenerator;


public class BomTaskAddSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
		String icTask = ukg.getUniqueKey().toString();
		incomingRequest.put("BomTask_icTask", icTask);


		this.setStatus(Status.SUCCEEDED);
		return null;
	}

}
