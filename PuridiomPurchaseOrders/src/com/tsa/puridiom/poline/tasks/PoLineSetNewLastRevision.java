/**
 *
 * Created on Jan 22, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoLineRetrieveByHeader.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

import org.hibernate.*;

public class PoLineSetNewLastRevision extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		try
		{
	        List poLineList = (ArrayList)incomingRequest.get("poLineList");
	        List newPoLineList = new ArrayList();

	        for (int m=0; m < poLineList.size(); m++)
	        {
	        	PoLine poLine = (PoLine)poLineList.get(m);
	        	poLine.setStatus(DocumentStatus.PO_AWARDED);
	        	newPoLineList.add(poLine);
	        }
	        result = newPoLineList;

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}

		return result ;
	}

}
