/**
 *
 * Created on Jan 20, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoLineCreateSetup.java
 *
 */
package com.tsa.puridiom.bomrouting.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.UniqueKeyGenerator;


public class BomRoutingAddSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
		String icRouting = ukg.getUniqueKey().toString();
		incomingRequest.put("BomRouting_icRouting", icRouting);


		this.setStatus(Status.SUCCEEDED);
		return null;
	}

}
