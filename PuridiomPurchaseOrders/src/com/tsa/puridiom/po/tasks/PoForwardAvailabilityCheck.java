/**
 * Created on Jan 18, 2005
 * @author Kelli
 * com.tsa.puridiom.po.tasks.PoForwardAvailabilityCheck.java
 */

package com.tsa.puridiom.po.tasks;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.poheader.tasks.UserErrors;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PoForwardAvailabilityCheck extends Task
{
	/**
	 * Method executeTask.
	 *
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object returnObj = null;
		try
		{
			Map incomingRequest = (Map) object;
			List	requiredFlds = new ArrayList();
			StringBuffer errorMsg = new StringBuffer();
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");

			if (Utility.isEmpty(poHeader.getPoNumber()) || poHeader.getPoNumber().equals("N/A") ) {
			    requiredFlds.add("Po Number");
			}
			if (Utility.isEmpty(poHeader.getVendorId())) {
			    requiredFlds.add("Vendor Id");
			}

			if (requiredFlds.size() > 0) {
			    errorMsg.append("Cannot Forward Without the following required fields: [ ");
			    for (int i=0; i < requiredFlds.size(); i++) {
			        errorMsg.append((String) requiredFlds.get(i));
			        if (i == (requiredFlds.size() - 1)) {
			            errorMsg.append(" ].");
			        } else {
			            errorMsg.append(", ");
			        }
			    }

			    UserErrors userErrors = (UserErrors)incomingRequest.get("userErrors");
			    if (userErrors == null) {	userErrors = new UserErrors();	}
			    userErrors.addError(errorMsg.toString(), PoErrors.CRITICAL);

				incomingRequest.put("userErrors", userErrors);

				this.setPostAction("exitProcess");
				//this.setStatus(Status.COMPLETED);
			}

			returnObj = errorMsg.toString();
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			throw e;
		}
		return returnObj;
	}
}
