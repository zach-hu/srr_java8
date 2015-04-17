package com.tsa.puridiom.requisitionline.autorelease.tasks;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.autorelease.RequisitionLineAutoReleaseObject;
import com.tsa.puridiom.entity.Catalog;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RequisitionLineGroupByReq extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		Map byOrder = new LinkedHashMap();
		try
		{
			List requisitionLineList = (List)incomingRequest.get("requisitionLineList");
			for(int i = 0; i < requisitionLineList.size(); i++)
			{
				Object data[] = (Object[])requisitionLineList.get(i);
				RequisitionLine reqLine = (RequisitionLine)data[1];
				Catalog catalog = (Catalog)data[3];
				if(catalog.getConsolidateReleases().equalsIgnoreCase("N") && catalog.getAllowReleases().equalsIgnoreCase("Y"))
				{
					String blanketOrder = reqLine.getBlanketOrder();

					this.getLines(data, this.getByReqMap(blanketOrder, byOrder));
				}
			}
			ret = byOrder;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("RequisitionLineGroupByOrder failed to group line items: " + e.getMessage(), e);
		}

		return ret;
	}

	public void getLines(Object data[], Map byReqNumber)
	{
		String reqNumber = this.getReqNumber(data);
		List lines = null;
		if(byReqNumber.containsKey(reqNumber))
		{
			lines = (List)byReqNumber.get(reqNumber);
		}
		else
		{
			lines = new ArrayList();
		}
		lines.add(new RequisitionLineAutoReleaseObject(data));
		 byReqNumber.put(reqNumber, lines);
	}

	public Map getByReqMap(String blanketOrder, Map byOrder)
	{
		if(!byOrder.containsKey(blanketOrder))
		{
			byOrder.put(blanketOrder, new LinkedHashMap());
		}

		return (Map)byOrder.get(blanketOrder);
	}

	public String getReqNumber(Object data[])
	{
		RequisitionHeader reqHeader = (RequisitionHeader)data[0];
		String reqNumber = reqHeader.getRequisitionNumber();

		return reqNumber;
	}

}
