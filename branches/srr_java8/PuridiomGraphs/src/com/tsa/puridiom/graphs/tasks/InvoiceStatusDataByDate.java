/*
 * Created on March 13, 2008
 */
package com.tsa.puridiom.graphs.tasks;

import java.util.Date;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.graphs.exceptions.ProcessException;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;

/**
 * @author renzo
 *
 */
public class InvoiceStatusDataByDate extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            String organizationId = (String)incomingRequest.get("organizationId");
            String begin = PropertiesManager.getInstance(organizationId).getProperty("Misc", "fybegin", "1");
            String userId = (String) incomingRequest.get("userId");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            UserProfile	user = UserManager.getInstance().getUser(organizationId,userId);

            String barChartDisplayINV = user.getBarChart();
            String queryDate = "";
            String valueDate = "";

            if(HiltonUtility.isEmpty(barChartDisplayINV))
            {
            	queryDate = " AND header.fiscalYear >= ? ";
            	valueDate = Dates.getFiscalYear(Integer.parseInt(begin),userTimeZone);
            }
            else
            {
            	Date d_today = new Date();

            	int queryMonth = d_today.getMonth() - Integer.parseInt(barChartDisplayINV) + 1;
            	int queryYear  = d_today.getYear()  + 1900;

            	if(queryMonth <= 0)
            	{
            		queryMonth += 12;
            		queryYear  -= 1;
            	}

            	queryDate = " AND header.invoiceDate >= to_date( ? ,'MM-dd-yyyy') ";
            	valueDate = String.valueOf(queryMonth) + "/1/" + String.valueOf(queryYear);
            }

            String sql = "select count(*), header.status from InvoiceHeader as header " +
					     "where header.invoiceNumber <> 'N/A' AND header.status > ? " +
					     "AND header.status <= ? " + queryDate +
					     "group by header.status order by header.status";
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			ret = dbs.query(sql, new Object[] {DocumentStatus.IVC_INPROGRESS, DocumentStatus.IVC_APPROVED, valueDate} , new Type[] { Hibernate.STRING, Hibernate.STRING, Hibernate.STRING}) ;
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new ProcessException("Invoice Status Graph could not be generated!" + e.toString());
        }

        return ret;
    }
}