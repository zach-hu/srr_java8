/*
 * Created on Dec 3, 2003
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.inventory.forwardSetReqLineStatus.java
 * Update Requisition Line with qty disbursed.
 * if Requisition Line was disbursed from multiple bins, make sure the
 * right amount is set. RR- 12/30/04
 */
 
package com.tsa.puridiom.inventory.tasks.forward;

import java.math.BigDecimal;
import java.util.*;

import com.tsa.puridiom.entity.DisbLine;
import com.tsagate.foundation.processengine.Task;


public class GetReqLinesIc extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.common.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List disbLines= (List)incomingRequest.get("disbLines");
		Map icReqLines = new HashMap();
		for (Iterator iter = disbLines.iterator(); iter.hasNext();)
		{
			DisbLine disbLine = (DisbLine) iter.next();
			BigDecimal icReqLine = disbLine.getIcReqLine();
			BigDecimal tempQty = (BigDecimal)icReqLines.get(icReqLine);
			if(tempQty != null)
			{
			    tempQty = tempQty.add(disbLine.getQuantity());
			}
			else
			{
			    tempQty = disbLine.getQuantity();
			}
			icReqLines.put(icReqLine, tempQty);
		}
		return icReqLines;
	}

}
