package com.tsa.puridiom.rfq.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.mapping.exceptions.MappingNullException;
import com.tsa.puridiom.mapping.tasks.MappingHeaderToLinesUdfs;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class RfqHeaderLinesMapUdfs extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		try
		{
			RfqHeader rfqHeader = (RfqHeader)incomingRequest.get("rfqHeader");
			Map rfqHeaderUdfs = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")).getSection("RFQ HEADER TO LINES");
			Log.debug(this.getName(), "Mappings found: " + rfqHeaderUdfs.toString());

			Set udfs = rfqHeaderUdfs.keySet();
			for (Iterator iter = udfs.iterator(); iter.hasNext();)
			{
				String rfqHeaderUdf = (String) iter.next();
				String rfqLineUdf = (String)rfqHeaderUdfs.get(rfqHeaderUdf);

				try
				{
					if (!HiltonUtility.isEmpty(rfqLineUdf))
					{
						incomingRequest.put(MappingHeaderToLinesUdfs.getLineUdfName(rfqLineUdf, "RFQ"),  MappingHeaderToLinesUdfs.getRfqHeaderUdfValue(rfqHeaderUdf, rfqHeader));
					}
				}
				catch(MappingNullException mne)
				{
					Log.warn(this.getName(), rfqLineUdf + " was not found") ;
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
