package com.tsa.puridiom.shipto.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.ShipTo;
import com.tsa.puridiom.entity.ShipToPK;

public class ShipToSetValuesPK
{
	public void setValues(Map incomingRequest, ShipTo shipto ) throws Exception
	{
		try
		{
			String icHeaderString = (String) incomingRequest.get("ShipTo_icHeader");
			BigDecimal icHeader = new BigDecimal ( icHeaderString );
			String icLineString = (String) incomingRequest.get("ShipTo_icLine");
			BigDecimal icLine = new BigDecimal ( icLineString );
			String shipToCode = (String ) incomingRequest.get("ShipTo_shipToCode");
			String shipToPriority = (String ) incomingRequest.get("ShipTo_shipToPriority");
			ShipToPK comp_id = new ShipToPK();
			comp_id.setIcHeader(icHeader);
			comp_id.setIcLine(icLine);
			comp_id.setShipToCode(shipToCode);
			comp_id.setShipToPriority(shipToPriority);
			shipto.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}