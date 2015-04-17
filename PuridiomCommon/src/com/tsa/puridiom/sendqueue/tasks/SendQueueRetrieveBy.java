package com.tsa.puridiom.sendqueue.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class SendQueueRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from SendQueue as sendqueue where 1=1 ");
		if(incomingRequest.containsKey("SendQueue_queueid"))
		{			
			String queueid = (String) incomingRequest.get("SendQueue_queueid");
			queryString.append(" AND sendqueue.id.queueid = '" + queueid + "'");
		}
		if(incomingRequest.containsKey("SendQueue_doctype"))
		{			
			String doctype = (String) incomingRequest.get("SendQueue_doctype");
			queryString.append(" AND sendqueue.doctype = '" + doctype + "'");
		}
		if(incomingRequest.containsKey("SendQueue_docic"))
		{			
			String docic = (String) incomingRequest.get("SendQueue_docic");
			queryString.append(" AND sendqueue.docic = '" + docic + "'");
		}
		if(incomingRequest.containsKey("SendQueue_subject"))
		{			
			String subject = (String) incomingRequest.get("SendQueue_subject");
			queryString.append(" AND sendqueue.subject = '" + subject + "'");
		}
		if(incomingRequest.containsKey("SendQueue_messagetext"))
		{			
			String messagetext = (String) incomingRequest.get("SendQueue_messagetext");
			queryString.append(" AND sendqueue.messagetext = '" + messagetext + "'");
		}
		if(incomingRequest.containsKey("SendQueue_owner"))
		{			
			String owner = (String) incomingRequest.get("SendQueue_owner");
			queryString.append(" AND sendqueue.owner = '" + owner + "'");
		}
		if(incomingRequest.containsKey("SendQueue_sendfromtype"))
		{			
			String sendfromtype = (String) incomingRequest.get("SendQueue_sendfromtype");
			queryString.append(" AND sendqueue.sendfromtype = '" + sendfromtype + "'");
		}
		if(incomingRequest.containsKey("SendQueue_sendfrom"))
		{			
			String sendfrom = (String) incomingRequest.get("SendQueue_sendfrom");
			queryString.append(" AND sendqueue.sendfrom = '" + sendfrom + "'");
		}
		if(incomingRequest.containsKey("SendQueue_sendtotype"))
		{			
			String sendtotype = (String) incomingRequest.get("SendQueue_sendtotype");
			queryString.append(" AND sendqueue.sendtotype = '" + sendtotype + "'");
		}
		if(incomingRequest.containsKey("SendQueue_sendto"))
		{			
			String sendto = (String) incomingRequest.get("SendQueue_sendto");
			queryString.append(" AND sendqueue.sendto = '" + sendto + "'");
		}
		if(incomingRequest.containsKey("SendQueue_status"))
		{			
			String status = (String) incomingRequest.get("SendQueue_status");
			queryString.append(" AND sendqueue.status = '" + status + "'");
		}
		if(incomingRequest.containsKey("SendQueue_dateadded"))
		{			
			String dateadded = (String) incomingRequest.get("SendQueue_dateadded");
			queryString.append(" AND sendqueue.dateadded = '" + dateadded + "'");
		}
		if(incomingRequest.containsKey("SendQueue_timeadded"))
		{			
			String timeadded = (String) incomingRequest.get("SendQueue_timeadded");
			queryString.append(" AND sendqueue.timeadded = '" + timeadded + "'");
		}
		if(incomingRequest.containsKey("SendQueue_action"))
		{			
			String action = (String) incomingRequest.get("SendQueue_action");
			queryString.append(" AND sendqueue.action = '" + action + "'");
		}
		if(incomingRequest.containsKey("SendQueue_datesent"))
		{			
			String datesent = (String) incomingRequest.get("SendQueue_datesent");
			queryString.append(" AND sendqueue.datesent = '" + datesent + "'");
		}
		if(incomingRequest.containsKey("SendQueue_timesent"))
		{			
			String timesent = (String) incomingRequest.get("SendQueue_timesent");
			queryString.append(" AND sendqueue.timesent = '" + timesent + "'");
		}
		if(incomingRequest.containsKey("SendQueue_attachment"))
		{			
			String attachment = (String) incomingRequest.get("SendQueue_attachment");
			queryString.append(" AND sendqueue.attachment = '" + attachment + "'");
		}
		if(incomingRequest.containsKey("SendQueue_vendorId"))
		{			
			String vendorId = (String) incomingRequest.get("SendQueue_vendorId");
			queryString.append(" AND sendqueue.vendorId = '" + vendorId + "'");
		}
		if(incomingRequest.containsKey("SendQueue_attempts"))
		{			
			String attempts = (String) incomingRequest.get("SendQueue_attempts");
			queryString.append(" AND sendqueue.attempts = '" + attempts + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}