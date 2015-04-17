/*
 * Created on Nov 14, 2003
 */
package com.tsa.puridiom.shipto.tasks;

import com.tsa.puridiom.entity.ShipTo ;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;

/**
 * @author Jeff / Kelli
 */
public class ShipToSaveasList extends Task
{
	
	/**
	 * executeTask
	 * <p>Thismethod takes a ShipTo List coming from incoming request(shipToList)</p>
	 * <p> and changes the header ic(ShipTo_icHeader) and line ic(ShipTo_icLine) for each one of them.</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List shipToList = (List)incomingRequest.get("shipToList");
		for(int row = 0; row < shipToList.size(); row++)
		{
			ShipTo shipTo = (ShipTo)shipToList.get(row);
			
			incomingRequest.put("shipTo", shipTo);
			
			ShipToSaveas saveas = new ShipToSaveas();
			shipTo = (ShipTo) saveas.executeTask(incomingRequest);
			
			if (saveas.getStatus() != Status.SUCCEEDED)
			{
				throw new Exception("Ship To Saveas failed.");		
			}
			
			shipToList.set(row, shipTo);
			this.setStatus(Status.SUCCEEDED);
		}
		if(shipToList.size() == 0)
		{
			this.setStatus(Status.SUCCEEDED);
		}
		return shipToList;
	}

}
