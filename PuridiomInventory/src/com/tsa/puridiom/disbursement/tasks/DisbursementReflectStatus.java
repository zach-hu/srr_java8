package com.tsa.puridiom.disbursement.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.common.documents.DisbursementType;
import com.tsa.puridiom.entity.DisbHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

@SuppressWarnings(value = { "unchecked" })
public class DisbursementReflectStatus extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        try
        {
        	String oid = (String)incomingRequest.get("organizationId");
        	String userId = (String)incomingRequest.get("userId");
        	DBSession dbsession = (DBSession)incomingRequest.get("dbsession");
        	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
        	DisbHeader disbHeader = (DisbHeader)incomingRequest.get("disbHeader");
        	
        	String  reflectStatus = propertiesManager.getProperty("DSB OPTIONS","REFLECTSTATUS","N");
        	
        	if(reflectStatus.equalsIgnoreCase("Y") && disbHeader.getDisbType().equalsIgnoreCase(DisbursementType.OVER_THE_COUNTER))
        	{
        		Map reviewIncomingRequest = new HashMap();
        		reviewIncomingRequest.put("organizationId", oid);
        		reviewIncomingRequest.put("userId", userId);
        		reviewIncomingRequest.put("dbsession", dbsession);
        		reviewIncomingRequest.put("disbHeader", disbHeader);
        		reviewIncomingRequest.put("disbLineList", incomingRequest.get("disbLines"));
        		
        		PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
    			PuridiomProcess process = processLoader.loadProcess("dsb-update-status.xml");
    			
    			process.executeProcess(reviewIncomingRequest);
        	}
        	
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
        	this.setStatus(Status.SUCCEEDED);
        	System.err.println("ERROR Trying to Reflect Status \n"+e);
        	Log.error(this, "ERROR Trying to Reflect Status \n"+e);
        }
        return null ;
    }
}
