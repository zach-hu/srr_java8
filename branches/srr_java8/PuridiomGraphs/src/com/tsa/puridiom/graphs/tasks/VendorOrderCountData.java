/*
 * Created on February 06, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.graphs.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.graphs.exceptions.ProcessException;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;

/**
 * @author kathleen
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class VendorOrderCountData extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map) object;
            DBSession dbs = (DBSession) incomingRequest.get("dbsession");

            String organizationId = (String) incomingRequest.get("organizationId");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            String vendorId = (String) incomingRequest.get("vendorId");
            String contactCode = (String) incomingRequest.get("contactCode");

            String begin = PropertiesManager.getInstance(organizationId).getProperty("Misc", "fybegin", "1");
            String fiscalYear = Dates.getFiscalYear(Integer.parseInt(begin), userTimeZone);

            String sql = "select count(*), poheader.status from PoHeader as poheader " +
            					"where poheader.vendorId = ? AND poheader.vendContactCode = ? AND poheader.fiscalYear = ? " +
            					"AND poheader.status >= '" + DocumentStatus.PO_AWARDED + "' AND poheader.lastRevision = 'C' " +
            					"group by poheader.status order by poheader.status";

            ret = dbs.query(sql, new Object[] {vendorId, contactCode, fiscalYear} , new Type[] { Hibernate.STRING, Hibernate.STRING, Hibernate.STRING });
            if (ret != null)
            {
                Log.debug(this, "VendorOrderCountData: " + ((List)ret).size());
            }
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new ProcessException("VendorOrderCountData" + e.toString());
        }

        return ret;
    }
}
