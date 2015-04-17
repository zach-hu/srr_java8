/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.invformdata.tasks;

import java.util.*;

import com.tsa.puridiom.entity.InvFormData;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility ;
import com.tsagate.foundation.processengine.Status;
/**
 * @author Administrator 
 */
public class InvFormDataRetrieveSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;
		this.setStatus(Status.SUCCEEDED) ;

		InvFormData ifd = (InvFormData) incomingRequest.get("invFormData") ;
		if (ifd == null) {
			this.setStatus(Status.FAILED) ;
		} else {
			String icNotes = ifd.getIcNotes().toString();
			
			if (Utility.isEmpty(icNotes)) {
				this.setStatus(Status.FAILED);
			} else {
				incomingRequest.put("DocText_icText",icNotes) ;
			}
		}
		
		return null ;
	}
}
