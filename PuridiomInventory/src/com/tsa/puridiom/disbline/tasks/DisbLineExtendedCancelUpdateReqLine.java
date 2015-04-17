/*
 * Created on Dec 28, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.disbline.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 */
public class DisbLineExtendedCancelUpdateReqLine extends Task
{
    
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
    	try
    	{
    		Map incomingRequest = (Map) object;
    		RequisitionLine reqLine = (RequisitionLine)incomingRequest.get("reqLine");
    		if(reqLine.getStatus().equals(DocumentStatus.INV_BACKORDERED))
    		{
    		    reqLine.setBackordered(new BigDecimal(0));
    		    reqLine.setStatus(DocumentStatus.CANCELLED);
    		}
    		else
    		{
    		    BigDecimal qtyDisbursed = (BigDecimal)incomingRequest.get("qtyDisbursed");
    		    if(qtyDisbursed == null)
    		    {
    		        qtyDisbursed = new BigDecimal(0);
    		    }
    		    
    		    if (qtyDisbursed.compareTo(new BigDecimal(0)) < 1)
    		    {
    		        reqLine.setBackordered(new BigDecimal(0));
    		        reqLine.setStatus(DocumentStatus.CANCELLED);
    		    }
    		    else
    		    {
    		        BigDecimal balance = reqLine.getQuantity().subtract(qtyDisbursed);
    		        reqLine.setBackordered(new BigDecimal(0));
    		        reqLine.setStatus(DocumentStatus.INV_DISBURSED);
    		        reqLine.setQuantity(balance);
    		    }
    		    incomingRequest.put("disbursed", new Boolean(true));
    		}
    		ret = reqLine;
    		this.setStatus(Status.SUCCEEDED);
    	}
    	catch (Exception e)
    	{
    	    this.setStatus(Status.FAILED);
    		throw new TsaException(this.getName(), e);
    	}
    	return ret;
    }
}