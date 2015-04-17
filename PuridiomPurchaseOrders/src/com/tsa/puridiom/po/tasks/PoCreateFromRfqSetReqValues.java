/**
 * Created on Sept 18, 2006
 * @author Kelli
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoCreateFromRfqSetReqValues.java
 */
package com.tsa.puridiom.po.tasks;

import java.text.SimpleDateFormat;
import java.util.Map;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class PoCreateFromRfqSetReqValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String	organizationId = (String) incomingRequest.get("organizationId");
		String	userId = (String) incomingRequest.get("userId");
		String userDateFormat = (String) incomingRequest.get("userDateFormat");

		if (Utility.isEmpty(userDateFormat)) {
            userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
        }

		RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
		RfqHeader rfqHeader = (RfqHeader)incomingRequest.get("rfqHeader");

		if (requisitionHeader != null) {
			incomingRequest.put("PoHeader_billToContact", requisitionHeader.getBillToContact());
			incomingRequest.put("PoHeader_billToCode", requisitionHeader.getBillToCode());
			SimpleDateFormat format = new SimpleDateFormat(userDateFormat);
			String requiredDate = format.format(requisitionHeader.getRequiredDate());
			incomingRequest.put("PoHeader_requiredDate", requiredDate);
			incomingRequest.put("PoHeader_bidWaiver", requisitionHeader.getBidWaiver());
			incomingRequest.put("PoHeader_workOrder", requisitionHeader.getWorkOrder());
			incomingRequest.put("PoHeader_msrNumber", requisitionHeader.getMsrNumber());
			incomingRequest.put("PoHeader_icMsrHeader", requisitionHeader.getIcMsrHeader().toString());
			if (Utility.isEmpty(rfqHeader.getUdf10Code()))
			{
				incomingRequest.put("PoHeader_udf10Code", requisitionHeader.getUdf10Code());
			}
		}

		this.setStatus(Status.SUCCEEDED);

		return null;
	}
}