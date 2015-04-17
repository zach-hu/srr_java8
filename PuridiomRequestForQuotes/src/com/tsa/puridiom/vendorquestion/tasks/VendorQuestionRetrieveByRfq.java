package com.tsa.puridiom.vendorquestion.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
import org.hibernate.Hibernate;

public class VendorQuestionRetrieveByRfq extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String icRfqHeader = (String) incomingRequest.get("VendorQuestion_icRfqHeader");
		
		if (HiltonUtility.isEmpty(icRfqHeader)) {
		    	throw new Exception("VendorQuestion_icRfqHeader cannot be empty.");
		}

		String	query = "from VendorQuestion as vendorquestion where vendorquestion.icRfqHeader = ?";

		List result = dbs.query(query, icRfqHeader, Hibernate.STRING) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
