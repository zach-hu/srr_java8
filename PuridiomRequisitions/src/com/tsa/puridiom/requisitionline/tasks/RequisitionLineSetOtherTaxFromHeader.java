/*
 * Created on Dec 13, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RequisitionLineSetOtherTaxFromHeader extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            RequisitionHeader header = (RequisitionHeader)incomingRequest.get("requisitionHeader");
            List requisitionLineList = (List)incomingRequest.get("requisitionLineList");
            boolean headerOtherTax = header.getTaxOther().equals("Y");
            
            for (Iterator iter = requisitionLineList.iterator(); iter.hasNext();)
            {
                RequisitionLine line = (RequisitionLine) iter.next();
                if(headerOtherTax && line.getTaxable().equals("Y"))
                {
	                line.setTaxOther("Y");
                }
                else
                {
                    line.setTaxOther("N");
                }
            }
            ret = requisitionLineList;
            
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
        }
        return ret;
    }
}
