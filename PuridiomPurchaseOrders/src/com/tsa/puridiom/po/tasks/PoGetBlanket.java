/*
 * Created on Dec 8, 2005
 *
 * @author  * tamy
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.po.tasks;.PoGetBlanket.java
 * 
 */
package com.tsa.puridiom.po.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

/**
 * @author tamy
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PoGetBlanket extends Task
{
    public Object executeTask(Object object) throws Exception
    {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String poNumber = (String) incomingRequest.get("PoHeader_poNumber");
			
			String queryString = "from PoHeader as poHeader " + "Where poHeader.poNumber = ? AND " +
    		"poHeader.releaseNumber = 0 AND " + "poHeader.lastRevision = 'C'";
			
			List resultList = dbs.query(queryString, new Object[] {poNumber } , new Type[] { Hibernate.STRING}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
				PoHeader blanketHeader = (PoHeader) result;
				incomingRequest.put("blanketIc", blanketHeader.getIcPoHeader());
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
    }
}
