/**
 * 
 */
package com.tsa.puridiom.catalog.tasks;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Catalog;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author Johnny
 */
public class CatalogItemCreateFromRequisitionLines extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		try
		{
			Map incomingRequest = (Map) object;
			String organizationId = (String) incomingRequest.get("organizationId");
			RequisitionHeader reqHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			List reqList = (List) incomingRequest.get("requisitionLineList");
			Catalog catalog;
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
			PuridiomProcess process;

			if (reqList == null)
			{
				reqList = (List) incomingRequest.get("reqLines");
			}

			if (!HiltonUtility.isEmpty(reqHeader.getVendorId()))
			{
				incomingRequest.put("Catalog_catalogId", reqHeader.getVendorId());

				process = processLoader.loadProcess("catalog-retrieve-by-id.xml");

				process.executeProcess(incomingRequest);

				catalog = (Catalog) incomingRequest.get("catalog");

				if (catalog != null)
				{
					for (Iterator iter = reqList.iterator(); iter.hasNext();)
					{
						RequisitionLine reqLine = (RequisitionLine) iter.next();

						if ((!HiltonUtility.isEmpty(reqLine.getItemNumber())) && (HiltonUtility.isEmpty(reqLine.getCatalogId())) && (reqLine.getUdf3Code().equalsIgnoreCase("Y")))
						{
							Map newIncomingRequest = new HashMap();

							newIncomingRequest.put("requisitionLine", reqLine);

							newIncomingRequest.put("CatalogItem_catalogId", catalog.getCatalogId());

							process = processLoader.loadProcess("catalog-item-create-from-requisition-line.xml");

							process.executeProcess(newIncomingRequest);

							if (process.getStatus() != Status.SUCCEEDED)
							{
								throw new TsaException(process.getName() + " Error occurred creating Catalog Item from Requisition Line " + reqLine.getLineNumber() + ", item: "
										+ reqLine.getItemNumber() + ", in Requisition " + reqLine.getRequisitionNumber());
							}
						}
					}
				}
			}

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, e.getMessage());

			throw new TsaException(this.getName(), e);
		}
		return super.executeTask(object);
	}
}