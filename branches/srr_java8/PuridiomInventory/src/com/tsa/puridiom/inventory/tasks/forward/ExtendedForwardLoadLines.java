/*
 * Created on Dec 1, 2003
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.inventoryExtendedForwardLoadLines.java
 */

package com.tsa.puridiom.inventory.tasks.forward;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.Document;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.DisbLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.EntityUtility;


public class ExtendedForwardLoadLines extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.common.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List disbLines = (List)incomingRequest.get("disbLines");

		for(int i = 0; i < disbLines.size(); i++)
		{
			DisbLine disb = (DisbLine)disbLines.get(i);
			incomingRequest.put("qtytodisburse", disb.getQuantity());
			incomingRequest.put("duomqtytodisburse", disb.getDuomQuantity());
			incomingRequest.put("InvBinLocation_icRc", disb.getIcRc().toString());
			incomingRequest.put("InvLocation_itemNumber", disb.getItemNumber());
			incomingRequest.put("InvLocation_itemLocation", disb.getItemLocation());

			int updBin = EntityUtility.executeProcess("forward-update-inventory.xml", incomingRequest);
			this.setStatus(updBin);
			if(updBin != Status.SUCCEEDED)
			{
				return null;
			}

			disb.setStatus(DocumentStatus.INV_DISBURSED);
			disbLines.set(i, disb);
		}

		this.setStatus(Status.SUCCEEDED);

		return disbLines;
	}

}
