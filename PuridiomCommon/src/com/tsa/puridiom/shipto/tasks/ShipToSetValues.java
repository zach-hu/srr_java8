package com.tsa.puridiom.shipto.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class ShipToSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			ShipToPK comp_id = new ShipToPK();
			ShipTo shipTo = (ShipTo) incomingRequest.get("shipTo");
			if (shipTo == null)
			{
				shipTo = new ShipTo();
			}

			if (incomingRequest.containsKey("ShipTo_icHeader"))
			{
				String icHeaderString = (String) incomingRequest.get("ShipTo_icHeader");
				if (Utility.isEmpty(icHeaderString))
				{
					icHeaderString = "0";
				}
				BigDecimal icHeader = new BigDecimal ( icHeaderString );
				comp_id.setIcHeader(icHeader);
			}
			if (incomingRequest.containsKey("ShipTo_icLine"))
			{
				String icLineString = (String) incomingRequest.get("ShipTo_icLine");
				if (Utility.isEmpty(icLineString))
				{
					icLineString = "0";
				}
				BigDecimal icLine = new BigDecimal ( icLineString );
				comp_id.setIcLine(icLine);
			}
			if (incomingRequest.containsKey("ShipTo_shipToCode"))
			{
				String shipToCode = (String ) incomingRequest.get("ShipTo_shipToCode");
				comp_id.setShipToCode(shipToCode);
			}
			if (incomingRequest.containsKey("ShipTo_quantity"))
			{
				String quantityString = (String) incomingRequest.get("ShipTo_quantity");
				if (Utility.isEmpty(quantityString))
				{
					quantityString = "0";
				}
				BigDecimal quantity = new BigDecimal ( quantityString );
				shipTo.setQuantity(quantity);
			}
			if (incomingRequest.containsKey("ShipTo_attention"))
			{
				String attention = (String ) incomingRequest.get("ShipTo_attention");
				shipTo.setAttention(attention);
			}
			if (incomingRequest.containsKey("ShipTo_shipDate"))
			{
				String shipDateString = (String) incomingRequest.get("ShipTo_shipDate");
				Date shipDate = Dates.getDate(shipDateString);
				shipTo.setShipDate(shipDate);
			}
			if (incomingRequest.containsKey("ShipTo_shipToPriority"))
			{
				String shipToPriority = (String ) incomingRequest.get("ShipTo_shipToPriority");
				comp_id.setShipToPriority(shipToPriority);
			}
			if (incomingRequest.containsKey("ShipTo_codeType"))
			{
				String codeType = (String ) incomingRequest.get("ShipTo_codeType");
				shipTo.setCodeType(codeType);
			}
			shipTo.setComp_id(comp_id);

			result = shipTo;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}