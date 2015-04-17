package com.tsa.puridiom.sendalert.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class SendAlertRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from SendAlert as sendalert where 1=1 ");
		if(incomingRequest.containsKey("SendAlert_queueid"))
		{			
			String queueid = (String) incomingRequest.get("SendQueue_queueid");
			queryString.append(" AND sendalert.id.queueid = '" + queueid + "'");
		}
		if(incomingRequest.containsKey("SendAlert_doctype"))
		{			
			String doctype = (String) incomingRequest.get("SendAlert_doctype");
			queryString.append(" AND sendalert.doctype = '" + doctype + "'");
		}
		if(incomingRequest.containsKey("SendAlert_docic"))
		{			
			String docic = (String) incomingRequest.get("SendAlert_docic");
			queryString.append(" AND sendalert.docic = '" + docic + "'");
		}
		if(incomingRequest.containsKey("SendAlert_subject"))
		{			
			String subject = (String) incomingRequest.get("SendAlert_subject");
			queryString.append(" AND sendalert.subject = '" + subject + "'");
		}
		if(incomingRequest.containsKey("SendAlert_messagetext"))
		{			
			String messagetext = (String) incomingRequest.get("SendAlert_messagetext");
			queryString.append(" AND sendalert.messagetext = '" + messagetext + "'");
		}
		if(incomingRequest.containsKey("SendAlert_owner"))
		{			
			String owner = (String) incomingRequest.get("SendAlert_owner");
			queryString.append(" AND sendalert.owner = '" + owner + "'");
		}
		if(incomingRequest.containsKey("SendAlert_sendfromtype"))
		{			
			String sendfromtype = (String) incomingRequest.get("SendAlert_sendfromtype");
			queryString.append(" AND sendalert.sendfromtype = '" + sendfromtype + "'");
		}
		if(incomingRequest.containsKey("SendAlert_sendfrom"))
		{			
			String sendfrom = (String) incomingRequest.get("SendAlert_sendfrom");
			queryString.append(" AND sendalert.sendfrom = '" + sendfrom + "'");
		}
		if(incomingRequest.containsKey("SendAlert_sendtotype"))
		{			
			String sendtotype = (String) incomingRequest.get("SendAlert_sendtotype");
			queryString.append(" AND sendalert.sendtotype = '" + sendtotype + "'");
		}
		if(incomingRequest.containsKey("SendAlert_sendto"))
		{			
			String sendto = (String) incomingRequest.get("SendAlert_sendto");
			queryString.append(" AND sendalert.sendto = '" + sendto + "'");
		}
		if(incomingRequest.containsKey("SendAlert_status"))
		{			
			String status = (String) incomingRequest.get("SendAlert_status");
			queryString.append(" AND sendalert.status = '" + status + "'");
		}
		if(incomingRequest.containsKey("SendAlert_dateadded"))
		{			
			String dateadded = (String) incomingRequest.get("SendAlert_dateadded");
			queryString.append(" AND sendalert.dateadded = '" + dateadded + "'");
		}
		if(incomingRequest.containsKey("SendAlert_timeadded"))
		{			
			String timeadded = (String) incomingRequest.get("SendAlert_timeadded");
			queryString.append(" AND sendalert.timeadded = '" + timeadded + "'");
		}
		if(incomingRequest.containsKey("SendAlert_action"))
		{			
			String action = (String) incomingRequest.get("SendAlert_action");
			queryString.append(" AND sendalert.action = '" + action + "'");
		}
		if(incomingRequest.containsKey("SendAlert_datesent"))
		{			
			String datesent = (String) incomingRequest.get("SendAlert_datesent");
			queryString.append(" AND sendalert.datesent = '" + datesent + "'");
		}
		if(incomingRequest.containsKey("SendAlert_timesent"))
		{			
			String timesent = (String) incomingRequest.get("SendAlert_timesent");
			queryString.append(" AND sendalert.timesent = '" + timesent + "'");
		}
		if(incomingRequest.containsKey("SendAlert_attachment"))
		{			
			String attachment = (String) incomingRequest.get("SendAlert_attachment");
			queryString.append(" AND sendalert.attachment = '" + attachment + "'");
		}
		if(incomingRequest.containsKey("SendAlert_vendorId"))
		{			
			String vendorId = (String) incomingRequest.get("SendAlert_vendorId");
			queryString.append(" AND sendalert.vendorId = '" + vendorId + "'");
		}
		if(incomingRequest.containsKey("SendAlert_attempts"))
		{			
			String attempts = (String) incomingRequest.get("SendAlert_attempts");
			queryString.append(" AND sendalert.attempts = '" + attempts + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}