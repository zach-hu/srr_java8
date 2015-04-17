package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class PoPyStatusSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object result = null;
		Map incomingRequest = (Map)object;

		try
		{
			List invoicedQuantitiesList = (List)incomingRequest.get("invoicedList");
			List poLineList = (List)incomingRequest.get("poLineList");
			List poLines = new ArrayList();
			for (int i = 0; i < poLineList.size(); i++)
			{
				PoLine poLine = (PoLine)poLineList.get(i);
				if(!poLine.getStatus().equals(DocumentStatus.CANCELLED)){
					BigDecimal invoiceQty = (BigDecimal)invoicedQuantitiesList.get(i);
					if(invoiceQty.compareTo(poLine.getQuantity()) >= 0)
					{
						poLine.setPyStatus("6060");
					}
					else if(invoiceQty.compareTo(new BigDecimal(0)) > 0)
					{
						poLine.setPyStatus("6055");
					}
					else
					{
						poLine.setPyStatus("6050");
					}
				}
				poLines.add(poLine);
			}
			incomingRequest.put("poLines", poLines);
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			e.printStackTrace();
			throw e;
		}
		return result;
	}
}
