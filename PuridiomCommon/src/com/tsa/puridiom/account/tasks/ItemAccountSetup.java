/**
 *
 * Created on Feb 15, 2005
 * @author kathleen
 * com.tsa.puridiom.account.tasks.ItemAccountSetup.java
 *
 */
package com.tsa.puridiom.account.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class ItemAccountSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			String formtype = (String) incomingRequest.get("formtype");
			String headerIc = (String) incomingRequest.get("icHeader");
			String lineIc = (String) incomingRequest.get("icLine");

			if (Utility.isEmpty(headerIc) || Utility.isEmpty(lineIc))
			{
				if (formtype.equalsIgnoreCase("REQ"))
				{
					headerIc = (String) incomingRequest.get("RequisitionHeader_icReqHeader");
					lineIc = (String) incomingRequest.get("RequisitionLine_icReqLine");
					incomingRequest.put("RequisitionLine_icAccount",	"0") ;
				}
				else if (formtype.equalsIgnoreCase("PO"))
				{
					headerIc = (String) incomingRequest.get("PoHeader_icPoHeader");
					lineIc = (String) incomingRequest.get("PoLine_icPoLine");
					incomingRequest.put("PoLine_icAccount",	"0") ;
				}
				else if (formtype.equalsIgnoreCase("DSB"))
				{
					headerIc = (String) incomingRequest.get("DisbHeader_icDsbHeader");
					lineIc = (String) incomingRequest.get("DisbLine_icDsbLine");
					incomingRequest.put("DisbLine_icAccount",	"0") ;
				}
			}
			incomingRequest.put("Account_icHeader", headerIc);
			incomingRequest.put("Account_icLine", lineIc);
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}

}
