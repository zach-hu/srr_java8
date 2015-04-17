/*
 * Created on Nov 28, 2007
 *
 * @author  * renzo
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
 */
public class PoGetLastReqApprover extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        //Object ret = null;
        Map incomingRequest = (Map)object;
        List list = null;
        try
        {
	        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icHeader = (String)incomingRequest.get("ApprovalLog_icHeader");
			BigDecimal bdh = new BigDecimal(icHeader) ;

	        //String queryString = "Select a.callForward from ApprovalLog as a where a.id.icHeader = ? and a.id.sequence = (select max(b.id.sequence) from ApprovalLog as b where b.id.icHeader = ?)";
			String queryString = "from ApprovalLog as a where a.id.icHeader = ? order by a.id.userId, a.id.sequence";
			list = dbs.query(queryString, new Object[] {bdh} , new Type[] { Hibernate.BIG_DECIMAL}) ;
			//if(list != null && list.size() > 0)
            //{
            //   ret = list.get(0);
                //if(ret == null){		ret = "";		}
            //}
            //else
            //{
            //	ret ="";
            //}

			this.setStatus(dbs.getStatus()) ;
		}
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        //return ret;
        return list;
    }
}
