/*
 * Created on Sep 15, 2004
 *
 * @author  * renzo
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.po.tasks;.PoGetLastRelease.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 *
 * TODO make it use the last-release field
 */
public class PoGetLastRelease extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;

        try
        {
            Map incomingRequest = (Map)object;
            DBSession dbs =(DBSession)incomingRequest.get("dbsession");
            String poNumber = (String)incomingRequest.get("PoHeader_poNumber");

            String sql = "Select MAX(poHeader.releaseNumber) from PoHeader as poHeader " +
                    "Where poHeader.poNumber = ?";

            List resultList = dbs.query(sql, new Object[] {poNumber} , new Type[] { Hibernate.STRING});


            if(resultList != null && resultList.size() > 0)
            {
                ret = resultList.get(0);
            }
            else
            {
                ret = new BigDecimal(0);
            }
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return ret;
    }
}
