package com.tsa.puridiom.punchout.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.tsa.puridiom.entity.Punchout;

public class PunchoutRetrieve extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
		public Object  executeTask (Object object) throws Exception
		{
			Map incomingRequest = (Map)object;
			Object result = null;

			try
			{
				String punchoutIdString	= (String) incomingRequest.get("punchoutId");

				if (punchoutIdString != null && !punchoutIdString.equals("0"))
				{
					DBSession dbs = (DBSession)incomingRequest.get("dbsession");

					BigDecimal punchoutId = new BigDecimal (punchoutIdString);

					String queryString = "from Punchout as Punchout where icPunchout = ? ";
					List resultList = dbs.query(queryString, new Object[] {punchoutId} , new Type[] {Hibernate.BIG_DECIMAL});

					if (resultList != null && resultList.size() > 0)
					{
						result = resultList.get(0);
					}
					this.setStatus(dbs.getStatus());

					try{
						Object obj = resultList.get(0);
						Punchout punchout = (Punchout) obj;
						incomingRequest.put("punchout", punchout);
						dbs.update(punchout);
					}
					catch (Exception e){
						incomingRequest.put("punchout", null);
					}
				}
				else {
					this.setStatus(Status.SUCCEEDED);
					incomingRequest.put("punchout", null);
				}

			}
			catch (Exception e)
			{
				this.setStatus(Status.FAILED);
				throw e;
			}
			return result;
			}

	}