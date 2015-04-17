package com.tsa.puridiom.contact.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ContactSelectMax extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String vendorId = (String ) incomingRequest.get("Contact_vendorId");

			String queryString = "Select max(contact.id.contactCode) from Contact as contact where contact.id.vendorId = ? ";
			List resultList = dbs.query(queryString, new Object[] {vendorId } , new Type[] { Hibernate.STRING}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
				if (result != null)
				{
					String currentCode = (String)result;
					BigDecimal next = (new BigDecimal(currentCode)).add(new BigDecimal(1));
					StringBuffer nextCode = new StringBuffer(next.toString());
					int needed = 3 - nextCode.length();
					for(int i = 0; i < needed; i++)
					{
						nextCode.insert(0, "0");
					}
					result = nextCode.toString();
				}
			}
			
			if (Utility.isEmpty((String) result))
			{
				result = "001";
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			e.printStackTrace();
			Log.error(this, e.toString());
		}
		return result;
	}

}