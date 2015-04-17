/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.account.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import org.hibernate.*;

/**
 * @author Administrator
 */
public class AccountRetrieveHeader extends Task
{
    /* (non-Javadoc)
     * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
     */
    public Object executeTask(Object object) throws Exception
    {

        Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
        String icHeader = (String)incomingRequest.get("Account_icHeader");

        BigDecimal bdh = new BigDecimal(icHeader) ;

          String queryString = "from Account as a where a.id.icHeader = ? and a.id.icLine = 0";

        List result = dbs.query(queryString,
                    bdh,
                    Hibernate.BIG_DECIMAL) ;

        this.setStatus(dbs.getStatus()) ;

        return result ;
    }

}
