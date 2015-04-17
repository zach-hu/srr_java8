/*
 * Created on Nov 20, 2003
 */
package com.tsa.puridiom.po.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.*;

/**
 * @author Renzo
*/
public class PoValidateNumber extends Task {

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
			String	disbNumber = (String) incomingRequest.get("PoHeader_poNumber");

			String accountPref = (String)incomingRequest.get("accountPref");

            if (!HiltonUtility.isEmpty(accountPref))
			{
            	disbNumber = accountPref+disbNumber;
            	incomingRequest.put("PoHeader_poNumber", disbNumber);
			}

			String queryString = "select poHeader.poNumber from PoHeader as poHeader where poHeader.poNumber = ?";
			List resultList = dbs.query(queryString, new Object[] {disbNumber} , new Type[] { Hibernate.STRING}) ;

			if (resultList != null && resultList.size() > 0)
			{
				throw new Exception("Duplicate PO Number Requested!");
			}
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, e.toString());
		}
		return result ;
	}

}
