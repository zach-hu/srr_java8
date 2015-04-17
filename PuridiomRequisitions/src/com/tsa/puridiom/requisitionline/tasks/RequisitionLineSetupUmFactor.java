/*
 * Created on Mar 26, 2008 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author david TODO To change the template for this generated type comment go
 *         to Window - Preferences - Java - Code Style - Code Templates
 */
public class RequisitionLineSetupUmFactor extends Task
{
	public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            DBSession dbs = (DBSession)incomingRequest.get("dbsession");
            String uomCode = (String) incomingRequest.get("RequisitionLine_umCode");

            if(!HiltonUtility.isEmpty(uomCode))
            {
            	String sql = "select uom.factor from UnitOfMeasure as uom where uom.umCode = ? order by uom.umCode";
                List lst = dbs.query(sql, new Object[] {uomCode} , new Type[] { Hibernate.STRING});

                BigDecimal bd_uomCode = new BigDecimal("0");

                if( lst != null && lst.size() > 0 )
                {
                	bd_uomCode = (BigDecimal)lst.get(0);
                }
                incomingRequest.put("RequisitionLine_umFactor", bd_uomCode.toString());
            }
            else
            {
            	incomingRequest.put("RequisitionLine_umFactor", "1");
            }
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
        	this.setStatus(Status.FAILED);
        	Log.error(this, "RequisitionLine_umFactor Failed: " + e.getMessage());
        }

        return ret;
    }
}