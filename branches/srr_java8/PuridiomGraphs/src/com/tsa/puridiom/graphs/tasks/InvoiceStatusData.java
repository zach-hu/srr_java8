/*
 * Created on March 13, 2008
 */
package com.tsa.puridiom.graphs.tasks;

import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.graphs.exceptions.ProcessException;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author renzo
 *
 */
public class InvoiceStatusData extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            //String organizationId = (String)incomingRequest.get("organizationId");
            //String begin = PropertiesManager.getInstance(organizationId).getProperty("Misc", "fybegin", "1");
            //String fiscalYear = Dates.getFiscalYear(Integer.parseInt(begin));
            String sql = "select count(*), header.status from InvoiceHeader as header " +
            					"where header.invoiceNumber <> 'N/A' AND header.status > ? AND header.status <= ? " +
            					"group by header.status order by header.status";
            DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
            //String userId = (String) incomingRequest.get("userId");

            ret = dbs.query(sql, new Object[] {DocumentStatus.IVC_INPROGRESS, DocumentStatus.IVC_APPROVED} , new Type[] { Hibernate.STRING, Hibernate.STRING}) ;
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new ProcessException("Invoice Status Graph could not be generated!" + e.toString());
        }

        return ret;
    }
}