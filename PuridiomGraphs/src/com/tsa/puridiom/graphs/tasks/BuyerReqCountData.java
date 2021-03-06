/*
 * Created on Dec 8, 2004
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
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BuyerReqCountData extends Task
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
            String sql = "select count(*), header.status from RequisitionHeader as header where header.assignedBuyer = ? AND header.fiscalYear = ? group by header.status";
            DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String userId = (String) incomingRequest.get("userId");

			ret = dbs.query(sql, new Object[] {userId, fiscalYear} , new Type[] { Hibernate.STRING, Hibernate.STRING}) ;
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new ProcessException("BuyerPoCountData" + e.toString());
        }

        return ret;
    }
}
