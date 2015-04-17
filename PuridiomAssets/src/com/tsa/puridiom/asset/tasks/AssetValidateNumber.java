/*
 * Created on Nov 20, 2003
 */
package com.tsa.puridiom.asset.tasks;

import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.*;

public class AssetValidateNumber extends Task
{

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String	assetNumber = (String) incomingRequest.get("Asset_trackingNumber");

			String queryString = "select Asset.trackingNumber from Asset as Asset where Asset.trackingNumber = ?";
			List resultList = dbs.query(queryString, new Object[] {assetNumber} , new Type[] { Hibernate.STRING}) ;

			if (resultList != null && resultList.size() > 0)
			{
				throw new Exception("Duplicate Asset Number Obtained!");
			}
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result ;
	}

}
