/*
 * Created on June 09, 2004
 */
package com.tsa.puridiom.disbline.tasks;

import java.util.*;

import com.tsa.puridiom.entity.DisbLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility ;
import com.tsagate.foundation.processengine.Status;
/**
 * @author Administrator 
 */
public class DisbLineRetrieveSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;
		this.setStatus(Status.SUCCEEDED) ;

		DisbLine dbl = (DisbLine) incomingRequest.get("disbLine") ;
		if (dbl == null) {
			this.setStatus(Status.FAILED) ;
		} else {
			String icHeader = dbl.getIcDsbHeader().toString() ;
			String icLine = dbl.getIcDsbLine().toString() ;
			
			if (Utility.isEmpty(icHeader)) {
				this.setStatus(Status.FAILED);
			} else {
				incomingRequest.put("Account_icHeader",icHeader) ;
				incomingRequest.put("Account_icLine",icLine) ;
				incomingRequest.put("DocComment_icHeader",icHeader) ;
				incomingRequest.put("DocComment_icLine",icLine) ;
				incomingRequest.put("RequisitionLine_icReqLine", dbl.getIcReqLine().toString()) ;
				incomingRequest.put("Commodity_commodity",dbl.getCommodityCode().toString()) ;
			}
		}
		
		return null ;
	}
}

