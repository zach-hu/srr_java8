/**
 * 
 */
package com.tsa.puridiom.invitem.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class InvItemFormatNumberSetup extends Task
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			String invItemNumber = (String) incomingRequest.get("InvItem_itemNumber");
			String fiscalYear = (String) incomingRequest.get("PoHeader_fiscalYear");

			if (HiltonUtility.isEmpty(fiscalYear))
			{
				fiscalYear = "1994";
			}

			incomingRequest.put("AutoGen_documentType", "INV");
			incomingRequest.put("AutoGen_genYear", fiscalYear);
			incomingRequest.put("documentNumber", invItemNumber);

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			Log.error(this, e.toString());

			this.setStatus(Status.FAILED);

			throw e;
		}

		return result;
	}
}