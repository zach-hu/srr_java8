package com.tsa.puridiom.requisition.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.AutoGenType;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

/**
 *
 *
 * @author Alexander
 */
public class PoGetNumberSetupFromReq extends Task
{

	/**
	 *
	 * @param object
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Object executeTask(Object object) throws Exception
    {

		Map incomingRequest = (Map) object;
		RequisitionHeader reqHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");

		try
		{
			String	oid = (String) incomingRequest.get("organizationId") ;
			PropertiesManager properties = PropertiesManager.getInstance(oid);
			String	requisitionFormat = properties.getProperty("AUTONUMBER", "REQFORMAT", "NNNNNN").toUpperCase();
			String	fiscalYear = reqHeader.getFiscalYear();
			String	reqType = reqHeader.getRequisitionType();

			if (Utility.isEmpty(fiscalYear))
			{
				fiscalYear = "1994";
			}

			String	temp = AutoGenType.toString("PO" + reqType, oid) ;
			if (Utility.isEmpty(temp) || temp.equals("PO" + reqType)) {
				temp = "PO" ;
			} else {
				temp = "PO" + reqType ;
			}

			incomingRequest.put("AutoGen_documentType",  temp);
			incomingRequest.put("AutoGen_genYear", fiscalYear);

			String requisitionNumber = reqHeader.getRequisitionNumber();

			int numPos = requisitionFormat.indexOf("N");
			int numLast = requisitionFormat.lastIndexOf("N");

			String documentNumber = requisitionNumber.substring(numPos, numLast + 1);
			incomingRequest.put("documentNumber", documentNumber);

			this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, e.toString());
		}
		return null ;

    }
}