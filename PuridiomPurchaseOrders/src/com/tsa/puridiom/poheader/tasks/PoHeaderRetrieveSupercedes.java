package com.tsa.puridiom.poheader.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class PoHeaderRetrieveSupercedes extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			if( incomingRequest.get("poHeader") != null )
			{
				PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
				if ( !HiltonUtility.isEmpty(poHeader.getLinkPriorOrder()) )
				{
					String displayPoNumber = poHeader.getLinkPriorOrder();
					displayPoNumber = displayPoNumber.replaceAll("Order ", "");
					displayPoNumber = displayPoNumber.replaceAll("-", " ");
					displayPoNumber = displayPoNumber.replaceAll(" Revision","");
					displayPoNumber = displayPoNumber.replaceAll(" Rev","");
					String[] parameters = displayPoNumber.split(" ");
					String poNumber = parameters[0];
					String releaseNumber = "0";
					String revisionNumber = "0";
					if(parameters.length > 1)
					{
						releaseNumber = parameters[1];

						if (parameters.length > 2)
						{
							revisionNumber = parameters[2];
						}
					}

					String queryString = "select PoHeader.icPoHeader from PoHeader as PoHeader " +
							" where PoHeader.poNumber = ? and PoHeader.releaseNumber = ? and PoHeader.revisionNumber = ? and PoHeader.lastRevision = 'C' ";
					List resultList = dbs.query(queryString, new Object[] {poNumber, releaseNumber, revisionNumber } , new Type[] { Hibernate.STRING, Hibernate.STRING, Hibernate.STRING }) ;

					if (resultList != null && resultList.size() > 0)
					{
						result = resultList.get(0);
					}
					if (result != null)
					{
						BigDecimal linkPriorOrder_icPoHeader = (BigDecimal) result;
						incomingRequest.put("linkPriorOrder_icPoHeader", linkPriorOrder_icPoHeader);
					}
				}
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