/*
 * Created on Sept 21, 2006
 */
package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.entity.RfqLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kelli
 */
public class RfqLineGetIcReqLine extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;
		this.setStatus(Status.SUCCEEDED) ;

		RfqLine rfqLine = (RfqLine) incomingRequest.get("rfqLine") ;
		String	icReqLine = "";
		
		if (rfqLine != null) {
			icReqLine = String.valueOf(rfqLine.getIcReqLine());
		}
		
		return icReqLine ;
	}
}
