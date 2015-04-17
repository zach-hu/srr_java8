/*
 * Created on Nov 20, 2003
 */
package com.tsa.puridiom.requisition.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.*;

/**
 * @author Kelli
*/
public class RequisitionValidateNumber extends Task {

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;

		try {
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String	requisitionNumber = (String) incomingRequest.get("RequisitionHeader_requisitionNumber");

			String accountPref = (String)incomingRequest.get("accountPref");

            if (!HiltonUtility.isEmpty(accountPref))
			{
            	requisitionNumber = accountPref+requisitionNumber;
            	incomingRequest.put("RequisitionHeader_requisitionNumber", requisitionNumber);
			}

			String queryString = "select RequisitionHeader.requisitionNumber from RequisitionHeader as RequisitionHeader where RequisitionHeader.requisitionNumber = ?";
			List resultList = dbs.query(queryString, new Object[] {requisitionNumber} , new Type[] { Hibernate.STRING}) ;

			if (resultList != null && resultList.size() > 0)
			{
				throw new Exception("Duplicate Requisition Number Requested!");
			}
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result ;
	}

}
