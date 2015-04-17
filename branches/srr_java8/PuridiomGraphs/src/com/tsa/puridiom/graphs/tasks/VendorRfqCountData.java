/*
 * Created on Feb 07, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.graphs.tasks;

import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.graphs.exceptions.ProcessException;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;

/**
 * @author kathleen
 */
public class VendorRfqCountData extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map) object;
            DBSession dbs = (DBSession)incomingRequest.get("dbsession");

            String organizationId = (String)incomingRequest.get("organizationId");
            String userTimeZone = (String)incomingRequest.get("userTimeZone");
            String vendorId = (String) incomingRequest.get("vendorId");
            String contactCode = (String) incomingRequest.get("contactCode");

            String begin = PropertiesManager.getInstance(organizationId).getProperty("Misc", "fybegin", "1");
            String fiscalYear = Dates.getFiscalYear(Integer.parseInt(begin), userTimeZone);

            String sql = "select count(*), rfqheader.status from RfqHeader as rfqheader, RfqVendor as rfqvendor " +
            "where rfqheader.icRfqHeader = rfqvendor.id.icRfqHeader AND rfqheader.fiscalYear = ? AND rfqheader.rfqNumber <> 'N/A' " +
            "AND rfqheader.webpost <> 'N' AND rfqheader.webpost <> ' ' AND rfqvendor.id.vendorId = ? AND rfqvendor.contactId = ? " +
            "group by rfqheader.status order by rfqheader.status";

            ret = dbs.query(sql, new Object[] {fiscalYear, vendorId, contactCode} , new Type[] { Hibernate.STRING, Hibernate.STRING, Hibernate.STRING});

        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new ProcessException("VendorRfqCountData" + e.toString());
        }

        return ret;
    }
}
