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
import com.tsa.puridiom.entity.PoHeader;
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
public class TopSuppliers extends Task
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

            /*String sql = "select vendorid, volume " +
            		"from (SELECT  p.vendorId vendorid, sum(p.total) volume " +
            				"from PoHeader p " +
            				"where p.status >= '3030' and p.status < '9000' " +
            				"group by p.vendorId order by volume desc) " +
            		"where rownum<=10";*/
            String sql = "SELECT  sum(p.total), p.vendorId from PoHeader as p where p.fiscalYear >= '" + fiscalYear + "'and  p.status >= '" + DocumentStatus.PO_AWARDED +
            				"' and p.status < '" + DocumentStatus.HISTORY + "' group by p.vendorId order by sum(p.total) desc";

            /*String sql = "SELECT  p.vendor_Id, sum(p.total) " +
			"from Po_Header p " +
			"where p.status >= '3030' and p.status < '9000' " +
			"group by p.vendor_Id order by sum(p.total) desc";
			*/

            DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

            //ret = dbs.sqlQuery(sql, null, null, "PoHeader", PoHeader.class) ;
            ret = dbs.query(sql) ;
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new ProcessException("RequisitionerReqCountData" + e.toString());
        }

        return ret;
    }
}
