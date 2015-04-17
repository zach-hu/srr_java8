/*
 * Created on April 20, 2006
 */
package com.tsa.puridiom.account.tasks;

import java.math.BigDecimal;
import java.util.*;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

/**
 * @author Administrator
 */
public class AccountSetAllocateTotal extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

		String total = (String) incomingRequest.get("RequisitionHeader_total");
		if (Utility.isEmpty(total))
		{
			RequisitionHeader reqHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			if (reqHeader != null)
			{
				BigDecimal bdTotal = reqHeader.getTotal();
				total = bdTotal.toString();
			}
		}

		incomingRequest.put("allocateTotal", total);

        this.setStatus(Status.SUCCEEDED);

		return null;
	}

}
