 /*
 * Created on Aug 14, 2009
 */
package com.tsa.puridiom.graphs.tasks;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.graphs.exceptions.ProcessException;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsa.puridiom.common.utility.*;
import com.tsa.puridiom.entity.UserProfile;

/**
 * @author Kelli
 */
public class SpendBySupplier extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            String organizationId = (String)incomingRequest.get("organizationId");
            String userId = (String) incomingRequest.get("userId");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            UserProfile	user = UserManager.getInstance().getUser(organizationId,userId);
            String begin = PropertiesManager.getInstance(organizationId).getProperty("Misc", "fybegin", "1");

            String barChartDisplayPO = user.getBarChart();
            String queryDate = "";
            String valueDate = "";

            if(HiltonUtility.isEmpty(barChartDisplayPO))
            {
            	queryDate = " AND poHeader.fiscalYear >= ? ";
            	valueDate = Dates.getFiscalYear(Integer.parseInt(begin),userTimeZone);
            }
            else
            {
            	Date d_today = new Date();

            	int queryMonth = d_today.getMonth() - Integer.parseInt(barChartDisplayPO) + 1;
            	int queryYear  = d_today.getYear()  + 1900;

            	if(queryMonth <= 0)
            	{
            		queryMonth += 12;
            		queryYear  -= 1;
            	}

            	queryDate = " AND poHeader.poDate >= to_date( ? ,'MM-dd-yyyy') ";
            	valueDate = String.valueOf(queryMonth) + "/1/" + String.valueOf(queryYear);
            }

            String sql = "select sum(poHeader.total), poHeader.vendorId from PoHeader as poHeader " +
            			 "where poHeader.status >= '" + DocumentStatus.PO_AWARDED + "' " + queryDate +
            			 " and poHeader.lastRevision = 'C'" +
//            			 " and rownum <= 5 " +
            			 "group by poHeader.vendorId order by sum(poHeader.total) desc";

            DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

            ret = dbs.query(sql, new Object[] {valueDate} , new Type[] { Hibernate.STRING}, 5) ;
            if(ret != null)
            {
                Log.debug(this, "SupplierTotalSpend " + ((List)ret).size());
            }
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            e.printStackTrace();
            throw new ProcessException("SupplierTotalSpend" + e.toString());
        }

        return ret;
    }
}
