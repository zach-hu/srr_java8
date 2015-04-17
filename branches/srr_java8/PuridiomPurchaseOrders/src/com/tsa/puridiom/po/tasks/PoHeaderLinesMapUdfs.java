package com.tsa.puridiom.po.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.mapping.exceptions.MappingNullException;
import com.tsa.puridiom.mapping.tasks.MappingHeaderToLinesUdfs;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class PoHeaderLinesMapUdfs extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		try
		{
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			Map poHeaderUdfs = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")).getSection("PO HEADER TO LINES");
			Log.debug(this.getName(), "Mappings found: " + poHeaderUdfs.toString());

			Set udfs = poHeaderUdfs.keySet();
			for (Iterator iter = udfs.iterator(); iter.hasNext();)
			{
				String poHeaderUdf = (String) iter.next();
				String poLineUdf = (String)poHeaderUdfs.get(poHeaderUdf);

				try
				{
					if (MappingHeaderToLinesUdfs.getLineUdfName(poLineUdf, "PO").equals("PoLine_commodity") && incomingRequest.get("PoLine_commodity") != null)
					{
						continue;
					}

					if (!HiltonUtility.isEmpty(poLineUdf))
					{
						incomingRequest.put(MappingHeaderToLinesUdfs.getLineUdfName(poLineUdf, "PO"),  MappingHeaderToLinesUdfs.getPoHeaderUdfValue(poHeaderUdf, poHeader));
					}
				}
				catch(MappingNullException mne)
				{
					Log.warn(this.getName(), poLineUdf + " was not found") ;
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
