/*
 * Created on November 17, 2004
 */
package com.tsa.puridiom.poline.tasks;

import java.util.*;

import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

/**
 * @author Administrator
 */
public class PoLineAccountRetrieveSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

		try
		{
			PoLine pol = (PoLine) incomingRequest.get("poLine");
			String icHeader = pol.getIcPoHeader().toString();
			String icLine = pol.getIcPoLine().toString();
			incomingRequest.put("Account_icHeader", icHeader);
	        incomingRequest.put("Account_icLine", icLine);

	        this.setStatus(Status.SUCCEEDED) ;
		}
		catch(Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null ;
	}

}
