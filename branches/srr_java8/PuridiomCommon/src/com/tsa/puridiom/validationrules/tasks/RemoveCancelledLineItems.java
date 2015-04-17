/*
 * Created on October 10, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.validationrules.tasks;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionLine;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author Matthew
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RemoveCancelledLineItems extends Task
{

    /* (non-Javadoc)
     * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
     */
    public Object executeTask(Object object) throws Exception
    {
    	List result = new ArrayList();
        try
        {
            Map incomingRequest = (Map)object;
            String formType = HiltonUtility.ckNull((String)incomingRequest.get("formType"));
            List lineitems = (List) incomingRequest.get("lineitems");
            if (lineitems == null)
            	lineitems = new ArrayList();

            if (formType.equalsIgnoreCase("REQ"))
    		{
            	for (int i = 0; i < lineitems.size(); i++)
                {
            		RequisitionLine line = (RequisitionLine) lineitems.get(i);
        			if (!"9020".equals(line.getStatus()))
        				result.add(line);
                }
    		}
        	else if (formType.equalsIgnoreCase("PO"))
        	{
        		for (int i = 0; i < lineitems.size(); i++)
                {
        			PoLine line = (PoLine) lineitems.get(i);
        			if (!"9020".equals(line.getStatus()))
        				result.add(line);
                }
        	}
        	else
        	{
        		result = lineitems;
        	}

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return result;
    }
}