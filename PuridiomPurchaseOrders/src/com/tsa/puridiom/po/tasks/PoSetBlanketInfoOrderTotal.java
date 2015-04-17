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

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoSetBlanketInfoOrderTotal extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icHeader = (String)incomingRequest.get("icHeader");
			BigDecimal blanketIc = (BigDecimal)incomingRequest.get("blanketIc");

			PoBlanketInfo blanketInfo = (PoBlanketInfo)incomingRequest.get("blanketInfo");

			String queryString = "Select poHeader.total FROM PoHeader as poHeader " +
					"WHERE poHeader.icPoHeader = ? AND poHeader.lastRevision= 'C' ";
			List resultList = dbs.query(queryString, new Object[] {icHeader} , new Type[] {Hibernate.BIG_DECIMAL});

			if((resultList == null && resultList.size() < 1) || icHeader.compareTo(blanketIc.toString() ) == 0)
			{
				blanketInfo.setOrderTotal(new BigDecimal(0));
				blanketInfo.setAvailableOrder(blanketInfo.getPoReleaseLimit());
			}
			else
			{
				BigDecimal orderTotal = (BigDecimal)resultList.get(0);
				blanketInfo.setOrderTotal(orderTotal);
				BigDecimal availableOrder = blanketInfo.getPoReleaseLimit().subtract(orderTotal);

				if(availableOrder.compareTo(new BigDecimal(0)) == 1 )
				{
					blanketInfo.setAvailableOrder(availableOrder);
				}
				else
				{
				    blanketInfo.setAvailableOrder(new BigDecimal(0));
				}
			}
			ret = blanketInfo;
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
