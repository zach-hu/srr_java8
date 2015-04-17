/*
 * Created on Mar 26, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.rfqline.tasks;

import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author david
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RfqLineSetupUmFactor extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            DBSession dbs = (DBSession)incomingRequest.get("dbsession");
            String uomCode = (String) incomingRequest.get("RfqLine_umCode");

            if(!HiltonUtility.isEmpty(uomCode))
            {
            	String sql = "select uom.factor from UnitOfMeasure as uom where uom.umCode = ? order by uom.umCode";
                List lst = dbs.query(sql, new Object[] {uomCode} , new Type[] { Hibernate.STRING});

                BigDecimal bd_uomCode = (BigDecimal)lst.get(0);
                incomingRequest.put("RfqLine_umFactor", bd_uomCode.toString());
            }
            else
            	incomingRequest.put("RfqLine_umFactor", "1");
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
        }

        return ret;
    }
}