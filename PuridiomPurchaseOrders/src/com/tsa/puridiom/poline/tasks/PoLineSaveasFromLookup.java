/**
 * 
 */
package com.tsa.puridiom.poline.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.ItemLookup;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Johnny
 */
public class PoLineSaveasFromLookup extends Task
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

			PoLine pol = (PoLine) incomingRequest.get("poLine");

			String icHeaderComment = item.getIcHeaderComment().toString();
			String icLineComment = item.getIcLineComment().toString();

			String newIcHeader = pol.getIcPoHeader().toString();
			String newIcLine = pol.getIcPoLine().toString();

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
