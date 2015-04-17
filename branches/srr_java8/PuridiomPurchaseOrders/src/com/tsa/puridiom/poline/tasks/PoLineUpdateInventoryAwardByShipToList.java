/**
 * Created on Dec 3, 2007
 * @author Kelli
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoLineUpdateInventoryAwardByShipToList.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class PoLineUpdateInventoryAwardByShipToList extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
            PoLine poLine = (PoLine) incomingRequest.get("poLine");
            String  itemNumber = poLine.getItemNumber();
            List shipToList =(List) incomingRequest.get("shipToList");

            if (shipToList != null && shipToList.size() > 0) {
                for (int i = 0; i < shipToList.size(); i++)
                {
    //                ShipTo
                }                
            }
            
            
            for (int i = 0; i < shipToList.size(); i++)
			{

				if(!Utility.isEmpty(itemNumber))
				{
					PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
					PuridiomProcess process = processLoader.loadProcess("poline-update-inventory-award.xml");
					incomingRequest.put("poLine", poLine);
                    process.executeProcess(incomingRequest);
				}
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return super.executeTask(object);
	}

}
