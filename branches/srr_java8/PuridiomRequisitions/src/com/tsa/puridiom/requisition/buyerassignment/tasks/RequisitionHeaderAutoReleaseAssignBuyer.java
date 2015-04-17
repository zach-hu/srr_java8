/*
 * Created on Apr 19, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.requisition.buyerassignment.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

/**
 * @author renzo
 */
public class RequisitionHeaderAutoReleaseAssignBuyer extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            Map releasedItems = (Map)incomingRequest.get("releaseItems");
            Map nonReleasedItems = (Map)incomingRequest.get("nonReleaseItems");
            RequisitionHeader rqh = (RequisitionHeader)incomingRequest.get("requisitionHeader");
            String headerBuyer = "";

            if(nonReleasedItems.size() > 0)
            {
                Log.debug(this, "there are items not autoreleased!");
                Set keys = nonReleasedItems.keySet();
                for (Iterator iter = keys.iterator(); iter.hasNext();)
                {
                    Object key = iter.next();
                    List items = (List)nonReleasedItems.get(key);
                    for(int i = 0; i < items.size(); i++)
                    {
                        RequisitionLine rql = (RequisitionLine)items.get(i);
                        headerBuyer = rql.getAssignedBuyer();
                        if(!Utility.isEmpty(headerBuyer))
                        {
                            i = items.size();
                        }
                    }
                }
            }
            else if(releasedItems.size() > 0)
            {
                Log.debug(this, "there are released items");
                Set keys = releasedItems.keySet();
                for (Iterator iter = keys.iterator(); iter.hasNext();)
                {
                    Object key = iter.next();
                    Object tempArray[] = (Object[])releasedItems.get(key);
                    List items = (List)tempArray[0];

                    for(int i = 0; i < items.size(); i++)
                    {
                        RequisitionLine rql = (RequisitionLine)items.get(i);
                        headerBuyer = rql.getAssignedBuyer();
                        if(!headerBuyer.equalsIgnoreCase("AUTORELEASE"))
                        {
                            i = items.size();
                        }
                    }
                }
            }

            rqh.setAssignedBuyer(headerBuyer);
            rqh.setAssignedDate(Dates.getCurrentDate((String) incomingRequest.get("userTimeZone")));
            Log.debug(this, "Requisition " + rqh.getRequisitionNumber() + " was assgined to: " + headerBuyer);

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return super.executeTask(object);
    }
}
