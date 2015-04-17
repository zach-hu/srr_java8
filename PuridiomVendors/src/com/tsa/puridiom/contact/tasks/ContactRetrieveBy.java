package com.tsa.puridiom.contact.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class ContactRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from Contact as contact where 1=1 ");
		if(incomingRequest.containsKey("Contact_contactCode"))
		{			
			String contactCode = (String) incomingRequest.get("Contact_contactCode");
			queryString.append(" AND contact.id.contactCode = '" + contactCode + "'");
		}
		if(incomingRequest.containsKey("Contact_contactType"))
		{			
			String contactType = (String) incomingRequest.get("Contact_contactType");
			queryString.append(" AND contact.id.contactType = '" + contactType + "'");
		}
		if(incomingRequest.containsKey("Contact_vendorId"))
		{			
			String vendorId = (String) incomingRequest.get("Contact_vendorId");
			queryString.append(" AND contact.id.vendorId = '" + vendorId + "'");
		}
		if(incomingRequest.containsKey("Contact_lastName"))
		{			
			String lastName = (String) incomingRequest.get("Contact_lastName");
			queryString.append(" AND contact.lastName = '" + lastName + "'");
		}
		if(incomingRequest.containsKey("Contact_firstName"))
		{			
			String firstName = (String) incomingRequest.get("Contact_firstName");
			queryString.append(" AND contact.firstName = '" + firstName + "'");
		}
		if(incomingRequest.containsKey("Contact_middleInit"))
		{			
			String middleInit = (String) incomingRequest.get("Contact_middleInit");
			queryString.append(" AND contact.middleInit = '" + middleInit + "'");
		}
		if(incomingRequest.containsKey("Contact_salutation"))
		{			
			String salutation = (String) incomingRequest.get("Contact_salutation");
			queryString.append(" AND contact.salutation = '" + salutation + "'");
		}
		if(incomingRequest.containsKey("Contact_contactTitle"))
		{			
			String contactTitle = (String) incomingRequest.get("Contact_contactTitle");
			queryString.append(" AND contact.contactTitle = '" + contactTitle + "'");
		}
		if(incomingRequest.containsKey("Contact_phoneNumber"))
		{			
			String phoneNumber = (String) incomingRequest.get("Contact_phoneNumber");
			queryString.append(" AND contact.phoneNumber = '" + phoneNumber + "'");
		}
		if(incomingRequest.containsKey("Contact_phoneFormat"))
		{			
			String phoneFormat = (String) incomingRequest.get("Contact_phoneFormat");
			queryString.append(" AND contact.phoneFormat = '" + phoneFormat + "'");
		}
		if(incomingRequest.containsKey("Contact_faxNumber"))
		{			
			String faxNumber = (String) incomingRequest.get("Contact_faxNumber");
			queryString.append(" AND contact.faxNumber = '" + faxNumber + "'");
		}
		if(incomingRequest.containsKey("Contact_faxFormat"))
		{			
			String faxFormat = (String) incomingRequest.get("Contact_faxFormat");
			queryString.append(" AND contact.faxFormat = '" + faxFormat + "'");
		}
		if(incomingRequest.containsKey("Contact_addressCode"))
		{			
			String addressCode = (String) incomingRequest.get("Contact_addressCode");
			queryString.append(" AND contact.addressCode = '" + addressCode + "'");
		}
		if(incomingRequest.containsKey("Contact_dateEntered"))
		{			
			String dateEntered = (String) incomingRequest.get("Contact_dateEntered");
			queryString.append(" AND contact.dateEntered = '" + dateEntered + "'");
		}
		if(incomingRequest.containsKey("Contact_dateExpires"))
		{			
			String dateExpires = (String) incomingRequest.get("Contact_dateExpires");
			queryString.append(" AND contact.dateExpires = '" + dateExpires + "'");
		}
		if(incomingRequest.containsKey("Contact_status"))
		{			
			String status = (String) incomingRequest.get("Contact_status");
			queryString.append(" AND contact.status = '" + status + "'");
		}
		if(incomingRequest.containsKey("Contact_owner"))
		{			
			String owner = (String) incomingRequest.get("Contact_owner");
			queryString.append(" AND contact.owner = '" + owner + "'");
		}
		if(incomingRequest.containsKey("Contact_emailAddr"))
		{			
			String emailAddr = (String) incomingRequest.get("Contact_emailAddr");
			queryString.append(" AND contact.emailAddr = '" + emailAddr + "'");
		}
		if(incomingRequest.containsKey("Contact_info1"))
		{			
			String info1 = (String) incomingRequest.get("Contact_info1");
			queryString.append(" AND contact.info1 = '" + info1 + "'");
		}
		if(incomingRequest.containsKey("Contact_info2"))
		{			
			String info2 = (String) incomingRequest.get("Contact_info2");
			queryString.append(" AND contact.info2 = '" + info2 + "'");
		}
		if(incomingRequest.containsKey("Contact_info3"))
		{			
			String info3 = (String) incomingRequest.get("Contact_info3");
			queryString.append(" AND contact.info3 = '" + info3 + "'");
		}
		if(incomingRequest.containsKey("Contact_info4"))
		{			
			String info4 = (String) incomingRequest.get("Contact_info4");
			queryString.append(" AND contact.info4 = '" + info4 + "'");
		}
		if(incomingRequest.containsKey("Contact_contactPassword"))
		{			
			String contactPassword = (String) incomingRequest.get("Contact_contactPassword");
			queryString.append(" AND contact.contactPassword = '" + contactPassword + "'");
		}
		if(incomingRequest.containsKey("Contact_passChanged"))
		{			
			String passChanged = (String) incomingRequest.get("Contact_passChanged");
			queryString.append(" AND contact.passChanged = '" + passChanged + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}