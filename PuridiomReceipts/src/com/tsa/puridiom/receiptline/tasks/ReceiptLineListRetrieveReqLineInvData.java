package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.entity.InvItem;
import com.tsa.puridiom.entity.InvLocation;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.*;

import java.util.*;

public class ReceiptLineListRetrieveReqLineInvData extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("invlocation-retrieve-by-item.xml");

			List receiptLineList = (List) incomingRequest.get("receiptLineList");

			if (receiptLineList != null && receiptLineList.size() > 0)
			{
				for(int i = 0; i < receiptLineList.size(); i++)
				{
					ReceiptLine receiptLine = (ReceiptLine)receiptLineList.get(i);
					
					if (receiptLine != null)
					{
						String itemNumber = "";
						if (receiptLine.getRequisitionLine() != null)
						{
							itemNumber = receiptLine.getRequisitionLine().getItemNumber();
						}

						Map requestParameters = new HashMap();
						requestParameters.put("userId", incomingRequest.get("userId"));
						requestParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
						requestParameters.put("organizationId", incomingRequest.get("organizationId"));
						requestParameters.put("dbsession", incomingRequest.get("dbsession"));
						requestParameters.put("InvItem_itemNumber", itemNumber);
						requestParameters.put("InvLocation_itemNumber", itemNumber);

						process.executeProcess(requestParameters);

						if (process.getStatus() == Status.SUCCEEDED)
						{
							InvItem invItem = (InvItem)requestParameters.get("invItem");

							List invLocationList = (List)requestParameters.get("invLocation");
							if (invLocationList != null && invLocationList.size() > 0)
							{
								for(int j = 0; j < invLocationList.size(); j++)
								{
									InvLocation invLocation = (InvLocation)invLocationList.get(j);
									if (invLocation != null && invLocation.getComp_id().getItemLocation().equalsIgnoreCase(receiptLine.getItemLocation()))
									{
										receiptLine.setInvLocation(invLocation);
										break;
									}
								}
							}

							receiptLine.setInvItem(invItem);
						}
						receiptLineList.set(i, receiptLine);
					}
				}
			}

			result = receiptLineList;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
