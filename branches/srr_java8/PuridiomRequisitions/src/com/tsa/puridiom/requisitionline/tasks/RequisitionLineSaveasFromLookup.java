/**
 * 
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.ItemLookup;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Johnny
 */
public class RequisitionLineSaveasFromLookup extends Task
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map) object;

		try
		{
			ItemLookup item = (ItemLookup) incomingRequest.get("itemLookup");
			
			String	icHeaderComment = item.getIcHeaderComment().toString() ;
			String	icLineComment = item.getIcLineComment().toString() ;
			String newIcHeader = "";
			String newIcLine = "";

			if (incomingRequest.containsKey("requisitionLine"))
			{
				RequisitionLine rql = (RequisitionLine) incomingRequest.get("requisitionLine");
				if (rql != null)
				{
					newIcHeader = rql.getIcReqHeader().toString();
					newIcLine = rql.getIcReqLine().toString();
				}
			}
			
			if (HiltonUtility.isEmpty(newIcHeader) && HiltonUtility.isEmpty(newIcLine))
			{
				newIcHeader = (String) incomingRequest.get("RequisitionLine_icReqHeader");
				newIcLine = (String) incomingRequest.get("RequisitionLine_icReqLine");
			}

			incomingRequest.put("DocComment_icHeader", icHeaderComment);
			incomingRequest.put("DocComment_icLine", icLineComment);

			incomingRequest.put("newDocComment_icHeader", newIcHeader);
			incomingRequest.put("newDocComment_icLine", newIcLine);

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null;
	}

}
