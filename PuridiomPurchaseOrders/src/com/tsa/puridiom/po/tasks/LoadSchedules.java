/*
 * Created on Aug 17, 2004
 *
 * @author  * renzo
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.po.tasks;.LoadSchedules.java
 * 
 */
package com.tsa.puridiom.po.tasks;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.Schedule;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LoadSchedules extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            String   	protect = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")).getProperty("Schedules","Protect Dates", "N");
            List scheduleList = (List)incomingRequest.get("scheduleList");
            Date expiredDate = null;
            Date requiredDate = null;
            
            for (Iterator iter = scheduleList.iterator(); iter.hasNext();)
            {
                Schedule schedule = (Schedule) iter.next();
                String type = schedule.getComp_id().getScheduleType();
                if(protect.equalsIgnoreCase("Y") && (type.equalsIgnoreCase("D") || type.equalsIgnoreCase("R")))
                {
                    //Schedules (FW) Default Schedule Dates into these PO dates
                    java.util.Date scheduleDate = schedule.getCompletionDate();
                    if(scheduleDate == null)
                    {
                        scheduleDate = schedule.getScheduleDate();
                    }
                    if(type.equalsIgnoreCase("R"))
                    {
                        if(expiredDate == null)
                        {
                            expiredDate = scheduleDate;
                        }
                        else if(scheduleDate.compareTo(expiredDate) == 1)
                        {
                            expiredDate = scheduleDate;
                        }
                    }
                    else if(type.equalsIgnoreCase("D"))
                    {
                        if(requiredDate == null)
                        {
                            requiredDate = scheduleDate;
                        }
                        else if(scheduleDate.compareTo(expiredDate) == 1)
                        {
                            requiredDate = scheduleDate;
                        }
                    }
                }
            }
            if(protect.equalsIgnoreCase("Y"))
            {
                incomingRequest.put("PoHeader_expirationDate", expiredDate);
                incomingRequest.put("PoHeader_requiredDate", requiredDate);
            }
            
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
