package com.tsa.puridiom.requisition.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

@SuppressWarnings(value = { "unchecked" })
public class RequisitionReflectStatus extends Task
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
        	RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
        	
        	String  reflectStatus = propertiesManager.getProperty("REQ OPTIONS","REFLECTSTATUS","N");
        	
        	if(reflectStatus.equalsIgnoreCase("Y") && requisitionHeader.getStatus().compareToIgnoreCase(DocumentStatus.REQ_APPROVED) >= 0)
        	{
        		Map reviewIncomingRequest = new HashMap();
        		reviewIncomingRequest.put("organizationId", oid);
        		reviewIncomingRequest.put("userId", userId);
        		reviewIncomingRequest.put("dbsession", dbsession);
        		reviewIncomingRequest.put("requisitionHeader", requisitionHeader);
        		reviewIncomingRequest.put("requisitionLineList", incomingRequest.get("requisitionLineList"));
        		reviewIncomingRequest.put("msrHeader", incomingRequest.get("msrHeader"));
        		reviewIncomingRequest.put("msrLineList", incomingRequest.get("msrLineList"));
        		reviewIncomingRequest.put("RequisitionHeader_icReqHeader", incomingRequest.get("RequisitionHeader_icReqHeader"));
        		reviewIncomingRequest.put("MsrHeader_icReqHeader", incomingRequest.get("MsrHeader_icReqHeader"));
        		
        		PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
    			PuridiomProcess process = processLoader.loadProcess("req-update-status.xml");
    			
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
