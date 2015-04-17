/**
 * Created on Jan 6, 2004
 * @author renzo
 * com.tsa.puridiom.formhtml.tasks.GeneralEmailHtmlCreateSetup.java
 */

package com.tsa.puridiom.formhtml.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.SendQueue;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

public class GeneralEmailHtmlCreateSetup extends Task
{
	/**
	 * author EDSAC
	 * it gets the date and userId that made changes
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		Map incomingRequest = (Map) object;
		try
		{
			//SendQueue sendQueue = (SendQueue)incomingRequest.get("sendQueue");
			//String sendQueueArgs = sendQueue.getArgs();
			String sendQueueArgs = (String) incomingRequest.get("args");
			if(!Utility.isEmpty(sendQueueArgs))
			{
				/*parametro_nombre=value@parametro_nombre=value@parametro_nombre=value
				RequisitionHeader_icReqHeader=docic@templateName=requisition.jasper@processName=requisition.xml

				RequisitionHeader_icReqHeader=docic
				templateName=requisition.jasper
				processName=requisition.xml

				where: RequisitionHeader_icReqHeader can also be RfqHeader_icRfqHeader...
				templateName represent jasper file to execute
				processName represents process to retrieve data from.
				*/
				 incomingRequest.put("entityLine","requisitionLineList");
				 incomingRequest.put("ShipToAddress","getShipToAddress");
				 incomingRequest.put("VendorAddress","getVendorAddress");
				 incomingRequest.put("AccountList","getAccountList");
				 incomingRequest.put("DocCommentList","getDocCommentList");
				 incomingRequest.put("EntityType","getRequisitionType");
				 incomingRequest.put("EntityNumber","getRequisitionNumber");

				String partialArgs[] = sendQueueArgs.split("@");
				String entityName[] = sendQueueArgs.split("_");
				incomingRequest.put("status",entityName[0] + "_status");
				incomingRequest.put("entityNameFull","com.tsa.puridiom.entity." + entityName[0]);
				for (int i = 0; i < partialArgs.length; i++) {
					String parameterMap = partialArgs[i];
					String parameterValues[] = parameterMap.split("=");
					incomingRequest.put(parameterValues[0], parameterValues[1]);
				}
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return ret;
	}
}
