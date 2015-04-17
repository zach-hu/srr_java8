/*
 * Created on March 11, 2004
 * @author Kelli
 * com.tsa.puridiom.tasks.contactContactRetrieveByEmailAddr.java
 */

package com.tsa.puridiom.contact.tasks;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.common.utility.HiltonUtility;
import java.util.List;
import java.util.Map;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import com.tsagate.foundation.utility.Utility;


public class ContactRetrieveByEmailAddr extends Task
{
	/**
	 * Method executeTask.
	 *
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String mailId = (String ) incomingRequest.get("Contact_emailAddr");

			mailId = Utility.ckNull(mailId).toLowerCase();

			String queryString = "from Contact as Contact where Contact.emailAddr = ? ";
			List resultList = dbs.query(queryString, new Object[] {mailId } , new Type[] { Hibernate.STRING}) ;

			if (resultList != null && resultList.size() > 0)
			{
				ret = resultList.get(0);
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
		    throw e;
		}
		return ret;
	}
}
