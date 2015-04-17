/*
 * Created on Dec 9, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.common.tasks;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Map;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TodayDateComparison extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        Map incomingRequest = (Map)object;
        try
        {
            Calendar today = Calendar.getInstance();
            String organizationId = (String)incomingRequest.get("organizationId");
            String reqDueDate = PropertiesManager.getInstance(organizationId).getProperty("REQVALRULES", "DUEDATE", "14");
            String datePart = PropertiesManager.getInstance(organizationId).getProperty("REQVALRULES", "DUEDATEPART", "d");
            BigDecimal dateTime = new BigDecimal(reqDueDate);
            if(datePart.equalsIgnoreCase("D"))
            {
            	today.add(Calendar.DATE, dateTime.intValue());
            }
            else if(datePart.equalsIgnoreCase("M"))
            {
            	today.add(Calendar.MONTH, dateTime.intValue());
            }
            else if(datePart.equalsIgnoreCase("Y"))
            {
            	today.add(Calendar.YEAR, dateTime.intValue());
            }

            ret = today.getTime();

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
