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

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class PoSetBlanketInfoTotalReleased extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String poNumber = (String)incomingRequest.get("PoHeader_poNumber");
			BigDecimal blanketIc = (BigDecimal)incomingRequest.get("blanketIc");
			PoBlanketInfo blanketInfo = (PoBlanketInfo)incomingRequest.get("blanketInfo");

			if (Utility.isEmpty(poNumber) || poNumber.equals("N/A"))
			{
				blanketInfo.setReleaseTotal(new BigDecimal(0));
				blanketInfo.setAvailableBlanket(blanketInfo.getPoBlanket());
			}
			else
			{
				String queryString = "Select sum(poHeader.total) FROM PoHeader as poHeader " +
						"WHERE poHeader.poNumber = ?  AND poHeader.icPoHeader <> ? AND " +
						"poHeader.lastRevision = 'C' AND " +
						"(poHeader.status > '" + DocumentStatus.PO_APPROVING + "' AND " +
						"poHeader.status < '" + DocumentStatus.CANCELLED + "')";
				List resultList = dbs.query(queryString, new Object[] {poNumber,  blanketIc} , new Type[] { Hibernate.STRING, Hibernate.BIG_DECIMAL}) ;
				if(resultList == null && resultList.size() < 1)
				{
					blanketInfo.setReleaseTotal(new BigDecimal(0));
					blanketInfo.setAvailableBlanket(blanketInfo.getPoBlanket());
				}
				else
				{
					BigDecimal totalReleased = (BigDecimal)resultList.get(0);
					if(totalReleased != null)
					{
						blanketInfo.setReleaseTotal(totalReleased);
						BigDecimal availableBlanket = blanketInfo.getPoBlanket().subtract(totalReleased);

						if(availableBlanket.compareTo(new BigDecimal(0)) == 1 )
						{
							blanketInfo.setAvailableBlanket(availableBlanket);
						}
						else
						{
						    blanketInfo.setAvailableBlanket(new BigDecimal(0));
						}
					}
					else
					{
						blanketInfo.setReleaseTotal(new BigDecimal(0));
						blanketInfo.setAvailableBlanket(blanketInfo.getPoBlanket());
					}
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
