package com.tsa.puridiom.po.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class PoCountTotalReleases extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object ret = null;

		try
		{
			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			PoBlanketInfo blanketInfo = (PoBlanketInfo) incomingRequest.get("blanketInfo");
			String poNumber = (String) incomingRequest.get("PoHeader_poNumber");

			if (Utility.isEmpty(poNumber) || poNumber.equals("N/A"))
			{
				blanketInfo.setReleaseCount(new BigDecimal(0));
				blanketInfo.setReleaseTotal(new BigDecimal(0));
				blanketInfo.setAvailableBlanket(blanketInfo.getPoBlanket());
			} else
			{
				String queryString = "Select count(*), sum(poHeader.total), max(poHeader.lastRelease) from PoHeader as poHeader " + "where poHeader.poNumber = ? AND poHeader.lastRevision = 'C' AND " + "poHeader.status < '" + DocumentStatus.CANCELLED + "'";
				List resultList = dbs.query(queryString, new Object[] { poNumber }, new Type[] { Hibernate.STRING });

				if (resultList != null && resultList.size() > 0)
				{
					// Release Number
					Object data[] = (Object[]) resultList.get(0);
					Integer releaseInt = (Integer) data[0];
					Integer lastRelease = new Integer(data[2].toString());
					BigDecimal releases = new BigDecimal(releaseInt.intValue());
					blanketInfo.setReleaseCount(releases);

					// Total Released
					BigDecimal totalReleased = (BigDecimal) data[1];
					if (totalReleased != null)
					{
						blanketInfo.setReleaseTotal(totalReleased);
						BigDecimal availableBlanket = blanketInfo.getPoBlanket().subtract(totalReleased);

						if (availableBlanket.compareTo(new BigDecimal(0)) == 1)
						{
							blanketInfo.setAvailableBlanket(availableBlanket);
						} else
						{
							blanketInfo.setAvailableBlanket(new BigDecimal(0));
						}
					} else
					{
						blanketInfo.setReleaseTotal(new BigDecimal(0));
						blanketInfo.setAvailableBlanket(blanketInfo.getPoBlanket());
					}

					blanketInfo.setLastRelease(lastRelease);

				} else
				{
					blanketInfo.setReleaseCount(new BigDecimal(0));
					blanketInfo.setReleaseTotal(new BigDecimal(0));
					blanketInfo.setAvailableBlanket(blanketInfo.getPoBlanket());
				}
			}

			ret = blanketInfo;
			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return ret;
	}
}