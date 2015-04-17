/*
 * Created on Aug 19, 2003
 * Modifed Feb 10, 2005 -KK
 */
package com.tsa.puridiom.requisition.tasks;

import java.util.*;
//import org.hibernate.Hibernate;
//import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionLine;
//import com.tsa.puridiom.poline.exceptions.PoLineNotFoundException;
//import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
//import com.tsagate.foundation.utility.TsaException;

public class RequisitionLineDeleteByHeader extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		try
		{

			List	requisitionLineList = (List)incomingRequest.get("requisitionLineList");

			if (requisitionLineList != null)
			{
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("requisitionline-delete.xml");

				for (int i=0; i < requisitionLineList.size(); i++)
				{
					RequisitionLine requisitionLine = (RequisitionLine) requisitionLineList.get(i);

					incomingRequest.put("organizationId", (String)incomingRequest.get("organizationId"));
					incomingRequest.put("requisitionLine", requisitionLine);
					incomingRequest.put("RequisitionHeader_icReqHeader", String.valueOf(requisitionLine.getIcReqHeader()));
					incomingRequest.put("RequisitionLine_icReqHeader", String.valueOf(requisitionLine.getIcReqHeader()));
					incomingRequest.put("RequisitionLine_icReqLine", String.valueOf(requisitionLine.getIcReqLine()));
					incomingRequest.put("deleteAction", "BY-HEADER");

					process.executeProcess(incomingRequest);

					if (process.getStatus() < Status.SUCCEEDED)
					{
						throw new Exception("Requisition Line save as process failed.");
					}
				}
			}

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
			//throw new TsaException(this.getName(), e);
		}
		return null;
	}
}
