package com.tsa.puridiom.vendorquestion.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class VendorQuestionRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from VendorQuestion as vendorquestion where 1=1 ");
		if(incomingRequest.containsKey("VendorQuestion_icVendorQuestion"))
		{			
			String icVendorQuestion = (String) incomingRequest.get("VendorQuestion_icVendorQuestion");
			queryString.append(" AND vendorquestion.id.icVendorQuestion = '" + icVendorQuestion + "'");
		}
		if(incomingRequest.containsKey("VendorQuestion_icRfqHeader"))
		{			
			String icRfqHeader = (String) incomingRequest.get("VendorQuestion_icRfqHeader");
			queryString.append(" AND vendorquestion.icRfqHeader = '" + icRfqHeader + "'");
		}
		if(incomingRequest.containsKey("VendorQuestion_vendorId"))
		{			
			String vendorId = (String) incomingRequest.get("VendorQuestion_vendorId");
			queryString.append(" AND vendorquestion.vendorId = '" + vendorId + "'");
		}
		if(incomingRequest.containsKey("VendorQuestion_questionTitle"))
		{			
			String questionTitle = (String) incomingRequest.get("VendorQuestion_questionTitle");
			queryString.append(" AND vendorquestion.questionTitle = '" + questionTitle + "'");
		}
		if(incomingRequest.containsKey("VendorQuestion_questionText"))
		{			
			String questionText = (String) incomingRequest.get("VendorQuestion_questionText");
			queryString.append(" AND vendorquestion.questionText = '" + questionText + "'");
		}
		if(incomingRequest.containsKey("VendorQuestion_datePosted"))
		{			
			String datePosted = (String) incomingRequest.get("VendorQuestion_datePosted");
			queryString.append(" AND vendorquestion.datePosted = '" + datePosted + "'");
		}
		if(incomingRequest.containsKey("VendorQuestion_timePosted"))
		{			
			String timePosted = (String) incomingRequest.get("VendorQuestion_timePosted");
			queryString.append(" AND vendorquestion.timePosted = '" + timePosted + "'");
		}
		if(incomingRequest.containsKey("VendorQuestion_responseText"))
		{			
			String responseText = (String) incomingRequest.get("VendorQuestion_responseText");
			queryString.append(" AND vendorquestion.responseText = '" + responseText + "'");
		}
		if(incomingRequest.containsKey("VendorQuestion_responseDate"))
		{			
			String responseDate = (String) incomingRequest.get("VendorQuestion_responseDate");
			queryString.append(" AND vendorquestion.responseDate = '" + responseDate + "'");
		}
		if(incomingRequest.containsKey("VendorQuestion_responseTime"))
		{			
			String responseTime = (String) incomingRequest.get("VendorQuestion_responseTime");
			queryString.append(" AND vendorquestion.responseTime = '" + responseTime + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
