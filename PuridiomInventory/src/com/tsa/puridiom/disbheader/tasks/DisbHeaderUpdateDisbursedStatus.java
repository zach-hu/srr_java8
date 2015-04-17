/*
 * Created on Jul 7, 2004
 *
 * @author renzo
 * project: HiltonInventory
 * com.tsa.puridiom.disbheader.tasks.DisbHeaderUpdateDisbursedStatus.java
 * 
 */
package com.tsa.puridiom.disbheader.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.DisbHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class DisbHeaderUpdateDisbursedStatus extends Task
{

	/* (non-Javadoc)
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map)object;
			DisbHeader header = (DisbHeader)incomingRequest.get("disbHeader");
			header.setStatus(DocumentStatus.INV_INPROGRESS);
			ret = header;
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return ret;
	}
}
