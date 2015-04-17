/*
 * Created on Autust 30, 2006
 */
package com.tsa.puridiom.checklistentry.tasks;

import com.tsa.puridiom.entity.ChecklistEntry;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;

/**
 * @author Kelli 
 */
public class ChecklistEntryDataSet extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

		ChecklistEntry checklistEntry = (ChecklistEntry) incomingRequest.get("checklistEntry") ;

		checklistEntry.setResponseValueList((List) incomingRequest.get("responseValueList")) ;
					
		this.setStatus(Status.SUCCEEDED) ;
        
		return checklistEntry  ;
	}
}
