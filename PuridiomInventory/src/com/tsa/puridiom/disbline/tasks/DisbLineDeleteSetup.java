/*
 * Created on July 19, 2004 
 */
package com.tsa.puridiom.disbline.tasks;

import java.util.*;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility ;
import com.tsagate.foundation.processengine.Status;
/**
 * @author Administrator 
 */
public class DisbLineDeleteSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		DBSession dbs = (DBSession) incomingRequest.get("dbsession");

		String icDsbHeader = (String) incomingRequest.get("DisbLine_icDsbHeader");
		String icDsbLine = (String) incomingRequest.get("DisbLine_icDsbLine");
		
		if (Utility.isEmpty(icDsbHeader) || Utility.isEmpty(icDsbLine))
		{
			this.setStatus(Status.FAILED);
		}
		else
		{
			incomingRequest.put("Account_icHeader",icDsbHeader);
			incomingRequest.put("Account_icLine",icDsbLine);
			incomingRequest.put("DocComment_icHeader",icDsbHeader);
			incomingRequest.put("DocComment_icLine",icDsbLine);
			/*
			incomingRequest.put("BillTo_icHeader",icDsbHeader);
			incomingRequest.put("BillTo_icLine",icDsbLine);
			incomingRequest.put("ShipTo_icHeader",icDsbHeader);
			incomingRequest.put("ShipTo_icLine",icDsbLine);
			*/
			this.setStatus(dbs.getStatus());
		}
		
		return null ;
	}
}
