/*
 * Created on March 22, 2007
 */
package com.tsa.puridiom.requisition.tasks;

import java.util.*;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.processengine.Status;
/**
 * @author KC
 */
public class RequisitionSetTemplateNumber extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;

		try
		{
			String templateNumber = HiltonUtility.ckNull((String) incomingRequest.get("templateNumber"));
			if (HiltonUtility.isEmpty(templateNumber))
			{
				templateNumber = "TEMPLATE";
			}
			incomingRequest.put("newRequisitionHeader_requisitionNumber", templateNumber);
			incomingRequest.put("newRequisitionLine_requisitionNumber", templateNumber);

			this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null;
	}
}
