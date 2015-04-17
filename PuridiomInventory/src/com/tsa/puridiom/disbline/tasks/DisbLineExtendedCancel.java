/*
 * Created on Dec 12, 2003
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.inventoryDisbLineExtendedCancel.java
 */
 
package com.tsa.puridiom.disbline.tasks;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.DisbLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;


public class DisbLineExtendedCancel extends Task
{
	/**
	 * Method executeTask.
	 *
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			String icReqLineString = (String) incomingRequest.get("DisbLine_icReqLine");
			if(Utility.isEmpty(icReqLineString))
			{
			    this.setStatus(Status.FAILED);
				throw new Exception("invalid Line");
			}
			BigDecimal icReqLine = new BigDecimal(icReqLineString);
			BigDecimal disbQty = new BigDecimal(0);
			List disbLines = (List)incomingRequest.get("disbLines");
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("disbursementline-extended-cancel-process.xml");
			incomingRequest.put("qtyToCancel", new BigDecimal(0));
			for (Iterator iter = disbLines.iterator(); iter.hasNext();)
			{
				DisbLine disbLine = (DisbLine) iter.next();
				if(disbLine.getIcReqLine().compareTo(icReqLine) == 0)
				{
					incomingRequest.put("disbLine", disbLine);
					incomingRequest.put("InvBinLocation_icRc", disbLine.getIcRc().toString());
					
					process.executeProcess(incomingRequest);
					if(process.getStatus() != Status.SUCCEEDED)
					{
						break;
					}
					this.setStatus(process.getStatus());
				}
			}
		}
		catch (Exception e)
		{
			throw new TsaException(this.getName(), e);
		}
		return ret;
	}
}
