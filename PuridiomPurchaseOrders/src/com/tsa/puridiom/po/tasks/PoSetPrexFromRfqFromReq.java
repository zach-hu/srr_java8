/*
 * Created on Nov 20, 2003
 */
package com.tsa.puridiom.po.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.*;

/**
 * @author Renzo
 */
public class PoSetPrexFromRfqFromReq extends Task
{

	/*
	 * (non-Javadoc)
	 *
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			String disbNumber = (String) incomingRequest.get("PoHeader_poNumber");

			String organizationId = (String) incomingRequest.get("organizationId");
			String poFromRfqFromReq = "";
			String icRfqHeader = (String) incomingRequest.get("RfqHeader_icRfqHeader");
			String icReqHeader = (String) incomingRequest.get("RequisitionHeader_icReqHeader");
			String prefixNumber = (String) incomingRequest.get("PoHeader_prefix");
			RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			String requisitionNumber = null;
			if (requisitionHeader != null)
			{
				requisitionNumber = requisitionHeader.getRequisitionNumber();
			}
			if (!HiltonUtility.isEmpty(disbNumber) && !HiltonUtility.isEmpty(prefixNumber))
			{
				disbNumber = prefixNumber + disbNumber;
				incomingRequest.put("PoHeader_poNumber", disbNumber);
			}

			if (!HiltonUtility.isEmpty(requisitionNumber) && !HiltonUtility.isEmpty(icRfqHeader) && !HiltonUtility.isEmpty(icReqHeader))
			{
				poFromRfqFromReq = requisitionNumber.substring(0, 1);
				disbNumber = poFromRfqFromReq + disbNumber;
				incomingRequest.put("PoHeader_poNumber", disbNumber);
			}
			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, e.toString());
		}
		return result;
	}

}
