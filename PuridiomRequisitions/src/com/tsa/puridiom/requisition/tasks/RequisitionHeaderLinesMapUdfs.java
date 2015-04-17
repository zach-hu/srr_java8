package com.tsa.puridiom.requisition.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.CatalogItem;
import com.tsa.puridiom.entity.InvItem;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.mapping.exceptions.MappingNullException;
import com.tsa.puridiom.mapping.tasks.MappingHeaderToLinesUdfs;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

public class RequisitionHeaderLinesMapUdfs extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		try
		{
			RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
			List<CatalogItem> itemList = HiltonUtility.ckNull((List<CatalogItem>) incomingRequest.get("catalogItemList"));
			InvItem invItem = (InvItem) HiltonUtility.ckNull(incomingRequest.get(("invItem")));
			int i = 0;
			String procLevel = "";
			String invProcLevel = "";
			Map requisitionHeaderUdfs = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")).getSection("REQ HEADER TO LINES");
			Log.debug(this.getName(), "Mappings found: " + requisitionHeaderUdfs.toString());

			Set udfs = requisitionHeaderUdfs.keySet();
			for (Iterator iter = udfs.iterator(); iter.hasNext();)
			{
				if (itemList.isEmpty())
				{
					procLevel = "";
				}
				else
				{
					CatalogItem catalogItem = itemList.get(i);
					procLevel = catalogItem.getUdf2Code();
				}
				if (Utility.isObjectEmpty(invItem))
				{
					invProcLevel = "";
				}
				else
				{
					invProcLevel = invItem.getUdf2Code();
				}

				String requisitionHeaderUdf = (String) iter.next();
				String requisitionLineUdf = (String)requisitionHeaderUdfs.get(requisitionHeaderUdf);
				if (requisitionHeaderUdf.equalsIgnoreCase("PRIORITY") && (!Utility.isEmpty(requisitionHeader.getPriorityCode()) && Utility.isEmpty(procLevel) && Utility.isEmpty(invProcLevel)))
				{
					try
					{
						if (MappingHeaderToLinesUdfs.getLineUdfName(requisitionLineUdf, "REQ").equals("RequisitionLine_commodityCode") && incomingRequest.get("RequisitionLine_commodityCode") != null)
						{
							continue;
						}

						if (!HiltonUtility.isEmpty(requisitionLineUdf))
						{
							incomingRequest.put(MappingHeaderToLinesUdfs.getLineUdfName(requisitionLineUdf, "REQ"),  MappingHeaderToLinesUdfs.getRequisitionHeaderUdfValue(requisitionHeaderUdf, requisitionHeader));
						}
					}
					catch(MappingNullException mne)
					{
						Log.warn(this.getName(), requisitionLineUdf + " was not found") ;
					}
				}
				if (itemList.size() - 1 > i)
					i++;
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
