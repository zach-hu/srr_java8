/*
 * Created on Jan 22, 2004
 */
package com.tsa.puridiom.shipto.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.ShipTo;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author Kelli
 */
public class ShipToDataRetrieve extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
	        PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("shiptodata-retrieve-by-id.xml");

			List shipToList = (List) incomingRequest.get("shipToList");
	        for (Iterator it = shipToList.iterator(); it.hasNext(); )
	        {
					incomingRequest.put("shipTo", (ShipTo) it.next());
					process.executeProcess(incomingRequest);
					this.setStatus(process.getStatus()) ;
					if (process.getStatus() == Status.FAILED) {	break ;		}
	        }
	        this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw new TsaException("ShipTo Data could not be retrieved!", e);
		}

		return null  ;
	}

}
