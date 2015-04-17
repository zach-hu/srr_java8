package com.tsa.puridiom.requisitionline.autorelease.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.autorelease.RequisitionLineAutoReleaseObject;
import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.Catalog;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.ShipTo;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RequisitionLineGroupByOrder extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		Map byOrder = new HashMap();
		try
		{
			List requisitionLineList = (List)incomingRequest.get("requisitionLineList");
			for(int i = 0; i < requisitionLineList.size(); i++)
			{
				Object data[] = (Object[])requisitionLineList.get(i);
				RequisitionLine reqLine = (RequisitionLine)data[1];
				Catalog catalog = (Catalog)data[3];
				if(catalog.getConsolidateReleases().equalsIgnoreCase("Y"))
				{
					String blanketOrder = reqLine.getBlanketOrder();

					this.getLines(data, this.getByShipToMap(blanketOrder, byOrder), (String)incomingRequest.get("organizationId"));
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

	public void getLines(Object data[], Map byShipTo, String organizationId)
	{
		String shipToCode = this.getShipToCode(data, organizationId);
		List lines = null;
		if(byShipTo.containsKey(shipToCode))
		{
			lines = (List)byShipTo.get(shipToCode);
		}
		else
		{
			lines = new ArrayList();
		}
		lines.add(new RequisitionLineAutoReleaseObject(data));
		 byShipTo.put(shipToCode, lines);
	}

	public Map getByShipToMap(String blanketOrder, Map byOrder)
	{
		if(!byOrder.containsKey(blanketOrder))
		{
			byOrder.put(blanketOrder, new HashMap());
		}

		return (Map)byOrder.get(blanketOrder);
	}

	public String getShipToCode(Object data[], String organizationid)
	{
		RequisitionHeader reqHeader = (RequisitionHeader)data[0];
		ShipTo shipTo = (ShipTo)data[2];
		String shipToCode = reqHeader.getShipToCode();
		if(shipTo != null)
		{
			shipToCode = shipTo.getShipToCode();
		}
		shipToCode = this.getShipToGroupByKey(organizationid, shipToCode);
		return shipToCode;
	}

	public String getShipToGroupByKey(String organizationId, String shipToCode)
	{
		String shipToKey = shipToCode;
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);

	        PuridiomProcess process = processLoader.loadProcess("shipto-address-retrieve-by-id.xml");
	        Map newIncomingRequest = new HashMap();
	        newIncomingRequest.put("organizationId", organizationId);
	        newIncomingRequest.put("Address_addressCode", shipToCode);
	        process.executeProcess(newIncomingRequest);
	        if(process.getStatus() == Status.SUCCEEDED)
	        {
	        	Address shipToAddress = (Address)newIncomingRequest.get("shipToAddress");
	        	shipToKey = shipToAddress.getAddrFld11();
	        }
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
		return shipToKey;
	}
}
