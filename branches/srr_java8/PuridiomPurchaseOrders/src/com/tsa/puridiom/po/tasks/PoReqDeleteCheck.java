/**
 * Created on Apr 12, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.pp.tasks.PoCancelCheck.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.po.exceptions.PoCancelException;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class PoReqDeleteCheck extends Task
{
    public Object executeTask(Object object) throws Exception
    {
    	Log.debug(this, "PoReqDeleteChecks");
        Map incomingRequest = (Map)object;
        try
        {
        	PoLine poLine = (PoLine) incomingRequest.get("poLine");

            if (poLine != null)
            {
                BigDecimal	icReqLine = poLine.getIcReqLine();
                BigDecimal  icRfqLine = poLine.getIcRfqLine();

                if (  (icReqLine != null && icReqLine.compareTo(new BigDecimal(0)) > 0)  ||
                		(  icRfqLine != null && icRfqLine.compareTo(new BigDecimal(0)) > 0))
                {

                	if(icRfqLine != null && icRfqLine.compareTo(new BigDecimal(0)) > 0)
                	{
                		incomingRequest.put("poLineFromRFQ", "1");
                	}
                	else
                	{
                		incomingRequest.put("poLineFromRFQ", "0");
                	}

                	incomingRequest.put("poLine", poLine);
                	this.setStatus(Status.SUCCEEDED);
                	incomingRequest.put("successPage", "/orders/po_items_delete_options.jsp");
                	String icPoHeader = (String) incomingRequest.get("PoLine_icPoHeader");
            		String icPoLine = (String) incomingRequest.get("PoLine_icPoLine");
            		incomingRequest.put("PoLine_icPoHeader", icPoHeader);
            		incomingRequest.put("PoLine_icPoLine", icPoLine);

                	this.setPostAction("exitProcess");

                }

            }

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