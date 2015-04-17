/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.labels.tasks;

import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator
 */
public class LabelsRetrieveByUniqueKey extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
	        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
	        StringBuffer queryString = new StringBuffer("from Labels as labels where 1 = 1 ");
	        
	        String labelCode = ((String) incomingRequest.get("Labels_labelCode")).toUpperCase();
	        String module = (String) incomingRequest.get("Labels_module");
	        String moduleType = (String) incomingRequest.get("Labels_moduleType");

	        queryString.append(" and upper(labels.labelCode) = '" + labelCode + "'");
	        
	        if (HiltonUtility.isEmpty(module)) {
		        queryString.append(" and (labels.module = '' or labels.module is null)");
	        } else {
		        queryString.append(" and labels.module = '" + module + "'");
	        }
	        if (HiltonUtility.isEmpty(moduleType)) {
		        queryString.append(" and (labels.moduleType = '' or labels.moduleType is null)");
	        } else {
		        queryString.append(" and labels.moduleType = '" + moduleType + "'");
	        }
	        
	        List resultList = dbs.query(queryString.toString()) ;

	        if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			e.printStackTrace();
		}

		return result ;
	}
}
