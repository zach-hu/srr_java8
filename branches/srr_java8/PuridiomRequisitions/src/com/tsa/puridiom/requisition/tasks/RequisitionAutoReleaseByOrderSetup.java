/*
 * Created on Mar 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.requisition.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

public class RequisitionAutoReleaseByOrderSetup extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            Map releaseItems = (Map)incomingRequest.get("releaseItems");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");

            if(releaseItems.size() > 0)
            {//items to create release for
                Set keys = releaseItems.keySet();

                for (Iterator iter = keys.iterator(); iter.hasNext();)
                {
                    String catalogId = (String) iter.next();
                    Log.debug(this, "AutoRelease for items from Catalog: " + catalogId);
                    Object tempArray[] = (Object[])releaseItems.get(catalogId);
                    List requisitionLineList = (List)tempArray[0];
                    PoHeader blanket = (PoHeader)tempArray[1];

                    this.setAutoReleaseFlags(requisitionLineList, "AUTORELEASE", blanket.getPoNumber(), userTimeZone);
                }
            }

            Log.debug(this, "AUTORELEASE PART DONE!");
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException("Release Order could not be created!", e);
        }
        return ret;
    }

    public List setAutoReleaseFlags(List reqLines, String buyer, String blanketOrder, String userTimeZone)
    {
    	Log.debug(this, "Setting Release flags ");
        for(int i = 0; i < reqLines.size(); i++)
        {
            RequisitionLine reqLine = (RequisitionLine)reqLines.get(i);
            Log.debug(this, "Setting assigned Buyer for line: " + reqLine.getLineNumber().toString() + ", buyer: " + buyer);
            reqLine.setAssignedBuyer(buyer);
            reqLine.setAssignedDate(Dates.getCurrentDate(userTimeZone));
            reqLine.setBlanketOrder(blanketOrder);
            reqLines.set(i, reqLine);
        }

        return reqLines;
    }
}
