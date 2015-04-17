/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.disbheader.tasks;

import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator
 */
public class DisbHeaderDataRetrieve extends Task 
{
	public Object executeTask(Object object) throws Exception 
	{

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
        
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
		PuridiomProcess process = processLoader.loadProcess("disbheaderdata-retrieve.xml");
		
		process.executeProcess(incomingRequest);
					
		this.setStatus(process.getStatus()) ;
					
		return null  ;
	}
}
