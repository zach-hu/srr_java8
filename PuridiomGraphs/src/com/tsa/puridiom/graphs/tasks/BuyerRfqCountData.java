/*
 * Created on Dec 17, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.graphs.tasks;

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

/**
 * @author renzo
 */
public class BuyerRfqCountData extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            String organizationId = (String)incomingRequest.get("organizationId");
            String userTimeZone = (String)incomingRequest.get("userTimeZone");
            String begin = PropertiesManager.getInstance(organizationId).getProperty("Misc", "fybegin", "1");
            String fiscalYear = Dates.getFiscalYear(Integer.parseInt(begin), userTimeZone);
            String sql = "select count(*), header.status from RfqHeader as header where (header.owner = ? OR header.buyer = ?) AND header.fiscalYear >= ? AND header.rfqNumber <> 'N/A' AND header.status < '" + DocumentStatus.HISTORY + "' group by header.status order by header.status";
            DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
            String userId = (String) incomingRequest.get("userId");

            ret = dbs.query(sql, new Object[] {userId, userId, fiscalYear} , new Type[] { Hibernate.STRING, Hibernate.STRING, Hibernate.STRING}) ;
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new ProcessException("BuyerRfqCountData" + e.toString());
        }

        return ret;
    }
}
