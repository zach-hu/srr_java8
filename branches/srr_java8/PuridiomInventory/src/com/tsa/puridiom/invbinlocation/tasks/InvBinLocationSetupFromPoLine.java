/**
 * Created on August 29, 2005
 * @author Kelli
 * project: HiltonInventory
 * com.tsa.puridiom.invbinlocation.tasks.InvBinLocationSetUpFromPoLine.java
 */
package com.tsa.puridiom.invbinlocation.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class InvBinLocationSetupFromPoLine extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		try
		{
		    Map incomingRequest = (Map) object;
			PoLine poLine = (PoLine) incomingRequest.get("poLine");
			if (poLine != null) {
                String itemNumber = poLine.getItemNumber();

                if (!Utility.isEmpty(poLine.getAltItemNumber())) {
                    itemNumber = poLine.getAltItemNumber();
                }

                incomingRequest.put("InvBinLocation_itemNumber", itemNumber);
				incomingRequest.put("InvBinLocation_itemLocation", poLine.getItemLocation());
				// InvBinLocation_icRecLine is initially set to the icPoLine as a temporary bin record
				//	This will be reset to the ReceiptLine_icRecLine when the tempIc is removed and status changed to perm.
				incomingRequest.put("InvBinLocation_icRecLine", poLine.getIcPoLine().toString());
				incomingRequest.put("InvLocation_itemNumber", itemNumber);
				incomingRequest.put("InvLocation_itemLocation", poLine.getItemLocation());
				incomingRequest.put("InvItem_itemNumber", itemNumber);
			} else {
//			    throw new Exception("PoLine entity was not found.");
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			//throw new TsaException(this.getName(), e);
			throw e;
		}
		return super.executeTask(object);
	}

}
