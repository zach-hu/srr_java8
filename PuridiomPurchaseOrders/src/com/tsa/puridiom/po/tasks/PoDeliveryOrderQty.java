/**
 * Created on Feb 17, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoTotalReleased.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.OrderType;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoDeliveryOrderQty extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String stringIcRelKey = (String)incomingRequest.get("PoLine_icRelKey");
			BigDecimal icRelKey = new BigDecimal(0);
			
			if(stringIcRelKey != null)
			{
			    icRelKey = new BigDecimal(stringIcRelKey);
			}
			else
			{
			    this.setStatus(Status.FAILED);
			    throw new TsaException("No Line was found!");
			}
			
			String queryString = "Select poLine.quantity FROM PoHeader as poHeader, PoLine as poLine " +
					"WHERE poLine.icPoHeader = poHeader.id AND " +
					"(poLine.icRelKey = ? AND " +
					"poHeader.poType = '" + OrderType.DELIVERY_ORDER + "' AND " +
					"poHeader.lastRevision = 'C')";
			List resultList = dbs.query(queryString, new Object[] {icRelKey} , new Type[] { Hibernate.BIG_DECIMAL});
			
			
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
