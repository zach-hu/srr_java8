/*
 * Created on Nov 20, 2003
 */
package com.tsa.puridiom.rfq.tasks;

import com.tsa.puridiom.common.tasks.AutoGenDocNumber;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kelli
*/
public class RfqAutoNumber extends Task {

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;
		
		try {
			DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;

			/* Setup Parameters */
			incomingRequest.put("AutoGen_documentType","RFQ") ;
			incomingRequest.put("AutoGen_genYear",(String) incomingRequest.get("RfqHeader_fiscalYear"));
		 		
			 /* Call AutoGenDocNumber Task and Return Next Number */
			AutoGenDocNumber autoGenTask = new AutoGenDocNumber();
			String docNum = (String) autoGenTask.executeTask(incomingRequest) ;
		
			if (docNum == null) {
				throw new Exception("Rfq Number could not be generated.");
			}
			
			
			
			result = docNum;
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result ;
	}

}
