package com.tsa.puridiom.shipto.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.math.BigDecimal;
import java.util.*;

public class ShipToSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain shipTo, newShipTo_icHeader, and newShipTo_icLine */
			String icHeader = (String)incomingRequest.get("newShipTo_icHeader");
			String icLine = (String)incomingRequest.get("newShipTo_icLine");
			ShipToPK comp_id = new ShipToPK();
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			ShipTo	originalShipTo = (ShipTo) incomingRequest.get("shipTo");
			ShipTo	shipTo = new ShipTo();

			comp_id.setIcHeader(new BigDecimal(icHeader));
			comp_id.setIcLine(new BigDecimal(icLine));
			comp_id.setShipToCode(originalShipTo.getComp_id().getShipToCode());
			shipTo.setQuantity(originalShipTo.getQuantity());
			shipTo.setAttention(originalShipTo.getAttention());
			shipTo.setShipDate(originalShipTo.getShipDate());
			comp_id.setShipToPriority(originalShipTo.getComp_id().getShipToPriority());
			shipTo.setCodeType(originalShipTo.getCodeType());
			shipTo.setComp_id(comp_id);

			incomingRequest.put("shipTo", shipTo);

			ShipToAdd addTask = new ShipToAdd();
			shipTo = (ShipTo) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = shipTo;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}