/*
 * Created on Mar 22, 2005
 */
package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 */
public class TotalReleasedToDate extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            DBSession dbs = (DBSession)incomingRequest.get("dbsession");
            String sql = "SELECT sum(poHeader.total) FROM PoHeader poHeader " +
                    "WHERE poHeader.poNumber = ? and " +
                                "(poHeader.poType = 'RO' OR poHeader.poType = 'SR')";
            String poNumber = (String)incomingRequest.get("PoHeader_poNumber");
            Log.debug(this.getName(), "poNumber: " + poNumber);
            List result = dbs.query(sql, poNumber, Hibernate.STRING) ;
            if(result != null && result.size() == 1)
            {
                ret = (BigDecimal)result.get(0);
                if(ret == null)
                {
                    ret = new BigDecimal(0);
                }
            }
            else
            {
                ret = new BigDecimal(0);
            }
            Log.debug(this.getName(), "TotalReleasedToDate: " + ret.toString());
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException("Total Released could not be calculated!");
        }
        return ret;
    }
}