/**
 *
 * Created on Jan 23, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoReqMapUdfs.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.po.exceptions.MappingNullException;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;


public class PoRfqMapUdfs extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			RfqHeader rfqHeader = (RfqHeader)incomingRequest.get("rfqHeader");
			Map rfqudfs = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")).getSection("RFQ TO PO");
			Set udfs = rfqudfs.keySet();
			for (Iterator iter = udfs.iterator(); iter.hasNext();)
			{
				String rfqUdf = (String) iter.next();
				String poUdf = (String)rfqudfs.get(rfqUdf);
				try
				{
					incomingRequest.put( MappingUdfs.findPoUdfName(poUdf), MappingUdfs.getRfqUdf(rfqUdf, rfqHeader));
				}
				catch (MappingNullException mne)
				{
					Log.debug(this, poUdf + " is null");
				}
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return null;
	}


}
