package com.tsa.puridiom.vendorquestion.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
import org.hibernate.Hibernate;

public class VendorQuestionRetrieveById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icVendorQuestion = (String) incomingRequest.get("VendorQuestion_icVendorQuestion");
			
			if (HiltonUtility.isEmpty(icVendorQuestion)) {
			    	throw new Exception("VendorQuestion_icVendorQuestion cannot be empty.");
			}
	
			String	query = "from VendorQuestion as vendorquestion where vendorquestion.icVendorQuestion = ?";
	
			List resultList = dbs.query(query, icVendorQuestion, Hibernate.STRING) ;
			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result ;
	}
}
