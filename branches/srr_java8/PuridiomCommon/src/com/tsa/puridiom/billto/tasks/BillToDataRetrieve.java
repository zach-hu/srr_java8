/*
 * Created on Jan 22, 2004
 */
package com.tsa.puridiom.billto.tasks;

import com.tsa.puridiom.entity.BillTo;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Kelli 
 */
public class BillToDataRetrieve extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
        this.setStatus(Status.SUCCEEDED) ;
        
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
		PuridiomProcess process = processLoader.loadProcess("billtodata-retrieve-by-id.xml");
		        
		List billToList = (List) incomingRequest.get("billToList");
        for (Iterator it = billToList.iterator(); it.hasNext(); ) {
				incomingRequest.put("billTo", (BillTo) it.next());
				process.executeProcess(incomingRequest);
				this.setStatus(process.getStatus()) ;
				if (process.getStatus() == Status.FAILED) {
					break ;
				}
        }
					
		return null  ;
	}
	
}
