/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.disbline.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.DisbLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator 
 */
public class DisbLineDataRetrieve extends Task 
{
	public Object executeTask(Object object) throws Exception 
	{

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
        this.setStatus(Status.SUCCEEDED) ;
        
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
		PuridiomProcess process = processLoader.loadProcess("disbline-data-retrieve.xml");
		        
		List disbLineList = (List) incomingRequest.get("disbLineList");
        for (Iterator it = disbLineList.iterator(); it.hasNext(); ) 
        {
				incomingRequest.put("disbLine", (DisbLine) it.next());
				process.executeProcess(incomingRequest);
				this.setStatus(process.getStatus()) ;
				if (process.getStatus() == Status.FAILED) 
				{
					break ;
				}
				
        }
		return disbLineList;
	}
	
}
