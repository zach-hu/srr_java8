/**
 * Created on Apr 12, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.pp.tasks.PoCancelCheck.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.po.exceptions.PoCancelException;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

public class PoLineReqCancelCheck extends Task
{
    public Object executeTask(Object object) throws Exception
    {
    	Log.debug(this, "PoReqCancelChecks");
        Map incomingRequest = (Map)object;
        try
        {
            List poItemsFromReq = new ArrayList();
            int numPoItemsFromReq = 0;
        	PoLine poLine = (PoLine)incomingRequest.get("poLine");
        	
        	if (poLine.getIcReqLine().compareTo(new BigDecimal(0)) > 0)
			{
				numPoItemsFromReq++;
			}
        	
        	poItemsFromReq.add(poLine);
        	
//        	PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
//            PuridiomProcess process = processLoader.loadProcess("poline-cancel-req-check.xml");
//        	Map newIncomingRequest = new HashMap();
//            newIncomingRequest.put("poLine", poLine);
//            newIncomingRequest.put("poItemsFromReq", poItemsFromReq);
//            newIncomingRequest.put("userId", incomingRequest.get("userId"));
//            newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
//            newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
//            newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
//
//            process.executeProcess(newIncomingRequest);
//            this.setStatus(process.getStatus());
//            if (this.getStatus() != Status.SUCCEEDED)
//            {
//                Log.error(this, "Canceling Line item " + poLine.getLineNumber() + " failed with status: " + process.getStatus());
//                throw new TsaException("Error Canceling PoLine: " + poLine.getLineNumber());
//            }
//            else
//            {
//            	poItemsFromReq = (List)newIncomingRequest.get("poItemsFromReq");
//            }
//            if(poItemsFromReq.size() > 0)
//            {
            	incomingRequest.put("poItemsFromReq", poItemsFromReq);
            	incomingRequest.put("numPoItemsFromReq", new Integer(numPoItemsFromReq));
//            	this.setStatus(Status.SUCCEEDED);
            	incomingRequest.put("successPage", "/orders/po_items_cancel_options.jsp");
            	this.setPostAction("exitProcess");
//            }

            this.setStatus(Status.SUCCEEDED);
        }
//        catch (PoCancelException e)
//        {
//            this.setStatus(Status.FAILED);
//            throw e;
//        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return null;
    }

}