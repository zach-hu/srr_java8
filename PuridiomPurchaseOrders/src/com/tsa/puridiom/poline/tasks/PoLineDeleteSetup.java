/*
 * Created on Aug 18, 2004
 */
package com.tsa.puridiom.poline.tasks;

import java.util.*;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility ;
import com.tsagate.foundation.processengine.Status;
/**
 * @author Administrator
 */
public class PoLineDeleteSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		DBSession dbs = (DBSession) incomingRequest.get("dbsession");

		String icPoHeader = (String) incomingRequest.get("PoLine_icPoHeader");
		String icPoLine = (String) incomingRequest.get("PoLine_icPoLine");

		if (Utility.isEmpty(icPoHeader) || Utility.isEmpty(icPoLine))
		{
			this.setStatus(Status.FAILED);
		}
		else
		{
			incomingRequest.put("BillTo_icHeader",icPoHeader) ;
			incomingRequest.put("BillTo_icLine",icPoLine) ;
			incomingRequest.put("ShipTo_icHeader",icPoHeader) ;
			incomingRequest.put("ShipTo_icLine",icPoLine) ;
			incomingRequest.put("Account_icHeader",icPoHeader) ;
			incomingRequest.put("Account_icLine",icPoLine) ;
			incomingRequest.put("DocComment_icHeader",icPoHeader) ;
			incomingRequest.put("DocComment_icLine",icPoLine) ;
			this.setStatus(dbs.getStatus());
		}
		String skipdeletecheck = (String)incomingRequest.get("skipdeletecheck");
		if(skipdeletecheck == null)
        {
        	incomingRequest.put("skipdeletecheck", "N");
        }
		return null ;
	}
}
