/**
 * 
 */
package com.tsa.puridiom.rfqline.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.ItemLookup;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RfqLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Johnny
 */
public class RfqLineSaveasFromLookup extends Task
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

			String icHeaderComment = item.getIcHeaderComment().toString();
			String icLineComment = item.getIcLineComment().toString();
			String newIcHeader = "";
			String newIcLine = "";

			if (incomingRequest.containsKey("rfqLine"))
			{
				RfqLine rfqLine = (RfqLine) incomingRequest.get("rfqLine");
				if (rfqLine != null)
				{
					newIcHeader = rfqLine.getIcRfqHeader().toString();
					newIcLine = rfqLine.getIcRfqLine().toString();
				}
			}

			if (HiltonUtility.isEmpty(newIcHeader) && HiltonUtility.isEmpty(newIcLine))
			{
				newIcHeader = (String) incomingRequest.get("RfqLine_icRfqHeader");
				newIcLine = (String) incomingRequest.get("RfqLine_icRfqLine");
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
