/*
 * Created on Dec 8, 2003
 */
package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RfqLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.*;

/**
 * @author Kelli
 */
public class RfqLineCancelSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		Object result = null;

		try	{
			DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;
			RfqLine rfqLine = (RfqLine) incomingRequest.get("rfqLine");

			String icReqLine = String.valueOf(rfqLine.getIcReqLine());

			incomingRequest.put("RfqLine_status", DocumentStatus.CANCELLED) ;
			incomingRequest.put("RequisitionLine_icReqLine", icReqLine) ;
			incomingRequest.put("RequisitionLine_status", DocumentStatus.REQ_APPROVED) ;
			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result ;
	}
}
