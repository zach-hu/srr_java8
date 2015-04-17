package com.tsa.puridiom.invoice.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

import java.sql.Date;
import java.util.Map;

public class InvoiceSetDateExported extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        try
        {
            InvoiceHeader ivh = (InvoiceHeader)incomingRequest.get("invoiceHeader");
            
            if (ivh.getStatus().compareTo(DocumentStatus.IVC_APPROVED) == 0)
            {
            	String organizationId = (String)incomingRequest.get("organizationId");
            	String userTimeZone = (String) incomingRequest.get("userTimeZone");
            	String userDateFormat = (String) incomingRequest.get("userDateFormat");
            	if (Utility.isEmpty(userDateFormat)) {
            		userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
            	}
            	String today = Dates.today(userDateFormat, userTimeZone);
            	Date ivDate = Dates.getSqlDate(today, userDateFormat);

            	ivh.setDateExported(ivDate);
            }

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException("An Error ocurred submitting the current Invoice ", e);
        }
        return null;
    }
}
