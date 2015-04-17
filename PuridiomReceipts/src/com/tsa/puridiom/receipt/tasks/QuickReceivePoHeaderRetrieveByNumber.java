package com.tsa.puridiom.receipt.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class QuickReceivePoHeaderRetrieveByNumber extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List result = new ArrayList();

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String totalOrdersString = (String)incomingRequest.get("totalOrders") ;
			int totalOrders = 10;
			if(!HiltonUtility.isEmpty(totalOrdersString)){
				totalOrders = Integer.parseInt(totalOrdersString);
			}
			String poNumberString = "";
			String poNumber = "";
			List poNumberList = new ArrayList();

			for (int i = 1; i <= totalOrders; i++)
			{
				poNumberString = "PoNumber" + i;
				poNumber = Utility.ckNull((String) incomingRequest.get(poNumberString));
				poNumberList.add(poNumber);

				String queryString = "from PoHeader as PoHeader where PoHeader.poNumber = ? AND PoHeader.lastRevision = 'C'";
				List resultList = dbs.query(queryString, new Object[] { poNumber} , new Type[] { Hibernate.STRING});

				if (resultList != null && resultList.size() > 0)
				{
					queryString = "from PoHeader as PoHeader where PoHeader.poNumber = ? AND PoHeader.status = '3030' AND PoHeader.lastRevision = 'C' AND PoHeader.subType = '04' ";
					resultList = dbs.query(queryString, new Object[] { poNumber} , new Type[] { Hibernate.STRING});

					if (resultList != null && resultList.size() > 0)
					{
						result.add(resultList.get(0));
					}
					else
					{
						queryString = "from PoHeader as PoHeader where PoHeader.poNumber = ? AND PoHeader.status = '4100' AND PoHeader.lastRevision = 'C' AND PoHeader.subType = '04' ";
						resultList = dbs.query(queryString, new Object[] { poNumber} , new Type[] { Hibernate.STRING});

						if (resultList != null && resultList.size() > 0)
						{
							result.add(resultList.get(0));
						}
						else
						{
							result.add("NE");
						}
					}
				}
				else
				{
					result.add("");
				}
			}
			this.setStatus(dbs.getStatus()) ;
			incomingRequest.put("poNumberList", poNumberList);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}