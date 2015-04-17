/**
 *
 */
package com.tsa.puridiom.common.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DisbursementType;
import com.tsa.puridiom.common.documents.OrderType;
import com.tsa.puridiom.common.documents.ReceiptType;
import com.tsa.puridiom.common.documents.RequisitionType;
import com.tsa.puridiom.common.documents.RfqType;
import com.tsa.puridiom.common.documents.SourcingType;
import com.tsa.puridiom.common.documents.StdType;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Johnny Zapana
 */
public class TypesListRetrieve extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;
		String organizationId = (String) incomingRequest.get("organizationId");
		String type = (String) incomingRequest.get("type");

		try
		{
			if (type.equals("ORDER-TYPE"))
			{
				result = OrderType.getPropertyMap(organizationId);
			} else if (type.equals("REQUISITION-TYPE"))
			{
				result = RequisitionType.getPropertyMap(organizationId);
			} else if (type.equals("RFQ-TYPE"))
			{
				result = RfqType.getPropertyMap(organizationId);
			} else if ( type.equals("DISBURSEMENT-TYPE") )
			{
				result = DisbursementType.getPropertyMap(organizationId);
			} else if (type.equals("SOURCING-TYPE"))
			{
				result = SourcingType.getPropertyMap(organizationId) ;
			}
			else if (type.equals("AUTOGEN-TYPE"))
			{

			} else if (type.equals("SCHEDULE-TYPE"))
			{

			} else if (type.equals("STDDOCUMENT-TYPE"))
			{

			} else if (type.equals("STDQUESTION-RESPONSE-TYPE"))
			{

			} else if (type.equals("INSPECTIONLEVEL-TYPE"))
			{

			} else if (type.equals("STD-TYPE"))
			{
				result = StdType.getPropertyMap(organizationId);
			} else if (type.equals("RECEIPT-TYPE"))
			{
				result = ReceiptType.getPropertyMap(organizationId);
			}

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result;
	}
}
