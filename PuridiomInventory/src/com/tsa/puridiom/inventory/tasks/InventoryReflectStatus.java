package com.tsa.puridiom.inventory.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

@SuppressWarnings(value = { "unchecked" })
public class InventoryReflectStatus extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        try
        {
        	String oid = (String)incomingRequest.get("organizationId");
        	String userId = (String)incomingRequest.get("userId");

        	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
        	
        	ReceiptLine receiptLine = (ReceiptLine)incomingRequest.get("receiptLine");
        	
        	String  reflectStatus = propertiesManager.getProperty("INV OPTIONS","REFLECTSTATUS","N");
        	
        	if(reflectStatus.equalsIgnoreCase("Y"))
        	{
        		Map reviewIncomingRequest = new HashMap();
        		reviewIncomingRequest.put("organizationId", oid);
        		reviewIncomingRequest.put("userId", userId);
        		reviewIncomingRequest.put("receiptLine", receiptLine);
        		reviewIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
        		reviewIncomingRequest.put("receiptHeader", incomingRequest.get("receiptHeader"));
        		reviewIncomingRequest.put("receiptLineList", incomingRequest.get("receiptLineList"));
        		reviewIncomingRequest.put("poHeader", incomingRequest.get("poHeader"));
        		reviewIncomingRequest.put("poLineList", incomingRequest.get("poLineList"));
        		reviewIncomingRequest.put("requisitionHeader", incomingRequest.get("requisitionHeader"));
        		reviewIncomingRequest.put("requisitionLineList", incomingRequest.get("requisitionLineList"));
        		reviewIncomingRequest.put("msrHeader", incomingRequest.get("msrHeader"));
        		reviewIncomingRequest.put("msrLineList", incomingRequest.get("msrLineList"));
        		reviewIncomingRequest.put("ReceiptHeader_icRecHeader", incomingRequest.get("ReceiptHeader_icRecHeader"));
        		reviewIncomingRequest.put("PoHeader_icPoHeader", incomingRequest.get("PoHeader_icPoHeader"));
        		reviewIncomingRequest.put("RequisitionHeader_icReqHeader", incomingRequest.get("RequisitionHeader_icReqHeader"));
        		reviewIncomingRequest.put("MsrHeader_icReqHeader", incomingRequest.get("MsrHeader_icReqHeader"));
        		
        		PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
    			PuridiomProcess process = processLoader.loadProcess("inv-update-status.xml");
    			
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
