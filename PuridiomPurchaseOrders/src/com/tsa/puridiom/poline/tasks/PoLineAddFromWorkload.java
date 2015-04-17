/*
 * Created on Aug 31, 2004
 *
 * @author  * renzo
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.poline.tasks;.PoLineAddFromWorkload.java
 * 
 */
package com.tsa.puridiom.poline.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoLineAddFromWorkload extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
	        Map incomingRequest = (Map)object;
			Object result = null;
			Object icReqLineObj = incomingRequest.get("RequisitionLine_icReqLine");
			Object quantityObj = incomingRequest.get("quantity");
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			
			if (icReqLineObj instanceof String[]) 
			{
				String	icReqLines[] = (String[]) icReqLineObj;
				String	quantities[] = (String[]) quantityObj;
				
				for (int i = 0; i < icReqLines.length; i++) 
				{
					PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
					PuridiomProcess process = processLoader.loadProcess("poline-create-workload.xml");
					
					String	icReqLine = icReqLines[i];
					String	quantity = quantities[i];
					
					Map updateParameters = new HashMap();
					updateParameters.put("userId", incomingRequest.get("userId"));
					updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
					updateParameters.put("organizationId", incomingRequest.get("organizationId"));
					updateParameters.put("dbsession", incomingRequest.get("dbsession"));
					updateParameters.put("poHeader", poHeader);
					updateParameters.put("RequisitionLine_icReqLine", icReqLine);
					updateParameters.put("formtype", "PO");
					updateParameters.put("lineNumber", String.valueOf(i++));
					updateParameters.put("createdfrom", "REQ");
					updateParameters.put("quantity", quantity);
					
					process.executeProcess(updateParameters);
					if (process.getStatus() < Status.SUCCEEDED) 
					{
					    this.setStatus(Status.FAILED);
						throw new Exception("Adding PoLine failed.");
					}
				}
			}
			else 
			{
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("poline-create-workload.xml");
				
				Map updateParameters = new HashMap();
				updateParameters.put("userId", incomingRequest.get("userId"));
				updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
				updateParameters.put("organizationId", incomingRequest.get("organizationId"));
				updateParameters.put("dbsession", incomingRequest.get("dbsession"));
				updateParameters.put("poHeader", poHeader);
				updateParameters.put("RequisitionLine_icReqLine", icReqLineObj);
				updateParameters.put("formtype", "PO");
				updateParameters.put("lineNumber", "1");
				updateParameters.put("createdfrom", "REQ");
				
				process.executeProcess(updateParameters);
					
				if (process.getStatus() < Status.SUCCEEDED) 
				{
				    this.setStatus(Status.FAILED);
					throw new Exception("InvItemLookup failed.");
				}
			}
						
			this.status = Status.SUCCEEDED;
        }
        catch (Exception e) 
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return ret;
    }
}
